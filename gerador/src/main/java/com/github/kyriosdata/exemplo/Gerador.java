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
    
    private static final String nome = "nomes.txt";
    private static final String sobrenome = "sobrenomes.txt";
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
        nomes = carregaLinhas(nome);
        sobrenomes = carregaLinhas(sobrenome);
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
	           //System.out.print(c);
	           return c;
	        }
                  
      	}
        catch(Exception e){
         e.printStackTrace();
      	}
      	finally {
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
                  
    }	
    catch(Exception e) {
    }
     	finally {
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
    * Usa de uma fórmula específica para criação de CPF's válidos, a partir de 9 digitos aleátorios, conclui 2 dígitos verificadores
    * @return cpf, retorna o CPF válido com os 11 dígitos
    */
    Random gerador = new Random();
    public String cpf(){
    Random gerador = new Random();

    int digito1 = (gerador.nextInt(10));
    int digito2 = (gerador.nextInt(10));
    int digito3 = (gerador.nextInt(10));
    int digito4 = (gerador.nextInt(10));
    int digito5 = (gerador.nextInt(10));
    int digito6 = (gerador.nextInt(10));
    int digito7 = (gerador.nextInt(10));
    int digito8 = (gerador.nextInt(10));
    int digito9 = (gerador.nextInt(10));
    
   	 	int verificador1 = (digito1*1 + digito2*2 + digito3*3 + digito4*4 + digito5*5 + digito6*6 + digito7*7 + digito8*8 + digito9*9) % 11 ;
    	int verificador2 = (digito1*0 + digito2*1 + digito3*2 + digito4*3 + digito5*4 + digito6*5 + digito7*6 + digito8*7 + digito9*8 + verificador1*9) % 11 ;

        String cpf = String.format("%d%d%d%d%d%d%d%d%d%d%d",digito1,digito2,digito3,digito4,digito5,digito6,digito7,digito8,digito9,verificador1,verificador2);
        return cpf;
    }

    /**
    * Usa de uma fórmula específica para criação de CNPJ's válidos, a partir de 8 dígitos aleátorios, conclui 2 dígitos verificadores
    * @return cpf, retorna o CNPJ com os 14 dígitos válidos
    */
    public String cnpj() {
    Random gerador = new Random();
    int digito1 = (gerador.nextInt(10));
    int digito2 = (gerador.nextInt(10));
    int digito3 = (gerador.nextInt(10));
    int digito4 = (gerador.nextInt(10));
    int digito5 = (gerador.nextInt(10));
    int digito6 = (gerador.nextInt(10));
    int digito7 = (gerador.nextInt(10));
    int digito8 = (gerador.nextInt(10));
    int digito9  = 0;
    int digito10 = 0;
    int digito11 = 0;
    int digito12 = 1;
    
    	int verificador1 = (digito1*6 + digito2*7 + digito3*8 + digito4*9 + digito5*2 + digito6*3 + digito7*4 + digito8*5 + digito12*9) % 11 ;
    	int verificador2 = (digito1*5 + digito2*6 + digito3*7 + digito4*8 + digito5*9 + digito6*2 + digito7*3 + digito8*4 + digito12*8 + verificador1*9) % 11 ;
    
        String cnpj = String.format("%d%d%d%d%d%d%d%d%d%d%d%d%d%d",digito1,digito2,digito3,digito4,digito5,digito6,digito7,digito8,digito9,digito10,digito11,digito12,verificador1,verificador2); 
        
        return cnpj ;
    }
    
    /**
    * É realizado a multiplicação dos 8 primeiros digitos por 2 3 4 5 6 7 8 9 encontro o digito
    * O digito do estado é desmembrado em unidades, por exemplo ESTADO = 05, logo DV2 = (0*7 + 5*8 + DV1*9) modulo por 11
    * ESTADO recebe um desses valores de forma aleátoria : 
    * 01-SP, 02-MG, 03-RJ, 04-RS, 05-BA, 06-PR, 07-CE, 08-PE, 09-SC, 10-GO, 11-MA, 12-PB, 13-PA, 14-ES, 15-PI, 
    * 16-RN, 17-AL, 18-MT, 19-MS, 20-DF, 21-SE, 22-AM, 23-RO, 24-AC, 25-AP, 26-RR, 27-TO e 28-Exterior(ZZ)
    
    * @return titulo, retornará o título composto por 12 digitos
    */
    public String tituloEleitoral() {
    Random gerador = new Random();
    int digito1 = (gerador.nextInt(10));
    int digito2 = (gerador.nextInt(10));
    int digito3 = (gerador.nextInt(10));
    int digito4 = (gerador.nextInt(10));
    int digito5 = (gerador.nextInt(10));
    int digito6 = (gerador.nextInt(10));
    int digito7 = (gerador.nextInt(10));
    int digito8 = (gerador.nextInt(10));
    
    int numEstado [] = {1,2,3,4,5,6,7,8,9,10,11,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28};
    int estado = gerador.nextInt(numEstado.length);
            
    int digito1_estado, digito2_estado;
    int verificador1, verificador2;
    
    if(estado <= 9){
        digito1_estado = 0;
        digito2_estado = estado;
    }
    else{
        digito1_estado = estado / 10;
        digito2_estado = estado % 10;
    }
   
    verificador1 = (digito1*2 + digito2*3 + digito3*4 + digito4*5 + digito5*6 + digito6*7 + digito7*8 + digito8*9) % 11 ;
    if(verificador1 >= 10){
        verificador1 = 0;
    }
   
    verificador2 = (digito1_estado*7 + digito2_estado*8 + verificador1*9) % 11 ;
    if(verificador2 >= 10){
        verificador2 = 0;
    }
   
        String titulo = String.format("%d%d%d%d%d%d%d%d%d%d%d%d",digito1,digito2,digito3,digito4,digito5,digito6,digito7,digito8,digito1_estado,digito2_estado,verificador1,verificador2);

        return titulo;
    }
    
	/**
	 *De forma aleátoria cria o primeiro digito sendo 4 para VISA e 5 para Mastercard
	 *Cria-se um vetor de 14 posições, e a partir do primeiro digito + o digitos[13], encontra o digito verificador que do cartão
	 *digitoInicial será o primero digito do cartão, JCB será sempre 3, VISA 4, Mastercard 5; 
	 *digito2, será o segundo digito sendo JCB sendo sempre 5,mastercard indo no maximo até 5, e no caso de visa sem restrições;
	 *No caso do Diners Club, por padrão os 4 primeiros digitos já são pre-definidos 6011. 
	 */
    public String luhn() {
	Random gerador = new Random();
    int digitos[] = new int[13];
    int multiplicadores[] = {2,1,2,1,2,1,2,1,2,1,2,1,2};
    int digitoMastercard [] = {1,2,3,4,5};
    int Num[] = new int[13];
    int i = 0, j = 0;
    int digito2=0, digitoInicial=0,digitorVerificador, digitoAuxiliar = 0, resto10, somavetor = 0;
    int randomCase = gerador.nextInt(4);
        
        switch(randomCase){
        case 0:
            digitoInicial = 3;  
            digitoAuxiliar = 6;
            digito2 = 5;
	break;    
        case 1:
            digitoInicial = 4; 
            digitoAuxiliar = 8;
            digito2 = gerador.nextInt(10);
	break;
        case 2:
            digitoInicial = 5;  
            digitoAuxiliar = 1;
            digito2 = gerador.nextInt(digitoMastercard.length+1);
	break;
        case 3:
            digitoInicial = 6;  
            digitoAuxiliar = 3;
	break;
        }
        
        for(i=0; i<13; i++){
            int x = (int)Math.round(Math.random()*9);
            digitos[i] = x;
        }
        if(digitoInicial == 6){
        digito2 = 0;
        digitos[0] = 1;
        digitos[1] = 1;
        }
        
        for(j=0; j<multiplicadores.length ; j++){
            Num[j] = digitos[j] * multiplicadores[j];
        if((digitos[j] * multiplicadores[j]) > 9){
            Num[j]=Num[j] - 9;
        }       
        somavetor +=Num[j];
        }
        
        resto10 = (( digitoAuxiliar + digito2 + somavetor ) % 10);
        if(resto10 == 0){
            digitorVerificador = 0;
        }
        else {
            digitorVerificador = 10 - resto10;
        }
        
        String numeroCartao = String.format("%d%d%d%d%d%d%d%d%d%d%d%d%d%d%d%d",digitoInicial,digito2,digitos[0],digitos[1],digitos[2],digitos[3],digitos[4],digitos[5],digitos[6],digitos[7],digitos[8],digitos[9],digitos[10],digitos[11],digitos[12],digitorVerificador);

        return numeroCartao;
    }
    
}