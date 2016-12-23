package br.com.colbert.consolidador.dominio.ranking;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Um item em um ranking.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
public class ItemRanking implements Comparable<ItemRanking>, Serializable {

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

    public int getNumero() {
        return numero;
    }

    protected void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int compareTo(ItemRanking other) {
        return new CompareToBuilder().append(numero, other.numero).toComparison();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ItemRanking && equals((ItemRanking) obj);
    }

    private boolean equals(ItemRanking other) {
        return new EqualsBuilder().append(numero, other.numero).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(numero).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("numero", numero).append("descricao", descricao).toString();
    }
}
