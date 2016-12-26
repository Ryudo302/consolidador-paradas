package br.com.colbert.consolidador.dominio.ranking;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

import javax.validation.ConstraintValidatorContext;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.junit.*;

/**
 * Testes unitários da {@link ListaRankingValidaValidator}.
 * 
 * @author Thiago Miranda
 * @since 23 de dez de 2016
 */
public class ListaRankingValidaValidatorTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private ListaRankingValida annotation;
	@Mock
	private ConstraintValidatorContext context;

	private ListaRankingValidaValidator validator;

	@BeforeClass
	public static void setUpClass() {
		ItemRankingBuilder.setValidacaoHabilitada(false);
	}

	@Before
	public void setUp() {
		validator = new ListaRankingValidaValidator();
		validator.initialize(annotation);
	}

	@Test
	public void deveriaRetornarTrueParaListaValida() {
		ItemRanking item1 = ItemRanking.novo("1").deNumero(1).build();
		ItemRanking item2 = ItemRanking.novo("2").deNumero(2).build();

		SortedSet<ItemRanking> itens = new TreeSet<>(Arrays.asList(item1, item2));

		assertThat(validator.isValid(itens, context)).isTrue();
	}

	@Test
	public void deveriaRetornarFalseParaListaComItensDuplicados() {
		ItemRanking item1 = ItemRanking.novo("Teste A").deNumero(2).build();
		ItemRanking item2 = ItemRanking.novo("Teste B").deNumero(2).build();

		SortedSet<ItemRanking> itens = new TreeSet<>(Arrays.asList(item1, item2));

		assertThat(validator.isValid(itens, context)).isFalse();
	}

	@Test
	public void deveriaRetornarFalseParaListaIncompleta() {
		ItemRanking item1 = ItemRanking.novo("1").deNumero(1).build();
		ItemRanking item2 = ItemRanking.novo("3").deNumero(3).build();

		SortedSet<ItemRanking> itens = new TreeSet<>(Arrays.asList(item1, item2));

		assertThat(validator.isValid(itens, context)).isFalse();
	}
}
