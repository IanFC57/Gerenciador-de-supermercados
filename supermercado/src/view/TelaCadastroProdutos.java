package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaCadastroProdutos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFProdutos;
	private JTextField TFQtd;
	private JTextField TFPreco;
	private JButton BTCadastrar, btnExcluir, btnSair;
	private JTable table;
	private DefaultTableModel modeloTabela; 

	public TelaCadastroProdutos() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(new MigLayout("", "[grow 10][][][grow 10]", "[][][][][][grow][]"));
				
		JLabel lblNewLabel_1 = new JLabel("Cadastrar Produtos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblNewLabel_1, "cell 2 0,alignx center");
		
		JLabel lblNome = new JLabel("Nome do Produto:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "cell 1 1,alignx left");
		
		TFProdutos = new JTextField();
		add(TFProdutos, "cell 2 1,growx");
		TFProdutos.setColumns(10);
		
		JLabel Qtd = new JLabel("Qtd:");
		Qtd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(Qtd, "cell 1 3,alignx left");
		
		TFQtd = new JTextField();
		add(TFQtd, "cell 2 3,growx");
		TFQtd.setColumns(10);
		
		JLabel lblPreco = new JLabel("Preço");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblPreco, "cell 1 4,alignx left");
		
		TFPreco = new JTextField();
		TFPreco.setColumns(10);
		add(TFPreco, "cell 2 4,growx");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 2 5,grow");
		
		
		modeloTabela = new DefaultTableModel(
			new Object[][] {},
			new String[] { "ID", "Produto", "Preço", "Qtd" }
		) {
			
			boolean[] columnEditables = new boolean[] { false, false, false, false };
			public boolean isCellEditable(int row, int column) { return columnEditables[column]; }
		};
		
		table = new JTable(modeloTabela);
		scrollPane.setViewportView(table);
		
		BTCadastrar = new JButton("Cadastrar");
		add(BTCadastrar, "flowx,cell 2 6,alignx center");
		
		btnExcluir = new JButton("Excluir");
		add(btnExcluir, "cell 2 6");
		
		btnSair = new JButton("Sair");
		add(btnSair, "cell 2 6");
	}

	
	
	public void cadastroproduto(ActionListener actionListener) { this.BTCadastrar.addActionListener(actionListener); }
	public void acaoExcluir(ActionListener actionListener) { this.btnExcluir.addActionListener(actionListener); }
	public void acaoSair(ActionListener actionListener) { this.btnSair.addActionListener(actionListener); }
	public void adicionarOuvinte(ComponentListener listener) { this.addComponentListener(listener); }

	public String getNome() { return this.TFProdutos.getText(); }
	public String getQtd() { return this.TFQtd.getText(); }
	public String getPreco() { return this.TFPreco.getText(); }
	
	public JTable getTabelaProdutos() { return this.table; }
	public DefaultTableModel getModeloTabela() { return this.modeloTabela; }

	public void limparCampos() {
		this.TFProdutos.setText("");
		this.TFQtd.setText("");
		this.TFPreco.setText("");
	}
	
	public void limparTabela() {
		this.modeloTabela.setRowCount(0);
	}

	public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
		javax.swing.JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
	}
}