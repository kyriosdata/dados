/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.dados;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Classe geradora de dados. O principal uso pretendido é fornecer dados para
 * a execução de testes.
 */
public final class Gerador {

    /**
     * Gerador de números randômicos empregado por vários métodos.
     */
    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * Formato padrão (default) para data.
     */
    private static final DateTimeFormatter DATA_FMT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Relacionamentos obtidos da norma ABNT NBR 15985:2011.
     */
    private static String[] relacionamento = {
            "avó materna",
            "avô materno",
            "cônjuge/companheiro(a)",
            "irmão",
            "irmã",
            "meio-irmão",
            "meio-irmã",
            "criança",
            "filha",
            "avó paterno",
            "avô paterno",
            "tio materno",
            "tio paterno",
            "tia paterna",
            "tia materna",
            "recém-nascido",
            "pais",
            "mãe adotiva",
            "pai adotivo",
            "responsável"
    };

    /**
     * Raça/cor obtidos da norma ABNT NBR 15985:2011.
     */
    private static String[] cor = {
            "branca",
            "preta",
            "parda",
            "amarela",
            "indígena"
    };

    /**
     * sexo obtidos da norma ABNT NBR 15985:2011.
     */
    private static String[] sexo = {
            "masculino",
            "feminino",
            "indeterminado",
            "nao-declarado"
    };

    private final List<String> nomes;
    private final List<String> sobrenomes;
    private final List<String> textos;
    private final List<String> logradouros;
    private final List<String> cartorios;
    private final List<String> codigosNacionais;

    /**
     * Cria uma instância do gerador de dados.
     *
     * @throws GeradorException Se não foi possível criar uma instância do
     *                          gerador. Em geral, isto é decorrência da
     *                          impossibilidade de carregar
     *                          dados.
     */
    private Gerador() throws GeradorException {
        nomes = carregarDados(Fonte.NOMES.getFileName());
        sobrenomes = carregarDados(Fonte.SOBRENOMES.getFileName());
        textos = carregarDados(Fonte.TEXTO.getFileName());
        logradouros = carregarDados(Fonte.LOGRADOUROS.getFileName());
        cartorios = carregarDados(Fonte.CARTORIOS.getFileName());
        codigosNacionais = carregarDados(Fonte.MUNICIPIOS.getFileName());
    }

    /**
     * Referência para a única instância da classe.
     */
    private static final Gerador INSTANCIA = criaInstancia();

    /**
     * Único método que cria uma instância da presente classe.
     *
     * @return Instância da presente classe ou o valor {@code null} caso
     * ocorra uma falha durante a construção da instância.
     */
    private static Gerador criaInstancia() {
        try {
            return new Gerador();
        } catch (GeradorException e) {
            return null;
        }
    }

    /**
     * Recupera instância (única) da classe Gerador.
     *
     * @return A única instância da classe Gerador. Se houve situação
     * excepcional durante a construção da única instância, então o valor
     * retornado é {@code null}.
     */
    public static Gerador getInstance() {
        return INSTANCIA;
    }

    /**
     * Gera o valor lógico {@code true} ou {@code false}.
     *
     * @return Um valor lógico.
     */
    public static boolean logico() {
        return RANDOM.nextBoolean();
    }

    /**
     * Gera um inteiro de 0 (inclusive) até 1000 (inclusive).
     *
     * @return inteiro gerado, já com um valor limite definido, nesse caso
     * até 1000.
     */
    public static int inteiro() {
        return RANDOM.nextInt(1001);
    }

    /**
     * Gera um inteiro contido na faixa indicada, inclusive.
     *
     * @param min O menor valor que pode ser sorteado.
     * @param max O maior valor que pode ser sorteado.
     * @return Um inteiro entre menor e maior, inclusive.
     */
    public static int inteiro(int min, int max) {
        return RANDOM.nextInt(min, max + 1);
    }

    /**
     * Produz uma data entre aquelas fornecidas (inclusive).
     *
     * @param inicioInclusive  Data de início (inclusive).
     * @param terminoInclusive Data de término (inclusive).
     * @return Uma data entre aquelas fornecidas, possivelmente uma
     * delas.
     */
    public static LocalDate entre(final LocalDate inicioInclusive,
                                  final LocalDate terminoInclusive) {
        final long inicio = inicioInclusive.toEpochDay();
        final long termino = terminoInclusive.toEpochDay() + 1;
        final long dataNaFaixa = RANDOM.nextLong(inicio, termino);
        return LocalDate.ofEpochDay(dataNaFaixa);
    }

    /**
     * Produz uma data no formato "dd/mm/aaaa" entre o início e o término,
     * inclusive.
     *
     * @param inicioInclusive  Data de início (inclusive).
     * @param terminoInclusive Data de término (inclusive).
     * @return Uma data entre aquelas fornecidas, possivelmente uma delas.
     */
    public static String entre(final String inicioInclusive,
                               final String terminoInclusive) {
        final LocalDate inicio = LocalDate.parse(inicioInclusive, DATA_FMT);
        final LocalDate termino = LocalDate.parse(terminoInclusive, DATA_FMT);
        final LocalDate retorno = entre(inicio, termino);
        return DATA_FMT.format(retorno);
    }

    /**
     * Carrega conteúdo do arquivo texto (linhas) em uma lista.
     *
     * @param arquivo O arquivo texto (UTF-8) cujas linhas serão carregadas.
     * @return Lista de sequências de caracteres correspondentes às linhas
     * do arquivo texto.
     * @throws GeradorException Em caso de falha ao carregar dados.
     */
    List<String> carregarDados(String arquivo) throws GeradorException {
        if (arquivo == null || arquivo.isEmpty()) {
            throw new GeradorException("nome inválido");
        }

        InputStream is = ClassLoader.getSystemResourceAsStream(arquivo);
        InputStreamReader isr = new InputStreamReader(is,
                StandardCharsets.UTF_8);
        try (BufferedReader br = new BufferedReader(isr)) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new GeradorException("arquivo inválido");
        }
    }

    public List<String> getNomes() {
        return nomes;
    }

    public List<String> getSobrenomes() {
        return sobrenomes;
    }

    /**
     * Gera uma data no formato "dd/mm/aaaa" no período que vai de ?? até ??.
     *
     * @return Uma data arbitrária no período de ?? até ??.
     */
    public String getDataAsString() {
        return DATA_FMT.format(getData());
    }

    /**
     * Retorna um nome completo (primeiro nome e sobrenome).
     *
     * @return Um nome completo (nome e sobrenome).
     */
    public String nomeCompleto() {
        int indexNome = inteiro(0, nomes.size() - 1);
        int indexSobrenome = inteiro(0, sobrenomes.size() - 1);
        return nomes.get(indexNome) + " " + sobrenomes.get(indexSobrenome);
    }

    /**
     * Gera um double de 0 a 1000.
     *
     * @return double gerado, já com um valor limite definido, nesse caso até
     * 100.
     */
    public double getDoubleUnico() {
        return RANDOM.nextDouble(1000);
    }

    /**
     * Gera um double dentro da faixa indicada.
     *
     * @param min O menor valor que pode ser sorteado.
     * @param max O maior valor que pode ser sorteado.
     * @return Um double entre a faixa fornecida, inclusive.
     */
    public double getDouble(double min, double max) {
        return RANDOM.nextDouble(min, max);
    }

    /**
     * Gera uma data aleátoria entre o intervalo de 1900 ao ano 2100,
     * tratando a diferença de dias de referente a cada mês.
     *
     * @return a data aleátoria usando a biblioteca LocalDate, no formato
     * (ano/mes/dia).
     */
    public LocalDate getData() {
        int[] totalDias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int mes = inteiro(1, 12);
        int dia = Math.min(inteiro(1, 31), totalDias[mes - 1]);
        int ano = inteiro(1900, 2100);

        return LocalDate.of(ano, mes, dia);
    }

    /**
     * Gera uma data aleátoria entre o intervalo especificado nos parâmetros
     * dataInicio recebe uma data inicial(limite inferior)
     * dataFim recebe uma data final(limite superior).
     *
     * @return a data a partir do intervalo especificado no método getData.
     */
    public LocalDate getDataIntervalo() {
        LocalDate dataInicio = LocalDate.of(2010, 10, 10);
        long inicio = dataInicio.toEpochDay();

        LocalDate dataFim = LocalDate.of(2018, 12, 11);
        long fim = dataFim.toEpochDay();

        long dataQualquer =
                ThreadLocalRandom.current().longs(inicio, fim).findAny().getAsLong();
        return LocalDate.ofEpochDay(dataQualquer);
    }

    /**
     * Método criado para acessar e ler caracteres do arquivo Lorem.txt
     *
     * @param min O menor valor que pode ser sorteado.
     * @param max O maior valor que pode ser sorteado.
     *            <p>
     *            "inputStream" recebe o documento e abre com a finalidade de
     *            leitura
     *            "leitorTexto" recebe a entrada, como sendo um novo leitor
     *            de entrada
     *            "cBuffer" cria um novo leitor em buffer.
     * @return c retorna os caracteres do intervalo min e max, passados como
     * parâmetro.
     */
    public char[] getTexto(int min, int max) throws IOException {

        char[] cBuffer;
        BufferedReader leitorTexto = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(Fonte.TEXTO.getFileName());
            leitorTexto =
                    new BufferedReader(new InputStreamReader(inputStream));
            cBuffer = new char[inputStream.available()];
            leitorTexto.read(cBuffer, min, max);
            return cBuffer;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                leitorTexto.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Acessa getTexto para realizar leitura do arquivo texto.
     *
     * @return, retorna uma quantidade exata de caracteres passado no
     * parâmetro de getTexto, nesse caso os 1000 primeiros caracteres.
     */
    public char[] gettextoFixo() throws IOException {
        return getTexto(0, 1000);
    }

    /**
     * Acessa getTexto para realizar leitura do arquivo texto
     * Acessa o método "aleatorio" para passar como parâmetro o limite de
     * faixa, min e max.
     *
     * @return, retorna de forma aleatoria, um minimo e maximo de início e
     * fim de leitura do arquivo.
     * Nesse caso, o min será aleátorio entre 0 e 99 e o max será de 100 a 1000.
     */
    public char[] gettextoIntervalo() throws IOException {
        return getTexto(inteiro(0, 99), inteiro(100, 1000));
    }

    /**
     * Usa de uma fórmula específica para criação de CPF's válidos, a partir
     * de 9 digitos aleátorios, conclui 2 dígitos verificadores.
     *
     * @return cpf, retorna o CPF válido com os 11 dígitos.
     */
    public String cpf() {
        int[] digitos = new int[12];
        int somador1 = 0, somador2 = 0;

        IntStream.range(0, 10).forEach(i -> digitos[i] = inteiro(0, 8));

        for (int i = 1; i < digitos.length - 2; i++) {
            somador1 += digitos[i] * i;
        }

        digitos[10] = somador1 % 11;
        if (digitos[10] == 10 ) {
            digitos[10] = 0;
        }
        for (int j = 1; j < digitos.length - 1; j++) {
            somador2 += digitos[j] * (j-1);
        }
        digitos[11] = somador2 % 11;
        if (digitos[11] == 10 ) {
            digitos[11] = 0;
        }

        return IntStream.range(1, 12)
                .mapToObj(i -> Integer.toString(digitos[i]))
                .collect(Collectors.joining());
    }


    /**
     * Usa de uma fórmula específica para criação de Pis válidos, a partir de
     * 10 digitos aleátorios, conclui 1 dígito verificador.
     *
     * @return Pis, retorna o Pis válido com os 11 dígitos.
     */
    public String pis() {
        int[] digitos = new int[12];
        int somador1 = 0, somador2 = 0;
        int verificador;

        IntStream.range(0, 11).forEach(i -> digitos[i] = inteiro(0, 8));

        for (int i = 1; i < digitos.length - 9; i++) {
            somador1 += digitos[i] * (4 - i);
        }

        for (int i = 3; i < digitos.length - 2; i++) {
            somador2 += digitos[i] * (12 - i);
        }
        digitos[10] = 11 - ((somador1 + somador2) % 11);
        if (digitos[10] == 10 || digitos[10] == 11) {
            digitos[10] = 0;
        }

        return IntStream.range(1, 12)
                .mapToObj(i -> Integer.toString(digitos[i]))
                .collect(Collectors.joining());
    }

    /**
     * TODO verificar
     * Usa de uma fórmula específica para criação de CNPJ's válidos, a partir
     * de 8 dígitos aleátorios, conclui 2 dígitos verificadores.
     *
     * @return cpf, retorna o CNPJ com os 14 dígitos válidos.
     */
    public String cnpj() {
        // CNPJ arbitrário com filial 0001 (dígitos verificadores d[13] e d[14])
        int[] d = new int[15];
        IntStream.range(0, 9).forEach(i -> d[i] = inteiro(0, 8));
        d[12] = 1;

        d[13] = (d[1] * 6 + d[2] * 7 + d[3] * 8 + d[4] * 9 + d[5] * 2
                + d[6] * 3 + d[7] * 4 + d[8] * 5 + d[12] * 9) % 11;

        d[14] = (d[1] * 5 + d[2] * 6 + d[3] * 7 + d[4] * 8 + d[5] * 9
                + d[6] * 2 + d[7] * 3 + d[8] * 4 + d[12] * 8 + d[13] * 9) % 11;

        return IntStream.range(1, 15)
                .mapToObj(i -> Integer.toString(d[i]))
                .collect(Collectors.joining());
    }

    /**
     * É realizado a multiplicação dos 8 primeiros digitos por 2 3 4 5 6 7 8
     * 9 encontro o digito.
     * O digito do estado é desmembrado em unidades, por exemplo estado = 05,
     * logo verificador2 = (0*7 + 5*8 + DV1*9) modulo por 11
     * estado recebe um desses valores de forma aleátoria :
     * 01-SP, 02-MG, 03-RJ, 04-RS, 05-BA, 06-PR, 07-CE, 08-PE, 09-SC, 10-GO,
     * 11-MA, 12-PB, 13-PA, 14-ES, 15-PI,
     * 16-RN, 17-AL, 18-MT, 19-MS, 20-DF, 21-SE, 22-AM, 23-RO, 24-AC, 25-AP,
     * 26-RR, 27-TO e 28-Exterior(ZZ).
     *
     * @return titulo, retornará o título composto por 12 digitos.
     */
    public StringBuilder tituloEleitoral() {

        int estado = inteiro(1, 28);
        int[] digitos = new int[12];
        int somador1 = 0;
        StringBuilder titulo = new StringBuilder();

        for (int i = 0; i < digitos.length - 4; i++) {
            digitos[i] = inteiro(1, 9);
            titulo.append(digitos[i]);
        }

        int digito1_estado, digito2_estado;
        int verificador1 = 0, verificador2 = 0;

        if (estado <= 9) {
            digito1_estado = 0;
            digito2_estado = estado;
        } else {
            digito1_estado = estado / 10;
            digito2_estado = estado % 10;
        }

        for (int j = 0; j < digitos.length - 4; j++) {
            somador1 += digitos[j] * (j + 2);
            verificador1 = somador1 % 11;

            if (verificador1 >= 10) {
                verificador1 = 0;
            }
        }
        verificador2 =
                ((digito1_estado * 7 + digito2_estado * 8 + verificador1 * 9) % 11);
        if (verificador2 >= 10) {
            verificador2 = 0;
        }

        digitos[digitos.length - 4] = digito1_estado;
        digitos[digitos.length - 3] = digito2_estado;
        digitos[digitos.length - 2] = verificador1;
        digitos[digitos.length - 1] = verificador2;

        titulo.setLength(0);
        for (int i = 0; i < digitos.length; i++) {
            titulo.append(digitos[i]);
        }

        return titulo;
    }

    /**
     * De forma aleátoria cria o primeiro digito sendo 3 para JCB, 
     * 4 para VISA, 5 para Mastercard e 6 para Discover.
     * Cria-se um vetor de 16 posições, a partir de operações sobre os 15
     * primeiros digitos encontra o digito verificador que do cartão
     * digito[0] será o primero digito do cartão
     * digito[1], será o segundo digito sendo JCB sendo sempre 5,
     * mastercard um valor aleatório(1 a 5), e no caso de visa sem restrições(0 a 9);
     * No caso do Discover, por padrão os 4 primeiros digitos já são pre-definidos 6011.
     *
     * @return o número completo de um cartão de credito válido.
     */
    public StringBuilder luhn() {

        int[] digitos = new int[16];
        StringBuilder numeroCartao = new StringBuilder();

        for (int i = 0; i < digitos.length - 1; i++) {
            switch (i) {
                case 0:
                    digitos[i] = inteiro(3, 6);
                    break;
                case 1:
                    digitos[1] = (
                            digitos[0] == 4 ? inteiro(0, 9) :
                                    (digitos[0] == 5 ? inteiro(1, 5) :
                                            (digitos[0] == 3 ? 5 :
                                                    (digitos[0] == 6 ? 0 : 0))));
                    break;
                case 2:
                case 3:
                    if (digitos[0] == 6) {
                        digitos[i] = 1;
                    } else {
                        digitos[i] = inteiro(0, 9);
                    }

                    break;
                default:
                    digitos[i] = inteiro(0, 9);
                    break;
            }
            numeroCartao.append(digitos[i]);
        }

        int somaDigitos = 0;
        for (int i = 0; i < digitos.length; i++) {
            int digitoAtual = digitos[i];
            if ((i % 2) == 0) {
                digitoAtual = digitos[i] * 2;
                if (digitoAtual > 9) {
                    digitoAtual -= 9;
                }
            }
            somaDigitos += digitoAtual;
        }

        int digitoVerificador = ((somaDigitos % 10) == 0) ? 0 :
                (10 - (somaDigitos % 10));
        digitos[digitos.length - 1] = digitoVerificador;
        numeroCartao.setLength(0);
        for (int i = 0; i < digitos.length; i++) {
            numeroCartao.append(digitos[i]);
        }

        return numeroCartao;
    }


    /**
     * String[] relacionamento são os possiveis vinculos de um indivíduo.
     *
     * @return, retorna de forma aleátoria um dos possiveis relacionamentos.
     */
    public String getRelacionamento() {
        int vinculo = inteiro(0, relacionamento.length);

        return relacionamento[vinculo];
    }

    
    /**
     * String[] cor são s possiveis cores/raça de um indivíduo.
     *
     * @return, retorna de forma aleátoria uma das possiveis cores.
     */
    public String getCor() {
        int raca = inteiro(0, cor.length);

        return cor[raca];
    }

    /**
     * String[] sexo são os possiveis gêneros de um indivíduo.
     *
     * @return, retorna de forma aleátoria um dos possíveis gêneros.
     */
    public String getSexo() {
        int genero = inteiro(0, sexo.length);

        return sexo[genero];
    }

    /**
     * String[] certidao são os três possíveis certidões.
     *
     * @return, retorna de forma aleátoria uma certidão.
     */
    public String getCertidao() {
        String[] certidao = {
                "Certidão de nascimento",
                "Certidão de casamento",
                "Certidão de divórcio"
        };
        int tipo = inteiro(0, certidao.length);

        return certidao[tipo];
    }

    /**
     * indexCartorios recebe um valor aleátorio entre 0 e o tamanho de
     * cartorios(número máximo de cartorios).
     *
     * @return, retorna de forma aleátoria um cartório.
     */
    public String cartorio() {
        int indexCartorios = inteiro(0, cartorios.size() - 1);
        return cartorios.get(indexCartorios);
    }

    /**
     * indexLogradouro recebe um valor aleátorio entre 0 e o tamanho de
     * logradouros(número máximo de logradouros).
     *
     * @return, retorna de forma aleátoria um logradouro.
     */
    public String localizaLogradouro() {
        int indexLogradouro = inteiro(0, logradouros.size() - 1);
        return logradouros.get(indexLogradouro);
    }

    /**
     * indexCodigoNacional recebe um valor aleátorio entre 0 e o tamanho de
     * codigos(número máximo de códigos nacionais).
     *
     * @return, retorna de forma aleátoria um código nacional completo.
     */
    public String codigoNacional() {
        int indexCodigoNacional = inteiro(0, codigosNacionais.size() - 1);
        return codigosNacionais.get(indexCodigoNacional);
    }

}