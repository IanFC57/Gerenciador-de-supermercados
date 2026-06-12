package view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class TelaLogin extends JPanel {
    private static final long serialVersionUID = 1L;

    private JFormattedTextField tfCpf;
    private JPasswordField      pfSenha;
    private JButton             btnEntrar;
    private JLabel              lblCadastrar;

    public TelaLogin() {
        setPreferredSize(new Dimension(750, 800));
        setBackground(new Color(240, 242, 245));
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow][][][][][][][grow][]"));

        // ── Título ──────────────────────────────────────────
        JLabel lblTitulo = new JLabel("Supermercado Manager");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(33, 90, 170));
        add(lblTitulo, "cell 0 1, alignx center");

        JLabel lblSub = new JLabel("Faça login para continuar");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSub.setForeground(Color.GRAY);
        add(lblSub, "cell 0 2, alignx center");

        // ── Painel central ───────────────────────────────────
        JPanel card = new JPanel(new MigLayout("wrap 1, insets 30 40 30 40, gapy 8", "[300]"));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)));

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

        card.add(label("Senha:"), "");
        pfSenha = new JPasswordField();
        pfSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pfSenha.setPreferredSize(new Dimension(300, 34));
        card.add(pfSenha, "growx");

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(33, 90, 170));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEntrar.setPreferredSize(new Dimension(300, 38));
        btnEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.add(btnEntrar, "growx, gaptop 10");

        lblCadastrar = new JLabel("<html><u>Não possui uma conta? Cadastre-se</u></html>");
        lblCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCadastrar.setForeground(new Color(33, 90, 170));
        lblCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.add(lblCadastrar, "alignx center, gaptop 8");

        add(card, "cell 0 4, alignx center");
    }

    private JLabel label(String txt) {
        JLabel l = new JLabel(txt);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return l;
    }

    // ── Getters ──────────────────────────────────────────────
    public String getCpf()   { return tfCpf.getText(); }
    public String getSenha() { return new String(pfSenha.getPassword()); }

    public void limparCampos() {
        tfCpf.setValue(null);
        pfSenha.setText("");
    }

    // ── Ações ────────────────────────────────────────────────
    public void autenticar(ActionListener al) { btnEntrar.addActionListener(al); }
    public void acaoCadastrar(MouseListener ml) { lblCadastrar.addMouseListener(ml); }

    public void exibirMensagem(String titulo, String mensagem, int tipo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
    }
}
