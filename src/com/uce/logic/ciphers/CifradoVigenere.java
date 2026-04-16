package com.uce.logic.ciphers;

public class CifradoVigenere implements Cifrador {

    private String clave;

    public CifradoVigenere(String clave) {
        this.clave = clave;
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
        StringBuilder resultado = new StringBuilder();
        int indiceClave = 0;

        for (char caracter : texto.toCharArray()) {

            if (caracter == ' ') {
                resultado.append(caracter);
                continue;
            }

            int valorTexto = caracter - 'A';
            int valorClave = clave.charAt(indiceClave % clave.length()) - 'A';

            int nuevoValor;

            if (cifrar) {
                nuevoValor = (valorTexto + valorClave) % 26;
            } else {
                nuevoValor = (valorTexto - valorClave + 26) % 26;
            }

            resultado.append((char) (nuevoValor + 'A'));
            indiceClave++;
        }

        return resultado.toString();
    }
}
