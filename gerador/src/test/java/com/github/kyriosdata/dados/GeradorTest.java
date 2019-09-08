/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.dados;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeradorTest {

    @Test
    void redundancias() {
        String cartorio = new Gerador().cartorio();
        assertNotNull(cartorio);
        assertTrue(cartorio.length() > 10);
        assertFalse(cartorio.isEmpty());
    }

    @Test
    void recuperaNomesSobrenomes() {
        List<String> nomes = new Gerador().getNomes();
        assertTrue(nomes.size() > 1000);

        List<String> sobrenomes = new Gerador().getSobrenomes();
        assertTrue(sobrenomes.size() > 400);
    }

    @Test
    void verificaData() {
        String data = new Gerador().getDataAsString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Gera exceção se formato for inválido.
        // TODO não verifica bissexto!?
        LocalDate.parse(data, dtf);
    }

    @Test
    void verificaNomeCompleto() {
        String nome = new Gerador().nomeCompleto();
        assertTrue(nome.contains(" "));
    }
}

