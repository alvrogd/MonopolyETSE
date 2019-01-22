package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.ImagenAnimada;
import aplicacionGUI.informacion.tableroGUI.ColorCasillaGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.handlers.DescribirCasilla;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import monopoly.jugadores.Avatar;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;
import resources.avatares.modoAvanzado.AnimacionAvataresModoAvanzado;
import resources.casillas.FondosCasillas;

import java.util.ArrayList;
import java.util.HashSet;

public class CasillaGUI {

    /* Atributos */

    // Representación del tablero asociada
    private final TableroGUI tableroGUI;

    // Nodo propiedad de la casilla
    private final Group nodo;

    // Canvas contenido en el nodo
    private final Canvas canvas;

    // Contexto en el que representar objetos
    private final GraphicsContext gc;

    // Dimensiones de la representación
    private final int ancho = ConstantesGUI.CASILLA_ANCHO;
    private final int alto = ConstantesGUI.CASILLA_ALTO;

    // Desplazamiento dado de la casilla
    private final int desplazamientoX;
    private final int desplazamientoY;

    // La casilla asociada
    private Casilla casilla;

    // Imagen de fondo de la casilla asociada
    private Image fondo;

    // Sensor de la casilla
    private Rectangle sensor;

    // Animación de movimiento avanzado para los avatares
    private final static ImagenAnimada ANIMACION_MODO_AVANZADO = new ImagenAnimada(new AnimacionAvataresModoAvanzado(),
            ConstantesGUI.AVATARES_AVANZADO_FRAMES, 0.25);

    // Menú contextual mostrado
    private ContextMenu menu;

    // Booleano para saber si la casilla ha sido seleccionada en la gestión de los tratos
    private boolean seleccionada;

    // Si la representación pertence a una representación de un tablero
    private final boolean perteneceTableroGUI;



    /* Constructor */

    /**
     * Se crea una representación de una casilla
     *
     * @param tableroGUI          representación de un tablero asociada a la representación de una casilla
     * @param raiz                nodo sobre el cual crear un hijo para la representación de la casilla
     * @param casilla             casilla a representar
     * @param ficheroFondo        imagen de fondo de la casilla a representar
     * @param posicionX           posición (coordenada X) de la representación de la casilla en la representación del
     *                            tablero
     * @param posicionY           posición (coordenada Y) de la representación de la casilla en la representación del
     *                            tablero
     * @param perteneceTableroGUI si pertenece a una representación de un tablero
     */
    public CasillaGUI(TableroGUI tableroGUI, Group raiz, Casilla casilla, String ficheroFondo, int posicionX,
                      int posicionY, boolean perteneceTableroGUI) {

        if (tableroGUI == null) {
            System.err.println("TableroGUI no inicializado");
            System.exit(1);
        }

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if (casilla == null) {
            System.err.println("Casilla no inicializada");
            System.exit(1);
        }

        if (ficheroFondo == null) {
            System.err.println("Nombre del fichero de fondo no inicializado");
            System.exit(1);
        }

        // Se registra la representación del tablero a la que pertenece
        this.tableroGUI = tableroGUI;

        // Se añade al nodo dado un nuevo nodo de uso para la casilla
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana
        this.desplazamientoX = posicionX;
        this.desplazamientoY = posicionY;
        this.nodo.getTransforms().add(new Translate(this.desplazamientoX, this.desplazamientoY));

        // Se crea un canvas en el nuevo nodo para representar la casilla
        this.canvas = new Canvas(ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO);
        this.nodo.getChildren().add(canvas);

        // Se genera un contexto a partir del canvas para insertar la representación de la casilla
        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Se guarda la referencia a la casilla asociada
        this.casilla = casilla;

        // Se obtiene el fondo correspondiente
        this.fondo = new Image(FondosCasillas.class.getResource(ficheroFondo).toString());

        // El menú se generá cuando sea solicitado
        this.menu = null;

        // Inicialmente, la casilla no se encuentra seleccionada
        this.seleccionada = false;

        // Se indica si pertenece o no a una representación de un tablero
        this.perteneceTableroGUI = perteneceTableroGUI;
    }



    /* Getters y setters */

    public TableroGUI getTableroGUI() {
        return tableroGUI;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public Image getFondo() {
        return fondo;
    }

    public void setFondo(Image fondo) {
        this.fondo = fondo;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public void setSensor(Rectangle sensor) {
        this.sensor = sensor;
    }

    public Group getNodo() {
        return nodo;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public boolean isSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public int getDesplazamientoX() {
        return desplazamientoX;
    }

    public int getDesplazamientoY() {
        return desplazamientoY;
    }

    public static ImagenAnimada getAnimacionModoAvanzado() {
        return ANIMACION_MODO_AVANZADO;
    }

    public ContextMenu getMenu() {
        return menu;
    }

    public void setMenu(ContextMenu menu) {
        this.menu = menu;
    }

    public boolean isPerteneceTableroGUI() {
        return perteneceTableroGUI;
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
    }

    /**
     * Se ejecuta la acción definida ante un click derecho
     *
     * @param x        coordenada X del click
     * @param y        coordenada Y del click
     * @param nodoRaiz nodo de anclaje
     * @param e        evento del click
     * @param menus    conjunto de menús contextuales activos
     * @param app      aplicación de Monopoly sobre la cual se ejecuta el juego
     */
    public void handleClickDerecho(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
            menus, Aplicacion app) {

        double posicionX = x - getDesplazamientoX();
        double posicionY = y - getDesplazamientoY();

        System.out.println(getCasilla().getNombre());

        // Se genera el menú
        setMenu(generarMenuContextual(app));

        // Se muestra el menú
        getMenu().show(nodoRaiz, e.getScreenX(), e.getScreenY());

        // Se añade al listado de menús activos
        menus.add(getMenu());
    }

    /**
     * Se renderiza la casilla
     *
     * @param t tiempo transcurrido
     */
    public void render(double t) {

        clear();
        renderFondo();
        renderNombre();
        renderContenido(t);
    }

    /**
     * Se renderiza el fondo
     */
    public void renderFondo() {

        // Se añade la imagen
        getGc().drawImage(getFondo(), 0, 0);
    }

    /**
     * Se renderiza el nombre
     */
    public void renderNombre() {

        // Se añade el color a la casilla en la posición del nombre
        getGc().setStroke(Color.TRANSPARENT);

        if (getCasilla() instanceof Propiedad) {
            getGc().setFill(ColorCasillaGUI.tipoColorToColorTransparente(((Propiedad) getCasilla()).getGrupo().getTipo(
            ).getColor()));
        } else {
            getGc().setFill(Color.rgb(128, 128, 128, 0.85));
        }

        getGc().fillRect(3, 3, getAncho() - 6, 14);

        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.CENTER);
        getGc().setLineWidth(1);

        // Se añade el nombre de la casilla (la posición es la parte central inferior)
        getGc().fillText(getCasilla().getNombre(), ancho / 2, 14);
    }

    /**
     * Se renderiza el contenido (avatares contenidos)
     *
     * @param t tiempo transcurrido
     */
    public void renderContenido(double t) {

        // Se añade un fondo transparente sobre el que introducir la información de la casilla
        getGc().setFill(Color.rgb(128, 128, 128, 0.6));
        getGc().fillRect(3, 19, ancho - 6, 43); // todo cambiar ancho por llamada

        // Se renderiza el contenido
        renderAvataresContenidos(t);
    }

    /**
     * Se borra la representación de la casilla en la ventana
     */
    public void clear() {

        getGc().clearRect(0, 0, ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO);
    }

    /**
     * Se renderizan los avatares contenidos
     *
     * @param t tiempo transcurrido
     */
    public void renderAvataresContenidos(double t) {

        // Se insertan los identificadores de los avatares contenidos
        int desplazamiento = 0;

        for (Avatar avatar : getCasilla().getAvataresContenidos().values()) {

            getGc().drawImage(getTableroGUI().getRepresentacionesAvatares().get(avatar.getIdentificador()), 6 +
                    desplazamiento, 22);

            // Y, si se encuentra en modo avanzado, se añade la animación
            if (!avatar.isMovimientoEstandar()) {
                getGc().drawImage(getAnimacionModoAvanzado().getFrame(t), 3 + desplazamiento, 20);
            }

            desplazamiento += 18;
        }
    }

    /**
     * Se genera un menú contextual para la casilla
     *
     * @param app aplicación de Monopoly sobre la cual se ejecuta el juego
     * @return menú contextual generado
     */
    public ContextMenu generarMenuContextual(Aplicacion app) {

        // Se crea el menú de opciones para la casilla
        ContextMenu menu = new ContextMenu();

        // Se obtienen las funciones propias a la casilla
        HashSet<TipoFuncion> funciones = getCasilla().funcionesARealizar();

        if (funciones.contains(TipoFuncion.describir)) {

            // Se añade la opción de describir
            MenuItem item1 = new MenuItem("Describir");
            item1.setOnAction(new DescribirCasilla(getCasilla()));
            // Se añade la opción al menú
            menu.getItems().add(item1);
        }

        return (menu);
    }
}
