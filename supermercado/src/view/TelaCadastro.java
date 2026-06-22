package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JRadioButton;

public class TelaCadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFUsuario;
	private JFormattedTextField TFCPF; // Alterado para JFormattedTextField
	private JButton BTCadastrar, btnSair;
	private ButtonGroup b;
	private JRadioButton RBSim, RBNao;

	public TelaCadastro() {
		setPreferredSize(new Dimension(750, 800));
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(new MigLayout("", "[grow 50][][grow 10][grow 50]", "[grow 2][][][][][grow 2][]"));

		JLabel lblNewLabel_1 = new JLabel("Cadastre-se");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(lblNewLabel_1, "cell 2 0,alignx center");
		
				JLabel lblNome = new JLabel("Nome:");
				lblNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
				add(lblNome, "cell 1 1,alignx right");
		
				TFUsuario = new JTextField();
				add(TFUsuario, "cell 2 1,growx");
				TFUsuario.setColumns(10);

		this.BTCadastrar = new JButton("Cadastrar");
		BTCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 18));

		try {
			MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraCPF.setPlaceholderCharacter('_');
			TFCPF = new JFormattedTextField(mascaraCPF);
		} catch (ParseException e) {
			TFCPF = new JFormattedTextField();
		}
		
				JLabel lblCPF = new JLabel("CPF:");
				lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 18));
				add(lblCPF, "cell 1 2,alignx right");
		add(TFCPF, "cell 2 2,growx");
		TFCPF.setColumns(10);
		
				JLabel lblNewLabel = new JLabel("Será um administrador?");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				add(lblNewLabel, "flowx,cell 2 4,alignx left,aligny center");
		add(BTCadastrar, "flowx,cell 2 6,alignx center");
		b = new ButtonGroup();

		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(btnSair, "cell 2 6");
								
										RBSim = new JRadioButton("Sim");
										RBSim.setFont(new Font("Tahoma", Font.PLAIN, 17));
										add(RBSim, "cell 2 4,aligny center");
										b.add(RBSim);
										
												RBNao = new JRadioButton("Não");
												RBNao.setFont(new Font("Tahoma", Font.PLAIN, 17));
												add(RBNao, "cell 2 4,aligny center");
												b.add(RBNao);
	}

	public void cadastrar(ActionListener actionListener) {
		this.BTCadastrar.addActionListener(actionListener);
	}

	public void acaoSair(ActionListener actionListener) {
		this.btnSair.addActionListener(actionListener);
	}

	public String getNome() {
		return this.TFUsuario.getText();
	}

	public String getCPF() {
		return this.TFCPF.getText();
	}

	public void limparCampos() {
		this.TFUsuario.setText("");
		this.TFCPF.setValue(null);
	}

	public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
	}

	public boolean getAdmin() {
		return this.RBSim.isSelected();
	}
}