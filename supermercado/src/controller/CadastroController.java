package controller;

import model.Cliente;
import model.ClienteDAO;
import view.TelaCadastro;

/**
 * Classe responsável pela comunicação entre a view (TelaCadastro) e o model (candidatoDAO).
 */
public class CadastroController {
	private final TelaCadastro view;
	private final ClienteDAO model;
	private final Navegador navegador;
	
	public CadastroController(TelaCadastro view, ClienteDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.cadastrar(e -> {
			String nome = view.getNome();
			String CPF = view.getCPF();

			if((!nome.equals("") &&
					!CPF.equals(""))) {

//				CLiente c = new Cliente(nome, CPF);
//				this.model.adicionar(c);

				this.view.limparCampos();
				this.view.exibirMensagem("Cadastro", "Cadastro Feito com sucesso!", 0);
			}
			else {
				this.view.exibirMensagem("Erro", "Erro no Cadastro", 0);
			}
		});
		
		
	}
	
}
	 

