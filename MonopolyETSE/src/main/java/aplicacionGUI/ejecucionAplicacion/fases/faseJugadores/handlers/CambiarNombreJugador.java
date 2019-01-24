package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.BotonFase;
import aplicacionGUI.input.ILectorString;
import aplicacionGUI.input.InputString;
import monopoly.jugadores.TipoAvatar;

import java.util.ArrayList;
import java.util.HashMap;

public class CambiarNombreJugador implements ILectorString {

    //Botón que crea el input
    private BotonFase botonFase;
    

    public CambiarNombreJugador(BotonFase botonFase) {
        if(botonFase == null){
            System.err.println("Botón fase no inicializado.");
            System.exit(1);
        }

        this.botonFase = botonFase;
        new InputString(false, 0, this);
    }

    public BotonFase getBotonFase() {
        return botonFase;
    }

    public void setBotonFase(BotonFase botonFase) {
        this.botonFase = botonFase;
    }

    /* Métodos */

    @Override
    public void almacenarString(String stringLeido, int identificadorAtributo) {
        Integer repeticiones = 0;
        // En caso de que no esté vacío se elimina el primero, ya que la cuestión es almacenar solo un único valor

        if(getBotonFase().getFaseJugador().getAplicacionGUI().getJugadoresCreados().containsKey(stringLeido)){
            stringLeido += repeticiones;
        }

        while(getBotonFase().getFaseJugador().getAplicacionGUI().getJugadoresCreados().containsKey(stringLeido)) {
                // Si el nombre ya existe se determina uno al azar
                char[] arrayChar = stringLeido.toCharArray();

                //Se borra el último carácter
                arrayChar[arrayChar.length-1] = ' ';

                stringLeido = "";

                //Como se ha borrado el carácter del final, este no se coge
                for(int i = 0; i < arrayChar.length-1; i++){
                    stringLeido += arrayChar[i];
                }

                stringLeido += repeticiones.toString();

                repeticiones++;
        }
        getBotonFase().setNombreJugador(stringLeido);
        // Como ya se ha leido el String, se ha finalizado la acción del botón
        getBotonFase().setFinAccion(true);
        getBotonFase().setActivo(true);
    }
}
