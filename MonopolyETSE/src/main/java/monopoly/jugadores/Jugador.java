package monopoly.jugadores;

import aplicacion.excepciones.InputUsuarioException;
import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.Dado;
import monopoly.jugadores.acciones.Edificacion;
import monopoly.jugadores.acciones.IAccionJugador;
import monopoly.jugadores.acciones.TransferenciaMonetaria;
import monopoly.jugadores.acciones.TransferenciaPropiedad;
import monopoly.jugadores.excepciones.*;
import monopoly.jugadores.tratos.*;
import monopoly.tablero.*;
import monopoly.tablero.jerarquiaCasillas.*;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.Edificio;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;

import java.util.ArrayList;
import java.util.HashMap;

public class Jugador extends Participante {

    /* Atributos */

    // Avatar que lo representa en el tablero
    private final Avatar avatar;

    // Veces que se han tirado los dados en un turno
    private int tiradasEnTurno;
    // Cantidad de turnos penalizado sin poder tirar los dados
    private int turnosPenalizado;

    // Acciones que ha realizado a lo largo de un turno
    private ArrayList<IAccionJugador> acciones;

    // Tratos que ha recibido
    private HashMap<String, Trato> tratosRecibidos;

    //Tratos que ha emitido
    private HashMap<String, Trato> tratosEmitidos;

    // Inmunidades de las que goza
    private ArrayList<Inmunidad> inmunidades;

    // Estadísticas del jugador
    private int dineroInvertido;
    private int pagoDeAlquileres;
    private int cobroDeAlquileres;
    private int pasarPorCasillaDeSalida;
    private int premiosInversionesOBote;
    private int vecesEnLaCarcel;
    private int pagoTasasEImpuestos;

    // Atributo para las estadísticas globales
    private int numeroTiradas;



    /* Constructor */

    /**
     * @param nombre         nombre del jugador a crear
     * @param tablero        tablero del juego
     * @param tipoAvatar     tipo de avatar que crear para el jugador
     * @param casillaInicial casilla en la que establecer inicialmente al avatar del jugador
     */
    public Jugador(String nombre, Tablero tablero, TipoAvatar tipoAvatar, Casilla casillaInicial) {

        super(nombre, Constantes.DINERO_INICIAL);

        if (tablero == null) {
            System.err.println("Tablero no inicializado");
            System.exit(1);
        }

        if (tipoAvatar == null) {
            System.err.println("Tipo de avatar no inicializado");
            System.exit(1);
        }

        if (casillaInicial == null) {
            System.err.println("Casilla inicial no inicializada");
            System.exit(1);
        }

        switch (tipoAvatar) {

            case coche:
                this.avatar = new Coche(this, tablero, casillaInicial);
                break;

            case esfinge:
                this.avatar = new Esfinge(this, tablero, casillaInicial);
                break;

            case pelota:
                this.avatar = new Pelota(this, tablero, casillaInicial);
                break;

            case sombrero:
            default:
                this.avatar = new Sombrero(this, tablero, casillaInicial);
                break;
        }

        this.tiradasEnTurno = 0;

        this.turnosPenalizado = 0;

        this.acciones = new ArrayList<>();

        this.tratosRecibidos = new HashMap<>();
        this.tratosEmitidos = new HashMap<>();

        this.inmunidades = new ArrayList<>();

        this.dineroInvertido = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorCasillaDeSalida = 0;
        this.premiosInversionesOBote = 0;
        this.vecesEnLaCarcel = 0;
        this.pagoTasasEImpuestos = 0;
    }



    /*Getters y setters*/

    public Avatar getAvatar() {
        return (avatar);
    }


    /* No se implementa el setter de avatar dado que es una constante */


    public int getNumeroTiradas() {
        return numeroTiradas;
    }


    public void setNumeroTiradas(int numeroTiradas) {

        if (numeroTiradas < 0) {

            System.err.println("El número de tiradas no puede ser menor que 0");
            System.exit(1);
        }

        this.numeroTiradas = numeroTiradas;
    }


    public void incrementarNumeroTiradas(int numeroTiradas) {

        if (numeroTiradas < 0) {

            System.err.println("El número de tiradas no puede ser menor que 0");
            System.exit(1);
        }

        setNumeroTiradas(getNumeroTiradas() + numeroTiradas);
    }


    public int getTiradasEnTurno() {
        return tiradasEnTurno;
    }


    public void setTiradasEnTurno(int tiradasEnTurno) {

        if (tiradasEnTurno < 0) {
            System.err.println("El número de tiradas en un turno no puede ser menor a 0");
            System.exit(1);
        }

        this.tiradasEnTurno = tiradasEnTurno;

    }


    public int getTurnosPenalizado() {
        return turnosPenalizado;
    }


    public void setTurnosPenalizado(int turnosPenalizado) {

        if (turnosPenalizado < 0) {
            System.err.println("El número de turnos penalizados no puede ser menor a 0");
            System.exit(1);
        }

        this.turnosPenalizado = turnosPenalizado;
    }


    public ArrayList<IAccionJugador> getAcciones() {
        return acciones;
    }


    public void setAcciones(ArrayList<IAccionJugador> acciones) {

        if (acciones == null) {
            System.err.println("ArrayList de acciones no inicializado");
            System.exit(1);
        }

        this.acciones = acciones;
    }


    public HashMap<String,Trato> getTratosRecibidos() {
        return tratosRecibidos;
    }


    public void setTratosRecibidos(HashMap<String, Trato> tratosRecibidos) {

        if (tratosRecibidos == null) {
            System.err.println("HashMap de tratos recibidos no inicializado");
            System.exit(1);
        }

        this.tratosRecibidos = tratosRecibidos;
    }


    public ArrayList<Inmunidad> getInmunidades() {
        return inmunidades;
    }

    public HashMap<String, Trato> getTratosEmitidos() {
        return tratosEmitidos;
    }

    public void setInmunidades(ArrayList<Inmunidad> inmunidades) {

        if (inmunidades == null) {
            System.err.println("ArrayList de inmunidades no inicializado");
            System.exit(1);
        }

        this.inmunidades = inmunidades;
    }


    public int getDineroInvertido() {
        return dineroInvertido;
    }


    public void setDineroInvertido(int dineroInvertido) {

        if (dineroInvertido < 0) {

            System.err.println("El dinero invertido debe ser mayor que 0");
            System.exit(1);
        }

        this.dineroInvertido = dineroInvertido;
    }


    public void incrementarDineroInvertido(int dineroInvertido) {

        if (dineroInvertido < 0) {

            System.err.println("El dinero invertido debe ser mayor que 0");
            System.exit(1);
        }

        setDineroInvertido(getDineroInvertido() + dineroInvertido);
    }


    public int getPagoDeAlquileres() {
        return pagoDeAlquileres;
    }


    public void setPagoDeAlquileres(int pagoDeAlquileres) {

        if (pagoDeAlquileres < 0) {

            System.err.println("El pago de alquileres debe ser mayor que 0");
            System.exit(1);
        }

        this.pagoDeAlquileres = pagoDeAlquileres;
    }


    public void incrementarPagoDeAlquileres(int pagoDeAlquileres) {

        if (pagoDeAlquileres < 0) {

            System.err.println("El pago de alquileres debe ser mayor que 0");
            System.exit(1);
        }

        setPagoDeAlquileres(getPagoDeAlquileres() + pagoDeAlquileres);
    }


    public int getCobroDeAlquileres() {
        return cobroDeAlquileres;
    }


    public void setCobroDeAlquileres(int cobroDeAlquileres) {

        if (cobroDeAlquileres < 0) {

            System.err.println("El cobro de alquiler debe ser mayor que 0");
            System.exit(1);
        }

        this.cobroDeAlquileres = cobroDeAlquileres;
    }


    public void incrementarCobroDeAlquileres(int cobroDeAlquileres) {

        if (cobroDeAlquileres < 0) {

            System.err.println("El cobro de alquiler debe ser mayor que 0");
            System.exit(1);
        }

        setCobroDeAlquileres(getCobroDeAlquileres() + cobroDeAlquileres);
    }


    public int getPagoTasasEImpuestos() {
        return pagoTasasEImpuestos;
    }


    public void setPagoTasasEImpuestos(int pagoTasasEImpuestos) {

        if (pagoTasasEImpuestos < 0) {

            System.err.println("El pago de tasas e impuestos debe ser mayor que 0");
            System.exit(1);
        }

        this.pagoTasasEImpuestos = pagoTasasEImpuestos;
    }


    public void incrementarPagoTasasEImpuestos(int pagoTasasEImpuestos) {

        if (pagoTasasEImpuestos < 0) {


            System.err.println("El pago de tasas e impuestos debe ser mayor que 0");
            System.exit(1);
        }

        setPagoTasasEImpuestos(getPagoTasasEImpuestos() + pagoTasasEImpuestos);
    }


    public int getPasarPorCasillaDeSalida() {
        return pasarPorCasillaDeSalida;
    }


    public void setPasarPorCasillaDeSalida(int pasarPorCasillaDeSalida) {

        if (pasarPorCasillaDeSalida < 0) {

            System.err.println("El número de veces que se ha pasado por la casilla de salida debe ser mayor que 0");
            System.exit(1);
        }

        this.pasarPorCasillaDeSalida = pasarPorCasillaDeSalida;
    }


    public void incrementarPasarPorCasillaDeSalida(int pasarPorCasillaDeSalida) {

        if (pasarPorCasillaDeSalida < 0) {

            System.err.println("El número de veces que se ha pasado por la casilla de salida debe ser mayor que 0");
            System.exit(1);
        }

        setPasarPorCasillaDeSalida(getPasarPorCasillaDeSalida() + pasarPorCasillaDeSalida);
    }


    public int getPremiosInversionesOBote() {
        return premiosInversionesOBote;
    }


    public void setPremiosInversionesOBote(int premiosInversionesOBote) {

        if (premiosInversionesOBote < 0) {

            System.err.println("Los premios de inversiones o bote debe ser mayor que 0");
            System.exit(1);
        }

        this.premiosInversionesOBote = premiosInversionesOBote;
    }


    public void incrementarPremiosInversionesOBote(int premiosInversionesOBote) {

        if (premiosInversionesOBote < 0) {

            System.err.println("Los premios de inversiones o bote debe ser mayor que 0");
            System.exit(1);
        }

        setPremiosInversionesOBote(getPremiosInversionesOBote() + premiosInversionesOBote);
    }

    public int getVecesEnLaCarcel() {
        return vecesEnLaCarcel;
    }


    public void setVecesEnLaCarcel(int vecesEnLaCarcel) {

        if (vecesEnLaCarcel < 0) {

            System.err.println("El número de veces en la carcel debe ser mayor que 0");
            System.exit(1);
        }

        this.vecesEnLaCarcel = vecesEnLaCarcel;
    }


    public void incrementarVecesEnLaCarcel(int vecesEnLaCarcel) {

        if (vecesEnLaCarcel < 0) {

            System.err.println("El número de veces en la carcel debe ser mayor que 0");
            System.exit(1);
        }

        setVecesEnLaCarcel(getVecesEnLaCarcel() + vecesEnLaCarcel);
    }



    /* Métodos */

    @Override
    public boolean pagar(Participante receptor, int importe) throws EstarBancarrotaException,
            NoSerPropietarioException {

        return( pagar( receptor, importe, true) );
    }


    /**
     * Se paga a otro participante una cantidad dada; en caso de no disponer de suficiente liquidez, el participante
     * cae en bancarrota y sus propiedades se transfieren al deudor; se actualizan además las correspondientes
     * estadísticas
     *
     * @param receptor participante al que pagar el importe
     * @param importe  cantidad a pagar
     * @param alquiler si el pago se encuentra relacionado con un alquiler
     * @return si se ha efectuado el pago correctamente
     */
    public boolean pagar(Participante receptor, int importe, boolean alquiler) throws EstarBancarrotaException,
            NoSerPropietarioException {

        if (super.pagar(receptor, importe)) {

            // Si no se trata de la banca
            if (!(receptor instanceof Banca)) {

                final Jugador jugador = (Jugador) receptor;
                // Si es un pago por alquiler
                if( alquiler ) {

                    incrementarPagoDeAlquileres(importe);
                    jugador.incrementarCobroDeAlquileres(importe);
                }

            }

            return (true);

        } else
            return (false);
    }


    @Override
    public int pagar(ArrayList<Participante> participantes, int importe) throws EstarBancarrotaException,
            NoSerPropietarioException {
        return( pagar(participantes, importe, true));
    }


    /**
     * Se paga a varios participantes una cantidad dada; en caso de no disponer de suficiente liquidez, el participante
     * cae en bancarrota y sus propiedades se transfieren al deudor; se actualizan además las correspondientes
     * estadísticas
     *
     * @param participantes participantes a los que pagar el importe
     * @param importe       cantidad a pagar
     * @param alquiler      si el pago está relacionado con un alquiler
     * @return número de pagos efectuados correctamente
     */
    public int pagar(ArrayList<Participante> participantes, int importe, boolean alquiler) throws
            EstarBancarrotaException, NoSerPropietarioException {

        int pagosExitosos = super.pagar(participantes, importe);


        // Se incrementan las estadísticas del deudor
        if( alquiler )
            incrementarPagoDeAlquileres(importe * pagosExitosos);

        // Se incrementan las estadísticas de los receptores
        if (alquiler) {

            for (int i = 0; i < pagosExitosos; i++) {

                if (participantes.get(i) instanceof Jugador) {

                    final Jugador jugador = (Jugador) participantes.get(i);
                    jugador.incrementarCobroDeAlquileres(importe);
                }
            }
        }

        return (pagosExitosos);
    }


    /**
     * Se compra una casilla a un participante pagando el correspondiente importe, en caso de disponer de la suficiente
     * liquidez
     *
     * @param vendedor  jugador al que comprar la casilla
     * @param propiedad propiedad a comprar
     * @return importe de la compra
     */
    @Override
    public int comprar(Participante vendedor, Propiedad propiedad) throws NoEncontrarseEnPropiedadException,
            NoComprarABancaException, NoLiquidezException, NoSerPropietarioException {

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada");
            System.exit(1);
        }

        // Si el jugador no se encuentra en la casilla a comprar
        if (getAvatar().getPosicion().getPosicionEnTablero() != propiedad.getPosicionEnTablero())
            throw new NoEncontrarseEnPropiedadException("El jugador no se encuentra en la propiedad a comprar");

        int importe = super.comprar(vendedor, propiedad);


        // Si se ha efectuado la compra correctamente
        if (importe > 0) {

            // Se incrementan las estadísticas del jugador
            incrementarDineroInvertido(importe);

            // Se indica que se ha efectuado una compra en el turno
            getAvatar().getTablero().getJuego().setHaCompradoPropiedad(true);

            // Se registra la acción
            getAcciones().add(new TransferenciaMonetaria(importe, true,this, vendedor));
            getAcciones().add(new TransferenciaPropiedad(vendedor, this, propiedad));
        }

        return (importe);
    }

    @Override
    public boolean puedeComprar(Propiedad propiedad){

        boolean puedeComprar = super.puedeComprar(propiedad);

        if(getAvatar().getPosicion().getPosicionEnTablero() != propiedad.getPosicionEnTablero())
            return false;

        if ((getAvatar().getTablero().getJuego().isHaCompradoPropiedad() && getAvatar().getTablero().getJuego().getTurno().getAvatar() instanceof Coche) &&
                !getAvatar().getTablero().getJuego().getTurno().getAvatar().isMovimientoEstandar()) {
            return false;
        }

        return(puedeComprar);

    }


    /**
     * Se lanzan dos dados, y se mueve el avatar del jugador tantas casillas como sea la suma de los valores dados por
     * los dados, además de indicarle si se han sacado dobles
     *
     * @param dado instancia del dado a tirar
     */
    public void lanzarDados(Dado dado) throws EstarPenalizadoException, ImposibleMoverseException,
            EstarBancarrotaException, NoSerPropietarioException, NoEstarEncarceladoException,
            ImposibleCambiarModoException, EdificiosSolarException, NumeroIncorrectoException {

        if (dado == null) {
            System.err.println("Dado no inicializado");
            System.exit(1);
        }

        if (getTurnosPenalizado() > 0)
            throw new EstarPenalizadoException("El jugador se encuentra penalizado durante " + getTurnosPenalizado() +
                    " turno(s)");


        // Se eliminan las acciones almacenadas
        getAcciones().clear();

        int primeraTirada = dado.lanzarDado();
        int segundaTirada = dado.lanzarDado();

        incrementarNumeroTiradas(1);

        boolean dobles = primeraTirada == segundaTirada;

        Output.respuesta("Se han tirado los dados:",
                "        -> Primer dado: " + primeraTirada,
                "        -> Segundo dado: " + segundaTirada,
                "        -> ¿Han sido dobles?: " + (dobles ? "sí" : "no"));

        setTiradasEnTurno(getTiradasEnTurno() + 1);

        getAvatar().getTablero().getJuego().setHaHechoUnaTirada(true);

        // Si el tipo de avatar no permite más dobles, es encarcelado
        if (dobles && getAvatar().doblesMaximos())
            getAvatar().caerEnIrACarcel();

            // En caso contrario
        else {

            // Se comprueba si se podrá realizar otra tirada
            getAvatar().getTablero().getJuego().setHaLanzadoDados(getAvatar().noMasTiradas(primeraTirada, segundaTirada));

            // Y se mueve el avatar
            getAvatar().mover(primeraTirada + segundaTirada, dobles);
        }
    }


    /**
     * Se hipoteca una propiedad, no pudiendo cobrar alquiler por ella a partir de ahora, a cambio de obtener la mitad
     * del importe pagado para adquirirla
     *
     * @param propiedad propiedad a hipotecar
     */
    public void hipotecar(Propiedad propiedad) throws NoSerPropietarioException, HipotecaPropiedadException,
            EdificiosSolarException {

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada");
            System.exit(1);
        }

        if (!propiedad.getPropietario().equals(this))
            throw new NoSerPropietarioException("La propiedad no es suya");

        if (propiedad.isHipotecada())
            throw new HipotecaPropiedadException("La propiedad ya se encuentra hipotecada");

        if (propiedad instanceof Solar) {

            final Solar solar = (Solar) propiedad;

            if (solar.tieneEdificios())
                throw new EdificiosSolarException("No se puede hipotecar una propiedad mientras esta " +
                        "contenga edificios");
        }

        // Al hipotecar una casilla, tan sólo se recupera la mitad de su valor original
        int importe = (int) ((double) propiedad.getImporteCompra() * 0.5);

        setFortuna(getFortuna() + importe);
        propiedad.setHipotecada(true);

        Output.respuesta("Se ha hipotecado la propiedad:",
                "        -> Importe obtenido: " + importe);
    }


    /**
     * Se deshipoteca una propiedad, volviendo a poder cobrar alquiler por ella a partir de ahora, a cambio de pagar el
     * el importe obtenido por la hipoteca e incrementado en un 10%
     *
     * @param propiedad propiedad a deshipotecar
     */
    public void deshipotecar(Propiedad propiedad) throws NoSerPropietarioException, HipotecaPropiedadException,
            NoLiquidezException {

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada");
            System.exit(1);
        }

        if (!propiedad.getPropietario().equals(this))
            throw new NoSerPropietarioException("La propiedad no es suya");

        if (!propiedad.isHipotecada())
            throw new HipotecaPropiedadException("La propiedad no se encuentra hipotecada");

        // Si el jugador no dispone de la suficiente liquidez para deshipotecar la casilla; debe pagarse un 10% a
        // mayores del valor obtenido al hipotecarla
        int importe = (int) ((double) propiedad.getImporteCompra() * 0.5 * 1.10);

        if (balanceNegativoTrasPago(importe))
            throw new NoLiquidezException("El jugador no dispone de suficiente liquidez como para deshipotecar la " +
                    "propiedad");

        setFortuna(getFortuna() - importe);
        propiedad.setHipotecada(false);

        Output.respuesta("Se ha deshipotecado la propiedad:",
                "        -> Importe pagado: " + importe);
    }


    /**
     * Se edifica en el solar especificado un tipo de edificio dado
     *
     * @param tipoEdificio tipo de edificio a edificar
     * @param solar        solar en el que edificar
     */
    public void crearEdificio(TipoEdificio tipoEdificio, Solar solar) throws NoSerPropietarioException,
            HipotecaPropiedadException, EdificiosSolarException, NoLiquidezException {

        if (tipoEdificio == null) {
            System.err.println("Tipo de edificio no inicializado");
            System.exit(1);
        }

        if (!solar.getPropietario().equals(this))
            throw new NoSerPropietarioException("El solar no es de su propiedad");

        if (solar.isHipotecada())
            throw new HipotecaPropiedadException("El solar se encuentra hipotecado");

        // El usuario debe, o bien haber caído más de dos veces en el solar para edificar, o bien poseer todos los
        // solares del grupo
        if (getAvatar().getVecesCaidasEnPropiedades().get(solar.getPosicionEnTablero() % 40) > 2 ||
                haObtenidoSolaresGrupo(solar.getGrupo())) {

            // Se resta a la fortuna el importe de edificar
            Integer dinero = Edificio.calcularPrecioCompra(tipoEdificio, solar.getGrupo().getTipo());

            if(balanceNegativoTrasPago(dinero))
                throw new NoLiquidezException("No dispones de suficiente dinero para edificar");

            dinero = solar.edificar(tipoEdificio);
            setFortuna(getFortuna() - dinero);
            incrementarDineroInvertido(dinero);

            Output.respuesta("Se creado el edificio por " +dinero+"K €");

            // Se registra la acción
            getAcciones().add(new Edificacion(solar, tipoEdificio, 1));

        } else
            Output.respuesta("Para edificar en una casilla, debe haber cumplido uno de los siguientes requisitos:",
                    "        -> Poseer todos los solares del grupo de la casilla",
                    "        -> Haber caído más de dos veces en el solar");
    }

    public boolean puedeEdificar(Solar solar){

        if(!solar.getPropietario().equals(this)){
            return false;
        }

        if(solar.isHipotecada()){
            return false;
        }

        if(!(getAvatar().getVecesCaidasEnPropiedades().get(solar.getPosicionEnTablero()%40) > 2 || haObtenidoSolaresGrupo(solar.getGrupo()))){
            return false;
        }

        return true;

    }


    /**
     * Se vende, de un solar dado, un número de edificios de un tipo dado
     *
     * @param tipoEdificio tipo de edificio a edificar
     * @param cantidad     cantidad de edificios a vender
     * @param solar        solar cuyos edificios se van a vender
     */
    public void venderEdificio(TipoEdificio tipoEdificio, int cantidad, Solar solar) throws NoSerPropietarioException,
            HipotecaPropiedadException, EdificiosSolarException, InputUsuarioException {

        if (tipoEdificio == null) {
            System.err.println("Tipo de edificio no inicializado");
            System.exit(1);
        }

        if (solar == null) {
            System.err.println("Solar no inicializado");
            System.exit(1);
        }

        if (!solar.getPropietario().equals(this))
            throw new NoSerPropietarioException("El solar no es de su propiedad");

        if (solar.isHipotecada())
            throw new HipotecaPropiedadException("El solar se encuentra hipotecado");

        if (solar.getEdificiosContenidos().get(tipoEdificio).isEmpty())
            throw new EdificiosSolarException("No existen edificios del tipo especificado en el solar dado");

        if (cantidad < 1)
            throw new InputUsuarioException("Debe venderse al menos un edificio");

        // Se suma a la fortuna el importe de eliminar los edificios
        Integer dinero = solar.venderEdificio(tipoEdificio, cantidad);
        setFortuna(getFortuna() + dinero);

        Output.respuesta("Se ha(n) vendido " + cantidad + " edificio(s).",
                "    -> Dinero recibido: " + dinero + "K €.");
        // Se registra la acción
        getAcciones().add(new Edificacion(solar, tipoEdificio, -cantidad));
    }


    /**
     * Se deshacen las acciones que han beneficiado al jugador en la última tirada; es decir, el dinero recibido por
     * premios y cobros de tasas, así como todas las compras y ventas efectuadas
     */
    public void revertirAcciones() throws EdificiosSolarException{

        for( int i = getAcciones().size() - 1; i >= 0; i-- )
            getAcciones().get(i).revertirAccion();
    }


    /**
     * Se propone a un jugador un trato de intercambio de una propiedad por otra
     */
    public void proponerTrato(Trato trato) throws NoSerPropietarioException {

        if(trato == null){
            System.err.println("Trato referencia a null");
            System.exit(1);
        }

        getTratosRecibidos().put(trato.getId(), trato);
    }


    /**
     * Se acepta un trato dado
     *
     * @param idTrato id del trato a aceptar
     */
    public void aceptarTrato(String idTrato) throws NoLiquidezException, NoSerPropietarioException, NoExisteTratoException {

        Trato trato = getTratosRecibidos().get(idTrato);

        if(trato == null){
            throw new NoExisteTratoException("Ese trato no existe.");
        }

        trato.aceptar();
        getTratosRecibidos().remove(idTrato);
        trato.getEmisor().getTratosEmitidos().remove(idTrato);
    }


    /**
     * Se elimina un trato dado
     * @param idTrato id del trato a eliminar
     */
    public void eliminarTrato(String idTrato) throws NoExisteTratoException{

        Trato trato = getTratosEmitidos().remove(idTrato);

        if(trato == null){
            throw new NoExisteTratoException("Ese trato no existe.");
        } else {
            trato.getReceptor().getTratosRecibidos().remove(idTrato);
            Output.respuesta("El trato "+idTrato+" se ha eliminado.");
        }
    }


    /**
     * Se comprueba si el jugador es inmune a pagos de alquiler en una propiedad dada
     *
     * @param propiedad propiedad cuya inmunidad comprobar
     * @return si el jugador es inmune a pagos en la propiedad especificada
     */
    public boolean serInmuneA(Propiedad propiedad) {

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada");
            System.exit(1);
        }

        for (Inmunidad inmunidad : getInmunidades()) {

            if (inmunidad.getPropiedad().equals(propiedad))
                return (true);
        }

        return (false);
    }


    /**
     * Se reduce en un turno la inmunidad de todas las inmunidades del jugador, siendo eliminadas aquellas que alcanzan
     * los 0 turnos
     */
     public void reducirInmunidad() {

        for (int i = 0; i < getInmunidades().size(); i++) {

            final Inmunidad inmunidad = getInmunidades().get(i);

            inmunidad.setNumeroTurnos(inmunidad.getNumeroTurnos() - 1);

            // Si ya se ha agotado, se elimina
            if (inmunidad.getNumeroTurnos() == 0) {

                getInmunidades().remove(i);

                Output.respuesta("Has dejado de ser inmune a los alquileres de " +
                        inmunidad.getPropiedad().getNombre() + "!");

                // Se reduce en uno el iterante para un funciomiento apropiado del bucle
                i--;
            }
        }
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
        final Jugador otro = (Jugador) obj;

        // Si los identificadores de sus avatares son el mismo
        return (this.getAvatar().getIdentificador() == otro.getAvatar().getIdentificador());

    } /* Fin del método equals */


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        // Se añade el nombre, el identificador del avatar y la fortuna del jugador
        stringBuilder.append("(*) Jugador: ").append(getNombre()).append("\n");
        stringBuilder.append("        -> Avatar: ").append(((Character) getAvatar().getIdentificador()).toString()).append("\n");
        stringBuilder.append("        -> Fortuna: ").append(((Integer) getFortuna()).toString()).append("K €\n");

        // Número de propiedades del jugador
        int numPropiedades = getPropiedades().size();

        // En la variable auxiliar propr se añadirá la información de las propiedades
        StringBuilder prop = new StringBuilder();

        // Se calculará el número de propiedades y de propiedades hipotecadas para, en caso de que sean 0, añadir un
        // formato especial
        int numProp = 0;

        // Se realiza lo mismo con las propiedades hipotecadas
        StringBuilder propHipotecadas = new StringBuilder();
        int numHip = 0;

        // Variable en la que almacenar propiedades del jugador
        ArrayList<Propiedad> propiedades;

        // Se añaden las cabeceras de las líneas de las propiedades
        prop.append("        -> Propiedades: {");
        propHipotecadas.append("        -> Propiedades hipotecadas: {");

        for (int i = 0; i < numPropiedades; i++) {

            // Se obtienen las propiedades
            propiedades = getPropiedades();

            // Si la casilla está hipotecada se añade a su StringBuilder correspondiente
            if (propiedades.get(i).isHipotecada()) {

                // El nombre
                propHipotecadas.append(propiedades.get(i).getNombre());

                // Si es la última propiedad del jugador no se añade la coma
                if (i != numPropiedades - 1)
                    propHipotecadas.append(", ");

                numHip++;

            } else {

                // El nombre
                prop.append(propiedades.get(i).getNombre());

                // En caso de que sea la última propiedad del jugador no se añade la coma
                if (i != numPropiedades - 1)
                    prop.append(", ");

                numProp++;
            }
        }

        // Si no se han encontrado propiedades de un determinado tipo
        if (numProp == 0)
            prop.append("Sin propiedades");
        if (numHip == 0)
            propHipotecadas.append("Sin propiedades hipotecadas");

        // Se cierran los StringBuilders
        prop.append("}");
        propHipotecadas.append("}");

        // Y se añaden al StringBuilder original
        stringBuilder.append(prop.toString()).append("\n");
        stringBuilder.append(propHipotecadas.toString()).append("\n");

        HashMap<TipoEdificio, ArrayList<Edificio>> edificiosJugador = edificiosJugador();

        // Ahora se añaden los edificios
        stringBuilder.append("        -> Edificios:\n");

        for (TipoEdificio tipoEdificio : TipoEdificio.values()) {

            // Se crea un StringBuilder para cada tipo de edificio
            StringBuilder edificios = new StringBuilder();

            // Se cogen los edificios de dicho tipo
            ArrayList<Edificio> aux = edificiosJugador.get(tipoEdificio);

            // Variable para llevar la cuenta de edificios añadidos
            int i = 1;

            // Se inserta la cabecera para el tipo de edificio
            edificios.append("            (*) ").append(tipoEdificio.getNombre()).append(": {");

            for (Edificio edificio : aux) {

                // Se inserta el ID del edificio
                edificios.append(edificio.getId());

                // Si no es el último edificio a añadir, se inserta una coma
                if (i != aux.size())
                    edificios.append(", ");

                i++;
            }

            // Se cierra la línea y se añade al StringBuilder
            edificios.append("}");
            stringBuilder.append(edificios.toString()).append("\n");
        }

        return (stringBuilder.toString());
    }


    /**
     * Se obtienen los edificios que posee el jugador
     *
     * @return HashMap con los edificios clasificados por tipo
     */
    private HashMap<TipoEdificio, ArrayList<Edificio>> edificiosJugador() {

        HashMap<TipoEdificio, ArrayList<Edificio>> hashMap = new HashMap<>();

        // Se añade un ArrayList vacío al mapa por cada tipo de edificio posible
        for (TipoEdificio tipoEdificio : TipoEdificio.values())
            hashMap.put(tipoEdificio, new ArrayList<>());

        // Para cada propiedad
        for (Propiedad propiedad : getPropiedades()) {

            // Se comprueba si es un solar
            if (propiedad instanceof Solar) {

                final Solar solar = (Solar) propiedad;

                // Se añaden sus edificios por tipos
                for (TipoEdificio tipoEdificio : TipoEdificio.values()) {

                    ArrayList<Edificio> edificiosContenidos = solar.getEdificiosContenidos().get(tipoEdificio);
                    hashMap.get(tipoEdificio).addAll(edificiosContenidos);
                }
            }
        }

        return (hashMap);
    }
}

