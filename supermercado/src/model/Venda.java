package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venda {
	private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private int id;
	private int clienteId;
	private String clienteNome;
	private LocalDateTime dataVenda;
	private double valorTotal;
	private List<ItemVenda> itens;

	public Venda(int id, int clienteId, String clienteNome, LocalDateTime dataVenda, double valorTotal) {
		this.id = id;
		this.clienteId = clienteId;
		this.clienteNome = clienteNome;
		this.dataVenda = dataVenda;
		this.valorTotal = valorTotal;
	}

	public int getId() {
		return id;
	}

	public int getClienteId() {
		return clienteId;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public String getDataFormatada() {
		return dataVenda.format(FMT);
	}
}
