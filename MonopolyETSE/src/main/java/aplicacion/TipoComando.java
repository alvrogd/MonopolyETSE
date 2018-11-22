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
    lanzarDados("lanzar dados"),
    finalizarTurno("acabar turno"),
    salirCarcel("salir carcel"),
    describirCasilla("describir"),
    describirJugador("describir jugador"),
    describirAvatar("describir avatar"),
    comprarPropiedad("comprar"),
    listarVentas("listar enventa"),
    verTablero("ver tablero"),
    edificar("edificar"),
    ayuda("ayuda");

    /*Atributos*/
    private final String comando;

    /*Constructores*/
    private TipoComando(String comando){
        this.comando = comando;
    }

    /*Métodos*/
    public String getComando(){
        return(comando);
    }

}
