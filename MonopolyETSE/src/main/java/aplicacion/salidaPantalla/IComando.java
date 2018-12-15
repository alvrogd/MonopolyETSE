package aplicacion.salidaPantalla;

import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;

public interface IComando {

    void crearJugador(String nombre, String avatar);
    void iniciarJuego();
    Jugador turno();
    void listarJugadores();
    void listarAvatares();
    void listarEdificios();
    void listarEdificiosGrupo(String grupo);
    void lanzarDados();
    void finalizarTurno();
    void salirCarcel();
    void describirCasilla(String nombreCasilla);
    void describirJugador(String nombreJugador);
    void describirAvatar(String idAvatar);
    void comprar(String nombreSolar);
    void listarEnVenta();
    void verTablero();
    void edificar(String tipoEdificio);
    void cambiarModo();
    void hipotecar(String nombrePropiedad);
    void deshipotecar(String nombrePropiedad);
    void vender(String tipoEdificio, String numero, String nombrePropiedad);
    void avanzar();
    void estadisticasJugadores(String nombreJugador);
    void estadisticasGlobales();
    void ayuda();

}
