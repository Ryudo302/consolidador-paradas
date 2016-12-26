package br.com.colbert.consolidador.dominio.ranking;

import java.io.Serializable;
import java.util.*;

import org.apache.commons.lang3.builder.CompareToBuilder;

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

	public RankingBuilder atualizarPontuacaoItens() {
		instanciaIncompleta.getItens().forEach(item -> {
			item.setPontuacao(instanciaIncompleta.getQuantidadeItens() - item.getNumero() + 1);
		});
		return this;
	}

	public RankingBuilder ordenarItensPelaPontuacao() {
		SortedSet<ItemRanking> itensOrdenados = new TreeSet<>((itemA, itemB) -> new CompareToBuilder()
				.append(itemB.getPontuacao(), itemA.getPontuacao()).append(itemA.getDescricao(), itemB.getDescricao()).toComparison());
		itensOrdenados.addAll(instanciaIncompleta.getItens());
		// TODO verificar
		instanciaIncompleta.setItens(atualizarNumeracaoItens(itensOrdenados));
		return this;
	}

	private SortedSet<ItemRanking> atualizarNumeracaoItens(SortedSet<ItemRanking> itens) {
		int numero = 1;
		for (ItemRanking itemRanking : itens) {
			itemRanking.setNumero(numero++);
		}
		return itens;
	}

	@Override
	public Ranking build() {
		return super.build();
	}
}
