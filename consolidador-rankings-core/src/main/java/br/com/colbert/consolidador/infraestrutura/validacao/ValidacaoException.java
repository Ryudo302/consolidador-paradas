package br.com.colbert.consolidador.infraestrutura.validacao;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import br.com.colbert.consolidador.infraestrutura.string.MultilineStringBuilder;

/**
 * Exceção lançada quando ocorre algum erro de validação.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
public class ValidacaoException extends RuntimeException {

    private static final long serialVersionUID = 1202930886165026092L;

    private final Object bean;
    private transient final List<String> mensagens;

    /**
     * Cria uma nova exceção.
     * 
     * @param bean
     *            o objeto que foi validado
     * @param violacoes
     *            os erros de validação identificados
     * @throws NullPointerException
     *             caso qualquer parâmetro seja {@code null}
     */
    public ValidacaoException(Object bean, Set<ConstraintViolation<Object>> violacoes) {
        this.bean = Objects.requireNonNull(bean, "bean");
        mensagens = Objects.requireNonNull(violacoes, "violacoes").stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

    @Override
    public String getMessage() {
        MultilineStringBuilder mensagemUnicaBuilder = new MultilineStringBuilder(mensagens.size() * 10);
        mensagemUnicaBuilder.appendLine("Violações:");
        mensagens.forEach(mensagemUnicaBuilder::appendLine);
        return mensagemUnicaBuilder.toString();
    }

    public Object getBean() {
        return bean;
    }

    public List<String> getMensagens() {
        return Collections.unmodifiableList(mensagens);
    }
}
