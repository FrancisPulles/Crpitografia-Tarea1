package com.uce.logic;

import com.uce.logic.ciphers.*;

public class CifradoFactory {

    public static Cifrador crear(String tipo, String parametro) {

        switch (tipo) {

            case "César":
                return new CifradoCesar(Integer.parseInt(parametro));

            case "Atbash":
                return new CifradoAtbash();

            case "Vigenere":
                return new CifradoVigenere(parametro);

            case "Rail Fence":
                return new CifradoRailFence(Integer.parseInt(parametro));

            case "Playfair":
                return new CifradoPlayfair(parametro);

            default:
                throw new IllegalArgumentException("Cifrado no soportado: " + tipo);
        }
    }
}