package view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFUsuario;
	private JButton BTEntrar;
	private JLabel LNao;
	private JTextField TFCpf;

	/**
	 * Create the panel.
	 */
	public TelaLogin() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(new MigLayout("", "[grow 10][][grow][grow 10]", "[grow 5][][][][][grow 5][]"));
		
		JLabel lblNewLabel = new JLabel("Seja Bem Vindo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblNewLabel, "cell 2 0,alignx center,aligny center");
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "cell 1 1,alignx center");
		
		TFUsuario = new JTextField();
		add(TFUsuario, "cell 2 1,growx");
		TFUsuario.setColumns(10);
		
		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblCPF, "cell 1 3,alignx trailing");
		
		TFCpf = new JTextField();
		add(TFCpf, "cell 2 3,growx");
		TFCpf.setColumns(10);
		
		BTEntrar = new JButton("Entrar");
		add(BTEntrar, "flowx,cell 2 5,alignx center");
		
		LNao = new JLabel("Não possui uma conta?");
		LNao.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(LNao, "cell 2 6,alignx center");

	}
	public String getUsuario() {
		return this.TFUsuario.getText();
	}
	
	public String getCpf() {
		return this.TFCpf.getText();
	}
	
	public void Cadastro(MouseListener mouselistener) {
		this.LNao.addMouseListener(mouselistener);
	}
	public void autenticar(ActionListener actionlistener) {
		this.BTEntrar.addActionListener(actionlistener);
	}
	public void exibirMensagem(String string, String string2, int i) {
		// TODO Auto-generated method stub
		
	}

}
