package model;

public class Produto {
	private int id;
	private String nomeProduto;
	private double precoUnitario;
	private int qtd;
	private int fornecedorId;
	private String nomeFornecedor;

	public Produto(int id, String nomeProduto, double precoUnitario, int qtd, int fornecedorId, String nomeFornecedor) {
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.precoUnitario = precoUnitario;
		this.qtd = qtd;
		this.fornecedorId = fornecedorId;
		this.nomeFornecedor = nomeFornecedor;
	}

	public Produto(int id, String nomeProduto, double precoUnitario, int qtd) {
		this(id, nomeProduto, precoUnitario, qtd, 0, "—");
	}

	public int getId() {
		return id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public int getQtd() {
		return qtd;
	}

	public int getFornecedorId() {
		return fornecedorId;
	}

	@Override
	public String toString() {
		return nomeProduto;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor != null ? nomeFornecedor : "—";
	}
}
