package br.com.colbert.consolidador.dominio.consolidacao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import javax.inject.Inject;

import org.jboss.weld.log.LoggerProducer;
import org.jglue.cdiunit.*;
import org.junit.*;
import org.junit.runner.RunWith;

import br.com.colbert.consolidador.dominio.ranking.*;

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
	public void deveriaConsolidarRankingsOrdenandoItens() {
		ItemRanking itemA1 = ItemRanking.novo(1).comDescricao("A1").build();
		ItemRanking itemA2 = ItemRanking.novo(2).comDescricao("A2").build();
		ItemRanking itemB1 = ItemRanking.novo(1).comDescricao("B1").build();
		ItemRanking itemB2 = ItemRanking.novo(2).comDescricao("B2").build();

		Ranking ranking1 = Ranking.novo("Teste A").comItens(itemA1, itemA2).build();
		Ranking ranking2 = Ranking.novo("Teste B").comItens(itemB1, itemB2).build();

		Ranking rankingConsolidado = consolidacaoServico.consolidar(ranking1, ranking2);

		assertThat(rankingConsolidado).isNotNull();
		assertThat(rankingConsolidado).extracting("itens").containsExactly(new TreeSet<>(Arrays.asList(itemA1, itemB1, itemA2, itemB2)));
	}

	@Test
	public void deveriaConsolidarRankingsComItensSemelhantes() {
		ItemRanking itemA1 = ItemRanking.novo(1).comDescricao("A1").build();
		ItemRanking itemA2 = ItemRanking.novo(2).comDescricao("A2").build();

		Ranking ranking1 = Ranking.novo("Teste A").comItens(itemA1, itemA2).build();
		Ranking ranking2 = Ranking.novo("Teste B").comItens(itemA1, itemA2).build();

		Ranking rankingConsolidado = consolidacaoServico.consolidar(ranking1, ranking2);

		assertThat(rankingConsolidado).isNotNull();
		assertThat(rankingConsolidado).extracting("itens").containsExactly(new TreeSet<>(Arrays.asList(itemA1, itemA2)));
	}

	@Test
	public void deveriaConsolidarRankingsConsiderandoFrequencia() {
		ItemRanking item1 = ItemRanking.novo(1).comDescricao("1").build();
		ItemRanking item2 = ItemRanking.novo(2).comDescricao("2").build();
		ItemRanking item3 = ItemRanking.novo(3).comDescricao("3").build();
		ItemRanking item4 = ItemRanking.novo(3).comDescricao("4").build();

		Ranking ranking1 = Ranking.novo("Teste A").comItens(item1, item2, item3).build();
		Ranking ranking2 = Ranking.novo("Teste B").comItens(item2, item1, item3).build();
		Ranking ranking3 = Ranking.novo("Teste C").comItens(item4, item1, item2).build();

		Ranking rankingConsolidado = consolidacaoServico.consolidar(ranking1, ranking2, ranking3);

		assertThat(rankingConsolidado).isNotNull();
		assertThat(rankingConsolidado).extracting("itens").containsExactly(new TreeSet<>(Arrays.asList(item1, item2, item3, item4)));
	}
}
