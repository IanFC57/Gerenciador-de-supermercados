package model;

public class ItemVenda {
	private int id;
	private int vendaId;
	private int produtoId;
	private String nomeProduto;
	private int quantidade;
	private double precoUnitario;

	public ItemVenda(int id, int vendaId, int produtoId, String nomeProduto, int quantidade, double precoUnitario) {
		this.id = id;
		this.vendaId = vendaId;
		this.produtoId = produtoId;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}

	public int getId() {
		return id;
	}

	public int getVendaId() {
		return vendaId;
	}

	public int getProdutoId() {
		return produtoId;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public double getSubtotal() {
		return quantidade * precoUnitario;
	}
}
