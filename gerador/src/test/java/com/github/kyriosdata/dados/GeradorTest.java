/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.dados;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.*;

class GeradorTest {

    private Gerador gerador = null;

    @BeforeEach
    void setup() {
        gerador = Gerador.getInstance();
    }

    @Test
    void raca() {
        Set<Raca> sorteados = new HashSet<>();
        IntStream.range(0, 100).forEach(i -> sorteados.add(gerador.getRaca()));

        // Estamos supondo que em 1000 sorteios todas as opções serão
        // utilizadas.
        assertEquals(5, sorteados.size());
    }

    @Test
    void sexo() {
        Set<Sexo> sorteados = new HashSet<>();
        IntStream.range(0, 100).forEach(i -> sorteados.add(gerador.getSexo()));

        // Estamos supondo que em 1000 sorteios todas as opções serão
        // utilizadas.
        assertEquals(4, sorteados.size());
    }

    @Test
    void gerarDataFaixaUmUnicoDia() {
        String dia = "01/01/2019";
        assertEquals(dia, Gerador.entre(dia, dia));
    }

    @Test
    void gerarDataEmUmMes() {
        String inicio = "30/01/2019";
        String termino = "31/01/2019";
        String data = Gerador.entre(inicio, termino);
        assertTrue(data.equals(inicio) || data.equals(termino));
    }

    @Test
    void umValorLogico() {
        boolean logico = Gerador.logico();
        assertTrue(logico == true || logico == false);
    }

    @Test
    void umInteiroMenorIgual1000() {
        int x = Gerador.inteiro();
        assertTrue(x >= 0 && x <= 1e3);
    }

    @Test
    void geraEmFaixaUmUnicoValor() {
        assertEquals(0, Gerador.inteiro(0, 0));
        assertEquals(987, Gerador.inteiro(987, 987));
    }

    @Test
    void carregarDadosInexistentesGeraExcecao() {
        assertThrows(GeradorException.class, () ->
                gerador.carregarDados(""));
    }

    @Test
    void inteiroFaixaInvalida() {
        assertThrows(IllegalArgumentException.class, () ->
                gerador.inteiro(0, -1));
    }

    @Test
    void inteiroUnicoValor() {
        assertEquals(0, gerador.inteiro(0, 0));
    }

    @Test
    void recuperaNomesSobrenomes() {
        List<String> nomes = Gerador.getInstance().getNomes();
        assertTrue(nomes.size() > 1000);

        List<String> sobrenomes = Gerador.getInstance().getSobrenomes();
        assertTrue(sobrenomes.size() > 400);
    }

    @Test
    void verificaData() {
        String data = Gerador.getInstance().getDataAsString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Gera exceção se formato for inválido.
        // TODO não verifica bissexto!?
        LocalDate.parse(data, dtf);
    }

    @Test
    void verificaNomeCompleto() {
        String nome = Gerador.getInstance().nomeCompleto();
        assertTrue(nome.contains(" "));
    }

    @Test
    void doubleValido() {
        double d = gerador.getDoubleUnico();
        assertTrue(GeradorTestHelper.isDouble(d));
    }

    @Test
    void doubleInvalido() {
        double d = 1001.054897;
        assertFalse(GeradorTestHelper.isDouble(d));
    }

    @Test
    void corValida() {
        String corPreta = "preta";
        String corBranca = "branca";
        String corParda = "parda";
        String corAmarela = "amarela";
        String corIndigena = "indígena";

        assertTrue(GeradorTestHelper.corValida(corPreta));
        assertTrue(GeradorTestHelper.corValida(corBranca));
        assertTrue(GeradorTestHelper.corValida(corParda));
        assertTrue(GeradorTestHelper.corValida(corAmarela));
        assertTrue(GeradorTestHelper.corValida(corIndigena));
    }

    @Test
    void corInvalida() {
        String cor = "verde";
        assertFalse(GeradorTestHelper.corValida(cor));
    }

    @Test
    void sexoValido() {
        String sexoMasculino = "masculino";
        String sexoFeminino = "feminino";
        String sexoIndeterminado = "indeterminado";
        String sexoNaoDeclarado = "nao-declarado";

        assertTrue(GeradorTestHelper.sexoValido(sexoMasculino));
        assertTrue(GeradorTestHelper.sexoValido(sexoFeminino));
        assertTrue(GeradorTestHelper.sexoValido(sexoIndeterminado));
        assertTrue(GeradorTestHelper.sexoValido(sexoNaoDeclarado));
    }

    @Test
    void sexoInvalida() {
        assertFalse(GeradorTestHelper.sexoValido("trans"));
    }

    @Test
    void relacionamentoValido() {
        String avoMaterna = "avó materna";
        String avoMaterno = "avô materno";
        String conjuge = "cônjuge/companheiro(a)";
        String irmao = "irmão";
        String irma = "irmã";
        String irmaoMeio = "meio-irmão";
        String irmaMeio = "meio-irmã";
        String crianca = "criança";
        String filha = "filha";
        String avooPaterno = "avô paterno";
        String avoPaterno = "avó paterno";
        String tioMaterno = "tio materno";
        String tioPaterno = "tio paterno";
        String tiaPaterna = "tia paterna";
        String tiaMaterna = "tia materna";
        String recemNasc = "recém-nascido";
        String pais = "pais";
        String maeAdot = "mãe adotiva";
        String paiAdot = "pai adotivo";
        String responsavel = "responsável";

        assertTrue(GeradorTestHelper.relacionamentoValido(avoMaterna));
        assertTrue(GeradorTestHelper.relacionamentoValido(avoMaterno));
        assertTrue(GeradorTestHelper.relacionamentoValido(conjuge));
        assertTrue(GeradorTestHelper.relacionamentoValido(irmao));
        assertTrue(GeradorTestHelper.relacionamentoValido(irma));
        assertTrue(GeradorTestHelper.relacionamentoValido(irmaoMeio));
        assertTrue(GeradorTestHelper.relacionamentoValido(irmaMeio));
        assertTrue(GeradorTestHelper.relacionamentoValido(crianca));
        assertTrue(GeradorTestHelper.relacionamentoValido(filha));
        assertTrue(GeradorTestHelper.relacionamentoValido(avooPaterno));
        assertTrue(GeradorTestHelper.relacionamentoValido(avoPaterno));
        assertTrue(GeradorTestHelper.relacionamentoValido(tioMaterno));
        assertTrue(GeradorTestHelper.relacionamentoValido(tioPaterno));
        assertTrue(GeradorTestHelper.relacionamentoValido(tiaPaterna));
        assertTrue(GeradorTestHelper.relacionamentoValido(tiaMaterna));
        assertTrue(GeradorTestHelper.relacionamentoValido(recemNasc));
        assertTrue(GeradorTestHelper.relacionamentoValido(pais));
        assertTrue(GeradorTestHelper.relacionamentoValido(maeAdot));
        assertTrue(GeradorTestHelper.relacionamentoValido(paiAdot));
        assertTrue(GeradorTestHelper.relacionamentoValido(responsavel));
    }

    @Test
    void relacionamentoInvalida() {
        assertFalse(GeradorTestHelper.sexoValido("marido"));
    }

    @Test
    void certidaoValida() {
        String Nascimento = "Certidão de nascimento";
        String Casamento = "Certidão de casamento";
        String Divorcio = "Certidão de divórcio";

        assertTrue(GeradorTestHelper.certidaoValida(Nascimento));
        assertTrue(GeradorTestHelper.certidaoValida(Casamento));
        assertTrue(GeradorTestHelper.certidaoValida(Divorcio));
    }

    @Test
    void certidaoInvalida() {
        assertFalse(GeradorTestHelper.sexoValido("Certidão de união"));
    }

    @Test
    void verificaCnpjValido() {
        String cnpj = gerador.cnpj();
        assertTrue(GeradorTestHelper.CNPJ(cnpj));
    }

    @Test
    void cnpjInvalido() {
        assertFalse(GeradorTestHelper.CNPJ("12345678000156"));
    }

    @Test
    void verificaCpfValido() {
        assertTrue(GeradorTestHelper.CPF(((gerador.cpf()))));
    }

    @Test
    void cpfInvalido() {
        assertFalse(GeradorTestHelper.CPF("70200961160"));
    }

    @Test
    void verificaPisValido() {
        String pis = gerador.pis();
        assertTrue(GeradorTestHelper.PIS(pis));
    }

    @Test
    void pisInvalido() {
        assertFalse(GeradorTestHelper.PIS("11000000167"));
    }

    @Test
    void verificatituloValido() {
        assertTrue(GeradorTestHelper.tituloEleitoral(((gerador.tituloEleitoral()))));
    }

    @Test
    void tituloInvalido() {
        assertFalse(GeradorTestHelper.tituloEleitoral("670637480935"));
    }

    @Test
    void verificaluhnValido() {
        String cartao = gerador.luhn();
        assertTrue(GeradorTestHelper.luhn((cartao)));
    }

    @Test
    void luhnInvalido() {
        assertFalse(GeradorTestHelper.luhn("3544335808738481"));
    }

    @Test
    void verificaTexto() throws IOException {
        String textos = Gerador.getInstance().gettextoInteiro();
        assertTrue(textos.contains(" "));
        assertFalse(textos.length() > 1000);
        char[] texto = Gerador.getInstance().getTexto(1, 11);
//
//        Class cls = texto.getClass();
//        System.out.println("The type of the object is: " + cls.getName());
//
//        cls = "Lorem ipsum".getClass();
//        System.out.println("The type of the object is: " + cls.getName());

        assertEquals("Lorem ipsum", valueOf().charAt(texto));
    }

    @Test
    void verificaLogradouro() throws IOException {
        String logradouros = Gerador.getInstance().localizaLogradouro();
        assertTrue(logradouros.contains(" "));
    }

    @Test
    void verificaCartorio() throws IOException {
        String cartorio = Gerador.getInstance().cartorio();
        assertTrue(cartorio.contains(" "));
    }

    @Test
    void verificaCodigoNacional() throws IOException {
        String cod_Nacional = Gerador.getInstance().codigoNacional();
        assertFalse(cod_Nacional.isEmpty());
        assertFalse(cod_Nacional.length() > 10000);
        String codigo = Gerador.getInstance().codigoNacional();
        assertEquals(codigo,codigo);
    }


}

