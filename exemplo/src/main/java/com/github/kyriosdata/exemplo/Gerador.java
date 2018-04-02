/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.exemplo;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implementação do cálculo de dia da semana.
 *
 * <p>Implementação do algoritmo criado por
 * LARSEN, K. S. Computing the Day of the week,
 * Dr. Dobb´s Journal, april, 1995.
 *
 */
public final class Gerador {

    /**
     * Gera um inteiro dentro da faixa indicada.
     * @param min O menor valor que pode ser sorteado.
     * @param max O maior valor que pode ser sorteado.
     *
     * @return Um inteiro entre a faixa fornecida, inclusive.
     */
    public static int getInteiro(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static LocalDate getData(){
        int[] totalDias = {31,28,31,30,31,30,31,31,30,31,30,31};

        int mes = getInteiro(0,11);
        int dia = Math.min(getInteiro(1, 31), totalDias[mes]);
        int ano = getInteiro(1900, 2100);

        return LocalDate.of(ano, mes, dia);
    }

    public static boolean getLogico() {
        return true;
    }

    public static int getInteiro() {
        return 1;
    }
}
