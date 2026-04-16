package com.uce.logic.ciphers;

import java.util.HashSet;
import java.util.Set;

public class CifradoPlayfair implements Cifrador {

    private char[][] matriz = new char[5][5];

    public CifradoPlayfair(String clave) {
        generarMatriz(clave);
    }

    private void generarMatriz(String clave) {
        Set<Character> usados = new HashSet<>();
        clave = clave.replace("J", "I");

        StringBuilder alfabetoCompleto = new StringBuilder();

        for (char caracter : clave.toCharArray()) {
            if (!usados.contains(caracter)) {
                usados.add(caracter);
                alfabetoCompleto.append(caracter);
            }
        }

        for (char caracter = 'A'; caracter <= 'Z'; caracter++) {
            if (caracter == 'J') continue;

            if (!usados.contains(caracter)) {
                usados.add(caracter);
                alfabetoCompleto.append(caracter);
            }
        }

        for (int i = 0; i < 25; i++) {
            matriz[i / 5][i % 5] = alfabetoCompleto.charAt(i);
        }
    }

    private int[] buscarPosicion(char caracter) {
        if (caracter == 'J') caracter = 'I';

        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 5; columna++) {
                if (matriz[fila][columna] == caracter) {
                    return new int[]{fila, columna};
                }
            }
        }
        return null;
    }

    @Override
    public String cifrar(String texto) {
        return procesar(texto, true);
    }

    @Override
    public String descifrar(String texto) {
        return procesar(texto, false);
    }

    private String procesar(String texto, boolean cifrar) {

        texto = texto.replace("J", "I").replace(" ", "");

        StringBuilder textoPreparado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char a = texto.charAt(i);
            char b = (i + 1 < texto.length()) ? texto.charAt(i + 1) : 'X';

            if (a == b) {
                textoPreparado.append(a).append('X');
            } else {
                textoPreparado.append(a).append(b);
                i++;
            }
        }

        if (textoPreparado.length() % 2 != 0) {
            textoPreparado.append('X');
        }

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < textoPreparado.length(); i += 2) {
            char a = textoPreparado.charAt(i);
            char b = textoPreparado.charAt(i + 1);

            int[] posA = buscarPosicion(a);
            int[] posB = buscarPosicion(b);

            int desplazamiento = cifrar ? 1 : 4;

            if (posA[0] == posB[0]) {
                // misma fila
                resultado.append(matriz[posA[0]][(posA[1] + desplazamiento) % 5]);
                resultado.append(matriz[posB[0]][(posB[1] + desplazamiento) % 5]);

            } else if (posA[1] == posB[1]) {
                // misma columna
                resultado.append(matriz[(posA[0] + desplazamiento) % 5][posA[1]]);
                resultado.append(matriz[(posB[0] + desplazamiento) % 5][posB[1]]);

            } else {
                // rectángulo
                resultado.append(matriz[posA[0]][posB[1]]);
                resultado.append(matriz[posB[0]][posA[1]]);
            }
        }

        return resultado.toString();
    }
}