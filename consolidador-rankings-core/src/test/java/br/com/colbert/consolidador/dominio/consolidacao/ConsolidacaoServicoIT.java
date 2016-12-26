package br.com.colbert.consolidador.dominio.consolidacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.cdi.ValidatorBean;
import org.hibernate.validator.internal.cdi.interceptor.ValidationInterceptor;
import org.jboss.weld.log.LoggerProducer;
import org.jglue.cdiunit.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.colbert.consolidador.dominio.ranking.*;
import br.com.colbert.consolidador.infraestrutura.validacao.ValidacaoServico;

/**
 * Testes de integração da {@link ConsolidacaoServico}.
 * 
 * @author ThiagoColbert
 * @since 26 de dez de 2016
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ LoggerProducer.class, ValidationInterceptor.class })
@AdditionalPackages({ ValidatorBean.class, ValidacaoServico.class })
public class ConsolidacaoServicoIT {

	@Inject
	private ConsolidacaoServico consolidacaoServico;

	@Test
	public void deveriaLancarExcecaoCasoNomeSejaNull() {
		Ranking ranking = Ranking.novo("Teste").comItens(ItemRanking.novo("A1").deNumero(1).build()).build();
		assertThatThrownBy(() -> consolidacaoServico.consolidar(null, ranking)).isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	public void deveriaLancarExcecaoCasoNomeEstejaVazio() {
		Ranking ranking = Ranking.novo("Teste").comItens(ItemRanking.novo("A1").deNumero(1).build()).build();
		assertThatThrownBy(() -> consolidacaoServico.consolidar(StringUtils.EMPTY, ranking)).isInstanceOf(ConstraintViolationException.class);
	}

	@Test
	public void deveriaConsolidarRankings() {
		ItemRanking itemA1 = ItemRanking.novo("Item A").deNumero(1).build();
		ItemRanking itemA2 = ItemRanking.novo("Item B").deNumero(2).build();
		ItemRanking itemA3 = ItemRanking.novo("Item C").deNumero(3).build();
		ItemRanking itemA4 = ItemRanking.novo("Item D").deNumero(4).build();

		ItemRanking itemB1 = ItemRanking.novo("Item B").deNumero(1).build();
		ItemRanking itemB2 = ItemRanking.novo("Item D").deNumero(2).build();
		ItemRanking itemB3 = ItemRanking.novo("Item E").deNumero(3).build();
		ItemRanking itemB4 = ItemRanking.novo("Item A").deNumero(4).build();

		Ranking ranking1 = Ranking.novo("Teste A").comItens(itemA1, itemA2, itemA3, itemA4).atualizarPontuacaoItens().build();
		Ranking ranking2 = Ranking.novo("Teste B").comItens(itemB1, itemB2, itemB3, itemB4).atualizarPontuacaoItens().build();

		Ranking rankingConsolidado = consolidacaoServico.consolidar("Teste Consolidado", ranking1, ranking2);

		assertThat(rankingConsolidado).isNotNull();
		assertThat(rankingConsolidado.getItens()).extracting("descricao").containsExactly("Item B", "Item A", "Item D", "Item C", "Item E");
	}
}
