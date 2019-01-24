package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.BotonFase;
import aplicacionGUI.input.ILectorString;
import aplicacionGUI.input.InputString;

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
        // En caso de que no esté vacío se elimina el primero, ya que la cuestión es almacenar solo un único valor
        getBotonFase().setNombreJugador(stringLeido);
        // Como ya se ha leido el String, se ha finalizado la acción del botón
        getBotonFase().setFinAccion(true);
        getBotonFase().setActivo(true);
    }
}
