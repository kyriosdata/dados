/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.dados;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FonteTest {

    @Test
    void todasAsFontes() {
        assertEquals("nomes.txt", Fonte.NOME.getFileName());
        assertEquals("sobrenomes.txt", Fonte.SOBRENOME.getFileName());
        assertEquals("lorem-ipsum.txt", Fonte.TEXTO.getFileName());
        assertEquals("municipios.txt", Fonte.MUNICIPIO.getFileName());
    }
}
