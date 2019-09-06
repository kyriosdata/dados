package com.github.kyriosdata.dados;

import com.github.kyriosdata.code.Gerador;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

class GeradorTest {

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

