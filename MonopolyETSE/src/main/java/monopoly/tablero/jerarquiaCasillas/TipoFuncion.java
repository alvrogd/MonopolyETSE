package monopoly.tablero.jerarquiaCasillas;

import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;

public enum TipoFuncion {

    cambiarModo("cambiar modo", true),
    avanzar("avanzar", true),
    finalizarTurno("finalizar turno", true),
    lanzarDados("lanzar dados", true),
    describir("describir", true, false),
    hipotecar("hipotecar", true),
    comprar("comprar", true),
    deshipotecar("deshipotecar", true),
    vender("vender", true),
    venderCasa("casa", vender),
    venderHotel("hotel", vender),
    venderPiscina("piscina", vender),
    venderPista("pista", vender),
    edificar("edificar", true),
    edificarCasa("casa", edificar),
    edificarHotel("hotel", edificar),
    edificarPiscina("piscina", edificar),
    edificarPista("pista", edificar),
    ayuda("ayuda", true),
    atras("atras", ayuda),
    estadisticasGlobales("estadísticas globales", true),
    estadisticasUsuario("estadisticas", true),
    listar("listar", true),
    listarAvatares("listar avatares", listar),
    listarEdificios("listar edificios", listar),
    listarJugadores("listar jugadores", listar),
    listarTratos("listar tratos", listar);


    private final String nombre;

    //Booleano que está a true si la función no está en un submenu
    private final boolean menuPrincipal;

    //Booleano para saber si la función tiene un botón asignado
    private final boolean botonAsignado;

    //Pueden tener una función raiz
    private TipoFuncion funcionRaiz;

    TipoFuncion(String nombre, TipoFuncion funcionRaiz) {
        this(nombre, false);
        this.funcionRaiz = funcionRaiz;
    }
    TipoFuncion(String nombre, boolean menuPrincipal){
        this(nombre, menuPrincipal, true);
    }

    TipoFuncion(String nombre, boolean menuPrincipal, boolean botonAsignado){
        this.nombre = nombre;
        this.menuPrincipal = menuPrincipal;
        this.botonAsignado = botonAsignado;
        this.funcionRaiz = null;
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

    public static TipoFuncion toFuncion(TipoEdificio tipoEdificio){

        TipoFuncion tipoFuncion;

        switch(tipoEdificio){

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
}
