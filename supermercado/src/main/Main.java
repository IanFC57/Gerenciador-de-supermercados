package main;

import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import controller.CadastroController;
import controller.LoginController;
import controller.Navegador;
import controller.ProdutoController;
import model.ClienteDAO;
import model.produtoDAO;
import view.Principal;
import view.TelaCadastro;
import view.TelaCadastroProdutos;
import view.TelaLogin;
import view.TelaProdutos;


public class Main {
	public static void main(String[] args) {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.PLAIN, 18)));

		Principal principal = new Principal();
		Navegador navegador = new Navegador(principal);
		
        // Models
		ClienteDAO clienteDAO = new ClienteDAO();
		produtoDAO produtoDao = new produtoDAO();

        // Views
		TelaLogin telaLogin = new TelaLogin();
		TelaCadastro telaCadastro = new TelaCadastro();
        TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();
        TelaProdutos telaCompra = new TelaProdutos();

        // Controllers
		LoginController loginController = new LoginController(telaLogin, clienteDAO, navegador);
		CadastroController cadastroController = new CadastroController(telaCadastro, clienteDAO, navegador);
		ProdutoController produtoController = new ProdutoController(telaCadastroProdutos, produtoDao, navegador);

		// Adicionando Telas ao Navegador
		navegador.adicionarPainel("LOGIN", telaLogin);
		navegador.adicionarPainel("CADASTRO", telaCadastro);
        navegador.adicionarPainel("CADASTRO_PRODUTOS", telaCadastroProdutos);
        navegador.adicionarPainel("COMPRA", telaCompra);

		// Configurações finais do JFrame
		principal.setLocationRelativeTo(null);
		principal.setVisible(true);

        // Tela inicial exigida pelo projeto
		navegador.navegarPara("LOGIN");
		
		// ... dentro do seu public static void main ...

		// Models
//		ClienteDAO clienteDAO = new ClienteDAO();
//		produtoDAO produtoDao = new produtoDAO(); // <- NOVO
//
//		// Views
//		TelaLogin telaLogin = new TelaLogin();
//		TelaCadastro telaCadastro = new TelaCadastro();
//		TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();
//		TelaProdutos telaCompra = new TelaProdutos();
//
//		// Controllers
//		LoginController loginController = new LoginController(telaLogin, clienteDAO, navegador);
//		CadastroController cadastroController = new CadastroController(telaCadastro, clienteDAO, navegador);
//		// <- NOVO CONTROLLER ABAIXO
//		ProdutoController produtoController = new ProdutoController(telaCadastroProdutos, produtoDao, navegador); 
//
//		// ... restante da Main que adiciona os painéis no Navegador ...
	}
		
	}

