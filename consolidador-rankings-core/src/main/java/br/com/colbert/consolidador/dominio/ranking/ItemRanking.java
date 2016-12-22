package br.com.colbert.consolidador.dominio.ranking;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Um item em um ranking.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
public class ItemRanking implements Serializable {

    private static final long serialVersionUID = 2861269186814727483L;

    @Min(1)
    private final int numero;
    @NotEmpty
    private String descricao;

    protected ItemRanking(int numero) {
        this.numero = numero;
    }

    /**
     * Inicia a criação de um novo item de ranking.
     * 
     * @param numero
     *            o número do item
     * @return o item sendo criado
     */
    public static ItemRankingBuilder novo(int numero) {
        return new ItemRankingBuilder(numero);
    }

    protected void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
