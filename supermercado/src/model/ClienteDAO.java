package model;

import java.sql.*;

public class ClienteDAO {
	public void adicionarCliente(Cliente cliente) {
		String sql = "INSERT INTO usuario (nome_usuario, cpf, is_admin) VALUES (?, ?, ?)";
		try (Connection conexao = BancoDeDados.conectar(); PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getCPF());
			pstm.setBoolean(3, cliente.isAdmin());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Cliente buscarPorCPF(String cpf) {
		String sql = "SELECT * FROM usuario WHERE cpf = ?";
		try (Connection conexao = BancoDeDados.conectar(); PreparedStatement pstm = conexao.prepareStatement(sql)) {

			pstm.setString(1, cpf);
			try (ResultSet rs = pstm.executeQuery()) {
				if (rs.next()) {
					return new Cliente(rs.getString("nome_usuario"), rs.getString("cpf"), rs.getBoolean("is_admin"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}