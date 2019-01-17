package aplicacionGUI.MenuGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import resources.MenuGUI.Jugadores.JugadoresImagen;
import resources.MenuGUI.MenuGUIFondo;

public class JugadorGUI {

    private final Group nodo;
    private final Canvas canvas;
    private final GraphicsContext gc;

    private final Image barra;
    private final Image barraOscura;

    private Image barraActual;
    private final Image avatar;

    private final Jugador jugador;

    private final int desplazamientoX;
    private final int desplazamientoY;

    private Rectangle sensor;
    private Rectangle boton;

    /* Constructor */

    public JugadorGUI(Group raiz, Jugador jugador, int numJugador, TableroGUI tableroGUI){

        if(raiz == null){
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if(jugador == null){
            System.err.println("Jugador no inicializado");
            System.exit(1);
        }

        if(tableroGUI == null){
            System.err.println("TableroGUI no inicializado");
            System.exit(1);
        }

        if(numJugador < 1){
            System.err.println("El jugador no puede ser negativo o 0");
            System.exit(1);
        }

        this.jugador = jugador;

        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        this.desplazamientoX = ConstantesGUI.BARRA_DESPLAZAMIENTO_X;
        this.desplazamientoY = ConstantesGUI.BARRA_DESPLAZAMIENTO_Y +
                (ConstantesGUI.BARRA_JUGADOR_ALTO + ConstantesGUI.JUGADORES_SEPARACION) * (numJugador - 1);

        this.nodo.getTransforms().add(new Translate(this.desplazamientoX, this.desplazamientoY));

        this.canvas = new Canvas(ConstantesGUI.BARRA_JUGADOR_ANCHO, ConstantesGUI.BARRA_JUGADOR_ALTO);
        this.nodo.getChildren().add(canvas);

        this.gc = this.canvas.getGraphicsContext2D();

        // Sensor para la zona de jugadores
        this.sensor = new Rectangle(0, 0, ConstantesGUI.BARRA_JUGADOR_ANCHO, ConstantesGUI.BARRA_JUGADOR_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Sensor para el botón
        this.boton = new Rectangle(ConstantesGUI.BARRA_DESPLAZAMIENTO_BOTON_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_BOTON_Y,
                ConstantesGUI.BARRA_JUGADOR_ANCHO, ConstantesGUI.BARRA_JUGADOR_ALTO);
        this.boton.setFill(Color.TRANSPARENT);

        this.barra = new Image(JugadoresImagen.class.getResource(ConstantesGUI.BARRA_NOMBRE).toString());
        this.barraActual = this.barra;

        this.barraOscura = new Image(JugadoresImagen.class.getResource(ConstantesGUI.BARRA_NOMBRE_OSCURA).toString());
        this.avatar = tableroGUI.getRepresentacionesAvatares().get(jugador.getAvatar().getIdentificador());

    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getBarra() {
        return barra;
    }

    public Image getAvatar() {
        return avatar;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int getDesplazamientoX() {
        return desplazamientoX;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    public Image getBarraActual() {
        return barraActual;
    }

    public Image getBarraOscura() {
        return barraOscura;
    }

    public void setBarraActual(Image barraActual) {
        this.barraActual = barraActual;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public Rectangle getBoton() {
        return boton;
    }

    public void renderBarra(){
        getGc().drawImage(getBarraActual(), 0, 0);
    }

    public void renderAvatar(){
        getGc().drawImage(getAvatar(), ConstantesGUI.BARRA_DESPLAZAMIENTO_AVATAR_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_AVATAR_Y);
    }

    public void renderNombre(){
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setLineWidth(1);

        getGc().fillText(getJugador().getNombre(), ConstantesGUI.BARRA_DESPLAZAMIENTO_NOMBRE_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_NOMBRE_Y);
    }

    public void renderDinero(){
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setLineWidth(1);

        getGc().fillText(Integer.toString(getJugador().getFortuna()) + "K €", ConstantesGUI.BARRA_DESPLAZAMIENTO_DINERO_X, ConstantesGUI.BARRA_DESPLAZAMIENTO_DINERO_Y);
    }

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        return(getSensor().contains(posicionX, posicionY));
    }

    public boolean pulsandoBoton(double x, double y){
        double posicionX = x;
        double posicionY = y;

        return(getBoton().contains(posicionX, posicionY));
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if(pulsandoBoton(posicionX, posicionY)){
            System.out.println("Se ha pulsado el botón TRATO");
        }
    }

    public void handleClickPulsado(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if(pulsandoBoton(posicionX, posicionY)){
            setBarraActual(getBarraOscura());
        }
    }

    public void handleClickSoltado(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        if(pulsandoBoton(posicionX, posicionY)){
            setBarraActual(getBarra());
        }
    }

    public void render(){
        renderBarra();
        renderAvatar();
        renderNombre();
        renderDinero();
    }

}
