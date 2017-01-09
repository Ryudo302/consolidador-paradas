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
        assertThat(ranking.getNome()).asString().contains("Teste");
        assertThat(ranking.getQuantidadeItens()).isEqualTo(0);
    }

    @Test
    public void deveriaCriarRankingComNomeAndItens() {
        Ranking ranking = new RankingBuilder("Teste").comItens(new ItemRanking("1"), new ItemRanking("2")).build();
        assertThat(ranking.getNome()).asString().contains("Teste");
        assertThat(ranking.getQuantidadeItens()).isEqualTo(2);
    }

    @Test
    public void deveriaCriarRankingComPontuacoesAtualizadas() {
        ItemRanking item1 = new ItemRanking("1");
        ItemRanking item2 = new ItemRanking("2");

        new RankingBuilder("Teste").comItens(item1, item2).atualizarPontuacaoItens().build();

        assertThat(item1.getPontuacao()).isPositive();
        assertThat(item2.getPontuacao()).isPositive();
    }
}
