package aplicacionGUI.MenuGUI;
import aplicacionGUI.ConstantesGUI;
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
import monopoly.tablero.Tablero;
import resources.MenuGUI.MenuGUIFondo;

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


    /* Constructor */

    public MenuGUI(Group raiz, Juego juego, String imagen){

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
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

    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getFondo() {
        return fondo;
    }

    public void render() {

        // Se muestra la imagen
        getGc().drawImage(getFondo(), 0, 0);
        JugadoresGUI iJug = new JugadoresGUI(this.nodo, this.juego);
        iJug.render();
    }


}
