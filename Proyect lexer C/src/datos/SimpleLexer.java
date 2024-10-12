
package datos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleLexer {

    private static final Set<String> keywords = new HashSet<>(Arrays.asList(
        "auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else",
        "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return",
        "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned",
        "void", "volatile", "while"
    ));

    public static List<Token> analizadorCodigoFuente(String source) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;
        while (i < source.length()) {
            char c = source.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
            } else if (Character.isLetter(c) || c == '_') {
                StringBuilder sb = new StringBuilder();
                while (i < source.length() && (Character.isLetterOrDigit(source.charAt(i)) || source.charAt(i) == '_')) {
                    sb.append(source.charAt(i));
                    i++;
                }
                String palabra = sb.toString();
                if (keywords.contains(palabra)) {
                    tokens.add(new Token(TokenType.KEYWORD, palabra));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, palabra));
                }
            } else if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                boolean isFloat = false;
                while (i < source.length() && (Character.isDigit(source.charAt(i)) || source.charAt(i) == '.')) {
                    if (source.charAt(i) == '.') {
                        isFloat = true;
                    }
                    sb.append(source.charAt(i));
                    i++;
                }
                if (isFloat) {
                    tokens.add(new Token(TokenType.FLOAT_NUM, sb.toString()));
                } else {
                    tokens.add(new Token(TokenType.INT_NUM, sb.toString()));
                }
            } else {
                switch (c) {
                    case '+': tokens.add(new Token(TokenType.PLUS, "+")); break;
                    case '-': tokens.add(new Token(TokenType.MINUS, "-")); break;
                    case '*': tokens.add(new Token(TokenType.STAR, "*")); break;
                    case '/': tokens.add(new Token(TokenType.SLASH, "/")); break;
                    case '=':
                        if (i + 1 < source.length() && source.charAt(i + 1) == '=') {
                            tokens.add(new Token(TokenType.EQUAL, "=="));
                            i++;
                        } else {
                            tokens.add(new Token(TokenType.ASSIGN, "="));
                        }
                        break;
                    case '(': tokens.add(new Token(TokenType.LPAR, "(")); break;
                    case ')': tokens.add(new Token(TokenType.RPAR, ")")); break;
                    case '{': tokens.add(new Token(TokenType.LBRACE, "{")); break;
                    case '}': tokens.add(new Token(TokenType.RBRACE, "}")); break;
                    case ';': tokens.add(new Token(TokenType.SEMI, ";")); break;
                    case ',': tokens.add(new Token(TokenType.COMMA, ",")); break;
                    default:
                        System.out.println("Carácter no reconocido: " + c);
                }
                i++;
            }
        }
        return tokens;
    }


}
