package br.com.colbert.consolidador.infraestrutura.builder;

import org.apache.commons.lang3.builder.Builder;

import br.com.colbert.consolidador.infraestrutura.validacao.*;

/**
 * Classe-base para implementações de {@link Builder}.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 *
 * @param <T>
 *            o tipo de objeto gerado pelo <em>builder</em>
 */
public abstract class AbstractBuilder<T> implements Builder<T> {

    private static boolean validacaoHabilitada = true;

    protected final T instanciaIncompleta;

    public AbstractBuilder(T instanciaIncompleta) {
        this.instanciaIncompleta = instanciaIncompleta;
    }

    public static boolean isValidacaoHabilitada() {
        return validacaoHabilitada;
    }

    public static void setValidacaoHabilitada(boolean validacaoHabilitada) {
        AbstractBuilder.validacaoHabilitada = validacaoHabilitada;
    }

    /**
     * @throws ValidacaoException
     *             caso ocorra algum erro de validação
     */
    @Override
    public T build() {
        if (isValidacaoHabilitada()) {
            ValidacaoServico.getInstance().validar(instanciaIncompleta);
        }
        return instanciaIncompleta;
    }
}
