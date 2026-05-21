package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import exception.PersistenciaException;
import exception.ValidacaoException;
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
			try {
				String cpfFormatado = view.getCpf();
				String cpfLimpo = cpfFormatado.replaceAll("[^0-9]", "");

				if (cpfLimpo.length() != 11) {
					throw new ValidacaoException("Por favor, introduza um CPF válido para aceder.");
				}

				Cliente cliente = this.model.buscarPorCPF(cpfLimpo);

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
					this.view.exibirMensagem("Acesso Negado", "Utilizador não encontrado ou CPF inválido.", 0);
				}
			} catch (ValidacaoException ex) {
				this.view.exibirMensagem("Aviso", ex.getMessage(), 2);
			} catch (PersistenciaException ex) {
				this.view.exibirMensagem("Erro Crítico", "Erro de sistema: " + ex.getMessage(), 0);
			}
		});
	}
}