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
		setLayout(new MigLayout("", "[grow 10][][][grow][grow 10]", "[grow 1][][grow 1][grow 1][][grow 1][grow 1][]"));

		JLabel lblNewLabel = new JLabel("Seja Bem Vindo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblNewLabel, "cell 3 0,alignx center,aligny center");

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "cell 2 1,alignx center");

		TFUsuario = new JTextField();
		add(TFUsuario, "cell 3 1 1 2,grow");
		TFUsuario.setColumns(10);

		try {
			MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraCPF.setPlaceholderCharacter('_');
			TFCpf = new JFormattedTextField(mascaraCPF);
		} catch (ParseException e) {
			TFCpf = new JFormattedTextField();
		}
		
				JLabel lblCPF = new JLabel("CPF:");
				lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
				add(lblCPF, "cell 2 4,alignx trailing");
		
				add(TFCpf, "cell 3 4 1 2,grow");
				TFCpf.setColumns(10);

		BTEntrar = new JButton("Entrar");
		BTEntrar.setBackground(new Color(255, 255, 255));
		BTEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(BTEntrar, "flowx,cell 3 6,alignx center");

		LNao = new JLabel("Não possui uma conta?");
		LNao.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(LNao, "cell 3 7,alignx center");
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