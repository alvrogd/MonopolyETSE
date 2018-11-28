package monopoly;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.Avatar;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;
import monopoly.tablero.TipoGrupo;
import monopoly.tablero.cartas.*;

import java.util.*;

public class Juego {

    /* Atributos */

    //Jugador que tiene el turno
    private Jugador turno;

    //HashMap con los jugadores para acceder a través de su nombre
    private HashMap<String, Jugador> jugadores;

    //ArrayList con los nombres de los jugadores almacenados por orden de turno
    private ArrayList<String> nombresJugadores;

    //Jugador que hace el rol de la banca
    private Jugador banca;

    //Tablero del juego
    private Tablero tablero;

    //Iterador que se utilizará para los turnos
    //No se crea un getter para iterador ya que no tiene sentido que el usuario pueda tener acceso a él, ya que si se
    //quiere obtener información se puede llamar a getTurno, ya que el turno va en función del iterator.
    private Iterator iterador;

    //Atributo para calcular el mínimo de las vueltas recorrido por los usuarios en módulo 4
    //No hay getter para vueltasMin ya que es un atributo interno para realizar operaciones de incremento del precio de
    //los grupos.
    private int vueltasMin;

    //Booleano que indica si el juego ya está iniciado o no
    private boolean iniciado;

    //Atributo para indicar si el juego ha finalizado.
    private boolean finalizado;

    //Booleano para establecer si se ha incrementado el valor de las casillas de grupo
    //No se crea getter para seHaIncrementado debido a que es un atributo interno para realizar operaciones en el
    //incremento del precio de los grupos y no afecta en nada al entendimiento del usuario de la clase.
    private boolean seHaIncrementado;

    //Booleano para saber si el usuario puede volver a tirar los dados
    private boolean haLanzadoDados;

    //Booleano para saber si el usuario ha acabado el movimiento actual.
    private boolean haAcabadoMovimiento;

    // Si ha comprado una propiedad en el turno (por el avatar coche)
    private boolean haCompradoPropiedad;

    private ArrayList<Carta> cartasSuerte;

    private ArrayList<Carta> cartasComunidad;

    /* Constructores */

    /**
     * Se genera el juego, creando la banca y asignándole el turno. Se crean las estructuras de datos HashMap y
     * ArrayList además del tablero. Constructor sin argumentos teniendo la posibilidad de añadir jugadores
     * Inicialmente vueltasMin = 0, iniciado = false, seHaIncremento = false y haLanzadoDados = false
     */
    public Juego() {

        banca = new Jugador("banca");
        turno = banca;
        jugadores = new HashMap<>();
        nombresJugadores = new ArrayList<>();
        tablero = new Tablero(banca, this);
        iniciado = false;
        vueltasMin = 0;
        seHaIncrementado = false;
        finalizado = false;
        haLanzadoDados = false;
        haAcabadoMovimiento = false;
        haCompradoPropiedad = false;
        anadirCartas();

    }

    /**
     * Constructor para iniciar un Juego con unos jugadores predeterminados, se podrían añadir más
     *
     * @param jugadores
     */
    public Juego(ArrayList<Jugador> jugadores) {

        this();

        if (jugadores == null) {
            System.err.println("Jugador referencia a null");
            System.exit(1);
        }

        for (Jugador jugador : jugadores) {

            if (jugador == null) {
                System.err.println("Jugador referencia a null");
                System.exit(1);
            }

            addJugador(jugador);

        }
    }

    private void anadirCartas() {

        cartasComunidad = new ArrayList<>();
        cartasSuerte = new ArrayList<>();

        cartasSuerte.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverAeropuerto));
        cartasSuerte.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverCadiz));
        cartasSuerte.add(new Carta(TipoAccion.cobro, TipoCobro.cobrarBilleteAvion));
        cartasSuerte.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverCaceres));
        cartasSuerte.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverCarcel));
        cartasSuerte.add(new Carta(TipoAccion.cobro, TipoCobro.cobrarLoteria));
        cartasSuerte.add(new Carta(TipoAccion.pago, TipoPago.pagarMatriculaColegio));
        cartasSuerte.add(new Carta(TipoAccion.pago, TipoPago.pagarBienesInmuebles));
        cartasSuerte.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverLeganes));
        cartasSuerte.add(new Carta(TipoAccion.pago, TipoPago.pagarPresidente));
        cartasSuerte.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverTrafico));
        cartasSuerte.add(new Carta(TipoAccion.pago, TipoPago.pagarMovil));
        cartasSuerte.add(new Carta(TipoAccion.cobro, TipoCobro.cobrarAcciones));
        cartasSuerte.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverTransporte));

        cartasComunidad.add(new Carta(TipoAccion.pago, TipoPago.pagarBalneario));
        cartasComunidad.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverCarcel));
        cartasComunidad.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverSalida));
        cartasComunidad.add(new Carta(TipoAccion.cobro, TipoCobro.cobrarInternet));
        cartasComunidad.add(new Carta(TipoAccion.pago, TipoPago.pagarViajeLeon));
        cartasComunidad.add(new Carta(TipoAccion.cobro, TipoCobro.cobrarHacienda));
        cartasComunidad.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverValencia));
        cartasComunidad.add(new Carta(TipoAccion.pago, TipoPago.pagarAlquilerCannes));
        cartasComunidad.add(new Carta(TipoAccion.cobro, TipoCobro.cobrarJet));
        cartasComunidad.add(new Carta(TipoAccion.movimiento, TipoMovimiento.moverPamplona));

    }

    private void barajarCarta(String tipo) {

        switch (tipo) {
            case "suerte":
                Collections.shuffle(getCartasSuerte());

            case "comunidad":
                Collections.shuffle(getCartasComunidad());
        }
    }

    public Carta barajarSuerte(int numCarta) {

        if (numCarta < 1 || numCarta > Constantes.NUM_CARTAS_SUERTE) {
            Output.errorComando("Ha introducido un número incorrecto.");
            return null;
        }
        barajarCarta("suerte");

        return (getCartasSuerte().get(numCarta - 1));

    }

    public Carta barajarComunidad(int numCarta) {

        if (numCarta < 1 || numCarta > Constantes.NUM_CARTAS_SUERTE) {
            Output.errorComando("Ha introducido un número incorrecto.");
            return null;
        }
        barajarCarta("comunidad");

        return (getCartasComunidad().get(numCarta - 1));

    }

    /* Getters */
    public HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public ArrayList<String> getNombresJugadores() {
        return nombresJugadores;
    }

    public Jugador getJugador(String jugador) {
        return (jugadores.get(jugador));
    }

    public Jugador getBanca() {
        return banca;
    }

    public Jugador getTurno() {
        return turno;
    }

    public boolean isHaLanzadoDados() {
        return haLanzadoDados;
    }

    public void setHaLanzadoDados(boolean haLanzadoDados) {
        this.haLanzadoDados = haLanzadoDados;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public boolean isHaAcabadoMovimiento() {
        return haAcabadoMovimiento;
    }

    public void setHaAcabadoMovimiento(boolean haAcabadoMovimiento) {
        this.haAcabadoMovimiento = haAcabadoMovimiento;
    }

    public boolean isHaCompradoPropiedad() {
        return haCompradoPropiedad;
    }

    public void setHaCompradoPropiedad(boolean haCompradoPropiedad) {
        this.haCompradoPropiedad = haCompradoPropiedad;
    }

    public ArrayList<Carta> getCartasSuerte() {
        return cartasSuerte;
    }

    public void setCartasSuerte(ArrayList<Carta> cartasSuerte) {
        this.cartasSuerte = cartasSuerte;
    }

    public ArrayList<Carta> getCartasComunidad() {
        return cartasComunidad;
    }

    public void setCartasComunidad(ArrayList<Carta> cartasComunidad) {
        this.cartasComunidad = cartasComunidad;
    }

    /* Setters */

    /**
     * Función para añadir un jugador al juego.
     *
     * @param jugador jugador que se desea añadir al juego.
     */
    public void addJugador(Jugador jugador) {

        if (jugador == null) {
            System.out.println("Jugador referencia a null");
            System.exit(1);
        }

        //Se introduce el usuario en el HashMap de jugadores y al final del ArrayList con los nombres
        getJugadores().put(jugador.getNombre(), jugador);
        getNombresJugadores().add(jugador.getNombre());

        //También se añade a la estructura de Tablero (avataresContenidos) el avatar del jugador y su identificador
        getTablero().getAvataresContenidos().put(jugador.getAvatar().getIdentificador(), jugador.getAvatar());
    }

    /* Métodos */

    /**
     * Función para iniciar el juego
     */
    public void iniciarJuego() {
        if (getJugadores().isEmpty()) {
            System.err.println("No ha introducido ningún jugador");
            return;
        }
        if (!isIniciado()) {

            //Se pone iniciado a true.
            this.iniciado = true;

            //En el caso de que el juego no se haya iniciado se obtiene el iterator de los nombres de jugadores para
            //utilizarlo a la hora de los turnos.
            this.iterador = getNombresJugadores().iterator();
            this.turno = getJugadores().get(iterador.next());
        } else {
            System.err.println("El juego ya está iniciado");
            return;
        }
    }

    /**
     * Función para pasar el turno al siguiente jugador en el ArrayList de nombresJugadores
     */
    public void finalizarTurno() {

        //Para poder pasar el turno el juego debe haberse iniciado.
        if (isIniciado() && !isFinalizado()) {

            if (this.iterador == null) {
                System.out.println("No se ha añadido ningún jugador.");
                System.exit(1);
            }

            //Se pone el número de tiradas del jugador que tiene el turno a 0.
            getTurno().setTiradasEnTurno(0);

            //En el caso de que haya un siguiente en el iterador el turno lo tendrá este jugador, obteniendo el jugador
            //desde el HashMap
            if (this.iterador.hasNext())
                this.turno = getJugadores().get(this.iterador.next());

                //En caso contrario se vuelve a crear el Iterator de los nombres de jugadores y se asigna el turno al primer
                //jugador.
            else {
                this.iterador = getNombresJugadores().iterator();
                this.turno = getJugadores().get(this.iterador.next());
            }

            //En caso de que los turnos penalizados del jugador no sea 0 se decrementa una unidad.
            if (getTurno().getTurnosPenalizado() != 0)
                getTurno().setTurnosPenalizado(getTurno().getTurnosPenalizado() - 1);

            //Se establece el booleano de se han lanzado los dados a false.
            this.haLanzadoDados = false;

            // Y el indicador de haber comprado una propiedad
            setHaCompradoPropiedad(false);

        } else {

            System.out.println("Juego no iniciado.");
            return;
        }

    }

    public void jugadorEnBancarrota(Jugador jugador) {

        if (jugador == null) {
            System.err.println("jugador referencia a null");
            return;
        }
        if (!jugador.isEstaBancarrota()) {
            System.err.println("El jugador no está en bancarrota");
            return;
        }

        Avatar avatarJugador = jugador.getAvatar();

        getJugadores().remove(jugador.getNombre());
        getNombresJugadores().remove(jugador.getNombre());

        getTablero().getAvataresContenidos().remove(avatarJugador.getIdentificador());
        avatarJugador.getPosicion().getAvataresContenidos().remove((Character) avatarJugador.getIdentificador());

        finalizarTurno();

        if (getNombresJugadores().size() == 1) {
            Output.mensaje("¡" + getTurno().getNombre() + " ha ganado el juego!");
            this.finalizado = true;
        }


    }

    /**
     * Función que calcula el mínimo de las vueltas recorridas por los avatares, en caso de que se cambien de 3 a 4 se
     * incrementará un 5% el valor de los grupos en venta.
     */
    public void actualizarVueltas() {

        //Se comprueba si el juego está inicializado
        if (!iniciado) {
            System.err.println("Juego no iniciado.");
            return;
        }

        if (isFinalizado()) {
            System.err.println("Juego acabado.");
            return;
        }

        //Se guardan los nombres de los jugadores en el ArrayList jugadores
        ArrayList<String> jugadores = getNombresJugadores();

        //El mínimo será el número de vueltas que recorrió el avatar del primer jugador
        //Se obtiene el jugador a través del HashMap, luego el avatar y posteriormente las vueltas.
        int min = getJugadores().get(jugadores.get(0)).getAvatar().getVueltas();

        //Variable auxiliar para guardar las vueltas del jugador actual.
        int vueltasIteradas;

        //Se recorren los jugadores para calcular el mínimo
        for (String jugador : jugadores) {

            //Se calculan las vueltas del avatar del jugador actual.
            vueltasIteradas = getJugadores().get(jugador).getAvatar().getVueltas();

            //Si el mínimo es más grande que las vueltas actuales entonces estas serán el nuevo mínimo
            if (min > vueltasIteradas)
                min = vueltasIteradas;

        }

        // Si el jugador con menos vueltas ha dado una nueva vuelta completa
        if (this.vueltasMin != min)
            this.seHaIncrementado = false;

        this.vueltasMin = min;

        // En el caso de que todos los avatares hayan recorrido ya 4 vueltas y no se haya incrementado antes el precio
        // de los solares
        if (this.vueltasMin % 4 == 0 && !this.seHaIncrementado && this.vueltasMin != 0) {

            //Se miran todas las casillas del tablero y en caso de que sean comprables y no sean ni transportes ni
            //servicios se incrementa el precio del grupo en Constantes.INCREMENTO_VUELTAS

            //Se recorren las filas del tablero
            for (ArrayList<Casilla> fila : getTablero().getCasillas()) {

                //Se recorren las casillas de cada fila
                for (Casilla casilla : fila) {

                    //Se comprueba que estén en venta
                    if (casilla.isComprable()) {

                        //Si no pertenecen al grupo de transportes ni al de servicios, o sea, si pertenecen a solares.
                        if (casilla.getGrupo().getTipo() != TipoGrupo.transporte &&
                                casilla.getGrupo().getTipo() != TipoGrupo.servicios) {

                            //Se establece el nuevo precio en caso de que no se haya comprado la casilla.
                            casilla.getGrupo().setPrecio(
                                    (int) ((1.0 + Constantes.INCREMENTO_VUELTAS) * casilla.getGrupo().getPrecio()));

                        }
                    }
                }
            }
        }
    }
}
