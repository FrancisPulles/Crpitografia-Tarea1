# 🔐 Sistema de Cifrado - README

## 📌 Descripción General

Este proyecto es una aplicación de escritorio desarrollada en Java que permite **cifrar y descifrar texto** utilizando diferentes algoritmos clásicos de criptografía.

La aplicación cuenta con una interfaz gráfica (Swing) donde el usuario puede:

* Seleccionar un método de cifrado
* Ingresar texto
* Definir parámetros (clave, desplazamiento, etc.)
* Obtener el resultado cifrado o descifrado

---

## 🧩 Cifrados Implementados

### 🔸 César

Desplaza cada letra del texto un número fijo de posiciones en el alfabeto.
Ejemplo: A → D (con desplazamiento 3)

* Requiere: número entero (1–25)
* Tipo: sustitución simple

---

### 🔸 Atbash

Invierte el alfabeto:
A ↔ Z, B ↔ Y, C ↔ X, etc.

* No requiere parámetros
* Tipo: sustitución simétrica

---

### 🔸 Vigenere

Utiliza una **clave** para aplicar múltiples desplazamientos tipo César.

* Requiere: clave de texto
* Tipo: cifrado polialfabético

---

### 🔸 Rail Fence

Reorganiza el texto en forma de **zig-zag** sobre varios niveles (rieles).

* Requiere: número de rieles (≥ 2)
* Tipo: transposición

---

### 🔸 Playfair

Cifra el texto en **pares de letras** usando una matriz 5x5 basada en una clave.

* Requiere: clave de texto
* Tipo: sustitución por pares

---

## ⚙️ Componentes del Sistema

### 🔹 Cifradores (`Cifrador`)

Interfaz base que define:

* `cifrar(texto)`
* `descifrar(texto)`

Cada algoritmo implementa esta interfaz.

---

### 🔹 Fábrica de Cifradores (`FabricaCifradores`)

Se encarga de:

* Crear dinámicamente el cifrador correcto según el tipo seleccionado
* Pasar los parámetros necesarios a cada algoritmo

---

### 🔹 Servicio de Cifrado (`ServicioCifrado`)

Orquesta todo el proceso:

1. Valida entrada
2. Normaliza texto
3. Valida parámetros
4. Crea el cifrador
5. Ejecuta cifrado o descifrado

---

### 🔹 Validador de Entrada (`ValidadorEntrada`)

Verifica que los parámetros sean correctos según el cifrado:

* César → número entre 1 y 25
* Rail Fence → mínimo 2 rieles
* Vigenere / Playfair → clave no vacía

---

### 🔹 Normalizador de Texto (`NormalizadorTexto`)

Preprocesa el texto antes de cifrar:

* Convierte a mayúsculas
* Elimina tildes (Á → A, etc.)
* Reemplaza Ñ → N
* Elimina caracteres no alfabéticos (excepto espacios)

---

## 🖥️ Interfaz Gráfica (`InterfazPrincipal`)

Permite al usuario:

* Seleccionar el tipo de cifrado
* Ingresar texto de entrada
* Definir parámetros dinámicos
* Alternar entre modo cifrar / descifrar
* Visualizar resultados

Incluye:

* Validación de errores
* Actualización dinámica de campos según el cifrado
* Intercambio rápido entre entrada y salida

---

## 🔄 Flujo de Ejecución

1. Usuario ingresa texto y selecciona cifrado
2. Se valida y normaliza la entrada
3. Se valida el parámetro correspondiente
4. Se crea el cifrador adecuado
5. Se ejecuta cifrado o descifrado
6. Se muestra el resultado en pantalla

---

## 🎯 Objetivo del Proyecto

Demostrar la implementación de distintos algoritmos clásicos de cifrado, aplicando:

* Principios de diseño (interfaces, fábrica, separación de responsabilidades)
* Validación y procesamiento de datos
* Interfaz gráfica en Java

---

