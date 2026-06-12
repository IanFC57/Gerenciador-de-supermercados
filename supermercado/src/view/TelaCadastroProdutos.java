package view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import model.Fornecedor;

public class TelaCadastroProdutos extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextField tfNome, tfQtd, tfPreco, tfPesquisa;
	private JComboBox<Object> cbFornecedor;
	private JButton btnSalvar, btnLimpar, btnExcluir, btnSair;
	private JButton btnFornecedores, btnMovimentacao, btnHistorico;
	private JTable table;
	private DefaultTableModel modeloTabela;

	
	private boolean modoEdicao = false;
	private int idEdicao = -1;

	public TelaCadastroProdutos() {
		setPreferredSize(new Dimension(750, 800));
		setBackground(new Color(245, 246, 250));
		setLayout(new MigLayout("fill, insets 10", "[grow]", "[][][][grow][]"));
		JPanel header = new JPanel(new MigLayout("insets 0", "[grow][]"));
		header.setOpaque(false);
		JLabel lblTitulo = new JLabel("Gestão de Produtos");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTitulo.setForeground(new Color(33, 90, 170));
		header.add(lblTitulo, "");

		
		btnFornecedores = navBtn("Fornecedores");
		btnMovimentacao = navBtn("Estoque");
		btnHistorico = navBtn("Histórico");
		header.add(btnFornecedores, "");
		header.add(btnMovimentacao, "");
		header.add(btnHistorico, "");
		add(header, "cell 0 0, growx");

		
		JPanel form = new JPanel(new MigLayout("insets 10 0 10 0, wrap 4", "[100][grow][80][grow]", "[][]"));
		form.setOpaque(false);

		form.add(label("Nome do Produto:"));
		tfNome = campo();
		form.add(tfNome, "growx");

		form.add(label("Fornecedor:"));
		cbFornecedor = new JComboBox<>();
		cbFornecedor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbFornecedor.setPreferredSize(new Dimension(150, 30));
		form.add(cbFornecedor, "growx");

		form.add(label("Quantidade:"));
		tfQtd = campo();
		form.add(tfQtd, "growx");

		form.add(label("Preço (R$):"));
		tfPreco = campo();
		form.add(tfPreco, "growx");

		add(form, "cell 0 1, growx");

		
		JPanel pesqPanel = new JPanel(new MigLayout("insets 0", "[80][grow]", "[]"));
		pesqPanel.setOpaque(false);
		pesqPanel.add(label("Pesquisar:"));
		tfPesquisa = new JTextField();
		tfPesquisa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tfPesquisa.setToolTipText("Filtre por nome ou ID em tempo real");
		pesqPanel.add(tfPesquisa, "growx");
		add(pesqPanel, "cell 0 2, growx");

		
		modeloTabela = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Produto", "Preço", "Qtd", "Fornecedor" }) {
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		table = new JTable(modeloTabela);
		table.setRowHeight(24);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setGridColor(new Color(230, 230, 230));

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		add(scroll, "cell 0 3, grow");

		
		JPanel barBtn = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
		barBtn.setOpaque(false);

		btnSalvar = acaoBtn("Cadastrar", new Color(33, 90, 170), Color.WHITE);
		btnLimpar = acaoBtn("Limpar", new Color(100, 100, 100), Color.WHITE);
		btnExcluir = acaoBtn("Excluir", new Color(200, 50, 50), Color.WHITE);
		btnSair = acaoBtn("Sair", new Color(80, 80, 80), Color.WHITE);

		barBtn.add(btnSalvar);
		barBtn.add(btnLimpar);
		barBtn.add(btnExcluir);
		barBtn.add(Box.createHorizontalStrut(20));
		barBtn.add(btnSair);
		add(barBtn, "cell 0 4, growx");
	}

	
	private JLabel label(String t) {
		JLabel l = new JLabel(t);
		l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		return l;
	}

	private JTextField campo() {
		JTextField tf = new JTextField();
		tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tf.setPreferredSize(new Dimension(120, 30));
		return tf;
	}

	private JButton acaoBtn(String txt, Color bg, Color fg) {
		JButton b = new JButton(txt);
		b.setBackground(bg);
		b.setForeground(fg);
		b.setFocusPainted(false);
		b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		b.setPreferredSize(new Dimension(100, 32));
		return b;
	}

	private JButton navBtn(String txt) {
		JButton b = new JButton(txt);
		b.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		b.setBackground(new Color(70, 130, 180));
		b.setForeground(Color.WHITE);
		b.setFocusPainted(false);
		b.setPreferredSize(new Dimension(110, 28));
		return b;
	}

	
	
	public void ativarModoEdicao(int id, String nome, double preco, int qtd, int fornecedorId) {
		this.modoEdicao = true;
		this.idEdicao = id;
		tfNome.setText(nome);
		tfPreco.setText(String.valueOf(preco).replace(',', '.'));
		tfQtd.setText(String.valueOf(qtd));
		selecionarFornecedor(fornecedorId);
		btnSalvar.setText("Atualizar");
		btnSalvar.setBackground(new Color(0, 140, 90));
	}

	public void desativarModoEdicao() {
		this.modoEdicao = false;
		this.idEdicao = -1;
		btnSalvar.setText("Cadastrar");
		btnSalvar.setBackground(new Color(33, 90, 170));
	}

	private void selecionarFornecedor(int id) {
		for (int i = 0; i < cbFornecedor.getItemCount(); i++) {
			Object item = cbFornecedor.getItemAt(i);
			if ((item instanceof Fornecedor) && ((Fornecedor) item).getId() == id) {
				cbFornecedor.setSelectedIndex(i);
				return;
			}
		}
		cbFornecedor.setSelectedIndex(0);
	}

	public boolean isModoEdicao() {
		return modoEdicao;
	}

	public int getIdEdicao() {
		return idEdicao;
	}

	
	public void carregarFornecedores(List<Fornecedor> lista) {
		cbFornecedor.removeAllItems();
		cbFornecedor.addItem("(Sem fornecedor)");
		for (Fornecedor f : lista)
			cbFornecedor.addItem(f);
	}

	
	public int getFornecedorIdSelecionado() {
		Object sel = cbFornecedor.getSelectedItem();
		return (sel instanceof Fornecedor) ? ((Fornecedor) sel).getId() : 0;
	}

	public void adicionarOuvintePesquisa(DocumentListener dl) {
		tfPesquisa.getDocument().addDocumentListener(dl);
	}

	public String getTermoPesquisa() {
		return tfPesquisa.getText();
	}

	public String getNome() {
		return tfNome.getText();
	}

	public String getQtd() {
		return tfQtd.getText();
	}

	public String getPreco() {
		return tfPreco.getText();
	}

	public JTable getTabelaProdutos() {
		return table;
	}

	public DefaultTableModel getModeloTabela() {
		return modeloTabela;
	}

	public void limparCampos() {
		tfNome.setText("");
		tfQtd.setText("");
		tfPreco.setText("");
		cbFornecedor.setSelectedIndex(0);
		desativarModoEdicao();
	}

	public void limparTabela() {
		modeloTabela.setRowCount(0);
	}


	public void acaoSalvar(ActionListener al) {
		btnSalvar.addActionListener(al);
	}

	public void acaoLimpar(ActionListener al) {
		btnLimpar.addActionListener(al);
	}

	public void acaoExcluir(ActionListener al) {
		btnExcluir.addActionListener(al);
	}

	public void acaoSair(ActionListener al) {
		btnSair.addActionListener(al);
	}

	public void acaoFornecedores(ActionListener al) {
		btnFornecedores.addActionListener(al);
	}

	public void acaoMovimentacao(ActionListener al) {
		btnMovimentacao.addActionListener(al);
	}

	public void acaoHistorico(ActionListener al) {
		btnHistorico.addActionListener(al);
	}

	public void cadastroproduto(ActionListener al) {
		acaoSalvar(al);
	}

	public void adicionarOuvinte(java.awt.event.ComponentListener l) {
		addComponentListener(l);
	}

	public void exibirMensagem(String titulo, String mensagem, int tipo) {
		JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
	}
}
