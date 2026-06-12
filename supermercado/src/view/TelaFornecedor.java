package view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class TelaFornecedor extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField tfNome, tfCnpj, tfTelefone, tfEmail, tfEndereco;
    private JTextField tfPesquisa;
    private JButton    btnSalvar, btnLimpar, btnExcluir, btnVoltar;
    private JTable     table;
    private DefaultTableModel modelo;

    private boolean modoEdicao = false;
    private int     idEdicao   = -1;

    public TelaFornecedor() {
        setPreferredSize(new Dimension(750, 800));
        setBackground(new Color(245, 246, 250));
        setLayout(new MigLayout("fill, insets 10", "[grow]", "[][][][grow][]"));

        // ── Título ───────────────────────────────────────────
        JLabel lblTitulo = new JLabel("Gestão de Fornecedores");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(33, 90, 170));
        add(lblTitulo, "cell 0 0");

        // ── Formulário ───────────────────────────────────────
        JPanel form = new JPanel(new MigLayout("insets 10 0 10 0, wrap 4",
                "[90][grow][90][grow]", "[][][]"));
        form.setOpaque(false);

        form.add(label("Nome *:"));
        tfNome = campo(); form.add(tfNome, "growx");
        form.add(label("CNPJ:"));
        tfCnpj = campo(); form.add(tfCnpj, "growx");

        form.add(label("Telefone:"));
        tfTelefone = campo(); form.add(tfTelefone, "growx");
        form.add(label("E-mail:"));
        tfEmail = campo(); form.add(tfEmail, "growx");

        form.add(label("Endereço:"));
        tfEndereco = campo(); form.add(tfEndereco, "growx, span 3");

        add(form, "cell 0 1, growx");

        // ── Pesquisa ─────────────────────────────────────────
        JPanel pesq = new JPanel(new MigLayout("insets 0", "[70][grow]"));
        pesq.setOpaque(false);
        pesq.add(label("Pesquisar:"));
        tfPesquisa = new JTextField();
        tfPesquisa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        pesq.add(tfPesquisa, "growx");
        add(pesq, "cell 0 2, growx");

        // ── Tabela ───────────────────────────────────────────
        modelo = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID","Nome","CNPJ","Telefone","E-mail","Endereço"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(modelo);
        table.setRowHeight(24);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        add(scroll, "cell 0 3, grow");

        // ── Botões ───────────────────────────────────────────
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        bar.setOpaque(false);
        btnSalvar  = btn("Salvar",  new Color(33, 90, 170), Color.WHITE);
        btnLimpar  = btn("Limpar",  new Color(100,100,100), Color.WHITE);
        btnExcluir = btn("Excluir", new Color(200,50,50),   Color.WHITE);
        btnVoltar  = btn("Voltar",  new Color(80,80,80),    Color.WHITE);
        bar.add(btnSalvar); bar.add(btnLimpar);
        bar.add(btnExcluir); bar.add(Box.createHorizontalStrut(20)); bar.add(btnVoltar);
        add(bar, "cell 0 4, growx");
    }

    private JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return l;
    }
    private JTextField campo() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tf.setPreferredSize(new Dimension(100, 30));
        return tf;
    }
    private JButton btn(String txt, Color bg, Color fg) {
        JButton b = new JButton(txt);
        b.setBackground(bg); b.setForeground(fg);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setPreferredSize(new Dimension(100, 32));
        return b;
    }

    // ── Modo Edição ──────────────────────────────────────────
    public void ativarModoEdicao(int id, String nome, String cnpj,
                                  String tel, String email, String end) {
        this.modoEdicao = true;
        this.idEdicao   = id;
        tfNome.setText(nome); tfCnpj.setText(cnpj);
        tfTelefone.setText(tel); tfEmail.setText(email); tfEndereco.setText(end);
        btnSalvar.setText("Atualizar");
        btnSalvar.setBackground(new Color(0, 140, 90));
    }
    public void desativarModoEdicao() {
        this.modoEdicao = false; this.idEdicao = -1;
        btnSalvar.setText("Salvar");
        btnSalvar.setBackground(new Color(33, 90, 170));
    }
    public boolean isModoEdicao() { return modoEdicao; }
    public int     getIdEdicao()  { return idEdicao; }

    // ── Getters ──────────────────────────────────────────────
    public String getNome()      { return tfNome.getText(); }
    public String getCnpj()      { return tfCnpj.getText(); }
    public String getTelefone()  { return tfTelefone.getText(); }
    public String getEmail()     { return tfEmail.getText(); }
    public String getEndereco()  { return tfEndereco.getText(); }
    public String getPesquisa()  { return tfPesquisa.getText(); }
    public JTable getTabela()    { return table; }
    public DefaultTableModel getModelo() { return modelo; }

    public void limparCampos() {
        tfNome.setText(""); tfCnpj.setText(""); tfTelefone.setText("");
        tfEmail.setText(""); tfEndereco.setText("");
        desativarModoEdicao();
    }
    public void limparTabela() { modelo.setRowCount(0); }

    // ── Ações ────────────────────────────────────────────────
    public void acaoSalvar(ActionListener al)  { btnSalvar.addActionListener(al); }
    public void acaoLimpar(ActionListener al)  { btnLimpar.addActionListener(al); }
    public void acaoExcluir(ActionListener al) { btnExcluir.addActionListener(al); }
    public void acaoVoltar(ActionListener al)  { btnVoltar.addActionListener(al); }
    public void adicionarOuvinte(java.awt.event.ComponentListener l) { addComponentListener(l); }

    public void adicionarOuvintePesquisa(javax.swing.event.DocumentListener dl) {
        tfPesquisa.getDocument().addDocumentListener(dl);
    }

    public void exibirMensagem(String titulo, String msg, int tipo) {
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }
}
