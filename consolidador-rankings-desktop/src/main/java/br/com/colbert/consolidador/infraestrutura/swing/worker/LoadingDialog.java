package br.com.colbert.consolidador.infraestrutura.swing.worker;

import java.awt.*;
import java.awt.event.*;
import java.beans.Beans;
import java.io.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.*;

import br.com.colbert.base.ui.*;
import br.com.colbert.consolidador.infraestrutura.io.ImagesProvider;

/**
 * Tela exibida quando é aguardada a execução de uma tarefa em <em>background</em>.
 * 
 * @author Thiago Colbert
 * @since 09/01/2015
 */
@ApplicationScoped
public class LoadingDialog implements WindowView, Serializable {

    private static final long serialVersionUID = 8248885596292025385L;

    private JDialog dialog;

    @Inject
    @JanelaPrincipal
    private WindowView mainWindow;
    @Inject
    private ImagesProvider images;

    public static void main(String[] args) throws IOException {
        new LoadingDialog().init();
    }

    @PostConstruct
    protected void init() {
        dialog = new JDialog(mainWindow != null ? (Frame) mainWindow.getContainer() : null, true);
        dialog.setUndecorated(true);
        dialog.setPreferredSize(new Dimension(200, 230));
        dialog.setResizable(false);
        dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dialog.setLocationRelativeTo(mainWindow != null ? mainWindow.getContainer() : null);

        dialog.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dialog.setVisible(false);
                }
            }
        });

        JLabel imagemLabel = new JLabel();
        imagemLabel.setIcon(Beans.isDesignTime() ? null : images.loading());
        dialog.getContentPane().add(imagemLabel, BorderLayout.CENTER);

        dialog.pack();
    }

    @Override
    public Container getContainer() {
        return dialog;
    }
}
