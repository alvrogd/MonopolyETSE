package aplicacionGUI.menuGUI;
import aplicacion.excepciones.NumMaximoJugadoresException;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.menuGUI.BotonesGUI.BotoneraGUI;
import aplicacionGUI.menuGUI.JugadoresGUI.JugadoresGUI;
import aplicacionGUI.menuGUI.registroGUI.RegistroGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import monopoly.tablero.Tablero;
import resources.menuGUI.MenuGUIFondo;

public class MenuGUI{

    /* Atributos */

    // Nodo propiedad de la sección del menú (parte inferior)
    private final Group nodo;

    // Sensor asociado a esta sección
    private Rectangle sensor;

    // Juego
    private final Juego juego;

    // Contexto en el que representar objetos
    private final GraphicsContext gc;

    // Imagen de fondo para el menú
    private final Image fondo;

    // Canvas para la representación del menú
    private final Canvas canvas;
    
    // Registro del menú
    private final RegistroGUI registroGUI;

    // Representación de la zona de los jugadores
    private final JugadoresGUI jugadoresGUI;

    // Representación de la botonera
    private final BotoneraGUI botonera;

    // Número de jugadores
    private int numJugadores;

    /* Constructor */

    public MenuGUI(Group raiz, Juego juego, String imagen, TableroGUI tableroGUI){

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if(tableroGUI == null){
            System.err.println("TableroGUI no inicializado");
            System.exit(1);
        }

        if (juego == null) {
            System.err.println("Juego no inicializado");
            System.exit(1);
        }

        if(imagen == null){
            System.err.println("Imagen no inicializada");
            System.exit(1);
        }

        // Se asigna el juego
        this.juego = juego;

        // Se añade el nodo a la raíz
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece la posición correspondiente en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.MENU_DESPLAZAMIENTO_X, ConstantesGUI.MENU_DESPLAZAMIENTO_Y));

        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.CONTROLES_ANCHO, ConstantesGUI.CONTROLES_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se crea un canvas en el nuevo nodo para representar el menú
        this.canvas = new Canvas( ConstantesGUI.CONTROLES_ANCHO, ConstantesGUI.CONTROLES_ALTO);
        this.nodo.getChildren().add(canvas);

        this.gc = this.canvas.getGraphicsContext2D();

        this.fondo = new Image(MenuGUIFondo.class.getResource(imagen).toString());
        
        // Se crea el registro del medio
        this.registroGUI = new RegistroGUI(this.nodo);

        // Se crea la representación de los jugadores (parte derecha)
        this.jugadoresGUI = new JugadoresGUI(this.nodo, juego, tableroGUI);

        this.botonera = new BotoneraGUI(this.nodo, juego);

        this.numJugadores = 0;
    }

    public BotoneraGUI getBotonera() {
        return botonera;
    }

    public void anadirJugador(Jugador jugador){
        if(jugador == null){
            System.err.println("Jugador no inicializado");
            System.exit(1);
        }
        getJugadoresGUI().nuevoJugador(jugador);
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getFondo() {
        return fondo;
    }

    public JugadoresGUI getJugadoresGUI() {
        return jugadoresGUI;
    }

    public Juego getJuego() {
        return juego;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        if(numJugadores < 0){
            System.err.println("Número de jugadores no puede ser negativo");
            System.exit(1);
        }
        this.numJugadores = numJugadores;
    }

    public RegistroGUI getRegistroGUI() {
        return registroGUI;
    }

    public void incrementarJugadores(int incremento){
        this.numJugadores += incremento;
    }

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.MENU_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MENU_DESPLAZAMIENTO_Y;

        return(getSensor().contains(posicionX, posicionY));
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - ConstantesGUI.MENU_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MENU_DESPLAZAMIENTO_Y;

        if (getJugadoresGUI().contienePosicion(posicionX, posicionY)) {
            getJugadoresGUI().handleClickIzquierdo(posicionX, posicionY);
        } else if (getBotonera().contienePosicion(posicionX, posicionY)) {
            getBotonera().handleClickIzquierdo(posicionX, posicionY);
        }
    }

    public void handleClickPulsado(double x, double y){
        double posicionX = x - ConstantesGUI.MENU_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MENU_DESPLAZAMIENTO_Y;

        if( getJugadoresGUI().contienePosicion(posicionX, posicionY)) {
            getJugadoresGUI().handleClickPulsado(posicionX, posicionY);
        } else if(getBotonera().contienePosicion(posicionX, posicionY)){
            getBotonera().handleClickPulsado(posicionX, posicionY);
        }
    }

    public void handleClickSoltado(double x, double y){
        double posicionX = x - ConstantesGUI.MENU_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MENU_DESPLAZAMIENTO_Y;

        if( getJugadoresGUI().contienePosicion(posicionX, posicionY)) {
            getJugadoresGUI().handleClickSoltado(posicionX, posicionY);
        } else if(getBotonera().contienePosicion(posicionX, posicionY)){
            getBotonera().handleClickSoltado(posicionX, posicionY);
        }
    }

    public void render(){

        if(getJuego().getNombresJugadores().size() > getNumJugadores()){
            int jugadoresNuevos = getJuego().getNombresJugadores().size() - getNumJugadores();

            for(int i = getNumJugadores(); i < getJuego().getNombresJugadores().size(); i++){
                anadirJugador(getJuego().getJugador(getJuego().getNombresJugadores().get(i)));
            }

            incrementarJugadores(jugadoresNuevos);
        }

        // Se muestra la imagen
        getGc().drawImage(getFondo(), 0, 0);
        getJugadoresGUI().render();
        getBotonera().render();

    }


}
