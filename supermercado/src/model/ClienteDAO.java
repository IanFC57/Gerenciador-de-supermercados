package model;

import java.sql.*;
import exception.PersistenciaException;

public class ClienteDAO {

	public void adicionarCliente(Cliente cliente) {
		String sql = "INSERT INTO usuario (nome_usuario, cpf, is_admin, senha) VALUES (?, ?, ?, ?)";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCPF());
			ps.setBoolean(3, cliente.isAdmin());
			ps.setString(4, cliente.getSenhaHash());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao cadastrar: CPF já pode estar em uso.", e);
		}
	}

	public Cliente autenticar(String cpfLimpo, String senhaHash) {
		String sql = "SELECT * FROM usuario WHERE cpf = ?";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, cpfLimpo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String hashBanco = rs.getString("senha");

					if (hashBanco == null || hashBanco.isBlank())
						return null;

					if (hashBanco.equals(senhaHash)) {
						return mapear(rs);
					}
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao autenticar usuário.", e);
		}
		return null;
	}

	public Cliente buscarPorCPF(String cpfLimpo) {
		String sql = "SELECT * FROM usuario WHERE cpf = ?";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setString(1, cpfLimpo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return mapear(rs);
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao buscar usuário pelo CPF.", e);
		}
		return null;
	}

	private Cliente mapear(ResultSet rs) throws SQLException {
		return new Cliente(rs.getInt("id_usuario"), rs.getString("nome_usuario"), rs.getString("cpf"),
				rs.getBoolean("is_admin"), rs.getString("senha"));
	}
}
