package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Produto;
import model.produtoDAO;
import view.TelaProdutos;

public class CompraController extends ComponentAdapter {
	private final TelaProdutos view;
	private final produtoDAO model;
	private final Navegador navegador;
	private Cliente clienteLogado;
	private double valorTotal = 0.0;

	public CompraController(TelaProdutos view, produtoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;

		this.view.acaoAdicionar(e -> adicionarProdutoAoCarrinho());
		this.view.acaoRemover(e -> removerProdutoDoCarrinho());
		this.view.acaoEmitirNota(e -> emitirNotaFiscal());
		this.view.acaoDeslogar(e -> {
			limparTudo();
			this.navegador.navegarPara("LOGIN");
		});
	}

	@Override
	public void componentShown(ComponentEvent e) {
		carregarProdutosDaBase();
	}

	public void setClienteLogado(Cliente cliente) {
		this.clienteLogado = cliente;
	}

	public void carregarProdutosDaBase() {
		DefaultTableModel modelo = view.getModeloProdutos();
		view.limparTabela(modelo);
		List<Produto> produtos = model.listarTodos();

		for (Produto p : produtos) {
			modelo.addRow(new Object[] { p.getId(), p.getNomeProduto(), p.getPrecoUnitario(), p.getQtd() });
		}
	}

	private void adicionarProdutoAoCarrinho() {
		int linha = view.getTabelaProdutos().getSelectedRow();
		if (linha == -1) {
			view.exibirMensagem("Erro", "Selecione um produto da lista esquerda.", 0);
			return;
		}

		int estoque = (int) view.getModeloProdutos().getValueAt(linha, 3);
		int qtdDesejada = view.getQuantidadeSelecionada();

		if (qtdDesejada > estoque) {
			view.exibirMensagem("Aviso", "Estoque insuficiente! Temos apenas " + estoque + " unidades.", 2);
			return;
		}

		int id = (int) view.getModeloProdutos().getValueAt(linha, 0);
		String nome = (String) view.getModeloProdutos().getValueAt(linha, 1);
		double preco = (double) view.getModeloProdutos().getValueAt(linha, 2);

		double subtotal = preco * qtdDesejada;

		view.getModeloCarrinho().addRow(new Object[] { id, nome, qtdDesejada, subtotal });

		view.getModeloProdutos().setValueAt(estoque - qtdDesejada, linha, 3);

		valorTotal += subtotal;
		view.setTotal(valorTotal);
	}

	private void removerProdutoDoCarrinho() {
		int linha = view.getTabelaCarrinho().getSelectedRow();
		if (linha == -1) {
			view.exibirMensagem("Erro", "Selecione um produto no carrinho para remover.", 0);
			return;
		}

		int id = (int) view.getModeloCarrinho().getValueAt(linha, 0);
		int qtdRemovida = (int) view.getModeloCarrinho().getValueAt(linha, 2);
		double subtotal = (double) view.getModeloCarrinho().getValueAt(linha, 3);

		view.getModeloCarrinho().removeRow(linha);

		for (int i = 0; i < view.getModeloProdutos().getRowCount(); i++) {
			if ((int) view.getModeloProdutos().getValueAt(i, 0) == id) {
				int estoqueAtual = (int) view.getModeloProdutos().getValueAt(i, 3);
				view.getModeloProdutos().setValueAt(estoqueAtual + qtdRemovida, i, 3);
				break;
			}
		}

		valorTotal -= subtotal;
		view.setTotal(valorTotal);
	}

	private void emitirNotaFiscal() {
		DefaultTableModel carrinho = view.getModeloCarrinho();
		if (carrinho.getRowCount() == 0) {
			view.exibirMensagem("Aviso", "O carrinho está vazio!", 2);
			return;
		}

		StringBuilder nota = new StringBuilder();
		nota.append("NOTA FISCAL\n");
		nota.append("Cliente: ").append(clienteLogado.getNome()).append("\n");
		nota.append("CPF: ").append(clienteLogado.getCPF()).append("\n\n");
		nota.append("Itens Comprados:\n");

		for (int i = 0; i < carrinho.getRowCount(); i++) {
			int idProduto = (int) carrinho.getValueAt(i, 0);
			String nome = (String) carrinho.getValueAt(i, 1);
			int qtdComprada = (int) carrinho.getValueAt(i, 2);
			double valor = (double) carrinho.getValueAt(i, 3);

			model.baixarEstoque(idProduto, qtdComprada);

			nota.append("- ").append(qtdComprada).append("x ").append(nome).append(" | R$ ")
					.append(String.format("%.2f", valor)).append("\n");
		}

		nota.append("\n=============================================\n");
		nota.append("Total Pago: R$ ").append(String.format("%.2f", valorTotal));

		view.exibirMensagem("Compra Finalizada!", nota.toString(), 1);

		limparTudo();
		carregarProdutosDaBase();
	}

	private void limparTudo() {
		view.limparTabela(view.getModeloCarrinho());
		valorTotal = 0.0;
		view.setTotal(valorTotal);
	}
}