package br.com.colbert.consolidador.ui.principal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.*;

import br.com.colbert.base.ui.*;

/**
 * 
 * @author Thiago Miranda
 * @since 10 de jan de 2017
 */
@ApplicationScoped
public class JanelaPrincipalProducer {

    @Inject
    private transient MainWindow mainWindow;

    @Produces
    @Singleton
    @JanelaPrincipal
    public WindowView windowView() {
        return mainWindow;
    }
}
