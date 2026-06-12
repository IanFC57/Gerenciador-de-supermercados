package view;

import javax.swing.*;
import java.awt.*;

/**
 * Frame principal — contém o CardLayout com todas as telas.
 */
public class Principal extends JFrame {
    private static final long serialVersionUID = 1L;

    private final JPanel     container;
    private final CardLayout cardLayout;

    public Principal() {
        super("Supermercado Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cardLayout = new CardLayout();
        container  = new JPanel(cardLayout);
        add(container);
    }

    public JPanel    getContainer()  { return container; }
    public CardLayout getCardLayout(){ return cardLayout; }

    public void exibir() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
