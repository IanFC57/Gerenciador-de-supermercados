package model;

public class Cliente {
	private String nome;
	private String CPF;
	public Cliente(String nome, String CPF) {
		super();
		this.nome = nome;
		CPF = CPF;
	}
	public String getNome() {
		return nome;
	}
	public String getCPF() {
		return CPF;
	}
}
