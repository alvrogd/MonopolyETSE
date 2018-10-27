package monopoly;

import java.util.Random;

public class Dado {
    // Generador de números aleatorios
    private Random random;


    /* Constructor */
    public Dado() {
        random = new Random();
    }

    // No puede ser un método estático dado que la clase Random no lo es
    public int lanzarDado() {
        return (random.nextInt(6) + 1);
    }

}
