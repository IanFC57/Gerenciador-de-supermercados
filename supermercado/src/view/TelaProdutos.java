package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TelaProdutos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabelaProdutos;
	private JTable tabelaCarrinho;
	private JTextField TFTotal;
	private JButton btnAdicionar, btnExcluir, btnEmitirNota, btnDeslogar;
	private DefaultTableModel modeloProdutos, modeloCarrinho;
	private JSpinner spinnerQtd;

	public TelaProdutos() {
		setPreferredSize(new Dimension(750, 800));
		setLayout(new MigLayout("", "[][grow 50][grow 50][][grow 10]", "[][][grow 10][][][grow][grow 10]"));

		JLabel lblNewLabel = new JLabel("Produtos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblNewLabel, "cell 0 1,alignx center");
		
				JLabel lblNewLabel_1 = new JLabel("Carrinho de compras");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
				add(lblNewLabel_1, "cell 3 1,alignx center");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 2 2 3,grow");

		modeloProdutos = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Produto", "Preço", "Estoque" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tabelaProdutos = new JTable(modeloProdutos);
		tabelaProdutos.setColumnSelectionAllowed(false);
		tabelaProdutos.setCellSelectionEnabled(false);
		tabelaProdutos.setRowSelectionAllowed(true);
		scrollPane.setViewportView(tabelaProdutos);

		modeloCarrinho = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Produto", "Qtd", "Subtotal" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
				JScrollPane scrollPane_1 = new JScrollPane();
				add(scrollPane_1, "cell 2 2 3 3,grow");
				
						tabelaCarrinho = new JTable(modeloCarrinho);
						scrollPane_1.setViewportView(tabelaCarrinho);
				
						JLabel lblQtd = new JLabel("Qtd:");
						lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 18));
						add(lblQtd, "flowx,cell 0 6");
						
								spinnerQtd = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
								spinnerQtd.setFont(new Font("Tahoma", Font.PLAIN, 18));
								add(spinnerQtd, "cell 0 6");
								
										btnAdicionar = new JButton("Adicionar");
										btnAdicionar.setFont(new Font("Tahoma", Font.PLAIN, 18));
										add(btnAdicionar, "cell 0 6");
										
												btnExcluir = new JButton("Excluir");
												btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
												add(btnExcluir, "cell 0 6");
												
														btnEmitirNota = new JButton("Emitir nota fiscal");
														btnEmitirNota.setFont(new Font("Tahoma", Font.PLAIN, 18));
														add(btnEmitirNota, "cell 0 6");
														
																btnDeslogar = new JButton("Sair");
																btnDeslogar.setFont(new Font("Tahoma", Font.PLAIN, 18));
																add(btnDeslogar, "cell 0 6");
																
																		JLabel lblNewLabel_2 = new JLabel("Total: R$");
																		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
																		add(lblNewLabel_2, "flowx,cell 3 6,alignx right");
																				
																						TFTotal = new JTextField();
																						TFTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
																						TFTotal.setEditable(false);
																						add(TFTotal, "cell 3 6,alignx right");
																						TFTotal.setColumns(10);
	}

	public void adicionarOuvinte(ComponentListener listener) {
		this.addComponentListener(listener);
	}

	public void acaoAdicionar(ActionListener listener) {
		this.btnAdicionar.addActionListener(listener);
	}

	public void acaoRemover(ActionListener listener) {
		this.btnExcluir.addActionListener(listener);
	}

	public void acaoEmitirNota(ActionListener listener) {
		this.btnEmitirNota.addActionListener(listener);
	}

	public void acaoDeslogar(ActionListener listener) {
		this.btnDeslogar.addActionListener(listener);
	}

	public int getQuantidadeSelecionada() {
		return (Integer) this.spinnerQtd.getValue();
	}

	public DefaultTableModel getModeloProdutos() {
		return modeloProdutos;
	}

	public DefaultTableModel getModeloCarrinho() {
		return modeloCarrinho;
	}

	public JTable getTabelaProdutos() {
		return tabelaProdutos;
	}

	public JTable getTabelaCarrinho() {
		return tabelaCarrinho;
	}

	public void setTotal(double total) {
		this.TFTotal.setText(String.format("%.2f", total).replace(",", "."));
	}

	public void limparTabela(DefaultTableModel modelo) {
		if (modelo != null) {
			modelo.setRowCount(0);
		}
	}

	public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
	}
}