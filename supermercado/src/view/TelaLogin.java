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

public class TelaLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TFUsuario;
	private JPasswordField PFUsuario;

	/**
	 * Create the panel.
	 */
	public TelaLogin() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(new MigLayout("", "[grow 10][][grow 50][grow 10]", "[grow 5][][][][][grow 5][]"));
		
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
		add(lblCPF, "cell 1 3,alignx left");
		
		PFUsuario = new JPasswordField();
		add(PFUsuario, "cell 2 3,growx");
		
		JButton BTEntrar = new JButton("Entrar");
		BTEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(BTEntrar, "flowx,cell 2 5,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Não possui uma conta?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblNewLabel_1, "cell 2 6,alignx center");

	}

}
