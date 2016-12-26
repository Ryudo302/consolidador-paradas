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

	@NotEmpty
	private final String descricao;
	@Min(1)
	private int numero;
	@Min(0)
	private int pontuacao;

	protected ItemRanking(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Inicia a criação de um novo item de ranking.
	 * 
	 * @param descricao
	 *            descrição do item
	 * @return o item sendo criado
	 */
	public static ItemRankingBuilder novo(String descricao) {
		return new ItemRankingBuilder(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public int getNumero() {
		return numero;
	}

	protected void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	protected void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	protected void somarPontuacao(int valor) {
		this.pontuacao += valor;
	}

	@Override
	public int compareTo(ItemRanking other) {
		return new CompareToBuilder().append(descricao, other.descricao).toComparison();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof ItemRanking && equals((ItemRanking) obj);
	}

	private boolean equals(ItemRanking other) {
		return new EqualsBuilder().append(descricao, other.descricao).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(descricao).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("numero", numero).append("descricao", descricao)
				.append("pontuacao", pontuacao).toString();
	}
}
