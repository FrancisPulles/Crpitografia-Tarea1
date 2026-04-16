package com.uce.logic.ciphers;

public class CifradoRailFence implements Cifrador {

    private int rieles;

    public CifradoRailFence(int rieles) {
        this.rieles = rieles;
    }

    @Override
    public String cifrar(String texto) {
        StringBuilder[] vallas = new StringBuilder[rieles];

        for (int i = 0; i < rieles; i++) {
            vallas[i] = new StringBuilder();
        }

        int fila = 0;
        boolean bajando = true;

        for (char caracter : texto.toCharArray()) {
            vallas[fila].append(caracter);

            if (fila == 0) bajando = true;
            else if (fila == rieles - 1) bajando = false;

            fila += bajando ? 1 : -1;
        }

        StringBuilder resultado = new StringBuilder();
        for (StringBuilder valla : vallas) {
            resultado.append(valla);
        }

        return resultado.toString();
    }

    @Override
    public String descifrar(String texto) {

        char[][] matriz = new char[rieles][texto.length()];

        int fila = 0;
        boolean bajando = true;

        // 🔹 Marcar recorrido zig-zag
        for (int columna = 0; columna < texto.length(); columna++) {
            matriz[fila][columna] = '*';

            if (fila == 0) bajando = true;
            else if (fila == rieles - 1) bajando = false;

            fila += bajando ? 1 : -1;
        }

        // 🔹 Rellenar matriz
        int indice = 0;
        for (int i = 0; i < rieles; i++) {
            for (int j = 0; j < texto.length(); j++) {
                if (matriz[i][j] == '*' && indice < texto.length()) {
                    matriz[i][j] = texto.charAt(indice++);
                }
            }
        }

        // 🔹 Leer en zig-zag
        StringBuilder resultado = new StringBuilder();
        fila = 0;
        bajando = true;

        for (int columna = 0; columna < texto.length(); columna++) {
            resultado.append(matriz[fila][columna]);

            if (fila == 0) bajando = true;
            else if (fila == rieles - 1) bajando = false;

            fila += bajando ? 1 : -1;
        }

        return resultado.toString();
    }
}