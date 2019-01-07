package monopoly.jugadores.acciones;

import monopoly.jugadores.Participante;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

import java.util.ArrayList;
import java.util.Arrays;

public class TransferenciaPropiedad implements IAccionJugador {

    /* Atributos */

    private Participante emisor;
    private Participante receptor;
    private ArrayList<Propiedad> propiedades;



    /* Constructor */

    public TransferenciaPropiedad(Participante emisor, Participante receptor, Propiedad... propiedades) {

        if (emisor == null) {
            System.err.println("Emisor no inicializado");
            System.exit(1);
        }

        if (receptor == null) {
            System.err.println("Receptor no inicializado");
            System.exit(1);
        }

        if (propiedades == null) {
            System.err.println("Array de propiedades no inicializado");
            System.exit(1);
        }

        for (Propiedad propiedad : propiedades) {

            if (propiedad == null) {
                System.err.println("Propiedad no inicializada");
                System.exit(1);
            }
        }

        this.emisor = emisor;

        this.receptor = receptor;

        this.propiedades = new ArrayList<>();
        this.propiedades.addAll(Arrays.asList(propiedades));
    }



    /* Getters y setters */

    public Participante getEmisor() {
        return emisor;
    }

    public void setEmisor(Participante emisor) {

        if (emisor == null) {
            System.err.println("Emisor no inicializado");
            return;
        }

        this.emisor = emisor;
    }

    public Participante getReceptor() {
        return receptor;
    }

    public void setReceptor(Participante receptor) {

        if (receptor == null) {
            System.err.println("Receptor no inicializado");
            return;
        }

        this.receptor = receptor;
    }

    public ArrayList<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(ArrayList<Propiedad> propiedades) {

        if (propiedades == null) {
            System.err.println("Array de propiedades no inicializado");
            return;
        }

        for (Propiedad propiedad : propiedades) {

            if (propiedad == null) {
                System.err.println("Propiedad no inicializada");
                return;
            }
        }

        this.propiedades = propiedades;
    }



    /* Métodos */

    /**
     * Se deshace el intercambio de propiedades de un jugador a otro, restableciendo los propietarios de cada una al
     * jugador correspondiente
     */
    @Override
    public void revertirAccion() {

        // Para cada propiedad
        for( Propiedad propiedad : getPropiedades() ) {

            // Se devuelve la propiedad al emisor original
            propiedad.setPropietario(emisor);
            emisor.getPropiedades().add(propiedad);

            // Y se elimina de las propiedades del receptor
            receptor.getPropiedades().remove(propiedad);
        }
    }
}
