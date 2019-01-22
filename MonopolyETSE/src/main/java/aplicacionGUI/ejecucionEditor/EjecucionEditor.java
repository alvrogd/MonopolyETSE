package aplicacionGUI.ejecucionEditor;

import aplicacionGUI.AplicacionGUI;
import aplicacionGUI.editor.Editor;
import aplicacionGUI.ejecucionEditor.handlers.Pulsacion;
import aplicacionGUI.ejecucionEditor.handlers.Release;
import aplicacionGUI.input.Input;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import resources.fondo.Fondo;

import java.util.ArrayList;

public class EjecucionEditor {

    /* Atributos */

    // Nodo raíz de la aplicación
    private final Group raiz;

    // Ventana de la aplicación
    private final Stage ventana;

    // Escena de la aplicación
    private final Scene escena;

    // GC para representar el fondo de la aplicación
    private final GraphicsContext gc;

    // Fondo a representar
    private final Image fondo = new Image(Fondo.class.getResource("fondo.jpg").toString());

    // Conjunto de menús contextuales activos
    private final ArrayList<ContextMenu> menus;

    // Registro de inputs activos
    private final ArrayList<Input> inputsActivos;

    // Editor del tablero
    private Editor editor;

    // Posiciones de eventos de ratón
    private double xPresionado;
    private double yPresionado;



    /* Constructor */

    /**
     * Se crea una instancia que relaciona ofrece en ventana un editor para el tablero del Monopoly
     *
     * @param aplicacionGUI aplicación gráfica asociada
     */
    public EjecucionEditor(AplicacionGUI aplicacionGUI) {

        if (aplicacionGUI == null) {
            System.err.println("Aplicación no inicializada");
            System.exit(1);
        }

        // Se inicializan los atributos a partir de aquellos de la aplicación
        this.raiz = aplicacionGUI.getRaiz();
        this.ventana = aplicacionGUI.getVentana();
        this.escena = aplicacionGUI.getEscena();
        this.gc = aplicacionGUI.getGc();
        this.menus = aplicacionGUI.getMenus();
        this.inputsActivos = aplicacionGUI.getInputsActivos();
    }



    /* Getters y setters */

    public Group getRaiz() {
        return raiz;
    }

    public Stage getVentana() {
        return ventana;
    }

    public Scene getEscena() {
        return escena;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getFondo() {
        return fondo;
    }

    public ArrayList<ContextMenu> getMenus() {
        return menus;
    }

    public ArrayList<Input> getInputsActivos() {
        return inputsActivos;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public double getxPresionado() {
        return xPresionado;
    }

    public void setxPresionado(double xPresionado) {
        this.xPresionado = xPresionado;
    }

    public double getyPresionado() {
        return yPresionado;
    }

    public void setyPresionado(double yPresionado) {
        this.yPresionado = yPresionado;
    }



    /* Métodos */

    /**
     * Se inicia el editor del tablero
     */
    public void iniciar() {

        // Se crea un editor
        setEditor(new Editor(getRaiz()));

        // Se define la acción al presionar un botón del ratón
        escena.setOnMousePressed(new Pulsacion(this));

        // Se define la acción al soltar un botón del ratón
        escena.setOnMouseReleased(new Release(this));

        // Se inicia el game loop
        EjecucionEditorLoop loop = new EjecucionEditorLoop(this);
        loop.iniciar();

        // Se muestra la ventana
        ventana.show();
    }
}
