package monopoly.jugadores.acciones;

import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Propiedad;

public class Movimiento implements IAccionJugador {

    /* Atributos */

    private Jugador jugador;
    private Casilla casillaOrigen;
    private Casilla casillaDestino;



    /* Constructor */

    public Movimiento(Jugador jugador, Casilla casillaOrigen, Casilla casillaDestino) {

        if (jugador == null) {
            System.err.println("Jugador no inicializado");
            System.exit(1);
        }

        if (casillaOrigen == null) {
            System.err.println("Casilla de origen no inicializada");
            System.exit(1);
        }

        if (casillaDestino == null) {
            System.err.println("Casilla de destino no inicializada");
            System.exit(1);
        }

        this.jugador = jugador;
        this.casillaOrigen = casillaOrigen;
        this.casillaDestino = casillaDestino;
    }



    /* Getters y setters */

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {

        if (jugador == null) {
            System.err.println("Jugador no inicializado");
            return;
        }

        this.jugador = jugador;
    }

    public Casilla getCasillaOrigen() {
        return casillaOrigen;
    }

    public void setCasillaOrigen(Casilla casillaOrigen) {

        if (casillaOrigen == null) {
            System.err.println("Casilla de origen no inicializada");
            return;
        }

        this.casillaOrigen = casillaOrigen;
    }

    public Casilla getCasillaDestino() {
        return casillaDestino;
    }

    public void setCasillaDestino(Casilla casillaDestino) {

        if (casillaDestino == null) {
            System.err.println("Casilla de destino no inicializada");
            return;
        }

        this.casillaDestino = casillaDestino;
    }



    /* Métodos */

    @Override
    public void revertirAccion() {

        // Se decrementa la frecuencia de la casilla de destino
        getCasillaDestino().setFrecuencia(getCasillaDestino().getFrecuencia() - 1);

        // Se decrementa el número de veces que el jugador ha caído en la casilla de destino
        final Avatar avatar = getJugador().getAvatar();
        avatar.getVecesCaidasEnCasillas().set(getCasillaDestino().getPosicionEnTablero(),
                avatar.getVecesCaidasEnCasillas().get(getCasillaDestino().getPosicionEnTablero()) - 1);

        // Si además la casilla de destino es propiedad del jugador, se decrementa el número de veces caído en ella
        // como propiedad
        if (getCasillaDestino() instanceof Propiedad) {

            final Propiedad propiedad = (Propiedad) getCasillaDestino();

            if (propiedad.getPropietario().equals(getJugador()))
                avatar.getVecesCaidasEnPropiedades().set(getCasillaDestino().getPosicionEnTablero(),
                        avatar.getVecesCaidasEnPropiedades().get(getCasillaDestino().getPosicionEnTablero()) - 1);
        }

        // Se elimina al avatar de la lista de avatares contenidos en la casilla de destino
        getCasillaDestino().getAvataresContenidos().remove(avatar.getIdentificador());

        // Y se añade a la lista de avatares contenidos en la casilla de origen
        getCasillaOrigen().getAvataresContenidos().put(avatar.getIdentificador(), avatar);
    }
}
