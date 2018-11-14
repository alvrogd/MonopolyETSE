package monopoly.jugadores;

import monopoly.Constantes;
import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;
import aplicacion.salidaPantalla.Output;
import monopoly.tablero.TipoGrupo;

import java.util.ArrayList;
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
    // Veces caídas en casa casilla
    private ArrayList<Integer> vecesCaidasEnCasillas;

    // Representación ASCII en el dibujado del tablero
    private final char identificador;
    // Uno de los cuatro tipos de avatares disponibles
    private final TipoAvatar tipo;

    // Moverse de casilla en casilla o empleando el movimiento especial del tipo de avatar
    private boolean movimientoEstandar;


    /* Constructores */

    /**
     * Constructor diseñado para crear el avatar de la Banca al inicializar el juego
     *
     * @param jugador jugador que sea la Banca
     */
    public Avatar(Jugador jugador) {

        if (jugador == null) {
            System.err.println("Error: jugador no inicializado.");
            System.exit(1);
        }

        this.jugador = jugador;
        this.tablero = null;

        haEstadoCarcel = false;
        encarcelado = false;
        turnosEnCarcel = 0;

        vueltas = 0;
        posicion = null;
        vecesCaidasEnCasillas = null;

        identificador = 'B';

        tipo = TipoAvatar.banca;

        movimientoEstandar = true;

    } /* Fin del constructor para la banca */


    /**
     * Constructor que crea el avatar de un jugador normal
     *
     * @param jugador        jugador cuyo avatar se va a crear
     * @param tablero        tablero del juego
     * @param tipo           tipo de avatar a crear
     * @param casillaInicial casilla en la que posicionar el avatar del jugador
     */
    public Avatar(Jugador jugador, Tablero tablero, TipoAvatar tipo, Casilla casillaInicial) {

        if (jugador == null) {
            System.err.println("Jugador no inicializado.");
            System.exit(1);
        }

        if (tablero == null) {
            System.err.println("Tablero no inicializado.");
            System.exit(1);
        }

        if (tipo == null) {
            System.err.println("Tipo de avatar no inicializado.");
            System.exit(1);
        }

        if (casillaInicial == null) {
            System.err.println("Casilla inicial no inicializada.");
            System.exit(1);
        }

        this.jugador = jugador;

        this.tablero = tablero;

        haEstadoCarcel = false;
        encarcelado = false;
        turnosEnCarcel = 0;

        vueltas = 0;
        posicion = casillaInicial;
        vecesCaidasEnCasillas = new ArrayList<>();
        for (int i = 0; i < 40; i++)
            vecesCaidasEnCasillas.add(0);

        // Identificador aleatorio y único
        Random random = new Random();
        boolean identificadorUnico;
        char identificadorAleatorio;
        Collection<Avatar> avatares = tablero.getAvataresContenidos().values();

        do {
            identificadorAleatorio = (char) (random.nextInt(127 - 32) + 32);    // Carácter ASCII del 32 al 126
            identificadorUnico = true;
            for (Avatar avatar : avatares) {
                if (avatar.getIdentificador() == identificadorAleatorio)
                    identificadorUnico = false;
            }
        } while (!identificadorUnico);

        identificador = identificadorAleatorio;

        casillaInicial.getAvataresContenidos().put(identificador, this);

        this.tipo = tipo;

        movimientoEstandar = true;

    }



    /* Getters y setters */

    public Jugador getJugador() {
        return jugador;
    }


    /* No se implementa el setter de jugador dado que es una constante */


    public Tablero getTablero() {
        return tablero;
    }


    /* No se implementa el setter de tablero dado que es una constante */


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
            Output.sugerencia("El número turnos en la cárcel de un avatar no puede ser menor a 0.");
            return;
        }
        this.turnosEnCarcel = turnosEnCarcel;
    }


    public int getVueltas() {
        return (vueltas);
    }


    public void setVueltas(int vueltas) {

        if (vueltas < 0) {
            Output.sugerencia("El número de vueltas de un avatar no puede ser menor a 0.");
            return;
        }

        this.vueltas = vueltas;
    }


    public Casilla getPosicion() {
        return (posicion);
    }


    public void setPosicion(Casilla posicion) {

        if (posicion == null) {
            System.err.println("Casilla no inicializada.");
            return;
        }

        this.posicion = posicion;

    }

    public ArrayList<Integer> getVecesCaidasEnCasillas() {
        return vecesCaidasEnCasillas;
    }

    public void setVecesCaidasEnCasillas(ArrayList<Integer> vecesCaidasEnCasillas) {

        if (vecesCaidasEnCasillas == null) {
            System.err.println("ArrayList no inicializado.");
            return;
        }

        this.vecesCaidasEnCasillas = vecesCaidasEnCasillas;
    }

    public char getIdentificador() {
        return (identificador);
    }


    /* No se implementa el setter de identificador dado que es una constante */


    public TipoAvatar getTipo() {
        return (tipo);
    }


    /* No se implementa el setter de tipo dado que es una constante */


    public boolean isMovimientoEstandar() {
        return (movimientoEstandar);
    }


    public void setMovimientoEstandar(boolean movimientoEstandar) {
        this.movimientoEstandar = movimientoEstandar;
    }



    /* Métodos */

    /**
     * En caso de ser posible, el jugador del avatar paga el importe correspondiente y en desencarcelado
     */
    public void salirCarcel() {

        if (!isEncarcelado()) {
            Output.sugerencia("El avatar no se encuentra en la cárcel.");
            return;
        }

        if( getTablero().getJuego().isHaLanzadoDados() ) {
            Output.sugerencia("No se puede salir de la cárcel después de haber lanzado los dados");
            return;
        }

        if (getJugador().balanceNegativoTrasPago(Constantes.DINERO_CARCEL)) {
            Output.respuesta("El jugador no dispone de suficiente liquidez como para salir de la cárcel.");
            return;
        }

        Output.sugerencia("Se pagará el importe correspondiente para salir de la cárcel.");
        getJugador().pagar(getTablero().getBanca(), Constantes.DINERO_CARCEL);

        setEncarcelado(false);

    }

    /**
     * Se fuerza la salida de la cárcel, incluso si no se dispone de suficiente liquidez, cayendo el jugador del avatar
     * en la bancarrota en dicho caso
     */
    public void forzarSalirCarcel() {

        if (!isEncarcelado()) {
            Output.sugerencia("El avatar no se encuentra en la cárcel.");
            return;
        }

        getJugador().pagar(getTablero().getBanca(), Constantes.DINERO_CARCEL);

        setEncarcelado(false);

    }


    /**
     * Mueve al jugador de un avatar, en caso de no estar encarcelado, o estarlo y sacar dobles; si se ha estado en la
     * cárcel el número máximo de turnos permitidos, se fuerza su salida
     *
     * @param numeroCasillas cantidad de casillas a moverse
     * @param dobles         si los dados han dado el mismo valor
     */
    public void mover(int numeroCasillas, boolean dobles) {

        if (numeroCasillas < 2) {
            Output.sugerencia("El número sacado en una tirada no puede ser menor que 2.");
            return;
        }

        // Si está en la cárcel y no ha sacado dobles
        if (isEncarcelado()) {

            if (!dobles) {
                Output.sugerencia("No se puede salir de la cárcel sin sacar dobles.");
                setTurnosEnCarcel(getTurnosEnCarcel() + 1);

                // Si ya ha estado tres turnos en la cárcel, se fuerza su salida
                if (getTurnosEnCarcel() == Constantes.MAX_TURNOS_CARCEL) {
                    Output.sugerencia("Has estado en la cárcel el número máximo de turnos permitidos.",
                            "Se pagará el importe correspondiente para salir de la cárcel.");

                    forzarSalirCarcel();

                    // Si el jugador ha caído en bancarrota tras el pago, debe notificarse
                    if (getJugador().isEstaBancarrota())
                        getTablero().getJuego().jugadorEnBancarrota(getJugador());

                    // No se debe mover en el turno actual dado que acaba de pagar el importe para salir de la cárcel
                    return;
                }
                // En caso contrario, no se hace nada
                else
                    return;
            } else
                setEncarcelado(false);


        }

        // Se elimina el avatar del listado de avatares contenidos en la casilla actual
        getPosicion().getAvataresContenidos().remove(getIdentificador());

        // Se calcula la posición nueva del avatar
        int posicionFinal = getPosicion().getPosicionEnTablero() + numeroCasillas;

        // Y se establece
        setPosicion(getTablero().getCasillas().get((posicionFinal / 10) % 4).get(posicionFinal % 10));

        // Si ha pasado por la casilla de salida
        if (posicionFinal >= 40)
            pasarPorSalida();

        // Se actualiza el número de veces que ha caído en la casilla
        getVecesCaidasEnCasillas().set(posicionFinal % 40, getVecesCaidasEnCasillas().get(posicionFinal % 40) + 1);
        // Y se añade el avatar al listado de avatares contenidos en la nueva casilla
        getPosicion().getAvataresContenidos().put(getIdentificador(), this);

        // En función del tipo de casilla
        switch (posicion.getGrupo().getTipo()) {

            case suerte:
                // acción asociada a la casilla de suerte
                Output.respuesta("Has caído en una casilla de suerte.");
                break;

            case comunidad:
                // acción asociada a la casilla de comunidad
                Output.respuesta("Has caído en una casilla de comunidad.");
                break;

            case impuesto1:
                Output.respuesta("Has caído en la primera casilla de impuestos.");
                caerEnImpuesto1();
                break;

            case impuesto2:
                Output.respuesta("Has caído en la segunda casilla de impuestos.");
                caerEnImpuesto2();
                break;

            case transporte:
                Output.respuesta("Has caído en una casilla de transporte.");
                caerEnTransporte();
                break;

            case servicios:
                Output.respuesta("Has caído en una casilla de servicio.");
                caerEnServicio(numeroCasillas);
                break;

            case carcel:
                // acción asociada a la casilla de cárcel
                Output.respuesta("Has caído en la casilla de visita de la cárcel.");
                break;

            case irCarcel:
                Output.respuesta("Has caído en la casilla de ir a la cárcel.");
                caerEnIrACarcel();
                break;

            case parking:
                Output.respuesta("Has caído en la casilla del parking.");
                caerEnParking();
                break;

            case salida:
                Output.respuesta("Has caído en la casilla de salida.");
                break;

            default:
                Output.respuesta("Has caído en una casilla de un solar.");
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


    /**
     * En caso de que la casilla pertenezca a otro jugador, el jugador del avatar paga el correspondiente importe
     */
    private void caerEnTransporte() {

        // Si ha caído en una casilla que no es comprable dado que la tiene otro jugadror
        if (!getPosicion().isComprable() && !getPosicion().getPropietario().equals(this.getJugador())) {

            getJugador().pagar(getPosicion().getPropietario(), (int) (getPosicion().getAlquiler() *
                    getPosicion().getPropietario().numeroCasillasObtenidas(TipoGrupo.transporte) * 0.25));

            // Si el jugador ha caído en bancarrota tras el pago, debe notificarse
            if (getJugador().isEstaBancarrota())
                getTablero().getJuego().jugadorEnBancarrota(getJugador());

        }

    }


    /**
     * En caso de que la casilla pertenezca a otro jugador, el jugador del avatar paga el correspondiente importe
     */
    private void caerEnServicio(int numeroCasillas) {

        // Si ha caído en una casilla que no es comprable dado que la tiene otro jugadror
        if (!getPosicion().isComprable() && !getPosicion().getPropietario().equals(this.getJugador())) {

            int multiplicador = (getPosicion().getPropietario().numeroCasillasObtenidas(TipoGrupo.servicios) == 1) ? 4 : 10;

            getJugador().pagar(getPosicion().getPropietario(), numeroCasillas * Constantes.FACTOR_SERVICIO *
                    multiplicador);

            // Si el jugador ha caído en bancarrota tras el pago, debe notificarse
            if (getJugador().isEstaBancarrota())
                getTablero().getJuego().jugadorEnBancarrota(getJugador());

        }

    }


    /**
     * En caso de que la casilla pertenezca a otro jugador, el jugador del avatar paga el correspondiente importe
     */
    private void caerEnSolar() {

        // Si ha caído en una casilla que no es comprable dado que la tiene otro jugadror
        if (!getPosicion().isComprable() && !getPosicion().getPropietario().equals(this.getJugador())) {

            int multiplicador = getPosicion().getPropietario().haObtenidoSolaresGrupo(getPosicion().getGrupo()) ? 2 : 1;

            getJugador().pagar(getPosicion().getPropietario(), getPosicion().getAlquiler() * multiplicador);

            // Si el jugador ha caído en bancarrota tras el pago, debe notificarse
            if (getJugador().isEstaBancarrota())
                getTablero().getJuego().jugadorEnBancarrota(getJugador());

        }

    }


    /**
     * El jugador del avatar paga el correspondiente importe y se añade al montón que un jugador obtendrá al caer su
     * avatar en el parking
     */
    private void caerEnImpuesto1() {

        getJugador().pagar(getTablero().getBanca(), Constantes.IMPUESTO_1);
        // Se añade la cantidad pagada al "alquiler" del parking
        final Casilla parking = getTablero().getCasillas().get(Constantes.POSICION_PARKING / 10).get(
                Constantes.POSICION_PARKING % 10);
        parking.setAlquiler(parking.getAlquiler() + Constantes.IMPUESTO_1);

        // Si el jugador ha caído en bancarrota tras el pago, debe notificarse
        if (getJugador().isEstaBancarrota())
            getTablero().getJuego().jugadorEnBancarrota(getJugador());

    }


    /**
     * El jugador del avatar paga el correspondiente importe y se añade al montón que un jugador obtendrá al caer su
     * avatar en el parking
     */
    private void caerEnImpuesto2() {

        getJugador().pagar(getTablero().getBanca(), Constantes.IMPUESTO_2);
        // Se añade la cantidad pagada al "alquiler" del parking
        final Casilla parking = getTablero().getCasillas().get(Constantes.POSICION_PARKING / 10).get(
                Constantes.POSICION_PARKING % 10);
        parking.setAlquiler(parking.getAlquiler() + Constantes.IMPUESTO_2);

        // Si el jugador ha caído en bancarrota tras el pago, debe notificarse
        if (getJugador().isEstaBancarrota())
            getTablero().getJuego().jugadorEnBancarrota(getJugador());

    }


    /**
     * Se mueve al avatar del jugador hasta la cárcel y se encarcela
     */
    public void caerEnIrACarcel() {

        // Se elimina el avatar del listado de avatares contenidos en la casilla actual
        getPosicion().getAvataresContenidos().remove(getIdentificador());

        setPosicion(getTablero().getCasillas().get(Constantes.POSICION_CARCEL / 10).get(Constantes.POSICION_CARCEL % 10));
        setEncarcelado(true);
        setHaEstadoCarcel(true);

        // Y se añade el avatar al listado de avatares contenidos en la cárcel
        getPosicion().getAvataresContenidos().put(getIdentificador(), this);

        Output.mensaje(
                "          ,",
                " __  _.-\"` `'-." ,
                "/||\\'._ __{}_(" ,
                "||||  |'--.__\\" ,
                "|  L.(   ^_\\^     ¡Has sido encarcelado!" ,
                "\\ .-' |   _ |" ,
                "| |   )\\___/" ,
                "|  \\-'`:._]" ,
                "\\__/;      '-.", ""
        );

    }


    /**
     * El jugador del avatar obtiene la cantidad acumulada en el parking por el pago de impuestos
     */
    private void caerEnParking() {

        // Se añaden a la fortuna del jugador los impuestos recaudados
        final Casilla parking = getTablero().getCasillas().get(Constantes.POSICION_PARKING / 10).get(
                Constantes.POSICION_PARKING % 10);
        getJugador().setFortuna(getJugador().getFortuna() + parking.getAlquiler());

        Output.respuesta("¡Has cobrado el valor acumulado por los impuestos!");
        // Y se resetea el valor del "alquiler" del parking
        parking.setAlquiler(0);


    }


    /**
     * Se suma al jugador del avatar la cantidad asociada al paso por la casilla de salida en caso de no haber sido
     * encarcelado en la vuelta completada, se desencarcela al avatar y se resetea la cantidad de turnos pasados en la
     * cárcel a 0, se incrementa en 1 el número de vueltas completadas, y se llama al tablero para actualizar las
     * vueltas completadas por cada jugador
     */
    private void pasarPorSalida() {

        // Si no ha estado en la carcel, se le suma el correspondiente importe a su fortuna
        if (!isHaEstadoCarcel()) {
            getJugador().setFortuna(getJugador().getFortuna() + Constantes.DINERO_SALIDA);
            Output.respuesta("Has cobrado el importe de la casilla de salida.");
        }
        setHaEstadoCarcel(false);
        setTurnosEnCarcel(0);
        setVueltas(getVueltas() + 1);
        getTablero().getJuego().actualizarVueltas();

    }

}