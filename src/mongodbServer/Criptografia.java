package mongodbServer;

import java.util.HashMap;
import java.util.Map;

public class Criptografia {

    // Dicionários estáticos para criptografia e descriptografia
    static Map<Character, Character> dicionarioCriptografia = new HashMap<>();
    static Map<Character, Character> dicionarioDescriptografia = new HashMap<>();

    static {
        inicializarDicionarios();
    }

    // Método estático para inicializar os dicionários
    private static void inicializarDicionarios() {
        // Letras maiúsculas
        dicionarioCriptografia.put('A', 'N');
        dicionarioCriptografia.put('B', 'O');
        dicionarioCriptografia.put('C', 'P');
        dicionarioCriptografia.put('D', 'Q');
        dicionarioCriptografia.put('E', 'R');
        dicionarioCriptografia.put('F', 'S');
        dicionarioCriptografia.put('G', 'T');
        dicionarioCriptografia.put('H', 'U');
        dicionarioCriptografia.put('I', 'V');
        dicionarioCriptografia.put('J', 'W');
        dicionarioCriptografia.put('K', 'X');
        dicionarioCriptografia.put('L', 'Y');
        dicionarioCriptografia.put('M', 'Z');
        dicionarioCriptografia.put('N', 'A');
        dicionarioCriptografia.put('O', 'B');
        dicionarioCriptografia.put('P', 'C');
        dicionarioCriptografia.put('Q', 'D');
        dicionarioCriptografia.put('R', 'E');
        dicionarioCriptografia.put('S', 'F');
        dicionarioCriptografia.put('T', 'G');
        dicionarioCriptografia.put('U', 'H');
        dicionarioCriptografia.put('V', 'I');
        dicionarioCriptografia.put('W', 'J');
        dicionarioCriptografia.put('X', 'K');
        dicionarioCriptografia.put('Y', 'L');
        dicionarioCriptografia.put('Z', 'M');

        // Letras minúsculas
        dicionarioCriptografia.put('a', 'n');
        dicionarioCriptografia.put('b', 'o');
        dicionarioCriptografia.put('c', 'p');
        dicionarioCriptografia.put('d', 'q');
        dicionarioCriptografia.put('e', 'r');
        dicionarioCriptografia.put('f', 's');
        dicionarioCriptografia.put('g', 't');
        dicionarioCriptografia.put('h', 'u');
        dicionarioCriptografia.put('i', 'v');
        dicionarioCriptografia.put('j', 'w');
        dicionarioCriptografia.put('k', 'x');
        dicionarioCriptografia.put('l', 'y');
        dicionarioCriptografia.put('m', 'z');
        dicionarioCriptografia.put('n', 'a');
        dicionarioCriptografia.put('o', 'b');
        dicionarioCriptografia.put('p', 'c');
        dicionarioCriptografia.put('q', 'd');
        dicionarioCriptografia.put('r', 'e');
        dicionarioCriptografia.put('s', 'f');
        dicionarioCriptografia.put('t', 'g');
        dicionarioCriptografia.put('u', 'h');
        dicionarioCriptografia.put('v', 'i');
        dicionarioCriptografia.put('w', 'j');
        dicionarioCriptografia.put('x', 'k');
        dicionarioCriptografia.put('y', 'l');
        dicionarioCriptografia.put('z', 'm');

        // Números
        dicionarioCriptografia.put('0', '5');
        dicionarioCriptografia.put('1', '6');
        dicionarioCriptografia.put('2', '7');
        dicionarioCriptografia.put('3', '8');
        dicionarioCriptografia.put('4', '9');
        dicionarioCriptografia.put('5', '0');
        dicionarioCriptografia.put('6', '1');
        dicionarioCriptografia.put('7', '2');
        dicionarioCriptografia.put('8', '3');
        dicionarioCriptografia.put('9', '4');

        // Caracteres especiais e espaço
        dicionarioCriptografia.put(' ', '_');
        dicionarioCriptografia.put('.', ',');
        dicionarioCriptografia.put(',', '.');
        dicionarioCriptografia.put('!', '?');
        dicionarioCriptografia.put('?', '!');
        dicionarioCriptografia.put('-', '+');
        dicionarioCriptografia.put('+', '-');
        dicionarioCriptografia.put('@', '|');

        // Inicializar o dicionário de descriptografia invertendo o de criptografia
        for (Map.Entry<Character, Character> entry : dicionarioCriptografia.entrySet()) {
            dicionarioDescriptografia.put(entry.getValue(), entry.getKey());
        }
    }

    // Método estático para criptografar
    public static String criptografar(String texto) {
        char[] textoArray = texto.toCharArray();
        StringBuilder cripto = new StringBuilder();

        for (char c : textoArray) {
            if (dicionarioCriptografia.containsKey(c)) {
                cripto.append(dicionarioCriptografia.get(c));
            } else {
                cripto.append(c);
            }
        }

        return cripto.toString();
    }

    // Método estático para descriptografar
    public static String descriptografar(String texto) {
        char[] textoArray = texto.toCharArray();
        StringBuilder descripto = new StringBuilder();

        for (char c : textoArray) {
            if (dicionarioDescriptografia.containsKey(c)) {
                descripto.append(dicionarioDescriptografia.get(c));
            } else {
                descripto.append(c);
            }
        }


        return descripto.toString();
    }

}