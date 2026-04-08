package model;

public class Produto {
	private String nomeProduto;
	private String PrecoUnitario;
	private String qtd;
	public Produto(String nomeProduto, String precoUnitario, String qtd) {
		super();
		this.nomeProduto = nomeProduto;
		PrecoUnitario = precoUnitario;
		this.qtd = qtd;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public String getPrecoUnitario() {
		return PrecoUnitario;
	}
	public String getQtd() {
		return qtd;
	}
	
	

}
