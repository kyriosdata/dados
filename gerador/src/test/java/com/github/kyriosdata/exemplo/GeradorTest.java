package com.github.kyriosdata.exemplo;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class GeradorTest {

    @Test
    public void recuperaNomesSobrenomes() {
        List<String> nomes = new Gerador().getNomes();
        assertTrue(nomes.size() > 1000);

        List<String> sobrenomes = new Gerador().getSobrenomes();
        assertTrue(sobrenomes.size() > 400);
    }

    @Test
    public void verificaData() {
        String data = new Gerador().getDataAsString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Gera exceção se formato for inválido.
        // TODO não verifica bissexto!?
        LocalDate.parse(data, dtf);
    }

    @Test
    public void verificaNomeCompleto() {
        String nome = new Gerador().nomeCompleto();
        assertTrue(nome.contains(" "));
    }
}
