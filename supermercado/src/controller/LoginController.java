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
		
		this.view.autenticar(e ->{
			String CPF = view.getCpf();
            if (!CPF.equals("")) {
                // Pergunta ao model se o cliente existe
                Cliente cliente = this.model.buscarPorCPF(CPF);

                if (cliente != null) {
                    this.view.exibirMensagem("Login", "Bem-vindo, " + cliente.getNome() + "!", 1);
                    
                    // Aqui você usa o navegador para ir para a tela principal
                    // this.navegador.irParaHome(); 
                } else {
                    this.view.exibirMensagem("Erro", "Usuário não encontrado ou CPF inválido.", 0);
                }
            } else {
                this.view.exibirMensagem("Aviso", "Por favor, preencha o campo CPF.", 0);
            }
		});
		
	}
	
	
	
	

}
