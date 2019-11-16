    /*
     * Copyright (c) 2016.
     * Fábrica de Software - Instituto de Informática (UFG)
     * Creative Commons Attribution 4.0 International License.
     */

    package com.github.kyriosdata.dados;

    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.util.List;

    import static org.junit.jupiter.api.Assertions.*;

    class GeradorTest {
        
        private Gerador gerador = null;
        
        @BeforeEach
        void setup(){
            gerador = Gerador.getInstance();
        }

        @Test
        void gerarDataFaixaUmUnicoDiaTest() {
            String dia = "01/01/2019";
            assertEquals(dia, Gerador.entre(dia, dia));
        }

        @Test
        void gerarDataEmUmMesTest() {
            String inicio = "30/01/2019";
            String termino = "31/01/2019";
            String data = Gerador.entre(inicio, termino);
            assertTrue(data.equals(inicio) || data.equals(termino));
        }

        @Test
        void umValorLogicoTest() {
            boolean logico = Gerador.logico();
            assertTrue(logico == true || logico == false);
        }

        @Test
        void umInteiroMenorIgual1000Test() {
            int x = Gerador.inteiro();
            assertTrue(x >= 0 && x <= 1e3);
        }

        @Test
        void geraEmFaixaUmUnicoValorTest() {
            assertEquals(0, Gerador.inteiro(0, 0));
            assertEquals(987, Gerador.inteiro(987, 987));
        }

        @Test
        void carregarDadosInexistentesGeraExcecaoTest() {
            assertThrows(GeradorException.class, () ->
                    gerador.carregarDados(""));
        }

        @Test
        void inteiroFaixaInvalidaTest() {
            assertThrows(IllegalArgumentException.class, () ->
                    gerador.inteiro(0, -1));
        }

        @Test
        void inteiroUnicoValorTest() {
            
            assertEquals(0, gerador.inteiro(0, 0));
        }

        @Test
        void recuperaNomesSobrenomesTest() {
            List<String> nomes = Gerador.getInstance().getNomes();
            assertTrue(nomes.size() > 1000);

            List<String> sobrenomes = Gerador.getInstance().getSobrenomes();
            assertTrue(sobrenomes.size() > 400);
        }

        @Test
        void verificaDataTest() {
            String data = Gerador.getInstance().getDataAsString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Gera exceção se formato for inválido.
            // TODO não verifica bissexto!?
            LocalDate.parse(data, dtf);
        }

        @Test
        void verificaNomeCompletoTest() {
            String nome = Gerador.getInstance().nomeCompleto();
            assertTrue(nome.contains(" "));
        }

        @Test
        void doubleValidoTest(){
            double d = gerador.getDoubleUnico();
            assertTrue(GeradorTestHelper.isDouble(d));
        }

        @Test
        void doubleInvalidoTest(){
            double d = 1001.054897;
            assertFalse(GeradorTestHelper.isDouble(d));
        }

        @Test
        void corValidaTest(){
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
        void corInvalidaTest(){
            String cor = "verde";
            assertFalse(GeradorTestHelper.corValida(cor));
        }

        @Test
        void sexoValidoTest(){
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
        void sexoInvalidaTest(){
            assertFalse(GeradorTestHelper.sexoValido("trans"));
        }

        @Test
        void relacionamentoValidoTest(){
            String avoMaterna ="avó materna";
            String avoMaterno = "avô materno";
            String conjuge = "cônjuge/companheiro(a)";
            String irmao = "irmão";
            String irma ="irmã";
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
        void relacionamentoInvalidaTest(){
            assertFalse(GeradorTestHelper.sexoValido("marido"));
        }

        @Test
        void certidaoValidaTest(){
            String Nascimento = "Certidão de nascimento";
            String Casamento = "Certidão de casamento";
            String Divorcio = "Certidão de divórcio";

            assertTrue(GeradorTestHelper.certidaoValida(Nascimento));
            assertTrue(GeradorTestHelper.certidaoValida(Casamento));
            assertTrue(GeradorTestHelper.certidaoValida(Divorcio));
        }

        @Test
        void certidaoInvalidaTest(){
            assertFalse(GeradorTestHelper.sexoValido("Certidão de união"));
        }

        @Test
        void cnpjTest() {
            System.out.println("CNPJ : "+ gerador.cnpj());
        }

        @Test
        void verificaCnpjValidoTest(){
            assertTrue(GeradorTestHelper.CNPJ(gerador.cnpj()));
        }

        @Test
        void cnpjInvalidoTest(){
            assertFalse(GeradorTestHelper.CNPJ("12345678000156"));
        }

        @Test
        void cpfTest() {
            System.out.println("CPF : "+ gerador.cpf());
        }

        @Test
        void verificaCpfValidoTest(){
            assertTrue(GeradorTestHelper.CPF(((gerador.cpf()))));
        }

        @Test
        void cpfInvalidoTest(){
            assertFalse(GeradorTestHelper.CPF("70200961160"));
        }

        @Test
        void verificaPisValidoTest(){
            assertTrue(GeradorTestHelper.PIS(((gerador.pis()))));
        }

        @Test
        void pisInvalidoTest(){
            assertFalse(GeradorTestHelper.PIS("11000000167"));
        }
    }

