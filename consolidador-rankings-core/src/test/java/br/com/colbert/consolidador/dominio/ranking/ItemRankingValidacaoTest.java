package br.com.colbert.consolidador.dominio.ranking;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.*;

import org.assertj.core.api.ListAssert;
import org.junit.*;

/**
 * Testes unitários de validação da {@link ItemRanking}.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
public class ItemRankingValidacaoTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void deveriaValidarDadosCorretos() {
        ItemRanking item = new ItemRanking("Teste");

        Set<ConstraintViolation<ItemRanking>> violacoes = validator.validate(item);

        assertThat(violacoes).isEmpty();
    }

    @Test
    public void deveriaValidarItemSemDescricao() {
        ItemRanking item = new ItemRanking(null);

        Set<ConstraintViolation<ItemRanking>> violacoes = validator.validate(item);

        assertThat(violacoes).hasSize(1);
        assertThatInvalidValue(violacoes).containsNull();
    }

    private ListAssert<Object> assertThatInvalidValue(Set<ConstraintViolation<ItemRanking>> violacoes) {
        return assertThat(violacoes).extracting("invalidValue");
    }
}
