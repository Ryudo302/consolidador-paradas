package br.com.colbert.consolidador.infraestrutura.string;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * Extensão de {@link StringBuilder} que permite a inclusão de novas linhas de texto.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
public class MultilineStringBuilder implements Appendable, CharSequence, Serializable {

    private static final long serialVersionUID = -3208388509326798713L;

    private transient final StringBuilder internalBuilder;

    public MultilineStringBuilder() {
        internalBuilder = new StringBuilder();
    }

    public MultilineStringBuilder(int capacity) {
        internalBuilder = new StringBuilder(capacity);
    }

    /**
     * 
     * @param line
     *            linha a ser adicionada
     * @return {@code this}, para chamadas encadeadas de método
     * @see #append(CharSequence)
     */
    public Appendable appendLine(CharSequence line) {
        append(line);
        append(StringUtils.LF);
        return this;
    }

    @Override
    public int length() {
        return internalBuilder.length();
    }

    @Override
    public char charAt(int index) {
        return internalBuilder.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        internalBuilder.subSequence(start, end);
        return this;
    }

    @Override
    public Appendable append(CharSequence csq) {
        internalBuilder.append(csq);
        return this;
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) {
        internalBuilder.append(csq, start, end);
        return this;
    }

    @Override
    public Appendable append(char c) {
        internalBuilder.append(c);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MultilineStringBuilder && internalBuilder.equals(((MultilineStringBuilder) obj).internalBuilder);
    }

    @Override
    public int hashCode() {
        return internalBuilder.hashCode();
    }

    @Override
    public String toString() {
        return internalBuilder.toString();
    }
}
