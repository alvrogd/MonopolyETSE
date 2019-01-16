package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacionGUI.informacion.tableroGUI.TableroGUI;
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
        
        // Se añade la opción de hipotecar
        MenuItem item1 = new MenuItem( "Hipotecar" );
        item1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle( ActionEvent event ) {
                System.out.println("Escogida opcion hipotecar");
            }
        });
        
        // Se añade la opción de deshipotecar
        MenuItem item2 = new MenuItem( "Deshipotecar" );
        item2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle( ActionEvent event ) {
                System.out.println("Escogida opcion deshipotecar");
            }
        });
        
        // Se añaden los items junto con un separador
        menu.getItems().addAll(new SeparatorMenuItem(), item1, item2);
        
        return( menu );
    }
}
