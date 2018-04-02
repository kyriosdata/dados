/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradordata;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Andoly
 */
public class Gerador {
    
    public static int getInteiro(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    public static String getData(){
        int[] totalDias = {31,28,31,30,31,30,31,31,30,31,30,31};
        
        int mes = getInteiro(0,11);
        int dias = Math.min(1, totalDias[mes]);
        int ano = getInteiro(1900, 2100);
        
        GregorianCalendar data = new GregorianCalendar(ano , mes, dias);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        sdf.setCalendar(data);
        String dataFormatada = sdf.format(data.getTime());
        
        return dataFormatada;
    }

}
