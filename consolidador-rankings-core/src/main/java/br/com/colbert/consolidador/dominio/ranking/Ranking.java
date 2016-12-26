package br.com.colbert.consolidador.dominio.ranking;

import java.io.Serializable;
import java.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Um ranking corresponde a uma lista de itens, cada um com um número único referente a sua posição.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
public class Ranking implements Serializable {

	private static final long serialVersionUID = -9032713030529380774L;

	@NotEmpty
	private final String nome;
	@ListaRankingValida
	private transient final SortedSet<ItemRanking> itens;

	/**
	 * Cria um novo ranking com um nome.
	 * 
	 * @param nome
	 *            o nome do ranking
	 */
	protected Ranking(String nome) {
		this.nome = nome;
		this.itens = new TreeSet<>();
	}

	/**
	 * Inicia a criação de um novo ranking.
	 * 
	 * @param nome
	 *            o nome do ranking a ser criado
	 * @return o ranking sendo criado
	 */
	public static RankingBuilder novo(String nome) {
		return new RankingBuilder(nome);
	}

	public String getNome() {
		return nome;
	}

	public SortedSet<ItemRanking> getItens() {
		return Collections.unmodifiableSortedSet(itens);
	}

	/**
	 * Obtém o total de itens listados no ranking.
	 * 
	 * @return o total de itens (sempre >= 0)
	 */
	public int getQuantidadeItens() {
		return itens.size();
	}

	protected void addItens(ItemRanking... itens) {
		this.addItens(Arrays.asList(itens));
	}

	protected void addItens(Collection<ItemRanking> itens) {
		if (CollectionUtils.isNotEmpty(itens)) {
			itens.stream().forEach(itemRankingAdicionar -> {

				this.itens.add(itemRankingAdicionar);
			});
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("nome", nome).append("quantidadeItens", getQuantidadeItens())
				.toString();
	}
}
