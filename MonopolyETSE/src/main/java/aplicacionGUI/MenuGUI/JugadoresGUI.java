package aplicacionGUI.MenuGUI;

import aplicacion.Aplicacion;
import aplicacion.excepciones.NumMaximoJugadoresException;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.Juego;
import monopoly.jugadores.Jugador;
import resources.MenuGUI.Jugadores.JugadoresImagen;

import java.util.ArrayList;

public class JugadoresGUI {

    /* Atributos */

    // Nodo propiedad de la sección del menú (parte inferior)
    private final Group nodo;

    // Sensor asociado a esta sección
    private Rectangle sensor;

    //Juego
    private Juego juego;

    //ArrayList que almacena cada Jugador
    private ArrayList<JugadorGUI> jugadores;

    //Representación del tablero
    private final TableroGUI tableroGUI;

    /* Constructor */
    public JugadoresGUI(Group raiz, Juego juego, TableroGUI tableroGUI){

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

    public ArrayList<JugadorGUI> getJugadores() {
        return jugadores;
    }

    public TableroGUI getTableroGUI() {
        return tableroGUI;
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

        getJugadores().add(new JugadorGUI(this.nodo, jugador, jugadores.size()+1, getTableroGUI()));
    }

    public void render() {

        for(JugadorGUI jugadorGUI : getJugadores()){
            jugadorGUI.render();
        }

    }

}
