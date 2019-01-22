package aplicacionGUI.input;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import resources.entrada.ImagenEntradaGUI;

import java.util.ArrayList;

public abstract class Input {

    /* Atributos */

    // Nodo propiedad del input
    private final Group nodo;

    // Desplazamientos dados para el input
    private final int desplazamientoX;
    private final int desplazamientoY;

    // Canvas contenido en el nodo
    private final Canvas canvas;

    // Contexto en el que representar el unput
    private final GraphicsContext gc;

    // Sensor asociado al input
    private final Rectangle sensor;

    // Representaciones del input
    private Image imagenSeleccionada;
    private final Image imagen;
    private final Image imagenOscura;

    // Cuadro para la entrada de texto
    private final TextField textField;

    // Referencia al conjunto de inputs activos en la aplicación
    private static ArrayList<Input> inputsActivos;


    /* Constructor */

    public Input(Group raiz, String imagen, String imagenOscura, boolean editor) {

        if( raiz == null ) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if( imagen == null ) {
            System.err.println("Nombre de la imagen no inicializado");
            System.exit(1);
        }

        if( imagenOscura == null ) {
            System.err.println("Nombre de la imagen oscura no inicializado");
            System.exit(1);
        }

        if( Input.inputsActivos == null ) {
            System.err.println("El conjunto de inputs activos aún no ha sido inicializado");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para el input
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana
        if( editor ) {
            this.desplazamientoX = ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_X;
            this.desplazamientoY = ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_Y;
        }

        else {
            this.desplazamientoX = ConstantesGUI.INPUT_DESPLAZAMIENTO_JUEGO_X;
            this.desplazamientoY = ConstantesGUI.INPUT_DESPLAZAMIENTO_JUEGO_Y;
        }

        this.nodo.getTransforms().add(new Translate(this.desplazamientoX, this.desplazamientoY));

        // Se crea un canvas en el nuevo nodo para representar la cuadrícula
        this.canvas = new Canvas(ConstantesGUI.INPUT_ANCHO, ConstantesGUI.INPUT_ALTO);
        this.nodo.getChildren().add(canvas);

        // Se genera un contexto a partir del canvas para insertar la cuadrícula
        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el sensor correspondiente para el botón
        this.sensor = new Rectangle(ConstantesGUI.INPUT_DESPLAZAMIENTO_BOTON_X,
                ConstantesGUI.INPUT_DESPLAZAMIENTO_BOTON_Y, ConstantesGUI.INPUT_BOTON_ANCHO,
                ConstantesGUI.INPUT_BOTON_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se crean las imágenes que representan el input
        this.imagen = new Image(ImagenEntradaGUI.class.getResource(imagen).toString());
        this.imagenOscura = new Image(ImagenEntradaGUI.class.getResource(imagenOscura).toString());

        // Inicialmente se muestra la imagen no oscurecida
        this.imagenSeleccionada = this.imagen;

        // Se crea el recuadro para que escriba el usuario
        this.textField = new TextField();
        this.textField.setPrefWidth(390);
        this.textField.setPrefHeight(25);

        // Y se añade al nodo del input
        this.nodo.getChildren().add(textField);
    }


    /* Getters y setters */

    public Group getNodo() {
        return nodo;
    }

    public int getDesplazamientoX() {
        return desplazamientoX;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public Image getImagenSeleccionada() {
        return imagenSeleccionada;
    }

    public void setImagenSeleccionada(Image imagenSeleccionada) {
        this.imagenSeleccionada = imagenSeleccionada;
    }

    public Image getImagen() {
        return imagen;
    }

    public Image getImagenOscura() {
        return imagenOscura;
    }

    public TextField getTextField() {
        return textField;
    }

    public static ArrayList<Input> getInputsActivos() {
        return inputsActivos;
    }

    public static void setInputsActivos(ArrayList<Input> inputsActivos) {
        Input.inputsActivos = inputsActivos;
    }



    /* Métodos */

    /**
     * Se comprueba si contiene una posición 2D dada
     *
     * @param x coordenada X
     * @param y coordenada Y
     * @return si contiene la posición dada
     */
    public boolean contienePosicion(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        return (getSensor().contains(posicionX, posicionY));
    }

    /**
     * Se ejecuta la acción definida ante una pulsación
     */
    public void handlePulsacion() {

        // Se cambia la imagen a aquella oscurecida
        setImagenSeleccionada(getImagenOscura());
    }

    /**
     * Se ejecuta la acción definida ante un release de un click
     */
    public void handleRelease() {

        // Se cambia la imagen a aquella no oscurecida
        setImagenSeleccionada(getImagen());
    }

    /**
     * Se renderiza el marco de información
     */
    public void render(){

        getGc().drawImage(getImagenSeleccionada(), 0,0);
    }
}
