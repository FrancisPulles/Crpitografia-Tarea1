package com.uce.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainUI extends JFrame {

    private JComboBox<String> selectorCifrado;
    private JTextField campoParametro;
    private JLabel etiquetaParametro;
    private JTextArea areaEntrada;
    private JTextArea areaSalida;
    private JButton botonAccion;
    private JButton botonIntercambiar;
    private JTextArea etiquetaDescripcion;
    private JLabel etiquetaEntrada;
    private JLabel etiquetaSalida;

    private boolean modoCifrado = true;

    public MainUI() {
        setTitle("Sistema de Cifrado");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        inicializarComponentes();
        botonAccion.addActionListener(e -> procesarTexto());
        setVisible(true);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(40, 55, 80));

        // 🔝 Panel superior
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        panelSuperior.setBackground(new Color(40, 55, 80));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        selectorCifrado = new JComboBox<>(new String[]{
                "César", "Atbash", "Vigenere", "Rail Fence", "Playfair"
        });
        selectorCifrado.setPreferredSize(new Dimension(180, 30));
        selectorCifrado.addActionListener(e -> actualizarUIPorCifrado());

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelSuperior.add(crearEtiqueta("Método de Cifrado:"), gbc);

        gbc.gridx = 1;
        panelSuperior.add(selectorCifrado, gbc);

        etiquetaParametro = crearEtiqueta("Desplazamiento:");
        gbc.gridx = 2;
        panelSuperior.add(etiquetaParametro, gbc);

        campoParametro = new JTextField();
        campoParametro.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 3;
        panelSuperior.add(campoParametro, gbc);

        etiquetaDescripcion = new JTextArea("Escoja un método de cifrado");
        etiquetaDescripcion.setLineWrap(true);
        etiquetaDescripcion.setWrapStyleWord(true);
        etiquetaDescripcion.setEditable(false);
        etiquetaDescripcion.setFocusable(false);
        etiquetaDescripcion.setForeground(Color.WHITE);
        etiquetaDescripcion.setBackground(new Color(40, 55, 80));
        etiquetaDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
        etiquetaDescripcion.setBorder(null);
        etiquetaDescripcion.setPreferredSize(new Dimension(600, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        panelSuperior.add(etiquetaDescripcion, gbc);

        // 📝 Panel central
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(40, 55, 80));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        areaEntrada = crearAreaTexto();
        areaSalida = crearAreaTexto();

        botonIntercambiar = new JButton("↔");
        botonIntercambiar.setFont(new Font("Arial", Font.BOLD, 18));
        botonIntercambiar.addActionListener(e -> alternarModo());

        botonAccion = new JButton("Cifrar");
        botonAccion.setPreferredSize(new Dimension(120, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        etiquetaEntrada = crearEtiqueta("Texto normal");
        panelCentral.add(etiquetaEntrada, gbc);

        gbc.gridy = 1;
        panelCentral.add(new JScrollPane(areaEntrada), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCentral.add(botonIntercambiar, gbc);

        gbc.gridy = 2;
        panelCentral.add(botonAccion, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        etiquetaSalida = crearEtiqueta("Texto cifrado");
        panelCentral.add(etiquetaSalida, gbc);

        gbc.gridy = 1;
        panelCentral.add(new JScrollPane(areaSalida), gbc);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // 🎨 Helpers UI

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 14));
        return etiqueta;
    }

    private JTextArea crearAreaTexto() {
        JTextArea area = new JTextArea(10, 20);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Consolas", Font.PLAIN, 14));
        return area;
    }

    // 🔄 Cambio de modo
    private void alternarModo() {
        modoCifrado = !modoCifrado;

        String temp = areaEntrada.getText();
        areaEntrada.setText(areaSalida.getText());
        areaSalida.setText(temp);

        botonAccion.setText(modoCifrado ? "Cifrar" : "Descifrar");

        if (modoCifrado) {
            etiquetaEntrada.setText("Texto normal");
            etiquetaSalida.setText("Texto cifrado");
        } else {
            etiquetaEntrada.setText("Texto cifrado");
            etiquetaSalida.setText("Texto normal");
        }
    }

    // 🔁 UI dinámica
    private void actualizarUIPorCifrado() {
    String seleccionado = (String) selectorCifrado.getSelectedItem();

    switch (seleccionado) {

        case "César":
            etiquetaParametro.setText("Desplazamiento:");
            campoParametro.setVisible(true);
            etiquetaDescripcion.setText("Cada letra se reemplaza desplazándola un número fijo de posiciones en el alfabeto, manteniendo el orden y aplicando aritmética modular.");
            break;

        case "Atbash":
            campoParametro.setVisible(false);
            etiquetaParametro.setText("");
            etiquetaDescripcion.setText("Cada letra se sustituye por su opuesta en el alfabeto, invirtiendo completamente el orden, generando un cifrado simple y totalmente simétrico.");
            break;

        case "Vigenere":
            etiquetaParametro.setText("Clave:");
            campoParametro.setVisible(true);
            etiquetaDescripcion.setText("Se utiliza una clave repetida para desplazar cada letra del texto, combinando múltiples cifrados César y generando un patrón polialfabético más seguro.");
            break;

        case "Rail Fence":
            etiquetaParametro.setText("Rieles:");
            campoParametro.setVisible(true);
            etiquetaDescripcion.setText("El texto se escribe en zig-zag sobre varios rieles y luego se lee fila por fila, alterando el orden original de los caracteres.");
            break;

        case "Playfair":
            etiquetaParametro.setText("Clave:");
            campoParametro.setVisible(true);
            etiquetaDescripcion.setText("El texto se divide en pares de letras y se transforma usando una matriz 5x5, aplicando reglas según fila, columna o rectángulo.");
            break;
    }

    campoParametro.revalidate();
    campoParametro.repaint();
}

    private void procesarTexto() {
        try {
            String tipo = (String) selectorCifrado.getSelectedItem();
            String texto = areaEntrada.getText();
            String parametro = campoParametro.getText();

            String resultado = com.uce.services.ServicioCifrado.procesar(
                    tipo,
                    texto,
                    parametro,
                    modoCifrado
            );

            areaSalida.setText(resultado);

        } catch (NumberFormatException e) {
            mostrarError("El parámetro debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        } catch (Exception e) {
            mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainUI::new);
    }
}