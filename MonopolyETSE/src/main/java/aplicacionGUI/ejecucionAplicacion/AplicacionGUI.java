package aplicacionGUI.ejecucionAplicacion;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.InformacionCasillaGUI;
import aplicacionGUI.ejecucionAplicacion.fases.faseBienvenida.FaseBienvenida;
import aplicacionGUI.ejecucionAplicacion.fases.faseEditor.FaseEditor;
import aplicacionGUI.ejecucionAplicacion.fases.faseJuego.FaseJuego;
import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.FaseJugador;
import aplicacionGUI.ejecucionAplicacion.fases.faseSeleccion.FaseSeleccion;
import aplicacionGUI.input.Input;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import monopoly.jugadores.TipoAvatar;

import java.util.ArrayList;
import java.util.HashMap;

public class AplicacionGUI {

    /* Atributos */

    // Ventana de la aplicación
    private final Stage ventana;

    // ImagenesFases de la ventana
    //private final Image fondo = new Image(ImagenesFases.class.getResource("fondo.jpg").toString());

    // Nodo raíz de la ventana
    private final Group raiz;

    // Escena para la ventana
    private final Scene escena;

    // Canvas en el que representar el fases
    private final Canvas canvas;

    // Contexto gráfico asociado al canvas
    private final GraphicsContext gc;

    // Menús contextuales activos;
    private final ArrayList<ContextMenu> menus;

    // Inputs activos
    private final ArrayList<Input> inputsActivos;

    // Jugadores almacenados
    private HashMap<String, TipoAvatar> jugadoresCreados;

    // Fase en la que se encuentra la aplicación
    private TipoFase tipoFase;
    private IFase fase;

    // Información de un tablero personalizado
    private ArrayList<InformacionCasillaGUI> tableroPersonalizado;

    // Loop
    private Loop loop;

    // Música para el editor
    private final MediaPlayer reproductor = new MediaPlayer(new Media(resources.sonidos.Sonidos.class.getResource(
            ConstantesGUI.MUSICA_EDITOR).toString()));



    /* Constructor */

    /**
     * Se crea una aplicación gráfica para jugar al Monopoly
     *
     * @param ventana ventana en la que mostrar la aplicación
     */
    public AplicacionGUI(Stage ventana) {

        if (ventana == null) {
            System.err.println("Ventana no inicializada");
            System.exit(1);
        }

        // Se guarda la ventana dada y se le cambia el nombre
        this.ventana = ventana;
        this.ventana.setTitle("MonopolyETSE GUI");

        // Se crea un nodo raíz
        this.raiz = new Group();

        // Se añade a una escena nueva
        this.escena = new Scene(raiz);

        // Se añade la escena a la ventana
        this.ventana.setScene(escena);

        // Se establece un estilo personalizado para la escena (para el registro y recuadro del input)
        this.escena.getStylesheets().add(ConstantesGUI.class.getResource("EstilosCSS.css").toExternalForm());

        // Se crea un canvas en el que representar el fases
        this.canvas = new Canvas(ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);
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

        // Inicialmente, la aplicación se encuentra en la fase de bienvenida
        this.tipoFase= TipoFase.bienvenida;

        // Inicialmente, no se ha creado un tablero personalizado
        this.tableroPersonalizado = null;

        // Y no hay jugadores aún
        this.jugadoresCreados = new HashMap<>();
    }



    /* Getters y setters */

    public Stage getVentana() {
        return ventana;
    }

    /*public Image getFondo() {
        return fondo;
    }*/

    public HashMap<String, TipoAvatar> getJugadoresCreados() {
        return jugadoresCreados;
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

    public TipoFase getTipoFase() {
        return tipoFase;
    }

    public void setTipoFase(TipoFase tipoFase) {
        this.tipoFase = tipoFase;
    }

    public IFase getFase() {
        return fase;
    }

    public void setFase(IFase fase) {
        this.fase = fase;
    }

    public ArrayList<InformacionCasillaGUI> getTableroPersonalizado() {
        return tableroPersonalizado;
    }

    public void setTableroPersonalizado(ArrayList<InformacionCasillaGUI> tableroPersonalizado) {
        this.tableroPersonalizado = tableroPersonalizado;
    }

    public Loop getLoop() {
        return loop;
    }

    public void setLoop(Loop loop) {
        this.loop = loop;
    }

    public MediaPlayer getReproductor() {
        return reproductor;
    }



    /* Métodos */

    /**
     * Se inicia la aplicación gráfica
     */
    public void iniciar() {

        setFase(new FaseBienvenida(this));
        getFase().iniciar();

        /*FaseEditor faseEditor = new FaseEditor(this);
        faseEditor.iniciar();*/

        setLoop(new Loop(getFase()));
        getLoop().start();
        getVentana().show();
    }

    /**
     * Se ejecuta una sección de la aplicación en función de la fase especificada
      * @param tipoFase fase de la aplicación a ejecutar
     */
    public void ejecutarFase(TipoFase tipoFase) {

        if( tipoFase == null ) {
            System.err.println("Tipo de fase no inicializada");
            return;
        }

        getLoop().stop();
        getFase().clear();

        switch(tipoFase) {

            case bienvenida:
                setFase(new FaseBienvenida(this));
                break;

            case creacionTablero:
                setFase(new FaseEditor(this));
                getReproductor().setVolume(0.4);
                getReproductor().play();
                break;

            case introduccionJugadores:
                setFase(new FaseJugador(this));
                getFase().iniciar();
                break;

            case inicioJuego:
                getReproductor().stop();
                setFase(new FaseJuego(this));
                break;

            case seleccionTablero:
                setFase(new FaseSeleccion(this));
                break;
        }

        if( getTableroPersonalizado() != null ) {
            System.out.println("No es null");
        }

        getFase().iniciar();
        getLoop().setFase(getFase());
        getLoop().start();
    }
}
