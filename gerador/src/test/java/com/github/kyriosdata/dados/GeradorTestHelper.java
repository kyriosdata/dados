package com.github.kyriosdata.dados;

import java.util.stream.IntStream;

public class GeradorTestHelper {

    /**
     * Método auxiliar que verifica se determinada valor do tipo
     * double recebido como parâmetro é válido em relação ao limite
     * definido não nulo de 0 a 1000.
     *
     * @param d
     * @return true se o double for válido, false se não.
     */
    public static boolean isDouble(double d) {
        return (!Double.isNaN(d)) && (d >= 0.0 && d < 1001.0);
    }

    public static String codNacional(String codigo){
        int codigoNac = Gerador.inteiro(1,100);
        return codigo;
    }

    /**
     * Método auxiliar que verifica se determinada cor recebida como
     * parâmetro é válida em relação as cores válidas.
     *
     * @param cor
     * @return true se a cor for válida, false se não.
     */
    public static boolean corValida(String cor) {
        String cores[] = {"branca", "preta", "parda", "amarela", "indígena"};
        for (int i = 0; i < cores.length; i++) {
            if (cor == cores[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método auxiliar que verifica se determinada cor recebida como
     * parâmetro é válida em relação aos sexos validos.
     *
     * @param sexo
     * @return true se a sexo for válido, false se não.
     */
    public static boolean sexoValido(String sexo) {
        String sexos[] = {
                "masculino",
                "feminino",
                "indeterminado",
                "nao-declarado"
        };
        for (int i = 0; i < sexos.length; i++) {
            if (sexo == sexos[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método auxiliar que verifica se determinado relacionado recebida como
     * parâmetro é válida em relação aos relacionamentos validos.
     *
     * @param relacionamento
     * @return true se o relacionamento for válida, false se não.
     */
    public static boolean relacionamentoValido(String relacionamento) {
        String relacionamentos[] = {
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
        for (int i = 0; i < relacionamentos.length; i++) {
            if (relacionamento == relacionamentos[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método auxiliar que verifica se determinado tipo de  certidão
     * recebida como parâmetro é válida em relação aos certidões validos.
     *
     * @param certidao
     * @return true se a certidão for válida, false se não.
     */
    public static boolean certidaoValida(String certidao) {
        String[] certidoes = {
                "Certidão de nascimento",
                "Certidão de casamento",
                "Certidão de divórcio"
        };
        for (int i = 0; i < certidoes.length; i++) {
            if (certidao == certidoes[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método auxiliar que reescreve o cnpj gerado e verifica se o verificador_1
     * é igual ao 13º dígito do cnpj e o verificador_2 é igual ao 14º dígito,
     * determinando se um cnpj recebido como parâmetro é válido.
     *
     * @param cnpj
     * @return true se o cnpj for válido, false se não.
     */
    public static boolean CNPJ(String cnpj) {
        int aux[] = new int[14];
        IntStream.range(1, 15).forEach(i -> aux[i - 1] =
                Integer.parseInt(String.valueOf(cnpj.charAt(i - 1))));

        int verificador_1 =
                (aux[0] * 6 + aux[1] * 7 + aux[2] * 8 + aux[3] * 9 + aux[4] * 2
                + aux[5] * 3 + aux[6] * 4 + aux[7] * 5 + aux[11] * 9) % 11;

        int verificador_2 =
                (aux[0] * 5 + aux[1] * 6 + aux[2] * 7 + aux[3] * 8 + aux[4] * 9
                + aux[5] * 2 + aux[6] * 3 + aux[7] * 4 + aux[11] * 8 + verificador_1 * 9) % 11;

        if (verificador_1 == 10) {
            verificador_1 = 0;
        }
        if (verificador_2 == 10) {
            verificador_2 = 0;
        }

        return (verificador_1 == aux[12]) && (verificador_2 == aux[13]);
    }

    /**
     * Método auxiliar que reescreve o cpf gerado e verifica se o verificador1
     * é igual ao 10º dígito do cpf e o verificador2 é igual ao 11º dígito,
     * determinando se um cpf recebido como parâmetro é válido.
     *
     * @param cpf
     * @return true se o cpf for válido, false se não.
     */
    public static boolean CPF(String cpf) {
        int[] digitos = new int[11];
        int somador1 = 0, somador2 = 0;
        int verificador1, verificador2;

        IntStream.range(0, 11).forEach(i -> digitos[i] =
                Integer.parseInt(String.valueOf(cpf.charAt(i))));

        for (int i = 1; i < 10; i++) {
            somador1 += digitos[i - 1] * i;
        }
        verificador1 = somador1 % 11;
        if (verificador1 == 10) {
            verificador1 = 0;
        }

        for (int j = 0; j < 10; j++) {
            somador2 += digitos[j] * j;
        }
        verificador2 = somador2 % 11;
        if (verificador2 == 10) {
            verificador2 = 0;
        }
        return (verificador1 == digitos[9]) && (verificador2 == digitos[10]);
    }

    /**
     * Método auxiliar que reescreve o pis gerado e verifica se o verificador
     * é igual ao 11º dígito que corresponde ao digitos[10],
     * determinando se um pis recebido como parâmetro é válido.
     *
     * @param pis
     * @return true se o pis for válido, false se não.
     */
    public static boolean PIS(String pis) {
        int[] digitos = new int[11];
        int somador1 = 0, somador2 = 0;
        int verificador;

        IntStream.range(0, 11).forEach(i -> digitos[i] =
                Integer.parseInt(String.valueOf(pis.charAt(i))));

        for (int i = 1; i < 3; i++) {
            somador1 += digitos[i - 1] * (4 - i);
        }

        for (int i = 2; i < 10; i++) {
            somador2 += digitos[i] * (11 - i);
        }
        verificador = 11 - ((somador1 + somador2) % 11);
        if (verificador == 10 || verificador == 11) {
            verificador = 0;
        }
        if (digitos[10] == 0) {
            digitos[10] = verificador;
        }

        return (verificador == digitos[10]);
    }

    public static boolean tituloEleitoral(String titulo) {
        int[] digitos = new int[12];
        int somador1 = 0;
        int verificador1 = 0, verificador2;
        IntStream.range(0, 12).forEach(i -> digitos[i] =
                Integer.parseInt(String.valueOf(titulo.charAt(i))));

        for (int j = 0; j < digitos.length - 4; j++) {
            somador1 += digitos[j] * (j + 2);
            verificador1 = somador1 % 11;
            //System.out.println("S1 =" + somador1);
            if (verificador1 == 10) {
                verificador1 = 0;
            }
        }
        verificador2 =
                (digitos[8] * 7 + digitos[9] * 8 + (verificador1 * 9)) % 11;
        if (verificador2 == 10) {
            verificador2 = 0;
        }
        if (digitos[11] == 0) {
            digitos[11] = verificador2;
        }
        return (verificador1 == digitos[10] && verificador2 == digitos[11]);
    }

    public static boolean luhn(String luhn) {
        int[] digitos = new int[16];

        IntStream.range(0, 16).forEach(i -> digitos[i] =
                Integer.parseInt(String.valueOf(luhn.charAt(i))));

        int somaDigitos = 0;
        for (int i = 0; i < 15; i++) {
            int digitoAtual = digitos[i];
            if ((i % 2) == 0) {
                digitoAtual = digitos[i] * 2;
                if (digitoAtual > 9) {
                    digitoAtual -= 9;
                }
            }
            somaDigitos += digitoAtual;
        }

        int digitoVerificador = (10 - (somaDigitos % 10));

        if (digitos[15] == 0) {
            digitos[15] = digitoVerificador;
        }

        return digitoVerificador == digitos[15];
    }

}
