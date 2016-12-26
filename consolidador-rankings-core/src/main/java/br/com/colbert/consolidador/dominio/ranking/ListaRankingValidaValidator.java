package br.com.colbert.consolidador.dominio.ranking;

import java.util.*;
import java.util.stream.*;

import javax.validation.*;

import br.com.colbert.consolidador.infraestrutura.validacao.ValorPropriedadeUnico;

/**
 * Validador de lista de itens de ranking.
 * 
 * @author Thiago Miranda
 * @since 23 de dez de 2016
 * 
 * @see ValorPropriedadeUnico
 */
public class ListaRankingValidaValidator implements ConstraintValidator<ListaRankingValida, SortedSet<ItemRanking>> {

	@Override
	public void initialize(ListaRankingValida constraintAnnotation) {
	}

	@Override
	public boolean isValid(SortedSet<ItemRanking> value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		} else {
			List<Integer> numeros = value.stream().map(ItemRanking::getNumero).sorted().collect(Collectors.toList());
			return IntStream.range(0, numeros.size() - 1).allMatch(i -> numeros.get(i) == numeros.get(i + 1) - 1);

		}
	}
}
