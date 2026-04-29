package main;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import controller.CadastroController;
import controller.CompraController;
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

		// DAOs
		ClienteDAO clienteDAO = new ClienteDAO();
		produtoDAO produtoDao = new produtoDAO();

		// Views
		TelaLogin telaLogin = new TelaLogin();
		TelaCadastro telaCadastro = new TelaCadastro();
		TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();
		TelaProdutos telaCompra = new TelaProdutos();

		// Controllers
		CompraController compraController = new CompraController(telaCompra, produtoDao, navegador);
		telaCompra.adicionarOuvinte(compraController);
		ProdutoController produtoController = new ProdutoController(telaCadastroProdutos, produtoDao, navegador);
		telaCadastroProdutos.adicionarOuvinte(produtoController);
		CadastroController cadastroController = new CadastroController(telaCadastro, clienteDAO, navegador);
		LoginController loginController = new LoginController(telaLogin, clienteDAO, navegador, compraController);

		navegador.adicionarPainel("LOGIN", telaLogin);
		navegador.adicionarPainel("CADASTRO", telaCadastro);
		navegador.adicionarPainel("CADASTRO_PRODUTOS", telaCadastroProdutos);
		navegador.adicionarPainel("COMPRA", telaCompra);

		principal.setLocationRelativeTo(null);
		principal.setVisible(true);

		navegador.navegarPara("LOGIN");
	}
}