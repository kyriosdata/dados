/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.dados;

/**
 * Identifica uma fonte de dados.
 */
public enum Fonte {

    NOMES("nomes.txt"),

    SOBRENOMES("sobrenomes.txt"),

    TEXTO("lorem-ipsum.txt"),

    CARTORIOS("cartorios.csv"),

    LOGRADOUROS("logradouros.csv"),

    MUNICIPIOS("municipios.txt");

    /**
     * Nome do arquivo contendo dados para a fonte em questão.
     */
    private String fonte;

    /**
     * Cria tipo de fonte.
     * @param arquivo Nome do arquivo contendo dados pertinente à fonte.
     */
    Fonte(final String arquivo) {
        fonte = arquivo;
    }

    /**
     * Retorna o nome do arquivo contendo dados pertinentes à fonte.
     *
     * @return O nome do arquivo contendo dados da fonte.
     */
    public String getFileName() {
        return fonte;
    }
}
