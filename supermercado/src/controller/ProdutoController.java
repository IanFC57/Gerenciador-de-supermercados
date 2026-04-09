package controller;

import java.awt.event.ComponentAdapter;
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
					// Converte as Strings da tela para números
					int qtd = Integer.parseInt(qtdStr);
					// Troca vírgula por ponto para evitar erro se o admin digitar "5,99"
					double preco = Double.parseDouble(precoStr.replace(",", ".")); 

					Produto p = new Produto(0, nome, preco, qtd);
					this.model.adicionarProduto(p);

					this.view.limparCampos();
					this.view.exibirMensagem("Sucesso", "Produto cadastrado com sucesso!", 1);
					
				} catch (NumberFormatException ex) {
					this.view.exibirMensagem("Erro", "Quantidade ou preço inválido. Use apenas números.", 0);
				}
			} else {
				this.view.exibirMensagem("Aviso", "Preencha todos os campos!", 2);
			}
		});
	}
}