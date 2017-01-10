package br.com.colbert.consolidador.aplicacao.principal;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.*;
import javax.inject.Inject;

import org.mvp4j.AppController;
import org.slf4j.Logger;

import br.com.colbert.base.aplicacao.Presenter;
import br.com.colbert.base.ui.*;
import br.com.colbert.consolidador.aplicacao.comum.erro.ErroPresenter;
import br.com.colbert.consolidador.infraestrutura.info.TituloAplicacao;
import br.com.colbert.consolidador.ui.comum.mensagens.*;

/**
 * <em>Presenter</em> principal da aplicação.
 *
 * @author Thiago Colbert
 * @since 18/12/2014
 */
public class MainPresenter implements Presenter, Serializable {

    private static final long serialVersionUID = 9104572255370820023L;

    @Inject
    private Logger logger;

    @Inject
    private AppController appController;

    @Inject
    @JanelaPrincipal
    private WindowView mainWindow;
    @Inject
    private MessagesView messagesView;

    @Inject
    @Any
    private Instance<Presenter> presenters;

    @Inject
    @TituloAplicacao
    private String tituloAplicacao;

    @Inject
    private ErroPresenter erroPresenter;

    @PostConstruct
    @Override
    public void doBinding() {
        appController.bindPresenter(mainWindow, this);
    }

    @Override
    public void start() {
        logger.info("Iniciando...");
        Thread.setDefaultUncaughtExceptionHandler(erroPresenter);

        setUpView();

        logger.debug("Exibindo a janela principal");
        mainWindow.show();
    }

    private void setUpView() {
        logger.debug("Título da aplicação: {}", tituloAplicacao);
        mainWindow.setTitulo(tituloAplicacao);
    }

    /**
     * Encerra a aplicação.
     */
    public void sair() {
        if (messagesView.exibirConfirmacao("Deseja realmente sair?") == RespostaConfirmacao.SIM) {
            logger.info("Encerrando...");
            mainWindow.close();
        }
    }

    /**
     * Verifica se a janela principal está visível ou não.
     * 
     * @return <code>true</code>/<code>false</code>
     */
    public boolean isJanelaVisivel() {
        return mainWindow.isVisible();
    }
}
