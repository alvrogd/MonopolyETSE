package aplicacionGUI;

import aplicacion.Aplicacion;
import aplicacion.excepciones.MonopolyETSEException;
import aplicacionGUI.menuGUI.MenuGUI;
import aplicacionGUI.informacion.Informacion;
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
        
        // Fondo de la ventana
        Image fondo = new Image(Fondo.class.getResource("fondo.jpg").toString());
        
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
            app.introducirComando("cambiar modo");

            // Se crea la sección superior de la GUI, encargada de representar información como el tablero del juego
            Informacion informacion = new Informacion(raiz, app.getJuego().getTablero());

            // Se crea la sección inferior de la GUI, encarga de ofrecer un menú al usuario
            MenuGUI menuGUI = new MenuGUI(raiz, app.getJuego(), "fondo.png", informacion.getTableroGUI());
            
            // Se añade texto de prueba al marco de información
            informacion.getMarcoInformacion().actualizarContenido(new String[]{
                "El Ministerio de Magia te investiga por colaboración con los mortífagos.",
                "Ve a Azkaban. Ve directamente sin pasar por la casilla de Salida y sin cobrar los 2M€.",
                "Y esto son más líneas de prueba para comprobar cómo se adapta el marco a distintos tamaños."});
            // Se activa
            informacion.getMarcoInformacion().setActivo(true);
            
            // Se define la acción ante un click derecho
            // todo lo pongo de este modo puesto que es más fácil de modificar para hacer pruebas; la intención sería
            // crear más adelante la clase aparte porque a Penín no le ha de de parecer buena idea
            escena.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

                @Override
                public void handle(ContextMenuEvent e ) {
                    
                    double x = e.getX();
                    double y = e.getY();

                    if( informacion.contienePosicion(x, y)) {
                        informacion.handleClickDerecho(x, y, raiz, e);
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
                    } else if(menuGUI.contienePosicion(x, y)){
                        //Solo en caso de que el botón presionado sea el primario (izquierdo)
                        if(e.getButton().equals(MouseButton.PRIMARY))
                            menuGUI.handleClickIzquierdo(x, y);
                    }
                }
            });

            //todo Lo pongo así por el tema de las inner classes, es la solución que me dio el intellij JAJAJA
            final double[] xPresionado = {0};
            final double[] yPresionado = {0};

            escena.setOnMousePressed(new EventHandler<MouseEvent>(){

                @Override
                public void handle( MouseEvent e ){

                    double x = e.getX();
                    double y = e.getY();

                    xPresionado[0] = x;
                    yPresionado[0] = y;

                    if(menuGUI.contienePosicion(x, y)){
                        menuGUI.handleClickPulsado(x, y);
                    }

                }
            });

            escena.setOnMouseReleased(new EventHandler<MouseEvent>(){

                @Override
                public void handle( MouseEvent e ){

                    //Se utiliza xPresionado para que en vez de detectar la acción en la posición donde se suelta el click
                    //lo detecte en la posición donde se empezó a presionar
                    if(menuGUI.contienePosicion(xPresionado[0], yPresionado[0])){
                        menuGUI.handleClickSoltado(xPresionado[0], yPresionado[0]);
                    }

                }
            });
            
            // Se registra el momento de inicio del juego
            final long tiempoInicio = System.nanoTime();

            // Se inicia el game loop
            new AnimationTimer() {

                @Override
                public void handle( long currentNanoTime ){
                    
                    // Tiempo que ha transcurrido desde el inicio del juego
                    double t = (currentNanoTime - tiempoInicio) / 1000000000.0;
                    
                    // Clear
                    gc.clearRect(0, 0, ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);

                    // Render
                    gc.drawImage(fondo, 0, 0);
                    informacion.render(t);
                    menuGUI.render();
                    
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
