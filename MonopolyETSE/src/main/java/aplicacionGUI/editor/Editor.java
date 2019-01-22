package aplicacionGUI.editor;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.filas.Fila;
import aplicacionGUI.editor.filas.TipoFila;
import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.TipoFase;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import monopoly.tablero.TipoGrupo;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Solar;
import monopoly.tablero.jerarquiaCasillas.TipoCasilla;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.Salida;
import resources.editor.EditorCuadricula;
import resources.menuGUI.botones.BotonesImagenes;

import java.util.ArrayList;
import java.util.HashSet;

public class Editor {

    /* Atributos */

    // Aplicación gráfica asociada
    private final AplicacionGUI aplicacionGUI;

    // Nodo propiedad del editor
    private final Group nodo;

    // Canvas contenido en el nodo
    private final Canvas canvas;

    // Contexto en el que representar objetos
    private final GraphicsContext gc;

    // Sensor asociado al editor
    private final Rectangle sensor;

    // Diferencia para el sensor del editor
    private final Rectangle diferencia;

    // Celdas del editor
    private final ArrayList<ArrayList<Celda>> celdas;

    // Cuadrícula del editor
    private final Image cuadricula = new
            Image(EditorCuadricula.class.getResource(ConstantesGUI.EDITOR_CUADRICULA).toString());

    // Filas para la contabilidad de las casillas
    private final ArrayList<Fila> filas;

    // Sensores para los botones de aceptar y cancelar
    private final Rectangle sensorAceptar;
    private final Rectangle sensorCancelar;

    // Imágenes para los botones de aceptar y cancelar
    private final Image botonAceptar = new Image(BotonesImagenes.class.getResource(ConstantesGUI.EDITOR_BOTON_ACEPTAR).toString());
    private final Image botonAceptarOscuro = new Image(BotonesImagenes.class.getResource(
            ConstantesGUI.EDITOR_BOTON_ACEPTAR_OSCURO).toString());

    private final Image botonCancelar = new Image(BotonesImagenes.class.getResource(ConstantesGUI.EDITOR_BOTON_CANCELAR).toString());
    private final Image botonCancelarOscuro = new Image(BotonesImagenes.class.getResource(
            ConstantesGUI.EDITOR_BOTON_CANCELAR_OSCURO).toString());

    private Image botonAceptarSeleccionada = botonAceptar;
    private Image botonCancelarSeleccionada = botonCancelar;



    /* Constructor */

    /**
     * Se crea un editor del tablero
     *
     * @param aplicacionGUI aplicación gráfica a asociar
     * @param raiz          nodo sobre el cual crear un hijo para el editor
     */
    public Editor(AplicacionGUI aplicacionGUI, Group raiz) {

        if( aplicacionGUI == null ) {
            System.err.println("Aplicación gráfica no inicializada");
            System.exit(1);
        }

        if (raiz == null) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }

        // Se guarda la aplicación gráfica
        this.aplicacionGUI = aplicacionGUI;

        // Se añade al nodo dado un nuevo nodo de uso para el editor
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se crea un canvas en el nuevo nodo para representar la cuadrícula
        this.canvas = new Canvas(ConstantesGUI.EDITOR_ANCHO, ConstantesGUI.EDITOR_ALTO);
        this.nodo.getChildren().add(canvas);

        // Se genera un contexto a partir del canvas para insertar la cuadrícula
        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(ConstantesGUI.EDITOR_CUADRICULA_DESPLAZAMIENTO_X,
                ConstantesGUI.EDITOR_CUADRICULA_DESPLAZAMIENTO_Y, ConstantesGUI.EDITOR_ANCHO, ConstantesGUI.EDITOR_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Y su diferencia
        this.diferencia = new Rectangle(ConstantesGUI.EDITOR_CUADRICULA_DESPLAZAMIENTO_X + ConstantesGUI.CASILLA_ANCHO,
                ConstantesGUI.EDITOR_CUADRICULA_DESPLAZAMIENTO_Y + ConstantesGUI.CASILLA_ALTO,
                (ConstantesGUI.CASILLAS_POR_LADO - 2) * (ConstantesGUI.CASILLA_ANCHO),
                (ConstantesGUI.CASILLAS_POR_LADO - 2) * (ConstantesGUI.CASILLA_ALTO));
        this.diferencia.setFill(Color.TRANSPARENT);

        // Se obtienen las correspondientes posiciones de las casillas en la ventana
        ArrayList<int[]> posiciones = TableroGUI.calcularPosiciones();

        // Se inicializa el array de celdas
        this.celdas = new ArrayList<>();

        // Se crean las celdas
        for (int i = 0, contador = 0; i < ConstantesGUI.NUMERO_LADOS; i++) {

            // Se crea una fila para cada lado
            ArrayList<Celda> fila = new ArrayList<>();
            this.celdas.add(fila);

            // Se crean tantas celdas como casillas haya por fila
            for (int j = 0; j < ConstantesGUI.CASILLAS_POR_FILA; j++, contador++) {

                fila.add(new Celda(this, this.nodo, posiciones.get(contador)[0] +
                        ConstantesGUI.EDITOR_CUADRICULA_DESPLAZAMIENTO_X, posiciones.get(contador)[1] +
                        ConstantesGUI.EDITOR_CUADRICULA_DESPLAZAMIENTO_Y, contador));
            }
        }

        // Se crea una fila para cada uno de los lados del tablero
        this.filas = new ArrayList<>();
        this.filas.add(new Fila(TipoFila.este));
        this.filas.add(new Fila(TipoFila.norte));
        this.filas.add(new Fila(TipoFila.oeste));
        this.filas.add(new Fila(TipoFila.sur));

        // Se crea la casilla de salida (fija)
        Celda salida = this.celdas.get(0).get(0);

        salida.setCasillaGUI(new CasillaGUI(Celda.getTableroGUI(), salida.getNodo(), new Salida("Salida",
                salida.getPosicionTablero(), Celda.getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0,
                0, false));

        actualizarNumeroCasillas(TipoCasilla.salida, salida.getPosicionTablero(), 1);

        // Se crean los sensores de los botones
        this.sensorAceptar = new Rectangle(ConstantesGUI.EDITOR_BOTON_ACEPTAR_DESPLAZAMIENTO_X,
                ConstantesGUI.EDITOR_BOTON_ACEPTAR_DESPLAZAMIENTO_Y, ConstantesGUI.BOTON_ANCHO, ConstantesGUI.BOTON_ALTO);
        this.sensorAceptar.setFill(Color.TRANSPARENT);

        this.sensorCancelar = new Rectangle(ConstantesGUI.EDITOR_BOTON_CANCELAR_DESPLAZAMIENTO_X,
                ConstantesGUI.EDITOR_BOTON_CANCELAR_DESPLAZAMIENTO_Y, ConstantesGUI.BOTON_ANCHO, ConstantesGUI.BOTON_ALTO);
        this.sensorAceptar.setFill(Color.TRANSPARENT);
    }


    /* Getters y setters */

    public AplicacionGUI getAplicacionGUI() {
        return aplicacionGUI;
    }

    public Group getNodo() {
        return nodo;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Image getCuadricula() {
        return cuadricula;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public Rectangle getDiferencia() {
        return diferencia;
    }

    public ArrayList<ArrayList<Celda>> getCeldas() {
        return celdas;
    }

    public ArrayList<Fila> getFilas() {
        return filas;
    }

    public Rectangle getSensorAceptar() {
        return sensorAceptar;
    }

    public Rectangle getSensorCancelar() {
        return sensorCancelar;
    }

    public Image getBotonAceptar() {
        return botonAceptar;
    }

    public Image getBotonAceptarOscuro() {
        return botonAceptarOscuro;
    }

    public Image getBotonCancelar() {
        return botonCancelar;
    }

    public Image getBotonCancelarOscuro() {
        return botonCancelarOscuro;
    }

    public Image getBotonAceptarSeleccionada() {
        return botonAceptarSeleccionada;
    }

    public void setBotonAceptarSeleccionada(Image botonAceptarSeleccionada) {
        this.botonAceptarSeleccionada = botonAceptarSeleccionada;
    }

    public Image getBotonCancelarSeleccionada() {
        return botonCancelarSeleccionada;
    }

    public void setBotonCancelarSeleccionada(Image botonCancelarSeleccionada) {
        this.botonCancelarSeleccionada = botonCancelarSeleccionada;
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

        double posicionX = x;
        double posicionY = y;

        return ((getSensor().contains(posicionX, posicionY) && !getDiferencia().contains(posicionX, posicionY)) ||
                getSensorAceptar().contains(posicionX, posicionY) || getSensorCancelar().contains(posicionX, posicionY));
    }

    /**
     * Se ejecuta la acción definida ante una pulsación del ratón
     *
     * @param x        coordenada X del click
     * @param y        coordenada Y del click
     */
    public void handlePulsacion(double x, double y) {

        double posicionX = x;
        double posicionY = y;

        // Se comprueban los botones
        if (getSensorAceptar().contains(posicionX, posicionY)) {

            setBotonAceptarSeleccionada(getBotonAceptarOscuro());

        } else if (getSensorCancelar().contains(posicionX, posicionY)) {

            setBotonCancelarSeleccionada(getBotonCancelarOscuro());
        }
    }

    /**
     * Se ejecuta la acción definida ante un release
     *
     * @param x        coordenada X del click
     * @param y        coordenada Y del click
     * @param nodoRaiz nodo de anclaje
     * @param e        evento del click
     * @param menus    conjunto de menús contextuales activos
     */
    public void handleRelease(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
            menus) {

        double posicionX = x;
        double posicionY = y;

        System.out.println("x es " + posicionX);
        System.out.println("y es " + posicionY);

        // Si es la cuadrícula quien contiene el evento
        if (getSensor().contains(posicionX, posicionY) && !getDiferencia().contains(posicionX, posicionY)) {

            // Se comprueba cada una de las celdas
            for (ArrayList<Celda> fila : getCeldas()) {

                for (Celda celda : fila) {

                    if (celda.contienePosicion(posicionX, posicionY)) {
                        celda.handleClick(posicionX, posicionY, nodoRaiz, e, menus);
                    }
                }
            }
        }

        // En caso contrario se comprueban los botones
        else if (getSensorAceptar().contains(posicionX, posicionY)) {

            setBotonAceptarSeleccionada(getBotonAceptar());

            // Si el tablero es válido, se exporta y se cambia de fase
            if (tableroValido()) {

                getAplicacionGUI().setTableroPersonalizado(exportarTablero());
                getAplicacionGUI().setTipoFase(TipoFase.inicioJuego);
                getAplicacionGUI().ejecutarFase(getAplicacionGUI().getTipoFase());
            }

        } else if (getSensorCancelar().contains(posicionX, posicionY)) {

            setBotonCancelarSeleccionada(getBotonCancelar());

            // Se cambia de fase
            getAplicacionGUI().setTipoFase(TipoFase.inicioJuego);
            getAplicacionGUI().ejecutarFase(getAplicacionGUI().getTipoFase());
        }
    }

    /**
     * Se renderiza el editor
     *
     * @param t tiempo transcurrido
     */
    public void render(double t) {

        // Se muestran los botones
        getGc().drawImage(getBotonAceptarSeleccionada(), ConstantesGUI.EDITOR_BOTON_ACEPTAR_DESPLAZAMIENTO_X,
                ConstantesGUI.EDITOR_BOTON_ACEPTAR_DESPLAZAMIENTO_Y);
        getGc().drawImage(getBotonCancelarSeleccionada(), ConstantesGUI.EDITOR_BOTON_CANCELAR_DESPLAZAMIENTO_X,
                ConstantesGUI.EDITOR_BOTON_CANCELAR_DESPLAZAMIENTO_Y);

        // Se muestra la cuadrícula
        getGc().drawImage(getCuadricula(), ConstantesGUI.EDITOR_CUADRICULA_DESPLAZAMIENTO_X,
                ConstantesGUI.EDITOR_CUADRICULA_DESPLAZAMIENTO_Y);

        // Se renderizan las celdas
        for (ArrayList<Celda> fila : getCeldas()) {

            for (Celda celda : fila) {
                celda.render(t);
            }
        }
    }

    /**
     * Se limpia el GC del editor
     */
    public void clear() {

        getGc().clearRect(0, 0, ConstantesGUI.EDITOR_ANCHO, ConstantesGUI.EDITOR_ALTO);

        // Se limpian los GCs de las celdas
        for (ArrayList<Celda> fila : getCeldas()) {

            for (Celda celda : fila) {
                celda.clear();
            }
        }
    }

    /**
     * Se comprueba si es posible crear una casilla más de un tipo dado en la posición especificada
     *
     * @param tipoCasilla tipo de casilla a comprobar
     * @param posicion    posición de la casilla a comprobar
     * @return si es posible crear una casilla más del tipo especificado en la casilla de la posición dada
     */
    public boolean masCasillas(TipoCasilla tipoCasilla, int posicion) {

        // La comprobación se hace sobre la correspondiente fila de la casilla en la posición dada
        return (getFilas().get(posicion / 10).masCasillas(tipoCasilla));
    }

    /**
     * Se actualiza el número de casillas de un tipo dado en la fila que contiene la casilla de la posición dada
     *
     * @param tipoCasilla tipo de casilla creada
     * @param posicion    posición de la casilla cuya fila actualizar
     * @param balance     cantidad en la que aumentar/disminuir el número de casillas
     */
    public void actualizarNumeroCasillas(TipoCasilla tipoCasilla, int posicion, int balance) {

        // La actualización se hace sobre la correspondiente fila de la casilla en la posición dada
        getFilas().get(posicion / 10).actualizarNumeroCasillas(tipoCasilla, balance);
    }

    /**
     * Se obtienen los grupos de solares para los cuales es posible añadir una casilla más en la casilla de la posición
     * especificada
     *
     * @param posicion posición de la casilla a comprobar
     * @return conjunto de grupos de solares con solares restantes por ser creados
     */
    public HashSet<TipoGrupo> getGruposSolaresLibres(int posicion) {

        int lado = posicion / 10;
        HashSet<TipoGrupo> resultado = new HashSet<>();

        // Lado sur
        if (lado == 0) {

            if (estaLibre(TipoGrupo.negro, 0)) {
                resultado.add(TipoGrupo.negro);
            }

            if (estaLibre(TipoGrupo.cyan, 0)) {
                resultado.add(TipoGrupo.cyan);
            }
        }

        // Lado oeste
        else if (lado == 1) {

            if (estaLibre(TipoGrupo.rosa, 1)) {
                resultado.add(TipoGrupo.rosa);
            }

            if (estaLibre(TipoGrupo.naranja, 1)) {
                resultado.add(TipoGrupo.naranja);
            }
        }

        // Lado norte
        else if (lado == 2) {

            if (estaLibre(TipoGrupo.rojo, 2)) {
                resultado.add(TipoGrupo.rojo);
            }

            if (estaLibre(TipoGrupo.marron, 2)) {
                resultado.add(TipoGrupo.marron);
            }
        }

        // Lado este
        else {

            if (estaLibre(TipoGrupo.verde, 3)) {
                resultado.add(TipoGrupo.verde);
            }

            if (estaLibre(TipoGrupo.azul, 3)) {
                resultado.add(TipoGrupo.azul);
            }
        }

        return (resultado);
    }

    /**
     * Se comprueba si, en un grupo de casillas, aún no se han creado todas las que este puede contener, buscando en
     * una fila específica
     *
     * @param tipoGrupo grupo a comprobar
     * @param fila      fila que contiene el grupo
     * @return si quedan más casillas por crear en la fila para el grupo dado
     */
    private boolean estaLibre(TipoGrupo tipoGrupo, int fila) {

        int total = 0;

        for (Celda celda : getCeldas().get(fila)) {

            // Si se ha creado una casilla en la celda
            if (celda.getCasillaGUI() != null) {

                Casilla casilla = celda.getCasillaGUI().getCasilla();

                if (casilla instanceof Solar) {

                    if (((Solar) casilla).getGrupo().getTipo().equals(tipoGrupo)) {
                        total++;
                    }
                }
            }
        }

        return (total < tipoGrupo.getTamano());
    }

    /**
     * Se comprueba si el tablero creado por el usuario es válido
     *
     * @return si el tablero actual es válido
     */
    public boolean tableroValido() {

        for (ArrayList<Celda> fila : getCeldas()) {

            for (Celda celda : fila) {

                if (celda.getCasillaGUI() == null) {
                    return (false);
                }
            }
        }

        return (true);
    }

    /**
     * Se exporta el tablero actual a un conjunto de contenedores de información sobre representaciones de casillas y
     * sobre estas
     *
     * @return información exportada
     */
    public ArrayList<InformacionCasillaGUI> exportarTablero() {

        ArrayList<InformacionCasillaGUI> informacion = new ArrayList<>();

        for (ArrayList<Celda> fila : getCeldas()) {

            for (Celda celda : fila) {
                informacion.add(celda.toInformacionCasillaGUI());
            }
        }

        return (informacion);
    }
}
