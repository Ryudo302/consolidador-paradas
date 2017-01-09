package br.com.colbert.consolidador.infraestrutura.validacao;

import java.io.Serializable;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.validation.*;

import org.slf4j.Logger;

/**
 * Classe que provê acesso à instância de {@link Validator} configurada.
 * 
 * @author Thiago Miranda
 * @since 22 de dez de 2016
 */
@ApplicationScoped
public class ValidacaoServico implements Serializable {

    private static final long serialVersionUID = -7780598847274916717L;

    @Inject
    private transient Logger logger;
    @Inject
    private transient Validator validator;

    /**
     * Obtém a instância única do serviço.
     * 
     * @return a instância
     */
    public static ValidacaoServico getInstance() {
        return CDI.current().select(ValidacaoServico.class).get();
    }

    /**
     * Realiza uma validação completa nas propriedades do objeto informado.
     * 
     * @param bean
     *            objeto a ser validado
     * @throws ValidacaoException
     *             caso ocorra algum erro de validação
     */
    public void validar(Object bean) {
        logger.debug("Validando: {}", bean);
        Set<ConstraintViolation<Object>> violacoes = validator.validate(bean);
        if (!violacoes.isEmpty()) {
            logger.debug("Violações: {}", violacoes);
            throw new ValidacaoException(bean, violacoes);
        }
    }
}
