/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.exemplo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * Geração de dados.
 *
 */
public final class Gerador {

    private static final String NOMES = "nomes.txt";
    private static final String SOBRENOMES = "sobrenomes.txt";
    private static final DateTimeFormatter SDF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final ThreadLocalRandom CURRENT = ThreadLocalRandom.current();

    private List<String> nomes;
    private List<String> sobrenomes;
    public static final Random RANDOM = new Random();

    public Gerador() {
        nomes = carregaLinhas(NOMES);
        sobrenomes = carregaLinhas(SOBRENOMES);
    }

    public List<String> getNomes() {
        return nomes;
    }

    public List<String> getSobrenomes() {
        return sobrenomes;
    }

    /**
     * Gera um inteiro dentro da faixa indicada.
     * @param min O menor valor que pode ser sorteado.
     * @param max O maior valor que pode ser sorteado.
     *
     * @return Um inteiro entre a faixa fornecida, inclusive.
     */
    public int getInteiro(int min, int max){
        return CURRENT.nextInt(min, max + 1);
    }

    /**
     * Gera um inteiro de 0 a 100.
     * @return inteiro gerado, já com um valor limite definido, nesse caso até 100.
     */
    public int getInteiroUnico(){
        return CURRENT.nextInt(100);
    }

    /**
     * Gera uma data no formato "dd/mm/aaaa" no período que vai de ?? até
     * ??.
     *
     * @return Uma data arbitrária no período de ?? até ??.
     */
    public String getDataAsString(){
        return SDF.format(getData());
    }

    /**
     * Gera um valor lógico {@code true} ou {@code false}.
     *
     * @return Um valor lógico.
     */
    public boolean getLogico() {
        return RANDOM.nextBoolean();
    }

    /**
     * Retorna um nome completo (primeiro nome e sobrenome).
     *
     * @return Um nome completo (nome e sobrenome).
     */
    public String nomeCompleto(){
        int indexNome = getInteiro(0, nomes.size() - 1);
        int indexSobrenome = getInteiro(0, sobrenomes.size() - 1);
        return nomes.get(indexNome) + " " + sobrenomes.get(indexSobrenome);
    }

    public int getInteiro() {
        return getInteiro(1,100);
    }

    /**
     * Gera um numero flutuante aleatorio de valor maximo 100, e formata para pegar somente 2 casas após a virgula.
     * @return um valor flutuante que foi armazenado na variavel Flut e posteriormente usado na AleatorioDouble.
     */
    public static String getAleatorioFlutuante(){
        DecimalFormat df = new DecimalFormat("0.##");
        double Flut = Math.random()*100;
        String AleatorioDouble = df.format(Flut);
        return AleatorioDouble;
    }
    /**
     * Gera um numero flutuante dentro da faixa de 5 a 15 quando o método for chamado, e formata para pegar somente 2 casas após a virgula.
     * @return um valor flutuante que foi armazenado na variavel Flut e posteriormente usado na IntervaloDouble.
     */
    public static String getIntervaloFlutuante(){
        DecimalFormat df = new DecimalFormat ("0.##");
        double Flut = 5+Math.random()*10;
        String  IntervaloDouble= df.format(Flut);
        return IntervaloDouble;
    }

    public LocalDate getData(){
        int[] totalDias = {31,28,31,30,31,30,31,31,30,31,30,31};

        int mes = getInteiro(1,12);
        int dia = Math.min(getInteiro(1, 31), totalDias[mes - 1]);
        int ano = getInteiro(1900, 2100);

        return LocalDate.of(ano, mes, dia);
    }

    /**
     * Obtém instância de {@link File} para arquivo mantido no diretório
     * "resources".
     *
     * @param file Nome do arquivo contido no diretório "resources".
     *
     * @return Instância de {@link File} para arquivo contido no diretório
     * "resources".
     */
    private File getFileFromResources(String file) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        return new File(classLoader.getResource(file).getFile());
    }

    /**
     * Carrega conteúdo do arquivo texto (linhas) em uma lista.
     *
     * @param arquivo O arquivo texto (UTF-8) cujas linhas serão carregadas.
     *
     * @return Lista de sequências de caracteres correspondentes às linhas
     * do arquivo texto.
     */
    private List<String> carregaLinhas(String arquivo) {
        List<String> linhas = new ArrayList<>();
        File file = getFileFromResources(arquivo);
        Path path = Paths.get(file.toURI());
        try (Stream<String> fluxo = Files.lines(path, StandardCharsets.UTF_8)) {
            fluxo.forEach(linha -> linhas.add(linha));
        } catch (IOException exp) {
            return null;
        }

        return linhas;
    }
}
