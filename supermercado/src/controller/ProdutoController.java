package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import exception.PersistenciaException;
import exception.ValidacaoException;
import model.Produto;
import model.ProdutoDAO;
import view.TelaCadastroProdutos;

public class ProdutoController extends ComponentAdapter {
	private final TelaCadastroProdutos view;
	private final ProdutoDAO model;
	private final Navegador navegador;

	public ProdutoController(TelaCadastroProdutos view, ProdutoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;

		this.view.cadastroproduto(e -> {
			try {
				String nome = view.getNome();
				String qtdStr = view.getQtd();
				String precoStr = view.getPreco();

				if (nome == null || nome.trim().isEmpty() || qtdStr == null || qtdStr.trim().isEmpty()
						|| precoStr == null || precoStr.trim().isEmpty()) {
					throw new ValidacaoException("Todos os campos de produto são de preenchimento obrigatório.");
				}

				if (nome.matches(".*\\d.*")) {
					throw new ValidacaoException("O nome do produto não pode conter números.");
				}

				int qtd = Integer.parseInt(qtdStr.trim());
				double preco = Double.parseDouble(precoStr.replace(",", ".").trim());

				if (qtd < 0 || preco < 0) {
					throw new ValidacaoException("Não pode inserir valores negativos de quantidade ou preço.");
				}

				Produto p = new Produto(0, nome.trim(), preco, qtd);
				this.model.adicionarProduto(p);

				this.view.limparCampos();
				this.view.exibirMensagem("Sucesso", "Produto adicionado com sucesso!", 1);
				carregarTabela();

			} catch (NumberFormatException ex) {
				this.view.exibirMensagem("Erro de Formato", "Quantidade ou preço inválido. Use apenas números.", 0);
			} catch (ValidacaoException ex) {
				this.view.exibirMensagem("Aviso", ex.getMessage(), 2);
			} catch (PersistenciaException ex) {
				this.view.exibirMensagem("Erro de Sistema", "Falha ao gravar o produto: " + ex.getMessage(), 0);
			}
		});

		this.view.acaoExcluir(e -> {
			try {
				int linhaSelecionada = this.view.getTabelaProdutos().getSelectedRow();

				if (linhaSelecionada == -1) {
					throw new ValidacaoException("Selecione um produto na tabela para poder excluí-lo.");
				}

				int idProduto = (int) this.view.getModeloTabela().getValueAt(linhaSelecionada, 0);
				this.model.excluirProduto(idProduto);

				this.view.exibirMensagem("Excluído", "Produto excluído com sucesso!", 1);
				carregarTabela();

			} catch (ValidacaoException ex) {
				this.view.exibirMensagem("Aviso", ex.getMessage(), 2);
			} catch (PersistenciaException ex) {
				this.view.exibirMensagem("Erro Crítico", "Erro ao excluir o produto: " + ex.getMessage(), 0);
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
		try {
			DefaultTableModel modelo = this.view.getModeloTabela();
			this.view.limparTabela();

			List<Produto> produtos = this.model.listarTodos();

			for (Produto p : produtos) {
				modelo.addRow(new Object[] { p.getId(), p.getNomeProduto(), p.getPrecoUnitario(), p.getQtd() });
			}
		} catch (PersistenciaException ex) {
			this.view.exibirMensagem("Falha",
					"Não foi possível conectar ao banco de dados para listar produtos: " + ex.getMessage(), 0);
		}
	}
}