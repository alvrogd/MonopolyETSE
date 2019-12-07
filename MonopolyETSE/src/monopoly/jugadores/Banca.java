package monopoly.jugadores;

public class Banca extends Participante {

    /* Constructor */

    public Banca() {

        // Se da una gran cantidad de dinero a la banca
        super("Banca", Integer.MAX_VALUE / 2 + Integer.MAX_VALUE / 4);
    }
}
