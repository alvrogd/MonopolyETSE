package monopoly.jugadores;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.Dado;
import monopoly.tablero.*;
import monopoly.tablero.cartas.*;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Edificio;
import monopoly.tablero.jerarquiaCasillas.Grupo;

import java.util.ArrayList;
import java.util.Collection;

public class Jugador {

    /* Atributos */

    // Nombre del jugador
    private final String nombre;
    // Avatar que lo representa en el tablero
    private final Avatar avatar;

    // Cantidad de dinero disponible
    private int fortuna;
    // Si se encuentra en bancarrota o no
    private boolean estaBancarrota;

    // Propiedades en posesión
    private ArrayList<Casilla> propiedades;

    // Veces que se han tirado los dados en un turno
    private int tiradasEnTurno;
    // Cantidad de turnos penalizado sin poder tirar los dados
    private int turnosPenalizado;

    //Estadísticas jugador
    private int dineroInvertido;
    private int pagoDeAlquileres;
    private int cobroDeAlquileres;
    private int pasarPorCasillaDeSalida;
    private int premiosInversionesOBote;
    private int vecesEnLaCarcel;
    private int pagoTasasEImpuestos;

    //Atributos para las estadísticas globales
    private int valorDados;

    /* Constructores */

    /**
     * Constructor diseñado para crear el avatar de la Banca al inicializar el juego
     *
     * @param nombre el nombre de la Banca
     */
    public Jugador(String nombre) {

        if (nombre == null) {
            System.err.println("Nombre no inicializado.");
            System.exit(1);
        }

        this.nombre = nombre;

        this.avatar = new Avatar(this);

        // Para evitar overflow cuando se pague a la banca
        this.fortuna = Integer.MAX_VALUE / 2;
        this.estaBancarrota = false;

        this.propiedades = new ArrayList<>();

        this.tiradasEnTurno = 0;

        this.turnosPenalizado = 0;

        this.dineroInvertido = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorCasillaDeSalida = 0;
        this.premiosInversionesOBote = 0;
        this.vecesEnLaCarcel = 0;
        this.pagoTasasEImpuestos = 0;

        this.valorDados = 0;

    }


    /**
     * Constructor que crea el avatar de un jugador normal
     *
     * @param nombre         nombre del jugador a crear
     * @param tablero        tablero del juego
     * @param tipoAvatar     tipo de avatar que crear para el jugador
     * @param casillaInicial casilla en la que establecer inicialmente al avatar del jugador
     */
    public Jugador(String nombre, Tablero tablero, TipoAvatar tipoAvatar, Casilla casillaInicial) {

        if (nombre == null) {
            System.err.println("Nombre no inicializado.");
            System.exit(1);
        }

        if (tablero == null) {
            System.err.println("Tablero no inicializado.");
            System.exit(1);
        }

        if (tipoAvatar == null) {
            System.err.println("Tipo de avatar no inicializado.");
            System.exit(1);
        }

        if (casillaInicial == null) {
            System.err.println("Casilla inicial no inicializada.");
            System.exit(1);
        }

        this.nombre = nombre;

        this.avatar = new Avatar(this, tablero, tipoAvatar, casillaInicial);

        this.fortuna = Constantes.DINERO_INICIAL;
        this.estaBancarrota = false;

        this.propiedades = new ArrayList<>();

        this.tiradasEnTurno = 0;

        this.turnosPenalizado = 0;

        this.dineroInvertido = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorCasillaDeSalida = 0;
        this.premiosInversionesOBote = 0;
        this.vecesEnLaCarcel = 0;
        this.pagoTasasEImpuestos = 0;

    }



    /*Getters y setters*/

    public String getNombre() {
        return (nombre);
    }


    /* No se implementa el setter de nombre dado que es una constante */


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

            System.err.println("Valor de dados no puede ser menor que 0.");
            return;
        }

        this.valorDados = valorDados;
    }


    public void incrementarValorDados(int valorDados) {

        if (valorDados < 0) {

            System.err.println("Valor de dados no puede ser menor que 0.");
            return;
        }

        setValorDados(getValorDados() + valorDados);
    }


    public int getFortuna() {
        return (fortuna);
    }


    public void setFortuna(int fortuna) {

        if (fortuna < 0) {
            Output.sugerencia("La fortuna de un jugador no puede ser menor a 0.");
            return;
        }

        this.fortuna = fortuna;

    }


    public int getDineroInvertido() {
        return dineroInvertido;
    }


    public void setDineroInvertido(int dineroInvertido) {

        if (dineroInvertido < 0) {

            System.err.println("El dinero invertido debe ser mayor que 0.");
            return;
        }

        this.dineroInvertido = dineroInvertido;
    }


    public void incrementarDineroInvertido(int dineroInvertido) {

        if (dineroInvertido < 0) {

            System.err.println("El dinero invertido debe ser mayor que 0.");
            return;
        }

        setDineroInvertido(getDineroInvertido() + dineroInvertido);
    }


    public int getPagoDeAlquileres() {
        return pagoDeAlquileres;
    }


    public void setPagoDeAlquileres(int pagoDeAlquileres) {

        if (pagoDeAlquileres < 0) {

            System.err.println("El pago de alquileres debe ser mayor que 0.");
            return;
        }

        this.pagoDeAlquileres = pagoDeAlquileres;
    }


    public void incrementarPagoDeAlquileres(int pagoDeAlquileres) {

        if (pagoDeAlquileres < 0) {

            System.err.println("El pago de alquileres debe ser mayor que 0.");
            return;
        }

        setPagoDeAlquileres(getPagoDeAlquileres() + pagoDeAlquileres);
    }


    public int getCobroDeAlquileres() {
        return cobroDeAlquileres;
    }


    public void setCobroDeAlquileres(int cobroDeAlquileres) {

        if (cobroDeAlquileres < 0) {

            System.err.println("El cobro de alquiler debe ser mayor que 0.");
            return;
        }

        this.cobroDeAlquileres = cobroDeAlquileres;
    }


    public void incrementarCobroDeAlquileres(int cobroDeAlquileres) {

        if (cobroDeAlquileres < 0) {

            System.err.println("El cobro de alquiler debe ser mayor que 0.");
            return;
        }

        setCobroDeAlquileres(getCobroDeAlquileres() + cobroDeAlquileres);
    }


    public int getPagoTasasEImpuestos() {
        return pagoTasasEImpuestos;
    }


    public void setPagoTasasEImpuestos(int pagoTasasEImpuestos) {

        if (pagoTasasEImpuestos < 0) {

            System.err.println("El pago de tasas e impuestos debe ser mayor que 0.");
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

            System.err.println("El número de veces que se ha pasado por la casilla de salida debe ser mayor que 0.");
            return;
        }

        this.pasarPorCasillaDeSalida = pasarPorCasillaDeSalida;
    }


    public void incrementarPasarPorCasillaDeSalida(int pasarPorCasillaDeSalida) {

        if (pasarPorCasillaDeSalida < 0) {

            System.err.println("El número de veces que se ha pasado por la casilla de salida debe ser mayor que 0.");
            return;
        }

        setPasarPorCasillaDeSalida(getPasarPorCasillaDeSalida() + pasarPorCasillaDeSalida);
    }


    public int getPremiosInversionesOBote() {
        return premiosInversionesOBote;
    }


    public void setPremiosInversionesOBote(int premiosInversionesOBote) {

        if (premiosInversionesOBote < 0) {

            System.err.println("Los premios de inversiones o bote debe ser mayor que 0.");
            return;
        }

        this.premiosInversionesOBote = premiosInversionesOBote;
    }


    public void incrementarPremiosInversionesOBote(int premiosInversionesOBote) {

        if (premiosInversionesOBote < 0) {

            System.err.println("Los premios de inversiones o bote debe ser mayor que 0.");
            return;
        }

        setPremiosInversionesOBote(getPremiosInversionesOBote() + premiosInversionesOBote);
    }

    public int getVecesEnLaCarcel() {
        return vecesEnLaCarcel;
    }


    public void setVecesEnLaCarcel(int vecesEnLaCarcel) {

        if (vecesEnLaCarcel < 0) {

            System.err.println("El número de veces en la carcel debe ser mayor que 0.");
            return;
        }

        this.vecesEnLaCarcel = vecesEnLaCarcel;
    }


    public void incrementarVecesEnLaCarcel(int vecesEnLaCarcel) {

        if (vecesEnLaCarcel < 0) {

            System.err.println("El número de veces en la carcel debe ser mayor que 0.");
            return;
        }

        setVecesEnLaCarcel(getVecesEnLaCarcel() + vecesEnLaCarcel);
    }

    public boolean isEstaBancarrota() {
        return (estaBancarrota);
    }


    public void setEstaBancarrota(boolean estaBancarrota) {
        this.estaBancarrota = estaBancarrota;
    }


    public ArrayList<Casilla> getPropiedades() {
        return (propiedades);
    }


    public void setPropiedades(ArrayList<Casilla> propiedades) {

        if (propiedades == null) {
            System.err.println("Propiedades no inicializadas.");
            return;
        }

        for (Casilla casilla : propiedades) {

            if (casilla == null) {
                System.err.println("Casilla no inicializada.");
                return;
            }

        }

        this.propiedades = propiedades;
    }


    public int getTiradasEnTurno() {
        return tiradasEnTurno;
    }


    public void setTiradasEnTurno(int tiradasEnTurno) {

        if (tiradasEnTurno < 0) {
            Output.sugerencia("El número de tiradas en un turno no puede ser menor a 0.");
            return;
        }

        this.tiradasEnTurno = tiradasEnTurno;

    }


    public int getTurnosPenalizado() {
        return turnosPenalizado;
    }


    public void setTurnosPenalizado(int turnosPenalizado) {

        if (turnosPenalizado < 0) {
            Output.sugerencia("El número de turnos penalizados no puede ser menor a 0.");
            return;
        }

        this.turnosPenalizado = turnosPenalizado;
    }

    /* Métodos */

    /**
     * Función para calcular la fortuna total del jugador, incluyendo su fortuna en dinero, valor de las propiedades y
     * valor de los edificios.
     */
    public int calcularFortunaTotal() {

        //Dinero invertido en la compra de propiedades
        int valorPropiedades = 0;

        //Dinero invertido en la compra de edificios
        int valorEdificios = 0;

        //Se recorren las propiedades del jugador
        for (Casilla casilla : getPropiedades()) {

            //Se suma el valor de cada casilla que sea propiedad del jugador
            valorPropiedades += casilla.getImporteCompra();

            //Se recorren los edificios de cada casilla
            for (TipoEdificio tipoEdificio : TipoEdificio.values()) {

                ArrayList<Edificio> edificios = casilla.getEdificiosContenidos().get(tipoEdificio);

                for (Edificio edificio : edificios) {

                    //Se suma el valor del precio del edificio
                    valorEdificios += edificio.getPrecioCompra();

                }

            }

        }

        return (getFortuna() + valorEdificios + valorPropiedades);

    }

    /**
     * Se paga a otro jugador una cantidad dada; en caso de no disponer de suficiente liquidez, el jugador cae en
     * bancarrota y sus propiedades se transfieren al deudor
     *
     * @param receptor jugador al que pagar el importe
     * @param importe  cantidad a pagar
     */
    public void pagar(Jugador receptor, int importe) {

        if (receptor == null) {
            System.err.println("Jugador no inicializado.");
            System.exit(1);
        }

        if (importe < 0) {
            Output.sugerencia("No se puede pagar a un jugador una cantidad menor a 0.");
            return;
        }

        // Si el jugador cayese en bancarrota, se transfieren al receptor las propiedades del jugador
        if (balanceNegativoTrasPago(importe))
            caerEnBancarrota(this, receptor);

            // En caso contrario, dispone de la suficiente liquidez como para pagar
        else {
            setFortuna(getFortuna() - importe);
            incrementarPagoDeAlquileres(importe);

            Output.respuesta("Se ha efectuado un pago:",
                    "        -> Receptor: " + receptor.getNombre(),
                    "        -> Importe: " + importe);
            receptor.setFortuna(receptor.getFortuna() + importe);
            receptor.incrementarCobroDeAlquileres(importe);
        }

    }

    /**
     * Se paga a varios jugadores una cantidad dada; en caso de no disponer de suficiente liquidez, el jugador cae en
     * bancarrota y sus propiedades se transfieren al deudor
     *
     * @param jugadores jugadores a los que pagar el importe
     * @param importe   cantidad a pagar
     */
    public void pagar(ArrayList<Jugador> jugadores, int importe) {

        if (jugadores == null) {
            System.err.println("Jugadores no inicializados.");
            System.exit(1);
        }

        for (Jugador jugador : jugadores) {

            if (jugador == null) {
                System.err.println("Jugador no inicializado.");
                System.exit(1);
            }
        }

        // todo EXITS EN TODAS LAS CLASES DEL PAQUETE MONOPOLY
        if (importe < 0) {
            Output.sugerencia("No se puede pagar a un jugador una cantidad menor a 0.");
            return;
        }

        StringBuilder receptores = new StringBuilder();

        for (Jugador jugador : jugadores) {

            // Si no es el propio jugador y no ha caido en bancarrota
            if (!jugador.equals(this) && !isEstaBancarrota()) {

                // Si el jugador cayese en bancarrota, se transfieren al receptor las propiedades del jugador
                if (balanceNegativoTrasPago(importe))
                    caerEnBancarrota(this, jugador);

                    // En caso contrario, dispone de la suficiente liquidez como para pagar
                else {
                    setFortuna(getFortuna() - importe);
                    incrementarPagoDeAlquileres(importe);
                    receptores.append(jugador.getNombre()).append("  ");
                    jugador.setFortuna(jugador.getFortuna() + importe);
                    jugador.incrementarCobroDeAlquileres(importe);
                }
            }
        }

        Output.respuesta("Se han efectuado los siguientes pagos:",
                "        -> Receptores: " + receptores.toString(),
                "        -> Importe: " + importe);

    }


    /**
     * Se compra una casilla a un jugador pagando el correspondiente importe, en caso de disponer de la suficiente
     * liquidez (de momento, sólo es posible comprar casillas a la Banca)
     *
     * @param vendedor jugador al que comprar la casilla
     * @param casilla  casilla a comprar
     */
    public void comprar(Jugador vendedor, Casilla casilla) {

        if (vendedor == null) {
            System.err.println("Jugador no inicializado.");
            System.exit(1);
        }

        if (casilla == null) {
            System.err.println("Casilla no inicializada.");
            System.exit(1);
        }

        // Si la casilla no es comprable
        if (!getAvatar().getPosicion().isComprable()) {
            Output.respuesta("La casilla no es comprable");
            return;
        }

        // Si el jugador no se encuentra en la casilla a comprar
        if (getAvatar().getPosicion().getPosicionEnTablero() != casilla.getPosicionEnTablero()) {
            Output.respuesta("El jugador no se encuentra en la casilla a comprar");
            return;
        }

        // Si la casilla no pertenece a la banca
        if (!getAvatar().getPosicion().getPropietario().equals(getAvatar().getTablero().getBanca())) {
            Output.respuesta("La casilla no pertenece a la banca.");
            return;
        }

        int importe;

        // Si no es un solar, el alquiler es el precio del grupo
        if (getAvatar().getPosicion().getGrupo().getTipo() == TipoGrupo.servicios ||
                getAvatar().getPosicion().getGrupo().getTipo() == TipoGrupo.transporte) {

            importe = casilla.getGrupo().getPrecio();

            // Si el jugador no dispone de suficiente liquidez como para llevar a cabo la compra
            if (balanceNegativoTrasPago(importe)) {
                Output.respuesta("El jugador no dispone de suficiente liquidez como para realiza la compra.");
                return;
            }

            incrementarDineroInvertido(importe);
            setFortuna(getFortuna() - importe);
            casilla.setComprable(false);
            casilla.setAlquiler(importe);
            casilla.setImporteCompra(importe);
            transferirCasilla(vendedor, this, casilla);
            getAvatar().getTablero().getJuego().setHaCompradoPropiedad(true);

        }
        // Si es un solar, el alquiler es proporcional al número de casillas del grupo
        else {

            importe = (int) (casilla.getGrupo().getPrecio() / (double) casilla.getGrupo().getCasillas().size());

            // Si el jugador no dispone de suficiente liquidez como para llevar a cabo la compra
            if (balanceNegativoTrasPago(importe)) {
                Output.respuesta("El jugador no dispone de suficiente liquidez como para realiza la compra.");
                return;
            }

            incrementarDineroInvertido(importe);
            setFortuna(getFortuna() - importe);
            casilla.setComprable(false);
            casilla.setAlquiler((int) (0.1 * importe));
            casilla.setImporteCompra(importe);
            transferirCasilla(vendedor, this, casilla);
            getAvatar().getTablero().getJuego().setHaCompradoPropiedad(true);

        }

        Output.respuesta("Se ha efectuado un pago:",
                "        -> Receptor: " + vendedor.getNombre(),
                "        -> Importe: " + importe);

    }


    /**
     * Se hipoteca una casilla, no pudiendo cobrar alquiler por ella a partir de ahora, a cambio de obtener la mitad
     * del importe pagado para adquirirla
     *
     * @param casilla casilla a hipotecar
     */
    public void hipotecar(Casilla casilla) {

        if (casilla == null) {
            System.err.println("Casilla no inicializada.");
            System.exit(1);
        }

        if (!casilla.getPropietario().equals(this)) {
            Output.sugerencia("La casilla no es de su propiedad");
            return;
        }

        if (casilla.isHipotecada()) {
            Output.sugerencia("La casilla ya se encuentra hipotecada.");
            return;
        }

        if (casilla.tieneEdificios()) {
            Output.sugerencia("No se puede hipotecar una casilla mientras esta contenga edificios");
            return;
        }

        // Al hipotecar una casilla, tan sólo se recupera la mitad de su valor original
        int importe = (int) ((double) casilla.getImporteCompra() * 0.5);

        setFortuna(getFortuna() + importe);
        casilla.setHipotecada(true);

        Output.respuesta("Se ha hipotecado la casilla:",
                "        -> Importe obtenido: " + importe);

    }


    /**
     * Se deshipoteca una casilla, volviendo a poder cobrar alquiler por ella a partir de ahora, a cambio de pagar el
     * el importe obtenido por la hipoteca e incrementado en un 10%
     *
     * @param casilla casilla a deshipotecar
     */
    public void deshipotecar(Casilla casilla) {

        if (casilla == null) {
            System.err.println("Error: casilla no inicializada.");
            System.exit(1);
        }

        if (!casilla.getPropietario().equals(this)) {
            Output.sugerencia("La casilla no es de su propiedad");
            return;
        }

        if (!casilla.isHipotecada()) {
            Output.sugerencia("La casilla no se encuentra hipotecada.");
            return;
        }

        // Si el jugador no dispone de la suficiente liquidez para deshipotecar la casilla; debe pagarse un 10% a
        // mayores del valor obtenido al hipotecarla
        int importe = (int) ((double) casilla.getImporteCompra() * 0.5 * 1.10);

        if (balanceNegativoTrasPago(importe)) {
            Output.respuesta("El jugador no dispone de suficiente liquidez como para deshipotecar la casilla.");

        } else {
            setFortuna(getFortuna() - importe);
            casilla.setHipotecada(false);

            Output.respuesta("Se ha deshipotecado la casilla:",
                    "        -> Importe pagado: " + importe);
        }

    }


    /**
     * Se lanzan dos dados, y se mueve el avatar del jugador tantas casillas como sea la suma de los valores dados por
     * los dados, además de indicarle si se han sacado dobles
     *
     * @param dado instancia del dado a tirar
     */
    public void lanzarDados(Dado dado) {

        if (dado == null) {
            System.err.println("Dado no inicializado.");
            System.exit(1);
        }

        if (getTurnosPenalizado() > 0) {
            Output.sugerencia("El jugador se encuentra penalizado durante " + getTurnosPenalizado() +
                    " turno(s).");
            getAvatar().getTablero().getJuego().setHaLanzadoDados(true);
            return;
        }

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

        // Si se han sacado tres dobles y no se trata de un avatar coche en movimiento avanzado, el jugador es
        // encarcelado
        if (getTiradasEnTurno() == 3 && dobles && (!getAvatar().getTipo().equals(TipoAvatar.coche) &&
                !getAvatar().isMovimientoEstandar()))

            getAvatar().caerEnIrACarcel();

            // En caso contrario, depende de si es un coche en modo avanzado
        else {

            if (getAvatar().getTipo().equals(TipoAvatar.coche) && !getAvatar().isMovimientoEstandar()) {

                // Si ha sacado un 4 o más y no ha hecho el máximo de tiradas en un turno
                if (getTiradasEnTurno() < 4 && (primeraTirada + segundaTirada) >= 4)
                    getAvatar().getTablero().getJuego().setHaLanzadoDados(false);

                else
                    getAvatar().getTablero().getJuego().setHaLanzadoDados(true);

            }

            // Sino, depende de si se han sacado dobles
            else
                getAvatar().getTablero().getJuego().setHaLanzadoDados(!dobles);

            getAvatar().mover(primeraTirada + segundaTirada, dobles);
        }
    }


    /**
     * Se comprueba si la fortuna del jugador quedaría negativa tras el pago de un importe dado
     *
     * @param importe cantidad con la que comprobar el balance
     * @return si la fortuna restante sería negativa o no
     */
    public boolean balanceNegativoTrasPago(int importe) {

        if (importe < 0.0) {
            Output.sugerencia("El importe de un pago no puede ser negativo.");
            // Se devuelve true dado que los métodos que emplean a este continuan la transacción correspondiente en
            // caso de obtener false al llamarlo
            return (true);
        }
        return ((getFortuna() - importe) < 0.0);
    }


    /**
     * Un jugador dado cae en bancarrota, transfiriendo todas sus propiedades al deudor
     *
     * @param endeudado jugador que cae en bancarrota
     * @param deudor    deudor del jugador que cae en bancarrota
     */
    private void caerEnBancarrota(Jugador endeudado, Jugador deudor) {

        if (endeudado == null) {
            System.err.println("Endeudado no inicializado");
            System.exit(1);
        }

        if (deudor == null) {
            System.err.println("Deudor no inicializado");
            System.exit(1);
        }

        if (isEstaBancarrota())
            return;

        Output.respuesta("¡El jugador ha caído en bancarrota!",
                "Transfiriendo todas las propiedades al jugador " + deudor.getNombre());

        transferirCasilla(endeudado, deudor, endeudado.getPropiedades());
        setEstaBancarrota(true);
    }


    /**
     * Se transfiere una casilla dada de un jugador a otro
     *
     * @param emisor   jugador que posee la casilla a transferir
     * @param receptor jugador que va a obtener la casilla
     * @param casilla  casilla a transferir
     */
    private void transferirCasilla(Jugador emisor, Jugador receptor, Casilla casilla) {

        if (emisor == null) {
            System.err.println("Emisor no inicializado.");
            System.exit(1);
        }

        if (receptor == null) {
            System.err.println("Receptor no inicializado.");
            System.exit(1);
        }

        if (casilla == null) {
            System.err.println("Casilla no inicializada.");
            System.exit(1);
        }

        casilla.setPropietario(receptor);
        receptor.getPropiedades().add(casilla);
        emisor.getPropiedades().remove(casilla);

        Output.respuesta("Se ha transferido la casilla:",
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Casilla: " + casilla.getNombre());

    }


    /**
     * Se transfiere un conjunto de casillas dadas de un jugador a otro
     *
     * @param emisor   jugador que posee la casilla a transferir
     * @param receptor jugador que va a obtener la casilla
     * @param casillas casillas a transferir
     */
    private void transferirCasilla(Jugador emisor, Jugador receptor, ArrayList<Casilla> casillas) {

        if (emisor == null) {
            System.err.println("Emisor no inicializado.");
            System.exit(1);
        }

        if (receptor == null) {
            System.err.println("Receptor no inicializado.");
            System.exit(1);
        }

        if (casillas == null) {
            System.err.println("Casillas no inicializadas.");
            System.exit(1);
        }

        for (Casilla casilla : casillas) {

            if (casilla == null) {
                System.err.println("Casilla no inicializada");
                System.exit(1);
            }
        }

        StringBuilder transferidas = new StringBuilder();

        while (!casillas.isEmpty()) {

            Casilla casilla = casillas.get(0);
            casilla.setPropietario(receptor);
            receptor.getPropiedades().add(casilla);
            emisor.getPropiedades().remove(casilla);
            transferidas.append(casilla.getNombre()).append("  ");

        }

        Output.respuesta("Se han transferido las casillas:",
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Casillas: " + transferidas.toString());

    }


    /**
     * Se calcula el número de casillas de un determinado grupo obtenidas por el jugador
     *
     * @param grupo tipo del grupo a comprobar
     * @return número de casillas obtenidas del grupo dado
     */
    public int numeroCasillasObtenidas(TipoGrupo grupo) {

        int numero = 0;

        for (Casilla casilla : getPropiedades()) {

            if (casilla.getGrupo().getTipo() == grupo)
                numero++;

        }

        return (numero);

    }


    /**
     * Se comprueba si el jugador ha obtenido todas los solares de un determinado grupo
     *
     * @param grupo tipo del grupo a comprobar
     * @return si ha obtenido todas las casillas del grupo dado
     */
    public boolean haObtenidoSolaresGrupo(Grupo grupo) {

        return (numeroCasillasObtenidas(grupo.getTipo()) == grupo.getCasillas().size());

    }


    /**
     * Se edifica en la casilla actual un tipo de edificio dado
     *
     * @param tipoEdificio tipo de edificio a edificar
     */
    public void crearEdificio(TipoEdificio tipoEdificio) {

        if (tipoEdificio == null) {
            System.err.println("Tipo de edificio no inicializado");
            System.exit(1);
        }

        final Casilla casilla = getAvatar().getPosicion();

        if (!casilla.getPropietario().equals(this)) {
            Output.sugerencia("La casilla no es de su propiedad");
            return;
        }

        if (!casilla.getGrupo().getTipo().getTipoCasilla().equals("solar")) {
            Output.respuesta("La casilla no es un solar");
            return;
        }

        if (casilla.isHipotecada()) {
            Output.sugerencia("La casilla se encuentra hipotecada.");
            return;
        }

        // El usuario debe, o bien haber caído más de dos veces en la casilla para edificar, o bien poseer todos los
        // solares del grupo
        if (getAvatar().getVecesCaidasEnPropiedades().get(casilla.getPosicionEnTablero() % 40) > 2 ||
                haObtenidoSolaresGrupo(casilla.getGrupo()))

            casilla.edificar(this, tipoEdificio);

        else
            Output.respuesta("Para edificar en una casilla, debe haber cumplido uno de los siguientes requisitos:",
                    "        -> Poseer todos los solares del grupo de la casilla",
                    "        -> Haber caído más de dos veces en el solar");

    }


    /**
     * Se vende, de una casilla dada, un número de edificios de un tipo dado
     *
     * @param tipoEdificio tipo de edificio a edificar
     * @param cantidad     cantidad de edificios a vender
     * @param casilla      casilla cuyos edificios se van a vender
     */
    public void venderEdificio(TipoEdificio tipoEdificio, int cantidad, Casilla casilla) {

        if (tipoEdificio == null) {
            System.err.println("Tipo de edificio no inicializado");
            System.exit(1);
        }

        if (casilla == null) {
            System.err.println("Casilla no inicializada");
            System.exit(1);
        }

        if (!casilla.getPropietario().equals(this)) {
            Output.sugerencia("La casilla no es de su propiedad");
            return;
        }

        if (casilla.isHipotecada()) {
            Output.sugerencia("La casilla se encuentra hipotecada.");
            return;
        }

        if (casilla.getEdificiosContenidos().isEmpty()) {
            Output.sugerencia("No existen edificios en la casilla dada");
            return;
        }

        if (cantidad < 1) {
            Output.sugerencia("Debe venderse al menos un edificio");
            return;
        }

        casilla.venderEdificio(this, tipoEdificio, cantidad);

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

