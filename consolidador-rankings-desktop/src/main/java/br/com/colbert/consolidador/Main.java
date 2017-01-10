package br.com.colbert.consolidador;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import br.com.colbert.consolidador.aplicacao.principal.MainPresenter;

/**
 * Classe que inicia a aplicação.
 * 
 * @author ThiagoColbert
 * @since 25 de dez de 2016
 */
@ApplicationScoped
public final class Main {

    @Inject
    private transient MainPresenter mainPresenter;

    /**
     * Método invocado assim que o contexto CDI é inicializado.
     * 
     * @param event
     *            o evento
     */
    public void contextoInicializado(@Observes ContainerInitialized event, @Parameters List<String> parameters) {
        mainPresenter.start();
    }
}
