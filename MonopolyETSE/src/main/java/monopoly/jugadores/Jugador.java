package monopoly.jugadores;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.Dado;
import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;
import monopoly.tablero.TipoGrupo;

import java.util.ArrayList;

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


    /* Métodos */

    /**
     * Se redirige un pago a un jugador con una cantidad en punto flotante a la función que gestiona el pago con un
     * número entero
     * @param receptor jugador al que pagar el importe
     * @param importe cantidad a pagar
     */
    public void pagar(Jugador receptor, double importe) {
        pagar(receptor, ( int ) importe );
    }


    /**
     * Se paga a otro jugador una cantidad dada; en caso de no disponer de suficiente liquidez, el jugador cae en
     * bancarrota y sus propiedades se transfieren al deudor
     *
     * @param receptor jugador al que pagar el importe
     * @param importe cantidad a pagar
     */
    public void pagar(Jugador receptor, int importe) {

        if (receptor == null) {
            System.err.println("Jugador no inicializado.");
            return;
        }

        if (importe < 0) {
            Output.sugerencia("No se puede pagar a un jugador una cantidad menor a 0.");
            return;
        }

        // Si el jugador cayese en bancarrota, se transfieren al receptor las propiedades del jugador
        if (balanceNegativoTrasPago(importe)) {

            ArrayList<Casilla> propiedadesEndeudado = getPropiedades();

            for (Casilla casilla : propiedadesEndeudado)
                transferirCasilla(this, receptor, casilla);

            setEstaBancarrota(true);

            Output.respuesta("¡El jugador ha caído en bancarrota!",
                    "Transfiriendo todas las propiedades al jugador " + receptor.getNombre());

        }

        // En caso contrario, dispone de la suficiente liquidez como para pagar
        else {
            setFortuna(getFortuna() - importe);

            Output.respuesta("Se ha efectuado un pago:",
                    "        -> Receptor: " + receptor.getNombre(),
                    "        -> Importe: " + importe);
            receptor.setFortuna(receptor.getFortuna() + importe);
        }

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
            return;
        }

        if (casilla == null) {
            System.err.println("Casilla no inicializada.");
            return;
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

        // Si el jugador no dispone de suficiente liquidez como para llevar a cabo la compra
        if (balanceNegativoTrasPago(casilla.getGrupo().getPrecio())) {
            Output.respuesta("El jugador no dispone de suficiente liquidez como para realiza la compra.");
            return;

        } else {

            int importe = 0;
            // Si no es un solar, el alquiler es el precio del grupo
            if (getAvatar().getPosicion().getGrupo().getTipo() == TipoGrupo.servicios ||
                    getAvatar().getPosicion().getGrupo().getTipo() == TipoGrupo.transporte) {

                importe = casilla.getGrupo().getPrecio();
                setFortuna(getFortuna() - importe);
                casilla.setComprable(false);
                casilla.setAlquiler(importe);
                transferirCasilla(vendedor, this, casilla);

            }
            // Si es un solar, el alquiler es proporcional al número de casillas del grupo
            else {

                importe = (int) (casilla.getGrupo().getPrecio() / (double) casilla.getGrupo().getCasillas().size());
                setFortuna(getFortuna() - importe);
                casilla.setComprable(false);
                casilla.setAlquiler((int) (0.1 * importe));
                transferirCasilla(vendedor, this, casilla);

            }

            Output.respuesta("Se ha efectuado un pago:",
                    "        -> Receptor: " + vendedor.getNombre(),
                    "        -> Importe: " + importe);

        }
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
            return;
        }

        if (casilla.isHipotecada()) {
            Output.sugerencia("La casilla ya se encuentra hipotecada.");
            return;
        }

        int importe = 0;
        // Al hipotecar una casilla, tan sólo se recupera la mitad de su valor original; el alquiler es un 10% del
        // importe de compra
        importe = 5 * casilla.getAlquiler();
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
            return;
        }

        if (!casilla.isHipotecada()) {
            Output.sugerencia("Error: la casilla no se encuentra hipotecada.");
            return;
        }

        // Si el jugador no dispone de la suficiente liquidez para deshipotecar la casilla; debe pagarse un 10% a
        // mayores del valor obtenido al hipotecarla
        int importe = (int) (casilla.getAlquiler() * 5 * 1.10);
        if (balanceNegativoTrasPago(importe)) {
            Output.respuesta("El jugador no dispone de suficiente liquidez como para deshipotecar la casilla.");
            return;
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
            return;
        }

        int primeraTirada = dado.lanzarDado();
        int segundaTirada = dado.lanzarDado();
        boolean dobles = primeraTirada == segundaTirada;

        Output.respuesta("Se han tirado los dados:",
                "        -> Primer dado: " + primeraTirada,
                "        -> Segundo dado: " + segundaTirada,
                "        -> ¿Han sido dobles?: " + (dobles ? "sí" : "no"));

        setTiradasEnTurno(getTiradasEnTurno() + 1);

        // Si se han sacado tres dobles, el jugador es encarcelado
        if (getTiradasEnTurno() == 3 && dobles)
            getAvatar().caerEnIrACarcel();

            // En caso contrario, se mueve normalmente
        else {
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
     * Se transfiere una casilla dada de un jugador a otro
     *
     * @param emisor   jugador que posee la casilla a transferir
     * @param receptor jugador que va a obtener la casilla
     * @param casilla  casilla a transferir
     */
    private void transferirCasilla(Jugador emisor, Jugador receptor, Casilla casilla) {

        if (emisor == null) {
            System.err.println("Emisor no inicializado.");
            return;
        }

        if (receptor == null) {
            System.err.println("Receptor no inicializado.");
            return;
        }

        if (casilla == null) {
            System.err.println("Casilla no inicializada.");
            return;
        }

        casilla.setPropietario(receptor);
        receptor.getPropiedades().add(casilla);
        emisor.getPropiedades().remove(casilla);

        Output.respuesta("Se ha transferido la casilla:",
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Casilla: " + casilla.getNombre());

    }


    /**
     * Se calcula el número de casillas de transporte obtenidas por el jugador
     *
     * @return número de casillas de transporte obtenidas
     */
    public int numeroTransportesObtenidos() {

        int numero = 0;

        for (Casilla casilla : getPropiedades()) {

            if (casilla.getGrupo().getTipo() == TipoGrupo.transporte)
                numero++;

        }

        return (numero);


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
        if (this.getAvatar().getIdentificador() != otro.getAvatar().getIdentificador()) return (false);

        /* Si no se ha cumplido ninguna condición anterior, son el mismo objeto */
        return (true);

    } /* Fin del método equals */

}

