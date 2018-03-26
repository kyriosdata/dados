/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geradorparadb;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andoly
 */
public class GeradorParaDB {

    /**
     * @param args the command line arguments
     */
    final static String path = "/src/geradorparadb/files/";
    public static void main(String[] args) throws IOException {
        
        Arquivo arq = new Arquivo();
        
        arq.setType("nomes.txt");
        arq.setPathName(new File("").getAbsolutePath() + path + arq.getType());
        
        arq.setType("sobrenomes.txt");
        arq.setPathLastName(new File("").getAbsolutePath() + path + arq.getType());
        
        arq.setType("dias.txt");
        arq.setPathDay(new File("").getAbsolutePath() + path + arq.getType());
        
        arq.setType("meses.txt");
        arq.setPathMonth(new File("").getAbsolutePath() + path + arq.getType());
        
        arq.setType("anos.txt");
        arq.setPathYear(new File("").getAbsolutePath() + path + arq.getType());
        
        List <String> nomes = Arquivo.leitorString(arq.getPathName());
        List <String> sobrenomes = Arquivo.leitorString(arq.getPathLastName());
        
        //Lista de arquivos de ano
        List <Integer> anos = Arquivo.leitorNumero(arq.getPathYear());
        List <Integer> meses = Arquivo.leitorNumero(arq.getPathMonth());
        List <Integer> dias = Arquivo.leitorNumero(arq.getPathDay());
        
        System.out.println("1 - Gerador de Nomes\n2 - Gerador de Datas\n");
        Scanner ler = new Scanner(System.in);
        
        int op = ler.nextInt();
        int n = 0;
        
        switch(op){
            case 1:
                //Gerador de nomes
                System.out.println("Digite a quantidade de datas a serem geradas: ");
                n = ler.nextInt();
                while(n > 0){
                    System.out.println(Sorteador.nomeCompleto(nomes, sobrenomes));
                    n--;
                }
            break;
            
            case 2:
                //Gerando datas
                System.out.println("Digite a quantidade de datas a serem geradas: ");
                n = ler.nextInt();
                while(n > 0){
                    int mes = Sorteador.sortearNumero(meses);
                    int dia = Sorteador.sortearNumero(dias);
                    if(mes == 02 && dia > 28){
                        dia = 28;
                    }
                    int ano = Sorteador.sortearNumero(anos);
                    String formatDia;
                    String formatMes;

                    if(mes < 10 && dia < 10){
                        formatDia = "0"+dia;
                        formatMes = "0"+mes;
                        System.out.println(formatDia+"/"+formatMes+"/"+ano);
                    } 
                    else if(dia < 10){
                        formatDia = "0"+dia;
                        System.out.println(formatDia+"/"+mes+"/"+ano);
                    }
                    else if(mes < 10){
                        formatMes = "0"+mes;
                        System.out.println(dia+"/"+formatMes+"/"+ano);
                    } else {
                        System.out.println(dia+"/"+mes+"/"+ano);
                    }
                    n--;
                }
            break;
            default:
                
            break;
        }
    }
}
