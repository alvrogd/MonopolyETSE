
package aplicacionGUI.informacion.cartaGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import resources.cartas.ImagenesCartas;

public abstract class CartaGUI {

    /* Atributos */

    // Nodo propiedad del tablero
    private final Group nodo;

    // Canvas contenido en el nodo
    private final Canvas canvas;

    // Contexto en el que representar objetos
    private final GraphicsContext gc;

    // Imagen de la carta
    private final Image imagen;

    // Reverso de una carta
    private final Image reverso;

    // Imagen seleccionada
    private Image imagenSeleccionada;

    // Sensor de la casilla
    private Rectangle sensor;

    // Desplazamientos correspondientes
    private final int desplazamientoX;
    private final int desplazamientoY;



    /* Constructor */

    /**
     * Se crea una representación de una carta
     *
     * @param raiz            nodo sobre el cual crear un hijo para la carta
     * @param imagen          imagen que representa la carta
     * @param desplazamientoX posición (coordenada X) de la carta en la sección de información
     * @param desplazamientoY posición (coordenada Y) de la carta en la sección de información
     */
    public CartaGUI(Group raiz, String imagen, int desplazamientoX, int desplazamientoY) {

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if (imagen == null) {
            System.err.println("Nombre de la imagen no inicializado");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para la carta
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se mueve la carta a su posición adecuada
        this.desplazamientoX = desplazamientoX;
        this.desplazamientoY = desplazamientoY;
        this.nodo.getTransforms().add(new Translate(desplazamientoX, desplazamientoY));

        // Se crea un canvas en el nuevo nodo para representar la carta
        this.canvas = new Canvas(ConstantesGUI.CARTA_ANCHO, ConstantesGUI.CARTA_ALTO);
        this.nodo.getChildren().add(canvas);

        // Se genera un contexto a partir del canvas para insertar la representación del tablero
        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.CARTA_ANCHO, ConstantesGUI.CARTA_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se obtiene la imagen correspondiente
        this.imagen = new Image(ImagenesCartas.class.getResource(imagen).toString());

        // Se obtiene el reverso de las cartas
        this.reverso = new Image(ImagenesCartas.class.getResource(ConstantesGUI.CARTA_REVERSO).toString());

        // Se establece por defecto la imagen correspondiente
        this.imagenSeleccionada = this.imagen;
    }



    /* Getters y setters */

    public Group getNodo() {
        return nodo;
    }


    public Canvas getCanvas() {
        return canvas;
    }


    public GraphicsContext getGc() {
        return gc;
    }


    public Image getImagen() {
        return imagen;
    }

    public Rectangle getSensor() {
        return sensor;
    }


    public void setSensor(Rectangle sensor) {
        this.sensor = sensor;
    }


    public Image getReverso() {
        return reverso;
    }


    public Image getImagenSeleccionada() {
        return imagenSeleccionada;
    }


    public void setImagenSeleccionada(Image imagenSeleccionada) {
        this.imagenSeleccionada = imagenSeleccionada;
    }


    public int getDesplazamientoX() {
        return desplazamientoX;
    }


    public int getDesplazamientoY() {
        return desplazamientoY;
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
     * Se ejecuta la acción definida ante un click
     *
     * @param x coordenada X del click
     * @param y coordenada Y del click
     */
    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        switchImagen();
    }

    /**
     * Se renderiza la carta
     */
    public void render() {

        // Se muestra la imagen
        getGc().drawImage(getImagenSeleccionada(), 0, 0);
    }

    /**
     * Se cambia la imagen a representar entre la dada en el contructor y la que representa el reverso
     */
    private void switchImagen() {

        if (getImagenSeleccionada() == getImagen()) {
            setImagenSeleccionada(getReverso());
        } else {
            setImagenSeleccionada(getImagen());
        }
    }
}
