package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ClienteDAO {
	public void adicionarCliente(Cliente cliente) {
		String sql = "INSERT INTO usuarios (nome, CPF) VALUES (?, ?)";
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

}

