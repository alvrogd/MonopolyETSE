package aplicacion.salidaPantalla;

import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.jugadores.excepciones.*;

public interface IComando {

    void crearJugador(String nombre, String avatar) throws JuegoIniciadoException, NumMaximoJugadoresException,
            JugadorRepetidoException, ArgComandoIncorrectoException;
    void iniciarJuego() throws JuegoIniciadoException, NoSuficientesJugadoresException;
    Jugador turno() throws JuegoNoIniciadoException;
    void listarJugadores() throws JuegoNoIniciadoException;
    void listarAvatares() throws JuegoNoIniciadoException;
    void listarEdificios() throws JuegoNoIniciadoException;
    void listarEdificiosGrupo(String grupo);
    void lanzarDados() throws EstarPenalizadoException, ImposibleMoverseException,
            EstarBancarrotaException, NoSerPropietarioException, NoEstarEncarceladoException,
            ImposibleCambiarModoException, JuegoNoIniciadoException, SeHanLanzadoDadosException, EdificiosSolarException;
    void finalizarTurno() throws JuegoNoIniciadoException, NoSeHanLanzadoDadosException;
    void salirCarcel() throws NoEstarEncarceladoException, SeHanLanzadoDadosException, NoLiquidezException,
            EstarBancarrotaException, NoSerPropietarioException, JuegoNoIniciadoException;
    void describirCasilla(String nombreCasilla) throws JuegoNoIniciadoException, NoExisteCasillaException;
    void describirJugador(String nombreJugador) throws JuegoNoIniciadoException, NoExisteJugadorException;
    void describirAvatar(String idAvatar) throws JuegoNoIniciadoException, NoExisteAvatarException;
    void comprar(String nombrePropiedad) throws JuegoNoIniciadoException, NoSerPropiedadException, NoExisteCasillaException,
            NoEncontrarseEnPropiedadException, NoComprarABancaException,
            NoLiquidezException, NoSerPropietarioException, SeHaCompradoCasillaTurnoException;
    void listarEnVenta() throws JuegoNoIniciadoException;
    void verTablero() throws JuegoNoIniciadoException;
    void edificar(String tipoEdificio) throws JuegoNoIniciadoException, NoSerPropietarioException, HipotecaPropiedadException,
            ArgComandoIncorrectoException, NoSerSolarException, EdificiosSolarException, NoLiquidezException;
    void hipotecar(String nombrePropiedad) throws NoSerPropietarioException, HipotecaPropiedadException,
            EdificiosSolarException, JuegoNoIniciadoException, NoExisteCasillaException, NoSerPropiedadException ;
    void deshipotecar(String nombrePropiedad) throws NoSerPropietarioException, HipotecaPropiedadException,
            NoLiquidezException, JuegoNoIniciadoException, NoExisteCasillaException, NoSerPropiedadException;
    void vender(String tipoEdificio, String numero, String nombrePropiedad) throws NoSerPropietarioException,
            HipotecaPropiedadException, EdificiosSolarException, InputUsuarioException, JuegoNoIniciadoException,
            ArgComandoIncorrectoException, NoExisteCasillaException, NoSerSolarException, NumeroIncorrectoException;
    void avanzar() throws ImposibleMoverseException, EstarBancarrotaException, NoSerPropietarioException,
            ImposibleCambiarModoException, JuegoNoIniciadoException, EdificiosSolarException;
    void estadisticasJugadores(String nombreJugador) throws JuegoNoIniciadoException, NoExisteJugadorException;
    void estadisticasGlobales() throws JuegoNoIniciadoException;
    void ayuda();
    void ejecutarTrato() throws NoLiquidezException, NoSerPropietarioException, JuegoNoIniciadoException,
            ComandoIncorrectoException, NumeroIncorrectoException, SubcomandoIncorrectoException, NoSerPropiedadException, NoExisteJugadorException;
    void aceptarTrato(String idTrato) throws NoLiquidezException, NoSerPropietarioException, JuegoNoIniciadoException, NoExisteTratoException;
    void eliminarTrato(String idTrato) throws JuegoNoIniciadoException, NoExisteTratoException;
    void listarTrato() throws JuegoNoIniciadoException;

}
