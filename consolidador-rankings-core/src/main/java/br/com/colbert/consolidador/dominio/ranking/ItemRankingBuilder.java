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

    /**
     * Inicia a criação de um novo item do ranking informado.
     * 
     * @param numero
     *            o número do item
     */
    public ItemRankingBuilder(int numero) {
        super(new ItemRanking(numero));
    }

    public ItemRankingBuilder comDescricao(String descricao) {
        this.instanciaIncompleta.setDescricao(descricao);
        return this;
    }
}
