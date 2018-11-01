package aplicacion.salidaPantalla;

public enum TipoColor {

    // Colores
    resetAnsi( "\u001B[00m" ),
    negroANSI( "\u001B[40m" ),
    rojoANSI( "\u001B[41m" ),
    verdeANSI( "\u001B[42m" ),
    amarilloANSI( "\u001B[43m" ),
    azulANSI( "\u001B[44m"),
    violetaANSI( "\u001B[45m" ),
    cianANSI( "\u001B[46m" ),
    blancoANSI( "\u001B[47m" );

    private final String fondo;

    private TipoColor(String fondo) {

        this.fondo = fondo;

    }

    public String getFondo() {
        return fondo;
    }
}
