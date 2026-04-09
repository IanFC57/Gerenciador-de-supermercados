package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

public class TelaCadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFUsuario;
	private JTextField TFCPF;
	private JButton BTCadastrar, btnSair;
	private ButtonGroup b;
	private JRadioButton RBSim, RBNao;
	

	/**
	 * Create the panel.
	 */
	public TelaCadastro() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(new MigLayout("", "[grow 10][][grow][grow 10]", "[grow 2][][][][][grow 2][]"));
		
		JLabel lblNewLabel_1 = new JLabel("Cadastre-se");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblNewLabel_1, "cell 2 0,alignx center");
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "cell 1 2,alignx left");
		
		TFUsuario = new JTextField();
		add(TFUsuario, "cell 2 2,growx");
		TFUsuario.setColumns(10);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblCPF, "cell 1 3,alignx trailing");
		
		this.BTCadastrar = new JButton("Cadastrar");
		
		
		
		TFCPF = new JTextField();
		add(TFCPF, "cell 2 3,growx");
		TFCPF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Será um administrador?");
		add(lblNewLabel, "flowx,cell 2 5,alignx left,aligny center");
		add(BTCadastrar, "flowx,cell 2 6,alignx center");
		
		
		
		RBSim = new JRadioButton("Sim");
		add(RBSim, "cell 2 5,aligny center");
//		RBSim.isSelected()
		
		RBNao = new JRadioButton("Não");
		add(RBNao, "cell 2 5,aligny center");
		b = new ButtonGroup();
		b.add(RBSim);
		b.add(RBNao);
		
		btnSair = new JButton("Sair");
		add(btnSair, "cell 2 6");
		

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
	
	public void limparCampos(){
		this.TFUsuario.setText(" ");
		this.TFCPF.setText(" ");
		
	}
	
	public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
		JOptionPane.showMessageDialog(
				null,
				mensagem,
				titulo,
				tipoMensagem
				);
		// TODO Auto-generated method stub
		
	}
	public boolean getAdmin() {
		return this.RBSim.isSelected();
	}
}
