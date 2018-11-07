package monopoly;

import java.util.Random;

public class Dado {

    // Generador de números aleatorios
    private Random random;


    /* Constructor */
    public Dado() {
        random = new Random();
    }

    /**
     * Se devuelve un número entero pseudoaleatorio entre 1 y 6
     * @return número entero
     */
    // No puede ser un método estático dado que la clase Random no lo es
    public int lanzarDado() {
        return (random.nextInt(6) + 1);
    }

}
