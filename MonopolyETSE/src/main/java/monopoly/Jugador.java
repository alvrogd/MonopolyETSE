package monopoly;

import java.util.ArrayList;

public class Jugador {

    /* Atributos */

    // Nombre del jugador
    private final String nombre;
    // Avatar que lo representa en el tablero
    private final Avatar avatar;

    // Cantidad de dinero disponible
    private double fortuna;
    // Si se encuentra en bancarrota o no
    private boolean estaBancarrota;

    // Propiedades en posesión
    private ArrayList<Casilla> propiedades;



    /* Constructores */

    public Jugador(String nombre, TipoAvatar tipoAvatar, Casilla casillaInicial) {

        if (tipoAvatar == null) {
            System.err.println("Error: tipo de avatar no inicializado");
            System.exit(1);
        }

        if (casillaInicial == null) {
            System.out.println("Error: casilla inicial no inicializada.");
            System.exit(1);
        }

        this.nombre = nombre;

        this.avatar = new Avatar(tipoAvatar, casillaInicial);

        this.fortuna = Constantes.DINERO_INICIAL;

        this.propiedades = new ArrayList<>();

    }



    /*Getters y setters*/

    public String getNombre() {
        return nombre;
    }


    public Avatar getAvatar() {
        return avatar;
    }


    public double getFortuna() {
        return fortuna;
    }


    public void setFortuna(double fortuna) {

        if (fortuna < 0) {
            System.err.println("Error: la fortuna de un jugador no puede ser menor a 0");
            return;
        }

        this.fortuna = fortuna;

    }


    public boolean isEstaBancarrota() {
        return estaBancarrota;
    }


    public void setEstaBancarrota(boolean estaBancarrota) {
        this.estaBancarrota = estaBancarrota;
    }


    public ArrayList<Casilla> getPropiedades() {
        return propiedades;
    }


    public void setPropiedades(ArrayList<Casilla> propiedades) {

        if (propiedades == null) {
            System.err.println("Error: propiedades no inicializadas");
            return;
        }

        for( Casilla casilla : propiedades ) {

            if( casilla == null ) {
                System.err.println( "Error: casilla no inicializada" );
                return;
            }

        }

        this.propiedades = propiedades;
    }



    /* Métodos */

    public void pagar( Jugador receptor, double importe ) {

        if( receptor == null ){
            System.err.println( "Error: jugador no inicializado" );
            return;
        }

        if( importe < 0 ) {
            System.err.println( "Error: no se puede pagar a un jugador una cantidad menor a 0");
            return;
        }

        // Fortuna del jugador tras realizar el pago
        double balance = getFortuna() - importe;

        // Si el jugador cayese en bancarrota, se transfieren al receptor las propiedades del jugador
        if( balance < 0 ) {

            ArrayList<Casilla> propiedadesEndeudado = getPropiedades();
            ArrayList<Casilla> propiedadesReceptor = receptor.getPropiedades();

            for( Casilla casilla : propiedadesEndeudado ) {
                casilla.setPropietario(receptor);
                propiedadesReceptor.add(casilla);
                propiedadesEndeudado.remove(casilla);
            }

        }

        // En caso contrario, dispone de la suficiente liquidez como para pagar
        else {

            setFortuna(balance);
            receptor.setFortuna(receptor.getFortuna() + importe);

    }

}

