package aplicacionGUI.menuGUI.BotonesGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import resources.menuGUI.botones.BotonesImagenes;

import java.util.ArrayList;

public class BotonGUI {

    // Nombre del botón
    private final String nombre;

    // Imágenes del botón
    private final Image boton;
    private final Image botonOscuro;
    private Image botonActual;

    // Desplazamientos con respecto a la botone
    private final int desplazamientoX;
    private final int desplazamientoY;

    // Nodo del botón
    private final Group nodo;
    private final Canvas canvas;
    private final GraphicsContext gc;

    // Sensor asociado a este botón
    private Rectangle sensor;

    // Booleano para saber si el botón es animado
    private boolean animado;

    // Booleano para saber si es el botón de ayuda
    private boolean ayuda;

    public BotonGUI(Group raiz, String nombre, int fila, int columna, boolean animado, boolean ayuda){

        if(raiz == null){
            System.err.println("Raiz no inicializada");
            System.exit(1);
        }

        if(nombre == null){
            System.err.println("Nombre no inicializado");
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

        // Se añade el nodo
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana

        this.desplazamientoX = (ConstantesGUI.BOTON_ANCHO + ConstantesGUI.BOTON_SEPARACION_X) * fila + ConstantesGUI.BOTON_SEPARACION_X + 15;
        this.desplazamientoY = (ConstantesGUI.BOTON_ALTO + ConstantesGUI.BOTON_SEPARACION_Y) * columna  + ConstantesGUI.BOTON_SEPARACION_Y + 15;
        this.nodo.getTransforms().add(new Translate(desplazamientoX, desplazamientoY));

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

    public BotonGUI(Group raiz, String nombre, int fila, int columna){
        this(raiz, nombre, fila, columna, false, false);
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
        return desplazamientoX;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    public Rectangle getSensor() {
        return sensor;
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

}
