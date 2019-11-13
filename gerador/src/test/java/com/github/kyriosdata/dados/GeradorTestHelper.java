package com.github.kyriosdata.dados;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class GeradorTestHelper {
    public static boolean isDouble(double d){
        return (!Double.isNaN(d)) && (d >= 0.0 && d < 1001.0);
    }

    /**
     * Método auxiliar que verifica se determinada cor recebida como
     * parâmetro é válida em relação as cores válidas
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

    public static boolean CNPJ(String cnpj){
        int aux[] = new int[14];
        IntStream.range(1, 15).forEach(i -> aux[i-1] = Integer.parseInt(String.valueOf(cnpj.charAt(i-1))));

        System.out.println(cnpj);

        int verificador_1 = (aux[0] * 6 + aux[1] * 7 + aux[2] * 8 + aux[3] * 9 + aux[4] * 2
                + aux[5] * 3 + aux[6] * 4 + aux[7] * 5 + aux[11] * 9) % 11;

        int verificador_2 = (aux[0] * 5 + aux[1] * 6 + aux[2] * 7 + aux[3] * 8 + aux[4] * 9
                + aux[5] * 2 + aux[6] * 3 + aux[7] * 4 + aux[11] * 8 + verificador_1 * 9) % 11;

        return (verificador_1 == aux[13]) && (verificador_2 == aux[14]);
    }

}
