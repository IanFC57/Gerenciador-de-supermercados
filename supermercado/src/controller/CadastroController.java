package controller;

import java.awt.event.ComponentAdapter;
import exception.PersistenciaException;
import exception.ValidacaoException;
import model.Cliente;
import model.ClienteDAO;
import view.TelaCadastro;

public class CadastroController extends ComponentAdapter {
	private final TelaCadastro view;
	private final ClienteDAO model;
	private final Navegador navegador;

	public CadastroController(TelaCadastro view, ClienteDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;

		this.view.cadastrar(e -> {
			try {
				String nome = view.getNome();
				String cpfFormatado = view.getCPF();
				boolean isAdmin = view.getAdmin();

				String cpfLimpo = cpfFormatado.replaceAll("[^0-9]", "");

				if (nome == null || nome.trim().isEmpty()) {
					throw new ValidacaoException("O campo Nome deve ser preenchido.");
				}
				if (cpfLimpo.length() != 11) {
					throw new ValidacaoException("Por favor, preencha o CPF completamente.");
				}

				Cliente c = new Cliente(nome.trim(), cpfLimpo, isAdmin);
				this.model.adicionarCliente(c);

				this.view.limparCampos();
				this.view.exibirMensagem("Cadastro", "Cadastro efetuado com sucesso!", 1);
				this.navegador.navegarPara("LOGIN");

			} catch (ValidacaoException ex) {
				this.view.exibirMensagem("Aviso", ex.getMessage(), 2);
			} catch (PersistenciaException ex) {
				this.view.exibirMensagem("Erro", "Erro ao cadastrar: " + ex.getMessage(), 0);
			}
		});

		this.view.acaoSair(e -> {
			this.view.limparCampos();
			this.navegador.navegarPara("LOGIN");
		});
	}
}