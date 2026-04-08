package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class produtoDAO {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/supermercado";
	 private static final String USUARIO = "root";
	 private static final String SENHA = "admin";
	
	public void adicionarProduto(Produto produto) {
		String sql = "INSERT INTO produto (nome_produto, qtd, preco) VALUES (?, ?, ?)";
		Connection conexao = null;
        PreparedStatement pstm = null;
	try {
        conexao = BancoDeDados.conectar();
        pstm = conexao.prepareStatement(sql);
        pstm.setString(1, produto.getNomeProduto());
        pstm.setString(2, produto.getQtd());
        pstm.setString(2, produto.getPrecoUnitario());
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






