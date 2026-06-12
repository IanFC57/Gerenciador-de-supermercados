package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.PersistenciaException;

public class FornecedorDAO {

	public void salvar(Fornecedor f) {
		String sql = "INSERT INTO fornecedor (nome, cnpj, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, f.getNome());
			ps.setString(2, f.getCnpj());
			ps.setString(3, f.getTelefone());
			ps.setString(4, f.getEmail());
			ps.setString(5, f.getEndereco());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao salvar fornecedor.", e);
		}
	}

	public void editar(Fornecedor f) {
		String sql = "UPDATE fornecedor SET nome=?, cnpj=?, telefone=?, email=?, endereco=? WHERE id=?";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, f.getNome());
			ps.setString(2, f.getCnpj());
			ps.setString(3, f.getTelefone());
			ps.setString(4, f.getEmail());
			ps.setString(5, f.getEndereco());
			ps.setInt(6, f.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao editar fornecedor.", e);
		}
	}

	public void excluir(int id) {
		String sql = "DELETE FROM fornecedor WHERE id = ?";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao excluir fornecedor.", e);
		}
	}

	public Fornecedor buscarPorId(int id) {
		String sql = "SELECT * FROM fornecedor WHERE id = ?";
		try (Connection c = BancoDeDados.conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return mapear(rs);
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao buscar fornecedor.", e);
		}
		return null;
	}

	public List<Fornecedor> listarTodos() {
		List<Fornecedor> lista = new ArrayList<>();
		String sql = "SELECT * FROM fornecedor ORDER BY nome";
		try (Connection c = BancoDeDados.conectar();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next())
				lista.add(mapear(rs));
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao listar fornecedores.", e);
		}
		return lista;
	}

	private Fornecedor mapear(ResultSet rs) throws SQLException {
		return new Fornecedor(rs.getInt("id"), rs.getString("nome"), rs.getString("cnpj"), rs.getString("telefone"),
				rs.getString("email"), rs.getString("endereco"));
	}
}
