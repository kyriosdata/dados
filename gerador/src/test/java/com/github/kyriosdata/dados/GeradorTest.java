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

import static org.junit.jupiter.api.Assertions.*;

class GeradorTest {

    @Test
    void carregarDadosInexistentesGeraExcecao() throws GeradorException {
        Gerador gerador = new Gerador();
        assertThrows(GeradorException.class, () ->
                gerador.carregarDados(""));
    }

    @Test
    void inteiroFaixaInvalida() throws GeradorException {
        Gerador gerador = new Gerador();
        assertThrows(IllegalArgumentException.class, () ->
                gerador.inteiro(0, -1));
    }

    @Test
    void inteiroUnicoValor() throws GeradorException {
        Gerador gerador = new Gerador();
        assertEquals(0, gerador.inteiro(0, 0));
    }

    @Test
    void recuperaNomesSobrenomes() throws GeradorException {
        List<String> nomes = new Gerador().getNomes();
        assertTrue(nomes.size() > 1000);

        List<String> sobrenomes = new Gerador().getSobrenomes();
        assertTrue(sobrenomes.size() > 400);
    }

    @Test
    void verificaData() throws GeradorException {
        String data = new Gerador().getDataAsString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Gera exceção se formato for inválido.
        // TODO não verifica bissexto!?
        LocalDate.parse(data, dtf);
    }

    @Test
    void verificaNomeCompleto() throws GeradorException {
        String nome = new Gerador().nomeCompleto();
        assertTrue(nome.contains(" "));
    }
}

