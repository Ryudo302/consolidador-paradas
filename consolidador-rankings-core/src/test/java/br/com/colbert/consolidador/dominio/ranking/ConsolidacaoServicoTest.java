package br.com.colbert.consolidador.dominio.ranking;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.jboss.weld.log.LoggerProducer;
import org.jglue.cdiunit.*;
import org.junit.*;
import org.junit.runner.RunWith;

/**
 * Testes unitários do {@link ConsolidacaoServico}.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ LoggerProducer.class })
public class ConsolidacaoServicoTest {

    @Inject
    private ConsolidacaoServico consolidacaoServico;

    @BeforeClass
    public static void setUpClass() {
        RankingBuilder.setValidacaoHabilitada(false);
    }

    @Test
    public void deveriaConsolidarRankings() {
        ItemRanking itemA1 = ItemRanking.novo(1).comDescricao("A1").build();
        ItemRanking itemA2 = ItemRanking.novo(2).comDescricao("A2").build();
        ItemRanking itemB1 = ItemRanking.novo(1).comDescricao("B1").build();
        ItemRanking itemB2 = ItemRanking.novo(2).comDescricao("B2").build();

        Ranking ranking1 = Ranking.novo("Teste A").comItens(itemA1, itemA2).build();
        Ranking ranking2 = Ranking.novo("Teste B").comItens(itemB1, itemB2).build();

        Ranking rankingConsolidado = consolidacaoServico.consolidar(ranking1, ranking2);

        assertThat(rankingConsolidado).isNotNull();
    }
}
