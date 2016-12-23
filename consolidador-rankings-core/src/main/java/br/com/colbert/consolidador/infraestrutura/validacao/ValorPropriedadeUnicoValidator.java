package br.com.colbert.consolidador.infraestrutura.validacao;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import javax.validation.*;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Validador que verifica se, em uma lista de itens, determinada propriedade possui valor único entre eles.
 * 
 * @author Thiago Miranda
 * @since 23 de dez de 2016
 *
 * @see ValorPropriedadeUnico
 */
public class ValorPropriedadeUnicoValidator implements ConstraintValidator<ValorPropriedadeUnico, List<?>> {

    private class Wrapper<T> {

        private final T objeto;

        public Wrapper(T wrapped, String... propriedades) {
            this.objeto = wrapped;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            return obj instanceof Wrapper && equals((Wrapper<T>) obj);
        }

        private boolean equals(Wrapper<T> other) {
            EqualsBuilder equalsBuilder = new EqualsBuilder();

            Arrays.stream(propriedades).forEach(propriedade -> {
                Object thisPropriedade = getProperty(objeto, propriedade);
                Object otherPropriedade = getProperty(other.objeto, propriedade);
                equalsBuilder.append(thisPropriedade, otherPropriedade);
            });

            return equalsBuilder.isEquals();
        }

        private Object getProperty(T objeto, String propriedade) {
            try {
                return PropertyUtils.getProperty(objeto, propriedade);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
                throw new ValidationException("Erro ao obter propriedade \"" + propriedade + "\" do objeto: " + objeto, exception);
            }
        }
    }

    private transient String[] propriedades;

    @Override
    public void initialize(ValorPropriedadeUnico constraintAnnotation) {
        propriedades = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else {
            List<?> wrappedObjects = value.stream().map(object -> new Wrapper<>(object)).collect(Collectors.toList());
            return wrappedObjects.stream().allMatch(wrapper -> Collections.frequency(wrappedObjects, wrapper) == 1);
        }
    }
}
