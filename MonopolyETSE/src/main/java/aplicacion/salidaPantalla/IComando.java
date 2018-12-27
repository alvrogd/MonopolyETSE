package aplicacion.salidaPantalla;

import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.jugadores.excepciones.*;

public interface IComando {

    void crearJugador(String nombre, String avatar);
    void iniciarJuego();
    Jugador turno();
    void listarJugadores();
    void listarAvatares();
    void listarEdificios();
    void listarEdificiosGrupo(String grupo);
    void lanzarDados() throws EstarPenalizadoException, ImposibleMoverseException, EstarBancarrotaException, NoSerPropietarioException, NoEstarEncarceladoException,
            ImposibleCambiarModoException;
    void finalizarTurno();
    void salirCarcel() throws NoEstarEncarceladoException, SeHanLanzadoDadosException, NoLiquidezException,
            EstarBancarrotaException, NoSerPropietarioException;
    void describirCasilla(String nombreCasilla);
    void describirJugador(String nombreJugador);
    void describirAvatar(String idAvatar);
    void comprar(String nombrePropiedad) throws NoEncontrarseEnPropiedadException, NoComprarABancaException, NoLiquidezException, NoSerPropietarioException;
    void listarEnVenta();
    void verTablero();
    void edificar(String tipoEdificio) throws NoSerPropietarioException, HipotecaPropiedadException;
    void cambiarModo() throws ImposibleCambiarModoException;
    void hipotecar(String nombrePropiedad) throws NoSerPropietarioException, HipotecaPropiedadException, EdificiosSolarException;
    void deshipotecar(String nombrePropiedad) throws NoSerPropietarioException, HipotecaPropiedadException, NoLiquidezException;
    void vender(String tipoEdificio, String numero, String nombrePropiedad) throws NoSerPropietarioException,
            HipotecaPropiedadException, EdificiosSolarException, InputUsuarioException;
    void avanzar() throws ImposibleMoverseException, EstarBancarrotaException, NoSerPropietarioException, ImposibleCambiarModoException;
    void estadisticasJugadores(String nombreJugador);
    void estadisticasGlobales();
    void ayuda();
    void ejecutarTrato(String linea);

}
