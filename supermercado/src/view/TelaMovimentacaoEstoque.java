package view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Produto;

public class TelaMovimentacaoEstoque extends JPanel {
    private static final long serialVersionUID = 1L;

    private JComboBox<Produto> cbProduto;
    private JSpinner          spQtd;
    private JButton           btnEntrada, btnSaida, btnAtualizar, btnVoltar;
    private JTable            table;
    private DefaultTableModel modelo;

    public TelaMovimentacaoEstoque() {
        setPreferredSize(new Dimension(750, 800));
        setBackground(new Color(245, 246, 250));
        setLayout(new MigLayout("fill, insets 10", "[grow]", "[][][][grow][]"));

        // ── Título ───────────────────────────────────────────
        JLabel lblTitulo = new JLabel("Controle de Estoque");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(33, 90, 170));
        add(lblTitulo, "cell 0 0");

        // ── Painel de ação ───────────────────────────────────
        JPanel acao = new JPanel(new MigLayout("insets 15, wrap 4",
                "[90][grow][80][grow]", "[][]"));
        acao.setBackground(Color.WHITE);
        acao.setBorder(BorderFactory.createTitledBorder("Registrar Movimentação"));

        acao.add(label("Produto:"));
        cbProduto = new JComboBox<>();
        cbProduto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        acao.add(cbProduto, "growx");

        acao.add(label("Quantidade:"));
        spQtd = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));
        spQtd.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        acao.add(spQtd, "growx");

        // Botões de ação
        btnEntrada = new JButton("▲ Registrar Entrada");
        btnEntrada.setBackground(new Color(0, 140, 90));
        btnEntrada.setForeground(Color.WHITE);
        btnEntrada.setFocusPainted(false);
        btnEntrada.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEntrada.setPreferredSize(new Dimension(180, 36));

        btnSaida = new JButton("▼ Registrar Saída");
        btnSaida.setBackground(new Color(200, 60, 40));
        btnSaida.setForeground(Color.WHITE);
        btnSaida.setFocusPainted(false);
        btnSaida.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSaida.setPreferredSize(new Dimension(180, 36));

        acao.add(btnEntrada, "span 2, gaptop 10");
        acao.add(btnSaida, "span 2, gaptop 10");
        add(acao, "cell 0 1, growx");

        // ── Controles da tabela ──────────────────────────────
        JPanel ctrl = new JPanel(new MigLayout("insets 0", "[grow][]"));
        ctrl.setOpaque(false);
        JLabel lblHist = new JLabel("Histórico de Movimentações:");
        lblHist.setFont(new Font("Segoe UI", Font.BOLD, 13));
        ctrl.add(lblHist, "growx");
        btnAtualizar = new JButton("↺ Atualizar");
        btnAtualizar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        ctrl.add(btnAtualizar);
        add(ctrl, "cell 0 2, growx");

        // ── Tabela ───────────────────────────────────────────
        modelo = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID","Produto","Tipo","Quantidade","Data/Hora"}) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(modelo) {
            public Component prepareRenderer(javax.swing.table.TableCellRenderer r, int row, int col) {
                Component c = super.prepareRenderer(r, row, col);
                String tipo = (String) getModel().getValueAt(row, 2);
                if ("ENTRADA".equals(tipo)) c.setForeground(new Color(0, 130, 80));
                else if ("SAIDA".equals(tipo)) c.setForeground(new Color(180, 40, 40));
                else c.setForeground(Color.BLACK);
                if (isRowSelected(row)) { c.setBackground(new Color(200, 220, 255)); }
                else { c.setBackground(Color.WHITE); }
                return c;
            }
        };
        table.setRowHeight(24);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        add(scroll, "cell 0 3, grow");

        // ── Rodapé ───────────────────────────────────────────
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        bar.setOpaque(false);
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnVoltar.setBackground(new Color(80, 80, 80));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setPreferredSize(new Dimension(100, 32));
        bar.add(btnVoltar);
        add(bar, "cell 0 4, growx");
    }

    private JLabel label(String t) {
        JLabel l = new JLabel(t);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return l;
    }

    // ── Produtos ─────────────────────────────────────────────
    public void carregarProdutos(List<Produto> lista) {
        cbProduto.removeAllItems();
        for (Produto p : lista) cbProduto.addItem(p);
    }

    public Produto getProdutoSelecionado() {
        return (Produto) cbProduto.getSelectedItem();
    }

    public int getQuantidade() { return (Integer) spQtd.getValue(); }

    public DefaultTableModel getModelo() { return modelo; }
    public void limparTabela() { modelo.setRowCount(0); }

    // ── Ações ────────────────────────────────────────────────
    public void acaoEntrada(ActionListener al)  { btnEntrada.addActionListener(al); }
    public void acaoSaida(ActionListener al)    { btnSaida.addActionListener(al); }
    public void acaoAtualizar(ActionListener al){ btnAtualizar.addActionListener(al); }
    public void acaoVoltar(ActionListener al)   { btnVoltar.addActionListener(al); }
    public void adicionarOuvinte(java.awt.event.ComponentListener l) { addComponentListener(l); }

    public void exibirMensagem(String titulo, String msg, int tipo) {
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }
}
