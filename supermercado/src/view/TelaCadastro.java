package view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class TelaCadastro extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField          tfNome;
    private JFormattedTextField tfCpf;
    private JPasswordField      pfSenha;
    private JPasswordField      pfConfirmarSenha;
    private JRadioButton        rbSim, rbNao;
    private ButtonGroup         grupo;
    private JButton             btnCadastrar, btnSair;

    public TelaCadastro() {
        setPreferredSize(new Dimension(750, 800));
        setBackground(new Color(240, 242, 245));
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow][][][grow][]"));

        JLabel lblTitulo = new JLabel("Criar Conta");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(33, 90, 170));
        add(lblTitulo, "cell 0 1, alignx center");

        JPanel card = new JPanel(new MigLayout("wrap 2, insets 30 40 30 40, gapy 8", "[][300]"));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        // Nome
        card.add(label("Nome:"), "");
        tfNome = new JTextField();
        tfNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfNome.setPreferredSize(new Dimension(300, 34));
        card.add(tfNome, "growx");

        // CPF
        card.add(label("CPF:"), "");
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setPlaceholderCharacter('_');
            tfCpf = new JFormattedTextField(mask);
        } catch (ParseException e) {
            tfCpf = new JFormattedTextField();
        }
        tfCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tfCpf.setPreferredSize(new Dimension(300, 34));
        card.add(tfCpf, "growx");

        // Senha
        card.add(label("Senha:"), "");
        pfSenha = new JPasswordField();
        pfSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pfSenha.setPreferredSize(new Dimension(300, 34));
        card.add(pfSenha, "growx");

        // Confirmar senha
        card.add(label("Confirmar senha:"), "");
        pfConfirmarSenha = new JPasswordField();
        pfConfirmarSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pfConfirmarSenha.setPreferredSize(new Dimension(300, 34));
        card.add(pfConfirmarSenha, "growx");

        // Admin
        card.add(label("Administrador?"), "");
        JPanel rbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rbPanel.setBackground(Color.WHITE);
        rbSim = new JRadioButton("Sim");
        rbNao = new JRadioButton("Não");
        rbNao.setSelected(true);
        grupo = new ButtonGroup();
        grupo.add(rbSim); grupo.add(rbNao);
        rbPanel.add(rbSim); rbPanel.add(rbNao);
        card.add(rbPanel, "");

        // Botões
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(33, 90, 170));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCadastrar.setPreferredSize(new Dimension(140, 38));

        btnSair = new JButton("Cancelar");
        btnSair.setFocusPainted(false);
        btnSair.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnSair.setPreferredSize(new Dimension(140, 38));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.add(btnCadastrar); btnPanel.add(btnSair);
        card.add(new JLabel(), "");
        card.add(btnPanel, "growx, gaptop 10");

        add(card, "cell 0 2, alignx center");
    }

    private JLabel label(String txt) {
        JLabel l = new JLabel(txt);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return l;
    }

    // ── Getters ──────────────────────────────────────────────
    public String  getNome()            { return tfNome.getText(); }
    public String  getCPF()             { return tfCpf.getText(); }
    public String  getSenha()           { return new String(pfSenha.getPassword()); }
    public String  getConfirmarSenha()  { return new String(pfConfirmarSenha.getPassword()); }
    public boolean getAdmin()           { return rbSim.isSelected(); }

    public void limparCampos() {
        tfNome.setText("");
        tfCpf.setValue(null);
        pfSenha.setText("");
        pfConfirmarSenha.setText("");
        rbNao.setSelected(true);
    }

    // ── Ações ────────────────────────────────────────────────
    public void cadastrar(ActionListener al)  { btnCadastrar.addActionListener(al); }
    public void acaoSair(ActionListener al)   { btnSair.addActionListener(al); }

    public void exibirMensagem(String titulo, String mensagem, int tipo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
    }
}
