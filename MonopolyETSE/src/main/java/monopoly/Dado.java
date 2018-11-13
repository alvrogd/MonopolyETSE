package monopoly;

import java.util.Random;

public class Dado {

    // Generador de números aleatorios
    private Random random;


    /* Constructores */

    public Dado() {

        random = new Random();

    } /* Fin del constructor sin argumentos */


    public Dado(Random random) {

        if (random == null) {
            System.err.println("Random referencia a null");
            System.exit(1);
        }

        this.random = random;

    } /* Fin del constructor con un objeto de la clase Random */


    /* No se implementa el getter de random dado que la única finalidad de esta clase es generar un número aleatorio
     *   empleando dicho atributo
     */


    /* Método */

    /**
     * Se devuelve un número entero pseudoaleatorio entre 1 y 6
     *
     * @return número entero
     */
    // No puede ser un método estático dado que la clase Random no lo es
    public int lanzarDado() {
        return (random.nextInt(6) + 1);
    }

}
