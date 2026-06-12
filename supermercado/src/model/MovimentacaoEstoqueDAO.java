package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import exception.PersistenciaException;
import exception.ValidacaoException;

public class MovimentacaoEstoqueDAO {

	public void registrarEntrada(int produtoId, int quantidade) {
		if (quantidade <= 0)
			throw new ValidacaoException("A quantidade de entrada deve ser maior que zero.");
		salvarMovimentacao(produtoId, "ENTRADA", quantidade);
		atualizarEstoque(produtoId, +quantidade);
	}

	public void registrarSaida(int produtoId, int quantidade) {
		if (quantidade <= 0)
			throw new ValidacaoException("A quantidade de saída deve ser maior que zero.");
		verificarEstoque(produtoId, quantidade);
		salvarMovimentacao(produtoId, "SAIDA", quantidade);
		atualizarEstoque(produtoId, -quantidade);
	}

	public void registrarSaidaHistorico(Connection conn, int produtoId, int quantidade) throws SQLException {
		String sql = "INSERT INTO movimentacao_estoque (produto_id, tipo, quantidade, data_movimentacao) "
				+ "VALUES (?, 'SAIDA', ?, NOW())";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, produtoId);
			ps.setInt(2, quantidade);
			ps.executeUpdate();
		}
	}

	public List<MovimentacaoEstoque> listarMovimentacoes() {
		List<MovimentacaoEstoque> lista = new ArrayList<>();
		String sql = "SELECT me.*, p.nome_produto " + "FROM movimentacao_estoque me "
				+ "JOIN produto p ON me.produto_id = p.id_produto " + "ORDER BY me.data_movimentacao DESC";
		try (Connection c = BancoDeDados.conectar();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Timestamp ts = rs.getTimestamp("data_movimentacao");
				lista.add(new MovimentacaoEstoque(rs.getInt("id"), rs.getInt("produto_id"),
						rs.getString("nome_produto"), rs.getString("tipo"), rs.getInt("quantidade"),
						ts != null ? ts.toLocalDateTime() : LocalDateTime.now()));
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao listar movimentações.", e);
		}
		return lista;
	}

	private void salvarMovimentacao(int produtoId, String tipo, int quantidade) {
		String sql = "INSERT INTO movimentacao_estoque (produto_id, tipo, quantidade, data_movimentacao) "
				+ "VALUES (?, ?, ?, NOW())";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, produtoId);
			ps.setString(2, tipo);
			ps.setInt(3, quantidade);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao registrar movimentação.", e);
		}
	}

	private void atualizarEstoque(int produtoId, int delta) {
		String sql = "UPDATE produto SET qtd = qtd + ? WHERE id_produto = ?";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, delta);
			ps.setInt(2, produtoId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao atualizar estoque.", e);
		}
	}

	private void verificarEstoque(int produtoId, int qtdNecessaria) {
		String sql = "SELECT qtd FROM produto WHERE id_produto = ?";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, produtoId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					int qtdAtual = rs.getInt("qtd");
					if (qtdAtual < qtdNecessaria) {
						throw new ValidacaoException("Estoque insuficiente! Disponível: " + qtdAtual + " unidade(s).");
					}
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao verificar estoque.", e);
		}
	}
}
