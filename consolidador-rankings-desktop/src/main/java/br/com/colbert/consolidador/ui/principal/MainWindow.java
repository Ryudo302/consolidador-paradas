package br.com.colbert.consolidador.ui.principal;

import java.awt.*;
import java.awt.event.WindowListener;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.mvp4j.annotation.Action;
import org.mvp4j.annotation.MVP;

import br.com.colbert.base.ui.WindowView;
import br.com.colbert.consolidador.aplicacao.principal.MainPresenter;
import br.com.colbert.consolidador.infraestrutura.swing.SwingUtils;

/**
 * A tela principal da aplicação implementada como um {@link JFrame}.
 *
 * @author Thiago Colbert
 * @since 17/12/2014
 */
@MVP(modelClass = Void.class, presenterClass = MainPresenter.class)
public class MainWindow implements WindowView, Serializable {

    private static final long serialVersionUID = 581637404111512993L;

    @Action(name = "sair", EventType = WindowListener.class, EventAction = "windowClosing")
    private JFrame frame;

    @Action(name = "sair")
    private JMenuItem menuItemSair;
   // @Action(name = "exibirTelaSobre")
    private JMenuItem menuItemSobre;

    /**
     * Inicializa todos os componentes da janela.
     */
    @PostConstruct
    protected void init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setBounds(100, 100, 600, 350);
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuArquivo = new JMenu("Arquivo");
        menuBar.add(menuArquivo);

        menuItemSair = new JMenuItem("Sair");
        menuArquivo.add(menuItemSair);

        JMenu menuAjuda = new JMenu("Ajuda");
        menuBar.add(menuAjuda);

        menuItemSobre = new JMenuItem("Sobre");
        menuAjuda.add(menuItemSobre);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout(0, 0));

        frame.setContentPane(contentPane);
    }

    @Override
    public void close() {
        // sobrescreve para chamar dispose() ao invés de setVisible(false)
        SwingUtils.invokeLater(() -> frame.dispose());
    }

    /**
     * Altera a tela sendo atualmente exibida na janela.
     * 
     * @param tela
     *            a ser exibida
     */
    public void mudarTela(String tela) {
        Container contentPane = frame.getContentPane();
        CardLayout layout = (CardLayout) contentPane.getLayout();
        layout.show(contentPane, tela);
    }

    @Override
    public Container getContainer() {
        return getFrame();
    }

    public Frame getFrame() {
        return frame;
    }

    public JMenuItem getMenuItemSair() {
        return menuItemSair;
    }

    public JMenuItem getMenuItemSobre() {
        return menuItemSobre;
    }

    public static void main(String[] args) {
        new MainWindow().init();
    }
}
