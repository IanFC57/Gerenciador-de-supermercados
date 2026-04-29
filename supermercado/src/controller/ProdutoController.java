package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import model.produtoDAO;
import view.TelaCadastroProdutos;

public class ProdutoController extends ComponentAdapter {
	private final TelaCadastroProdutos view;
	private final produtoDAO model;
	private final Navegador navegador;

	public ProdutoController(TelaCadastroProdutos view, produtoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;

		this.view.cadastroproduto(e -> {
			String nome = view.getNome();
			String qtdStr = view.getQtd();
			String precoStr = view.getPreco();

			if (!nome.isBlank() && !qtdStr.isBlank() && !precoStr.isBlank()) {
				try {
					int qtd = Integer.parseInt(qtdStr);
					double preco = Double.parseDouble(precoStr.replace(",", "."));

					Produto p = new Produto(0, nome, preco, qtd);
					this.model.adicionarProduto(p);

					this.view.limparCampos();
					this.view.exibirMensagem("Sucesso", "Produto cadastrado com sucesso!", 1);

					carregarTabela();

				} catch (NumberFormatException ex) {
					this.view.exibirMensagem("Erro", "Quantidade ou preço inválido. Use apenas números.", 0);
				}
			} else {
				this.view.exibirMensagem("Aviso", "Preencha todos os campos!", 2);
			}
		});

		this.view.acaoExcluir(e -> {
			int linhaSelecionada = this.view.getTabelaProdutos().getSelectedRow();

			if (linhaSelecionada != -1) {

				int idProduto = (int) this.view.getModeloTabela().getValueAt(linhaSelecionada, 0);

				this.model.excluirProduto(idProduto);

				this.view.exibirMensagem("Excluído", "Produto excluído com sucesso!", 1);

				carregarTabela();
			} else {
				this.view.exibirMensagem("Erro", "Selecione um produto na tabela para excluir.", 0);
			}
		});

		this.view.acaoSair(e -> {
			this.view.limparCampos();
			this.view.limparTabela();
			this.navegador.navegarPara("LOGIN");
		});
	}

	@Override
	public void componentShown(ComponentEvent e) {
		carregarTabela();
	}

	private void carregarTabela() {
		DefaultTableModel modelo = this.view.getModeloTabela();
		this.view.limparTabela();

		List<Produto> produtos = this.model.listarTodos();

		for (Produto p : produtos) {
			modelo.addRow(new Object[] { p.getId(), p.getNomeProduto(), p.getPrecoUnitario(), p.getQtd() });
		}
	}
}