package aplicacionGUI.ejecucionAplicacion;

import aplicacionGUI.input.Input;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import resources.fases.ImagenesFases;

import java.util.ArrayList;

public abstract class Fase implements IFase {

    /* Atributos */

    // Aplicación gráfica asociada
    private final AplicacionGUI aplicacionGUI;

    // Nodo raíz de la aplicación
    private final Group raiz;

    // Ventana de la aplicación
    private final Stage ventana;

    // Escena de la aplicación
    private final Scene escena;

    // GC para representar el fases de la aplicación
    private final GraphicsContext gc;

    // ImagenesFases a representar
    private final Image fondo;

    // Conjunto de menús contextuales activos
    private final ArrayList<ContextMenu> menus;

    // Registro de inputs activos
    private final ArrayList<Input> inputsActivos;

    // Posiciones de eventos de ratón
    private double xPresionado;
    private double yPresionado;

    // Si se ha iniciado
    private boolean iniciado;



    /* Constructor */

    /**
     * Se crea una fase, asociando sus atributos con los de una aplicación gráfica dada y obteniendo el fases
     * especificado
     *
     * @param aplicacionGUI aplicación gráfica a asociar
     * @param fondo         nombre de la imagen que emplear como fases
     */
    public Fase(AplicacionGUI aplicacionGUI, String fondo) {

        if (aplicacionGUI == null) {
            System.err.println("Aplicación no inicializada");
            System.exit(1);
        }

        // Se guarda la aplicación
        this.aplicacionGUI = aplicacionGUI;

        // Se inicializan los atributos a partir de aquellos de la aplicación
        this.raiz = aplicacionGUI.getRaiz();
        this.ventana = aplicacionGUI.getVentana();
        this.escena = aplicacionGUI.getEscena();
        this.gc = aplicacionGUI.getGc();
        this.menus = aplicacionGUI.getMenus();
        this.inputsActivos = aplicacionGUI.getInputsActivos();

        // Inicialmente, no se ha iniciado
        this.iniciado = false;

        this.fondo = new Image(ImagenesFases.class.getResource(fondo).toString());
    }



    /* Getters y setters */

    public AplicacionGUI getAplicacionGUI() {
        return aplicacionGUI;
    }

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

    public boolean isIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
    }
}
