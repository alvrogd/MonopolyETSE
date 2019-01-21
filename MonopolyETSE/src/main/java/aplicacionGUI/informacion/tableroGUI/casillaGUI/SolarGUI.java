package aplicacionGUI.informacion.tableroGUI.casillaGUI;

import aplicacion.Aplicacion;
import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.informacion.tableroGUI.TableroGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import monopoly.tablero.jerarquiaCasillas.Solar;
import monopoly.tablero.jerarquiaCasillas.TipoFuncion;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;
import resources.edificios.ImagenesEdificios;

import java.util.ArrayList;
import java.util.HashSet;

public class SolarGUI extends PropiedadGUI {
    
    /* Atributos */
    
    public static final Image CASA = new Image(ImagenesEdificios.class.getResource(ConstantesGUI.CASA).toString());
    public static final Image HOTEL = new Image(ImagenesEdificios.class.getResource(ConstantesGUI.HOTEL).toString());
    public static final Image PISCINA = new Image(ImagenesEdificios.class.getResource(ConstantesGUI.PISCINA).toString());
    public static final Image PISTA = new Image(ImagenesEdificios.class.getResource(ConstantesGUI.PISTA).toString());

    
    
    /* Constructor */
    
    public SolarGUI(TableroGUI tableroGUI, Group raiz, Solar solar, String ficheroFondo, int posicionX, int posicionY) {

        super(tableroGUI, raiz, solar, ficheroFondo, posicionX, posicionY);
    }

    
    
    /* Getters y setters */
    
    public Solar getSolar() {

        return ((Solar) getCasilla());
    }

    
    public static Image getCASA() {
        return CASA;
    }

    
    public static Image getHOTEL() {
        return HOTEL;
    }

    
    public static Image getPISCINA() {
        return PISCINA;
    }

    
    public static Image getPISTA() {
        return PISTA;
    }
    
    
    
    /* Métodos */
    
    @Override
    public void renderContenido(double t) {
        
        super.renderContenido(t);
        renderEdificiosContenidos();
    }
    
    
    public void renderEdificiosContenidos() {
        
        // Se establece la tipografía
        getGc().setFont(Font.font("Cousine Nerd Font", FontWeight.NORMAL, 12));
        getGc().setStroke(Color.TRANSPARENT);
        getGc().setFill(Color.BLACK);
        getGc().setTextAlign(TextAlignment.LEFT);
        
        // Se añaden los edificos contenidos
        int desplazamiento = 0;
        
        // Para cada tipo de edificio
        for (TipoEdificio tipoEdificio : getSolar().getEdificiosContenidos().keySet()) {
            
            // El número de edificios contenidos del tipo iterado
            int numeroEdificios = getSolar().getEdificiosContenidos().get(tipoEdificio).size();
            
            // Se obtiene la imagen apropiada para el tipo de edificio iterado
            Image imagen = null;
            
            switch( tipoEdificio ) {
                
                case casa:
                    imagen = getCASA();
                    break;
                    
                case hotel:
                    imagen = getHOTEL();
                    break;
                    
                case piscina:
                    imagen = getPISCINA();
                    break;
                    
                case pistaDeporte:
                    imagen = getPISTA();
                    break;
            }
            
            // Se añade un identificador por cada edificio contenido del tipo iterado
            for( int i = 0; i < numeroEdificios; i++ ) {
                
                getGc().drawImage( imagen, 4 + desplazamiento, 37 );
                desplazamiento += 10;
            }
        }
    }
    
    
    public ContextMenu generarMenuContextual(Aplicacion app) {
        
        // Se crea el menú de opciones para a partir del padre
        ContextMenu menu = super.generarMenuContextual(app);
        
        // Se obtienen las funciones propias a la casilla
        HashSet<TipoFuncion> funciones = getCasilla().funcionesARealizar();
        
        // Opciones a añadir al menú
        ArrayList<MenuItem> opciones = new ArrayList<>();
        
        if( funciones.contains(TipoFuncion.vender)) {
            
            // Se crea un submenú para las opciones de vender edificios
            Menu item1 = new Menu( "Vender edificicaciones" );
            
            if( funciones.contains(TipoFuncion.venderCasa)) {
                
                // Se añade la opción para vender casas
                MenuItem item2 = new MenuItem( "Casas" );
                item2.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle( ActionEvent event ) {
                        int cantidad = 1;
                        try {
                            app.getJuego().getTurno().venderEdificio(TipoEdificio.casa, cantidad, getSolar());
                        } catch (Exception ignored) {

                        }
                    }
                });
                
                item1.getItems().add(item2);
            }
            
            if( funciones.contains(TipoFuncion.venderHotel)) {
                
                // Se añade la opción para vender hoteles
                MenuItem item3 = new MenuItem( "Hoteles" );
                item3.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle( ActionEvent event ) {
                        int cantidad = 1;
                        try {
                            app.getJuego().getTurno().venderEdificio(TipoEdificio.hotel, cantidad, getSolar());
                        } catch (Exception ignored) {

                        }
                    }
                });
                
                item1.getItems().add(item3);
            }
            
            if( funciones.contains(TipoFuncion.venderPiscina)) {
                
                // Se añade la opción para vender piscinas
                MenuItem item4 = new MenuItem( "Piscinas" );
                item4.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle( ActionEvent event ) {
                        int cantidad = 1;
                        try {
                            app.getJuego().getTurno().venderEdificio(TipoEdificio.piscina, cantidad, getSolar());
                        } catch (Exception ignored) {

                        }
                    }
                });
                
                item1.getItems().add(item4);
            }
            
            if( funciones.contains(TipoFuncion.venderPista)) {
                
                // Se añade la opción para vender hoteles
                MenuItem item5 = new MenuItem( "Pistas" );
                item5.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle( ActionEvent event ) {
                        int cantidad = 1;
                        try {
                            app.getJuego().getTurno().venderEdificio(TipoEdificio.pistaDeporte, cantidad, getSolar());
                        } catch (Exception ignored) {

                        }
                    }
                });
                
                item1.getItems().add(item5);
            }
            
            // Se añade el submenú si contiene alguna opción
            if( !item1.getItems().isEmpty()) {
                opciones.add(item1);
            }
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
