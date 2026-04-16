package com.uce.logic.ciphers;

public class CifradoCesar implements Cifrador {

    private int desplazamiento;

    public CifradoCesar(int desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    @Override
    public String cifrar(String texto) {
        return aplicarDesplazamiento(texto, desplazamiento);
    }

    @Override
    public String descifrar(String texto) {
        return aplicarDesplazamiento(texto, 26 - desplazamiento);
    }

    private String aplicarDesplazamiento(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();

        for (char caracter : texto.toCharArray()) {
            if (caracter == ' ') {
                resultado.append(caracter);
                continue;
            }

            int base = 'A';
            int nuevoCaracter = (caracter - base + desplazamiento) % 26 + base;
            resultado.append((char) nuevoCaracter);
        }

        return resultado.toString();
    }
}