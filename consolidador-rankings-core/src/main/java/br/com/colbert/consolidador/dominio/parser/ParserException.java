package br.com.colbert.consolidador.dominio.parser;

/**
 * Exceção lançada quando ocorre algum erro durante uma operação de <em>parse</em>.
 * 
 * @author Thiago Miranda
 * @since 16 de jan de 2017
 */
public class ParserException extends RuntimeException {

    private static final long serialVersionUID = 6953378540921470776L;

    public ParserException(String message) {
        super(message);
    }
}
