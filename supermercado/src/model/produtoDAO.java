package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import exception.PersistenciaException;

public class produtoDAO {

	public void adicionarProduto(Produto produto) {
		String sql = "INSERT INTO produto (nome_produto, qtd, preco) VALUES (?, ?, ?)";
		try (Connection conexao = BancoDeDados.conectar(); 
		     PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setString(1, produto.getNomeProduto());
			pstm.setInt(2, produto.getQtd());
			pstm.setDouble(3, produto.getPrecoUnitario());
			pstm.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao inserir o produto na base de dados.", e);
		}
	}

	public List<Produto> listarTodos() {
		List<Produto> produtos = new ArrayList<>();
		String sql = "SELECT * FROM produto";

		try (Connection conexao = BancoDeDados.conectar();
			 PreparedStatement pstm = conexao.prepareStatement(sql);
			 ResultSet rs = pstm.executeQuery()) {

			while (rs.next()) {
				Produto p = new Produto(rs.getInt("id_produto"), rs.getString("nome_produto"), 
				                        rs.getDouble("preco"), rs.getInt("qtd"));
				produtos.add(p);
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao carregar a lista de produtos.", e);
		}
		return produtos;
	}

	public void baixarEstoque(int idProduto, int quantidadeComprada) {
		String sql = "UPDATE produto SET qtd = qtd - ? WHERE id_produto = ?";
		try (Connection conexao = BancoDeDados.conectar(); 
		     PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setInt(1, quantidadeComprada);
			pstm.setInt(2, idProduto);
			pstm.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao deduzir o stock do produto no sistema.", e);
		}
	}

	public void excluirProduto(int idProduto) {
		String sql = "DELETE FROM produto WHERE id_produto = ?";
		try (Connection conexao = BancoDeDados.conectar();
			 PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setInt(1, idProduto);
			pstm.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao excluir o produto selecionado.", e);
		}
	}
}