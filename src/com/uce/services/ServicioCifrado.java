package com.uce.services;

import com.uce.logic.ciphers.Cifrador;
import com.uce.logic.CifradoFactory;
import com.uce.utils.Normalizador;
import com.uce.utils.Validador;

public class ServicioCifrado {

    public static String procesar(String tipo, String texto, String parametro, boolean modoCifrado) {

        if (texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException("El texto no puede estar vacío.");
        }

        // 🔹 Normalizar texto
        texto = Normalizador.normalizar(texto);

        // 🔹 Validar parámetros
        Validador.validar(tipo, parametro);

        // 🔹 Normalizar clave si aplica
        if (tipo.equals("Vigenere") || tipo.equals("Playfair")) {
            parametro = Normalizador.normalizar(parametro);

            if (parametro.isEmpty()) {
                throw new IllegalArgumentException("La clave no puede quedar vacía.");
            }
        }

        // 🔹 Crear cifrador
        Cifrador cifrador = CifradoFactory.crear(tipo, parametro);

        return modoCifrado ? cifrador.cifrar(texto) : cifrador.descifrar(texto);
    }
}