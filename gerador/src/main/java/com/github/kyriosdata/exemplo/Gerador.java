/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.exemplo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static final String TEXTO = "Lorem.txt";
    public static final ThreadLocalRandom CURRENT = ThreadLocalRandom.current();
    public static final Random RANDOM = new Random();

    private final List<String> nomes;
    private final List<String> sobrenomes;
    private final List<String> texto;
    
    
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

    public Gerador() {
        nomes = carregaLinhas(NOMES);
        sobrenomes = carregaLinhas(SOBRENOMES);
        texto = carregaLinhas(TEXTO);
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
        return CURRENT.nextInt(min, max);
    }

    /**
     * Gera um inteiro de 0 a 100.
     * @return inteiro gerado, já com um valor limite definido, nesse caso até 100.
     */
    public int getInteiroUnico(){
        return CURRENT.nextInt(100);
    }

    /**
     * Gera uma data no formato "dd/mm/aaaa" no período que vai de ?? até ??.
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

    /**
     * Gera um numero flutuante aleatorio de valor maximo 100, e formata para pegar somente 2 casas após a virgula.
     * @return um valor flutuante que foi armazenado na variavel Flut e posteriormente usado na AleatorioDouble.
     * Essa seria uma opção viável, porém formando como se fosse String, pelo o fato de está usando DecimalFormat : 
     *  
     * public static String getAleatorioFlutuante(){
            DecimalFormat df = new DecimalFormat("0.##");
            double Flut = Math.random()*100;
            String AleatorioDouble = df.format(Flut);
            return AleatorioDouble;
        }
    /**
     * Gera um double de 0 a 100.
     * @return double gerado, já com um valor limite definido, nesse caso até 100.
     */
    public double getDoubleUnico(){
        return CURRENT.nextDouble(100);
    }
    /**
     * Gera um numero flutuante dentro da faixa de 5 a 15 quando o método for chamado, e formata para pegar somente 2 casas após a virgula.
     * @return um valor flutuante que foi armazenado na variavel Flut e posteriormente usado na IntervaloDouble.
     * Essa seria uma opção viável usando como o exemplo dito, sorteando valores entre 5 e 15 :
     *  
     *  public static String getIntervaloFlutuante(){
            DecimalFormat df = new DecimalFormat ("0.##");
            double Flut = 5+Math.random()*10;
            String  IntervaloDouble= df.format(Flut);
            return IntervaloDouble;
        }
        * Necessário importar as bibliotecas :
            import java.text.DecimalFormat;
            import java.text.SimpleDateFormat;
        *   
        * 
     * Gera um double dentro da faixa indicada.
     * @param min O menor valor que pode ser sorteado.
     * @param max O maior valor que pode ser sorteado.
     * @return Um double entre a faixa fornecida, inclusive.
     */
    public double getDouble(double min, double max){
        return CURRENT.nextDouble(min, max);
    }
    /**
     * Gera uma data aleátoria entre o intervalo de 1900 ao ano 2100, tratando a diferença de dias de referente a cada mês
     * @return a data aleátoria usando a biblioteca LocalDate, no formato (ano/mes/dia)
     */
    public LocalDate getData(){
        int[] totalDias = {31,28,31,30,31,30,31,31,30,31,30,31};

        int mes = getInteiro(1,12);
        int dia = Math.min(getInteiro(1, 31), totalDias[mes - 1]);
        int ano = getInteiro(1900, 2100);

        return LocalDate.of(ano, mes, dia);
    }
    /**
     * Gera uma data aleátoria entre o intervalo especificado nos parâmetros
     * dataInicio recebe uma data inicial(limite inferior)
     * dataFim recebe uma data final(limite superior)
     *
     * @return a data a partir do intervalo especificado no método LocalDate.of
     */
    public LocalDate getDataIntervalo(){
    LocalDate dataInicio = LocalDate.of(2010, 10, 10); 
	long inicio = dataInicio.toEpochDay();

	LocalDate dataFim    = LocalDate.of(2018, 12, 11); 
	long fim = dataFim.toEpochDay();

	long dataQualquer = ThreadLocalRandom.current().longs(inicio, fim).findAny().getAsLong();
        return LocalDate.ofEpochDay(dataQualquer);
    }
    
    /**
     * Acessa um arquivo Lorem.txt, exibindo apenas a quantidade arbitrária de caracteres
     * Usa o método read da biblioteca java.io.BufferedReader.read(char[] cbuf, int off, int len)
     * cbuf cria buffer de destino
     * off recebe o número do caracter inicial para leitura
     * len recebe o número do caracter final(delimitação) para leitura
     * 
     * "entrada" recebe o documento e abre com a finalidade de leitura
     * "leitor" recebe a entrada, como sendo um novo leitor de entrada
     * "buff" cria um novo leitor em buffer
     * @return c retorna os caracteres do intervalo minimo 0 a 100 e maximo de 100 a 1000, para realizar a leitura do txt
     */
    public char getTexto() throws IOException{
      InputStream entrada = null; 
      InputStreamReader leitor = null;
      BufferedReader buff = null;

      try {
         entrada = new FileInputStream("C:Lorem.txt");         
         leitor = new InputStreamReader(entrada);
         buff = new BufferedReader(leitor);

         char[] cbuf = new char[entrada.available()];

         int min = CURRENT.nextInt(100);
         int max = CURRENT.nextInt(100,1000);
         buff.read(cbuf, min, max);
         
         for (char c:cbuf) {
         
            if(c == (char)0) {
               c = ' ';
            } 
           // System.out.print(c);
           return c;
         }
                  
      } catch(Exception e) {
         e.printStackTrace();
      } finally {
         if(entrada!=null)
            entrada.close();
         if(leitor!=null)
            leitor.close();
         if(buff!=null)
            buff.close();
      }
        return 0;
    }
    
    /**
     * Acessa um arquivo Lorem.txt, exibindo apenas a quantidade de caracteres especificado
     * 
     * @return c retorna uma quantidade exata de caracteres passado no parâmetro com nome "totalCaracteres" 
     */
    public char getString(int totalCaracteres) throws IOException{
      InputStream entrada = null; 
      InputStreamReader leitor = null;
      BufferedReader buff = null;

      try {
         entrada = new FileInputStream("C:Lorem.txt");         
         leitor = new InputStreamReader(entrada);
         buff = new BufferedReader(leitor);

         char[] cbuf = new char[entrada.available()];
         
         int  exatoCarac = CURRENT.nextInt(totalCaracteres);
         buff.read(cbuf, 0, exatoCarac);
         
         for (char c:cbuf) {
         
            if(c == (char)0) {
               c = ' ';
            } 
           // System.out.print(c);
           return c;
         }
                  
      } catch(Exception e) {
      } finally {
         if(entrada!=null)
            entrada.close();
         if(leitor!=null)
            leitor.close();
         if(buff!=null)
            buff.close();
      }
        return 0;
    }   

    /**
     * Usa de uma fórmula específica para criação de CPF's válidos, a partir de 9 digitos aleátorios, conclui 2 digitos verificadores
     * 
     * @return cpf, retorna o CPF com os 11 digitos
     */
    Random gerador = new Random();
    public String CPF(){
   
    int N1 = (gerador.nextInt(10));
    int N2 = (gerador.nextInt(10));
    int N3 = (gerador.nextInt(10));
    int N4 = (gerador.nextInt(10));
    int N5 = (gerador.nextInt(10));
    int N6 = (gerador.nextInt(10));
    int N7 = (gerador.nextInt(10));
    int N8 = (gerador.nextInt(10));
    int N9 = (gerador.nextInt(10));
    
    int DV1 = (N1*1 + N2*2 + N3*3 + N4*4 + N5*5 + N6*6 + N7*7 + N8*8 + N9*9) % 11 ;
    int DV2 = (N1*0 + N2*1 + N3*2 + N4*3 + N5*4 + N6*5 + N7*6 + N8*7 + N9*8 + DV1*9) % 11 ;
    
        String cpf = 
                Integer.toString(N1) +
                Integer.toString(N2) +
                Integer.toString(N3) + 
                "." +
                Integer.toString(N4) +
                Integer.toString(N5) +
                Integer.toString(N6) +
                "." +
                Integer.toString(N7) +
                Integer.toString(N8) +
                Integer.toString(N9) +
                "-" +
                Integer.toString(DV1) +
                Integer.toString(DV2);
        
        return cpf;
    }
        /**
     * Usa de uma fórmula específica para criação de CNPJ's válidos, a partir de 9 digitos aleátorios, conclui 2 digitos verificadores
     * 
     * @return cpf, retorna o CNPJ com os 14 digitos
     */
    public String CNPJ() {

    int N1 = (gerador.nextInt(10));
    int N2 = (gerador.nextInt(10));
    int N3 = (gerador.nextInt(10));
    int N4 = (gerador.nextInt(10));
    int N5 = (gerador.nextInt(10));
    int N6 = (gerador.nextInt(10));
    int N7 = (gerador.nextInt(10));
    int N8 = (gerador.nextInt(10));
    int N9  = 0;
    int N10 = 0;
    int N11 = 0;
    int N12 = 1;
    
    int DV1 = (N1*6 + N2*7 + N3*8 + N4*9 + N5*2 + N6*3 + N7*4 + N8*5 + N12*9) % 11 ;
    int DV2 = (N1*5 + N2*6 + N3*7 + N4*8 + N5*9 + N6*2 + N7*3 + N8*4 + N12*8 + DV1*9) % 11 ;
    
        String cnpj = 
                Integer.toString(N1) +
                Integer.toString(N2) +
                "." +
                Integer.toString(N3) + 
                Integer.toString(N4) +
                Integer.toString(N5) +
                 "." +
                Integer.toString(N6) +
                Integer.toString(N7) +
                Integer.toString(N8) +
                "/" +
                Integer.toString(N9) +
                Integer.toString(N10) +
                Integer.toString(N11) +
                Integer.toString(N12) +
                "-" +
                Integer.toString(DV1) +
                Integer.toString(DV2);
        
        return cnpj ;
    }

    
}