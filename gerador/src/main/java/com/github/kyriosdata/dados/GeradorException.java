/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.dados;

public class GeradorException extends Exception {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Cria uma exceção com a descrição fornecida.
     *
     * @param msg Descrição da exceção.
     */
    public GeradorException(final String msg) {
        super(msg);
    }

    /**
     * Detalha exceção com a mensagem fornecida.
     *
     * @param msg Mensagem que detalha a exceção.
     * @param exp Exceção original detalhada pela mensagem fornecida.
     */
    public GeradorException(final String msg, final Throwable exp) {
        super(msg, exp);
    }

}
