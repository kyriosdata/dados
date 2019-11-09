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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeradorTest {

    @Test
    void umValorLogico() {
        boolean logico = Gerador.logico();
        assertTrue(logico == true || logico == false);
    }

    @Test
    void umInteiroMenorIgual1000() {
        int x = Gerador.inteiro();
        assertTrue(x >= 0 && x <= 1e3);
    }

    @Test
    void geraEmFaixaUmUnicoValor() {
        assertEquals(0, Gerador.inteiro(0, 0));
        assertEquals(987, Gerador.inteiro(987, 987));
    }

    @Test
    void carregarDadosInexistentesGeraExcecao() {
        Gerador gerador = Gerador.getInstance();
        assertThrows(GeradorException.class, () ->
                gerador.carregarDados(""));
    }

    @Test
    void inteiroFaixaInvalida() {
        Gerador gerador = Gerador.getInstance();
        assertThrows(IllegalArgumentException.class, () ->
                gerador.inteiro(0, -1));
    }

    @Test
    void inteiroUnicoValor() {
        Gerador gerador = Gerador.getInstance();
        assertEquals(0, gerador.inteiro(0, 0));
    }

    @Test
    void recuperaNomesSobrenomes() {
        List<String> nomes = Gerador.getInstance().getNomes();
        assertTrue(nomes.size() > 1000);

        List<String> sobrenomes = Gerador.getInstance().getSobrenomes();
        assertTrue(sobrenomes.size() > 400);
    }

    @Test
    void verificaData() {
        String data = Gerador.getInstance().getDataAsString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Gera exceção se formato for inválido.
        // TODO não verifica bissexto!?
        LocalDate.parse(data, dtf);
    }

    @Test
    void verificaNomeCompleto() {
        String nome = Gerador.getInstance().nomeCompleto();
        assertTrue(nome.contains(" "));
    }

    @Test
    void geraCnpj() {
        System.out.println(Gerador.getInstance().cnpj());
    }
}

