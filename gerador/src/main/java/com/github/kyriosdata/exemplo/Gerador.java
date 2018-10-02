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
    public String cpf(){
      	public static int aleatorio(int min, int max){
        Random r = new Random();
        return (min + r.nextInt(max - min + 1));
   		}    

   		int[] digitos = new int[11];
	    int somador1 =0,somador2=0;
	    int verificador1=0, verificador2=0;
	    StringBuilder cpf = new StringBuilder();
    
       for(int i=0; i<digitos.length-2; i++){
       digitos[i] = aleatorio(1,9);
       cpf.append(digitos[i]);
       } 

       for(int i=0; i<digitos.length-2 ; i++){
        somador1 += digitos[i] *(i+1) ;
       }
       verificador1 = somador1 % 11;
       
       digitos[digitos.length-2] = verificador1;
       for(int j=0; j<digitos.length-1 ; j++){
       somador2 += digitos[j] *j ;
       }

       verificador2 = somador2 % 11;
       digitos[digitos.length-1] = verificador2;
       cpf.setLength(0);
       for(int i = 0; i < digitos.length; i++) {
       cpf.append(digitos[i]);
   	   }
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
    * O digito do estado é desmembrado em unidades, por exemplo estado = 05, logo verificador2 = (0*7 + 5*8 + DV1*9) modulo por 11
    * estado recebe um desses valores de forma aleátoria : 
    * 01-SP, 02-MG, 03-RJ, 04-RS, 05-BA, 06-PR, 07-CE, 08-PE, 09-SC, 10-GO, 11-MA, 12-PB, 13-PA, 14-ES, 15-PI, 
    * 16-RN, 17-AL, 18-MT, 19-MS, 20-DF, 21-SE, 22-AM, 23-RO, 24-AC, 25-AP, 26-RR, 27-TO e 28-Exterior(ZZ)
    
    * @return titulo, retornará o título composto por 12 digitos
    */
    public String tituloEleitoral() {
    	public static int aleatorio(int min, int max){
        Random r = new Random();
        return (min + r.nextInt(max - min + 1));
   		}    
       int estado = aleatorio(1,28);
       int[] digitos = new int[12];
       int somador1=0;
       StringBuilder titulo = new StringBuilder();
       
	    for(int i=0; i<digitos.length-4; i++){
	    digitos[i] = aleatorio(1,9);
	    titulo.append(digitos[i]);
	    }
       
    	int digito1_estado, digito2_estado;
    	int verificador1 = 0, verificador2 = 0;
    
    	if(estado <= 9){
        digito1_estado = 0;
        digito2_estado = estado;
    	}
		    else{
		        digito1_estado = estado / 10;
		        digito2_estado = estado % 10;
		    }
    
	    for(int j=0; j<digitos.length-4; j++){
	        somador1 += digitos[j] * (j+2);
	        verificador1 = somador1 % 11;
	        
	        if(verificador1 >= 10){
	            verificador1 = 0;
	        }
	    }
	    verificador2 = ((digito1_estado*7 + digito2_estado*8 + verificador1*9) % 11) ;
	    if(verificador2 >= 10){
	        verificador2 = 0;
	    }
    
	    digitos[digitos.length-4] = digito1_estado;
	    digitos[digitos.length-3] = digito2_estado;
	    digitos[digitos.length-2] = verificador1;
	    digitos[digitos.length-1] = verificador2;

	    titulo.setLength(0);
	    for(int i = 0; i < digitos.length; i++) {
	        titulo.append(digitos[i]);
	    }

        return titulo;
    }
    
	/**
	 *De forma aleátoria cria o primeiro digito sendo 3 para JCB, 4 para VISA, 5 para Mastercard e 6 para Diners Club
	 *Cria-se um vetor de 16 posições, a partir de operações sobre os 15 primeiros digitos encontra o digito verificador que do cartão
	 *digito[0] será o primero digito do cartão 
	 *digito[1], será o segundo digito sendo JCB sendo sempre 5,mastercard um valor aleatório(1 a 5), e no caso de visa sem restrições(0 a 9);
	 *No caso do Diners Club, por padrão os 4 primeiros digitos já são pre-definidos 6011. 
	 */
    public String luhn() {
    	public static int aleatorio(int min, int max){
        Random r = new Random();
        return (min + r.nextInt(max - min + 1));
    	}
        int[] digitos = new int[16];
        StringBuilder numeroCartao = new StringBuilder();
     
        for(int i = 0; i < digitos.length-1; i++){
            switch(i){
                case 0:
                    digitos[i] = aleatorio(3,6);
                break;
                case 1:
                    digitos[1] = (
                            digitos[0] == 4 ? aleatorio(0,9) :
                            (digitos[0] == 5 ? aleatorio(1,5):
                            (digitos[0] == 3 ? 5:
                            (digitos[0] == 6 ? 0: 0))));  
                break;
                case 2:
                case 3:
                    if(digitos[0] == 6) {
                        digitos[i] = 1;
                    } else {
                        digitos[i] = aleatorio(0,9);
                    }
                   
                break;
                default:
                    digitos[i] = aleatorio(0,9);
                break;
            }
            numeroCartao.append(digitos[i]);
        }
       
        int somaDigitos = 0;
        for(int i = 0; i < digitos.length; i++){
            int digitoAtual = digitos[i];
            if((i % 2) == 0) {
                digitoAtual = digitos[i]*2;
                if(digitoAtual > 9) {
                    digitoAtual -= 9;
                }
            }
            somaDigitos += digitoAtual;
        }
       
        int digitoVerificador = ((somaDigitos % 10) == 0) ? 0 :
                                (10 - (somaDigitos % 10));
        digitos[digitos.length-1] = digitoVerificador;
        numeroCartao.setLength(0);
        for(int i = 0; i < digitos.length; i++) {
            numeroCartao.append(digitos[i]);
        }

        return numeroCartao;
    }
    
}