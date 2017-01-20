package br.com.colbert.consolidador.dominio.parser;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;

import br.com.colbert.consolidador.dominio.ranking.Ranking;

/**
 * Responsável por processar rankings a partir de fontes de dados diversas.
 * 
 * @author Thiago Miranda
 * @since 16 de jan de 2017
 * 
 * @param <T>
 *            o tipo de fonte de dados
 */
@ApplicationScoped
public class RankingParser implements Serializable {

    private static final long serialVersionUID = 8748250779317586421L;

    @Inject
    private transient Logger logger;

    /**
     * Processa uma fonte de dados, gerando um ranking a partir dela.
     * 
     * @param fonte
     *            a fonte de dados a ser processada
     * @param template
     *            modelo a ser utilizado para processar o ranking
     * @param charset
     *            <em>encoding</em> da fonte de dados
     * @return o ranking gerado
     * @throws NullPointerException
     *             caso seja informado {@code null}
     * @throws ParserException
     *             caso ocorra algum erro durante o processamento
     */
    public Ranking parse(@NotNull InputStream fonte, @NotNull RankingTemplate template, @NotNull Charset charset) {
        try (BufferedReader readerFonte = new BufferedReader(new InputStreamReader(fonte, charset)); BufferedReader readerTemplate = template.getBufferedReader()) {
            List<String> linhasFonte = readerFonte.lines().collect(Collectors.toList());
            List<String> linhasTemplate = readerTemplate.lines().collect(Collectors.toList());

            for (int i = 0; i < linhasTemplate.size(); i++) {
                String linhaTemplate = linhasTemplate.get(i);
                String linhaFonte = linhasFonte.get(i);

                Pattern templatePattern = Pattern.compile(linhaTemplate);
                Matcher matcherFonte = templatePattern.matcher(linhaFonte);

                if (matcherFonte.find()) {
                    Set<String> namedGroups = PatternFixture.getNamedGroups(templatePattern);
                } else {
                    throw new DoesNotMatchException(templatePattern, linhaFonte);
                }
            }
        } catch (IOException exception) {
            logger.warn("Erro ao fechar leitor de dados", exception);
        }

        // TODO
        return null;
    }
}
