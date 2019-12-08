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

    @Override
    public boolean equals(Object obj) {

        // Si apuntan a la misma dirección de memoria
        if (this == obj) return (true);

        // Si el objeto con el que se compara apunta a null
        if (obj == null) return (false);

        // Si no pertenecen a la misma clase
        if (getClass() != obj.getClass()) return (false);

        // Se referencia el objeto a comparar mediante un objeto de la misma clase, para poder
        // llamar a sus métodos
        final Inmunidad otro = (Inmunidad) obj;

        // Si los nombres del botón son el mismo
        return (this.getPropiedad() == otro.getPropiedad());

    } /* Fin del método equals */
}
