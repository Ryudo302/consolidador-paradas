package br.com.colbert.consolidador.dominio.ranking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.*;

/**
 * Testes unitários da {@link RankingBuilder}.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
public class RankingBuilderTest {

	@BeforeClass
	public static void setUpClass() {
		RankingBuilder.setValidacaoHabilitada(false);
	}

	@Test
	public void deveriaCriarRankingApenasComNome() {
		Ranking ranking = new RankingBuilder("Teste").build();
		assertThat(ranking).extracting("nome").containsExactly("Teste");
		assertThat(ranking).extracting("quantidadeItens").containsOnly(0);
	}

	@Test
	public void deveriaCriarRankingComNomeAndItens() {
		Ranking ranking = new RankingBuilder("Teste").comItens(new ItemRanking("1"), new ItemRanking("2")).build();
		assertThat(ranking).extracting("nome").containsExactly("Teste");
		assertThat(ranking).extracting("quantidadeItens").containsOnly(2);
	}
}
