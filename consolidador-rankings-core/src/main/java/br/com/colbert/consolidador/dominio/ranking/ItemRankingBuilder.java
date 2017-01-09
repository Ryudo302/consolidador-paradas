package br.com.colbert.consolidador.dominio.ranking;

import java.io.Serializable;

import br.com.colbert.consolidador.infraestrutura.builder.AbstractBuilder;

/**
 * Classe que facilita a criação de instâncias de {@link ItemRanking}.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
public class ItemRankingBuilder extends AbstractBuilder<ItemRanking> implements Serializable {

    private static final long serialVersionUID = 619017108767728842L;

    public ItemRankingBuilder(String descricao) {
        super(new ItemRanking(descricao));
    }
}
