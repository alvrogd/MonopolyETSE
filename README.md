# MonopolyETSE

## Introducción

Este proyecto ha sido realizado para aprender de forma didáctica el paradigma de __Programación Orientada a Objetos__, sin conocimientos previos sobre él ni sobre buenas prácticas de diseño de software.

Su desarrollo ha consistido en desarrollar una __implementación personalizada__ del famoso juego de mesa __Monopoly__. A continuación, se detallan los cambios más destacables respecto al juego original, aunque es posible consultar, en los PDFs adjuntos del directorio _Guiones_, todas sus características.

## Extensiones añadidas

### Creación del tablero

A la hora de iniciar el juego, se permite al usuario __crear su propio tablero__, pudiendo repartir las casillas de cada tipo a su gusto, y configurando propiedades como el _nombre_, el _alquiler_ o la _imagen que la representa_.

En caso de que el usuario no desee crear un tablero personalizado, tiene la opción de jugar con el predeterminado de la aplicación.

### Avatares en modo avanzado

En el juego se disponen de __cuatro tipos de avatares__: _esfinge_, _sombrero_, _coche_ y _pelota_. Estos son indistinguibles en su modo de juego __normal__ pero, cuando se activa el modo __avanzado__, sus movimientos se ven alterados.

El __modo avanzado__ puede ser activado o desactivado, a voluntad de un jugador, cada jugador al inicio de su turno correspondiente. Además, puede haber varios jugadores con el mismo tipo de avatar. De forma resumida cada uno de los siguiente avatares tiene las siguientes propiedades en su __modo avanzado:__

1. __Esfinge:__ realiza un movimiento en zigzag entre la zona norte y sur del tablero.
2. __Sombrero:__ realiza un movimiento en zigzag entre la zona oeste y este del tablero.
3. __Coche:__ permite tirar el dado varias veces en el mismo turno, mientras se obtenga un valor mínimo en él.
4. __Pelota:__ permite interactuar con más de una de las casillas por las que se pasa en una tirada.

### Tratos

Una de las funcionalidades añadidas al proyecto es la de que los propios jugadores puedan __realizar _tratos_ entre ellos__.

Estos acuerdos permiten el traspaso de un número arbitrario de __dinero__, __propiedades__, e __inmunidades__ con las que evitar el pago de determinados alquileres, entre el jugador emisor y el receptor.

## ¿Cómo se gana el juego?

Un __jugador pierde el juego cuando se queda sin dinero__, o en balance negativo directamente. Una vez un jugador alcanza dicho estado, no tiene la posibilidad de hipotecar o intercambiar propiedades con otro jugador para recuperarse.

Así, el juego lo ganará el __último jugador que quede con dinero__ en la partida.

## ¿Cómo ejecutar el juego?

El proyecto se ha realizado con __Java 8__ utilizando la tecnología __JavaFX__, y se dispone en forma de un fichero _.jar_ ejecutable.

Para la ejecución en Linux, se deberá navegar al directorio _MonopolyETSE/dist/_, y ejecutar el siguiente comando.

```
java -jar MonopolyETSE_Entrega_v4.jar
```

## Trabajo futuro

Dado que el proyecto fue realizado como parte práctica de la asignatura __Programación Orientada a Objetos__ del __Grado de Ingeniería Informática__ de la __USC (Universidad de Santiago de Compostela)__, no fue posible añadir todas las características esperadas para un juego de ordenador completo.

Concretamente, sería de interés implementar en un futuro las siguientes funcionalidades para mejorar el proyecto:

- Carga de guardado de tableros personalizados
- Carga y guardado de partidas.
- Mejora de los tratos.
