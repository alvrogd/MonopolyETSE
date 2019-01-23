package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores;

import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.Fase;
import javafx.scene.shape.Rectangle;

public class FaseJugador extends Fase {



    public FaseJugador(AplicacionGUI aplicacionGUI){
        super(aplicacionGUI, "introducirJugadores.png");
    }

    public void iniciar(){
        setIniciado(true);
    }

    public void render(double t){

    }

    public void clear(){

    }

}
