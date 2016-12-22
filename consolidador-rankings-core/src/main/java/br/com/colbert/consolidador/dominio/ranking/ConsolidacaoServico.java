package br.com.colbert.consolidador.dominio.ranking;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;

/**
 * Serviço para consolidação de rankings.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
@ApplicationScoped
public class ConsolidacaoServico implements Serializable {

    private static final long serialVersionUID = -2704892956483412749L;

    @Inject
    private transient Logger logger;

    /**
     * Realiza a consolidação dos rankings informados, gerando um novo com os dados consolidados.
     * 
     * @param rankings
     *            a serem consolidados
     * @return o ranking consolidado gerado
     */
    public Ranking consolidar(Ranking... rankings) {
        // TODO
        return null;
    }
}
