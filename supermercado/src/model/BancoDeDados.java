package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDeDados {
	private static final String URL = "jdbc:mysql://localhost:3306/db_supermercado";
	private static final String USUARIO = "root";
	private static final String SENHA = "admin";

	public static Connection conectar() {
		try {
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void desconectar(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}