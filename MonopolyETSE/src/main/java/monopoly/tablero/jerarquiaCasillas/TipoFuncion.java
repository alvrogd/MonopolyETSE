package monopoly.tablero.jerarquiaCasillas;

import aplicacionGUI.ConstantesGUI;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;
import resources.menuGUI.botones.avanzadoToNormal.AnimacionCambiarModo;

public enum TipoFuncion {
    aceptacionTratos("aceptar varios tratos", aceptarTratos),
    aceptar("aceptar", true),
    aceptarTratos("aceptar tratos", true),
    atras("atras", true),
    avanzar("avanzar", true),
    ayuda("ayuda", true),
    cambiarModo("cambiar modo", new AnimacionCambiarModo(), ConstantesGUI.FRAMES_CAMBIARMODO, 0.1),
    cancelar("cancelar", true),
    comprar("comprar", true),
    describir("describir", true, false),
    describirAvatar("describir avatar", false),
    describirJugador("describir jugador", false),
    deshipotecar("deshipotecar", true),
    edificar("edificar", true),
    edificarCasa("casa", edificar),
    edificarHotel("hotel", edificar),
    edificarPiscina("piscina", edificar),
    edificarPista("pista", edificar),
    eliminacionTratos("eliminacion varios tratos", eliminarTratos),
    eliminarTratos("eliminar tratos", true),
    estadisticasGlobales("estadísticas globales", true),
    estadisticasUsuario("estadisticas", true),
    finalizarTurno("finalizar turno", true),
    hipotecar("hipotecar", true),
    lanzarDados("lanzar dados", true),
    listar("listar", true),
    listarAvatares("listar avatares", listar),
    listarEdificios("listar edificios", listar),
    listarJugadores("listar jugadores", listar),
    listarTratos("listar tratos", listar),
    proponerTrato("proponer trato"),
    vender("vender", true),
    venderCasa("casa", vender),
    venderHotel("hotel", vender),
    venderPiscina("piscina", vender),
    venderPista("pista", vender);


    private final String nombre;

    //Booleano que está a true si la función no está en un submenu
    private final boolean menuPrincipal;

    //Booleano para saber si la función tiene un botón asignado
    private final boolean botonAsignado;

    // Para las funciones que tienen botones animados asignados
    private final Object localizacion;
    private final String[] frames;
    private final double duracion;

    //Pueden tener una función raiz
    private TipoFuncion funcionRaiz;

    TipoFuncion(String nombre, Object localizacion, String[] frames, double duracion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.frames = frames;
        this.duracion = duracion;
        this.menuPrincipal = true;
        this.botonAsignado = true;
    }

    TipoFuncion(String nombre) {
        this(nombre, false);
    }

    TipoFuncion(String nombre, TipoFuncion funcionRaiz) {
        this(nombre, false);
        this.funcionRaiz = funcionRaiz;
    }

    TipoFuncion(String nombre, boolean menuPrincipal) {
        this(nombre, menuPrincipal, true);
    }

    TipoFuncion(String nombre, boolean menuPrincipal, boolean botonAsignado) {
        this.nombre = nombre;
        this.menuPrincipal = menuPrincipal;
        this.botonAsignado = botonAsignado;
        this.funcionRaiz = null;
        this.localizacion = null;
        this.frames = null;
        this.duracion = 0;
    }

    public Object getLocalizacion() {
        return localizacion;
    }

    public String[] getFrames() {
        return frames;
    }

    public double getDuracion() {
        return duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isMenuPrincipal() {
        return menuPrincipal;
    }

    public TipoFuncion getFuncionRaiz() {
        return funcionRaiz;
    }

    public boolean isBotonAsignado() {
        return botonAsignado;
    }

    public static TipoFuncion toFuncion(TipoEdificio tipoEdificio) {

        TipoFuncion tipoFuncion;

        switch (tipoEdificio) {

            case casa:
                tipoFuncion = TipoFuncion.venderCasa;
                break;

            case hotel:
                tipoFuncion = TipoFuncion.venderHotel;
                break;

            case piscina:
                tipoFuncion = TipoFuncion.venderPiscina;
                break;

            case pistaDeporte:
                tipoFuncion = TipoFuncion.venderPista;
                break;

            default:
                tipoFuncion = null;
                break;

        }

        return tipoFuncion;

    }

    public static String toString(TipoFuncion tipoFuncion) {

        switch (tipoFuncion) {

            case cambiarModo:
                return ("Botón para activar o desactivar el modo avanzando del avatar.");
            case avanzar:
                return ("Botón para avanzar con el número de casillas que quedan por avanzar.");
            case finalizarTurno:
                return ("Botón para finalizar el turno del jugador actual.");
            case lanzarDados:
                return ("Botón para lanzar los dados.");
            case describir:
                return "";
            case hipotecar:
                return ("Botón para hipotecar la propiedad en la que está el avatar.");
            case comprar:
                return ("Botón para comprar la propiedad en la que está el avatar.");
            case deshipotecar:
                return ("Botón para deshipotecar la propiedad en la que está el avatar.");
            case vender:
                return ("Botón para vender edificios de la propiedad en la que está el avatar. Se abre un menú nuevo para decidir el edificio a vender");
            case venderCasa:
                return ("Botón para vender una casa de la propiedad en la que está el avatar.");
            case venderHotel:
                return ("Botón para vender un hotel de la propiedad en la que está el avatar.");
            case venderPiscina:
                return ("Botón para vender una piscina de la propiedad en la que está el avatar.");
            case venderPista:
                return ("Botón para vender una pista de la propiedad en la que está el avatar.");
            case edificar:
                return ("Botón para edificar un edificio en la propiedad en la que está el avatar. Se abre un menú nuevo para decidir el edificio a construir");
            case edificarCasa:
                return ("Botón para edificar una casa de la propiedad en la que está el avatar.");
            case edificarHotel:
                return ("Botón para edificar un hotel de la propiedad en la que está el avatar.");
            case edificarPiscina:
                return ("Botón para edificar una piscina de la propiedad en la que está el avatar.");
            case edificarPista:
                return ("Botón para edificar una pista de la propiedad en la que está el avatar.");
            case ayuda:
                return ("Tío que haces, esto podría haber generado un SEGFAULT, pero nuestro informáticos se lo curran bien ;-)");
            case atras:
                return ("");
            case estadisticasGlobales:
                return ("Botón para ver las estadísticas globales del juego.");
            case estadisticasUsuario:
                return ("Botón para ver las estadísticas del jugador actual.");
            case listar:
                return ("Botón que abre un nuevo menú de botones para listar diversas propiedades del juego.");
            case listarAvatares:
                return "";
            case listarEdificios:
                return ("Botón para listar todos los edificios del tablero.");
            case listarJugadores:
                return "";
            case listarTratos:
                return ("Botón para listar todos los tratos, recibidos y emitidos del jugador.");
            case proponerTrato:
                return ("Botón para proponer un trato al jugador seleccionado.");
                break;
            case aceptarTratos:
                return ("Botón para acceder a los tratos que puede aceptar el jugador.");
                break;
            case aceptacionTratos:
                return ("Botón para aceptar el trato indicado.");
                break;
            case eliminarTratos:
                return ("Botón para acceder a los tratos emitidos que se pueden eliminar");
                break;
            case eliminacionTratos:
                return ("Botón para eliminar el trato indicado emitido.");
                break;
            case describirJugador:
                return ("Botón para describir el jugador seleccionado.");
                break;
            case describirAvatar:
                return ("Botón para describir el avatar seleccionado.");
                break;
            case cancelar:
                return ("Botón para cancelar la acción actual.");
                break;
            case aceptar:
                return ("Botón para aceptar la acción actual.");
                break;
        }

        return ("");

    }
}
