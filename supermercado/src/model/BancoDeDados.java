package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import exception.PersistenciaException;

public class BancoDeDados {
	private static final String URL = "jdbc:mysql://localhost:3306/db_supermercado";
	private static final String USUARIO = "root";
	private static final String SENHA = "admin";

	public static Connection conectar() {
		try {
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			throw new PersistenciaException("Falha ao ligar à base de dados. Verifique a sua conexão local.", e);
		}
	}

	public static void desconectar(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new PersistenciaException("Falha ao fechar a ligação à base de dados.", e);
			}
		}
	}
}