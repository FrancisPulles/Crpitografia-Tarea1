package com.uce.logic.ciphers;

public class CifradoAtbash implements Cifrador {

    @Override
    public String cifrar(String texto) {
        return transformar(texto);
    }

    @Override
    public String descifrar(String texto) {
        return transformar(texto); // es simétrico
    }

    private String transformar(String texto) {
        StringBuilder resultado = new StringBuilder();

        for (char caracter : texto.toCharArray()) {
            if (caracter == ' ') {
                resultado.append(caracter);
                continue;
            }

            int nuevoCaracter = 'Z' - (caracter - 'A');
            resultado.append((char) nuevoCaracter);
        }

        return resultado.toString();
    }
}
