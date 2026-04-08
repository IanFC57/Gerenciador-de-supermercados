package main;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import controller.CadastroController;
import controller.LoginController;
import controller.Navegador;
import model.ClienteDAO;
import view.Principal;
import view.TelaCadastro;
import view.TelaLogin;


public class Main {
	public static void main(String[] args) {
		UIManager.put("OptionPane.messageFont", new FontUIResource(
				new Font("Arial", Font.PLAIN, 18)
				));

		Principal principal = new Principal();
		Navegador navegador = new Navegador(principal);
		ClienteDAO clienteDAO = new ClienteDAO();

		TelaCadastro telaCadastro = new TelaCadastro();
		CadastroController cadastroController = new CadastroController(telaCadastro, clienteDAO, navegador);

		TelaLogin telalogin = new TelaLogin();
		LoginController logincontroller = new LoginController(telalogin, clienteDAO, navegador);
//		telalogin.adicionarOuvinte(logincontroller);
//
//		TelaCentralFuncionarios telaCentral = new TelaCentralFuncionarios();
//		CentralController centralController = new CentralController(telaCentral, clienteDAO, navegador);
//		telaCentral.adicionarOuvinte(centralController);
//
//		navegador.adicionarPainel("CADASTRO", telaCadastro);
//		navegador.adicionarPainel("CONTRATACAO", telaContratacao);
//		navegador.adicionarPainel("CENTRAL", telaCentral);
//
//		//Seta o jframe para abrir no meio da tela.
//		principal.setLocationRelativeTo(null);
//		principal.setVisible(true);

		navegador.navegarPara("CADASTRO");
	}
		
	}

