package aplicacionGUI.ejecucionAplicacion.fases.faseJugadores;

import aplicacionGUI.ejecucionAplicacion.AplicacionGUI;
import aplicacionGUI.ejecucionAplicacion.Fase;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class FaseJugador extends Fase {

    // ArrayList con los botones que pueden aparecer
    private ArrayList<BotonFase> botones;

    // ArrayList con los botones actuales a mostrar en pantalla
    private ArrayList<BotonFase> botonesActuales;

    // HashMap donde se almacena un ArrayList de botones a los que se accede a partir de una función raíz
    private HashMap<TipoFuncionFase, ArrayList<BotonFase>> botonesPagina;

    public FaseJugador(AplicacionGUI aplicacionGUI){
        super(aplicacionGUI, "introducirJugadores.png");
        this.botones = new ArrayList<>();
        this.botonesActuales = new ArrayList<>();
        this.botonesPagina = new HashMap<>();
    }

    public void crearBotones(){
        for(TipoFuncionFase funcion : TipoFuncionFase.values()){

            BotonFase boton = new BotonFase(this, getRaiz(), funcion.toString(), funcion, funcion.getPosicionX(), funcion.getPosicionY());

            getBotones().add(boton);

            if(funcion.isPagina()){
                // En caso de que no contenga la clave se crea el ArrayList
                if(!getBotonesPagina().containsKey(funcion.getFuncionRaiz())){
                    getBotonesPagina().put(funcion.getFuncionRaiz(), new ArrayList<>());
                }

                getBotonesPagina().get(funcion.getFuncionRaiz()).add(boton);

            }
        }
    }

    public HashMap<TipoFuncionFase, ArrayList<BotonFase>> getBotonesPagina() {
        return botonesPagina;
    }

    public void setBotonesPagina(HashMap<TipoFuncionFase, ArrayList<BotonFase>> botonesPagina) {
        this.botonesPagina = botonesPagina;
    }

    public ArrayList<BotonFase> getBotones() {
        return botones;
    }

    public void setBotones(ArrayList<BotonFase> botones) {
        this.botones = botones;
    }

    public ArrayList<BotonFase> getBotonesActuales() {
        return botonesActuales;
    }

    public void setBotonesActuales(ArrayList<BotonFase> botonesActuales) {
        this.botonesActuales = botonesActuales;
    }

    public void iniciar(){
        setIniciado(true);

        // Se busca el botón de añadir jugadores y se inicia
        for(BotonFase boton : getBotones()){
            if(boton.getFuncion().equals(TipoFuncionFase.anadirJugador)){
                boton.setActivo(true);
            }
        }
    }

    public void finalizar(){

        // Este método para que no haya problemas debería poner todos los botones con su activo a false.
        /**
         * for(BotonFase boton : getBotones()){
         *      boton.setActivo(false);
         * }
         */

    }

    public void actualizarBotones(){

        for(BotonFase boton : getBotones()){

            setBotonesActuales(new ArrayList<>());

            if(boton.isActivo()){
                getBotonesActuales().add(boton);
            }

        }

    }

    public void render(double t){

        actualizarBotones();

        for(BotonFase boton : getBotonesActuales()){
            boton.render();
        }

    }

    public void clear(){

    }

}
