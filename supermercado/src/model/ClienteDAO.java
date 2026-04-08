package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ClienteDAO {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/supermercado";
	 private static final String USUARIO = "root";
	 private static final String SENHA = "admin";
	    
	public void adicionarCliente(Cliente cliente) {
		String sql = "INSERT INTO usuario (nome, CPF) VALUES (?, ?)";
		Connection conexao = null;
        PreparedStatement pstm = null;
	try {
        conexao = BancoDeDados.conectar();
        pstm = conexao.prepareStatement(sql);
        pstm.setString(1, cliente.getNome());
        pstm.setString(2, cliente.getCPF());
        pstm.executeUpdate();
    }catch (SQLException e) {
        e.printStackTrace();
    } finally {
    	BancoDeDados.desconectar(conexao);
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
	
	public Cliente buscarPorCPF(String cpf) {
		String sql = "SELECT * FROM usuario WHERE CPF = ?";
		try (Connection conexao = BancoDeDados.conectar();
	             PreparedStatement pstm = conexao.prepareStatement(sql)) {
	            
	            pstm.setString(1, cpf);
	            
	            try (ResultSet rs = pstm.executeQuery()) {
	                if (rs.next()) {
	                    // Se encontrou o registro, cria o objeto Cliente com os dados do banco
	                    String nome = rs.getString("nome");
	                    String cpfBanco = rs.getString("CPF");
	                    
	                    return new Cliente(nome, cpfBanco);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return null; // Retorna null se não encontrar ninguém com esse CPF
	    }
	}


