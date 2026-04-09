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
	private Cliente clienteLogado; // Guarda quem logou para imprimir na nota
	private double valorTotal = 0.0;

	public CompraController(TelaProdutos view, produtoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;

		// Ação: Adicionar ao Carrinho
		this.view.acaoAdicionar(e -> adicionarProdutoAoCarrinho());

		// Ação: Remover do Carrinho
		this.view.acaoRemover(e -> removerProdutoDoCarrinho());

		// Ação: Emitir Nota Fiscal
		this.view.acaoEmitirNota(e -> emitirNotaFiscal());

		// Ação: Deslogar
		this.view.acaoDeslogar(e -> {
			limparTudo();
			this.navegador.navegarPara("LOGIN");
		});
	}

	// Carrega os produtos sempre que a tela é aberta (pelo CardLayout)
	@Override
	public void componentShown(ComponentEvent e) {
		carregarProdutosDaBase();
	}

	// Define quem é o cliente atual para sair o nome na nota fiscal
	public void setClienteLogado(Cliente cliente) {
		this.clienteLogado = cliente;
	}

	// Método público para ser chamado pelo LoginController
	public void carregarProdutosDaBase() {
		DefaultTableModel modelo = view.getModeloProdutos();
		view.limparTabela(modelo);
		List<Produto> produtos = model.listarTodos();

		for (Produto p : produtos) {
			modelo.addRow(new Object[]{p.getId(), p.getNomeProduto(), p.getPrecoUnitario(), p.getQtd()});
		}
	}

	private void adicionarProdutoAoCarrinho() {
		int linha = view.getTabelaProdutos().getSelectedRow();
		if (linha == -1) {
			view.exibirMensagem("Erro", "Selecione um produto da lista esquerda.", 0);
			return;
		}

		int estoque = (int) view.getModeloProdutos().getValueAt(linha, 3);
		if (estoque <= 0) {
			view.exibirMensagem("Aviso", "Produto sem estoque!", 2);
			return;
		}

		// Pega dados da tabela da esquerda
		int id = (int) view.getModeloProdutos().getValueAt(linha, 0);
		String nome = (String) view.getModeloProdutos().getValueAt(linha, 1);
		double preco = (double) view.getModeloProdutos().getValueAt(linha, 2);

		// Adiciona no carrinho da direita (1 unidade por clique)
		view.getModeloCarrinho().addRow(new Object[]{id, nome, 1, preco});
		
		// Reduz estoque na tela (ainda não abateu no banco, só visualmente)
		view.getModeloProdutos().setValueAt(estoque - 1, linha, 3);

		// Atualiza o valor Total na tela
		valorTotal += preco;
		view.setTotal(valorTotal);
	}

	private void removerProdutoDoCarrinho() {
		int linha = view.getTabelaCarrinho().getSelectedRow();
		if (linha == -1) {
			view.exibirMensagem("Erro", "Selecione um produto no carrinho para remover.", 0);
			return;
		}

		int id = (int) view.getModeloCarrinho().getValueAt(linha, 0);
		double subtotal = (double) view.getModeloCarrinho().getValueAt(linha, 3);

		// Remove da tabela do carrinho
		view.getModeloCarrinho().removeRow(linha);

		// Devolve o estoque na tabela da esquerda
		for (int i = 0; i < view.getModeloProdutos().getRowCount(); i++) {
			if ((int) view.getModeloProdutos().getValueAt(i, 0) == id) {
				int estoqueAtual = (int) view.getModeloProdutos().getValueAt(i, 3);
				view.getModeloProdutos().setValueAt(estoqueAtual + 1, i, 3);
				break;
			}
		}

		// Atualiza o valor Total na tela
		valorTotal -= subtotal;
		view.setTotal(valorTotal);
	}

	private void emitirNotaFiscal() {
		DefaultTableModel carrinho = view.getModeloCarrinho();
		if (carrinho.getRowCount() == 0) {
			view.exibirMensagem("Aviso", "O carrinho está vazio!", 2);
			return;
		}

		// 1. Montar a string da Nota Fiscal com os dados do Cliente
		StringBuilder nota = new StringBuilder();
		nota.append("================ NOTA FISCAL ================\n");
		nota.append("Cliente: ").append(clienteLogado.getNome()).append("\n");
		nota.append("CPF: ").append(clienteLogado.getCPF()).append("\n\n");
		nota.append("Itens Comprados:\n");

		// 2. Loop para abater do banco de dados e listar na nota
		for (int i = 0; i < carrinho.getRowCount(); i++) {
			int idProduto = (int) carrinho.getValueAt(i, 0);
			String nome = (String) carrinho.getValueAt(i, 1);
			double valor = (double) carrinho.getValueAt(i, 3);

			// Baixa no banco de dados (1 unidade por linha do carrinho)
			model.baixarEstoque(idProduto, 1);

			nota.append("- ").append(nome).append(" | R$ ").append(String.format("%.2f", valor)).append("\n");
		}

		nota.append("\n=============================================\n");
		nota.append("TOTAL PAGO: R$ ").append(String.format("%.2f", valorTotal));

		// 3. Exibir a Nota na tela
		view.exibirMensagem("Compra Finalizada!", nota.toString(), 1);

		// 4. Limpar o carrinho e recarregar os produtos (com o novo estoque do banco)
		limparTudo();
		carregarProdutosDaBase(); 
	}

	// Método auxiliar para zerar a tela de compras
	private void limparTudo() {
		view.limparTabela(view.getModeloCarrinho());
		valorTotal = 0.0;
		view.setTotal(valorTotal);
	}
}