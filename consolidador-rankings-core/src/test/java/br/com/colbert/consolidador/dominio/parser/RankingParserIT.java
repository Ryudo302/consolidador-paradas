package br.com.colbert.consolidador.dominio.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.jboss.weld.log.LoggerProducer;
import org.jglue.cdiunit.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.colbert.consolidador.dominio.ranking.Ranking;

/**
 * Testes de integração da {@link RankingParser}.
 * 
 * @author Thiago Miranda
 * @since 20 de jan de 2017
 */
@RunWith(CdiRunner.class)
@AdditionalPackages({ LoggerProducer.class })
public class RankingParserIT {

    @Inject
    private RankingParser rankingParser;

    @Test
    public void deveriaFazerParseDeArquivo() throws IOException {
        Charset charset = Charset.defaultCharset();

        InputStream fonte = carregarArquivo(Paths.get("rankings", "ranking-simples.txt").toString());
        RankingTemplate template = RankingTemplate.parse(carregarArquivo(Paths.get("rankings", "template-simples.txt").toString()), charset);

        Ranking ranking = rankingParser.parse(fonte, template, charset);

        assertThat(ranking).isNotNull();
    }

    private InputStream carregarArquivo(String caminhoArquivo) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(caminhoArquivo);
    }
}
