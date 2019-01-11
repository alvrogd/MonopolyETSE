package aplicacionGUI;

import aplicacion.Aplicacion;
import aplicacion.excepciones.MonopolyETSEException;
import aplicacionGUI.tableroGUI.TableroGUI;
import aplicacionGUI.tableroGUI.casillaGUI.CasillaGUI;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alvrogd
 */
public class main extends Application {
    
    public static void main( String args[] ) {
        
        launch();
    }
    
    @Override
    public void start( Stage ventana ) {
        
        // Nombre de la ventana
        ventana.setTitle( "MonopolyETSE GUI Casilla" );
        
        // Se crea un nodo raíz
        Group raiz = new Group();
        // Se añade a una escena nueva
        Scene escena = new Scene( raiz );
        // Se añade la escena a la ventana
        ventana.setScene( escena );
        
        // Se crea un canvas en el que representar la GUI
        Canvas canvas = new Canvas( 1920, 1000 );
        
        // Se crea un entorno que manipular a partir del canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        Aplicacion app;
        
        try {
            
            app = new Aplicacion();
            app.getMenu().iniciarAplicacion();
            app.introducirComando("crear jugador alvaro coche");
            app.introducirComando("crear jugador fran pelota");
            app.introducirComando("iniciar");
            
            TableroGUI tableroGUI = new TableroGUI( app.getJuego().getTablero());
            CasillaGUI casillaGUI = new CasillaGUI(app.getJuego().getTablero().getCasillas().get(0).get(0), "Endor.png");

            // Se inicia el game loop
            new AnimationTimer() {

                @Override
                public void handle( long currentNanoTime ) {

                    // Render
                    //TableroGUI.render(gc);
                    CasillaGUI.render(gc);
                }
            }.start();

            // Se muestra la ventana
            ventana.show();
        }
        
        catch( MonopolyETSEException e ) {
            
        }
    }
}
