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
        //Casillas negras

        ArrayList<Object> aux = new ArrayList<>();
        aux.add(1); aux.add("negro1");

        ArrayList<Object> aux2 = new ArrayList<>();
        aux2.add(3); aux2.add("negro2");

        Grupo negro = new Grupo(TipoGrupo.negro, this, true, aux, aux2);
        grupos.put(TipoGrupo.negro, negro);


        //Casillas cyan

        aux = new ArrayList<>();
        aux.add(6); aux.add("cyan1");

        aux2 = new ArrayList<>();
        aux2.add(8); aux2.add("cyan2");

        ArrayList<Object> aux3 = new ArrayList<>();
        aux3.add(9); aux3.add("cyan3");

        Grupo cyan = new Grupo(TipoGrupo.cyan, this, true, aux, aux2, aux3);
        grupos.put(TipoGrupo.cyan, cyan);


        //Casillas naranja

        aux = new ArrayList<>();
        aux.add(16); aux.add("naranja1");

        aux2 = new ArrayList<>();
        aux2.add(18); aux2.add("naranja2");

        aux3 = new ArrayList<>();
        aux3.add(19); aux3.add("naranja3");

        Grupo naranja = new Grupo(TipoGrupo.naranja, this, true, aux, aux2, aux3);
        grupos.put(TipoGrupo.naranja, naranja);


        //Casillas rosa

        aux = new ArrayList<>();
        aux.add(11); aux.add("rosa1");

        aux2 = new ArrayList<>();
        aux2.add(13); aux2.add("rosa2");

        aux3 = new ArrayList<>();
        aux3.add(14); aux3.add("rosa3");

        Grupo rosa = new Grupo(TipoGrupo.rosa, this, true, aux, aux2, aux3);
        grupos.put(TipoGrupo.rosa, rosa);


        //Casillas rojo

        aux = new ArrayList<>();
        aux.add(21); aux.add("rojo1");

        aux2 = new ArrayList<>();
        aux2.add(23); aux2.add("rojo2");

        aux3 = new ArrayList<>();
        aux3.add(24); aux3.add("rojo3");

        Grupo rojo = new Grupo(TipoGrupo.rojo, this, true, aux, aux2, aux3);
        grupos.put(TipoGrupo.rojo, rojo);


        //Casillas verde

        aux = new ArrayList<>();
        aux.add(0); aux.add("verde1");

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add("verde2");

        aux3 = new ArrayList<>();
        aux3.add(2); aux3.add("verde3");

        Grupo verde = new Grupo(TipoGrupo.verde, this, true, aux, aux2, aux3);
        grupos.put(TipoGrupo.verde, verde);


        //Casilla azul

        aux = new ArrayList<>();
        aux.add(0); aux.add("azul1");

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add("azul2");

        Grupo azul = new Grupo(TipoGrupo.azul, this, true, aux, aux2);
        grupos.put(TipoGrupo.azul, azul);


        //Casilla marron

        aux = new ArrayList<>();
        aux.add(26); aux.add("marron1");

        aux2 = new ArrayList<>();
        aux2.add(27); aux2.add("marron2");

        aux3 = new ArrayList<>();
        aux3.add(29); aux3.add("marron3");

        Grupo marron = new Grupo(TipoGrupo.marron, this, true, aux, aux2, aux3);
        grupos.put(TipoGrupo.marron, marron);


        //Salida

        aux = new ArrayList<>();
        aux.add(0); aux.add("salida");

        Grupo salida = new Grupo(TipoGrupo.salida, this, false, aux);
        grupos.put(TipoGrupo.salida, salida);


        //Carcel

        aux = new ArrayList<>();
        aux.add(10); aux.add("carcel");

        Grupo carcel = new Grupo(TipoGrupo.carcel, this, false, aux);
        grupos.put(TipoGrupo.carcel, carcel);


        //Parking

        aux = new ArrayList<>();
        aux.add(20); aux.add("parking");

        Grupo parking = new Grupo(TipoGrupo.parking, this, false, aux);
        grupos.put(TipoGrupo.parking, parking);


        //irCarcel

        aux = new ArrayList<>();
        aux.add(30); aux.add("irCarcel");

        Grupo irCarcel = new Grupo(TipoGrupo.irCarcel, this, false, aux); //26
        grupos.put(TipoGrupo.irCarcel, irCarcel);


        //Transporte

        aux = new ArrayList<>();
        aux.add(5); aux.add("transporte1");

        aux2 = new ArrayList<>();
        aux2.add(10); aux2.add("transporte2");

        aux3 = new ArrayList<>();
        aux3.add(15); aux3.add("transporte3");

        ArrayList<Object> aux4 = new ArrayList<>();
        aux4.add(20); aux4.add("transporte4");

        Grupo transporte = new Grupo(TipoGrupo.transporte, this, true, aux, aux2, aux3, aux4); //30
        grupos.put(TipoGrupo.transporte, transporte);


        //Casillas servicio

        aux = new ArrayList<>();
        aux.add(12); aux.add("servicio1");

        aux2 = new ArrayList<>();
        aux2.add(28); aux2.add("servicio2");

        Grupo servicios = new Grupo(TipoGrupo.servicios, this, true, aux, aux2); //32
        grupos.put(TipoGrupo.servicios, servicios);


        //Impuesto tipo 1

        aux = new ArrayList<>();
        aux.add(4); aux.add("impuesto1");

        Grupo impuestos1 = new Grupo(TipoGrupo.impuesto1, this, false, aux);
        grupos.put(TipoGrupo.impuesto1, impuestos1);


        //Impuesto tipo 2

        aux = new ArrayList<>();
        aux.add(38); aux.add("impuesto2");

        Grupo impuestos2 = new Grupo(TipoGrupo.impuesto2, this, false, aux);//34
        grupos.put(TipoGrupo.impuesto2, impuestos2);


        //Casillas suerte

        aux = new ArrayList<>();
        aux.add(7); aux.add("suerte1");

        aux2 = new ArrayList<>();
        aux2.add(22); aux2.add("suerte2");

        aux3 = new ArrayList<>();
        aux3.add(36); aux3.add("suerte3");

        Grupo suerte = new Grupo(TipoGrupo.suerte, this, false, aux, aux2, aux3);
        grupos.put(TipoGrupo.suerte, suerte);


        //Casillas comunidad

        aux = new ArrayList<>();
        aux.add(2); aux.add("comunidad1");

        aux2 = new ArrayList<>();
        aux2.add(17); aux2.add("comunidad2");

        aux3 = new ArrayList<>();
        aux3.add(33); aux3.add("comunidad3");

        Grupo comunidad = new Grupo(TipoGrupo.comunidad, this, false, aux, aux2, aux3);
        grupos.put(TipoGrupo.comunidad, comunidad);

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