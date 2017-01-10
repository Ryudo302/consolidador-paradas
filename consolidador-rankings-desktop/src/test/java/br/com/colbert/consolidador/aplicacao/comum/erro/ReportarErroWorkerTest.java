package br.com.colbert.consolidador.aplicacao.comum.erro;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.swing.SwingWorker;

import org.jboss.weld.log.LoggerProducer;
import org.jglue.cdiunit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import br.com.colbert.consolidador.dominio.erro.*;
import br.com.colbert.consolidador.infraestrutura.info.ApplicationProperties;
import br.com.colbert.consolidador.infraestrutura.mail.MailErroReporter;
import br.com.colbert.consolidador.infraestrutura.swing.worker.*;

/**
 * Testes da {@link ReportarErroWorker}.
 * 
 * @author Thiago Colbert
 * @since 02/02/2015
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ MailErroReporter.class, LoggerProducer.class, ApplicationProperties.class })
public class ReportarErroWorkerTest {

    @Inject
    private ReportarErroWorker worker;

    @Produces
    @ProducesAlternative
    @Mock
    private ErroReporter erroReporter;

    @Produces
    @ProducesAlternative
    @Mock
    private WorkerWaitListener workerWaitListener;

    @Test
    public void testReportarErro() {
        Throwable exception = new IllegalArgumentException("Teste 1", new IllegalArgumentException("Teste 2"));
        Erro erro = new Erro(exception);

        worker.addWorkerDoneListener(new WorkerDoneAdapter() {

            private static final long serialVersionUID = -173190249806441811L;

            @Override
            public void doneWithError(SwingWorker<?, ?> worker, Throwable error) {
                error.printStackTrace();
                fail(error.getLocalizedMessage());
            }
        });

        worker.setErro(erro);
        worker.execute();

        worker.getResult();

        verify(erroReporter).reportar(erro);
    }
}
