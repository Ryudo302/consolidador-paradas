package br.com.colbert.consolidador.dominio.ranking;

import java.io.Serializable;
import java.util.Collection;

import br.com.colbert.consolidador.infraestrutura.builder.AbstractBuilder;

/**
 * Classe que facilita a criação de instâncias de {@link Ranking}.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
public class RankingBuilder extends AbstractBuilder<Ranking> implements Serializable {

    private static final long serialVersionUID = 3786919273333636858L;

    /**
     * Inicia a criação de um novo ranking.
     * 
     * @param nome
     *            o nome do novo ranking
     */
    public RankingBuilder(String nome) {
        super(new Ranking(nome));
    }

    public RankingBuilder comItens(ItemRanking... itens) {
        instanciaIncompleta.addItens(itens);
        return this;
    }

    public RankingBuilder comItens(Collection<ItemRanking> itens) {
        instanciaIncompleta.addItens(itens);
        return this;
    }
}
