package monopoly.jugadores;

import aplicacion.salidaPantalla.Output;
import monopoly.jugadores.excepciones.EstarBancarrotaException;
import monopoly.jugadores.excepciones.NoComprarABancaException;
import monopoly.jugadores.excepciones.NoLiquidezException;
import monopoly.jugadores.excepciones.NoSerPropietarioException;
import monopoly.tablero.TipoEdificio;
import monopoly.tablero.TipoGrupo;
import monopoly.tablero.Transporte;
import monopoly.tablero.jerarquiaCasillas.*;

import java.util.ArrayList;

public abstract class Participante {

    /* Atributos */

    // Nombre del jugador
    private final String nombre;

    // Cantidad de dinero disponible
    private int fortuna;

    // Si se encuentra en bancarrota o no
    private boolean estaBancarrota;

    // Propiedades en posesión
    private ArrayList<Propiedad> propiedades;



    /* Constructor */

    public Participante(String nombre, int fortuna) {

        if( nombre == null ) {
            System.err.println( "Nombre no inicializado" );
            System.exit( 1 );
        }

        this.nombre = nombre;
        this.fortuna = fortuna;
        this.estaBancarrota = false;
        this.propiedades = new ArrayList<>();
    }



    /* Getters y setters */

    public String getNombre() {
        return (nombre);
    }


    /* No se implementa el setter de nombre dado que es una constante */


    public int getFortuna() {
        return fortuna;
    }


    public void setFortuna(int fortuna) {

        if (fortuna < 0) {
            System.err.println("La fortuna de un participante no puede ser menor a 0");
            System.exit( 1 );
        }

        this.fortuna = fortuna;
    }


    public boolean isEstaBancarrota() {
        return estaBancarrota;
    }


    public void setEstaBancarrota(boolean estaBancarrota) {
        this.estaBancarrota = estaBancarrota;
    }


    public ArrayList<Propiedad> getPropiedades() {
        return (propiedades);
    }


    public void setPropiedades(ArrayList<Propiedad> propiedades) {

        if (propiedades == null) {
            System.err.println("ArrayList de propiedades no inicializado");
            System.exit( 1 );
        }

        for (Propiedad propiedad: propiedades){

            if (propiedad == null) {
                System.err.println("Propiedad no inicializada");
                System.exit( 1 );
            }
        }

        this.propiedades = propiedades;
    }



    /* Métodos */

    /**
     * Se calcula la fortuna total del jugador, incluyendo la liquidez, el valor de las propiedades y el valor de los
     * edificios
     * @return fortuna total del jugador
     */
    public int calcularFortunaTotal() {

        // Dinero invertido en la compra de propiedades
        int valorPropiedades = 0;

        // Dinero invertido en la compra de edificios
        int valorEdificios = 0;

        // Se recorren las propiedades del jugador
        for (Propiedad propiedad: getPropiedades()) {

            // Se suma el valor de cada casilla que sea propiedad del jugador
            valorPropiedades += propiedad.getImporteCompra();

            // Se recorren los edificios de cada casilla si se trata de un solar
            if( propiedad instanceof Solar ) {

                final Solar solar = (Solar) propiedad;

                for (TipoEdificio tipoEdificio : TipoEdificio.values()) {

                    ArrayList<Edificio> edificios = solar.getEdificiosContenidos().get(tipoEdificio);

                    for (Edificio edificio : edificios) {

                        // Se suma el valor del precio del edificio
                        valorEdificios += edificio.getPrecioCompra();
                    }
                }
            }
        }

        return (getFortuna() + valorEdificios + valorPropiedades);
    }


    /**
     * Se comprueba si la fortuna del participante quedaría negativa tras el pago de un importe dado
     *
     * @param importe cantidad con la que comprobar el balance
     * @return        si la fortuna restante sería negativa o no
     */
    public boolean balanceNegativoTrasPago(int importe) {

        if (importe < 0.0) {
            System.err.println("El importe de un pago no puede ser negativo");
            // Se devuelve true dado que los métodos que emplean a este continuan la transacción correspondiente en
            // caso de obtener false al llamarlo
            System.exit( 1 );
        }

        return ((getFortuna() - importe) < 0.0);
    }


    /**
     * El participante cae en bancarrota, transfiriendo todas sus propiedades al deudor
     *
     * @param endeudado participante que cae en bancarrota
     * @param deudor    deudor del jugador que cae en bancarrota
     */
    private void caerEnBancarrota(Participante endeudado, Participante deudor) throws EstarBancarrotaException,
            NoSerPropietarioException {

        if (endeudado == null) {
            System.err.println("Endeudado no inicializado");
            System.exit(1);
        }

        if (deudor == null) {
            System.err.println("Deudor no inicializado");
            System.exit(1);
        }

        if (isEstaBancarrota())
            throw new EstarBancarrotaException("El participante ya se encuentra en bancarrota");

        Output.respuesta("¡El participante ha caído en bancarrota!",
                "Transfiriendo todas las propiedades al participante " + deudor.getNombre());

        transferirPropiedad(endeudado, deudor, endeudado.getPropiedades());
        setEstaBancarrota(true);
    }


    /**
     * Se paga a otro participante una cantidad dada; en caso de no disponer de suficiente liquidez, el participante
     * cae en bancarrota y sus propiedades se transfieren al deudor
     *
     * @param receptor participante al que pagar el importe
     * @param importe  cantidad a pagar
     * @return         si se ha efectuado correctamente el pago
     */
    public boolean pagar(Participante receptor, int importe) throws EstarBancarrotaException, NoSerPropietarioException {

        if (receptor == null) {
            System.err.println("Receptor no inicializado");
            System.exit(1);
        }

        if (importe < 0) {
            System.err.println("No se puede pagar a un participante una cantidad menor a 0");
            System.exit( 1 );
        }

        // Si el jugador cayese en bancarrota, se transfieren al receptor las propiedades del jugador
        if (balanceNegativoTrasPago(importe)) {
            caerEnBancarrota(this, receptor);
            return( false );
        }

        // En caso contrario, dispone de la suficiente liquidez como para pagar
        else {
            setFortuna(getFortuna() - importe);

            Output.respuesta("Se ha efectuado un pago:",
                    "        -> Receptor: " + receptor.getNombre(),
                    "        -> Importe: " + importe);
            receptor.setFortuna(receptor.getFortuna() + importe);

            return( true );
        }
    }


    /**
     * Se paga a varios participantes una cantidad dada; en caso de no disponer de suficiente liquidez, el participante
     * cae en bancarrota y sus propiedades se transfieren al deudor
     *
     * @param participantes participantes a los que pagar el importe
     * @param importe       cantidad a pagar
     * @return              número de pagos efectuados correctamente
     */
    public int pagar(ArrayList<Participante> participantes, int importe) throws EstarBancarrotaException,
            NoSerPropietarioException {

        if (participantes == null) {
            System.err.println("Participantes no inicializados");
            System.exit(1);
        }

        for (Participante participante : participantes) {

            if (participante == null) {
                System.err.println("Participante no inicializado");
                System.exit(1);
            }
        }

        // todo EXITS EN TODAS LAS CLASES DEL PAQUETE MONOPOLY
        if (importe < 0) {
            System.err.println("No se puede pagar a un participante una cantidad menor a 0");
            System.exit( 1 );
        }

        StringBuilder receptores = new StringBuilder();
        int pagosExitosos = 0;

        for (Participante participante : participantes ) {

            // Si no es el propio jugador y no ha caido en bancarrota
            if (!participante.equals(this) && !isEstaBancarrota()) {

                // Si el jugador cayese en bancarrota, se transfieren al receptor las propiedades del jugador
                if (balanceNegativoTrasPago(importe))
                    caerEnBancarrota(this, participante);

                // En caso contrario, dispone de la suficiente liquidez como para pagar
                else {

                    setFortuna(getFortuna() - importe);
                    receptores.append(participante.getNombre()).append("  ");
                    participante.setFortuna(participante.getFortuna() + importe);

                    pagosExitosos++;
                }
            }
        }

        Output.respuesta("Se han efectuado los siguientes pagos:",
                "        -> Receptores: " + receptores.toString(),
                "        -> Importe: " + importe);

        return( pagosExitosos );
    }


    /**
     * Se compra una propiedad a un participante pagando el correspondiente importe, en caso de disponer de la
     * suficiente liquidez
     *
     * @param vendedor   participante al que comprar la propiedad
     * @param propiedad  propiedad a comprar
     * @return           importe de la compra
     */
    public int comprar(Participante vendedor, Propiedad propiedad) throws NoComprarABancaException, NoLiquidezException,
            NoSerPropietarioException {

        if (vendedor == null) {
            System.err.println("Jugador no inicializado");
            System.exit(1);
        }

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada");
            System.exit(1);
        }

        // Si la casilla no pertenece a la banca
        if (!(propiedad.getPropietario() instanceof Banca))
            throw new NoComprarABancaException("La casilla no pertenece a la banca");

        int importe;

        // Si no es un solar, el alquiler es el precio del grupo
        if (propiedad instanceof Servicio || propiedad instanceof Transporte) {

            importe = propiedad.getGrupo().getPrecio();

            // Si el jugador no dispone de suficiente liquidez como para llevar a cabo la compra
            if (balanceNegativoTrasPago(importe))
                throw new NoLiquidezException("El participante no dispone de suficiente liquidez como para realiza " +
                        "la compra");

            setFortuna(getFortuna() - importe);
            propiedad.setComprable(false);
            propiedad.setAlquiler(importe);
            propiedad.setImporteCompra(importe);
            transferirPropiedad(vendedor, this, propiedad);

        }

        // Si es un solar, el alquiler es proporcional al número de casillas del grupo
        else {
            importe = (int) (propiedad.getGrupo().getPrecio() / (double) propiedad.getGrupo().getSolares().size());

            // Si el jugador no dispone de suficiente liquidez como para llevar a cabo la compra
            if (balanceNegativoTrasPago(importe))
                throw new NoLiquidezException("El participante no dispone de suficiente liquidez como para realiza " +
                        "la compra");

            setFortuna(getFortuna() - importe);
            propiedad.setComprable(false);
            propiedad.setAlquiler((int) (0.1 * importe));
            propiedad.setImporteCompra(importe);
            transferirPropiedad(vendedor, this, propiedad);
        }

        Output.respuesta("Se ha efectuado un pago:",
                "        -> Receptor: " + vendedor.getNombre(),
                "        -> Importe: " + importe);

        return( importe );
    }


    /**
     * Se transfiere una propiedad dada de un participante a otro
     *
     * @param emisor     participante que posee la propiedad a transferir
     * @param receptor   participante que va a obtener la propiedad
     * @param propiedad  propiedad a transferir
     */
    private void transferirPropiedad(Participante emisor, Participante receptor, Propiedad propiedad) throws
            NoSerPropietarioException {

        if (emisor == null) {
            System.err.println("Emisor no inicializado.");
            System.exit(1);
        }

        if (receptor == null) {
            System.err.println("Receptor no inicializado.");
            System.exit(1);
        }

        if (propiedad == null) {
            System.err.println("Propiedad no inicializada.");
            System.exit(1);
        }

        if (!propiedad.getPropietario().equals(emisor))
            throw new NoSerPropietarioException("La propiedad no pertenece al emisor");

        propiedad.setPropietario(receptor);
        receptor.getPropiedades().add(propiedad);
        emisor.getPropiedades().remove(propiedad);

        Output.respuesta("Se ha transferido la propiedad:",
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Propiedad: " + propiedad.getNombre());
    }


    /**
     * Se transfiere un conjunto de propiedades dadas de un participante a otro
     *
     * @param emisor      participante que posee las propiedades a transferir
     * @param receptor    participante que va a obtener laz propiedades
     * @param propiedades propiedades a transferir
     */
    private void transferirPropiedad(Participante emisor, Participante receptor, ArrayList<Propiedad> propiedades)
        throws NoSerPropietarioException {

        if (emisor == null) {
            System.err.println("Emisor no inicializado");
            System.exit(1);
        }

        if (receptor == null) {
            System.err.println("Receptor no inicializado");
            System.exit(1);
        }

        if (propiedades == null) {
            System.err.println("ArrayList de propiedades no inicializado");
            System.exit(1);
        }

        for (Propiedad propiedad : propiedades) {

            if (propiedad == null) {
                System.err.println("Propiedad no inicializada");
                System.exit(1);
            }

            if (!propiedad.getPropietario().equals(emisor))
                throw new NoSerPropietarioException("La propiedad no pertenece al emisor");
        }

        StringBuilder transferidas = new StringBuilder();

        while (!propiedades.isEmpty()) {

            Propiedad propiedad = propiedades.get(0);
            propiedad.setPropietario(receptor);
            receptor.getPropiedades().add(propiedad);
            emisor.getPropiedades().remove(propiedad);
            transferidas.append(propiedad.getNombre()).append("  ");

        }

        Output.respuesta("Se han transferido las propiedades:",
                "        -> Receptor: " + receptor.getNombre(),
                "        -> Propiedades: " + transferidas.toString());

    }


    /**
     * Se calcula el número de casillas de un determinado grupo obtenidas por el participante
     *
     * @param grupo tipo del grupo a comprobar
     * @return      número de casillas obtenidas del grupo dado
     */
    public int numeroCasillasObtenidas(TipoGrupo grupo) {

        int numero = 0;

        for (Casilla casilla : getPropiedades()) {

            if( casilla instanceof Propiedad ) {

                final Propiedad propiedad = ( Propiedad ) casilla;

                if (propiedad.getGrupo().getTipo() == grupo)
                    numero++;
            }
        }

        return (numero);
    }


    /**
     * Se comprueba si el participante ha obtenido todas los solares de un determinado grupo
     *
     * @param grupo tipo del grupo a comprobar
     * @return      si ha obtenido todas las casillas del grupo dado
     */
    public boolean haObtenidoSolaresGrupo(Grupo grupo) {

        return (numeroCasillasObtenidas(grupo.getTipo()) == grupo.getSolares().size());
    }
}
