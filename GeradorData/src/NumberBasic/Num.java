
package NumberBasic;

import java.util.Random;
import java.text.DecimalFormat;  


/**
 * @author Andoly
 */
public class Num {

    public void Num() {
		//instância um objeto da classe Random usando o construtor padrão
        
		Random Number = new Random();
                DecimalFormat df = new DecimalFormat ("0.##");
                
	    	System.out.println("Sorteando numeros inteiros de 0 a 99: ");
	    for (int i = 0; i < 10; i++) {
                int In = (int)(Math.random()*100);
	    	System.out.println(In); 
	    }
            System.out.println("Sorteando numeros flutuantes de 0 a 99: ");
            for (int n = 0; n < 10; n++){
                double d = Math.random()*100;
                String dx = df.format(d);
            	System.out.println(dx);
            }
            	System.out.println("Sorteando numeros inteiros de 10 a 90: ");
	    for (int i = 0; i < 10; i++) {
                int In = 10+(int)(Math.random()*80);
	    	System.out.println(In); 
	    }
          
            
            
    

    }
}
