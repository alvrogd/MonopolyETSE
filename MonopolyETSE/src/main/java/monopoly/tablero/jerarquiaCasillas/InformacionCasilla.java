package monopoly.tablero.jerarquiaCasillas;

import aplicacionGUI.informacion.Informacion;

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

    public InformacionCasilla(TipoCasilla tipoCasilla, String nombre) {
        this(tipoCasilla, nombre, null, 0);
    }

    public InformacionCasilla(TipoCasilla tipoCasilla, String nombre, Grupo grupo, int importe) {

        if( tipoCasilla == null ) {
            System.out.println("Tipo de casilla no inicializado");
            System.exit(1);
        }

        if( nombre == null ) {
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
