package com.github.kyriosdata.dados;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class GeradorTestHelper {
    /**
     * Método auxiliar que verifica se determinada valor do tipo
     * double recebido como parâmetro é válido em relação ao limite
     * definido não nulo de 0 a 1000.
     *
     * @param d
     *
     * @return true se a cor for válida, false se não.
     */
    public static boolean isDouble(double d){
        return (!Double.isNaN(d)) && (d >= 0.0 && d < 1001.0);
    }

    /**
     * Método auxiliar que verifica se determinada cor recebida como
     * parâmetro é válida em relação as cores válidas.
     *
     * @param cor
     *
     * @return true se a cor for válida, false se não.
     */
    public static boolean corValida(String cor){
        String cores[] = {"branca","preta","parda","amarela","indígena"};
        for(int i = 0; i < cores.length; i++){
            if(cor == cores[i]){
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
     *
     * @return true se a cor for válida, false se não.
     */
    public static boolean sexoValido(String sexo){
        String sexos[] = {
                "masculino",
                "feminino",
                "indeterminado",
                "nao-declarado"
        };
        for(int i = 0; i < sexos.length; i++){
            if(sexo == sexos[i]){
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
     *
     * @return true se a cor for válida, false se não.
     */
    public static boolean relaçionamentoValido(String relacionamento){
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
        for(int i = 0; i < relacionamentos.length; i++){
            if(relacionamento == relacionamentos[i]){
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
     *
     * @return true se a cor for válida, false se não.
     */
    public static boolean CNPJ(String cnpj){
        int aux[] = new int[14];
        IntStream.range(1, 15).forEach(i -> aux[i-1] = Integer.parseInt(String.valueOf(cnpj.charAt(i-1))));

        System.out.println(cnpj);

        int verificador_1 = (aux[0] * 6 + aux[1] * 7 + aux[2] * 8 + aux[3] * 9 + aux[4] * 2
                + aux[5] * 3 + aux[6] * 4 + aux[7] * 5 + aux[11] * 9) % 11;

        //System.out.println(verificador_1);
        int verificador_2 = (aux[0] * 5 + aux[1] * 6 + aux[2] * 7 + aux[3] * 8 + aux[4] * 9
                + aux[5] * 2 + aux[6] * 3 + aux[7] * 4 + aux[11] * 8 + verificador_1 * 9) % 11;

        return (verificador_1 == aux[12]) && (verificador_2 == aux[13]);
    }

}
