
package geradordado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andoly
 */
public class Arquivo {
    private String pathName;
    private String pathLastName;
    private String type;
    /**
     * Método responsavel por ler e preenche a lista a partir dos txt passados(nomes.txt) e (sobrenomes.txt), que será chamado na classe principal .
     */
    public static List leitorString(String path) throws IOException {
        List<String> l = new ArrayList<>();
        int i = 0;
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            String linha = "";
            while (true) {
                if (linha != null) {
                    l.add(i,linha);
                    i++;
                } else
                    break;
                linha = buffRead.readLine();
            }
        }
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
  
}