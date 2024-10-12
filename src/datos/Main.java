
package datos;

import static datos.SimpleLexer.analizadorCodigoFuente;
import java.util.List;


public class Main {
        public static void main(String[] args) {
        String sourceCode = """
                            int main() {
                                int a = 5; 
                                float b = 3.14;
                                return 0;
                            }
                            """;
        List<Token> tokens = analizadorCodigoFuente(sourceCode);
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
    
}
