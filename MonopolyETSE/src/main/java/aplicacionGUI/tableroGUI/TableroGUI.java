package aplicacionGUI.tableroGUI;

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
        
        int desplazamiento;
        
        for( ArrayList<CasillaGUI> filaGUI : getCasillasGUI() ) {
            
            for( CasillaGUI casillaGUI : filaGUI ) {
                casillaGUI.render(gc);
            }
        }  
    }
}
