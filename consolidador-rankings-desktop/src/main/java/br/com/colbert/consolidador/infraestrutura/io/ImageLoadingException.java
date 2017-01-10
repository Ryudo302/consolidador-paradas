package br.com.colbert.consolidador.infraestrutura.io;

/**
 * Exceção lançada quando ocorre algum erro ao carregar imagens.
 * 
 * @author Thiago Miranda
 * @since 10 de jan de 2017
 */
public class ImageLoadingException extends RuntimeException {

    private static final long serialVersionUID = 8145421800985082549L;

    public ImageLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageLoadingException(String message) {
        super(message);
    }

    public ImageLoadingException(Throwable cause) {
        super(cause);
    }
}
