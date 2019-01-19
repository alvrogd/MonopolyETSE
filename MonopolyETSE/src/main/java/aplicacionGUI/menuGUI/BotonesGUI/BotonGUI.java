package aplicacionGUI.menuGUI.BotonesGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;
import resources.menuGUI.botones.BotonesImagenes;

import java.util.ArrayList;

public class BotonGUI {

    // Nombre del botón
    private final String nombre;

    // Funcion que tiene el botón
    private final TipoFuncion funcion;

    // Imágenes del botón
    private final Image boton;
    private final Image botonOscuro;
    private Image botonActual;

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

    // Booleano para saber si el botón es animado
    private boolean animado;

    // Booleano para saber si es el botón de ayuda
    private boolean ayuda;

    public BotonGUI(Group raiz, String nombre, TipoFuncion funcion, int fila, int columna, boolean animado, boolean ayuda){

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

        System.out.println(nombre);
        this.boton = new Image(BotonesImagenes.class.getResource(nombre + ".png").toString());
        this.botonOscuro = new Image(BotonesImagenes.class.getResource(nombre + "Oscuro.png").toString());
        this.botonActual = this.boton;

        this.animado = animado;
        this.ayuda = ayuda;
    }

    public BotonGUI(Group raiz, String nombre, TipoFuncion funcion, int fila, int columna){
        this(raiz, nombre, funcion, fila, columna, false, false);
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

    public void inhabilitarBoton(){

        getSensor().setX(-500);
        getSensor().setY(-500);

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

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if(pulsandoBoton(posicionX, posicionY)){
            System.out.println("Se ha pulsado el botón "+getNombre());
        }
    }

    public void handleClickPulsado(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if(pulsandoBoton(posicionX, posicionY)){
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

    public void render(){
        getGc().drawImage(getBotonActual(), 0, 0);
    }

    public void render(int fila, int columna){

        int x = desplazamientoX(fila);
        int y = desplazamientoY(columna);

        setFila(fila);
        setColumna(columna);

        getTranslate().setX(x);
        getTranslate().setY(y);
        habilitarBoton();

        render();
    }
}
