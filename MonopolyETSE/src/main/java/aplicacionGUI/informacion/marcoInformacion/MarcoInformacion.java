package aplicacionGUI.informacion.marcoInformacion;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ImagenAnimada;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import resources.marcoInformacion.animacion.AnimacionMarcoInformacion;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
            ConstantesGUI.MARCO_INFORMACION_ANIMACION_FRAMES[ConstantesGUI.MARCO_INFORMACION_ANIMACION_FRAMES.length
                    - 1]).toString());

    // Animación de abrir/cerrar
    private final static ImagenAnimada ANIMACION_ABRIR = new ImagenAnimada(new AnimacionMarcoInformacion(),
            ConstantesGUI.MARCO_INFORMACION_ANIMACION_FRAMES, 0.01);

    // Información a representar
    private ArrayList<String> informacion;

    // Si se encuentra activo
    private boolean activo;

    // Si ha finalizado la animación
    private boolean animacionFinalizada;

    // Si se va a abrir
    private boolean abrirse;

    // Si es el primer frame
    private boolean primerFrame;

    // Último frame renderizado
    private Image ultimoFrame;

    // Si es necesario llevar a cabo el fade del texto
    private boolean fadeTexto;

    // Tick correspondiente al texto
    private int tickTexto;

    // Sonido a reproducir cuando se abre/cierra el pergamino
    private final Media sonido = new Media(resources.sonidos.Sonidos.class.getResource(
            ConstantesGUI.SONIDO_PERGAMINO).toString());



    /* Constructor */

    /**
     * Se crea un marco de información sobre el cual representar texto
     *
     * @param raiz nodo sobre el cual crear un hijo para el marco de información
     */
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

        // Se inicializan los demás atributos a valores apropiados
        this.activo = false;
        this.animacionFinalizada = true;
        this.abrirse = false;
        this.primerFrame = false;
        this.ultimoFrame = null;
        this.fadeTexto = false;
        this.tickTexto = 0;
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

    public boolean isFadeTexto() {
        return fadeTexto;
    }

    public void setFadeTexto(boolean fadeTexto) {
        this.fadeTexto = fadeTexto;
    }

    public int getTickTexto() {
        return tickTexto;
    }

    public void setTickTexto(int tickTexto) {
        this.tickTexto = tickTexto;
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

        double posicionX = x - ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.MARCO_INFORMACION_DESPLAZAMIENTO_Y;

        return (getSensor().contains(posicionX, posicionY));
    }

    /**
     * Se ejecuta la acción definida ante un click izquierdo
     *
     * @param x coordenada X del click
     * @param y coordenada Y del click
     */
    public void handleClickIzquierdo(double x, double y) {

        if (!isActivo()) {
            abrir();
        }

        else {
           cerrar();
        }
    }

    /**
     * Se abre el marco de información
     */
    public void abrir() {

        // Se resetea el número de frame
        setPrimerFrame(true);
        setUltimoFrame(null);

        // Se indica la necesidad de llevar a cabo el fade del texto
        setFadeTexto(true);
        setTickTexto(0);

        // Se indica que ahora esté activo y que debe realizarse la animación en primer lugar
        setAnimacionFinalizada(false);
        setActivo(true);
        setAbrirse(true);

        MediaPlayer reproductor = new MediaPlayer(getSonido());
        reproductor.play();
    }

    /**
     * Se cierra el marco de información
     */
    public void cerrar() {

        // Se resetea el número de frame
        setPrimerFrame(true);
        setUltimoFrame(null);

        // Se indica la necesidad de llevar a cabo el fade del texto
        setFadeTexto(true);
        setTickTexto(0);

        // Debe continuar activo pero se indica que se cierre, y no se indicará la necesidad de realizar la animación
        // hasta que finalice el fade del texto
        setAbrirse(false);

        MediaPlayer reproductor = new MediaPlayer(getSonido());
        reproductor.play();
    }

    /**
     * Se cambia el contenido a representar en el marco de información
     *
     * @param informacion nueva información a representar
     */
    public void actualizarContenido(ArrayList<String> informacion) {

        // Se adapta la información al marco
        ArrayList<String> informacionAdaptada = ajustarInformacion(informacion);

        // Se guarda la información a representar
        setInformacion(informacionAdaptada);
    }

    /**
     * Se ajusta la información dada, creando líneas de modo que ninguna de ellas supere el ancho del marco de
     * información
     *
     * @param informacion información a ajustar
     * @return información tras el ajuste
     */
    private ArrayList<String> ajustarInformacion(ArrayList<String> informacion) {

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

    /**
     * Se renderiza el marco de información
     *
     * @param t tiempo transcurrido
     */
    public void render(double t) {

        if (isActivo()) {

            if (isAnimacionFinalizada()) {
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

    /**
     * Se renderiza el último frame de la animación del marco junto con el texto contenido, incluyendo el fade-in o
     * fade-out apropiados para el texto en caso de que sea necesario
     *
     * @param t tiempo transcurrido
     */
    private void renderAnimacionFinalizada(double t) {

        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL,
                ConstantesGUI.MARCO_INFORMACION_FUENTE_TAMANO));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setTextAlign(TextAlignment.CENTER);
        getGc().setLineWidth(1);

        // Si se ha finalizado el fade
        if (!isFadeTexto()) {

            // Se muestra un negro sólido
            getGc().setFill(Color.BLACK);

        }

        // En caso contrario
        else {

            // Si el marco se está abriendo, se realiza un fade-in
            if (isAbrirse()) {
                getGc().setFill(Color.rgb(0, 0, 0, 0.1 * getTickTexto()));
            } // Si se está cerrando, es un fade-out
            else {
                getGc().setFill(Color.rgb(0, 0, 0, 1 - 0.1 * getTickTexto()));
            }

            // Se incrementa el contador del tick del texto
            setTickTexto(getTickTexto() + 1);

            // Si se ha alcanzado el décimo tick, se finaliza el fade
            if (getTickTexto() == 10) {
                setFadeTexto(false);

                // Y, si se estaba realizando un fade-out, debe llevarse a cabo ahora la animación de cierre
                if (!isAbrirse()) {
                    setAnimacionFinalizada(false);
                }
            }
        }

        // Se muestra el pergamino
        getGc().drawImage(getIMAGEN_FINAL(), 0, 0);

        // Variables auxiliares para el texto
        int numeroLineas = getInformacion().size();

        // Se muestra el texto
        for (int i = 0, desplazamiento = ConstantesGUI.MARCO_INFORMACION_LINEA_ALTO
                * ((ConstantesGUI.MARCO_INFORMACION_NUMERO_LINEAS - numeroLineas) / 2); i < numeroLineas; i++,
                     desplazamiento += ConstantesGUI.MARCO_INFORMACION_LINEA_ALTO) {

            // Se añade texto
            getGc().fillText(getInformacion().get(i), getAncho() / 2, desplazamiento + 10);
        }
    }

    /**
     * Se renderiza uno de los frames del marco, pudiendo encontrarse este tanto abriéndose como cerrándose
     *
     * @param t tiempo transcurrido
     */
    private void renderAnimacionActiva(double t) {

        // Frame a renderizar
        Image frame;

        // Si se trata del primer frame, se guarda el tiempo de inicio
        if (isPrimerFrame()) {
            getANIMACION_ABRIR().setTiempoInicio(t);
            setPrimerFrame(false);
        }

        // Si se va a abrir
        if (isAbrirse()) {

            frame = getANIMACION_ABRIR().getFrame(t);

            // La animación debe finalizar al alcanzar el último frame; sin embargo, es probable que el último frame en
            // renderizarse no sea precisamente el último de la animación, sino que sea uno de los últimos al obtener
            // frames en función del tiempo transcurrido. Para ello, debe llevarse el registro del último frame
            // renderizado, y comprobar si el frame obtenido ha vuelto a comenzar el bucle de la animación
            // Si se vuelve a comenzar la animación, se indica el fin y se establece como activo
            if (getUltimoFrame() != null && getANIMACION_ABRIR().getFrames().indexOf(frame)
                    < getANIMACION_ABRIR().getFrames().indexOf(getUltimoFrame())) {
                setAnimacionFinalizada(true);
                frame = getANIMACION_ABRIR().getFrameInversoNumero(0);
            }
        }

        // En caso contrario, se está cerrando
        else {

            frame = getANIMACION_ABRIR().getFrameInverso(t);

            // Sucede algo similar en este caso, siendo posible que el último frame en renderizarse no sea el primero
            // de la animación
            // Si se comenzado de nuevo la animación, se indica el fin y se establece como no activo
            if (getUltimoFrame() != null && getANIMACION_ABRIR().getFrames().indexOf(frame)
                    > getANIMACION_ABRIR().getFrames().indexOf(getUltimoFrame())) {
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
