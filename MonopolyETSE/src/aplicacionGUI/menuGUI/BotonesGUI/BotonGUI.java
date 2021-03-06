package aplicacionGUI.menuGUI.BotonesGUI;

import aplicacion.Aplicacion;
import aplicacion.salidaPantalla.Output;
import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Translate;
import monopoly.jugadores.Coche;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.excepciones.*;
import monopoly.jugadores.tratos.Trato;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.Solar;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.ComunidadCasilla;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.SuerteCasilla;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.Edificio;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;
import resources.menuGUI.botones.BotonesImagenes;
import resources.sonidos.Sonidos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class BotonGUI {

    // Nombre del botón
    private final String nombre;

    // Botonera a la que pertenece
    private final BotoneraGUI botonera;

    // Funcion que tiene el botón
    private final TipoFuncion funcion;

    // Imágenes del botón
    private final Image boton;
    private final Image botonOscuro;
    private Image botonActual;
    private Image tachado;

    // Desplazamientos con respecto a la botonera
    private int desplazamientoX;
    private int desplazamientoY;

    private int fila;
    private int columna;

    // Nodo del botón
    private final Group nodo;
    private final Canvas canvas;
    private final GraphicsContext gc;

    // Transformacion
    private Translate translate;

    // Sensor asociado a este botón
    private Rectangle sensor;

    // Aplicacion
    private Aplicacion aplicacion;

    // Booleano para saber si el botón es animado
    private boolean animado;

    // Booleano para saber si es el botón de ayuda
    private boolean ayuda;

    // Sonido a reproducir cuando se pulsa un botón
    private static final Media sonido = new Media(Sonidos.class.getResource(ConstantesGUI.SONIDO_BOTON).toString());

    // Sonido a reproducir cuando se lanzan los dados
    private static final Media sonidoDados = new Media(Sonidos.class.getResource(ConstantesGUI.SONIDO_DADOS).toString());


    public BotonGUI(BotoneraGUI botonera, Group raiz, Aplicacion app, String nombre, TipoFuncion funcion, int fila, int columna, boolean animado, boolean ayuda){

        if(raiz == null){
            System.err.println("Raiz no inicializada");
            System.exit(1);
        }

        if(nombre == null){
            System.err.println("Nombre no inicializado");
            System.exit(1);
        }

        if(funcion == null){
            System.err.println("Función no inicializada");
            System.exit(1);
        }

        if(fila < 0){
            System.err.println("Fila no puede ser negativa");
            System.exit(1);
        }

        if(columna < 0){
            System.err.println("Columna no puede ser negativa");
            System.exit(1);
        }

        if(app == null){
            System.err.println("Aplicación no inicializada");
            System.exit(1);
        }

        this.botonera = botonera;
        this.aplicacion = app;
        this.nombre = nombre;
        this.funcion = funcion;

        // Se añade el nodo
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana

        this.fila = fila;
        this.columna = columna;
        this.desplazamientoX = desplazamientoX(fila);
        this.desplazamientoY = desplazamientoY(columna);
        this.translate = new Translate(desplazamientoX, desplazamientoY);

        this.nodo.getTransforms().add(this.translate);

        this.canvas = new Canvas(ConstantesGUI.BOTONES_ANCHO, ConstantesGUI.BOTON_ALTO);
        this.nodo.getChildren().add(canvas);

        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el correspondiente sensor
        this.sensor = new Rectangle(0, 0, ConstantesGUI.BOTON_ANCHO, ConstantesGUI.BOTON_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        this.boton = new Image(BotonesImagenes.class.getResource(nombre + ".png").toString());
        this.botonOscuro = new Image(BotonesImagenes.class.getResource(nombre + "Oscuro.png").toString());
        this.botonActual = this.boton;

        this.animado = animado;
        this.ayuda = ayuda;
    }

    public BotonGUI(BotoneraGUI botonera, Group raiz, String nombre, Aplicacion app, TipoFuncion funcion, int fila, int columna){
        this(botonera, raiz, app, nombre, funcion, fila, columna, false, false);
    }

    public BotonGUI(String nombreImagen, BotoneraGUI botonera, Group raiz, String nombre, Aplicacion app, TipoFuncion funcion, int fila, int columna){
        if(raiz == null){
            System.err.println("Raiz no inicializada");
            System.exit(1);
        }

        if(nombre == null){
            System.err.println("Nombre no inicializado");
            System.exit(1);
        }

        if(funcion == null){
            System.err.println("Función no inicializada");
            System.exit(1);
        }

        if(fila < 0){
            System.err.println("Fila no puede ser negativa");
            System.exit(1);
        }

        if(columna < 0){
            System.err.println("Columna no puede ser negativa");
            System.exit(1);
        }

        if(app == null){
            System.err.println("Aplicación no inicializada");
            System.exit(1);
        }

        this.botonera = botonera;
        this.aplicacion = app;
        this.nombre = nombre;
        this.funcion = funcion;

        // Se añade el nodo
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana

        this.fila = fila;
        this.columna = columna;
        this.desplazamientoX = desplazamientoX(fila);
        this.desplazamientoY = desplazamientoY(columna);
        this.translate = new Translate(desplazamientoX, desplazamientoY);

        this.nodo.getTransforms().add(this.translate);

        this.canvas = new Canvas(ConstantesGUI.BOTONES_ANCHO, ConstantesGUI.BOTON_ALTO);
        this.nodo.getChildren().add(canvas);

        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el correspondiente sensor
        this.sensor = new Rectangle(0, 0, ConstantesGUI.BOTON_ANCHO, ConstantesGUI.BOTON_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        this.boton = new Image(BotonesImagenes.class.getResource(nombreImagen + ".png").toString());
        this.botonOscuro = new Image(BotonesImagenes.class.getResource(nombreImagen + "Oscuro.png").toString());
        this.botonActual = this.boton;

        this.animado = false;
        this.ayuda = false;
    }

    public BotoneraGUI getBotonera() {
        return botonera;
    }

    public Aplicacion getApp() {
        return aplicacion;
    }

    public Translate getTranslate() {
        return translate;
    }

    public int desplazamientoX(int fila){
        return((ConstantesGUI.BOTON_ANCHO + ConstantesGUI.BOTON_SEPARACION_X) * fila + ConstantesGUI.BOTON_SEPARACION_X + 15);
    }

    public int desplazamientoY(int columna){
        return((ConstantesGUI.BOTON_ALTO + ConstantesGUI.BOTON_SEPARACION_Y) * columna  + ConstantesGUI.BOTON_SEPARACION_Y + 15);
    }

    public void setDesplazamientoX(int desplazamientoX) {
        this.desplazamientoX = desplazamientoX;
    }

    public void setDesplazamientoY(int desplazamientoY) {
        this.desplazamientoY = desplazamientoY;
    }

    public TipoFuncion getFuncion() {
        return funcion;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAnimado() {
        return animado;
    }

    public boolean isAyuda() {
        return ayuda;
    }

    public Image getBoton() {
        return boton;
    }

    public Image getBotonOscuro() {
        return botonOscuro;
    }

    public Group getNodo() {
        return nodo;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getBotonActual() {
        return botonActual;
    }

    public void setBotonActual(Image botonActual) {
        this.botonActual = botonActual;
    }

    public int getDesplazamientoX() {
        return desplazamientoX(getFila());
    }

    public int getDesplazamientoY() {
        return desplazamientoY(getColumna());
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public static Media getSonido() {
        return sonido;
    }

    public static Media getSonidoDados() {
        return sonidoDados;
    }

    public void inhabilitarBoton(){

        getSensor().setX(-1000);
        getSensor().setY(-1000);

    }

    public void habilitarBoton(){

        getSensor().setX(0);
        getSensor().setY(0);

    }

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        return(getSensor().contains(posicionX, posicionY));
    }

    public boolean pulsandoBoton(double x, double y){
        double posicionX = x;
        double posicionY = y;

        return(getSensor().contains(posicionX, posicionY));
    }

    public void lanzarDados(){

        // Se ponen bien las cartas de comunidad / suerte si el jugador estaba en ellas
        if(getApp().getJuego().getTurno().getAvatar().getPosicion() instanceof SuerteCasilla){
            getBotonera().getMenuGUI().getTableroGUI().getInformacion().getSuerteGUI().esconderCarta();
        } else if(getApp().getJuego().getTurno().getAvatar().getPosicion() instanceof ComunidadCasilla){
            getBotonera().getMenuGUI().getTableroGUI().getInformacion().getComunidadGUI().esconderCarta();
        }

        if (getApp().getJuego().getTurno().getAvatar() instanceof Coche)
            getApp().getJuego().setHaCompradoPropiedad(false);

        try {
            getApp().getJuego().getTurno().lanzarDados(getApp().getJuego().getTablero().getDado());
            MediaPlayer reproductor = new MediaPlayer(getSonidoDados());
            reproductor.play();
        } catch(Exception ignored){

        }

    }

    public void cambiarModo(){

        try {
            getApp().getJuego().getTurno().getAvatar().switchMovimiento();
        } catch (Exception ignored) {

        }

    }

    public void comprar() {
        try {
            getApp().getJuego().getTurno().comprar(getApp().getJuego().getBanca(), (Propiedad) getApp().getJuego().getTurno().getAvatar().getPosicion());
        } catch (Exception ignored) {

        }
    }

    public void finalizarTurno(){

        getApp().getJuego().finalizarTurno();

        Output.respuesta("El jugador actual es " + getApp().getJuego().getTurno().getNombre());

        if(!getApp().getJuego().getTurno().getTratosRecibidos().isEmpty()){
            Output.mensaje("¡Tienes " + getApp().getJuego().getTurno().getTratosRecibidos().size() + " tratos pendientes!");
            listarTrato();
        }
    }

    public void listarTrato(){

        Set<String> tratos = getApp().getJuego().getTurno().getTratosEmitidos().keySet();

        String salida = "";
        boolean emitido = false;
        boolean recibido = false;

        salida += "\n(!) Tratos emitidos pendientes: \n";

        int contador = 0;

        for (String idTrato : tratos) {
            emitido = true;
            contador++;
            salida += "(Trato " + contador + ")────────────────────────────────────────────────()\n\n";
            salida += getApp().getJuego().getTurno().getTratosEmitidos().get(idTrato).toString() + "\n\n";
        }

        if (!emitido)
            salida += " (-) Sin tratos emitidos.\n\n\n";

        salida += "\n(!) Tratos recibidos: \n\n";

        tratos = getApp().getJuego().getTurno().getTratosRecibidos().keySet();

        contador = 0;

        for (String idTrato : tratos) {
            recibido = true;
            contador++;
            salida += "(Trato " + contador + ")────────────────────────────────────────────────()\n\n";
            salida += getApp().getJuego().getTurno().getTratosRecibidos().get(idTrato).toString();
        }

        if (!recibido)
            salida += " (-) Sin tratos recibidos.\n\n\n";

        Output.respuesta(Output.toArrayString(salida));

    }

    public void listarEdificios(){
        ArrayList<String> respuesta = new ArrayList<>();
        boolean flag = false;

        respuesta.add("Los edificios del tablero son los siguientes.");

        for (ArrayList<Casilla> fila : getApp().getJuego().getTablero().getCasillas()) {

            for (Casilla casilla : fila) {

                if (casilla instanceof Solar) {

                    Solar solar = (Solar) casilla;

                    for (TipoEdificio tipoEdificio : TipoEdificio.values()) {

                        for (Edificio edificio : solar.getEdificiosContenidos().get(tipoEdificio)) {

                            flag = true;
                            respuesta.addAll(Output.toArrayString(edificio.toString()));
                            respuesta.add(" ");

                        }
                    }
                }
            }
        }


        if (!flag) {

            respuesta.add("(!) No hay edificios en el tablero.");

        }

        Output.respuesta(respuesta);
    }

    public void edificarVariable(String tipoEdificio){
        TipoEdificio edificio = TipoEdificio.toEdificio(tipoEdificio);

        Jugador turno = getApp().getJuego().getTurno();

        if (turno.getAvatar().getPosicion() instanceof Solar) {

            if (edificio != null) {
                try {
                    getApp().getJuego().getTurno().crearEdificio(edificio, (Solar) getApp().getJuego().getTurno().getAvatar().getPosicion());
                } catch (Exception ignored) {

                }
            }

        }
    }

    public void hipotecar(){

        try {
            getApp().getJuego().getTurno().hipotecar((Propiedad) getApp().getJuego().getTurno().getAvatar().getPosicion());
        } catch (Exception ignored) {

        }

    }

    public void deshipotecar(){

        try {
            getApp().getJuego().getTurno().deshipotecar((Propiedad) getApp().getJuego().getTurno().getAvatar().getPosicion());
        } catch (Exception ignored) {

        }

    }

    public void venderVariable(String tipoEdificio){

        TipoEdificio edificio = TipoEdificio.toEdificio(tipoEdificio);
        int cantidad = 1;
        try {
            getApp().getJuego().getTurno().venderEdificio(edificio, cantidad, (Solar)getApp().getJuego().getTurno().getAvatar().getPosicion());
        } catch (Exception ignored) {

        }

    }

    public void avanzar(){

        int casillasPorMoverse = getApp().getJuego().getTurno().getAvatar().getCasillasRestantesPorMoverse();
        try {
            getApp().getJuego().getTurno().getAvatar().avanzar(casillasPorMoverse);
        } catch (Exception ignored) {

        }

    }

    public void vender(){

        getBotonera().setPagina(true);
        getBotonera().setFuncionPagina(getFuncion());
    }

    public void listar(){

        getBotonera().setPagina(true);
        getBotonera().setFuncionPagina(getFuncion());
    }

    public void edificar(){

        getBotonera().setPagina(true);
        getBotonera().setFuncionPagina(getFuncion());
    }

    public void aceptarTratos(){

        ArrayList<BotonGUI> botones = new ArrayList<>();
        getBotonera().getBotonesPagina().put(TipoFuncion.aceptarTratos, botones);

        Collection<Trato> tratos = getApp().getJuego().getTurno().getTratosRecibidos().values();

        for(Trato trato : tratos){
            botones.add(new BotonGUI("tratoAceptar", getBotonera(), getBotonera().getNodo(), trato.getId(), getApp(), TipoFuncion.aceptacionTratos, 0, 0));
        }

        getBotonera().setPagina(true);
        getBotonera().setFuncionPagina(getFuncion());

    }

    public void aceptacionTratos(){

        if(getApp().getJuego().getTurno().getTratosRecibidos().containsKey(getNombre())){
            try {
                getApp().getJuego().getTurno().aceptarTrato(getNombre());
            } catch (NoLiquidezException | NoExisteTratoException | NoSerPropietarioException e) {
                Aplicacion.consola.imprimir(e.getMessage());
            }
        }

        ArrayList<BotonGUI> botones = getBotonera().getBotonesPagina().get(TipoFuncion.aceptarTratos);

        for(BotonGUI boton : botones){
            boton.getGc().clearRect(0, 0, ConstantesGUI.BOTON_ANCHO, ConstantesGUI.BOTON_ALTO);
        }

        botones = new ArrayList<>();
        getBotonera().getBotonesPagina().put(TipoFuncion.aceptarTratos, botones);

        Collection<Trato> tratos = getApp().getJuego().getTurno().getTratosRecibidos().values();

        for(Trato trato : tratos){
            if(trato.tratoValido()) {
                botones.add(new BotonGUI("tratoAceptar", getBotonera(), getBotonera().getNodo(), trato.getId(), getApp(), TipoFuncion.aceptacionTratos, 0, 0));
            } else {
                getApp().getJuego().getTurno().getTratosRecibidos().remove(trato.getId());
                trato.getEmisor().getTratosEmitidos().remove(trato.getId());
            }
        }
    }

    public void eliminarTratos(){

        ArrayList<BotonGUI> botones = new ArrayList<>();
        getBotonera().getBotonesPagina().put(TipoFuncion.eliminarTratos, botones);

        Collection<Trato> tratos = getApp().getJuego().getTurno().getTratosEmitidos().values();

        for(Trato trato : tratos){
            botones.add(new BotonGUI("tratoEliminar", getBotonera(), getBotonera().getNodo(), trato.getId(), getApp(), TipoFuncion.eliminacionTratos, 0, 0));
        }

        getBotonera().setPagina(true);
        getBotonera().setFuncionPagina(getFuncion());

    }

    public void eliminacionTratos(){

        if(getApp().getJuego().getTurno().getTratosEmitidos().containsKey(getNombre())){
            try {
                getApp().getJuego().getTurno().eliminarTrato(getNombre());
            } catch (NoExisteTratoException e) {
                Aplicacion.consola.imprimir(e.getMessage());
            }

            ArrayList<BotonGUI> botones = getBotonera().getBotonesPagina().get(TipoFuncion.eliminarTratos);

            for(BotonGUI boton : botones){
                boton.getGc().clearRect(0, 0, ConstantesGUI.BOTON_ANCHO, ConstantesGUI.BOTON_ALTO);
            }

            botones = new ArrayList<>();

            getBotonera().getBotonesPagina().put(TipoFuncion.eliminarTratos, botones);

            Collection<Trato> tratos = getApp().getJuego().getTurno().getTratosEmitidos().values();

            for(Trato trato : tratos){
                if(trato.tratoValido()) {
                    botones.add(new BotonGUI("tratoEliminar", getBotonera(), getBotonera().getNodo(), trato.getId(), getApp(), TipoFuncion.eliminacionTratos, 0, 0));
                } else {
                    getApp().getJuego().getTurno().getTratosEmitidos().remove(trato.getId());
                    trato.getEmisor().getTratosRecibidos().remove(trato.getId());
                }
            }

        }
    }

    public void estadisticasJugador(){
        Jugador auxJugador = getApp().getJuego().getTurno();

        Output.respuesta("(*) Estadísticas de " + auxJugador.getNombre(),
                "      -> Dinero invertido           : " + auxJugador.getDineroInvertido(),
                "      -> Pago tasas e impuestos     : " + auxJugador.getPagoTasasEImpuestos(),
                "      -> Pago de alquileres         : " + auxJugador.getPagoDeAlquileres(),
                "      -> Cobro de alquileres        : " + auxJugador.getCobroDeAlquileres(),
                "      -> Pasar por casilla de salida: " + auxJugador.getPasarPorCasillaDeSalida(),
                "      -> Premios inversiones o botes: " + auxJugador.getPremiosInversionesOBote(),
                "      -> Veces en la carcel         : " + auxJugador.getVecesEnLaCarcel());
    }

    public void estadisticasGlobales(){
        Output.respuesta("(*) Estadísticas globales",
                "      -> Casilla más rentable    : " + getApp().getJuego().casillaMasRentable().getNombre(),
                "      -> Grupo más rentable      : " + getApp().getJuego().grupoMasRentable().getTipo().toString(),
                "      -> Casilla más frecuentada : " + getApp().getJuego().casillaMasFrecuentada().getNombre(),
                "      -> Jugador con más vueltas : " + getApp().getJuego().jugadorMasVueltas().getNombre(),
                "      -> Jugador con más tiradas : " + getApp().getJuego().jugadorMasVecesDados().getNombre(),
                "      -> Jugador en cabeza       : " + getApp().getJuego().jugadorEnCabeza().getNombre());
    }

    public void atras(){

        ArrayList<BotonGUI> botonesPagina = getBotonera().getBotonesPagina().get(getBotonera().getFuncionPagina());
        for(BotonGUI boton : botonesPagina){
            boton.getGc().clearRect(0, 0, ConstantesGUI.BOTON_ANCHO, ConstantesGUI.BOTON_ALTO);
            boton.inhabilitarBoton();
        }
        getBotonera().setPagina(false);
        getBotonera().setFuncionPagina(null);

    }

    // Aceptar implica pasar a la siguiente posicion, para eso hay un booleano que indica siguientePosicion, el cual se pone
    // a true y ya se encargará el render
    public void aceptar(){
        getBotonera().getMenuGUI().setSiguientePaso(true);
    }

    // Todos los modos que utilizan cancelar irán en función de booleanos que indican que se está en ese modo, por lo tanto se
    // ponen todos en false.
    public void cancelar(){
        getBotonera().getMenuGUI().setProponiendoTrato(false);
        getBotonera().getMenuGUI().setInputActivo(false);
        getBotonera().getMenuGUI().setRenderMarco(true);
        if(!getBotonera().getBotones().contains(getBotonera().getMenuGUI().getBotonGUI())){
            getBotonera().getBotones().add(getBotonera().getMenuGUI().getBotonGUI());
        }
    }

    public void salirCarcel(){
        try {
            getApp().getJuego().getTurno().getAvatar().salirCarcel();
        } catch (Exception ignored) {}
    }

    public void ejecutarFuncion(){
        switch(getFuncion()){
            case cambiarModo:
                cambiarModo();
                break;
            case avanzar:
                avanzar();
                break;
            case finalizarTurno:
                finalizarTurno();
                break;
            case lanzarDados:
                lanzarDados();
                break;
            case hipotecar:
                hipotecar();
                break;
            case comprar:
                comprar();
                break;
            case deshipotecar:
                deshipotecar();
                break;
            case vender:
                vender();
                break;
            case venderCasa:
                venderVariable("casa");
                break;
            case venderHotel:
                venderVariable("hotel");
                break;
            case venderPiscina:
                venderVariable("piscina");
                break;
            case venderPista:
                venderVariable("pista");
                break;
            case edificar:
                edificar();
                break;
            case edificarCasa:
                edificarVariable("casa");
                break;
            case edificarHotel:
                edificarVariable("hotel");
                break;
            case edificarPiscina:
                edificarVariable("piscina");
                break;
            case edificarPista:
                edificarVariable("pista");
                break;
            case ayuda:
                Aplicacion.consola.imprimir("Seleccione un botón del que necesite ayuda.");
                break;
            case atras:
                atras();
                break;
            case estadisticasGlobales:
                estadisticasGlobales();
                break;
            case estadisticasUsuario:
                estadisticasJugador();
                break;
            case listar:
                listar();
                break;
            case listarEdificios:
                listarEdificios();
                break;
            case listarTratos:
                listarTrato();
                break;
            case aceptarTratos:
                aceptarTratos();
                break;
            case aceptacionTratos:
                aceptacionTratos();
                break;
            case eliminarTratos:
                eliminarTratos();
                break;
            case eliminacionTratos:
                eliminacionTratos();
                break;
            case aceptar:
                aceptar();
                break;
            case cancelar:
                cancelar();
                break;
            case salirCarcel:
                salirCarcel();
                break;
        }
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if(pulsandoBoton(posicionX, posicionY)){

            if(getBotonera().isAyuda()){
                // En caso de que la ayuda sea para el botón correspondiente de un trato, se imprime la descripción del trato
                if(getFuncion().equals(TipoFuncion.aceptacionTratos)){
                    Trato trato = getApp().getJuego().getTurno().getTratosRecibidos().get(getNombre());
                    Aplicacion.consola.imprimir(trato.toString());
                } else if(getFuncion().equals(TipoFuncion.eliminacionTratos)){
                    Trato trato = getApp().getJuego().getTurno().getTratosEmitidos().get(getNombre());
                    Aplicacion.consola.imprimir(trato.toString());
                }
                Aplicacion.consola.imprimir(TipoFuncion.toString(getFuncion()));
            } else {
                ejecutarFuncion();
            }

            if(getFuncion().equals(TipoFuncion.ayuda))
                getBotonera().setAyuda(true);
            else
                getBotonera().setAyuda(false);

        }
    }

    public void handleClickPulsado(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if(pulsandoBoton(posicionX, posicionY)){

            MediaPlayer reproductor = new MediaPlayer(getSonido());
            reproductor.play();
            setBotonActual(getBotonOscuro());
        }
    }

    public void handleClickSoltado(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if(pulsandoBoton(posicionX, posicionY)){
            setBotonActual(getBoton());
        }
    }

    public void render(double t){
        getGc().drawImage(getBotonActual(), 0, 0);
        if(getFuncion().equals(TipoFuncion.aceptacionTratos) || getFuncion().equals(TipoFuncion.eliminacionTratos)){
            renderNumTrato();
        }
    }

    public void renderNumTrato(){
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 20));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setLineWidth(1);

        Integer num = -1;

        for(Character numero : getNombre().toCharArray()){
            if(Aplicacion.consola.isInteger(numero.toString())){
                num = Integer.parseInt(numero.toString());
            }
        }

        if(num != -1) {
            getGc().fillText(num.toString(), ConstantesGUI.BOTON_ANCHO - 25, ConstantesGUI.BOTON_ALTO - 15);
        }
    }

    public void render(int fila, int columna, double t){

        int x = desplazamientoX(fila);
        int y = desplazamientoY(columna);

        setFila(fila);
        setColumna(columna);

        getTranslate().setX(x);
        getTranslate().setY(y);

        render(t);
    }

    @Override
    public boolean equals(Object obj) {

        // Si apuntan a la misma dirección de memoria
        if (this == obj) return (true);

        // Si el objeto con el que se compara apunta a null
        if (obj == null) return (false);

        // Si no pertenecen a la misma clase
        if (getClass() != obj.getClass()) return (false);

        // Se referencia el objeto a comparar mediante un objeto de la misma clase, para poder
        // llamar a sus métodos
        final BotonGUI otro = (BotonGUI) obj;

        // Si los nombres del botón son el mismo
        return (this.getNombre() == otro.getNombre());

    } /* Fin del método equals */
}
