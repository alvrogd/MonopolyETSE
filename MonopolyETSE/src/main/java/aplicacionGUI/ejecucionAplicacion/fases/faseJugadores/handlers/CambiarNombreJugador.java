package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.handlers;

import aplicacionGUI.ejecucionAplicacion.fases.faseJugadores.BotonFase;
import aplicacionGUI.input.ILectorString;
import aplicacionGUI.input.InputString;
import javafx.event.ActionEvent;
import monopoly.jugadores.TipoAvatar;

import java.util.ArrayList;
import java.util.HashMap;

public class CambiarNombreJugador implements ILectorString {

    //HashMap indexado por el nombre del jugador obteniendo el TipoAvatar
    private HashMap<String, TipoAvatar> hash;

    //ArrayList donde se almacena el nombre leído
    private ArrayList<String> contenedor;

    //Botón que crea el input
    private BotonFase botonFase;
    

    public CambiarNombreJugador(HashMap<String, TipoAvatar> hash, ArrayList<String> contenedor, BotonFase botonFase) {
        if(hash == null){
            System.err.println("Hash no inicializado.");
            System.exit(1);
        }
        if(contenedor == null){
            System.err.println("Contenedor no inicializado.");
            System.exit(1);
        }
        if(botonFase == null){
            System.err.println("Botón fase no inicializado.");
            System.exit(1);
        }

        this.hash = hash;
        this.contenedor = contenedor;
        this.botonFase = botonFase;
    }

    public HashMap<String, TipoAvatar> getHash() {
        return hash;
    }

    public void setHash(HashMap<String, TipoAvatar> hash) {
        this.hash = hash;
    }

    public ArrayList<String> getContenedor() {
        return contenedor;
    }

    public void setContenedor(ArrayList<String> contenedor) {
        this.contenedor = contenedor;
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
        getHash().put(stringLeido, null);

        // En caso de que no esté vacío se elimina el primero, ya que la cuestión es almacenar solo un único valor
        if(!getContenedor().isEmpty())
            getContenedor().remove(0);

        getContenedor().add(stringLeido);

        // Como ya se ha leido el String, se ha finalizado la acción del botón
        getBotonFase().setFinAccion(true);
    }
}
