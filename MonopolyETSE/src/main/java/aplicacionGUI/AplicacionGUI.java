package aplicacionGUI;

import aplicacionGUI.input.Input;
import aplicacionGUI.ejecucionJuego.EjecucionJuego;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import resources.fondo.Fondo;

import java.util.ArrayList;
import java.util.Scanner;

public class AplicacionGUI {

    /* Atributos */

    // Ventana de la aplicación
    private final Stage ventana;

    // Fondo de la ventana
    private final Image fondo = new Image(Fondo.class.getResource("fondo.jpg").toString());

    // Nodo raíz de la ventana
    private final Group raiz;

    // Escena para la ventana
    private final Scene escena;

    // Canvas en el que representar el fondo
    private final Canvas canvas;

    // Contexto gráfico asociado al canvas
    private final GraphicsContext gc;

    // Menús contextuales activos;
    private final ArrayList<ContextMenu> menus;

    // Inputs activos
    private final ArrayList<Input> inputsActivos;



    /* Constructor */

    public AplicacionGUI(Stage ventana) {

        if( ventana == null ) {
            System.err.println("Ventana no inicializada");
            System.exit(1);
        }

        // Se guarda la ventana dada y se le cambia el nombre
        this.ventana = ventana;
        this.ventana.setTitle( "MonopolyETSE GUI" );

        // Se crea un nodo raíz
        this.raiz = new Group();

        // Se añade a una escena nueva
        this.escena = new Scene( raiz );

        // Se añade la escena a la ventana
        this.ventana.setScene( escena );

        // Se establece un estilo personalizado para la escena (para el registro y recuadro del input)
        this.escena.getStylesheets().add(ConstantesGUI.class.getResource("EstilosCSS.css").toExternalForm());

        // Se crea un canvas en el que representar el fondo
        this.canvas = new Canvas( ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO );
        this.raiz.getChildren().add(canvas);

        // Se crea un entorno que manipular a partir del canvas
        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea un array en el que registrar menús contextuales abiertos
        this.menus = new ArrayList<>();

        // Se crea un array en el que registrar los inputs
        this.inputsActivos = new ArrayList<>();

        // Se establece en los inputs la referencia al conjunto de inputs activos
        Input.setInputsActivos(this.inputsActivos);

        // Se establece en los inputs la referencia al nodo raíz de la aplicación
        Input.setRaiz(this.raiz);
    }



    /* Getters y setters */

    public Stage getVentana() {
        return ventana;
    }

    public Image getFondo() {
        return fondo;
    }

    public Group getRaiz() {
        return raiz;
    }

    public Scene getEscena() {
        return escena;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public ArrayList<ContextMenu> getMenus() {
        return menus;
    }

    public ArrayList<Input> getInputsActivos() {
        return inputsActivos;
    }



    /* Métodos */

    public void iniciar() {

        Scanner scanner = new Scanner(System.in);

        int opcion = scanner.nextInt();

        if( opcion == 1 ) {
            EjecucionJuego ejecucionJuego = new EjecucionJuego(this);
            ejecucionJuego.iniciar();
        }

        else {
            //probarEditor(raiz, ventana, escena, gc, fondo, menus);
        }
    }
}
