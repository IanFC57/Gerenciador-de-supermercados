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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFUsuario;
	private JButton BTEntrar;
	private JLabel LNao;
	private JFormattedTextField TFCpf;

	public TelaLogin() {
		setPreferredSize(new Dimension(750, 800));
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(new MigLayout("", "[grow 10][][][][][][][][][][][][][][][][grow 10]",
				"[][][grow 1][][grow 1][][grow 1][][][grow 1][]"));

		try {
			MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraCPF.setPlaceholderCharacter('_');
			TFCpf = new JFormattedTextField(mascaraCPF);
		} catch (ParseException e) {
			TFCpf = new JFormattedTextField();
		}

		JLabel lblNewLabel = new JLabel("Seja Bem Vindo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		add(lblNewLabel, "cell 8 1,alignx center,aligny center");

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblNome, "cell 1 3,alignx center");

		TFUsuario = new JTextField();
		TFUsuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(TFUsuario, "cell 2 3 14 1,grow");
		TFUsuario.setColumns(10);

		JLabel lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(lblCPF, "cell 1 5,alignx trailing");
		
		TFCpf.setFont(new Font("Tahoma", Font.PLAIN, 17));

		add(TFCpf, "cell 2 5 14 1,grow");
		TFCpf.setColumns(10);

		BTEntrar = new JButton("Entrar");
		BTEntrar.setBackground(new Color(255, 255, 255));
		BTEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		BTEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(BTEntrar, "flowx,cell 8 7 1 2,grow");

		LNao = new JLabel("Não possui uma conta?");
		LNao.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(LNao, "cell 8 10,alignx center");
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

	public void exibirMensagem(String titulo, String mensagem, int tipo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, tipo);
	}
}