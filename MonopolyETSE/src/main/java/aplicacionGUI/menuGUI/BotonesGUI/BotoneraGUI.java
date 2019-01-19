package aplicacionGUI.menuGUI.BotonesGUI;

import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.Constantes;
import monopoly.Juego;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BotoneraGUI {

    /* Atributos */

    private boolean puedoImprimir = false;

    // Nodo propiedad de la sección de la botonera
    private final Group nodo;

    // Sensor asociado a esta sección
    private Rectangle sensor;

    // Botón de atras
    private BotonGUI botonAtras;

    // Juego
    private Juego juego;

    // Aplicacion
    private Aplicacion aplicacion;

    // Botones que pueden estar en la botonera
    private ArrayList<BotonGUI> botones;

    // Botones que pueden estar en la pagina concreta de un boton
    private HashMap<TipoFuncion, ArrayList<BotonGUI>> botonesPagina;

    // Botones que están en la botonera
    private ArrayList<BotonGUI> botonesActuales;

    // Si la botonera está actualmente en una página
    private boolean pagina;

    // Si está en una página, cual era la función del botón
    private TipoFuncion funcionPagina;

    /* Constructor */
    public BotoneraGUI(Group raiz, Aplicacion app){

        if(raiz == null){
            System.err.println("Raiz no inicializada");
            System.exit(1);
        }

        if(app == null){
            System.err.println("Juego no inicializado");
            System.exit(1);
        }

        // Se añade el nodo
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        this.juego = app.getJuego();
        this.aplicacion = app;
        // Se establece su correspondiente posición en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.BOTONES_DESPLAZAMIENTO_X, ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y));

        // Se crea el correspondiente sensor
        this.sensor = new Rectangle(0, 0, ConstantesGUI.BOTONES_ANCHO, ConstantesGUI.BOTONES_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        this.botones = new ArrayList<>();
        this.botonesActuales = new ArrayList<>();
        this.botonesPagina = new HashMap<>();
        this.pagina = false;
        this.funcionPagina = null;

        crearBotones();

    }

    public void crearBotones(){
        for(TipoFuncion funcion : TipoFuncion.values()) {
            if(funcion.isBotonAsignado()) {
                if(funcion.equals(TipoFuncion.cambiarModo)){
                    nuevoBoton(funcion.toString(), funcion, true, false, false);
                } else if(funcion.equals(TipoFuncion.ayuda)){
                    nuevoBoton(funcion.toString(), funcion, false, true, false);
                } else if(funcion.equals(TipoFuncion.atras)){
                    nuevoBoton(funcion.toString(), funcion, false, false, true);
                } else {
                    nuevoBoton(funcion);
                }
            }
        }
    }

    public void nuevoBoton(TipoFuncion funcion){

        if(funcion.equals(TipoFuncion.ayuda)){
            nuevoBoton(funcion.toString(), funcion, false, true, false);
        } else if(funcion.equals(TipoFuncion.atras)){
            nuevoBoton(funcion.toString(), funcion, false, false, true);
        } else {
            nuevoBoton(funcion.toString(), funcion, false, false ,false);
        }
    }

    public void setPagina(boolean pagina) {
        this.pagina = pagina;
    }

    public boolean isPagina() {
        return pagina;
    }

    public BotonGUI getBotonAtras() {
        return botonAtras;
    }

    public TipoFuncion getFuncionPagina() {
        return funcionPagina;
    }

    public void setFuncionPagina(TipoFuncion funcionPagina) {
        this.funcionPagina = funcionPagina;
    }

    public Aplicacion getAplicacion() {
        return aplicacion;
    }

    public void nuevoBoton(String nombre, TipoFuncion funcion, boolean animado, boolean ayuda, boolean atras){

        int columna = getBotones().size() / ConstantesGUI.BOTONES_POR_FILA;
        int fila = getBotones().size() % ConstantesGUI.BOTONES_POR_FILA;

        if(ayuda){
            fila = ConstantesGUI.BOTONES_POR_FILA - 1;
            columna = ConstantesGUI.BOTONES_COLUMNAS - 1;
        }

        if(atras){
            fila = ConstantesGUI.BOTONES_POR_FILA - 1;
            columna = ConstantesGUI.BOTONES_COLUMNAS - 1;
        }

        if(!funcion.isMenuPrincipal()){

            if(!getBotonesPagina().containsKey(funcion.getFuncionRaiz())) {
                getBotonesPagina().put(funcion.getFuncionRaiz(), new ArrayList<>());
            }

            int size = getBotonesPagina().get(funcion.getFuncionRaiz()).size();

            if(atras){
                fila = ConstantesGUI.BOTONES_POR_FILA - 1;
                columna = ConstantesGUI.BOTONES_COLUMNAS - 1;
            } else {
                fila = size / ConstantesGUI.BOTONES_POR_FILA;
                columna = size % ConstantesGUI.BOTONES_POR_FILA;
            }

            if(animado){
                getBotonesPagina().get(funcion.getFuncionRaiz()).add(new BotonAnimadoGUI(this, getNodo(), nombre, getAplicacion(), funcion, fila, columna, funcion.getLocalizacion(), funcion.getFrames(), funcion.getDuracion()));
            } else {
                getBotonesPagina().get(funcion.getFuncionRaiz()).add(new BotonGUI(this, getNodo(), getAplicacion(), nombre, funcion, fila, columna, animado, ayuda));
            }

        } else {
            if(animado){
                getBotones().add(new BotonAnimadoGUI(this, getNodo(), nombre, getAplicacion(), funcion, fila, columna, funcion.getLocalizacion(), funcion.getFrames(), funcion.getDuracion()));
            } else {
                BotonGUI boton = new BotonGUI(this, getNodo(), getAplicacion(), nombre, funcion, fila, columna, animado, ayuda);
                if(atras)
                    this.botonAtras = boton;
                getBotones().add(boton);
            }
        }
    }

    public ArrayList<BotonGUI> getBotonesActuales() {
        return botonesActuales;
    }

    public void setBotonesActuales(ArrayList<BotonGUI> botonesActuales) {
        this.botonesActuales = botonesActuales;
    }

    public HashMap<TipoFuncion, ArrayList<BotonGUI>> getBotonesPagina() {
        return botonesPagina;
    }

    public Group getNodo() {
        return nodo;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public Juego getJuego() {
        return juego;
    }

    public ArrayList<BotonGUI> getBotones() {
        return botones;
    }

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.BOTONES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y;

        return(getSensor().contains(posicionX, posicionY));
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - ConstantesGUI.BOTONES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y;

        ArrayList<BotonGUI> botonesRecorrer = new ArrayList<>();

        if(isPagina()){
            botonesRecorrer.addAll(getBotones());
            botonesRecorrer.addAll(getBotonesPagina().get(getFuncionPagina()));
        } else {
            botonesRecorrer.addAll(getBotones());
        }

        for(BotonGUI botonGUI : botonesRecorrer){
            if(botonGUI.contienePosicion(posicionX, posicionY)){
                System.out.println(botonGUI.getNombre());
                botonGUI.handleClickIzquierdo(posicionX, posicionY);
                break;
            }
        }

        this.puedoImprimir = true;
    }

    public void handleClickPulsado(double x, double y) {

        double posicionX = x - ConstantesGUI.BOTONES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y;

        ArrayList<BotonGUI> botonesRecorrer = new ArrayList<>();

        if(isPagina()){
            botonesRecorrer.addAll(getBotones());
            botonesRecorrer.addAll(getBotonesPagina().get(getFuncionPagina()));
        } else {
            botonesRecorrer.addAll(getBotones());
        }

        for(BotonGUI botonGUI : botonesRecorrer){
            if(botonGUI.contienePosicion(posicionX, posicionY)){
                botonGUI.handleClickPulsado(posicionX, posicionY);
                break;
            }
        }
    }

    public void handleClickSoltado(double x, double y) {

        double posicionX = x - ConstantesGUI.BOTONES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y;

        ArrayList<BotonGUI> botonesRecorrer = new ArrayList<>();

        if(isPagina()){
            botonesRecorrer.addAll(getBotones());
            botonesRecorrer.addAll(getBotonesPagina().get(getFuncionPagina()));
        } else {
            botonesRecorrer.addAll(getBotones());
        }

        for(BotonGUI botonGUI : botonesRecorrer){
            if(botonGUI.contienePosicion(posicionX, posicionY)){
                botonGUI.handleClickSoltado(posicionX, posicionY);
                break;
            }
        }
    }

    public void actualizarBotones(){

        if(getJuego().isIniciado()) {
            Casilla casilla = getJuego().getTurno().getAvatar().getPosicion();
            HashSet<TipoFuncion> funciones = getJuego().funcionesARealizar();
            ArrayList<BotonGUI> botones;
            ArrayList<BotonGUI> botonesRecorrer = new ArrayList<>();
            setBotonesActuales(new ArrayList<>());

            // En caso de que actualmente se esté en una página de botones (que no sea la principal) los botones donde se
            // comparará serán los asociados a esa página, en caso contrario serán todos los botones.
            if(isPagina()){
                botones = getBotonesPagina().get(getFuncionPagina());

                if(!botones.contains(getBotonAtras())){
                    botones.add(getBotonAtras());
                }

                // Se añaden los botones de la página y los botones a inhabilitar en los botones a recorrer
                botonesRecorrer.addAll(botones);
                botonesRecorrer.addAll(getBotones());
                funciones.add(TipoFuncion.atras);
            } else {
                botones = getBotones();
                botonesRecorrer = getBotones();
                funciones.add(TipoFuncion.ayuda);
            }

            for (BotonGUI boton : botonesRecorrer) {
                boton.getGc().clearRect(0,0, ConstantesGUI.BOTON_ANCHO, ConstantesGUI.BOTON_ALTO);

                // En caso de que las funciones a realizar contengan el botón actual, este podrá ser candidato a entrar en
                // la botonera

                if (funciones.contains(boton.getFuncion())) {

                    // Se comprueba si pertenece a los botones de la página
                    if(botones.contains(boton)) {
                        boton.habilitarBoton();
                        getBotonesActuales().add(boton);
                    }
                    else {
                        boton.inhabilitarBoton();
                    }

                } else {
                    // Si no pertenece a las funciones a realizar se inhabilita el botón
                    if(boton.getFuncion().equals(TipoFuncion.cambiarModo)){
                        //En caso de ser el botón cambiarModo, este sigue quedando en la botonera, pero inactivo.
                        if(isPagina()) {
                            getBotonesActuales().add(boton);
                        }
                    }
                    boton.inhabilitarBoton();
                }
            }
        }

    }


    public void render(double t){

        int fila = 0, columna = 0;
        actualizarBotones();
        for(BotonGUI botonGUI : getBotonesActuales()){

            if(botonGUI.getFuncion().equals(TipoFuncion.ayuda) || botonGUI.getFuncion().equals(TipoFuncion.atras)){
                botonGUI.render(ConstantesGUI.BOTONES_POR_FILA-1, ConstantesGUI.BOTONES_COLUMNAS-1, t);
            } else {
                botonGUI.render(fila, columna, t);

                if (puedoImprimir)
                    System.out.println(botonGUI.getNombre() + " en la posición (fila, columna): (" + fila + ", " + columna + ")");

                fila++;
                columna += fila / ConstantesGUI.BOTONES_POR_FILA;
                fila %= ConstantesGUI.BOTONES_POR_FILA;
            }
        }

        if(puedoImprimir)
            this.puedoImprimir = false;

    }

  }
