package model;

public class Fornecedor {
	private int id;
	private String nome;
	private String cnpj;
	private String telefone;
	private String email;
	private String endereco;

	public Fornecedor(int id, String nome, String cnpj, String telefone, String email, String endereco) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public Fornecedor(String nome, String cnpj, String telefone, String email, String endereco) {
		this(0, nome, cnpj, telefone, email, endereco);
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	@Override
	public String toString() {
		return nome;
	}
}
