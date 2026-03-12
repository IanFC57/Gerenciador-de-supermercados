package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JRadioButton;

public class TelaCadastroProdutos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFProdutos;
	private JTextField TFQtd;

	/**
	 * Create the panel.
	 */
	public TelaCadastroProdutos() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(new MigLayout("", "[grow 10][][grow][grow 10]", "[grow 2][][][][][grow 2][]"));
		
		JLabel lblNewLabel_1 = new JLabel("Cadastrar Produtos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblNewLabel_1, "cell 2 0,alignx center");
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "cell 1 2,alignx left");
		
		TFProdutos = new JTextField();
		add(TFProdutos, "cell 2 2,growx");
		TFProdutos.setColumns(10);
		
		JLabel Qtd = new JLabel("Qtd:");
		Qtd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(Qtd, "cell 1 3,alignx trailing");
		
		JButton BTCadastrar = new JButton("Cadastrar");
		BTCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		TFQtd = new JTextField();
		add(TFQtd, "cell 2 3,growx");
		TFQtd.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Será um administrador?");
		add(lblNewLabel, "flowx,cell 2 5,alignx left,aligny center");
		add(BTCadastrar, "flowx,cell 2 6,alignx center");
		
		
		
		JRadioButton RBSim = new JRadioButton("Sim");
		add(RBSim, "cell 2 5,aligny center");
		
		JRadioButton RBNao = new JRadioButton("Não");
		add(RBNao, "cell 2 5,aligny center");
		ButtonGroup b = null;
		b.add(RBSim);
		b.add(RBNao);
		

	}

}
