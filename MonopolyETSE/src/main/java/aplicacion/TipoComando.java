package aplicacion;

public enum TipoComando {

    crear("crear"),
    crearJugador("crear jugador"),
    iniciarJuego("iniciar"),
    turno("jugador"),
    listar("listar"),
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
    verTablero("ver tablero"),
    ayuda("ayuda");


    private final String comando;

    private TipoComando(String comando){
        this.comando = comando;
    }

    public String getComando(){
        return(comando);
    }

}
