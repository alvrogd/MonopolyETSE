package aplicacionGUI.menuGUI.JugadoresGUI;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.menuGUI.MenuGUI;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.Juego;
import monopoly.jugadores.Jugador;

import java.util.ArrayList;

public class JugadoresGUI {

    /* Atributos */

    // Nodo propiedad de la sección del menú (parte inferior)
    private final Group nodo;

    // Sensor asociado a esta sección
    private Rectangle sensor;

    //Juego
    private Juego juego;

    //Menú
    private MenuGUI menuGUI;

    //ArrayList que almacena cada Jugador
    private ArrayList<JugadorGUI> jugadores;

    //Representación del tablero
    private final TableroGUI tableroGUI;

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

        // Se crea el correspondiente sensor
        this.sensor = new Rectangle(0, 0, ConstantesGUI.JUGADORES_ANCHO, ConstantesGUI.JUGADORES_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        this.jugadores = new ArrayList<>();
        this.tableroGUI = tableroGUI;
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

        getJugadores().add(new JugadorGUI(this.nodo, jugador, jugadores.size()+1, getTableroGUI(), getMenuGUI()));
    }

    public void render() {

        for(JugadorGUI jugadorGUI : getJugadores()){
            jugadorGUI.render();
        }

    }

}
