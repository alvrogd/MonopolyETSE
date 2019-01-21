package aplicacionGUI.informacion.tableroGUI;

import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.InformacionCasillaGUI;
import aplicacionGUI.informacion.Informacion;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.PropiedadGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.SolarGUI;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.jugadores.Avatar;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.Solar;
import resources.avatares.ImagenesAvatares;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TableroGUI {

    /* Atributos */

    // Nodo propiedad del tablero
    private final Group nodo;

    // Sensor asociado al tablero
    private final Rectangle sensor;

    // Diferencia para el sensor del tablero
    private final Rectangle diferencia;

    // Representaciones de las casillas
    private final ArrayList<ArrayList<CasillaGUI>> casillasGUI;

    // Representaciones asignadas a los avatares de los jugadores
    private final HashMap<Character, Image> representacionesAvatares;

    // Información
    private final Informacion informacion;



    /* Constructor */

    /**
     * Se crea una representación vacía de un tablero
     */
    public TableroGUI() {
        this.casillasGUI = null;
        this.diferencia = null;
        this.nodo = null;
        this.representacionesAvatares = null;
        this.sensor = null;
        this.informacion = null;
    }

    /**
     * Se crea una representación de un tablero
     *
     * @param informacion sección de información asociada con la representación del tablero
     * @param raiz        nodo sobre el cual crear un hijo para la representación del tablero
     * @param tablero     tablero a representar
     */
    public TableroGUI(Informacion informacion, Group raiz, Tablero tablero) {

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        if (tablero == null) {
            System.err.println("Tablero no inicializado");
            System.exit(1);
        }

        if (informacion == null) {
            System.err.println("Información no inicializado");
            System.exit(1);
        }

        this.informacion = informacion;

        // Se añade al nodo dado un nuevo nodo de uso para el tablero
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.TABLERO_DESPLAZAMIENTO_X,
                ConstantesGUI.TABLERO_DESPLAZAMIENTO_Y));

        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.TABLERO_ANCHO, ConstantesGUI.TABLERO_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Y su diferencia (es necesario el ajuste de juntar las casillas)
        this.diferencia = new Rectangle(ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO,
                (ConstantesGUI.CASILLAS_POR_LADO - 2) * (ConstantesGUI.CASILLA_ANCHO - 3),
                (ConstantesGUI.CASILLAS_POR_LADO - 2) * (ConstantesGUI.CASILLA_ALTO - 3));
        this.diferencia.setFill(Color.TRANSPARENT);

        // Se obtienen las casillas del tablero
        ArrayList<ArrayList<Casilla>> casillas = tablero.getCasillas();

        // Se obtienen las correspondientes posiciones de las casillas en la ventana
        ArrayList<int[]> posiciones = calcularPosiciones();

        // Se inicializa el array de representaciones
        this.casillasGUI = new ArrayList<>();

        int contador = 0;

        // Se itera sobre cada fila
        for (ArrayList<Casilla> fila : casillas) {

            // Se crea una fila para cada fila iterada
            ArrayList<CasillaGUI> filaGUI = new ArrayList<>();

            // Se añade la fila a las representaciones de las casillas
            this.casillasGUI.add(filaGUI);

            // Se itera sobre las casillas de la fila
            for (Casilla casilla : fila) {

                if (casilla instanceof Propiedad) {

                    if (casilla instanceof Solar) {
                        // todo ya no es necesario this y this.nodo
                        filaGUI.add(new SolarGUI(this, this.nodo, (Solar) casilla, ConstantesGUI.CASILLAS_IMAGENES[contador],
                                posiciones.get(contador)[0], posiciones.get(contador)[1]));
                    } else {
                        filaGUI.add(new PropiedadGUI(this, this.nodo, (Propiedad) casilla, ConstantesGUI.CASILLAS_IMAGENES[
                                contador], posiciones.get(contador)[0], posiciones.get(contador)[1]));
                    }
                } else {
                    filaGUI.add(new CasillaGUI(this, this.nodo, casilla, ConstantesGUI.CASILLAS_IMAGENES[contador],
                            posiciones.get(contador)[0], posiciones.get(contador)[1]));
                }

                contador++;
            }
        }


        // Se crea inicialmente un ArrayList con todas las imágenes disponibles para los avatares
        ArrayList<Image> avatares = new ArrayList<>();

        for (String string : ConstantesGUI.AVATARES_IMAGENES) {
            avatares.add(new Image(ImagenesAvatares.class.getResource(string).toString()));
        }

        // A continuación, se asocia a cada uno de los avatares de los jugadores una de las representaciones de los
        // avatares
        this.representacionesAvatares = new HashMap<>();

        for (Avatar avatar : tablero.getAvataresContenidos().values()) {

            // Se bajaran las representaciones disponibles
            Collections.shuffle(avatares);

            this.representacionesAvatares.put(avatar.getIdentificador(), avatares.get(0));
            avatares.remove(0);
        }
    }

    /**
     * Se crea una representación de un tablero y se actualizan los fondos de cada casilla con la información dada
     *
     * @param informacion      sección de información asociada con la representación del tablero
     * @param raiz             nodo sobre el cual crear un hijo para la representación del tablero
     * @param tablero          tablero a representar
     * @param tableroExportado conjunto de información sobre las representaciones de las casillas al partir del cual
     *                         extraer los nuevos fondos
     */
    public TableroGUI(Informacion informacion, Group raiz, Tablero tablero, ArrayList<InformacionCasillaGUI>
            tableroExportado) {

        this(informacion, raiz, tablero);

        // Se cambian las imágenes de las casillas
        int contador = 0;

        for (ArrayList<CasillaGUI> fila : this.casillasGUI) {

            for (CasillaGUI casillaGUI : fila) {
                casillaGUI.setFondo(tableroExportado.get(contador++).getFondo());
            }
        }
    }



    /* Getters y setters */

    public Group getNodo() {
        return nodo;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public Informacion getInformacion() {
        return informacion;
    }

    public ArrayList<ArrayList<CasillaGUI>> getCasillasGUI() {
        return casillasGUI;
    }

    public Rectangle getDiferencia() {
        return diferencia;
    }

    public HashMap<Character, Image> getRepresentacionesAvatares() {
        return representacionesAvatares;
    }



    /* Métodos */

    /**
     * Se calculan las posiciones en pantalla de cada una de las casillas del tablero
     *
     * @return posiciones de las casillas, insertadas por orden de casilla; los dos elementos del array son las
     * coordenadas X e Y respectivamente
     */
    public static ArrayList<int[]> calcularPosiciones() {

        // Array final de posiciones
        ArrayList<int[]> posiciones = new ArrayList<>();

        // Array auxiliar
        ArrayList<int[]> aux;

        // Se calculan en orden las posiciones de las casillas, comenzando por la fila inferior (es necesario darle la
        // vuelta puesto que las posiciones se comienzan a calcular desde el lado izquierdo)
        aux = calcularPosicionesFila(false);
        Collections.reverse(aux);
        posiciones.addAll(aux);

        // Las posiciones de la fila izquierda (también es necesario darles la vuelta al calcularlas de arriba abajo)
        aux = calcularPosicionesColumna(true);
        Collections.reverse(aux);
        posiciones.addAll(aux);

        // Las posiciones de la fila superior
        aux = calcularPosicionesFila(true);
        posiciones.addAll(aux);

        // Las posiciones de la columna derecha
        aux = calcularPosicionesColumna(false);
        posiciones.addAll(aux);

        return (posiciones);
    }


    /**
     * Se calculan las posiciones en pantalla de una fila del tablero, de izquierda a derecha
     *
     * @param superior si se trata de la fila superior
     * @return posiciones de las casillas de la fila
     */
    private static ArrayList<int[]> calcularPosicionesFila(boolean superior) {

        ArrayList<int[]> posiciones = new ArrayList<>();

        // Posición X en el canvas; se comienzan a insertar las casillas desde la izquierda
        int posicionX = 0;
        // Posición Y en el canvas
        int posicionY;

        // Si es la fila superior
        if (superior) {
            // Se comienza a insertar desde el inicio
            posicionY = 0;
        }

        // En caso contrario, es la fila inferior
        else {
            // Se salta desde el inicio la suma de todas las casillas de un lado menos la última
            posicionY = (ConstantesGUI.CASILLAS_POR_LADO - 1) * (ConstantesGUI.CASILLA_ALTO - 3);
        }

        // Se calculan las correspondientes posiciones
        for (int i = 0; i < ConstantesGUI.CASILLAS_POR_LADO; i++, posicionX += ConstantesGUI.CASILLA_ANCHO - 3) {

            // Se guarda la posición
            posiciones.add(new int[]{posicionX, posicionY});

        }

        return (posiciones);
    }

    /**
     * Se calculan las posiciones en pantalla de una columna del tablero, de arriba abajo
     *
     * @param izquierda si se trata de la columna izquierda
     * @return posiciones de las casillas de la columna
     */
    private static ArrayList<int[]> calcularPosicionesColumna(boolean izquierda) {

        ArrayList<int[]> posiciones = new ArrayList<>();

        // Posición X en el canvas; se comienzan a insertar las casillas desde la izquierda
        int posicionX;
        // Posición Y en el canvas; comienzan a insertar las columnas desde el límite que comparten con la fila
        // superior
        int posicionY = ConstantesGUI.CASILLA_ALTO - 3;

        // Si es la columna izquierda
        if (izquierda) {
            // Se comienza a insertar desde el lado izquierdo
            posicionX = 0;
        }

        // En caso contrario, es la columna derecha
        else {
            // Se desplaza la posición en el número de casillas de un lado menos la última
            posicionX = (ConstantesGUI.CASILLAS_POR_LADO - 1) * (ConstantesGUI.CASILLA_ANCHO - 3);
        }

        // Se calculan las correspondientes posiciones
        for (int i = 0; i < ConstantesGUI.CASILLAS_POR_LADO - 2; i++, posicionY += ConstantesGUI.CASILLA_ALTO - 3) {

            // Se guarda la posición
            posiciones.add(new int[]{posicionX, posicionY});

        }

        return (posiciones);
    }

    /**
     * Se comprueba si contiene una posición 2D dada
     *
     * @param x coordenada X
     * @param y coordenada Y
     * @return si contiene la posición dada
     */
    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.TABLERO_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.TABLERO_DESPLAZAMIENTO_Y;

        return (getSensor().contains(posicionX, posicionY) && !getDiferencia().contains(posicionX, posicionY));
    }

    /**
     * Se ejecuta la acción definida ante un click izquierdo
     *
     * @param x coordenada X del click
     * @param y coordenada Y del click
     */
    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - ConstantesGUI.TABLERO_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.TABLERO_DESPLAZAMIENTO_Y;

        // Se comprueba cada una de las representaciones de las casillas
        for (ArrayList<CasillaGUI> fila : getCasillasGUI()) {

            for (CasillaGUI casillaGUI : fila) {

                if (casillaGUI.contienePosicion(posicionX, posicionY)) {
                    casillaGUI.handleClickIzquierdo(posicionX, posicionY);
                }
            }
        }
    }

    /**
     * Se ejecuta la acción definida ante un click derecho
     *
     * @param x        coordenada X del click
     * @param y        coordenada Y del click
     * @param nodoRaiz nodo de anclaje
     * @param e        evento del click
     * @param menus    conjunto de menús contextuales activos
     */
    public void handleClickDerecho(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
            menus, Aplicacion app) {

        double posicionX = x - ConstantesGUI.TABLERO_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.TABLERO_DESPLAZAMIENTO_Y;

        System.out.println("x es " + posicionX);
        System.out.println("y es " + posicionY);

        // Se comprueba cada una de las representaciones de las casillas
        for (ArrayList<CasillaGUI> fila : getCasillasGUI()) {

            for (CasillaGUI casillaGUI : fila) {

                if (casillaGUI.contienePosicion(posicionX, posicionY)) {
                    casillaGUI.handleClickDerecho(posicionX, posicionY, nodoRaiz, e, menus, app);
                }
            }
        }
    }

    /**
     * Se renderiza el tablero
     *
     * @param t tiempo transcurrido
     */
    public void render(double t) {

        for (ArrayList<CasillaGUI> fila : getCasillasGUI()) {

            for (CasillaGUI casillaGUI : fila) {
                casillaGUI.render(t);
            }
        }
    }
}
