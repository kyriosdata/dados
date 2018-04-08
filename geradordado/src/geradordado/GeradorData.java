
package geradordado;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andoly
 */
 public class GeradorData {
    
    final static String path = "/src/GeradorData/files/";
    
    public static void main(String[] args) throws IOException {
     /**
     * Mostra na tela as saidas da chamada dos métodos, colocando uma Data, um inteiro dentro de uma faixa, um inteiro aleatorio, um numero flutuante de 5 a 15, um flutuante aleatorio, um valor logico (True /False)
     */
        System.out.println("Data aleatoria criada: "+Gerador.getData());
        System.out.println("Numero aleatoria entre 0 e 100: "+Gerador.getInteiro());
        System.out.println("Numero inteiro aleatoria: "+Gerador.getInteiroUnico());
        System.out.println("Numero flutuante entre 5 e 15: "+Gerador.getIntervaloFlutuante());
        System.out.println("Numero flutuante aleatoria: "+Gerador.getAleatorioFlutuante()); 
        System.out.println("Valor logico aleatorio: "+Gerador.getLogico()+"\n");
       
     /**
     * Instancia a classe Arquivo a partir do caminho percorrido para alcançar os txt.
     * Ler do teclado a quantidade de nomes que serão gerados e decrementa esse valor, printando a quantidade de nomes desejada.
     */
        Arquivo arq = new Arquivo();
        arq.setType("nomes.txt");
        arq.setPathName(new File("").getAbsolutePath() + path + arq.getType());
        
        arq.setType("sobrenomes.txt");
        arq.setPathLastName(new File("").getAbsolutePath() + path + arq.getType());

        List <String> nomes = Arquivo.leitorString(arq.getPathName());
        List <String> sobrenomes = Arquivo.leitorString(arq.getPathLastName());

        int n = 0;
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite a quantidade de nomes a serem geradas: ");
                n = ler.nextInt();
                while(n > 0){
                    System.out.println(Gerador.nomeCompleto(nomes, sobrenomes));
                    n--;
                }
    }
    
}
