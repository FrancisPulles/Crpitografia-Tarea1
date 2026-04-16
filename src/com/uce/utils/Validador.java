package com.uce.utils;

public class Validador {

    public static void validar(String tipo, String parametro) {

        switch (tipo) {

            case "César":
                if (parametro.isEmpty()) {
                    throw new IllegalArgumentException("Debes ingresar un desplazamiento.");
                }

                int desplazamiento = Integer.parseInt(parametro);

                if (desplazamiento <= 0 || desplazamiento >= 26) {
                    throw new IllegalArgumentException("El desplazamiento debe estar entre 1 y 25.");
                }
                break;

            case "Rail Fence":
                if (parametro.isEmpty()) {
                    throw new IllegalArgumentException("Debes ingresar el número de rieles.");
                }

                int rieles = Integer.parseInt(parametro);

                if (rieles < 2) {
                    throw new IllegalArgumentException("Debe haber al menos 2 rieles.");
                }
                break;

            case "Vigenere":
            case "Playfair":
                if (parametro.isEmpty()) {
                    throw new IllegalArgumentException("Debes ingresar una clave.");
                }
                break;

            case "Atbash":
                break;
        }
    }
}
