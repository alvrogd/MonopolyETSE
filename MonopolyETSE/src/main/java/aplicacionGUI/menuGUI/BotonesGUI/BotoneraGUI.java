package aplicacionGUI.menuGUI.BotonesGUI;

import aplicacionGUI.ConstantesGUI;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import monopoly.Juego;

import java.util.ArrayList;

public class BotoneraGUI {

    /* Atributos */

    // Nodo propiedad de la sección de la botonera
    private final Group nodo;

    // Sensor asociado a esta sección
    private Rectangle sensor;

    // Juego
    private Juego juego;

    // Botones que están en la botonera
    private ArrayList<BotonGUI> botones;

    /* Constructor */
    public BotoneraGUI(Group raiz, Juego juego){

        if(raiz == null){
            System.err.println("Raiz no inicializada");
            System.exit(1);
        }

        if(juego == null){
            System.err.println("Juego no inicializado");
            System.exit(1);
        }

        // Se añade el nodo
        this.nodo = new Group();
        raiz.getChildren().add(this.nodo);

        // Se establece su correspondiente posición en la ventana
        this.nodo.getTransforms().add(new Translate(ConstantesGUI.BOTONES_DESPLAZAMIENTO_X, ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y));

        // Se crea el correspondiente sensor
        this.sensor = new Rectangle(0, 0, ConstantesGUI.BOTONES_ANCHO, ConstantesGUI.BOTONES_ALTO);
        this.sensor.setFill(Color.TRANSPARENT);

        this.botones = new ArrayList<>();

        nuevoBoton("finalizarTurno");
        nuevoBoton("avanzar");

    }

    public void nuevoBoton(String nombre){

        nuevoBoton(nombre, false, false);

    }

    public void nuevoBoton(String nombre, boolean animado, boolean ayuda){

        int columna = getBotones().size() / ConstantesGUI.BOTONES_POR_FILA;
        int fila = getBotones().size() % ConstantesGUI.BOTONES_POR_FILA;

        getBotones().add(new BotonGUI(getNodo(), nombre, fila, columna, animado, ayuda));

    }

    public Group getNodo() {
        return nodo;
    }

    public Rectangle getSensor() {
        return sensor;
    }

    public Juego getJuego() {
        return juego;
    }

    public ArrayList<BotonGUI> getBotones() {
        return botones;
    }

    public boolean contienePosicion(double x, double y) {

        double posicionX = x - ConstantesGUI.BOTONES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y;

        return(getSensor().contains(posicionX, posicionY));
    }

    public void handleClickIzquierdo(double x, double y) {

        double posicionX = x - ConstantesGUI.BOTONES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y;

        for(BotonGUI botonGUI : getBotones()){
            if(botonGUI.contienePosicion(posicionX, posicionY)){
                botonGUI.handleClickIzquierdo(posicionX, posicionY);
                break;
            }
        }
    }

    public void handleClickPulsado(double x, double y) {

        double posicionX = x - ConstantesGUI.BOTONES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y;

        for(BotonGUI botonGUI : getBotones()){
            if(botonGUI.contienePosicion(posicionX, posicionY)){
                botonGUI.handleClickPulsado(posicionX, posicionY);
                break;
            }
        }
    }

    public void handleClickSoltado(double x, double y) {

        double posicionX = x - ConstantesGUI.BOTONES_DESPLAZAMIENTO_X;
        double posicionY = y - ConstantesGUI.BOTONES_DESPLAZAMIENTO_Y;

        for(BotonGUI botonGUI : getBotones()){
            if(botonGUI.contienePosicion(posicionX, posicionY)){
                botonGUI.handleClickSoltado(posicionX, posicionY);
                break;
            }
        }
    }


    public void render(){

        for(BotonGUI botonGUI : getBotones()){
            botonGUI.render();
        }

    }

  }
