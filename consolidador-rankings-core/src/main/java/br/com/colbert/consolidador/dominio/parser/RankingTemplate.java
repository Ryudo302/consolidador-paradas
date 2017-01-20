package br.com.colbert.consolidador.dominio.parser;

import java.io.*;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

/**
 * Classe que representa um modelo de rankings.
 * 
 * @author Thiago Miranda
 * @since 17 de jan de 2017
 */
public class RankingTemplate implements Serializable {

    private static final long serialVersionUID = 4328500308798806445L;

    private String conteudo;

    public RankingTemplate(String conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * 
     * @param stream
     * @param charset
     * @return
     * @throws IOException
     */
    public static RankingTemplate parse(InputStream stream, Charset charset) throws IOException {
        return new RankingTemplate(IOUtils.toString(stream, charset));
    }

    public BufferedReader getBufferedReader() {
        return new BufferedReader(new StringReader(conteudo));
    }

    public String getConteudo() {
        return conteudo;
    }
}
