package aplicacionGUI;

import javafx.application.Application;
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

        AplicacionGUI aplicacionGUI = new AplicacionGUI(ventana);
        aplicacionGUI.iniciar();
    }
}
