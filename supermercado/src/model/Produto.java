package model;

public class Produto {
	private String nomeProduto;
	private String PrecoUnitario;
	public Produto(String nomeProduto, String precoUnitario) {
		super();
		this.nomeProduto = nomeProduto;
		PrecoUnitario = precoUnitario;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public String getPrecoUnitario() {
		return PrecoUnitario;
	}
	
	

}
