
package geradorparadb;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class Arquivo {
    private String pathName;
    private String pathLastName;
    private String pathDay;
    private String pathMonth;
    private String pathYear;
    private String type;
    
    public static List leitorString(String path) throws IOException {
        List<String> l = new ArrayList<>();
        int i = 0;
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        while (true) {
            if (linha != null) {
                l.add(i,linha);
                i++;
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        return l;
    }
    
    public static List leitorNumero(String path) throws IOException {
        List<Integer> l = new ArrayList<Integer>();
        int i = 0;
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "0";
        while (true) {
            if (linha != null){
                l.add(i,Integer.parseInt(linha));
                i++;
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        return l;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getPathLastName() {
        return pathLastName;
    }

    public void setPathLastName(String pathLastName) {
        this.pathLastName = pathLastName;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPathDay() {
        return pathDay;
    }

    public void setPathDay(String pathDay) {
        this.pathDay = pathDay;
    }

    public String getPathMonth() {
        return pathMonth;
    }

    public void setPathMonth(String pathMonth) {
        this.pathMonth = pathMonth;
    }

    public String getPathYear() {
        return pathYear;
    }

    public void setPathYear(String pathYear) {
        this.pathYear = pathYear;
    }
    
    
}