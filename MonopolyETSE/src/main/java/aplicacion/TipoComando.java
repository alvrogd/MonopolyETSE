package aplicacion;

public enum TipoComando {

    crearJugador("crear jugador"),
    turno("jugador"),
    listarJugadores("listar jugadores"),
    listarAvatares("listar avatares"),
    lanzarDados("lanzar dados"),
    finalizarTurno("acabar turno"),
    salirCarcel("salir carcel"),
    describirCasilla("describir"),
    describirJugador("describir jugador"),
    describirAvatar("describir avatar"),
    comprarPropiedad("comprar"),
    listarVentas("listar enventa"),
    verTablero("ver tablero");


    private final String comando;

    public TipoComando(String comando){
        this.comando = comando;
    }

    public String getComando(){
        return(comando);
    }

}
