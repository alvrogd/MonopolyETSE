package aplicacionGUI.tableroGUI;

import aplicacionGUI.ConstantesGUI;
import java.util.ArrayList;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import aplicacionGUI.tableroGUI.casillaGUI.*;
import javafx.scene.canvas.GraphicsContext;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.Solar;

public class TableroGUI {
    
    /* Atributos */
    
    private final ArrayList<ArrayList<CasillaGUI>> casillasGUI;
    
    
    
    /* Constructor */
    
    public TableroGUI( Tablero tablero ) {
        
        if( tablero == null ) {
            System.err.println("Tablero no inicializado");
            System.exit(1);
        }
        
        // Se obtienen las casillas del tablero
        ArrayList<ArrayList<Casilla>> casillas = tablero.getCasillas();
        
        // Se inicializa el array de representaciones
        this.casillasGUI = new ArrayList<>();
        
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
                        filaGUI.add(new SolarGUI((Solar)casilla, "BeyondTheWall.png"));
                    }
                    
                    else {
                        filaGUI.add(new PropiedadGUI((Propiedad)casilla, "BeyondTheWall.png"));
                    }
                }
                
                else {
                    filaGUI.add(new CasillaGUI(casilla, "BeyondTheWall.png"));
                } 
            }
        }
    }
    
    
    
    /* Getters y setters */
    
    public ArrayList<ArrayList<CasillaGUI>> getCasillasGUI( ) {
        return casillasGUI;        
    }

    
    
    /* Métodos */
    
    public void render(GraphicsContext gc) {
        
        // Se renderiza la fila superior
        //renderFila(true, gc);
        // La fila inferior
        renderFila(false, gc);
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
            posicionY = ((ConstantesGUI.CASILLAS_POR_LADO - 1) * ConstantesGUI.ALTO_CASILLA);
            posicionCasillaIterada = 10;
        }
        
        // Se comienzan a insertar las casillas desde la izquierda
        posicionX = 0;
        casillaIterada = getCasillasGUI().get(posicionCasillaIterada / ConstantesGUI.CASILLAS_POR_FILA).get(
                    posicionCasillaIterada % ConstantesGUI.CASILLAS_POR_FILA);

        // Se renderiza la casilla izquierda
        casillaIterada.render(gc, posicionX, posicionY);

        // Se suma el ancho de la casilla
        posicionX += ConstantesGUI.ANCHO_CASILLA;
        // En función de si es la fila superior o no, la casilla de la derecha será la siguiente o la anterior
        posicionCasillaIterada += superior ? 1 : -1;
        casillaIterada = getCasillasGUI().get(posicionCasillaIterada / ConstantesGUI.CASILLAS_POR_FILA).get(
                posicionCasillaIterada % ConstantesGUI.CASILLAS_POR_FILA);

        // Se insertan las casillas intermedias
        for (int i = 1; i < ConstantesGUI.CASILLAS_POR_FILA; i++) {
            casillaIterada.render(gc, posicionX, posicionY);

            posicionX += ConstantesGUI.ANCHO_CASILLA;
            posicionCasillaIterada += superior ? 1 : -1;
            casillaIterada = getCasillasGUI().get(posicionCasillaIterada / ConstantesGUI.CASILLAS_POR_FILA).get(
                    posicionCasillaIterada % ConstantesGUI.CASILLAS_POR_FILA);
        }

        // Se inserta la casilla derecha
        casillaIterada.render(gc, posicionX, posicionY);
    }
}
