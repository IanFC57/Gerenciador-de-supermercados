package controller;

import java.awt.event.ComponentAdapter;

import model.Cliente;
import model.ClienteDAO;
import view.TelaCadastro;
import view.TelaLogin;

public class LoginController extends ComponentAdapter{
	private final TelaLogin view;
	private final ClienteDAO model;
	private final Navegador navegador;
	
	public LoginController(TelaLogin view, ClienteDAO model, Navegador navegador) {
	    this.view = view;
	    this.model = model;
	    this.navegador = navegador;
	    
	    // Ação do link "Não possui conta?"
	    this.view.Cadastro(new java.awt.event.MouseAdapter() {
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            navegador.navegarPara("CADASTRO");
	        }
	    });

	    this.view.autenticar(e -> {
	        String CPF = view.getCpf();
	        if (!CPF.isBlank()) {
	            Cliente cliente = this.model.buscarPorCPF(CPF);

	            if (cliente != null) {
	                this.view.exibirMensagem("Login", "Bem-vindo, " + cliente.getNome() + "!", 1);
	                
	                // SEPARAÇÃO DE TELAS AQUI!
	                if (cliente.isAdmin()) {
	                    this.navegador.navegarPara("CADASTRO_PRODUTOS");
	                } else {
	                    this.navegador.navegarPara("COMPRA");
	                }
	            } else {
	                this.view.exibirMensagem("Erro", "Usuário não encontrado.", 0);
	            }
	        } else {
	            this.view.exibirMensagem("Aviso", "Preencha o CPF.", 2);
	        }
	    });
	}
	
	
	
	

}
