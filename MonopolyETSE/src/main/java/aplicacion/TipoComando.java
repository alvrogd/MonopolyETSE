package aplicacion;

public enum TipoComando {

    /*Tipos de enumeraciones*/
    crear("crear"),
    crearJugador("crear jugador"),
    iniciarJuego("iniciar"),
    turno("jugador"),
    listar("listar"),
    listarJugadores("listar jugadores"),
    listarAvatares("listar avatares"),
    listarEdificios("listar edificios"),
    lanzarDados("lanzar dados"),
    finalizarTurno("acabar turno"),
    salirCarcel("salir carcel"),
    describirCasilla(true, "describir", true), //puede no contener una casilla por ejemplo describir jugador
    describirJugador("describir jugador"),
    describirAvatar("describir avatar"),
    comprarPropiedad(true, "comprar"),
    listarVentas("listar enventa"),
    verTablero("ver tablero"),
    edificar("edificar"),
    cambiarModo("cambiar modo"),
    hipotecar(true, "hipotecar"),
    deshipotecar(true, "deshipotecar"),
    vender(true, 3, "vender"),
    avanzar("avanzar"),
    estadisticasJugador("estadisticas"),
    estadisticasGlobales("estadisticas"),
    ayuda("ayuda");

    /*Atributos*/
    private final String comando;

    //Atributos para saber si el comando va a tener el nombre de una casilla y de ser así,
    //indicar cuantos espacios hay de la primera palabra del comando a la que aparece la casilla.
    private final boolean contieneCasilla;
    private final boolean puedeContenerCasilla;
    private final int espaciosCasilla;

    /*Constructores*/
    private TipoComando(String comando) {
        this.comando = comando;
        this.contieneCasilla = false;
        this.puedeContenerCasilla = false;
        this.espaciosCasilla = 1;
    }

    private TipoComando(boolean contieneCasilla, String comando) {
        this.comando = comando;
        this.contieneCasilla = contieneCasilla;
        this.puedeContenerCasilla = false;
        this.espaciosCasilla = 1;
    }

    private TipoComando(boolean contieneCasilla, int espaciosCasilla, String comando) {
        this.comando = comando;
        this.contieneCasilla = contieneCasilla;
        this.puedeContenerCasilla = false;
        this.espaciosCasilla = espaciosCasilla;
    }

    private TipoComando(boolean contieneCasilla, String comando, boolean puedeContenerCasilla) {
        this.comando = comando;
        this.contieneCasilla = contieneCasilla;
        this.puedeContenerCasilla = puedeContenerCasilla;
        this.espaciosCasilla = 1;
    }

    /*Métodos*/
    public String getComando() {
        return (comando);
    }

    public boolean isContieneCasilla() {
        return contieneCasilla;
    }

    public int getEspaciosCasilla() {
        return espaciosCasilla;
    }

    public boolean isPuedeContenerCasilla() {
        return puedeContenerCasilla;
    }

    public static TipoComando toComandoCasilla(String palabra) {

        boolean encontrado = false;
        TipoComando comando = null;

        for (TipoComando tComando : TipoComando.values()) {

            if (tComando.getComando().equals(palabra)) {
                if (tComando.isContieneCasilla()) {
                    encontrado = true;
                    comando = tComando;
                    break;
                }

            }
        }

        return comando;

    }
}
