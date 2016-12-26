package br.com.colbert.consolidador.dominio.ranking;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.validation.*;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.colbert.consolidador.infraestrutura.validacao.ValorPropriedadeUnico;

/**
 * Qualificador que indica que uma lista de itens de ranking deve estar completa, ou seja, sem nenhum "buraco" entre os itens.
 * 
 * @author Thiago Miranda
 * @since 23 de dez de 2016
 */
@NotEmpty
@ValorPropriedadeUnico("numero")
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ListaRankingValidaValidator.class)
@Documented
public @interface ListaRankingValida {

	String message() default "{br.com.colbert.consolidador.ranking.ListaRankingCompleta.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
