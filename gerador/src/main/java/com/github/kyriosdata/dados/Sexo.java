/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.dados;

/**
 * Identificação do sexo de um indivíduo para fins administrativos,
 * conforme ABNT NBR 15985:2011.
 */
public enum Sexo {

    MASCULINO(1, "Masculino", 'M'),
    FEMININO(2, "Feminino", 'F'),
    INTERSEXO(3, "Intersexo ou indeterminado", 'I'),
    NAO_DECLARADO(9, "Não declarado ou indeterminado", 'U');

    /**
     * Código específico para o sexo.
     */
    private final int codigo;

    /**
     * Descrição do sexo.
     */
    private final String descricao;

    /**
     * Caractere alternativo para representar o sexo.
     */
    private final Character alternativo;

    Sexo(final int code, final String texto, final Character opcao) {
        codigo = code;
        descricao = texto;
        alternativo = opcao;
    }
}
