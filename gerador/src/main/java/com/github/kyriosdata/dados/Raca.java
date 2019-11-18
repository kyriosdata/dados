/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.dados;

public enum Raca {

    BRANCA(1, "Branca"),
    PRETA(2, "Preta"),
    PARDA(3, "Parda"),
    AMARELA(4, "Amarela"),
    INDIGENA(5, "Indígena");

    /**
     * Código específico para a raça.
     */
    private final int codigo;

    /**
     * Descrição da raça.
     */
    private final String descricao;

    Raca(final int code, final String texto) {
        codigo = code;
        descricao = texto;
    }
}
