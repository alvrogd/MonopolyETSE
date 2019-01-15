package aplicacionGUI;

import aplicacion.Aplicacion;
import aplicacion.excepciones.MonopolyETSEException;
import aplicacionGUI.informacion.Informacion;
import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.fondo.Fondo;

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
                        
        // Se crea un canvas en el que representar la GUI y se añade a la raíz
        // todo al final creo que este canvas será completamente innecesario si la parte de arriba y la de abajo tienen
        // sus respectivos canvas
        Canvas canvas = new Canvas( ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO );
        raiz.getChildren().add(canvas);
        
        // Se crea un entorno que manipular a partir del canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Se imprime el fondo de la GUI
        gc.drawImage(new Image(Fondo.class.getResource("fondo.jpg").toString()), 0, 0);

        // Se crea una aplicación Monopoly
        Aplicacion app;
        
        try {
            
            app = new Aplicacion();
            //app.getMenu().iniciarAplicacion();
            app.introducirComando("crear jugador alvaro coche");
            app.introducirComando("crear jugador fran pelota");
            app.introducirComando("iniciar");
            app.introducirComando("pasta 100000");
            app.introducirComando("mover 1");
            app.introducirComando("comprar Platform 9 3/4");
            app.introducirComando("mover 2");
            app.introducirComando("comprar Diagon Alley");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar hotel");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar hotel");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar casa");
            app.introducirComando("edificar pista");
            app.introducirComando("edificar piscina");

            // Se crea la sección superior de la GUI, encargada de representar información como el tablero del juego
            Informacion informacion = new Informacion(raiz, app.getJuego().getTablero());
            
            // Se define la acción ante un click derecho
            // todo lo pongo de este modo puesto que es más fácil de modificar para hacer pruebas
            escena.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

                @Override
                public void handle(ContextMenuEvent e ) {
                    
                    double x = e.getX();
                    double y = e.getY();

                    if( informacion.contienePosicion(x, y)) {
                        informacion.handleClickDerecho(x, y);
                    }
                }
            });
            
            // Se define la acción ante un click izquierdo
            escena.setOnMouseClicked(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle( MouseEvent e ) {
                    
                    double x = e.getX();
                    double y = e.getY();

                    if( informacion.contienePosicion(x, y)) {
                        informacion.handleClickIzquierdo(x, y);
                    }
                }
            });

            // Se inicia el game loop
            new AnimationTimer() {

                @Override
                public void handle( long currentNanoTime ) {

                    // Render
                    informacion.render();
                    
                }
            }.start();

            // Se muestra la ventana
            ventana.show();
        }
        
        catch( MonopolyETSEException e ) {
            e.toString();
        }
    }
}
