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
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GeradorTest {

    @Test
    void redundancias() {
        List<String> cartorios = new Gerador().cartorios();
        Set<String> nomes =
                cartorios.stream().map(c -> c.split(";")[0]).collect(Collectors.toSet());
        Set<String> fantasias =
                cartorios.stream().map(c -> c.split(";"))
                        .filter(e -> e.length > 1)
                        .map(a -> a[1])
                        .collect(Collectors.toSet());
        System.out.println(cartorios.size());
        System.out.println(nomes.size());
        System.out.println(fantasias.size());
        nomes.forEach(System.out::println);
        fantasias.forEach(System.out::println);
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

