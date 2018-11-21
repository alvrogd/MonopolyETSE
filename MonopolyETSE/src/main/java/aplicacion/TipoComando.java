package aplicacion;iniciarJuego

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
    edificarCasa("edificar casa"),
    edificarHotel("edificar hotel"),
    edificarPiscina("edificar piscina"),
    edificarPD("edificar pista"),
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
