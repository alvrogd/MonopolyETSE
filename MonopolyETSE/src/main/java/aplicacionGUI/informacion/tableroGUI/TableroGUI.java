package aplicacionGUI.informacion.tableroGUI;

import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.PropiedadGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.SolarGUI;
import aplicacionGUI.ConstantesGUI;
import java.util.ArrayList;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.transform.Translate;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.Solar;

public class TableroGUI {
    
    /* Atributos */
    
    // Nodo propiedad del tablero
    private final Group nodo;
    
    // Canvas contenido en el nodo
    private final Canvas canvas;
    
    // Contexto en el que representar objetos
    private final GraphicsContext gc;
    
    // Representaciones de las casillas
    private final ArrayList<ArrayList<CasillaGUI>> casillasGUI;
    
    
    
    /* Constructor */
    
    public TableroGUI( Group raiz, Tablero tablero ) {
        
        if( raiz == null ) {
            System.err.println("Raíz no inicializada");
            System.exit(1);
        }
        
        if( tablero == null ) {
            System.err.println("Tablero no inicializado");
            System.exit(1);
        }
        
        // Se añade al nodo dado un nuevo nodo de uso para el tablero
        this.nodo = new Group();
        raiz.getChildren().add( this.nodo );
        
        // Se establece su correspondiente posición en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.TABLERO_DESPLAZAMIENTO_X,
                ConstantesGUI.TABLERO_DESPLAZAMIENTO_Y));
        
        // Se crea un canvas en el nuevo nodo para representar el tablero
        this.canvas = new Canvas( ConstantesGUI.TABLERO_ANCHO, ConstantesGUI.TABLERO_ALTO);
        this.nodo.getChildren().add(canvas);
        
        // Se genera un contexto a partir del canvas para insertar la representación del tablero
        this.gc = this.canvas.getGraphicsContext2D();
        
        
        // Se obtienen las casillas del tablero
        ArrayList<ArrayList<Casilla>> casillas = tablero.getCasillas();
        
        // Se inicializa el array de representaciones
        this.casillasGUI = new ArrayList<>();
        
        int contador = 0;
        
        // Se itera sobre cada fila
        for( ArrayList<Casilla> fila : casillas ) {
            
            // Se crea una fila para cada fila iterada
            ArrayList<CasillaGUI> filaGUI = new ArrayList<>();
            
            // Se añade la fila a las representaciones de las casillas
            this.casillasGUI.add(filaGUI);
            
            // Se itera sobre las casillas de la fila
            for( Casilla casilla : fila ) {
                
                if( casilla instanceof Propiedad) {
                
                    if( casilla instanceof Solar ) {
                        filaGUI.add(new SolarGUI((Solar)casilla, ConstantesGUI.CASILLAS_IMAGENES[contador]));
                    }
                    
                    else {
                        filaGUI.add(new PropiedadGUI((Propiedad)casilla, ConstantesGUI.CASILLAS_IMAGENES[contador]));
                    }
                }
                
                else {
                    filaGUI.add(new CasillaGUI(casilla, ConstantesGUI.CASILLAS_IMAGENES[contador]));
                } 
                
                contador++;
            }
        }
        
        
        // Se inicializan los sensores de las casillas
        initSensores();
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

    
    public ArrayList<ArrayList<CasillaGUI>> getCasillasGUI() {
        return casillasGUI;
    }
    
    
    
    /* Métodos */
    
    public final void initSensores() {
        
        // La fila superior
        initSensoresFila(true);
        // La fila inferior
        initSensoresFila(false);
        
        // Las columnas
        initSensoresColumnas();
    }
    
    
    private void initSensoresFila(boolean superior) {
        
        // Posición X en el canvas
        int posicionX;
        // Posición Y en el canvas
        int posicionY;
        // Posición de la casilla a iterar en el conjunto de estas
        int posicionCasillaIterada;
        // Representación de una casilla iterada
        CasillaGUI casillaIterada;

        // Si es la fila superior
        if (superior) {
            // Se comienza a insertar desde el inicio
            posicionY = 0;
            posicionCasillaIterada = 20;
        }
        
        // En caso contrario, es la fila inferior
        else {
            // Se salta desde el inicio la suma de todas las casillas de un lado menos la última
            posicionY = ((ConstantesGUI.CASILLAS_POR_LADO - 1) * ( ConstantesGUI.CASILLA_ALTO - 3));
            posicionCasillaIterada = 10;
        }
        
        // Se comienzan a insertar las casillas desde la izquierda
        posicionX = 0;
        casillaIterada = getCasillasGUI().get(posicionCasillaIterada / ConstantesGUI.CASILLAS_POR_FILA).get(
                    posicionCasillaIterada % ConstantesGUI.CASILLAS_POR_FILA);

        // Se inicializa el sensor de la casilla iterada
        casillaIterada.initSensor(getNodo(), posicionX, posicionY);

        // Se suma el ancho de la casilla
        posicionX += ConstantesGUI.CASILLA_ANCHO - 3;
        // En función de si es la fila superior o no, la casilla de la derecha será la siguiente o la anterior
        posicionCasillaIterada += superior ? 1 : -1;
        casillaIterada = getCasillasGUI().get(posicionCasillaIterada / ConstantesGUI.CASILLAS_POR_FILA).get(
                posicionCasillaIterada % ConstantesGUI.CASILLAS_POR_FILA);

        // Se insertan las casillas intermedias
        for (int i = 1; i < ConstantesGUI.CASILLAS_POR_FILA; i++) {
            casillaIterada.initSensor(getNodo(), posicionX, posicionY);

            posicionX += ConstantesGUI.CASILLA_ANCHO - 3;
            posicionCasillaIterada += superior ? 1 : -1;
            casillaIterada = getCasillasGUI().get(posicionCasillaIterada / ConstantesGUI.CASILLAS_POR_FILA).get(
                    posicionCasillaIterada % ConstantesGUI.CASILLAS_POR_FILA);
        }

        // Se inserta la casilla derecha
        casillaIterada.initSensor(getNodo(), posicionX, posicionY);
    }
    
    
    private void initSensoresColumnas() {
        
        // Se comienzan a insertar las columnas desde el límite que comparten con la fila superior
        int posicionX = 0;
        int posicionY = ConstantesGUI.CASILLA_ALTO - 3;
        // Representación de una casilla iterada
        CasillaGUI casillaIterada;

        // Cada iteración inserta una fila de las columnas
        for (int i = 9; i > 0; i--) {

            // Casilla izquierda

            // Las casillas izquierdas son insertadas de mayor a menor en función del orden del tablero; las derechas
            // son insertadas de menor a mayor
            casillaIterada = getCasillasGUI().get(1).get(i);
            casillaIterada.initSensor(getNodo(), posicionX, posicionY);


            // Casilla derecha

            // Se desplaza la posición en el número de casillas de un lado menos la última
            posicionX += ((ConstantesGUI.CASILLAS_POR_LADO - 1) * ( ConstantesGUI.CASILLA_ANCHO - 3));
            casillaIterada = getCasillasGUI().get(3).get(ConstantesGUI.CASILLAS_POR_FILA - i);
            casillaIterada.initSensor(getNodo(), posicionX, posicionY);

            // Se sitúa en la siguiente casilla izquierda a insertar
            posicionX = 0;
            posicionY += ConstantesGUI.CASILLA_ALTO - 3;
        }
    }
    
    
    public void handleClickDerecho(double x, double y) {
        
        double posicionX = x - ConstantesGUI.TABLERO_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.TABLERO_DESPLAZAMIENTO_Y;
        
        System.out.println("x es " + posicionX);
        System.out.println("y es " + posicionY);
        
        // Se comprueba cada una de las representaciones de las casillas
        for( ArrayList<CasillaGUI> fila : getCasillasGUI() ) {
            
            for( CasillaGUI casillaGUI : fila ) {
            
                if( casillaGUI.contieneClickDerecho(posicionX, posicionY)) {
                    casillaGUI.handleClickDerecho(posicionX, posicionY);
                }
            }
        }
    }
    
    
    public void render() {
        
        // Se renderiza la fila superior
        renderFila(true, getGc());
        // Se renderiza la fila inferior
        renderFila(false, getGc());
        
        // Se renderizan las columnas
        renderColumnas(getGc());
    }
    
    
    private void renderFila(boolean superior, GraphicsContext gc) {

        // Posición X en el canvas
        int posicionX;
        // Posición Y en el canvas
        int posicionY;
        // Posición de la casilla a iterar en el conjunto de estas
        int posicionCasillaIterada;
        // Representación de una casilla iterada
        CasillaGUI casillaIterada;

        // Si es la fila superior
        if (superior) {
            // Se comienza a insertar desde el inicio
            posicionY = 0;
            posicionCasillaIterada = 20;
        }
        
        // En caso contrario, es la fila inferior
        else {
            // Se salta desde el inicio la suma de todas las casillas de un lado menos la última
            posicionY = ((ConstantesGUI.CASILLAS_POR_LADO - 1) * ( ConstantesGUI.CASILLA_ALTO - 3));
            posicionCasillaIterada = 10;
        }
        
        // Se comienzan a insertar las casillas desde la izquierda
        posicionX = 0;
        casillaIterada = getCasillasGUI().get(posicionCasillaIterada / ConstantesGUI.CASILLAS_POR_FILA).get(
                    posicionCasillaIterada % ConstantesGUI.CASILLAS_POR_FILA);

        // Se renderiza la casilla izquierda
        casillaIterada.render(gc, posicionX, posicionY);

        // Se suma el ancho de la casilla
        posicionX += ConstantesGUI.CASILLA_ANCHO - 3;
        // En función de si es la fila superior o no, la casilla de la derecha será la siguiente o la anterior
        posicionCasillaIterada += superior ? 1 : -1;
        casillaIterada = getCasillasGUI().get(posicionCasillaIterada / ConstantesGUI.CASILLAS_POR_FILA).get(
                posicionCasillaIterada % ConstantesGUI.CASILLAS_POR_FILA);

        // Se insertan las casillas intermedias
        for (int i = 1; i < ConstantesGUI.CASILLAS_POR_FILA; i++) {
            casillaIterada.render(gc, posicionX, posicionY);

            posicionX += ConstantesGUI.CASILLA_ANCHO - 3;
            posicionCasillaIterada += superior ? 1 : -1;
            casillaIterada = getCasillasGUI().get(posicionCasillaIterada / ConstantesGUI.CASILLAS_POR_FILA).get(
                    posicionCasillaIterada % ConstantesGUI.CASILLAS_POR_FILA);
        }

        // Se inserta la casilla derecha
        casillaIterada.render(gc, posicionX, posicionY);
    }
    
    
    private void renderColumnas(GraphicsContext gc) {

        // Se comienzan a insertar las columnas desde el límite que comparten con la fila superior
        int posicionX = 0;
        int posicionY = ConstantesGUI.CASILLA_ALTO - 3;
        // Representación de una casilla iterada
        CasillaGUI casillaIterada;

        // Cada iteración inserta una fila de las columnas
        for (int i = 9; i > 0; i--) {

            // Casilla izquierda

            // Las casillas izquierdas son insertadas de mayor a menor en función del orden del tablero; las derechas
            // son insertadas de menor a mayor
            casillaIterada = getCasillasGUI().get(1).get(i);
            casillaIterada.render(gc, posicionX, posicionY);

            // Casilla derecha

            // Se desplaza la posición en el número de casillas de un lado menos la última
            posicionX += ((ConstantesGUI.CASILLAS_POR_LADO - 1) * ( ConstantesGUI.CASILLA_ANCHO - 3));
            casillaIterada = getCasillasGUI().get(3).get(ConstantesGUI.CASILLAS_POR_FILA - i);
            casillaIterada.render(gc, posicionX, posicionY);

            // Se sitúa en la siguiente casilla izquierda a insertar
            posicionX = 0;
            posicionY += ConstantesGUI.CASILLA_ALTO - 3;
        }
    }
}
