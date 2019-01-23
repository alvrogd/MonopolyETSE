
package aplicacionGUI.informacion.cartaGUI;

import aplicacion.salidaPantalla.Output;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ImagenAnimada;
import aplicacionGUI.informacion.Informacion;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.Juego;
import monopoly.jugadores.excepciones.*;
import monopoly.tablero.cartas.Carta;
import resources.cartas.ImagenesCartas;

public abstract class CartaGUI {

    /* Atributos */

    // Nodo propiedad del tablero
    private final Group nodo;

    // Canvas contenido en el nodo
    private final Canvas canvas;

    // Contexto en el que representar objetos
    private final GraphicsContext gc;

    // Imágenes de la baraja de la carta
    private final ImagenAnimada baraja;

    // Animación de volteado de la carta
    private final ImagenAnimada volteado;

    // Imagen seleccionada
    private Image imagenSeleccionada;

    // Sensor de la casilla
    private Rectangle sensor;

    // Desplazamiento correspondiente
    private final int desplazamientoX;
    private final int desplazamientoY;

    // Desplazamiento de una carta individual
    private final int cartaDesplazamientoX;
    private final int cartaDesplazamientoY;

    // Si ha finalizado la animación
    private boolean animacionFinalizada;

    // Si se ha barajado
    private boolean haBarajado;

    // Si se está barajando
    private boolean barajando;

    // En el caso de voltearse, si se está mostrando o escondiendo
    private boolean mostrandose;

    // Si es el primer frame a renderizar de una animación
    private boolean primerFrame;

    // Último frame renderizado
    private Image ultimoFrame;

    // Sonido a reproducir cuando se revela una carta
    private final Media sonidoRevelar = new Media(resources.sonidos.Sonidos.class.getResource(
            ConstantesGUI.SONIDO_CARTA_REVELAR).toString());

    // Sonido a reproducir cuando se baraja
    private final Media sonidoBarajar = new Media(resources.sonidos.Sonidos.class.getResource(
            ConstantesGUI.SONIDO_CARTA_BARAJAR).toString());

    private final Informacion informacion;


    /* Constructor */

    /**
     * Se crea una representación de una carta
     *
     * @param raiz                 nodo sobre el cual crear un hijo para la carta
     * @param desplazamientoX      posición (coordenada X) de la carta en la sección de información
     * @param desplazamientoY      posición (coordenada Y) de la carta en la sección de información
     * @param cartaDesplazamientoX posición (coordenada X) de una carta dentro de su baraja
     * @param cartaDesplazamientoY posición (coordenada Y) de una carta dentro de su baraja
     */
    public CartaGUI(Group raiz, int desplazamientoX, int desplazamientoY, String[] imagenesBaraja,
                    String[] imagenesVolteada, int cartaDesplazamientoX, int cartaDesplazamientoY, Informacion informacion) {

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if (imagenesBaraja == null) {
            System.err.println("Nombres de las imágenes de la baraja no inicializados");
            System.exit(1);
        }

        if (imagenesVolteada == null) {
            System.err.println("Nombres de las imágenes de la carta voletada no inicializados");
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
        this.canvas = new Canvas(ConstantesGUI.BARAJA_ANCHO, ConstantesGUI.BARAJA_ALTO);
        this.nodo.getChildren().add(canvas);

        // Se genera un contexto a partir del canvas para insertar la representación del tablero
        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el sensor correspondiente
        this.cartaDesplazamientoX = cartaDesplazamientoX;
        this.cartaDesplazamientoY = cartaDesplazamientoY;
        /*this.sensor = new Rectangle(this.cartaDesplazamientoX, this.cartaDesplazamientoY, ConstantesGUI.CARTA_ANCHO,
                ConstantesGUI.CARTA_ALTO);*/
        this.sensor = new Rectangle(0, 0, ConstantesGUI.BARAJA_ANCHO, ConstantesGUI.BARAJA_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se crean las animaciones
        this.baraja = new ImagenAnimada(new ImagenesCartas(), imagenesBaraja, 0.005);
        this.volteado = new ImagenAnimada(new ImagenesCartas(), imagenesVolteada, 0.025);

        // Se establece por defecto la primera imagen de la baraja
        this.imagenSeleccionada = this.baraja.getFrameNumero(0);

        // Inicialmente, no hay animación, y se encuentra en la fase de baraja
        this.animacionFinalizada = true;
        this.haBarajado = false;
        this.barajando = true;

        this.informacion = informacion;
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

    public Informacion getInformacion() {
        return informacion;
    }

    public ImagenAnimada getBaraja() {
        return baraja;
    }

    public ImagenAnimada getVolteado() {
        return volteado;
    }

    public Image getImagenSeleccionada() {
        return imagenSeleccionada;
    }

    public void setImagenSeleccionada(Image imagenSeleccionada) {
        this.imagenSeleccionada = imagenSeleccionada;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public void setSensor(Rectangle sensor) {
        this.sensor = sensor;
    }

    public int getDesplazamientoX() {
        return desplazamientoX;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    public int getCartaDesplazamientoX() {
        return cartaDesplazamientoX;
    }

    public int getCartaDesplazamientoY() {
        return cartaDesplazamientoY;
    }

    public boolean isAnimacionFinalizada() {
        return animacionFinalizada;
    }

    public void setAnimacionFinalizada(boolean animacionFinalizada) {
        this.animacionFinalizada = animacionFinalizada;
    }

    public boolean isHaBarajado() {
        return haBarajado;
    }

    public void setHaBarajado(boolean haBarajado) {
        this.haBarajado = haBarajado;
    }

    public boolean isBarajando() {
        return barajando;
    }

    public void setBarajando(boolean barajando) {
        this.barajando = barajando;
    }

    public boolean isMostrandose() {
        return mostrandose;
    }

    public void setMostrandose(boolean mostrandose) {
        this.mostrandose = mostrandose;
    }

    public Image getUltimoFrame() {
        return ultimoFrame;
    }

    public void setUltimoFrame(Image ultimoFrame) {
        this.ultimoFrame = ultimoFrame;
    }

    public boolean isPrimerFrame() {
        return primerFrame;
    }

    public void setPrimerFrame(boolean primerFrame) {
        this.primerFrame = primerFrame;
    }

    public Media getSonidoRevelar() {
        return sonidoRevelar;
    }

    public Media getSonidoBarajar() {
        return sonidoBarajar;
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
     * Se ejecuta la acción definida ante un click izquierdo
     *
     * @param x coordenada X del click
     * @param y coordenada Y del click
     */
    public void handleClickIzquierdo(double x, double y) {

        // Si se está en la fase de barajar y la animación ha finalizado
        if (isBarajando() && isAnimacionFinalizada()) {

            // O bien se baraja
            if(!isHaBarajado()) {
                barajar();
            }

            // O bien se inicia la fase de mostrar la carta
            else {
                Juego.setEstarComunidad(false);
                Juego.setEstarSuerte(false);
                Carta carta = null;
                try {
                    if(this instanceof SuerteGUI)
                        carta = getInformacion().getMenuGUI().getJuego().barajarSuerte(0);
                    else
                        carta = getInformacion().getMenuGUI().getJuego().barajarComunidad(0);
                } catch (Exception ignored) {
                }

                try {
                    carta.accion();
                } catch (EstarBancarrotaException e) {
                    System.err.println(e.getMessage());
                } catch (NoSerPropietarioException e) {
                    System.err.println(e.getMessage());
                } catch (ImposibleCambiarModoException e) {
                    System.err.println(e.getMessage());
                } catch (ImposibleMoverseException e) {
                    System.err.println(e.getMessage());
                } catch (EdificiosSolarException e) {
                    System.err.println(e.getMessage());
                } catch (NumeroIncorrectoException e) {
                    System.err.println(e.getMessage());
                }
                mostrarCarta();
                getInformacion().getMarcoInformacion().actualizarContenido((String[])Output.toArrayString(carta.toString()).toArray());
            }


        }

        // Si se está en la fase de mostrar la carta y ha finalizado, se comienza a esconder la carta
        else if (!isBarajando() && isAnimacionFinalizada() && isMostrandose()) {
            esconderCarta();
        }
    }

    /**
     * Se baraja
     */
    public void barajar() {

        setAnimacionFinalizada(false);
        setMostrandose(false);

        setPrimerFrame(true);
        setUltimoFrame(null);

        MediaPlayer reproductor = new MediaPlayer(getSonidoBarajar());
        reproductor.play();
    }

    /**
     * Se muestra una carta
     */
    public void mostrarCarta() {

        setBarajando(false);
        setAnimacionFinalizada(false);
        setMostrandose(true);

        setPrimerFrame(true);
        setUltimoFrame(null);

        MediaPlayer reproductor = new MediaPlayer(getSonidoRevelar());
        reproductor.play();
    }

    /**
     * Se esconde la carta mostrada
     */
    public void esconderCarta() {
        setBarajando(false);
        setAnimacionFinalizada(false);
        setMostrandose(false);
        setPrimerFrame(true);
        setUltimoFrame(null);
    }

    /**
     * Se renderiza la carta
     */
    public void render(double t) {

        // Se limpia el gc del anterior frame
        getGc().clearRect(0, 0, ConstantesGUI.BARAJA_ANCHO, ConstantesGUI.BARAJA_ALTO);

        if (isAnimacionFinalizada()) {
            renderAnimacionFinalizada(t);
        }

        // En caso contrario, se renderiza un fotograma
        else {
            renderAnimacionActiva(t);
        }
    }

    /**
     * Se renderiza el último frame de la animación de la baraja o de la animación de voltear la carta, en función del
     * estado
     *
     * @param t tiempo transcurrido
     */
    private void renderAnimacionFinalizada(double t) {

        // Frame a renderizar
        Image frame;

        // Desplazamiento del renderizado
        int despX = 0;
        int despY = 0;

        if (isBarajando()) {
            frame = getBaraja().getFrameInversoNumero(0);
        } else {
            frame = getVolteado().getFrameInversoNumero(0);
            despX = getCartaDesplazamientoX();
            despY = getCartaDesplazamientoY();

            // Se renderiza igualmente la baraja, para que no desaparezcan las demás cartas
            getGc().drawImage(getBaraja().getFrameInversoNumero(0), 0, 0);
        }

        // Se renderiza y se guarda
        getGc().drawImage(frame, despX, despY);
    }

    /**
     * Se renderiza uno de los frames de la baraja, pudiendo encontrarse este tanto barajándose como volteándose
     *
     * @param t tiempo transcurrido
     */
    private void renderAnimacionActiva(double t) {

        // Frame a renderizar
        Image frame;

        // Desplazamiento del renderizado
        int despX = 0;
        int despY = 0;


        // Si se trata del primer frame, se guarda el tiempo de inicio
        if (isPrimerFrame()) {
            getBaraja().setTiempoInicio(t);
            getVolteado().setTiempoInicio(t);
            setPrimerFrame(false);
        }

        // Si se va a barajar
        if (isBarajando()) {

            frame = getBaraja().getFrame(t);

            // La animación debe finalizar al alcanzar el último frame; sin embargo, es probable que el último frame en
            // renderizarse no sea precisamente el último de la animación, sino que sea uno de los últimos al obtener
            // frames en función del tiempo transcurrido. Para ello, debe llevarse el registro del último frame
            // renderizado, y comprobar si el frame obtenido ha vuelto a comenzar el bucle de la animación
            // Si se vuelve a comenzar la animación, se indica el fin y se establece como activo
            if (getUltimoFrame() != null && getBaraja().getFrames().indexOf(frame)
                    < getBaraja().getFrames().indexOf(getUltimoFrame())) {

                frame = getBaraja().getFrameInversoNumero(0);
                setAnimacionFinalizada(true);
                setHaBarajado(true);
            }
        }

        // En caso contrario, se está en la fase de voltear una carta
        else {

            despX = getCartaDesplazamientoX();
            despY = getCartaDesplazamientoY();

            // Si se está mostrando la carta
            if (isMostrandose()) {

                frame = getVolteado().getFrame(t);

                // Si se comenzado de nuevo la animación, se indica el fin y se establece como no activo
                if (getUltimoFrame() != null && getVolteado().getFrames().indexOf(frame)
                        < getVolteado().getFrames().indexOf(getUltimoFrame())) {

                    frame = getVolteado().getFrameInversoNumero(0);
                    setAnimacionFinalizada(true);
                }
            }

            // Si se está escondiendo
            else {

                frame = getVolteado().getFrameInverso(t);

                // Si se comenzado de nuevo la animación, se indica el fin y se establece como no activo
                if (getUltimoFrame() != null && getVolteado().getFrames().indexOf(frame)
                        > getVolteado().getFrames().indexOf(getUltimoFrame())) {

                    frame = getVolteado().getFrameNumero(0);
                    setAnimacionFinalizada(true);
                    setBarajando(true);
                    setHaBarajado(false);
                }
            }

            // Se renderiza igualmente la baraja, para que no desaparezcan las demás cartas
            getGc().drawImage(getBaraja().getFrameInversoNumero(0), 0, 0);
        }

        // Se renderiza el frame
        getGc().drawImage(frame, despX, despY);

        // Se actualiza el último frame renderizado
        setUltimoFrame(frame);
    }
}
