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
	        boolean isAdmin = view.getAdmin(); 
	        
	        if (!nome.isBlank() && !CPF.isBlank()) {
	            Cliente c = new Cliente(nome, CPF, isAdmin);
	            this.model.adicionarCliente(c);
	            
	            this.view.limparCampos();
	            this.view.exibirMensagem("Cadastro", "Cadastro feito com sucesso!", 1);
	            this.navegador.navegarPara("LOGIN"); 
	        } else {
	            this.view.exibirMensagem("Erro", "Preencha todos os campos!", 0);
	        }
	    });
	    
	
	 		this.view.acaoSair(e -> {
	 			this.view.limparCampos(); 
	 			this.navegador.navegarPara("LOGIN"); 
	 		});
	}
	
}
	 

