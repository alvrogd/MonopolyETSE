package aplicacionGUI.MenuGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.Juego;
import resources.MenuGUI.Jugadores.JugadoresImagen;

public class JugadoresGUI {

    /* Atributos */

    // Nodo propiedad de la sección del menú (parte inferior)
    private final Group nodo;

    // Sensor asociado a esta sección
    private Rectangle sensor;

    //Juego
    private Juego juego;

    // Contexto en el que representar objetos
    private final GraphicsContext gc;

    // Imagen de fondo para el menú
    private final Image imagen;

    // Canvas para la representación del menú
    private final Canvas canvas;


    /* Constructor */
    public JugadoresGUI(Group raiz, Juego juego){

        if(raiz == null){
            System.err.println("Raiz no inicializada");
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

        // Se crea un canvas en el nuevo nodo para representar los jugadores
        this.canvas = new Canvas( ConstantesGUI.JUGADORES_ANCHO, ConstantesGUI.JUGADORES_ALTO);
        this.nodo.getChildren().add(canvas);

        // Se genera un contexto a partir del canvas para insertar la representación
        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el correspondiente sensor
        this.sensor = new Rectangle(0, 0, ConstantesGUI.JUGADORES_ANCHO, ConstantesGUI.JUGADORES_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        //Se obtiene la imagen correspondiente
        this.imagen = new Image(JugadoresImagen.class.getResource("jugador.png").toString());
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getImagen() {
        return imagen;
    }

    public void render() {

        // Se muestra la imagen
        getGc().drawImage(getImagen(), 0, 0);
    }

}
