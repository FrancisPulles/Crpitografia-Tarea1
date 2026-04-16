package com.uce.utils;

public class Normalizador {

    public static String normalizar(String texto) {
        if (texto == null) return "";

        texto = texto.toUpperCase();

        texto = texto.replace("Á", "A")
                     .replace("É", "E")
                     .replace("Í", "I")
                     .replace("Ó", "O")
                     .replace("Ú", "U")
                     .replace("Ñ", "N");

        return texto.replaceAll("[^A-Z ]", "");
    }
}
