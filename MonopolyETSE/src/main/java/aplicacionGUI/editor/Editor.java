package aplicacionGUI.editor;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.editor.filas.Fila;
import aplicacionGUI.editor.filas.TipoFila;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.tablero.TipoGrupo;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Solar;
import monopoly.tablero.jerarquiaCasillas.TipoCasilla;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.Salida;
import resources.editor.EditorCuadricula;

import java.util.ArrayList;
import java.util.HashSet;

public class Editor {

    /* Atributos */

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


    /* Constructor */

    public Editor(Group raiz ) {

        if( raiz == null ) {
            System.out.println("Raíz no inicializada");
            System.exit(1);
        }

        // Se añade al nodo dado un nuevo nodo de uso para el editor
        this.nodo = new Group();
        raiz.getChildren().add( this.nodo );

        // Se establece su correspondiente posición en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.EDITOR_DESPLAZAMIENTO_X,
                ConstantesGUI.EDITOR_DESPLAZAMIENTO_Y));

        // Se crea un canvas en el nuevo nodo para representar la cuadrícula
        this.canvas = new Canvas( ConstantesGUI.EDITOR_ANCHO, ConstantesGUI.EDITOR_ALTO);
        this.nodo.getChildren().add(canvas);

        // Se genera un contexto a partir del canvas para insertar la cuadrícula
        this.gc = this.canvas.getGraphicsContext2D();

        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.EDITOR_ANCHO, ConstantesGUI.EDITOR_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        // Y su diferencia
        this.diferencia = new Rectangle(ConstantesGUI.CASILLA_ANCHO, ConstantesGUI.CASILLA_ALTO,
                (ConstantesGUI.CASILLAS_POR_LADO - 2) * (ConstantesGUI.CASILLA_ANCHO),
                (ConstantesGUI.CASILLAS_POR_LADO - 2) * (ConstantesGUI.CASILLA_ALTO));
        this.diferencia.setFill(Color.TRANSPARENT);

        // Se obtienen las correspondientes posiciones de las casillas en la ventana
        ArrayList<int[]> posiciones = TableroGUI.calcularPosiciones();

        // Se inicializa el array de celdas
        this.celdas = new ArrayList<>();

        // Se crean las celdas
        for( int i = 0, contador = 0; i < ConstantesGUI.NUMERO_LADOS; i++ ) {

            // Se crea una fila para cada lado
            ArrayList<Celda> fila = new ArrayList<>();
            this.celdas.add(fila);

            // Se crean tantas celdas como casillas haya por fila
            for( int j = 0; j < ConstantesGUI.CASILLAS_POR_FILA; j++, contador++ ) {

                fila.add(new Celda(this, this.nodo, posiciones.get(contador)[0], posiciones.get(contador)[1],
                        contador));
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
                salida.getPosicionTablero(), Celda.getTablero()), ConstantesGUI.EDITOR_CASILLA_BLANCO, 0, 0));
        actualizarNumeroCasillas(TipoCasilla.salida, salida.getPosicionTablero(), 1);
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



    /* Métodos */

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.EDITOR_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.EDITOR_DESPLAZAMIENTO_Y;

        return(getSensor().contains(posicionX, posicionY) && !getDiferencia().contains(posicionX, posicionY));
    }

    public void handleClickDerecho(double x, double y, Group nodoRaiz, MouseEvent e, ArrayList<ContextMenu>
            menus) {

        double posicionX = x - ConstantesGUI.EDITOR_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.EDITOR_DESPLAZAMIENTO_Y;

        System.out.println("x es " + posicionX);
        System.out.println("y es " + posicionY);

        // Se comprueba cada una de las celdas
        for( ArrayList<Celda> fila : getCeldas() ) {

            for( Celda celda : fila ) {

                if( celda.contienePosicion(posicionX, posicionY) ) {
                    celda.handleClickDerecho(posicionX, posicionY, nodoRaiz, e, menus);
                }
            }
        }
    }

    public void render(double t ) {

        // Se muestra la cuadrícula
        getGc().drawImage(getCuadricula(), 0, 0);

        // Se renderizan las celdas
        for( ArrayList<Celda> fila : getCeldas() ) {

            for( Celda celda : fila ) {
                celda.render(t);
            }
        }
    }

    public boolean masCasillas(TipoCasilla tipoCasilla, int posicion) {

        return(getFilas().get(posicion/10).masCasillas(tipoCasilla));
    }

    public void actualizarNumeroCasillas(TipoCasilla tipoCasilla, int posicion, int balance ) {

        getFilas().get(posicion/10).actualizarNumeroCasillas(tipoCasilla, balance);
    }

    public HashSet<TipoGrupo> getGruposSolaresLibres(int posicion) {

        int lado = posicion / 10;
        HashSet<TipoGrupo> resultado = new HashSet<>();

        // Lado sur
        if( lado == 0 ) {

            if(estaLibre(TipoGrupo.negro, 0)) {
                resultado.add(TipoGrupo.negro);
            }

            if(estaLibre(TipoGrupo.cyan, 0)) {
                resultado.add(TipoGrupo.cyan);
            }
        }

        // Lado oeste
        else if(lado == 1 ){

            if(estaLibre(TipoGrupo.rosa, 1)) {
                resultado.add(TipoGrupo.rosa);
            }

            if(estaLibre(TipoGrupo.naranja, 1)) {
                resultado.add(TipoGrupo.naranja);
            }
        }

        // Lado norte
        else if(lado == 2 ) {

            if(estaLibre(TipoGrupo.rojo, 2)) {
                resultado.add(TipoGrupo.rojo);
            }

            if(estaLibre(TipoGrupo.marron, 2)) {
                resultado.add(TipoGrupo.marron);
            }
        }

        // Lado este
        else {

            if(estaLibre(TipoGrupo.verde, 3)) {
                resultado.add(TipoGrupo.verde);
            }

            if(estaLibre(TipoGrupo.azul, 3)) {
                resultado.add(TipoGrupo.azul);
            }
        }

        return(resultado);
    }

    private boolean estaLibre(TipoGrupo tipoGrupo, int fila) {

        int total = 0;

        for( Celda celda : getCeldas().get(fila)) {

            // Si se ha creado una casilla en la celda
            if( celda.getCasillaGUI() != null ) {

                Casilla casilla = celda.getCasillaGUI().getCasilla();

                if( casilla instanceof Solar) {

                    if( ((Solar)casilla).getGrupo().getTipo().equals(tipoGrupo)) {
                        total++;
                    }
                }
            }
        }

        return(total < tipoGrupo.getTamano());
    }

    public boolean tableroValido() {

        for( ArrayList<Celda> fila : getCeldas() ) {

            for( Celda celda : fila ) {

                if(celda.getCasillaGUI() == null ) {
                    return(false);
                }
            }
        }

        return(true);
    }

    public ArrayList<InformacionCasillaGUI> exportarTablero() {

        ArrayList<InformacionCasillaGUI> informacion = new ArrayList<>();

        for( ArrayList<Celda> fila : getCeldas() ) {

            for( Celda celda : fila ) {
                informacion.add(celda.toInformacionCasillaGUI());
            }
        }

        return(informacion);
    }
}
