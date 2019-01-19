package aplicacionGUI.informacion.marcoInformacion;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ImagenAnimada;
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
import resources.marcoInformacion.animacion.AnimacionMarcoInformacion;

public class MarcoInformacion {

    /* Atributos */
    // Nodo propiedad de la casilla
    private final Group nodo;

    // Canvas contenido en el nodo
    private final Canvas canvas;

    // Sensor asociado al tablero
    private final Rectangle sensor;

    // Contexto en el que representar objetos
    private final GraphicsContext gc;

    // Dimensiones de la representación
    private final int ancho = ConstantesGUI.MARCO_INFORMACION_ANCHO;
    private final int alto = ConstantesGUI.MARCO_INFORMACION_ALTO;

    // Imagen final
    private final static Image IMAGEN_FINAL = new Image(AnimacionMarcoInformacion.class.getResource(
            ConstantesGUI.MARCO_INFORMACION_ANIMACION_FRAMES[ConstantesGUI.MARCO_INFORMACION_ANIMACION_FRAMES.length -
                    1]).toString());

    // Información a representar
    private ArrayList<String> informacion;

    // Si se encuentra activo
    private boolean activo;
    
    // Si ha finalizado la animación
    private boolean animacionFinalizada;
    
    // Animación de abrir/cerrar
    private final static ImagenAnimada ANIMACION_ABRIR = new ImagenAnimada(new AnimacionMarcoInformacion(),
            ConstantesGUI.MARCO_INFORMACION_ANIMACION_FRAMES, 0.01);
    
    // Si se va a abrir
    private boolean abrirse;
    
    // Si es el primer frame
    private boolean primerFrame;
    
    // Último frame
    private Image ultimoFrame;

    /* Constructor */
    public MarcoInformacion(Group raiz) {

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para el marco
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);
        
        // Se mueve el marco a su posición adecuada
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_X,
                ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_Y));

        // Se crea un sensor del tamaño apropiado
        this.sensor = new Rectangle(0, 0, ConstantesGUI.MARCO_INFORMACION_ANCHO, ConstantesGUI.MARCO_INFORMACION_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se crea un canvas en el nodo para representar el marco
        this.canvas = new Canvas(ConstantesGUI.MARCO_INFORMACION_ANCHO, ConstantesGUI.MARCO_INFORMACION_ALTO);
        this.nodo.getChildren().add(this.canvas);

        // Se genera un contexto a partir del canvas para insertar la representación del tablero
        this.gc = this.canvas.getGraphicsContext2D();

        this.activo = false;
        this.animacionFinalizada = true;
        this.abrirse = false;
        this.primerFrame = false;
        this.ultimoFrame = null;
    }

    /* Getters y setters */
    public Group getNodo() {
        return nodo;
    }

    public Rectangle getSensor() {
        return sensor;
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

    public static Image getIMAGEN_FINAL() {
        return IMAGEN_FINAL;
    }

    public ArrayList<String> getInformacion() {
        return informacion;
    }

    public void setInformacion(ArrayList<String> informacion) {
        this.informacion = informacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isAnimacionFinalizada() {
        return animacionFinalizada;
    }

    public void setAnimacionFinalizada(boolean animacionFinalizada) {
        this.animacionFinalizada = animacionFinalizada;
    }

    public static ImagenAnimada getANIMACION_ABRIR() {
        return ANIMACION_ABRIR;
    }

    public boolean isAbrirse() {
        return abrirse;
    }

    public void setAbrirse(boolean abrirse) {
        this.abrirse = abrirse;
    }

    public boolean isPrimerFrame() {
        return primerFrame;
    }

    public void setPrimerFrame(boolean primerFrame) {
        this.primerFrame = primerFrame;
    }

    public Image getUltimoFrame() {
        return ultimoFrame;
    }

    public void setUltimoFrame(Image ultimoFrame) {
        this.ultimoFrame = ultimoFrame;
    }
    
       

    /* Métodos */
    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_Y;

        return (getSensor().contains(posicionX, posicionY));
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_Y;

        // Se indica la necesidad de abrirse/cerrarse
        setAnimacionFinalizada(false);
        
        // Se resetea el número de frame
        setPrimerFrame(true);
        setUltimoFrame(null);
        
        // Si se va a abrir, se indica que ahora esté activo
        if(!isActivo()) {
            setActivo(true);
            setAbrirse(true);
        }
        
        // Si se va a cerrar, debe continuar activo pero se indica que se cierre
        else {
            setAbrirse(false);
        }
    }

    public void actualizarContenido(String[] informacion) {

        // Se adapta la información al marco
        ArrayList<String> informacionAdaptada = ajustarInformacion(informacion);        

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

    public void render(double t) {

        if (isActivo()) {
            
            if( isAnimacionFinalizada() ) {
                renderAnimacionFinalizada(t);
            }
            
            // En caso contrario, se renderiza un fotograma
            else {
                renderAnimacionActiva(t);
            }          
        }
        
        // Si no está activo, simplemente se limpia el gc
        else {
            getGc().clearRect(0, 0, ConstantesGUI.MARCO_INFORMACION_ANCHO, ConstantesGUI.MARCO_INFORMACION_ALTO);
        }
    }
    
    private void renderAnimacionFinalizada(double t ) {
        
        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL,
                ConstantesGUI.MARCO_INFORMACION_FUENTE_TAMANO));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.CENTER);
        getGc().setLineWidth(1);

        // Se muestra el pergamino
        getGc().drawImage(getIMAGEN_FINAL(), 0, 0);

        // Variables auxiliares para el texto
        int numeroLineas = getInformacion().size();

        // Se muestra el texto
        for (int i = 0, desplazamiento = ConstantesGUI.MARCO_INFORMACION_LINEA_ALTO *
                ((ConstantesGUI.MARCO_INFORMACION_NUMERO_LINEAS - numeroLineas) / 2); i < numeroLineas; i++,
                desplazamiento += ConstantesGUI.MARCO_INFORMACION_LINEA_ALTO) {

            // Se añade texto
            getGc().fillText(getInformacion().get(i), getAncho() / 2, desplazamiento + 10);
        } 
    }
    
    private void renderAnimacionActiva(double t) {
        
        // Frame a renderizar
        Image frame;
                
        // Si se trata del primer frame, se guarda el tiempo de inicio
        if(isPrimerFrame()) {
            getANIMACION_ABRIR().setTiempoInicio(t);
            setPrimerFrame(false);
        }

        // Si se va a abrir
        if( isAbrirse()) {

            frame = getANIMACION_ABRIR().getFrame(t);

            // La animación debe finalizar al alcanzar el último frame; sin embargo, es probable que el último frame en
            // renderizarse no sea precisamente el último de la animación, sino que sea uno de los últimos al obtener
            // frames en función del tiempo transcurrido. Para ello, debe llevarse el registro del último frame
            // renderizado, y comprobar si el frame obtenido ha vuelto a comenzar el bucle de la animación
            
            // Si se vuelve a comenzar la animación, se indica el fin y se establece como activo
            if( getUltimoFrame() != null && getANIMACION_ABRIR().getFrames().indexOf(frame) <
                    getANIMACION_ABRIR().getFrames().indexOf(getUltimoFrame())) {
                setAnimacionFinalizada(true);
                return;
            }
        }

        // En caso contrario, se está cerrando
        else {

            frame = getANIMACION_ABRIR().getFrameInverso(t);

            // Sucede algo similar en este caso, siendo posible que el último frame en renderizarse no sea el primero
            // de la animación
            
            // Si se comenzado de nuevo la animación, se indica el fin y se establece como no activo
            if( getUltimoFrame() != null && getANIMACION_ABRIR().getFrames().indexOf(frame) >
                    getANIMACION_ABRIR().getFrames().indexOf(getUltimoFrame())) {
                setAnimacionFinalizada(true);
                setActivo(false);
                return;
            }
        }

        // Se limpia el gc del anterior frame
        getGc().clearRect(0, 0, ConstantesGUI.MARCO_INFORMACION_ANCHO, ConstantesGUI.MARCO_INFORMACION_ALTO);

        // Se renderiza el frame
        getGc().drawImage(frame, 0, 0);

        // Se actualiza el último frame renderizado
        setUltimoFrame(frame);
    }
}
