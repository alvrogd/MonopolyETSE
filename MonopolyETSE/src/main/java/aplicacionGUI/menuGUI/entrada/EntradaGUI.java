package aplicacionGUI.menuGUI.entrada;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.menuGUI.MenuGUI;
import aplicacionGUI.menuGUI.registroGUI.ConsolaInterfaz;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import resources.entrada.ImagenEntradaGUI;

public class EntradaGUI {

    /* Atributos */

    // Nodo propiedad de la sección del menú (parte inferior)
    private final Group nodo;
    private final Group nodoTextField;

    // Sensor asociado a esta sección
    private Rectangle sensor;

    // Contexto en el que representar objetos
    private final GraphicsContext gc;

    // Imagen de fondo para el menú
    private final Image fondo;

    // Canvas para la representación del menú
    private final Canvas canvas;

    // Pulsación del botón
    private final Image fondoOscuro;

    // Menú
    private final MenuGUI menuGUI;

    // Cuadrado de entrada de texto
    private TextField textField;

    // Booleano para saber si está activo
    private boolean activo;

    // Sensor para el botón de enter
    private Rectangle boton;

    // Botón para saber si se ha pulsado ya el enter
    private boolean pulsadoEnter;

    // String donde se almacenará la información
    private String buffer;

    public EntradaGUI(Group raiz, MenuGUI menuGUI){
        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if(menuGUI == null){
            System.err.println("TableroGUI no inicializado");
            System.exit(1);
        }

        this.menuGUI = menuGUI;

        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        this.nodo.getTransforms().add(new Translate(ConstantesGUI.INPUT_DESPLAZAMIENTO_JUEGO_X, ConstantesGUI.INPUT_DESPLAZAMIENTO_JUEGO_Y));

        this.sensor = new Rectangle(0, 0, ConstantesGUI.INFORMACION_ANCHO, ConstantesGUI.INPUT_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        this.canvas = new Canvas(ConstantesGUI.INPUT_ANCHO, ConstantesGUI.INPUT_ALTO);
        this.nodo.getChildren().add(canvas);

        this.gc = this.canvas.getGraphicsContext2D();

        this.fondo = new Image(ImagenEntradaGUI.class.getResource("plantillaEntradaDinero.png").toString());
        this.fondoOscuro = new Image(ImagenEntradaGUI.class.getResource("plantillaEntradaDineroOscuro.png").toString());

        this.nodoTextField = new Group();
        raiz.getChildren().add(this.nodoTextField);

        this.nodoTextField.getTransforms().add(new Translate(ConstantesGUI.INPUT_DESPLAZAMIENTO_JUEGO_X+ 10, ConstantesGUI.INPUT_DESPLAZAMIENTO_JUEGO_Y + 25));

        this.textField = new TextField();

        this.textField.setPrefHeight(25);
        this.textField.setPrefWidth(390);

        this.boton = new Rectangle(ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_X, ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_Y,
                25, 25);
        this.boton.setFill(Color.TRANSPARENT);

        this.activo = false;
        this.pulsadoEnter = false;
        this.buffer = new String();

    }

    public Group getNodo() {
        return nodo;
    }

    public boolean isPulsadoEnter() {
        return pulsadoEnter;
    }

    public void setPulsadoEnter(boolean pulsadoEnter) {
        this.pulsadoEnter = pulsadoEnter;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public void setSensor(Rectangle sensor) {
        this.sensor = sensor;
    }

    public String getBuffer() {
        return buffer;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
        setPulsadoEnter(false);
        getTextField().clear();
        getNodoTextField().getChildren().remove(getTextField());
    }

    public Group getNodoTextField() {
        return nodoTextField;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getFondo() {
        return fondo;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Image getFondoOscuro() {
        return fondoOscuro;
    }

    public MenuGUI getMenuGUI() {
        return menuGUI;
    }

    public TextField getTextField() {
        return textField;
    }

    public Rectangle getBoton() {
        return boton;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public boolean pulsandoBotonEnter(double x, double y){
        double posicionX = x;
        double posicionY = y;

        return(getBoton().contains(posicionX, posicionY));
    }

    public boolean contienePosicion(double x, double y){
        double posicionX = x - ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_X;
        double posicionY = y - ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_Y;

        return (getSensor().contains(posicionX, posicionY));
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_X;
        double posicionY = y - ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_Y;

        System.out.println("Estás en el handle de Entrada");

        if(pulsandoBotonEnter(posicionX, posicionY)){
            if(!getTextField().getText().isEmpty()) {
                setBuffer(getTextField().getText());
                getTextField().clear();
                setPulsadoEnter(true);
                getMenuGUI().setSiguientePaso(true);
                System.out.println("Has pulsado el botón");
            }
        }
    }


    // No se devolverá true hasta que se haya obtenido la información
    public boolean render(){

        if(isActivo()) {
            getGc().drawImage(getFondo(), 0, 0);
            if(!getNodoTextField().getChildren().contains(getTextField())){
                getNodoTextField().getChildren().add(getTextField());
            }
        } else {
            getGc().clearRect(0, 0, ConstantesGUI.INPUT_ANCHO, ConstantesGUI.INPUT_ALTO);
            getNodoTextField().getChildren().remove(getTextField());
        }
        return false;

    }

}
