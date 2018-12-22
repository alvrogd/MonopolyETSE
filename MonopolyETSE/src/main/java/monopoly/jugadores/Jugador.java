package monopoly.jugadores;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.Dado;
import monopoly.jugadores.acciones.IAccionJugador;
import monopoly.tablero.*;
import monopoly.tablero.cartas.*;
import monopoly.tablero.jerarquiaCasillas.*;

import java.util.ArrayList;
import java.util.Collection;

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

    // Estadísticas del jugador
    private int dineroInvertido;
    private int pagoDeAlquileres;
    private int cobroDeAlquileres;
    private int pasarPorCasillaDeSalida;
    private int premiosInversionesOBote;
    private int vecesEnLaCarcel;
    private int pagoTasasEImpuestos;

    // Atributo para las estadísticas globales
    private int valorDados;



    /* Constructor */

    /**
     * @param nombre         nombre del jugador a crear
     * @param tablero        tablero del juego
     * @param tipoAvatar     tipo de avatar que crear para el jugador
     * @param casillaInicial casilla en la que establecer inicialmente al avatar del jugador
     */
    public Jugador(String nombre, Tablero tablero, TipoAvatar tipoAvatar, Casilla casillaInicial) {

        super( nombre, Constantes.DINERO_INICIAL);

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

        switch( tipoAvatar ) {

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
                this.avatar = new Sombrero(this, tablero, casillaInicial);
                break;
        }

        this.tiradasEnTurno = 0;

        this.turnosPenalizado = 0;

        this.acciones = new ArrayList<>();

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


    public int getValorDados() {
        return valorDados;
    }


    // todo cambiar que es el número de tiradas
    public void setValorDados(int valorDados) {

        if (valorDados < 0) {

            System.err.println("Valor de dados no puede ser menor que 0");
            return;
        }

        this.valorDados = valorDados;
    }


    public void incrementarValorDados(int valorDados) {

        if (valorDados < 0) {

            System.err.println("Valor de dados no puede ser menor que 0");
            return;
        }

        setValorDados(getValorDados() + valorDados);
    }


    public int getTiradasEnTurno() {
        return tiradasEnTurno;
    }


    public void setTiradasEnTurno(int tiradasEnTurno) {

        if (tiradasEnTurno < 0) {
            Output.sugerencia("El número de tiradas en un turno no puede ser menor a 0");
            return;
        }

        this.tiradasEnTurno = tiradasEnTurno;

    }


    public int getTurnosPenalizado() {
        return turnosPenalizado;
    }


    public void setTurnosPenalizado(int turnosPenalizado) {

        if (turnosPenalizado < 0) {
            Output.sugerencia("El número de turnos penalizados no puede ser menor a 0");
            return;
        }

        this.turnosPenalizado = turnosPenalizado;
    }


    public ArrayList<IAccionJugador> getAcciones() {
        return acciones;
    }


    public void setAcciones(ArrayList<IAccionJugador> acciones) {

        if( acciones == null ) {
            System.err.println( "ArrayList de acciones no inicializado" );
            return;
        }

        this.acciones = acciones;
    }


    public int getDineroInvertido() {
        return dineroInvertido;
    }


    public void setDineroInvertido(int dineroInvertido) {

        if (dineroInvertido < 0) {

            System.err.println("El dinero invertido debe ser mayor que 0");
            return;
        }

        this.dineroInvertido = dineroInvertido;
    }


    public void incrementarDineroInvertido(int dineroInvertido) {

        if (dineroInvertido < 0) {

            System.err.println("El dinero invertido debe ser mayor que 0");
            return;
        }

        setDineroInvertido(getDineroInvertido() + dineroInvertido);
    }


    public int getPagoDeAlquileres() {
        return pagoDeAlquileres;
    }


    public void setPagoDeAlquileres(int pagoDeAlquileres) {

        if (pagoDeAlquileres < 0) {

            System.err.println("El pago de alquileres debe ser mayor que 0");
            return;
        }

        this.pagoDeAlquileres = pagoDeAlquileres;
    }


    public void incrementarPagoDeAlquileres(int pagoDeAlquileres) {

        if (pagoDeAlquileres < 0) {

            System.err.println("El pago de alquileres debe ser mayor que 0");
            return;
        }

        setPagoDeAlquileres(getPagoDeAlquileres() + pagoDeAlquileres);
    }


    public int getCobroDeAlquileres() {
        return cobroDeAlquileres;
    }


    public void setCobroDeAlquileres(int cobroDeAlquileres) {

        if (cobroDeAlquileres < 0) {

            System.err.println("El cobro de alquiler debe ser mayor que 0");
            return;
        }

        this.cobroDeAlquileres = cobroDeAlquileres;
    }


    public void incrementarCobroDeAlquileres(int cobroDeAlquileres) {

        if (cobroDeAlquileres < 0) {

            System.err.println("El cobro de alquiler debe ser mayor que 0");
            return;
        }

        setCobroDeAlquileres(getCobroDeAlquileres() + cobroDeAlquileres);
    }


    public int getPagoTasasEImpuestos() {
        return pagoTasasEImpuestos;
    }


    public void setPagoTasasEImpuestos(int pagoTasasEImpuestos) {

        if (pagoTasasEImpuestos < 0) {

            System.err.println("El pago de tasas e impuestos debe ser mayor que 0");
            return;
        }

        this.pagoTasasEImpuestos = pagoTasasEImpuestos;
    }


    public void incrementarPagoTasasEImpuestos(int pagoTasasEImpuestos) {

        if (pagoTasasEImpuestos < 0) {


            System.err.println("El pago de tasas e impuestos debe ser mayor que 0");
            return;
        }

        setPagoTasasEImpuestos(getPagoTasasEImpuestos() + pagoTasasEImpuestos);
    }


    public int getPasarPorCasillaDeSalida() {
        return pasarPorCasillaDeSalida;
    }


    public void setPasarPorCasillaDeSalida(int pasarPorCasillaDeSalida) {

        if (pasarPorCasillaDeSalida < 0) {

            System.err.println("El número de veces que se ha pasado por la casilla de salida debe ser mayor que 0");
            return;
        }

        this.pasarPorCasillaDeSalida = pasarPorCasillaDeSalida;
    }


    public void incrementarPasarPorCasillaDeSalida(int pasarPorCasillaDeSalida) {

        if (pasarPorCasillaDeSalida < 0) {

            System.err.println("El número de veces que se ha pasado por la casilla de salida debe ser mayor que 0");
            return;
        }

        setPasarPorCasillaDeSalida(getPasarPorCasillaDeSalida() + pasarPorCasillaDeSalida);
    }


    public int getPremiosInversionesOBote() {
        return premiosInversionesOBote;
    }


    public void setPremiosInversionesOBote(int premiosInversionesOBote) {

        if (premiosInversionesOBote < 0) {

            System.err.println("Los premios de inversiones o bote debe ser mayor que 0");
            return;
        }

        this.premiosInversionesOBote = premiosInversionesOBote;
    }


    public void incrementarPremiosInversionesOBote(int premiosInversionesOBote) {

        if (premiosInversionesOBote < 0) {

            System.err.println("Los premios de inversiones o bote debe ser mayor que 0");
            return;
        }

        setPremiosInversionesOBote(getPremiosInversionesOBote() + premiosInversionesOBote);
    }

    public int getVecesEnLaCarcel() {
        return vecesEnLaCarcel;
    }


    public void setVecesEnLaCarcel(int vecesEnLaCarcel) {

        if (vecesEnLaCarcel < 0) {

            System.err.println("El número de veces en la carcel debe ser mayor que 0");
            return;
        }

        this.vecesEnLaCarcel = vecesEnLaCarcel;
    }


    public void incrementarVecesEnLaCarcel(int vecesEnLaCarcel) {

        if (vecesEnLaCarcel < 0) {

            System.err.println("El número de veces en la carcel debe ser mayor que 0");
            return;
        }

        setVecesEnLaCarcel(getVecesEnLaCarcel() + vecesEnLaCarcel);
    }



    /* Métodos */

    /**
     * Se paga a otro participante una cantidad dada; en caso de no disponer de suficiente liquidez, el participante
     * cae en bancarrota y sus propiedades se transfieren al deudor; se actualizan además las correspondientes
     * estadísticas
     *
     * @param receptor participante al que pagar el importe
     * @param importe  cantidad a pagar
     * @return         si se ha efectuado el pago correctamente
     */
    @Override
    public boolean pagar(Participante receptor, int importe) {

        if( super.pagar( receptor, importe ) ) {

            // Si no se trata de la banca
            if( !(receptor instanceof Banca )) {

                final Jugador jugador = (Jugador)receptor;
                incrementarPagoDeAlquileres(importe);
                jugador.incrementarCobroDeAlquileres(importe);

            }

            return( true );
        }

        else
            return( false );
    }


    /**
     * Se paga a varios participantes una cantidad dada; en caso de no disponer de suficiente liquidez, el participante
     * cae en bancarrota y sus propiedades se transfieren al deudor; se actualizan además las correspondientes
     * estadísticas
     *
     * @param participantes participantes a los que pagar el importe
     * @param importe       cantidad a pagar
     * @return              número de pagos efectuados correctamente
     */
    @Override
    public int pagar(ArrayList<Participante> participantes, int importe) {

        int pagosExitosos = super.pagar(participantes, importe);


        // Se incrementan las estadísticas del deudor
        incrementarPagoDeAlquileres(importe * pagosExitosos);

        // Se incrementan las estadísticas de los receptores
        for( int i = 0; i < pagosExitosos; i++ ) {

            if( participantes.get( i ) instanceof Jugador ) {

                final Jugador jugador = (Jugador)participantes.get(i);
                jugador.incrementarCobroDeAlquileres(importe);
            }
        }

        return( pagosExitosos );
    }


    /**
     * Se compra una casilla a un participante pagando el correspondiente importe, en caso de disponer de la suficiente
     * liquidez
     *
     * @param vendedor   jugador al que comprar la casilla
     * @param propiedad  propiedad a comprar
     * @return           importe de la compra
     */
    @Override
    public int comprar(Participante vendedor, Propiedad propiedad) {

        if( propiedad == null ) {
            System.err.println( "Propiedad no inicializada" );
            System.exit( 1);
        }

        // Si el jugador no se encuentra en la casilla a comprar
        if (getAvatar().getPosicion().getPosicionEnTablero() != propiedad.getPosicionEnTablero()) {
            Output.respuesta("El jugador no se encuentra en la propiedad a comprar");
            return(0);
        }

        int importe = super.comprar( vendedor, propiedad);

        // Se incrementan las estadísticas del jugador
        incrementarDineroInvertido(importe);
    }


    /**
     * Se lanzan dos dados, y se mueve el avatar del jugador tantas casillas como sea la suma de los valores dados por
     * los dados, además de indicarle si se han sacado dobles
     *
     * @param dado instancia del dado a tirar
     */
    public void lanzarDados(Dado dado) {

        if (dado == null) {
            System.err.println("Dado no inicializado");
            System.exit(1);
        }

        if (getTurnosPenalizado() > 0) {
            Output.sugerencia("El jugador se encuentra penalizado durante " + getTurnosPenalizado() +
                    " turno(s)");
            getAvatar().getTablero().getJuego().setHaLanzadoDados(true);
            return;
        }

        // Se eliminan las acciones almacenadas
        getAcciones().clear();

        int primeraTirada = dado.lanzarDado();
        int segundaTirada = dado.lanzarDado();

        incrementarValorDados(1);

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
            getAvatar().getTablero().getJuego().setHaLanzadoDados(getAvatar().noMasTiradas(primeraTirada,segundaTirada));

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
    public void hipotecar(Propiedad propiedad) {

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada");
            System.exit(1);
        }

        if (!propiedad.getPropietario().equals(this)) {
            System.err.println("La propiedad no es de suya");
            return;
        }

        if (propiedad.isHipotecada()) {
            System.err.println("La propiedad ya se encuentra hipotecada");
            return;
        }

        if( propiedad instanceof Solar) {

            final Solar solar = ( Solar ) propiedad;

            if (solar.tieneEdificios()) {
                System.err.println("No se puede hipotecar una propiedad mientras esta contenga edificios");
                return;
            }
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
    public void deshipotecar(Propiedad propiedad) {

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada");
            System.exit(1);
        }

        if (!propiedad.getPropietario().equals(this)) {
            System.err.println("La propiedad no es suya");
            return;
        }

        if (!propiedad.isHipotecada()) {
            System.err.println("La propiedad no se encuentra hipotecada.");
            return;
        }

        // Si el jugador no dispone de la suficiente liquidez para deshipotecar la casilla; debe pagarse un 10% a
        // mayores del valor obtenido al hipotecarla
        int importe = (int) ((double) propiedad.getImporteCompra() * 0.5 * 1.10);

        if (balanceNegativoTrasPago(importe)) {
            Output.respuesta("El jugador no dispone de suficiente liquidez como para deshipotecar la " +
                    "propiedad");

        }

        else {

            setFortuna(getFortuna() - importe);
            propiedad.setHipotecada(false);

            Output.respuesta("Se ha deshipotecado la propiedad:",
                    "        -> Importe pagado: " + importe);
        }
    }


    /**
     * Se edifica en el solar especificado un tipo de edificio dado
     *
     * @param tipoEdificio tipo de edificio a edificar
     * @param solar        solar en el que edificar
     */
    public void crearEdificio(TipoEdificio tipoEdificio, Solar solar) {

        if (tipoEdificio == null) {
            System.err.println("Tipo de edificio no inicializado");
            System.exit(1);
        }

        if (!solar.getPropietario().equals(this)) {
            Output.sugerencia("El solar no es de su propiedad");
            return;
        }

        if (solar.isHipotecada()) {
            Output.sugerencia("El solar se encuentra hipotecado");
            return;
        }

        // El usuario debe, o bien haber caído más de dos veces en el solar para edificar, o bien poseer todos los
        // solares del grupo
        if (getAvatar().getVecesCaidasEnPropiedades().get(solar.getPosicionEnTablero() % 40) > 2 ||
                haObtenidoSolaresGrupo(solar.getGrupo()))

            solar.edificar(this, tipoEdificio);

        else
            Output.respuesta("Para edificar en una casilla, debe haber cumplido uno de los siguientes requisitos:",
                    "        -> Poseer todos los solares del grupo de la casilla",
                    "        -> Haber caído más de dos veces en el solar");

    }


    /**
     * Se vende, de un solar dado, un número de edificios de un tipo dado
     *
     * @param tipoEdificio tipo de edificio a edificar
     * @param cantidad     cantidad de edificios a vender
     * @param solar        solar cuyos edificios se van a vender
     */
    public void venderEdificio(TipoEdificio tipoEdificio, int cantidad, Solar solar) {

        if (tipoEdificio == null) {
            System.err.println("Tipo de edificio no inicializado");
            System.exit(1);
        }

        if (solar == null) {
            System.err.println("Solar no inicializado");
            System.exit(1);
        }

        if (!solar.getPropietario().equals(this)) {
            Output.sugerencia("El solar no es de su propiedad");
            return;
        }

        if (solar.isHipotecada()) {
            Output.sugerencia("El solar se encuentra hipotecado");
            return;
        }

        if (solar.getEdificiosContenidos().isEmpty()) {
            Output.sugerencia("No existen edificios en el solar dado");
            return;
        }

        if (cantidad < 1) {
            Output.sugerencia("Debe venderse al menos un edificio");
            return;
        }

        solar.venderEdificio(this, tipoEdificio, cantidad);
    }


    /**
     * Se procesa un tipo de cobro dado
     *
     * @param tipoCobro tipo de cobro a procesar
     */
    private void cobrarCarta(TipoCobro tipoCobro) {

        setFortuna(getFortuna() + tipoCobro.getImporte());
        incrementarPremiosInversionesOBote(tipoCobro.getImporte());
        Output.respuesta("Se han cobrado " + tipoCobro.getImporte() + "K €");

    }


    /**
     * Se procesa un tipo de pago dado
     *
     * @param tipoPago tipo de pago a procesar
     */
    private void pagarCarta(TipoPago tipoPago) {

        final String receptor = tipoPago.getNombreReceptor();

        int importe = 0;

        // Si el importe debe ser calculado, se trata de la casilla de pago de impuesto por bienes inmuebles
        if (tipoPago.isImporteCalculado()) {

            // Se obtienen las propiedades del jugador
            ArrayList<Casilla> propiedades = getPropiedades();

            // Se recorren los edificios de cada casilla sumando el correspiente importe
            for (Casilla casilla : propiedades) {

                importe += casilla.getEdificiosContenidos().get(TipoEdificio.casa).size() * 400;
                importe += casilla.getEdificiosContenidos().get(TipoEdificio.hotel).size() * 1150;
                importe += casilla.getEdificiosContenidos().get(TipoEdificio.piscina).size() * 200;
                importe += casilla.getEdificiosContenidos().get(TipoEdificio.pistaDeporte).size() * 750;

            }
        }

        // En caso contrario, el importe se obtiene directamente del tipo de pago
        else
            importe = tipoPago.getImporte();

        // Ahora se puede, o bien pagar a la banca
        if (receptor.equals("banca")) {
            pagar(getAvatar().getTablero().getJuego().getBanca(), importe);
            incrementarPagoTasasEImpuestos(importe);
            // Se incrementa el bote en el parking
            final Casilla parking = getAvatar().getTablero().getCasillas().get(Constantes.POSICION_PARKING / 10).get(Constantes.POSICION_PARKING % 10);
            parking.setAlquiler(parking.getAlquiler() + importe);
            // O bien pagar a todos los jugadores, en el caso de las cartas de pago de un alquiler en Cannes o pago por ser
            // escodigo presidente de la junta directiva
        } else {

            final Collection<Jugador> jugadores = getAvatar().getTablero().getJuego().getJugadores().values();

            incrementarPagoTasasEImpuestos(importe);
            pagar(new ArrayList<>(jugadores), importe);
        }
    }


    /**
     * Se procesa un tipo de movimiento dado
     *
     * @param tipoMovimiento tipo de movimiento a procesar
     */
    private void moverCarta(TipoMovimiento tipoMovimiento) {

        // Nombre de la casilla de destino
        final String nombreDestino = tipoMovimiento.getNombreCasillaDestino();

        // Posición actual
        int posicionActual = getAvatar().getPosicion().getPosicionEnTablero();
        // Posición de destino
        int posicionDestino;

        // Número de casillas a moverse
        int numeroCasillas;

        // Si el movimiento actual del avatar es el avanzado
        boolean movimientoEstandar = getAvatar().isMovimientoEstandar();


        // Si el movimiento se efectúa directamente a una casilla dada
        if (tipoMovimiento.isMoverseDirectamente()) {

            switch (nombreDestino) {

                // Si el destino es la cárcel, simplemente se encarcela al avatar del jugador
                case "Azkaban":
                    getAvatar().caerEnIrACarcel();
                    return;

                // Si el destino es la próxima casilla de transporte (los transportes se encuentran en las posiciones
                // 5, 15, 25 y 35
                case "transporte":

                    // La siguiente casilla de transporte puede estar como mucho a 10 casillas de la actual
                    posicionDestino = posicionActual + 10;
                    posicionDestino -= (posicionDestino - 5) % 10;

                    posicionDestino %= 40;
                    break;

                // En caso contrario, se obtiene la posición de destino directamente
                default:
                    final Casilla destino = getAvatar().getTablero().getCasillasTablero().get(nombreDestino);
                    posicionDestino = destino.getPosicionEnTablero();
                    break;
            }
        }

        // O sino, es el caso de la carta en la que se retroceden tres casillas
        else
            posicionDestino = posicionActual + tipoMovimiento.getCasillasDesplazarse();

        // Se calcula el número de casillas a avanzar (el valor de posicionDestino es incrementado en una vuelta
        // para realizar después el módulo y evitar así resultados negativos)
        numeroCasillas = (posicionDestino + 40 - posicionActual) % 40;

        // Se indica que aún no se ha movido las casillas correspondientes a la tirada
        getAvatar().sethaMovidoCasillasTirada(false);
        // Se indica el número de casillas restantes por moverse
        getAvatar().setCasillasRestantesPorMoverse(numeroCasillas + getAvatar().getCasillasRestantesPorMoverse());

        // Si el movimiento no es el estándar, se cambia
        if (!movimientoEstandar)
            getAvatar().switchMovimiento(true, false);

        // Se avanzan las casillas dadas
        getAvatar().avanzar(numeroCasillas, tipoMovimiento.isCobrarCasillaSalida(), false, tipoMovimiento.getMultiplicadorPago());

        // Y se devuelve el modo de movimiento a su estado original si fue modificado
        if (!movimientoEstandar)
            getAvatar().switchMovimiento(true, false);
    }


    /**
     * Se actúa en función de lo que indica una carta dada
     *
     * @param carta carta en función de cual actuar
     */
    public void leerCarta(Carta carta) {

        if (carta == null) {
            System.err.println("Carta no inicializada");
            System.exit(1);
        }

        //Carta miCarta = new Carta(TipoAccion.pago, TipoPago.pagarMatriculaColegio);

        final TipoAccion tipoAccion = carta.getTipoAccion();

        switch (tipoAccion) {

            case cobro:
                final TipoCobro tipoCobro = (TipoCobro) carta.getAccion();
                Output.respuesta(tipoCobro.getDescripcion());
                cobrarCarta(tipoCobro);
                break;

            case movimiento:
                final TipoMovimiento tipoMovimiento = (TipoMovimiento) carta.getAccion();
                Output.respuesta(tipoMovimiento.getDescripcion());
                moverCarta(tipoMovimiento);
                break;

            case pago:
                final TipoPago tipoPago = (TipoPago) carta.getAccion();
                Output.respuesta(tipoPago.getDescripcion());
                pagarCarta(tipoPago);
                break;
        }
    }


    public void


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

}

