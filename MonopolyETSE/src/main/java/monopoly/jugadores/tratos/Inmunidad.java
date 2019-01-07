package monopoly.jugadores.tratos;

import monopoly.jugadores.Jugador;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class Inmunidad {

    /* Atributos */

    final Propiedad propiedad;
    int numeroTurnos;



    /* Constructor */

    public Inmunidad(Propiedad propiedad, int numeroTurnos) {

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada");
            System.exit(1);
        }

        if (numeroTurnos < 0) {
            System.err.println("El número de turnos de inmunidad no puede ser negativo");
            System.exit(1);
        }

        this.propiedad = propiedad;
        this.numeroTurnos = numeroTurnos;
    }



    /* Getters y setters */

    public Propiedad getPropiedad() {
        return propiedad;
    }


    public int getNumeroTurnos() {
        return numeroTurnos;
    }


    public void setNumeroTurnos(int numeroTurnos) {

        if (numeroTurnos < 0) {
            System.err.println("El número de turnos de inmunidad no puede ser negativo");
            System.exit(1);
        }

        this.numeroTurnos = numeroTurnos;
    }

    @Override
    public String toString(){

        return("("+propiedad.getNombre()+", "+numeroTurnos+")");

    }
}
