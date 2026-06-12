package view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TelaProdutos extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTable           tabelaProdutos, tabelaCarrinho;
    private DefaultTableModel modeloProdutos, modeloCarrinho;
    private JTextField       tfTotal;
    private JButton          btnAdicionar, btnRemover, btnFinalizar, btnSair;
    private JSpinner         spinnerQtd;

    public TelaProdutos() {
        setPreferredSize(new Dimension(750, 800));
        setBackground(new Color(245, 246, 250));
        setLayout(new MigLayout("fill, insets 10",
                "[grow 45][grow 55]", "[][grow][][]"));

        // ── Cabeçalho ────────────────────────────────────────
        JLabel lblProd = new JLabel("Produtos Disponíveis");
        lblProd.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(lblProd, "cell 0 0");

        JLabel lblCart = new JLabel("Carrinho de Compras");
        lblCart.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(lblCart, "cell 1 0");

        // ── Tabela Produtos ──────────────────────────────────
        modeloProdutos = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID","Produto","Preço","Estoque"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelaProdutos = new JTable(modeloProdutos);
        tabelaProdutos.setRowHeight(24);
        tabelaProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabelaProdutos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabelaProdutos.setColumnSelectionAllowed(false);
        tabelaProdutos.setRowSelectionAllowed(true);
        tabelaProdutos.setGridColor(new Color(230, 230, 230));
        tabelaProdutos.setSelectionBackground(new Color(200, 220, 255));

        JScrollPane scrollProd = new JScrollPane(tabelaProdutos);
        scrollProd.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(scrollProd, "cell 0 1, grow");

        // ── Tabela Carrinho ──────────────────────────────────
        modeloCarrinho = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID","Produto","Qtd","Subtotal"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelaCarrinho = new JTable(modeloCarrinho);
        tabelaCarrinho.setRowHeight(24);
        tabelaCarrinho.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabelaCarrinho.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabelaCarrinho.setGridColor(new Color(230, 230, 230));

        JScrollPane scrollCart = new JScrollPane(tabelaCarrinho);
        scrollCart.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(scrollCart, "cell 1 1, grow");

        // ── Controles ────────────────────────────────────────
        JPanel ctrlProd = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        ctrlProd.setOpaque(false);
        JLabel lblQtd = new JLabel("Qtd:"); lblQtd.setFont(new Font("Segoe UI", Font.PLAIN, 13)); ctrlProd.add(lblQtd);
        spinnerQtd = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        spinnerQtd.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        spinnerQtd.setPreferredSize(new Dimension(60, 30));
        ctrlProd.add(spinnerQtd);
        btnAdicionar = compraBtn("+ Adicionar", new Color(0, 130, 80));
        btnRemover   = compraBtn("− Remover",   new Color(180, 60, 40));
        ctrlProd.add(btnAdicionar);
        ctrlProd.add(btnRemover);
        add(ctrlProd, "cell 0 2");

        JPanel ctrlCart = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        ctrlCart.setOpaque(false);
        JLabel lblTotalLabel = new JLabel("Total: R$"); lblTotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14)); ctrlCart.add(lblTotalLabel);
        tfTotal = new JTextField("0,00");
        tfTotal.setEditable(false);
        tfTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tfTotal.setHorizontalAlignment(JTextField.RIGHT);
        tfTotal.setPreferredSize(new Dimension(100, 30));
        ctrlCart.add(tfTotal);
        add(ctrlCart, "cell 1 2");

        // ── Rodapé ───────────────────────────────────────────
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        bar.setOpaque(false);
        btnFinalizar = compraBtn("✓ Finalizar Compra", new Color(33, 90, 170));
        btnSair      = compraBtn("Sair", new Color(80, 80, 80));
        bar.add(btnFinalizar);
        bar.add(btnSair);
        add(bar, "cell 0 3, span 2");
    }

    private JButton compraBtn(String txt, Color bg) {
        JButton b = new JButton(txt);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setPreferredSize(new Dimension(160, 32));
        return b;
    }

    // ── Ações ────────────────────────────────────────────────
    public void adicionarOuvinte(java.awt.event.ComponentListener l) { addComponentListener(l); }
    public void acaoAdicionar(ActionListener al)  { btnAdicionar.addActionListener(al); }
    public void acaoRemover(ActionListener al)    { btnRemover.addActionListener(al); }
    public void acaoEmitirNota(ActionListener al) { btnFinalizar.addActionListener(al); }
    public void acaoDeslogar(ActionListener al)   { btnSair.addActionListener(al); }

    // ── Getters ──────────────────────────────────────────────
    public int getQuantidadeSelecionada()  { return (Integer) spinnerQtd.getValue(); }
    public DefaultTableModel getModeloProdutos()  { return modeloProdutos; }
    public DefaultTableModel getModeloCarrinho()  { return modeloCarrinho; }
    public JTable getTabelaProdutos()    { return tabelaProdutos; }
    public JTable getTabelaCarrinho()    { return tabelaCarrinho; }

    public void setTotal(double total) {
        tfTotal.setText(String.format("%.2f", total));
    }
    public void limparTabela(DefaultTableModel m) {
        if (m != null) m.setRowCount(0);
    }

    public void exibirMensagem(String titulo, String mensagem, int tipo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, tipo);
    }
}
