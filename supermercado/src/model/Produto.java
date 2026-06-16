package model;

public class Produto {
	private int id;
	private String nomeProduto;
	private double precoUnitario;
	private int qtd;

	public Produto(int id, String nomeProduto, double precoUnitario, int qtd) {
		super();
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.precoUnitario = precoUnitario;
		this.qtd = qtd;
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

}