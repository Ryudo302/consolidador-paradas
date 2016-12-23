package br.com.colbert.consolidador.infraestrutura.validacao;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.validation.*;

/**
 * Qualificador que indica que, em uma coleção de objetos, determinada propriedade deve ter um valor único para cada um dos objetos, sem nenhuma repetição.
 * 
 * @author Thiago Miranda
 * @since 23 de dez de 2016
 */
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValorPropriedadeUnicoValidator.class)
@Documented
public @interface ValorPropriedadeUnico {

    String message() default "{br.com.colbert.consolidador.infraestrutura.validacao.ValorPropriedadeUnico.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();
}
