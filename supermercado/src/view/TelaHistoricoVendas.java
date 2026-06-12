package view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class TelaHistoricoVendas extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTable           tabelaVendas, tabelaItens;
    private DefaultTableModel modeloVendas, modeloItens;
    private JButton           btnVoltar, btnAtualizar;
    private JLabel            lblTotal;

    public TelaHistoricoVendas() {
        setPreferredSize(new Dimension(750, 800));
        setBackground(new Color(245, 246, 250));
        setLayout(new MigLayout("fill, insets 10", "[grow]",
                "[][grow 50][][grow 50][]"));

        // ── Cabeçalho ────────────────────────────────────────
        JPanel header = new JPanel(new MigLayout("insets 0", "[grow][][]"));
        header.setOpaque(false);
        JLabel lblTitulo = new JLabel("Histórico de Vendas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(33, 90, 170));
        header.add(lblTitulo, "growx");
        lblTotal = new JLabel();
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTotal.setForeground(new Color(0, 120, 60));
        header.add(lblTotal, "");
        btnAtualizar = new JButton("↺");
        btnAtualizar.setToolTipText("Atualizar lista");
        btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.add(btnAtualizar, "");
        add(header, "cell 0 0, growx");

        // ── Tabela de Vendas ─────────────────────────────────
        modeloVendas = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID","Cliente","Data/Hora","Total (R$)"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelaVendas = new JTable(modeloVendas);
        tabelaVendas.setRowHeight(26);
        tabelaVendas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabelaVendas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabelaVendas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaVendas.setGridColor(new Color(230, 230, 230));

        JScrollPane scrollVendas = new JScrollPane(tabelaVendas);
        scrollVendas.setBorder(BorderFactory.createTitledBorder("Vendas"));
        add(scrollVendas, "cell 0 1, grow");

        // ── Separador ────────────────────────────────────────
        JLabel lblItens = new JLabel("Itens da venda selecionada:");
        lblItens.setFont(new Font("Segoe UI", Font.BOLD, 13));
        add(lblItens, "cell 0 2");

        // ── Tabela de Itens ──────────────────────────────────
        modeloItens = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Produto","Qtd","Preço Unitário","Subtotal"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelaItens = new JTable(modeloItens);
        tabelaItens.setRowHeight(24);
        tabelaItens.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabelaItens.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scrollItens = new JScrollPane(tabelaItens);
        scrollItens.setBorder(BorderFactory.createTitledBorder("Itens"));
        add(scrollItens, "cell 0 3, grow");

        // ── Rodapé ───────────────────────────────────────────
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        bar.setOpaque(false);
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(80, 80, 80));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnVoltar.setPreferredSize(new Dimension(100, 32));
        bar.add(btnVoltar);
        add(bar, "cell 0 4, growx");
    }

    // ── Getters ──────────────────────────────────────────────
    public JTable            getTabelaVendas()  { return tabelaVendas; }
    public DefaultTableModel getModeloVendas()  { return modeloVendas; }
    public DefaultTableModel getModeloItens()   { return modeloItens; }

    public void setLabelTotal(String texto) { lblTotal.setText(texto); }

    public void limparVendas() { modeloVendas.setRowCount(0); }
    public void limparItens()  { modeloItens.setRowCount(0); }

    // ── Ações ────────────────────────────────────────────────
    public void acaoVoltar(ActionListener al)          { btnVoltar.addActionListener(al); }
    public void acaoAtualizar(ActionListener al)       { btnAtualizar.addActionListener(al); }
    public void acaoSelecaoVenda(javax.swing.event.ListSelectionListener l) {
        tabelaVendas.getSelectionModel().addListSelectionListener(l);
    }
    public void adicionarOuvinte(java.awt.event.ComponentListener l) { addComponentListener(l); }

    public void exibirMensagem(String titulo, String msg, int tipo) {
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }
}
