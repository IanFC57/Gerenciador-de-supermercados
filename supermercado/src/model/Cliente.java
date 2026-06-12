package model;

public class Cliente {
	private int id;
	private String nome;
	private String cpf;
	private boolean isAdmin;
	private String senhaHash;

	public Cliente(int id, String nome, String cpf, boolean isAdmin, String senhaHash) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.isAdmin = isAdmin;
		this.senhaHash = senhaHash;
	}

	public Cliente(String nome, String cpf, boolean isAdmin) {
		this(0, nome, cpf, isAdmin, null);
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCPF() {
		return cpf;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public String getSenhaHash() {
		return senhaHash;
	}
}
