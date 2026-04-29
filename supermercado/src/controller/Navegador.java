package controller;

import java.security.Principal;

import javax.swing.JPanel;

/**
 * Classe responsável por gerenciar a troca de telas.
 */
public class Navegador {

	private view.Principal principal;

	/**
	 * Construtor da classe.
	 * 
	 * @param janela Referência ao JFrame.
	 */
	public Navegador(view.Principal principal) {
		this.principal = principal;
	}

	/**
	 * Método responsável por chamar o método da view que adiciona as telas ao
	 * painel principal.
	 * 
	 * @param nome Nome do painel.
	 * @param tela Painel que será adicionado.
	 */
	public void adicionarPainel(String nome, JPanel tela) {
		this.principal.adicionarTela(nome, tela);
	}

	/**
	 * Método responsável por chamar o método da view que navega para outra tela.
	 * 
	 * @param nome Nome da tela.
	 */
	public void navegarPara(String nome) {
		this.principal.mostrarTela(nome);
	}

	/**
	 * Método responsável por chamar o método da view que fecha a aplicação.
	 */
	public void sair() {
		this.principal.dispose();
	}
}
