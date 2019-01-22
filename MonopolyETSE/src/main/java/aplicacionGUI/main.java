package aplicacionGUI;

import aplicacionGUI.editor.Editor;
import aplicacionGUI.input.Input;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

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

        AplicacionGUI aplicacionGUI = new AplicacionGUI(ventana);
        aplicacionGUI.iniciar();
    }

    private void probarJuego(Group raiz, Stage ventana, Scene escena, GraphicsContext gc, Image fondo, ArrayList<ContextMenu> menus) {

    }

    private void probarEditor(Group raiz, Stage ventana, Scene escena, GraphicsContext gc, Image fondo, ArrayList<ContextMenu> menus) {

        // Conjunto de inputs activos
        ArrayList<Input> inputsActivos = new ArrayList<>();
        Input.setInputsActivos(inputsActivos);

        // Raíz de los inputs
        Input.setRaiz(raiz);

        // Se crea un editor
        Editor editor = new Editor(raiz);

        final double[] xPresionado = {0};
        final double[] yPresionado = {0};

        escena.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle( MouseEvent e ){

                double x = e.getX();
                double y = e.getY();

                xPresionado[0] = x;
                yPresionado[0] = y;

                for( ContextMenu contextMenu : menus ) {
                    contextMenu.hide();
                }

                menus.clear();

                if(inputsActivos.size() > 0 ) {
                    if( inputsActivos.get(0).contienePosicion(xPresionado[0], yPresionado[0])){
                        System.out.println("contiene");
                        inputsActivos.get(0).handlePulsacion();
                    }
                }
            }
        });

        escena.setOnMouseReleased(new EventHandler<MouseEvent>(){

            @Override
            public void handle( MouseEvent e ){

                //Se utiliza xPresionado para que en vez de detectar la acción en la posición donde se suelta el click
                //lo detecte en la posición donde se empezó a presionar
                if(editor.contienePosicion(xPresionado[0], yPresionado[0])){
                    editor.handleClick(xPresionado[0], yPresionado[0], raiz, e, menus);
                }

                if(inputsActivos.size() > 0 ) {
                    if( inputsActivos.get(0).contienePosicion(xPresionado[0], yPresionado[0])){
                        System.out.println("contiene");
                        inputsActivos.get(0).handleRelease();
                    }
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
                editor.render(t);

                if(inputsActivos.size() > 0 ) {
                    inputsActivos.get(0).render();
                }
            }
        }.start();

        // Se muestra la ventana
        ventana.show();
    }
}
