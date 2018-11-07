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
    private Integer fortuna;
    // Si se encuentra en bancarrota o no
    private boolean estaBancarrota;

    // Propiedades en posesión
    private ArrayList<Casilla> propiedades;


    /* Constructores */
    public Jugador(String nombre) {

        this.nombre = nombre;

        this.avatar = new Avatar(this);

        this.fortuna = Integer.MAX_VALUE;

        this.propiedades = new ArrayList<>();

    }

    public Jugador(String nombre, Tablero tablero, TipoAvatar tipoAvatar, Casilla casillaInicial) {

        if (tablero == null) {
            Output.errorComando("Tablero no inicializado.");
            System.exit(1);
        }

        if (tipoAvatar == null) {
            Output.errorComando("Tipo de avatar no inicializado.");
            System.exit(1);
        }

        if (casillaInicial == null) {
            Output.errorComando("Casilla inicial no inicializada.");
            System.exit(1);
        }

        this.nombre = nombre;

        this.avatar = new Avatar(this, tablero, tipoAvatar, casillaInicial);

        this.fortuna = Constantes.DINERO_INICIAL;

        this.propiedades = new ArrayList<>();

    }



    /*Getters y setters*/

    public String getNombre() {
        return (nombre);
    }


    public Avatar getAvatar() {
        return (avatar);
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
            Output.sugerencia("Propiedades no inicializadas.");
            return;
        }

        for (Casilla casilla : propiedades) {

            if (casilla == null) {
                Output.sugerencia("Casilla no inicializada.");
                return;
            }

        }

        this.propiedades = propiedades;
    }



    /* Métodos */

    public void pagar(Jugador receptor, int importe) {

        if (receptor == null) {
            Output.sugerencia("Jugador no inicializado.");
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


    public void comprar(Jugador vendedor, Casilla casilla) {

        if (vendedor == null) {
            Output.sugerencia("Jugador no inicializado.");
            return;
        }

        if (casilla == null) {
            Output.sugerencia("Casilla no inicializada.");
            return;
        }

        // Si el jugador no se encuentra en la casilla a comprar
        if( getAvatar().getPosicion().getPosicionEnTablero() != casilla.getPosicionEnTablero() ) {
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


    public void hipotecar(Casilla casilla) {

        if (casilla == null) {
            Output.sugerencia("Casilla no inicializada.");
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


    public void deshipotecar(Casilla casilla) {

        if (casilla == null) {
            Output.sugerencia("Error: casilla no inicializada.");
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


    public void lanzarDados(Dado dado) {

        if (dado == null) {
            Output.sugerencia("Dado no inicializado.");
            return;
        }

        int primeraTirada = dado.lanzarDado();
        int segundaTirada = dado.lanzarDado();
        boolean dobles = primeraTirada == segundaTirada;

        Output.respuesta("Se han tirado los dados:",
                "        -> Primer dado: " + primeraTirada,
                "        -> Segundo dado: " + segundaTirada);

        getAvatar().mover(primeraTirada + segundaTirada, dobles);

    }


    public boolean balanceNegativoTrasPago(int importe) {

        if (importe < 0.0) {
            Output.sugerencia("El importe de un pago no puede ser negativo.");
            // Se devuelve true dado que los métodos que emplean a este continuan la transacción correspondiente en
            // caso de obtener false al llamarlo
            return (true);
        }
        return ((getFortuna() - importe) < 0.0);
    }


    private void transferirCasilla(Jugador emisor, Jugador receptor, Casilla casilla) {

        if (emisor == null) {
            Output.sugerencia("Emisor no inicializado.");
            return;
        }

        if (receptor == null) {
            Output.sugerencia("Receptor no inicializado.");
            return;
        }

        if (casilla == null) {
            Output.sugerencia("Casilla no inicializada.");
            return;
        }

        casilla.setPropietario(receptor);
        receptor.getPropiedades().add(casilla);
        emisor.getPropiedades().remove(casilla);

        Output.respuesta("Se ha transferido la casilla:",
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Casilla: " + casilla.getNombre());

    }


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

