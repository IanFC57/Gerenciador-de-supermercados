package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class TelaProdutos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabelaProdutos;
	private JTable tabelaCarrinho;
	private JTextField TFTotal;
	private JButton btnAdicionar, btnExcluir;

	/**
	 * Create the panel.
	 */
	public TelaProdutos() {
		setLayout(new MigLayout("", "[][grow][]", "[][][grow 10][][][grow][grow 10]"));
		
		 String[] columnNames = {"Produto", "preço unitário", "Disponibilidade"};
         Object[][] data = {
         {"0044831576", "Mouse", "ELEC-101-BK"},
         };
		
		JLabel lblNewLabel = new JLabel("Produtos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel, "cell 0 1");
		
		JLabel lblNewLabel_1 = new JLabel("Carrinho de compras");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel_1, "cell 1 1");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 2,grow");
		
		tabelaProdutos = new JTable();
		tabelaProdutos.setEnabled(false);
		tabelaProdutos.setColumnSelectionAllowed(true);
		tabelaProdutos.setCellSelectionEnabled(true);
		scrollPane.setViewportView(tabelaProdutos);
		
		tabelaProdutos.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", ""},
			},
			new String[] {
				"Produto", "pre\u00E7o unit\u00E1rio", "Disponibilidade"
			}
		));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 1 2,grow");
		
		tabelaCarrinho = new JTable();
		tabelaCarrinho.setModel(new DefaultTableModel(
			new Object[][] {
				{"Ma\u00E7a", null, null},
				{null, null, ""},
			},
			new String[] {
				"Produto", "Quantidade", "Valor"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(tabelaCarrinho);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnAdicionar, "flowx,cell 0 4");
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnExcluir, "cell 0 4");
		
		JLabel lblNewLabel_2 = new JLabel("Total:");
		add(lblNewLabel_2, "flowx,cell 1 4");
		
		TFTotal = new JTextField();
		add(TFTotal, "cell 1 4");
		TFTotal.setColumns(10);
		
		
		
		
		
//		
//		table = new JTable();
//		add(table, "cell 1 1,grow");
//		
//		table.setModel(new DefaultTableModel(
//				new Object[][] {
//					{"0044831576", "Mouse", "ELEC-101-BK"},
//				},
//				new String[] {
//					"ID", "Produto", "SKU"
//				}
//			) {
//				Class[] columnTypes = new Class[] {
//					String.class, Object.class, Object.class, Object.class, Object.class
//				};
//				public Class getColumnClass(int columnIndex) {
//					return columnTypes[columnIndex];
//				}
//			});
		

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
