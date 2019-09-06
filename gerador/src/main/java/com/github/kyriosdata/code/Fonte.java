/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.code;

/**
 * Identifica uma fonte de dados.
 */
public enum Fonte {

    NOME ("nomes.txt"),

    SOBRENOME ("sobrenomes.txt"),
    
    TEXTO("lorem-ipsum.txt"),

    MUNICIPIO("municipios.txt");

    /**
     * Nome do arquivo contendo dados para a fonte em questão.
     */
    private String fonte;

    Fonte(final String arquivo) {
        fonte = arquivo;
    }
}
