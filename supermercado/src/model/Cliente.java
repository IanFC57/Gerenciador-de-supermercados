package model;

public class Cliente {
	private String nome;
	private String CPF;
	private boolean isAdmin;
	public Cliente(String nome, String cPF, boolean isAdmin) {
		super();
		this.nome = nome;
		CPF = cPF;
		this.isAdmin = isAdmin;
	}
	public String getNome() {
		return nome;
	}
	public String getCPF() {
		return CPF;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	
	
}
