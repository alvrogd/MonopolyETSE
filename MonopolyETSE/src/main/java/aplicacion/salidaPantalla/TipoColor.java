package aplicacion.salidaPantalla;

public enum TipoColor {

    // Colores
    resetAnsi( "\u001B[00m", "\u001B[00m" ),
    negroANSI( "\u001B[40m","\u001B[30m" ),
    rojoANSI( "\u001B[41m", "\u001B[31m" ),
    verdeANSI( "\u001B[42m", "\u001B[32m" ),
    amarilloANSI( "\u001B[43m", "\u001B[33m" ),
    azulANSI( "\u001B[44m", "\u001B[34m"),
    violetaANSI( "\u001B[45m", "\u001B[35m" ),
    cianANSI( "\u001B[46m", "\u001B[36m" ),
    blancoANSI( "\u001B[47m", "\u001B[37m" ),
    Negrita("\u001B[01m", "\u001B[01m");

    private final String fondo;
    private final String letra;

    private TipoColor(String fondo, String letra) {

        this.fondo = fondo;
        this.letra = letra;

    }

    public String getFondo() {
        return fondo;
    }

    public String getLetra(){
        return letra;
    }
}
