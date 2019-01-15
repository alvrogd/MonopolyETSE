package aplicacionGUI.informacion.tableroGUI;

import aplicacionGUI.informacion.tableroGUI.casillaGUI.CasillaGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.PropiedadGUI;
import aplicacionGUI.informacion.tableroGUI.casillaGUI.SolarGUI;
import aplicacionGUI.ConstantesGUI;
import java.util.ArrayList;
import java.util.Collections;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.Solar;

public class TableroGUI {
    
    /* Atributos */
    
    // Nodo propiedad del tablero
    private final Group nodo;
    
    // Sensor asociado al tablero
    private final Rectangle sensor;
    
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
        
        // Se crea el sensor correspondiente
        this.sensor = new Rectangle(0, 0, ConstantesGUI.TABLERO_ANCHO, ConstantesGUI.TABLERO_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);
        
        
        // Se obtienen las casillas del tablero
        ArrayList<ArrayList<Casilla>> casillas = tablero.getCasillas();
        
        // Se obtienen las correspondientes posiciones de las casillas en la ventana
        ArrayList<int[]> posiciones = calcularPosiciones();
        
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
                
                if(casilla instanceof Propiedad) {
                
                    if( casilla instanceof Solar ) {
                        filaGUI.add(new SolarGUI(this.nodo, (Solar)casilla, ConstantesGUI.CASILLAS_IMAGENES[contador],
                                posiciones.get(contador)[0], posiciones.get(contador)[1]));
                    }
                    
                    else {
                        filaGUI.add(new PropiedadGUI(this.nodo, (Propiedad)casilla, ConstantesGUI.CASILLAS_IMAGENES[
                                contador], posiciones.get(contador)[0], posiciones.get(contador)[1]));
                    }
                }
                
                else {
                    filaGUI.add(new CasillaGUI(this.nodo, casilla, ConstantesGUI.CASILLAS_IMAGENES[contador],
                            posiciones.get(contador)[0], posiciones.get(contador)[1]));
                } 
                
                contador++;
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
           
    
    public ArrayList<ArrayList<CasillaGUI>> getCasillasGUI() {
        return casillasGUI;
    }
    
    
    
    /* Métodos */
    
    private ArrayList<int[]> calcularPosiciones() {
        
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
     
        return( posiciones );
    }
    
    
    private ArrayList<int[]> calcularPosicionesFila(boolean superior) {
        
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
        
        return( posiciones );
    }
    
    
    private ArrayList<int[]> calcularPosicionesColumna(boolean izquierda) {
        
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
            posicionX = (ConstantesGUI.CASILLAS_POR_LADO - 1) * ( ConstantesGUI.CASILLA_ANCHO - 3);
        }
        
        // Se calculan las correspondientes posiciones
        for (int i = 0; i < ConstantesGUI.CASILLAS_POR_LADO - 2; i++, posicionY += ConstantesGUI.CASILLA_ALTO - 3) {
            
            // Se guarda la posición
            posiciones.add(new int[]{posicionX, posicionY});

        }
        
        return( posiciones );
    }
    
    
    public boolean contieneClickDerecho(double x, double y) {
        
        double posicionX = x - ConstantesGUI.TABLERO_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.TABLERO_DESPLAZAMIENTO_Y;
        
        return(getSensor().contains(posicionX, posicionY));
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
        
        for( ArrayList<CasillaGUI> fila : getCasillasGUI() ) {
            
            for( CasillaGUI casillaGUI : fila ) {
                casillaGUI.render();
            }
        }
    }
}
