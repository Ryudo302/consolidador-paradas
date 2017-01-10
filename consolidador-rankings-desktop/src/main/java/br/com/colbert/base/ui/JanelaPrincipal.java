package br.com.colbert.base.ui;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import javax.inject.Qualifier;

/**
 * Qualificador indicando a janela principal da aplicação.
 * 
 * @author Thiago Colbert
 * @since 10/01/2017
 */
@Qualifier
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
@Documented
public @interface JanelaPrincipal {
}
