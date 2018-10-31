package monopoly.tablero;

import monopoly.jugadores.Avatar;
import monopoly.Dado;
import monopoly.jugadores.Jugador;

import java.util.ArrayList;
import java.util.HashMap;

public class Tablero {

    /* Atributos */
    private Dado dado;

    private Jugador banca;

    private ArrayList<ArrayList<Casilla>> casillas;
    private HashMap<String, Casilla> casillasTablero;
    private HashMap<TipoGrupo, Grupo> grupos;

    private HashMap<Character, Avatar> avataresContenidos;



    /* Constructores */
    public Tablero(Jugador banca) {

        if(banca == null){
            System.err.println("banca hace referencia a null");
            System.exit(1);
        }

        dado = new Dado();

        this.banca = banca;

        casillas = new ArrayList<>();
        casillasTablero = new HashMap<String, Casilla>();

        grupos = new HashMap<>();
        crearGrupos();

        avataresContenidos = new HashMap<>();

    }

    private void crearGrupos(){
        //todo pasarle a grupo tablero para que meta casillas


        //Casillas negras

        ArrayList<Object> aux = new ArrayList<>();
        aux.add(0); aux.add("negro1");

        ArrayList<Object> aux2 = new ArrayList<>();
        aux2.add(1); aux2.add("negro2");

        Grupo negro = new Grupo(TipoGrupo.negro, banca, aux, aux2);
        grupos.put(TipoGrupo.negro, negro);


        //Casillas cyan

        aux = new ArrayList<>();
        aux.add(2); aux.add("cyan1");

        aux2 = new ArrayList<>();
        aux2.add(3); aux2.add("cyan2");

        ArrayList<Object> aux3 = new ArrayList<>();
        aux3.add(4); aux3.add("cyan3");

        Grupo cyan = new Grupo(TipoGrupo.cyan, banca, aux, aux2, aux3);
        grupos.put(TipoGrupo.cyan, cyan);


        //Casillas naranja

        aux = new ArrayList<>();
        aux.add(5); aux.add("naranja1");

        aux2 = new ArrayList<>();
        aux2.add(6); aux2.add("naranja2");

        aux3 = new ArrayList<>();
        aux3.add(7); aux3.add("naranja3");

        Grupo naranja = new Grupo(TipoGrupo.naranja, banca, aux, aux2, aux3);
        grupos.put(TipoGrupo.naranja, naranja);


        //Casillas rosa

        aux = new ArrayList<>();
        aux.add(8); aux.add("rosa1");

        aux2 = new ArrayList<>();
        aux2.add(9); aux2.add("rosa2");

        ArrayList<Object> aux3 = new ArrayList<>();
        aux3.add(10); aux3.add("rosa3");

        Grupo rosa = new Grupo(TipoGrupo.rosa, banca, aux, aux2, aux3);
        grupos.put(TipoGrupo.rosa, rosa);


        //Casillas rojo

        aux = new ArrayList<>();
        aux.add(11); aux.add("rojo1");

        aux2 = new ArrayList<>();
        aux2.add(12); aux2.add("rojo2");

        ArrayList<Object> aux3 = new ArrayList<>();
        aux3.add(13); aux3.add("rojo3");

        Grupo rojo = new Grupo(TipoGrupo.rojo, banca, aux, aux2, aux3);
        grupos.put(TipoGrupo.rojo, rojo);


        //Casillas verde

        aux = new ArrayList<>();
        aux.add(0); aux.add("verde1");

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add("verde2");

        ArrayList<Object> aux3 = new ArrayList<>();
        aux3.add(2); aux3.add("verde3");

        Grupo verde = new Grupo(TipoGrupo.verde, banca, aux, aux2, aux3);
        grupos.put(TipoGrupo.verde, verde);


        //Casilla azul

        aux = new ArrayList<>();
        aux.add(0); aux.add("azul1");

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add("azul2");

        Grupo azul = new Grupo(TipoGrupo.azul, banca, aux, aux2);
        grupos.put(TipoGrupo.azul, azul);


        //Casilla marron

        aux = new ArrayList<>();
        aux.add(0); aux.add("marron1");

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add("marron2");

        ArrayList<Object> aux3 = new ArrayList<>();
        aux3.add(2); aux3.add("marron3");

        Grupo marron = new Grupo(TipoGrupo.marron, banca, aux, aux2, aux3);
        grupos.put(TipoGrupo.marron, marron);


        //Salida

        aux = new ArrayList<>();
        aux.add(0); aux.add("salida");

        Grupo salida = new Grupo(TipoGrupo.salida, banca, aux);
        grupos.put(TipoGrupo.salida, salida);


        //Carcel

        aux = new ArrayList<>();
        aux.add(0); aux.add("carcel");

        Grupo carcel = new Grupo(TipoGrupo.carcel, banca, aux);
        grupos.put(TipoGrupo.carcel, carcel);


        //Parking

        aux = new ArrayList<>();
        aux.add(0); aux.add("parking");

        Grupo parking = new Grupo(TipoGrupo.parking, banca, aux);
        grupos.put(TipoGrupo.parking, parking);


        //irCarcel

        aux = new ArrayList<>();
        aux.add(0); aux.add("marron1");

        Grupo irCarcel = new Grupo(TipoGrupo.irCarcel, banca, "irCarcel"); //26
        grupos.put(TipoGrupo.irCarcel, irCarcel);
        Grupo transporte = new Grupo(TipoGrupo.transporte, banca, "T1","T2","T3","T4"); //30
        grupos.put(TipoGrupo.transporte, transporte);
        Grupo servicios = new Grupo(TipoGrupo.servicios, banca, "S1","S2"); //32
        grupos.put(TipoGrupo.servicios, servicios);
        Grupo impuestos1 = new Grupo(TipoGrupo.impuesto1, banca, "I1");
        grupos.put(TipoGrupo.impuesto1, impuestos1);
        Grupo impuestos2 = new Grupo(TipoGrupo.impuesto2, banca, "I2");//34
        grupos.put(TipoGrupo.impuesto2, impuestos2);
        Grupo suerte = new Grupo(TipoGrupo.suerte, banca, "S1","S2","S3");
        grupos.put(TipoGrupo.suerte, suerte);
        Grupo comunidad = new Grupo(TipoGrupo.comunidad, banca, "C1","C2","C3");
        grupos.put(TipoGrupo.comunidad, comunidad);

    }

    private void anadirCasillas(){
        casillas;
    }

    /* Getters */
    public ArrayList<ArrayList<Casilla>> getCasillas() {
        return casillas;
    }

    public HashMap<Character, Avatar> getAvataresContenidos() {
        return avataresContenidos;
    }

    public HashMap<String, Casilla> getCasillasTablero() {
        return casillasTablero;
    }

    public Jugador getBanca(){
        return banca;
    }

}