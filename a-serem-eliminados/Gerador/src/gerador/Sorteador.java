/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorparadb;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Andoly
 */
public class Sorteador {
    public static String sortearNome(List <String> l){
        int size = l.size();
        int r = ThreadLocalRandom.current().nextInt(0, size + 1);
        return l.get(r);
    }
    
    public static String sortearString(List <String> l){
        int size = l.size();
        int r = ThreadLocalRandom.current().nextInt(0, size + 1);
        return l.get(r);
    }
    
    public static int sortearNumero(List <Integer> l){
        int size = l.size();
        int r = ThreadLocalRandom.current().nextInt(0, size + 1);
        return l.get(r);
    }
    
    public static String nomeCompleto(List <String> nome, List <String> sobrenome){
        return sortearNome(nome) + " " + sortearNome(sobrenome);
    }
}
