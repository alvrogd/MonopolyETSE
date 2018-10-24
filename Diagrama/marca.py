#!/usr/bin/env python
# coding=utf-8

# Paquete necesario para leer los argumentos obtenidos por línea de comandos
import sys

# El archivo de entrada es el nombre dado más la extesión .svg
entrada = sys.argv[1] + ".svg"
# El archivo de salida cuenta con "_arreglado" antes de la extensión
salida = sys.argv[1] + "_arreglado.svg"


def eliminarStrings():

    # Strings a eliminar
    listaStrings = ["UNREGISTERED"]
    # Variable para el archivo de entrada
    archivoEntrada = open(entrada)
    # Variable para el archivo de salida, en modo de escritura
    archivoSalida = open(salida, "w")

    # Para cada línea en el archivo de entrada
    for linea in archivoEntrada:
        # Cada string a eliminar
        for palabra in listaStrings:
            # Es sustituida en la linea por ""
            linea = linea.replace(palabra, "")
        # Se escribe la línea en el fichero de salida
        archivoSalida.write(linea)

    # Se cierran los archivos de entrada y salida
    archivoEntrada.close()
    archivoSalida.close()


eliminarStrings()
