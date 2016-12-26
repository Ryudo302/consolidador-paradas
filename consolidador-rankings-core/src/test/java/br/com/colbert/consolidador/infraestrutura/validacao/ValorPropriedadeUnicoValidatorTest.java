package br.com.colbert.consolidador.infraestrutura.validacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.*;

import javax.validation.ConstraintValidatorContext;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.junit.*;

/**
 * Testes unitários da {@link ValorPropriedadeUnicoValidator}.
 * 
 * @author Thiago Miranda
 * @since 23 de dez de 2016
 */
public class ValorPropriedadeUnicoValidatorTest {

	public class ObjetoTeste implements Comparable<ObjetoTeste> {

		private int numero;

		public ObjetoTeste(int numero) {
			this.numero = numero;
		}

		public int getNumero() {
			return numero;
		}

		@Override
		public int compareTo(ObjetoTeste other) {
			return Integer.valueOf(numero).compareTo(other.numero);
		}
	}

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private ValorPropriedadeUnico annotation;
	@Mock
	private ConstraintValidatorContext context;

	private ValorPropriedadeUnicoValidator validator;

	@Before
	public void setUp() {
		String[] propriedades = { "numero" };
		when(annotation.value()).thenReturn(propriedades);

		validator = new ValorPropriedadeUnicoValidator();
		validator.initialize(annotation);
	}

	@Test
	public void deveriaRetornarTrueParaListaValida() {
		SortedSet<ObjetoTeste> objetos = new TreeSet<>(Arrays.asList(new ObjetoTeste(1), new ObjetoTeste(2)));
		assertThat(validator.isValid(objetos, context)).isTrue();
	}
}
