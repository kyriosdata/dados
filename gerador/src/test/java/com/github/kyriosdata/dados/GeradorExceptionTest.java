/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.dados;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeradorExceptionTest {

    @Test
    void instanciaSemStackTrace() {
        GeradorException exception = new GeradorException("teste");
        assertEquals("teste", exception.getMessage());
    }

    @Test
    void instanciaComStackTrace() {
        NullPointerException npe = new NullPointerException("x");
        GeradorException exception = new GeradorException("teste", npe);
        assertEquals("teste", exception.getMessage());
    }
}
