package controller;

import java.awt.event.ComponentAdapter;

import model.Cliente;
import model.ClienteDAO;
import view.TelaCadastro;

/**
 * Classe responsável pela comunicação entre a view (TelaCadastro) e o model (candidatoDAO). SUPERMERCADO
 */
public class CadastroController extends ComponentAdapter{
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
			//String Admin = view.getAdmin();
			

			if((!nome.equals("") &&
					!CPF.equals(""))) {

			 Cliente c = new Cliente(nome, CPF);
			 this.model.adicionarCliente(c);

				this.view.limparCampos();
				this.view.exibirMensagem("Cadastro", "Cadastro Feito com sucesso!", 0);
			}
			else {
				this.view.exibirMensagem("Erro", "Erro no Cadastro", 0);
			}
		});
		
		
	}
	
}
	 

