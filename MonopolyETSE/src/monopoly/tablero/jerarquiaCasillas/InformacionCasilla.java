package monopoly.tablero.jerarquiaCasillas;

import monopoly.tablero.jerarquiaCasillas.Grupo;
import monopoly.tablero.jerarquiaCasillas.TipoCasilla;

public class InformacionCasilla {

    /* Atributos */

    // Tipo de casilla
    private final TipoCasilla tipoCasilla;

    // Nombre
    private final String nombre;

    // Grupo
    private final Grupo grupo;

    // Importe asociado (precio inicial o impuesto)
    private final int importe;



    /* Constructores */

    /**
     * Se crea un contenedor de información personalizada sobre una casilla
     *
     * @param tipoCasilla tipo de casilla al que corresponde la información
     * @param nombre      nombre de la casilla
     */
    public InformacionCasilla(TipoCasilla tipoCasilla, String nombre) {
        this(tipoCasilla, nombre, null, 0);
    }

    /**
     * Se crea un contenedor de información personalizada sobre una casilla
     *
     * @param tipoCasilla tipo de casilla al que corresponde la información
     * @param nombre      nombre de la casilla
     * @param grupo       grupo al que pertenece la casilla
     * @param importe     importe asociado a la casilla (precio de compra, impuesto...)
     */
    public InformacionCasilla(TipoCasilla tipoCasilla, String nombre, Grupo grupo, int importe) {

        if (tipoCasilla == null) {
            System.out.println("Tipo de casilla no inicializado");
            System.exit(1);
        }

        if (nombre == null) {
            System.out.println("Nombre no inicializado");
            System.exit(1);
        }

        this.tipoCasilla = tipoCasilla;
        this.nombre = nombre;
        this.grupo = grupo;
        this.importe = importe;
    }



    /* Getters y setters */

    public TipoCasilla getTipoCasilla() {
        return tipoCasilla;
    }

    public String getNombre() {
        return nombre;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public int getImporte() {
        return importe;
    }
}
