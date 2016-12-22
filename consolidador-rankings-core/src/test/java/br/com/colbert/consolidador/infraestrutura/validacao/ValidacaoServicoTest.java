package br.com.colbert.consolidador.infraestrutura.validacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.inject.Inject;
import javax.validation.constraints.Min;

import org.hibernate.validator.internal.cdi.ValidatorBean;
import org.jglue.cdiunit.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testes unitários da {@link ValidacaoServico}.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
@RunWith(CdiRunner.class)
@AdditionalPackages({ ValidatorBean.class })
public class ValidacaoServicoTest {

    class ObjetoTeste {

        @Min(value = 1, message = "Número deve ser maior ou igual a {value}")
        private final int numero;

        public ObjetoTeste(int numero) {
            this.numero = numero;
        }
    }

    @Inject
    private ValidacaoServico validacaoServico;

    @Test
    public void deveriaValidarObjeto() {
        assertThatThrownBy(() -> validacaoServico.validar(new ObjetoTeste(-1))).isInstanceOf(ValidacaoException.class).hasMessageContaining("Número")
                .hasMessageContaining("1");
    }
    
    @Test
    public void deveriaRecuperarInstanciaCdi() {
        assertThat(ValidacaoServico.getInstance()).isEqualTo(validacaoServico);
    }
}
