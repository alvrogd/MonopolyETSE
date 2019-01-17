package aplicacionGUI.informacion.marcoInformacion;

import aplicacionGUI.ConstantesGUI;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import resources.marcoInformacion.ImagenesMarcoInformacion;

public class MarcoInformacion {

    /* Atributos */
    // Nodo propiedad de la casilla
    private final Group nodo;

    // Canvas contenido en el nodo
    private Canvas canvas;

    // Sensor asociado al tablero
    private Rectangle sensor;

    // Contexto en el que representar objetos
    private GraphicsContext gc;

    // Dimensiones de la representación
    private final int ancho = ConstantesGUI.MARCO_INFORMACION_ANCHO;
    private int alto;

    // Desplazamiento en la coordenada Y
    private int desplazamientoY;

    // Imágenes correspondientes al marco
    private final static Image SECCION_SUPERIOR = new Image(ImagenesMarcoInformacion.class.getResource(
            ConstantesGUI.MARCO_INFORMACION_IMAGEN_SUPERIOR).toString());

    private final static Image SECCION_CENTRAL = new Image(ImagenesMarcoInformacion.class.getResource(
            ConstantesGUI.MARCO_INFORMACION_IMAGEN_CENTRAL).toString());

    private final static Image SECCION_INFERIOR = new Image(ImagenesMarcoInformacion.class.getResource(
            ConstantesGUI.MARCO_INFORMACION_IMAGEN_INFERIOR).toString());

    // Información a representar
    private ArrayList<String> informacion;

    // Nümero de secciones centrales necesarias
    private int numeroSeccionesCentrales;

    // Boolean si se encuentra activo
    private boolean activo;

    /* Constructor */
    public MarcoInformacion(Group raiz) {

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para el marco
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        this.activo = false;
    }

    /* Getters y setters */
    public Group getNodo() {
        return nodo;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public void setSensor(Rectangle sensor) {
        this.sensor = sensor;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    public void setDesplazamientoY(int desplazamientoY) {
        this.desplazamientoY = desplazamientoY;
    }

    public static Image getSECCION_SUPERIOR() {
        return SECCION_SUPERIOR;
    }

    public static Image getSECCION_CENTRAL() {
        return SECCION_CENTRAL;
    }

    public static Image getSECCION_INFERIOR() {
        return SECCION_INFERIOR;
    }

    public ArrayList<String> getInformacion() {
        return informacion;
    }

    public void setInformacion(ArrayList<String> informacion) {
        this.informacion = informacion;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getNumeroSeccionesCentrales() {
        return numeroSeccionesCentrales;
    }

    public void setNumeroSeccionesCentrales(int numeroSeccionesCentrales) {
        this.numeroSeccionesCentrales = numeroSeccionesCentrales;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /* Métodos */
    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - getDesplazamientoY();

        return (getSensor().contains(posicionX, posicionY));
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - getDesplazamientoY();

        setActivo(!isActivo());
    }

    public void actualizarContenido(String[] informacion) {

        // Se adapta la información al marco
        ArrayList<String> informacionAdaptada = ajustarInformacion(informacion);

        // Se obtiene la cantidad de secciones centrales necesarias para representar la información
        setNumeroSeccionesCentrales(calcularSeccionesCentrales(informacionAdaptada));

        // Se establece el tamaño vertical total
        setAlto(ConstantesGUI.MARCO_INFORMACION_SUPERIOR_ALTO + getNumeroSeccionesCentrales()
                * ConstantesGUI.MARCO_INFORMACION_CENTRAL_ALTO + ConstantesGUI.MARCO_INFORMACION_INFERIOR_ALTO);

        // Se mueve el marco a su posición adecuada
        setDesplazamientoY((int) ((ConstantesGUI.TABLERO_ALTO - getAlto()) / (double) 2) - 18);

        getNodo().getTransforms().clear();
        getNodo().getTransforms().add(new Translate(ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_X,
                getDesplazamientoY()));

        // Se crea un sensor del tamaño apropiado
        setSensor(new Rectangle(0, 0, ConstantesGUI.MARCO_INFORMACION_ANCHO, getAlto()));
        getSensor().setFill(Color.TRANSPARENT);

        // Se crea un canvas en el nodo para representar el marco
        getNodo().getChildren().remove(getCanvas());
        setCanvas(new Canvas(ConstantesGUI.MARCO_INFORMACION_ANCHO, getAlto()));
        getNodo().getChildren().add(canvas);

        // Se genera un contexto a partir del canvas para insertar la representación del tablero
        setGc(getCanvas().getGraphicsContext2D());

        // Se guarda la información a representar
        setInformacion(informacionAdaptada);
    }

    private ArrayList<String> ajustarInformacion(String[] informacion) {

        ArrayList<String> resultado = new ArrayList<>();

        // Se junta toda la información
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : informacion) {
            stringBuilder.append(string).append(" ");
        }

        // Se separa en palabras
        String[] palabras = stringBuilder.toString().split(" ");

        // Se itera sobre las palabras
        stringBuilder = new StringBuilder();

        for (String palabra : palabras) {

            // Si la palabra no cabe en la actual línea, se guarda esta y se crea una nueva
            if ((stringBuilder.length() + palabra.length()) * ConstantesGUI.MARCO_INFORMACION_FUENTE_TAMANO
                    >= ConstantesGUI.MARCO_INFORMACION_ANCHO - 20) {

                resultado.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            }

            // Se añade la palabra junto con un espacio
            stringBuilder.append(palabra).append(" ");
        }

        // Se guarda la última línea
        resultado.add(stringBuilder.toString());

        return (resultado);
    }

    private int calcularSeccionesCentrales(ArrayList<String> informacion) {

        return ((int) Math.ceil(informacion.size() * ConstantesGUI.MARCO_INFORMACION_FUENTE_TAMANO
                / (double) ConstantesGUI.MARCO_INFORMACION_CENTRAL_ALTO));

    }

    public void render() {

        if (isActivo()) {
            int desplazamiento = 0;

            // Se establece la tipografía
            getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL,
                    ConstantesGUI.MARCO_INFORMACION_FUENTE_TAMANO));
            getGc().setStroke(Color.TRANSPARENT);
            getGc().setFill(Color.BLACK);
            getGc().setTextAlign(TextAlignment.CENTER);
            getGc().setLineWidth(1);

            // Se muestra la sección superior
            getGc().drawImage(getSECCION_SUPERIOR(), 0, 0);
            desplazamiento += ConstantesGUI.MARCO_INFORMACION_SUPERIOR_ALTO;

            // Variables auxiliares para el texto
            int desplazamientoTexto = desplazamiento;
            int j = 0;

            // Se muestran las secciones centrales junto con el texto
            for (int i = 0; i < getNumeroSeccionesCentrales(); i++) {

                getGc().drawImage(getSECCION_CENTRAL(), - 2, desplazamiento);
                desplazamiento += ConstantesGUI.MARCO_INFORMACION_CENTRAL_ALTO;

                // Mientras no se supere el desplazamiento de la sección central actual
                do {
                    // Se añade texto
                    getGc().fillText(getInformacion().get(j++), getAncho() / 2, desplazamientoTexto + 10);
                    // Y se trata por separado su desplazamiento
                    desplazamientoTexto += ConstantesGUI.MARCO_INFORMACION_FUENTE_TAMANO + 5;

                } while (desplazamientoTexto < desplazamiento && j < getInformacion().size());
            }

            // Se muestra la sección inferior
            getGc().drawImage(getSECCION_INFERIOR(), 0, desplazamiento);
        }
        
        else {
            getGc().clearRect(0, 0, ConstantesGUI.MARCO_INFORMACION_ANCHO, getAlto());
        }
    }
}
