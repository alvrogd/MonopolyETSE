package aplicacionGUI.input;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import resources.entrada.ImagenEntradaGUI;
import resources.sonidos.Sonidos;

import java.util.ArrayList;

public abstract class Input {

    /* Atributos */

    // Raíz asociada a los inputs
    private static Group raiz;

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

    // Identificador del atributo que el usuario del input desea modificar
    private final int atributo;

    // Si ha finalizado la animación
    private boolean animacionFinalizada;

    // Si aparece o desaparece
    private boolean aparece;

    // Tick de la animación
    private int tickAnimacion;

    // Escalado establecido
    private Scale escalado;

    // Sonido a reproducir cuando se pulsa el botón
    private final Media sonido = new Media(Sonidos.class.getResource(ConstantesGUI.SONIDO_BOTON).toString());



    /* Constructor */

    /**
     * Se crea una instancia mediante la cual obtener información del usuario a través del teclado
     *
     * @param imagen       imagen que mostrar cuando no se esté presionando el botón de aceptar
     * @param imagenOscura imagen que mostrar cuando se esté presionando el botón de aceptar
     * @param editor       si el input se encuentra en un editor o en el juego, para establecer una correcta posición
     *                     en pantalla
     * @param atributo     identificador del atributo que desea modificar aquel que invocó al input (cuando se lea la
     *                     entrada del usuario, el método llamado para guardar la información obtenida recibirá como
     *                     argumento el identificador, pudiendo discernir qué atributo debe ser modificado)
     */
    public Input(String imagen, String imagenOscura, boolean editor, int atributo) {

        if (Input.raiz == null) {
            System.err.println("Raíz aún no inicializada");
            System.exit(1);
        }

        if (imagen == null) {
            System.err.println("Nombre de la imagen no inicializado");
            System.exit(1);
        }

        if (imagenOscura == null) {
            System.err.println("Nombre de la imagen oscura no inicializado");
            System.exit(1);
        }

        if (Input.inputsActivos == null) {
            System.err.println("El conjunto de inputs activos aún no ha sido inicializado");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para el input
        this.nodo = new Group();
        Input.raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana
        if (editor) {
            this.desplazamientoX = ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_X;
            this.desplazamientoY = ConstantesGUI.INPUT_DESPLAZAMIENTO_EDITOR_Y;
        } else {
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
        this.textField.setPrefWidth(ConstantesGUI.INPUT_RECUADRO_ANCHO);
        this.textField.setPrefHeight(ConstantesGUI.INPUT_RECUADRO_ALTO);

        // Se mueve a su correspondiente posición
        this.textField.getTransforms().add(new Translate(ConstantesGUI.INPUT_DESPLAZAMIENTO_RECUADRO_X,
                ConstantesGUI.INPUT_DESPLAZAMIENTO_RECUADRO_Y));

        // Y se añade al nodo del input
        this.nodo.getChildren().add(textField);

        // Se guarda el identificador del atributo asociado a la entrada
        this.atributo = atributo;

        // Se eliminan los inputs activos y se guarda este
        Input.inputsActivos.clear();
        Input.inputsActivos.add(this);

        // Inicialmente, la animación ha comenzado y se va a mostrar
        this.animacionFinalizada = false;
        this.aparece = true;
        this.tickAnimacion = 0;
    }



    /* Getters y setters */

    public static Group getRaiz() {
        return raiz;
    }

    public static void setRaiz(Group raiz) {
        System.out.println("Cambiando raiz");
        Input.raiz = raiz;
    }

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

    public int getAtributo() {
        return atributo;
    }

    public static ArrayList<Input> getInputsActivos() {
        return inputsActivos;
    }

    public static void setInputsActivos(ArrayList<Input> inputsActivos) {
        Input.inputsActivos = inputsActivos;
    }

    public boolean isAnimacionFinalizada() {
        return animacionFinalizada;
    }

    public void setAnimacionFinalizada(boolean animacionFinalizada) {
        this.animacionFinalizada = animacionFinalizada;
    }

    public boolean isAparece() {
        return aparece;
    }

    public void setAparece(boolean aparece) {
        this.aparece = aparece;
    }

    public int getTickAnimacion() {
        return tickAnimacion;
    }

    public void setTickAnimacion(int tickAnimacion) {
        this.tickAnimacion = tickAnimacion;
    }

    public Scale getEscalado() {
        return escalado;
    }

    public void setEscalado(Scale escalado) {
        this.escalado = escalado;
    }

    public Media getSonido() {
        return sonido;
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

        MediaPlayer reproductor = new MediaPlayer(getSonido());
        reproductor.play();
    }

    /**
     * Se ejecuta la acción definida ante un release de un click
     */
    public void handleRelease() {

        // Se cambia la imagen a aquella no oscurecida
        setImagenSeleccionada(getImagen());
    }

    /**
     * Se renderiza el input
     */
    public void render() {

        // Si la animación no ha finalizado, debe cambiarse el escalado
        if( !isAnimacionFinalizada() ) {
            actualizarEscalado();
        }

        // Se muestra la imagen
        getGc().drawImage(getImagenSeleccionada(), 0, 0);
    }

    /**
     * Se actualiza la transformación de escalado del marco de información, en función de si debe aparecer o
     * desaparecer
     */
    private void actualizarEscalado() {

        // Se elimina el anterior escalado
        getNodo().getTransforms().remove(getEscalado());

        // Se actualiza el tick
        setTickAnimacion(getTickAnimacion() + 1);

        // Se crea uno nuevo en función de si va a aparecer o desaparecer
        if( isAparece() ) {
            setEscalado(new Scale(0.1 * getTickAnimacion(), 0.1 * getTickAnimacion(),
                    (double)ConstantesGUI.INPUT_ANCHO / 2, (double)ConstantesGUI.INPUT_ALTO / 2));
        }

        else {
            setEscalado(new Scale(1 - 0.1* getTickAnimacion(), 1 - 0.1 * getTickAnimacion(),
                    (double)ConstantesGUI.INPUT_ANCHO / 2, (double)ConstantesGUI.INPUT_ALTO / 2));
        }

        // Se aplica
        getNodo().getTransforms().add(getEscalado());

        // Si se alcanza el décimo tick, la animación finaliza
        if( getTickAnimacion() == 10 ) {

            setAnimacionFinalizada(true);

            // Y, si está desapareciendo, el input debe eliminar su rastro
            if(!isAparece()) {
                eliminarRastro();
            }
        }
    }

    /**
     * El input realiza las acciones pertinentes a haber finalizado su trabajo, desapareciendo y eliminándose de la
     * lista de inputs activos
     */
    public void finalizar() {

        // Se indica que la animación no ha finalizado, y que debe cerrarse
        setTickAnimacion(0);
        setAnimacionFinalizada(false);
        setAparece(false);
    }

    /**
     * El input realiza una limpieza
     */
    public void eliminarRastro() {

        // Elimina su nodo
        Input.getRaiz().getChildren().remove(getNodo());

        // Se elimina de la lista de inputs activos
        Input.getInputsActivos().clear();
    }
}
