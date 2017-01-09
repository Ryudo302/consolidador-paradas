package br.com.colbert.consolidador.dominio.consolidacao;

import java.io.Serializable;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.internal.cdi.interceptor.MethodValidated;
import org.slf4j.Logger;

import br.com.colbert.consolidador.dominio.ranking.*;

/**
 * Serviço para consolidação de rankings.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
@MethodValidated
@ApplicationScoped
public class ConsolidacaoServico implements Serializable {

    private static final long serialVersionUID = -2704892956483412749L;

    @Inject
    private transient Logger logger;

    /**
     * Realiza a consolidação dos rankings informados, gerando um novo com os dados consolidados.
     * 
     * @param nome
     *            o nome do ranking consolidado
     * @param rankings
     *            a serem consolidados
     * @return o ranking consolidado gerado
     */
    public Ranking consolidar(@NotBlank String nome, @Valid Ranking... rankings) {
        RankingBuilder rankingConsolidadoBuilder = Ranking.novo(nome);

        Arrays.stream(rankings).forEach(rankingAtual -> {
            logger.debug("Processando ranking: {}", rankingAtual);
            rankingConsolidadoBuilder.comItens(rankingAtual.getItens());
        });

        return rankingConsolidadoBuilder.build();
    }
}
