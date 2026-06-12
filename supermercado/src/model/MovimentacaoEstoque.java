package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovimentacaoEstoque {
	private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private int id;
	private int produtoId;
	private String nomeProduto;
	private String tipo;
	private int quantidade;
	private LocalDateTime dataMovimentacao;

	public MovimentacaoEstoque(int id, int produtoId, String nomeProduto, String tipo, int quantidade,
			LocalDateTime data) {
		this.id = id;
		this.produtoId = produtoId;
		this.nomeProduto = nomeProduto;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.dataMovimentacao = data;
	}

	public int getId() {
		return id;
	}

	public int getProdutoId() {
		return produtoId;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public String getTipo() {
		return tipo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public String getDataFormatada() {
		return dataMovimentacao.format(FMT);
	}
}
