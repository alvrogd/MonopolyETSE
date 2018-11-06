package monopoly.jugadores;

import monopoly.Constantes;
import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;
import aplicacion.salidaPantalla.Output;

import java.util.Collection;
import java.util.Random;

public class Avatar {

    /* Atributos */

    // Jugador al que pertenece
    private final Jugador jugador;

    // Tablero en el que se encuentra
    private final Tablero tablero;

    // Se resetea a false al completar una vuelta
    private boolean haEstadoCarcel;
    // Si se encuentra en la cárcel actualmente
    private boolean encarcelado;
    // Número de turnos que ha pasado en la cárcel
    private int turnosEnCarcel;

    // Vueltas completadas en el tablero
    private int vueltas;
    // Casilla actual
    private Casilla posicion;

    // Representación ASCII en el dibujado del tablero
    private final char identificador;
    // Uno de los cuatro tipos de avatares disponibles
    private final TipoAvatar tipo;

    // Moverse de casilla en casilla o empleando el movimiento especial del tipo de avatar
    private boolean movimientoEstandar;


    /* Constructores */
    public Avatar(Jugador jugador) {

        if (jugador == null) {
            Output.errorComando("Error: jugador no inicializado.");
            System.exit(1);
        }

        this.jugador = jugador;
        this.tablero = null;

        haEstadoCarcel = false;
        encarcelado = false;
        turnosEnCarcel = 0;

        vueltas = 0;
        posicion = null;

        identificador = 'B';

        tipo = TipoAvatar.banca;

        movimientoEstandar = true;

    } /* Fin del constructor para la banca */

    public Avatar(Jugador jugador, Tablero tablero, TipoAvatar tipo, Casilla casillaInicial) {

        if (jugador == null) {
            Output.errorComando("Error: jugador no inicializado.");
            System.exit(1);
        }

        if (tablero == null) {
            Output.errorComando("Error: tablero no inicializado.");
            System.exit(1);
        }

        if (tipo == null) {
            Output.errorComando("Error: tipo de avatar no inicializado.");
            System.exit(1);
        }

        if (casillaInicial == null) {
            System.out.println("Error: casilla inicial no inicializada.");
            System.exit(1);
        }

        this.jugador = jugador;

        this.tablero = tablero;

        haEstadoCarcel = false;
        encarcelado = false;
        turnosEnCarcel = 0;

        vueltas = 0;
        posicion = casillaInicial;

        // Identificador aleatorio y único
        Random random = new Random();
        boolean identificadorUnico;
        char identificadorAleatorio;
        Collection<Avatar> avatares = tablero.getAvataresContenidos().values();

        do {
            identificadorAleatorio = (char) (random.nextInt(256 - 32) + 32);    // Carácter ASCII del 32 al 255
            identificadorUnico = true;
            for (Avatar avatar : avatares) {
                if (avatar.getIdentificador() == identificadorAleatorio)
                    identificadorUnico = false;
            }
        } while (!identificadorUnico);

        identificador = identificadorAleatorio;

        casillaInicial.getAvataresContenidos().put(jugador.getNombre(), this);

        this.tipo = tipo;

        movimientoEstandar = true;

    }



    /* Getters y setters */

    public Jugador getJugador() {
        return jugador;
    }


    public Tablero getTablero() {
        return tablero;
    }


    public boolean isHaEstadoCarcel() {
        return (haEstadoCarcel);
    }


    public void setHaEstadoCarcel(boolean haEstadoCarcel) {
        this.haEstadoCarcel = haEstadoCarcel;
    }


    public boolean isEncarcelado() {
        return (encarcelado);
    }


    public void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }


    public int getTurnosEnCarcel() {
        return turnosEnCarcel;
    }


    public void setTurnosEnCarcel(int turnosEnCarcel) {

        if (turnosEnCarcel < 0) {
            Output.sugerencia("Error: el número turnos en la cárcel de un avatar no puede ser menor a 0.");
            return;
        }
        this.turnosEnCarcel = turnosEnCarcel;
    }


    public int getVueltas() {
        return (vueltas);
    }


    public void setVueltas(int vueltas) {

        if (vueltas < 0) {
            Output.sugerencia("Error: el número de vueltas de un avatar no puede ser menor a 0.");
            return;
        }

        this.vueltas = vueltas;
    }


    public Casilla getPosicion() {
        return (posicion);
    }


    public void setPosicion(Casilla posicion) {

        if (posicion == null) {
            Output.sugerencia("Error: casilla no inicializada.");
            return;
        }

        this.posicion = posicion;

    }


    public char getIdentificador() {
        return (identificador);
    }


    public TipoAvatar getTipo() {
        return (tipo);
    }


    public boolean isMovimientoEstandar() {
        return (movimientoEstandar);
    }


    public void setMovimientoEstandar(boolean movimientoEstandar) {
        this.movimientoEstandar = movimientoEstandar;
    }



    /* Métodos */

    public void salirCarcel() {

        if (!isEncarcelado()) {
            Output.sugerencia("Error: el avatar no se encuentra en la cárcel.");
            return;
        }

        setEncarcelado(false);

    }


    public void mover(int numeroCasillas, boolean dobles) {

        if (numeroCasillas < 2) {
            Output.sugerencia("Error: el número sacado en una tirada no puede ser menor que 2.");
            return;
        }

        // Si está en la cárcel y no ha sacado dobles
        if (isEncarcelado()) {

            if (!dobles) {
                Output.sugerencia("No se puede salir de la cárcel sin sacar dobles");
                setTurnosEnCarcel(getTurnosEnCarcel() + 1);

                // Si ya ha estado tres turnos en la cárcel, se fuerza su salida
                if (getTurnosEnCarcel() == 3) {
                    Output.sugerencia("Has estado en la cárcel el número máximo de turnos permitidos",
                            "Se pagará el importe correspondiente para salir de ella" );
                    getJugador().pagar(getTablero().getBanca(), Constantes.DINERO_CARCEL);
                    setEncarcelado(false);
                }
                // En caso contrario, no se hace nada
                else
                    return;
            } else
                setEncarcelado(false);


        }

        int posicionFinal = getPosicion().getPosicionEnTablero() + numeroCasillas;
        posicion = getTablero().getCasillas().get(posicionFinal / 10).get(posicionFinal % 10);
        // Si ha pasado por la casilla de salida
        if( posicionFinal >= 40 )
            pasarPorSalida();

        // En función del tipo de casilla
        switch (posicion.getGrupo().getTipo()) {

            case suerte:
                // acción asociada a la casilla de suerte
                Output.respuesta("Has caído en una casilla de suerte");
                break;

            case comunidad:
                // acción asociada a la casilla de comunidad
                Output.respuesta("Has caído en una casilla de comunidad");
                break;

            case impuesto1:
                Output.respuesta("Has caído en la primera casilla de impuestos");
                caerEnImpuesto1();
                break;

            case impuesto2:
                Output.respuesta("Has caído en la segunda casilla de impuestos");
                caerEnImpuesto2();
                break;

            case transporte:
                Output.respuesta("Has caído en una casilla de transporte");
                caerEnTransporte();
                break;

            case servicios:
                Output.respuesta("Has caído en una casilla de servicio");
                caerEnServicio(numeroCasillas);
                break;

            case carcel:
                // acción asociada a la casilla de cárcel
                Output.respuesta("Has caído en la casilla de visita de la cárcel");
                break;

            case irCarcel:
                Output.respuesta("Has caído en la casilla de ir a la cárcel");
                caerEnIrACarcel();
                break;

            case parking:
                Output.respuesta("Has caído en la casilla del parking");
                caerEnParking();
                break;

            case salida:
                Output.respuesta("Has caído en la casilla de salida");
                break;

            default:
                Output.respuesta("Has caído en una casilla de un solar");
                caerEnSolar();

        }

    }


    @Override
    public boolean equals(Object o) {

        // Si este objeto y el objeto pasado como parámetro apuntan a la misma dirección de memoria
        if (this == o) return (true);

            // Si el parámetro referencia a null
        else if (o == null) return (false);

        // Si el parámetro no es un objeto de tipo Avatar
        if (getClass() != o.getClass()) return (false);

        // Se referencia el objeto a comparar mediante un objeto de la misma clase, para poder llamar a sus métodos
        final Avatar otro = (Avatar) o;

        // Si el identificador es distinto; son el mismo objeto
        return (getIdentificador() == otro.getIdentificador());

    }


    private void caerEnTransporte() {

        // Si ha caído en una casilla que no es comprable dado que la tiene otro jugadror
        if (!getPosicion().isComprable()) {

            getJugador().pagar(getPosicion().getPropietario(), (int) (getPosicion().getAlquiler() *
                    getJugador().numeroTransportesObtenidos() * 0.25));

        }

    }


    private void caerEnServicio(int numeroCasillas) {

        // Si ha caído en una casilla que no es comprable dado que la tiene otro jugadror
        if (!getPosicion().isComprable()) {

            getJugador().pagar(getPosicion().getPropietario(), numeroCasillas * Constantes.FACTOR_SERVICIO);

        }

    }


    private void caerEnSolar() {

        // Si ha caído en una casilla que no es comprable dado que la tiene otro jugadror
        if (!getPosicion().isComprable()) {

            getJugador().pagar(getPosicion().getPropietario(), getPosicion().getAlquiler());

        }

    }

    private void caerEnImpuesto1() {

        getJugador().pagar(getTablero().getBanca(), Constantes.IMPUESTO_1);
        // Se añade la cantidad pagada al "alquiler" del parking
        final Casilla parking = getTablero().getCasillas().get(Constantes.POSICION_PARKING / 10).get(
                Constantes.POSICION_PARKING % 10);
        parking.setAlquiler(parking.getAlquiler() + Constantes.IMPUESTO_1);

    }

    private void caerEnImpuesto2() {

        getJugador().pagar(getTablero().getBanca(), Constantes.IMPUESTO_2);
        // Se añade la cantidad pagada al "alquiler" del parking
        final Casilla parking = getTablero().getCasillas().get(Constantes.POSICION_PARKING / 10).get(
                Constantes.POSICION_PARKING % 10);
        parking.setAlquiler(parking.getAlquiler() + Constantes.IMPUESTO_2);

    }

    private void caerEnIrACarcel() {

        setPosicion(getTablero().getCasillas().get(Constantes.POSICION_CARCEL / 10).get(Constantes.POSICION_CARCEL % 10));
        setEncarcelado(true);

        Output.sugerencia("¡Has sido encarcelado!");
    }


    private void caerEnParking() {

        // Se añaden a la fortuna del jugador los impuestos recaudados
        final Casilla parking = getTablero().getCasillas().get(Constantes.POSICION_PARKING / 10).get(
                Constantes.POSICION_PARKING % 10);
        getJugador().setFortuna(getJugador().getFortuna() + parking.getAlquiler());

        Output.respuesta("¡Has cobrado el valor acumulado por los impuestos!");
        // Y se resetea el valor del "alquiler" del parking
        parking.setAlquiler(0);



    }


    private void pasarPorSalida() {

        // Si no ha estado en la carcel, se le suma el correspondiente importe a su fortuna
        if (!isHaEstadoCarcel()) {
            getJugador().setFortuna(getJugador().getFortuna() + Constantes.DINERO_SALIDA);
            Output.respuesta("Has cobrado el importe de la casilla de salida");
        }
        setHaEstadoCarcel(false);
        setTurnosEnCarcel(0);


    }

}