package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Cliente;
import model.ClienteDAO;
import view.TelaLogin;

public class LoginController extends ComponentAdapter {
	private final TelaLogin view;
	private final ClienteDAO model;
	private final Navegador navegador;
	private final CompraController compraController; 

	
	public LoginController(TelaLogin view, ClienteDAO model, Navegador navegador, CompraController compraController) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		this.compraController = compraController;

		
		this.view.Cadastro(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				navegador.navegarPara("CADASTRO");
			}
		});

		
		this.view.autenticar(e -> {
			String CPF = view.getCpf();
			if (!CPF.isBlank()) {
				
				Cliente cliente = this.model.buscarPorCPF(CPF);

				if (cliente != null) {
					this.view.exibirMensagem("Login", "Bem-vindo, " + cliente.getNome() + "!", 1);

					
					if (cliente.isAdmin()) {
						this.navegador.navegarPara("CADASTRO_PRODUTOS");
					} else {
						
						this.compraController.setClienteLogado(cliente);
						
						
						this.compraController.carregarProdutosDaBase();
						
						
						this.navegador.navegarPara("COMPRA");
					}
				} else {
					this.view.exibirMensagem("Erro", "Usuário não encontrado ou CPF inválido.", 0);
				}
			} else {
				this.view.exibirMensagem("Aviso", "Por favor, preencha o campo CPF.", 0);
			}
		});
	}
}