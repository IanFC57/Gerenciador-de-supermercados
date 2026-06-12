package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import exception.PersistenciaException;

public class VendaDAO {
	public void finalizarVenda(int clienteId, String clienteNome, double total, List<Object[]> itens) {
		String sqlVenda = "INSERT INTO venda (cliente_id, cliente_nome, data_venda, valor_total) "
				+ "VALUES (?, ?, NOW(), ?)";
		String sqlItem = "INSERT INTO item_venda (venda_id, produto_id, nome_produto, quantidade, preco_unitario) "
				+ "VALUES (?, ?, ?, ?, ?)";
		String sqlMov = "INSERT INTO movimentacao_estoque (produto_id, tipo, quantidade, data_movimentacao) "
				+ "VALUES (?, 'SAIDA', ?, NOW())";
		String sqlStock = "UPDATE produto SET qtd = qtd - ? WHERE id_produto = ?";

		try (Connection c = BancoDeDados.conectar()) {
			c.setAutoCommit(false);
			try {

				int vendaId;
				try (PreparedStatement ps = c.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {
					if (clienteId > 0)
						ps.setInt(1, clienteId);
					else
						ps.setNull(1, Types.INTEGER);
					ps.setString(2, clienteNome);
					ps.setDouble(3, total);
					ps.executeUpdate();
					try (ResultSet keys = ps.getGeneratedKeys()) {
						keys.next();
						vendaId = keys.getInt(1);
					}
				}

				for (Object[] it : itens) {
					int prodId = (int) it[0];
					String nome = (String) it[1];
					int qtd = (int) it[2];
					double preco = (double) it[3];

					try (PreparedStatement ps = c.prepareStatement(sqlItem)) {
						ps.setInt(1, vendaId);
						ps.setInt(2, prodId);
						ps.setString(3, nome);
						ps.setInt(4, qtd);
						ps.setDouble(5, preco);
						ps.executeUpdate();
					}
					try (PreparedStatement ps = c.prepareStatement(sqlMov)) {
						ps.setInt(1, prodId);
						ps.setInt(2, qtd);
						ps.executeUpdate();
					}
					try (PreparedStatement ps = c.prepareStatement(sqlStock)) {
						ps.setInt(1, qtd);
						ps.setInt(2, prodId);
						ps.executeUpdate();
					}
				}

				c.commit();
			} catch (Exception ex) {
				c.rollback();
				throw ex;
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao finalizar venda.", e);
		}
	}

	public List<Venda> listarVendas() {
		List<Venda> lista = new ArrayList<>();
		String sql = "SELECT * FROM venda ORDER BY data_venda DESC";
		try (Connection c = BancoDeDados.conectar();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Timestamp ts = rs.getTimestamp("data_venda");
				lista.add(new Venda(rs.getInt("id"), rs.getInt("cliente_id"), rs.getString("cliente_nome"),
						ts != null ? ts.toLocalDateTime() : LocalDateTime.now(), rs.getDouble("valor_total")));
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao listar vendas.", e);
		}
		return lista;
	}

	public List<ItemVenda> listarItensDaVenda(int vendaId) {
		List<ItemVenda> itens = new ArrayList<>();
		String sql = "SELECT * FROM item_venda WHERE venda_id = ?";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, vendaId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					itens.add(new ItemVenda(rs.getInt("id"), rs.getInt("venda_id"), rs.getInt("produto_id"),
							rs.getString("nome_produto"), rs.getInt("quantidade"), rs.getDouble("preco_unitario")));
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao listar itens da venda.", e);
		}
		return itens;
	}
}
