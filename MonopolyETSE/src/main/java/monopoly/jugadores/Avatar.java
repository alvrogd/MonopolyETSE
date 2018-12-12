package monopoly.jugadores;

import monopoly.Constantes;
import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;
import aplicacion.salidaPantalla.Output;
import monopoly.tablero.TipoGrupo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

public abstract class Avatar {

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
    // Si ha pasado por la casilla de salida
    private boolean haPasadoSalida;
    // Casilla actual
    private Casilla posicion;
    // Veces caídas en casa casilla
    private ArrayList<Integer> vecesCaidasEnCasillas;
    // Veces caídas en cada propiedad
    private ArrayList<Integer> vecesCaidasEnPropiedades;

    // Representación ASCII en el dibujado del tablero
    private final char identificador;

    // Moverse de casilla en casilla o empleando el movimiento especial del tipo de avatar
    private boolean movimientoEstandar;
    // Si se ha movido el total de casillas correspondientes a la tirada
    private boolean haMovidoCasillasTirada;
    // Cantidad de casillas que faltan por moverse
    private int casillasRestantesPorMoverse;


    /* Constructores */

    /**
     * Constructor que crea el avatar de un jugador normal
     *
     * @param jugador        jugador cuyo avatar se va a crear
     * @param tablero        tablero del juego
     * @param casillaInicial casilla en la que posicionar el avatar del jugador
     */
    public Avatar(Jugador jugador, Tablero tablero, Casilla casillaInicial) {

        if (jugador == null) {
            System.err.println("Jugador no inicializado.");
            System.exit(1);
        }

        if (tablero == null) {
            System.err.println("Tablero no inicializado.");
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
        haPasadoSalida = false;
        posicion = casillaInicial;
        vecesCaidasEnCasillas = new ArrayList<>();
        for (int i = 0; i < Constantes.NUMERO_CASILLAS; i++)
            vecesCaidasEnCasillas.add(0);
        vecesCaidasEnPropiedades = new ArrayList<>();
        for (int i = 0; i < Constantes.NUMERO_CASILLAS; i++)
            vecesCaidasEnPropiedades.add(0);

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

        movimientoEstandar = true;
        haMovidoCasillasTirada = true;
        casillasRestantesPorMoverse = 0;

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


    public ArrayList<Integer> getVecesCaidasEnPropiedades() {
        return vecesCaidasEnPropiedades;
    }


    public void setVecesCaidasEnPropiedades(ArrayList<Integer> vecesCaidasEnPropiedades) {

        if (vecesCaidasEnPropiedades == null) {
            System.err.println("ArrayList no inicializado.");
            return;
        }

        this.vecesCaidasEnPropiedades = vecesCaidasEnPropiedades;
    }


    public char getIdentificador() {
        return (identificador);
    }


    /* No se implementa el setter de identificador dado que es una constante */


    public boolean isMovimientoEstandar() {
        return (movimientoEstandar);
    }


    public void setMovimientoEstandar(boolean movimientoEstandar) {
        this.movimientoEstandar = movimientoEstandar;
    }


    public boolean ishaMovidoCasillasTirada() {
        return haMovidoCasillasTirada;
    }


    public void sethaMovidoCasillasTirada(boolean haMovidoCasillasTirada) {
        this.haMovidoCasillasTirada = haMovidoCasillasTirada;
    }


    public int getCasillasRestantesPorMoverse() {
        return casillasRestantesPorMoverse;
    }


    public void setCasillasRestantesPorMoverse(int casillasRestantesPorMoverse) {

        if (casillasRestantesPorMoverse < 0) {
            Output.sugerencia("El número de casillas restantes por moverse no puede ser menor a 0.");
            return;
        }
        this.casillasRestantesPorMoverse = casillasRestantesPorMoverse;
    }


    public boolean isHaPasadoSalida() {
        return haPasadoSalida;
    }


    public void setHaPasadoSalida(boolean haPasadoSalida) {
        this.haPasadoSalida = haPasadoSalida;
    }


    public boolean isHaMovidoCasillasTirada() {
        return haMovidoCasillasTirada;
    }


    public void setHaMovidoCasillasTirada(boolean haMovidoCasillasTirada) {
        this.haMovidoCasillasTirada = haMovidoCasillasTirada;
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

        if (getTablero().getJuego().isHaLanzadoDados()) {
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
        getJugador().incrementarPagoTasasEImpuestos(Constantes.DINERO_CARCEL);

        setEncarcelado(false);

    }


    /**
     * En función del valor obtenido en una tirada, se comprueba si el jugador puede moverse en el actual turno;
     * en caso de que no sea así, se actualiza el número de turnos pasados en la cárcel, y se fuerza la salida de ella
     * si se ha alcanzado el máximo de turnos permitidos
     *
     * @param dobles si los dados han dado el mismo valor
     * @return si el avatar puede moverse o no en la actual tirada
     */
    public boolean actualizarEncarcelamiento(boolean dobles) {

        // Si no ha sacado dobles
        if (!dobles) {

            Output.sugerencia("No se puede salir de la cárcel sin sacar dobles.");
            setTurnosEnCarcel(getTurnosEnCarcel() + 1);

            // Y, si ya ha estado tres turnos en la cárcel, se fuerza su salida
            if (getTurnosEnCarcel() == Constantes.MAX_TURNOS_CARCEL) {
                Output.sugerencia("Has estado en la cárcel el número máximo de turnos permitidos.",
                        "Se pagará el importe correspondiente para salir de la cárcel.");

                forzarSalirCarcel();

                // Si el jugador ha caído en bancarrota tras el pago, debe notificarse
                if (getJugador().isEstaBancarrota())
                    getTablero().getJuego().jugadorEnBancarrota(getJugador());

            }

            // Se indica que el avatar no debe moverse
            return (false);

        } else
            setEncarcelado(false);

        return (true);
    }


    /**
     * Se calcula la posición de la casilla a la que un avatar debe moverse, dado un número de casillas a avanzar; el
     * funcionamiento depende del modo de movimiento establecido actualmente
     *
     * @param numeroCasillas número de casillas a moverse
     * @return posición de la casilla destino
     */
    public abstract int calcularNuevaPosicion(int numeroCasillas);

    /**
     * Se actualiza la posición del avatar, dada la posición de la casilla a la que moverse; el funcionamiento depende
     * del modo de movimiento establecido actualmente
     *
     * @param posicionFinal         la posición de la casilla a la que moverse
     * @param cobrarSalida          si se debe cobrar el correspondiente importe en caso de pasar por la salida
     * @param importeSalidaEstandar si el importe a cobrar por pasar por la salida es el estándar o se ve modificado
     *                              por una carta obtenida
     */
    public void actualizarPosicion(int posicionFinal, boolean cobrarSalida, boolean importeSalidaEstandar) {

        // Se elimina el avatar del listado de avatares contenidos en la casilla actual
        getPosicion().getAvataresContenidos().remove(getIdentificador());

        // Se establece la nueva posición
        setPosicion(getTablero().getCasillas().get((posicionFinal / 10) % 4).get(posicionFinal % 10));

        // Si ha pasado por la casilla de salida
        if (isHaPasadoSalida())
            pasarPorSalida(cobrarSalida, importeSalidaEstandar);

        // Se actualiza el número de veces que ha caído en la casilla
        getVecesCaidasEnCasillas().set(posicionFinal % 40, getVecesCaidasEnCasillas().get(posicionFinal % 40) + 1);
        // También se actualiza en caso de que sea propiedad del propio jugador
        if (getPosicion().getPropietario().equals(getJugador()))
            getVecesCaidasEnPropiedades().set(posicionFinal % 40, getVecesCaidasEnPropiedades().get(posicionFinal % 40) + 1);

        // Y se añade el avatar al listado de avatares contenidos en la nueva casilla
        getPosicion().getAvataresContenidos().put(getIdentificador(), this);

        // Por último se incrementa la frecuencia de dicha casilla
        getPosicion().incrementarFrecuencia();
    }


    /**
     * Se actualiza la posición del avatar empleando el movimiento estándar
     *
     * @param numeroCasillas número de casillas a moverse
     * @return posición de la casilla en el tablero a la que el avatar se ha movido
     */
    public int actualizarPosicionNormal(int numeroCasillas) {

        int posicionFinal = getPosicion().getPosicionEnTablero() + numeroCasillas;

        // Si ha pasado por la casilla de salida
        if (posicionFinal >= 40)
            setHaPasadoSalida(true);

        setCasillasRestantesPorMoverse(getCasillasRestantesPorMoverse() - numeroCasillas);
        if (getCasillasRestantesPorMoverse() == 0)
            sethaMovidoCasillasTirada(true);

        return (posicionFinal % 40);
    }


    /**
     * Se cambia el modo de movimiento del avatar, alternando entre los dos disponibles
     */
    public void switchMovimiento() {

        switchMovimiento(false, true);
    }


    /**
     * Se comprueba si el avatar puede cambiar su modo de movimiento
     *
     * @return si el avatar puede cambiar el modo de movimiento
     */
    public boolean poderCambiarMovimiento(boolean forzar) {

        // Si no ha acabado de moverse las casillas correspondientes a una tirada, no puede cambiarse el modo de
        // movimiento
        // todo avisar de Output.sugerencia("No puede cambiarse el modo de movimiento hasta moverse el nº de casillas de la tirada");
        return( haMovidoCasillasTirada || forzar );
    }

    /**
     * Se cambia el modo de movimiento del avatar, alternando entre los dos disponibles
     *
     * @param forzar si se debe forzar el cambio de movimiento a pesar de las reglas del juego
     */
    public void switchMovimiento(boolean forzar, boolean splash) {


        if (!poderCambiarMovimiento(forzar))
            return;

        setMovimientoEstandar(!isMovimientoEstandar());

        if (splash)
            Output.respuesta("El nuevo modo de movimiento es: " + (isMovimientoEstandar() ? "estándar" : "avanzado"));

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

        // Si está en la cárcel, se comprueba si el avatar se moverá o no
        if (isEncarcelado()) {

            boolean puedeMoverse = actualizarEncarcelamiento(dobles);

            // Si no puede moverse en el turno actual
            if (!puedeMoverse) {
                getTablero().getJuego().setHaLanzadoDados(true);
                return;
            }

        }

        // Se indica que aún no se ha movido las casillas correspondientes a la tirada
        sethaMovidoCasillasTirada(false);
        // Se indica el número de casillas restantes por moverse
        setCasillasRestantesPorMoverse(numeroCasillas);

        // Y se avanza
        avanzar(numeroCasillas);

    }


    /**
     * El avatar se mueve en el tablero un número de casillas dado
     *
     * @param numeroCasillas número de casillas a moverse
     */
    public void avanzar(int numeroCasillas) {

        avanzar(numeroCasillas, true, true, 1);
    }


    /**
     * El avatar se mueve en el tablero, sujeto a condiciones en función de si es un movimiento resultado de una tirada
     * o si es resultado de haber sacado una carta de suerte o de comunidad
     *
     * @param numeroCasillas        número de casillas a moverse
     * @param cobrarSalida          si se debe cobrar el importe correspondiente en caso de pasar por la casilla de salida
     * @param importeSalidaEstandar si el importe a cobrar si se pasa por la casilla de salida es el estándar o el
     *                              establecido al escoger una carta de movimiento
     * @param multiplicador         multiplicador del pago a realizar en caso de caer en una propiedad de otro jugador
     */
    public void avanzar(int numeroCasillas, boolean cobrarSalida, boolean importeSalidaEstandar, int multiplicador) {

        if (ishaMovidoCasillasTirada()) {
            Output.sugerencia("Ya se ha movido todas las casillas correspondientes a la tirada");
            return;
        }

        if (numeroCasillas <= 0) {
            Output.sugerencia("No se puede avanzar menos de una casilla.");
            return;
        }
        // Se mueve el avatar
        actualizarPosicion(calcularNuevaPosicion(numeroCasillas), cobrarSalida, importeSalidaEstandar);

        // En función del tipo de casilla en la que se ha caído
        switch (getPosicion().getGrupo().getTipo()) {

            case suerte:
                Output.respuesta("Has caído en una casilla de suerte.");
                caerEnSuerte();
                break;

            case comunidad:
                Output.respuesta("Has caído en una casilla de comunidad.");
                caerEnComunidad();
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
                caerEnTransporte(multiplicador);
                break;

            case servicios:
                Output.respuesta("Has caído en una casilla de servicio.");
                caerEnServicio(numeroCasillas, multiplicador);
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
                caerEnSolar(multiplicador);
        }

        /*
        // Si es un avatar de pelota, aún pueden quedar posiciones por moverse
        if (getCasillasRestantesPorMoverse() > 0)
            avanzar(getCasillasRestantesPorMoverse(), cobrarSalida, importeSalidaEstandar, multiplicador);*/
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
     * Se escoge una carta de suerte
     */
    private void caerEnSuerte() {

        Scanner scanner = new Scanner(System.in);
        int numeroCarta;

        // Se pide el número de carta
        Output.imprimirEntradaComando("¡Carta de suerte! Introduzca un número del 1 al " +
                Constantes.NUM_CARTAS_SUERTE);

        do {
            numeroCarta = scanner.nextInt();
        } while (numeroCarta <= 0 || numeroCarta > Constantes.NUM_CARTAS_SUERTE);

        getJugador().leerCarta(getTablero().getJuego().barajarSuerte(numeroCarta));
    }


    /**
     * Se escoge una carta de comunidad
     */
    private void caerEnComunidad() {

        Scanner scanner = new Scanner(System.in);
        int numeroCarta;

        // Se pide el número de carta
        Output.imprimirEntradaComando("¡Carta de comunidad! Introduzca un número del 1 al " +
                Constantes.NUM_CARTAS_COMUNIDAD);

        do {
            numeroCarta = scanner.nextInt();
        } while (numeroCarta <= 0 || numeroCarta > Constantes.NUM_CARTAS_COMUNIDAD);

        getJugador().leerCarta(getTablero().getJuego().barajarComunidad(numeroCarta));

    }

    /**
     * En caso de que la casilla pertenezca a otro jugador, el jugador del avatar paga el correspondiente importe
     */
    private void caerEnTransporte(int multiplicador) {

        // Si ha caído en una casilla que no es comprable dado que la tiene otro jugadror
        if (!getPosicion().isComprable() && !getPosicion().getPropietario().equals(this.getJugador()) && !getPosicion().isHipotecada()) {

            int importePagar = (int) (getPosicion().getAlquiler() *
                    getPosicion().getPropietario().numeroCasillasObtenidas(TipoGrupo.transporte) * 0.25 * multiplicador);

            getJugador().pagar(getPosicion().getPropietario(), importePagar);

            // Se incrementa la rentabilidad de la casilla del jugador
            getPosicion().incrementarRentabilidad(importePagar);

            // Si el jugador ha caído en bancarrota tras el pago, debe notificarse
            if (getJugador().isEstaBancarrota())
                getTablero().getJuego().jugadorEnBancarrota(getJugador());

        }

    }

    // todo en teoría Fran va a hacer que los multiplicadores vayan implícitos al obtener el alquiler
    /**
     * En caso de que la casilla pertenezca a otro jugador, el jugador del avatar paga el correspondiente importe
     */
    private void caerEnServicio(int numeroCasillas, int multiplicador) {

        // Si ha caído en una casilla que no es comprable dado que la tiene otro jugadror
        if (!getPosicion().isComprable() && !getPosicion().getPropietario().equals(this.getJugador()) && !getPosicion().isHipotecada()) {

            int multiplicadorPropio = (getPosicion().getPropietario().numeroCasillasObtenidas(TipoGrupo.servicios) == 1) ? 4 : 10;

            int importePagar = numeroCasillas * Constantes.FACTOR_SERVICIO * multiplicadorPropio * multiplicador;
            getJugador().pagar(getPosicion().getPropietario(), importePagar);

            // Se incrementa la rentabilidad de la casilla del jugador
            getPosicion().incrementarRentabilidad(importePagar);

            // Si el jugador ha caído en bancarrota tras el pago, debe notificarse
            if (getJugador().isEstaBancarrota())
                getTablero().getJuego().jugadorEnBancarrota(getJugador());

        }

    }


    /**
     * En caso de que la casilla pertenezca a otro jugador, el jugador del avatar paga el correspondiente importe
     */
    private void caerEnSolar(int multiplicador) {

        // Si ha caído en una casilla que no es comprable dado que la tiene otro jugadror
        if (!getPosicion().isComprable() && !getPosicion().getPropietario().equals(this.getJugador()) && !getPosicion().isHipotecada()) {

            int multiplicadorPropio = getPosicion().getPropietario().haObtenidoSolaresGrupo(getPosicion().getGrupo()) ? 2 : 1;

            int importePagar = getPosicion().getAlquiler() * multiplicadorPropio * multiplicador;

            getJugador().pagar(getPosicion().getPropietario(), importePagar);

            // Se incrementa la rentabilidad de la casilla del jugador
            getPosicion().incrementarRentabilidad(importePagar);

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

        getJugador().incrementarPagoTasasEImpuestos(Constantes.IMPUESTO_1);
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

        getJugador().incrementarPagoTasasEImpuestos(Constantes.IMPUESTO_2);
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

        getJugador().incrementarVecesEnLaCarcel(1);

        setPosicion(getTablero().getCasillas().get(Constantes.POSICION_CARCEL / 10).get(Constantes.POSICION_CARCEL % 10));
        sethaMovidoCasillasTirada(true);
        getTablero().getJuego().setHaLanzadoDados(true);
        setCasillasRestantesPorMoverse(0);
        setEncarcelado(true);
        setHaEstadoCarcel(true);

        // Y se añade el avatar al listado de avatares contenidos en la cárcel
        getPosicion().getAvataresContenidos().put(getIdentificador(), this);

        Output.mensaje(
                "          ,",
                " __  _.-\"` `'-.",
                "/||\\'._ __{}_(",
                "||||  |'--.__\\",
                "|  L.(   ^_\\^     ¡Has sido encarcelado!",
                "\\ .-' |   _ |",
                "| |   )\\___/",
                "|  \\-'`:._]",
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

        getJugador().incrementarPremiosInversionesOBote(parking.getAlquiler());
        parking.setAlquiler(0);

    }


    /**
     * Se suma al jugador del avatar la cantidad asociada al paso por la casilla de salida en caso de no haber sido
     * encarcelado en la vuelta completada, se desencarcela al avatar y se resetea la cantidad de turnos pasados en la
     * cárcel a 0, se incrementa en 1 el número de vueltas completadas, y se llama al tablero para actualizar las
     * vueltas completadas por cada jugador
     */
    private void pasarPorSalida(boolean cobrarSalida, boolean importeSalidaEstandar) {

        // Si no ha estado en la carcel y se permite, se le suma el correspondiente importe a su fortuna
        if (!isHaEstadoCarcel() && cobrarSalida) {
            getJugador().setFortuna(getJugador().getFortuna() + (importeSalidaEstandar ? Constantes.DINERO_SALIDA :
                    Constantes.DINERO_SALIDA_CARTA));
            Output.respuesta("Has cobrado el importe de la casilla de salida.");
            getJugador().incrementarPasarPorCasillaDeSalida((importeSalidaEstandar ? Constantes.DINERO_SALIDA :
                    Constantes.DINERO_SALIDA_CARTA));
        }
        setHaEstadoCarcel(false);
        setTurnosEnCarcel(0);
        setHaPasadoSalida(false);
        setVueltas(getVueltas() + 1);
        getTablero().getJuego().actualizarVueltas();

    }

}