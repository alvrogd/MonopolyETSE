package monopoly.jugadores;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.Dado;
import monopoly.tablero.Casilla;
import monopoly.tablero.Tablero;

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
            Output.errorComando("Error: tablero no inicializado.");
            System.exit(1);
        }

        if (tipoAvatar == null) {
            Output.errorComando("Error: tipo de avatar no inicializado.");
            System.exit(1);
        }

        if (casillaInicial == null) {
            Output.errorComando("Error: casilla inicial no inicializada.");
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
            Output.sugerencia("Error: la fortuna de un jugador no puede ser menor a 0.");
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
            Output.sugerencia("Error: propiedades no inicializadas.");
            return;
        }

        for (Casilla casilla : propiedades) {

            if (casilla == null) {
                Output.sugerencia("Error: casilla no inicializada.");
                return;
            }

        }

        this.propiedades = propiedades;
    }



    /* Métodos */

    public void pagar(Jugador receptor, int importe) {

        if (receptor == null) {
            Output.sugerencia("Error: jugador no inicializado.");
            return;
        }

        if (importe < 0) {
            Output.sugerencia("Error: no se puede pagar a un jugador una cantidad menor a 0.");
            return;
        }

        // Si el jugador cayese en bancarrota, se transfieren al receptor las propiedades del jugador
        if (balanceNegativoTrasPago(importe)) {

            ArrayList<Casilla> propiedadesEndeudado = getPropiedades();

            for (Casilla casilla : propiedadesEndeudado)
                transferirCasilla(this, receptor, casilla);

            setEstaBancarrota(true);

        }

        // En caso contrario, dispone de la suficiente liquidez como para pagar
        else {
            setFortuna(getFortuna() - importe);
            receptor.setFortuna(receptor.getFortuna() + importe);
        }

    }


    public void comprar(Jugador vendedor, Casilla casilla) {

        if (vendedor == null) {
            Output.sugerencia("Error: jugador no inicializado.");
            return;
        }

        if (casilla == null) {
            Output.sugerencia("Error: casilla no inicializada.");
            return;
        }

        // Si la casilla no pertenece a la banca
        if (!getAvatar().getPosicion().getPropietario().equals(getAvatar().getTablero().getBanca())) {
            System.out.println("La casilla no pertenece a la banca");
            return;
        }

        // Si el jugador no dispone de suficiente liquidez como para llevar a cabo la compra
        if (balanceNegativoTrasPago(casilla.getGrupo().getPrecio())) {
            System.out.println("El jugador no dispone de suficiente liquidez como para realiza la compra.");
            return;

        } else {
            setFortuna(getFortuna() - casilla.getGrupo().getPrecio());
            casilla.setComprable(false);
            casilla.setAlquiler(casilla.getGrupo().getPrecio() / casilla.getGrupo().getCasillas().size());
            transferirCasilla(vendedor, this, casilla);
        }
    }


    public void hipotecar(Casilla casilla) {

        if (casilla == null) {
            Output.sugerencia("Error: casilla no inicializada.");
            return;
        }

        if (casilla.isHipotecada()) {
            Output.sugerencia("Error: la casilla ya se encuentra hipotecada.");
            return;
        }

        // Al hipotecar una casilla, tan sólo se recupera la mitad de su valor original
        setFortuna(getFortuna() + ((casilla.getGrupo().getTipo().getPrecioInicial() /
                casilla.getGrupo().getCasillas().size()) / 2));
        casilla.setHipotecada(true);

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
        if (balanceNegativoTrasPago((int) (casilla.getGrupo().getTipo().getPrecioInicial() /
                casilla.getGrupo().getCasillas().size() * 1.10))) {
            System.out.println("El jugador no dispone de suficiente liquidez como para deshipotecar la casilla.");
            return;
        } else {
            setFortuna((int) (getFortuna() - casilla.getGrupo().getTipo().getPrecioInicial() /
                    casilla.getGrupo().getCasillas().size() * 1.10));
            casilla.setHipotecada(false);
        }


    }


    public void lanzarDados(Dado dado) {

        if (dado == null) {
            Output.sugerencia("Error: dado no inicializado.");
            return;
        }

        int primeraTirada = dado.lanzarDado();
        int segundaTirada = dado.lanzarDado();
        boolean dobles = primeraTirada == segundaTirada;

        getAvatar().mover(primeraTirada + segundaTirada, dobles);

    }


    private boolean balanceNegativoTrasPago(int importe) {

        if (importe < 0.0) {
            Output.sugerencia("Error: el importe de un pago no puede ser negativo.");
            // Se devuelve true dado que los métodos que emplean a este continuan la transacción correspondiente en
            // caso de obtener false al llamarlo
            return (true);
        }
        return ((getFortuna() - importe) < 0.0);
    }


    private void transferirCasilla(Jugador emisor, Jugador receptor, Casilla casilla) {

        if (emisor == null) {
            Output.sugerencia("Error: emisor no inicializado.");
            return;
        }

        if (receptor == null) {
            Output.sugerencia("Error: receptor no inicializado.");
            return;
        }

        if (casilla == null) {
            Output.sugerencia("Error: casilla no inicializada.");
            return;
        }

        casilla.setPropietario(receptor);
        receptor.getPropiedades().add(casilla);
        emisor.getPropiedades().remove(casilla);

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

