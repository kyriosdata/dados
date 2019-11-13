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

}
