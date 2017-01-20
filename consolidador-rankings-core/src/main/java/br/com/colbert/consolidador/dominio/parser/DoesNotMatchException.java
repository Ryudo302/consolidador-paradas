package br.com.colbert.consolidador.dominio.parser;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Thiago Miranda
 * @since 20 de jan de 2017
 */
public class DoesNotMatchException extends ParserException {

    private static final long serialVersionUID = 6796154854905424013L;

    private Pattern padrao;
    private String texto;

    public DoesNotMatchException(Pattern pattern, String texto) {
        super(StringUtils.LF + "Padrão: " + pattern + StringUtils.LF + "Fonte: " + texto);
        this.padrao = pattern;
        this.texto = texto;
    }

    public Pattern getPadrao() {
        return padrao;
    }

    public String getTexto() {
        return texto;
    }
}
