package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import java.util.ArrayList;
import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.jugadores.Banca;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;

public class PropiedadGUI extends CasillaGUI {

    /* Constructor */
    
    public PropiedadGUI(TableroGUI tableroGUI, Group raiz, Propiedad propiedad, String ficheroFondo, int posicionX,
            int posicionY) {

        super(tableroGUI, raiz, propiedad, ficheroFondo, posicionX, posicionY);
    }

    
    
    /* Métodos */
    
    public Propiedad getPropiedad() {

        return ((Propiedad) getCasilla());
    }
    
    
    @Override
    public void renderContenido(double t) {
        
        super.renderContenido(t);
        renderPropietario();
    }
    
    
    public void renderPropietario() {
        
        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.LEFT);
        
        // Si el propietario es la banca, se añade el precio de la casilla
        if( getPropiedad().getPropietario() instanceof Banca ) {
            
            getGc().fillText(getPropiedad().getPrecioActual()+ " K €", 5, 58 );
        }
        
        // En caso contrario, se indica el propietario
        else {
            
            // Se diferencia entre estar hipotecada o no
            if( getPropiedad().isHipotecada() ) {
                
                getGc().fillText("Hipot.: " + getPropiedad().getPropietario().getNombre(), 5, 58 );
            }
            
            else {
                
                getGc().fillText("Prop.: " + getPropiedad().getPropietario().getNombre(), 5, 58 );
            }     
        }
    }
    
    
    public ContextMenu generarMenuContextual() {
        
        // Se crea el menú de opciones para a partir del padre
        ContextMenu menu = super.generarMenuContextual();
                
        // Se obtienen las funciones propias a la casilla
        HashSet<TipoFuncion> funciones = getCasilla().funcionesARealizar();
        
        // Opciones a añadir al menú
        ArrayList<MenuItem> opciones = new ArrayList<>();
        
        if( funciones.contains(TipoFuncion.hipotecar)) {
            
            // Se añade la opción de hipotecar
            MenuItem item1 = new MenuItem( "Hipotecar" );
            item1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion hipotecar");
                }
            });
            
            opciones.add(item1);
        }
        
        if( funciones.contains(TipoFuncion.deshipotecar)) {
            
            // Se añade la opción de deshipotecar
            MenuItem item2 = new MenuItem( "Deshipotecar" );
            item2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle( ActionEvent event ) {
                    System.out.println("Escogida opcion deshipotecar");
                }
            });
            
            opciones.add(item2);
        }
        
        // Si se van a añadir nuevas opciones
        if( !opciones.isEmpty() ) {
            
            // Se añade un sepador
            menu.getItems().add(new SeparatorMenuItem());

            // Y se añaden las opciones
            menu.getItems().addAll(opciones);
        }
        
        return( menu );
    }
}
