
package geradordado;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Andoly
 */
public class Gerador {
    /**
     * Gera um inteiro dentro da faixa indicado quando o método for chamado.
     * @param min O menor valor que pode ser sorteado.
     * @param max O maior valor que pode ser sorteado.
     * @return Um inteiro entre a faixa fornecida, inclusive.
     */
    
    public static int getInteiro(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    /**
     * Gera um inteiro de 0 a 100.
     * @return inteiro gerado, já com um valor limite definido, nesse caso até 100.
     */
    public static int getInteiroUnico(){
        return ThreadLocalRandom.current().nextInt(100);
    }
    
    /**
     * Gera datas divididas em dias/mes/ano.
     * totalDias temos um vetor que armazena o maximo de dias para cada mes.
     * mes usa o método intInteiro para gerar em uma faixa de 0 a 11 que representa os mes possiveis.
     * dias gerar de forma aleatoria a partir do intervalo passado, sendo o minimo 1 a 31 e o maximo o total de dias possiveis que tem como maximo 28 a 31 dias, ou seja, de 1 até 31. 
     * ano usa o método intInteiro para gerar em uma faixa de 1900 ate 2100 que são os possiveis anos do intervalo definido. 
     *
     * @return Um inteiro entre a faixa fornecida, inclusive.
     */
    public static String getData(){
        int[] totalDias = {31,28,31,30,31,30,31,31,30,31,30,31};
        
        int mes = getInteiro(0,11);
        int dias = Math.min(getInteiro(1, 31), totalDias[mes]);
        int ano = getInteiro(1900, 2100);
        
        GregorianCalendar data = new GregorianCalendar(ano , mes, dias);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        sdf.setCalendar(data);
        String dataFormatada = sdf.format(data.getTime());
        
        //return LocalDate.of(ano, mes, dias);  Sobre a hipotese de utilizar a biblioteca LocalDate eu preferir manter como String pois ja tenho um construtor de inteiros para dia/mes/ano
        return dataFormatada;
    }
    //Gerador de boolean
    public static boolean getLogico() {
    Random TF = new Random();
    return TF.nextBoolean();
    }
    
    /**
     * Usa a biblioteca ThreadLocalRandom.current() para sortear um nome a partir de uma lista.
     * Armazena os possiveis nomes do txt e em seguida sortea para utilizar no metodo nomeCompleto .
     * @param List<String>nome armazena um nome sorteado da lista.
     * @param List<String>sobrenome armazena um sobrenome sorteado da lista.
     * return sortearNome(nome) + " " + sortearNome(sobrenome), o nome concatenado a partir de 2 listas com possiveis nomes e sobrenomes.
     */
        public static String sortearNome(List <String> l){
            int size = l.size();
            int r = ThreadLocalRandom.current().nextInt(0, size + 1);
            return l.get(r);
        }
    public static String nomeCompleto(List <String> nome, List <String> sobrenome){
        return sortearNome(nome) + " " + sortearNome(sobrenome);
    }
    /**
     * Gera um inteiro dentro da faixa indicado do método getInteiro.
     * @param min sendo 1 que é o minimo do intervalo definido na chamada do método.
     * @param max sendo 100 que é o maximo do intervalo definido na chamada do método.
     * @return Um inteiro entre a faixa fornecida, 1-100.
     */
    public static int getInteiro() {
        int inteiro = getInteiro(1,100);
        return inteiro;
    }
     /**
     * Gera um inteiro aleatorio usando o método getInteiroUnico.
     * @return o inteiro aleatorio gerado.
     */
    public static int unico() {
        int Unico = getInteiroUnico();
        return Unico;
    }
    
    Random Number = new Random();
     /**
     * Gera um numero flutuante aleatorio de valor maximo 100, e formata para pegar somente 2 casas após a virgula.
     * @return um valor flutuante que foi armazenado na variavel Flut e posteriormente usado na AleatorioDouble.
     */
    public static String getAleatorioFlutuante(){
        DecimalFormat df = new DecimalFormat ("0.##");
        double Flut = Math.random()*100;
        String AleatorioDouble = df.format(Flut);
        return AleatorioDouble;
    }
     /**
     * Gera um numero flutuante dentro da faixa de 5 a 15 quando o método for chamado, e formata para pegar somente 2 casas após a virgula.
     * @return um valor flutuante que foi armazenado na variavel Flut e posteriormente usado na IntervaloDouble.
     */
    public static String getIntervaloFlutuante(){
        DecimalFormat df = new DecimalFormat ("0.##");
        double Flut = 5+Math.random()*10;
        String  IntervaloDouble= df.format(Flut);
        return IntervaloDouble;
    }
}
    


    



