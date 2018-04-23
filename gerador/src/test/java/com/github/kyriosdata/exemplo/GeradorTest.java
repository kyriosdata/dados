package com.github.kyriosdata.exemplo;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class GeradorTest {

    @Test
    public void geraInteiroUnicoValorDaFaixa() {
        int valorGerado = Gerador.getInteiro(12, 12);
        assertEquals(12, valorGerado);
    }

    @Test
    public void dataSempreDiaUmErro() {
        assertEquals(1, Gerador.getData().getDayOfMonth());
    }
}

