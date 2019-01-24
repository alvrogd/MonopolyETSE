package aplicacionGUI.menuGUI.JugadoresGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ImagenAnimada;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.menuGUI.MenuGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import resources.dineroMas.DineroMasImagen;
import resources.dineroMenos.DineroMenosReducido;

import java.util.ArrayList;
import java.util.HashMap;

public class JugadoresGUI {

    /* Atributos */

    // Nodo propiedad de la sección del menú (parte inferior)
    private final Group nodo;

    // Sensor asociado a esta sección
    private Rectangle sensor;

    //Juego
    private Juego juego;

    private final Canvas canvas;
    private final GraphicsContext gc;

    //Menú
    private MenuGUI menuGUI;

    //ArrayList que almacena cada Jugador
    private ArrayList<JugadorGUI> jugadores;

    //HashMap que almacena las imágenes animadas para cada JugadorGUI
    private HashMap<JugadorGUI, ArrayList<ImagenAnimada>> imagenesAnimadas;

    //Representación del tablero
    private final TableroGUI tableroGUI;

    //Booleano para saber si es el primer frame
    private boolean primerFrame;

    //Booleano para saber si se está animando
    private boolean animandose;

    private static final Media sonido = new Media(resources.sonidos.Sonidos.class.getResource(ConstantesGUI.SONIDO_DINERO).toString());

    /* Constructor */
    public JugadoresGUI(Group raiz, Juego juego, TableroGUI tableroGUI, MenuGUI menuGUI){

        if(raiz == null){
            System.err.println("Raiz no inicializada");
            System.exit(1);
        }

        if(tableroGUI == null){
            System.err.println("Tablero no inicializado");
            System.exit(1);
        }

        if(juego == null){
            System.err.println("Juego no inicializado");
            System.exit(1);
        }

        if(menuGUI == null){
            System.err.println("Menú no inicializado");
            System.exit(1);
        }

        this.menuGUI = menuGUI;

        // Se añade el nodo
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.JUGADORES_DESPLAZAMIENTO_X, ConstantesGUI.JUGADORES_DESPLAZAMIENTO_Y));

        this.canvas = new Canvas(ConstantesGUI.JUGADORES_DESPLAZAMIENTO_X, ConstantesGUI.JUGADORES_DESPLAZAMIENTO_Y);
        this.nodo.getChildren().add(canvas);

        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el correspondiente sensor
        this.sensor = new Rectangle(0, 0, ConstantesGUI.JUGADORES_ANCHO, ConstantesGUI.JUGADORES_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        this.jugadores = new ArrayList<>();
        this.tableroGUI = tableroGUI;
        this.imagenesAnimadas = new HashMap<>();
        this.primerFrame = true;
        this.animandose = false;
    }

    public static Media getSonido() {
        return sonido;
    }

    public boolean isPrimerFrame() {
        return primerFrame;
    }

    public void setPrimerFrame(boolean primerFrame) {
        this.primerFrame = primerFrame;
    }

    public boolean isAnimandose() {
        return animandose;
    }

    public void setAnimandose(boolean animandose) {
        this.animandose = animandose;
    }

    public HashMap<JugadorGUI, ArrayList<ImagenAnimada>> getImagenesAnimadas() {
        return imagenesAnimadas;
    }

    public void setImagenesAnimadas(HashMap<JugadorGUI, ArrayList<ImagenAnimada>> imagenesAnimadas) {
        this.imagenesAnimadas = imagenesAnimadas;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public ArrayList<JugadorGUI> getJugadores() {
        return jugadores;
    }

    public TableroGUI getTableroGUI() {
        return tableroGUI;
    }

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.JUGADORES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.JUGADORES_DESPLAZAMIENTO_Y;

        return(getSensor().contains(posicionX, posicionY));
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public MenuGUI getMenuGUI() {
        return menuGUI;
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - ConstantesGUI.JUGADORES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.JUGADORES_DESPLAZAMIENTO_Y;

        for(JugadorGUI jugadorGUI : getJugadores()){
            if(jugadorGUI.contienePosicion(posicionX, posicionY)){
                jugadorGUI.handleClickIzquierdo(posicionX, posicionY);
                break;
            }
        }
    }

    public void handleClickPulsado(double x, double y) {

        double posicionX = x - ConstantesGUI.JUGADORES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.JUGADORES_DESPLAZAMIENTO_Y;

        for(JugadorGUI jugadorGUI : getJugadores()){
            if(jugadorGUI.contienePosicion(posicionX, posicionY)){
                jugadorGUI.handleClickPulsado(posicionX, posicionY);
                break;
            }
        }
    }

    public void handleClickSoltado(double x, double y) {

        double posicionX = x - ConstantesGUI.JUGADORES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.JUGADORES_DESPLAZAMIENTO_Y;

        for(JugadorGUI jugadorGUI : getJugadores()){
            if(jugadorGUI.contienePosicion(posicionX, posicionY)){
                jugadorGUI.handleClickSoltado(posicionX, posicionY);
                break;
            }
        }
    }

    public void nuevoJugador(Jugador jugador){

        if(jugador == null){
            System.err.println("Jugador no inicializado");
            System.exit(1);
        }

        if(jugadores.size() == 6){
            System.err.println("No se pueden añadir más de 6 jugadores");
            System.exit(1);
        }

        JugadorGUI jugadorGUI = new JugadorGUI(this.nodo, jugador, jugadores.size()+1, getTableroGUI(), getMenuGUI());
        getJugadores().add(jugadorGUI);
        ArrayList<ImagenAnimada> imagenes = new ArrayList<>();
        getImagenesAnimadas().put(jugadorGUI, imagenes);

        imagenes.add(new ImagenAnimada(new DineroMasImagen(), ConstantesGUI.FRAMES_DINEROMAS, 0.1));
        imagenes.add(new ImagenAnimada(new DineroMenosReducido(), ConstantesGUI.FRAMES_DINEROMENOS, 0.1));

        jugadorGUI.getJugador().setAnimacionMenosDinero(false);
        jugadorGUI.getJugador().setAnimacionMasDinero(false);

    }

    public void render(double t) {

        for(JugadorGUI jugadorGUI : getJugadores()){
            jugadorGUI.render();
            if(jugadorGUI.getJugador().isAnimacionMasDinero()){
                renderAnimacion(t, true, jugadorGUI);
            } else if (jugadorGUI.getJugador().isAnimacionMenosDinero()){
                renderAnimacion(t, false, jugadorGUI);
            }
        }

    }

    private void renderAnimacion(double t, boolean masDinero, JugadorGUI jugadorGUI){

        Image frame;
        ImagenAnimada animacion;

        if(masDinero){
            animacion = getImagenesAnimadas().get(jugadorGUI).get(0);
        } else {
            animacion = getImagenesAnimadas().get(jugadorGUI).get(1);
        }

        if(isPrimerFrame()){
            animacion.setTiempoInicio(t);
            setPrimerFrame(false);
            MediaPlayer reproductor = new MediaPlayer(getSonido());
            reproductor.setVolume(0.6);
            reproductor.play();
        }

        frame = animacion.getFrame(t);

        jugadorGUI.getGc().drawImage(frame, ConstantesGUI.BARRA_JUGADOR_ANCHO-125, -15);

        if(animacion.getIndice(t) == animacion.getFrames().size() - 1){
            setPrimerFrame(true);
            if(masDinero){
                jugadorGUI.getJugador().setAnimacionMasDinero(false);
            } else {
                jugadorGUI.getJugador().setAnimacionMenosDinero(false);
            }
        }
    }

}
