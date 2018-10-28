package monopoly;

import java.util.ArrayList;
import java.util.HashMap;

public class Tablero {

    /* Atributos */
    private Dado dado;

    private Jugador banca;

    private ArrayList<ArrayList<Casilla>> casillas;
    private HashMap<String, Casilla> casillasTablero;
    private HashMap<TipoGrupo,Grupo> grupos;

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
        Grupo negro = new Grupo(TipoGrupo.negro, banca, "Negro1","Negro2");
        grupos.put(TipoGrupo.negro, negro);
        Grupo cyan = new Grupo(TipoGrupo.azul, banca, "Cyan1","Cyan2","Cyan3");
        grupos.put(TipoGrupo.cyan, cyan);
        Grupo naranja = new Grupo(TipoGrupo.naranja, banca, "Naranja1","Naranja2", "Naranja3");
        grupos.put(TipoGrupo.naranja, naranja);
        Grupo rosa = new Grupo(TipoGrupo.rosa, banca, "Rosa1","Rosa2", "Rosa3");
        grupos.put(TipoGrupo.rosa, rosa);
        Grupo rojo = new Grupo(TipoGrupo.rojo, banca, "Rojo1","Rojo2", "Rojo3");
        grupos.put(TipoGrupo.rojo, rojo);
        Grupo verde = new Grupo(TipoGrupo.verde, banca, "Verde1", "Verde2", "Verde3");
        grupos.put(TipoGrupo.verde, verde);
        Grupo azul = new Grupo(TipoGrupo.azul, banca, "Azul1","Azul2)");
        grupos.put(TipoGrupo.azul, azul);
        Grupo marron = new Grupo(TipoGrupo.marron, banca, "Marron1", "Marron2", "Marron3");
        grupos.put(TipoGrupo.marron, marron);
        Grupo salida = new Grupo(TipoGrupo.salida, banca, "Salida");
        grupos.put(TipoGrupo.salida, salida);
        Grupo carcel = new Grupo(TipoGrupo.carcel, banca, "Carcel");
        grupos.put(TipoGrupo.carcel, carcel);
        Grupo parking = new Grupo(TipoGrupo.parking, banca, "Parking");
        grupos.put(TipoGrupo.parking, parking);
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

}