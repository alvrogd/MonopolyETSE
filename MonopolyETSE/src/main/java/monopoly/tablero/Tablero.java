package monopoly.tablero;

import monopoly.Juego;
import monopoly.jugadores.Avatar;
import monopoly.Dado;
import monopoly.jugadores.Jugador;

import java.util.ArrayList;
import java.util.HashMap;

public class Tablero {

    /* Atributos */
    private Dado dado;

    //La banca
    private Jugador banca;

    //El juego actual
    private Juego juego;

    //ArrayList bidimensional para las casillas
    private ArrayList<ArrayList<Casilla>> casillas;

    //HashMap con las casillas, para acceder de forma directa a través de la clave.
    private HashMap<String, Casilla> casillasTablero;

    //HashMao con los grupos
    private HashMap<TipoGrupo, Grupo> grupos;

    //HashMap con los avatares que contiene el tablero
    private HashMap<Character, Avatar> avataresContenidos;


    /* Constructores */

    /**
     * Único constructor, se le pasa la banca y el juego para inicializar todos los atributos. Crea las casillas y los
     * grupos.
     *
     * @param banca el jugador que tomará el rol de banca en el juego
     * @param juego juego al que pertenecerá el tablero
     */
    public Tablero(Jugador banca, Juego juego) {

        if(banca == null){
            System.err.println("banca hace referencia a null");
            System.exit(1);
        }

        if(juego == null){
            System.err.println("juego hace referencia a null");
            System.exit(1);
        }

        //Se crea un nuevo dado
        this.dado = new Dado();

        this.banca = banca;
        this.juego = juego;

        this.casillas = new ArrayList<>();

        //Se inicializan las filas de casillas mediante un bucle for

        for(int i = 0; i < 4; i++){
            casillas.add(new ArrayList<Casilla>(10));
            for(int j = 0; j < 10; j++) {

                //Se inicializa a null los elementos del ArrayList para que cuando cada grupo cree sus casillas se
                //calcule de forma correcta el alquiler, que va en función del número de casillas por grupo.
                casillas.get(i).add(null);
            }
        }

        casillasTablero = new HashMap<String, Casilla>();

        grupos = new HashMap<>();

        //La función crearGrupos crea los correspondientes grupos y sus correspondientes casillas.
        crearGrupos();

        avataresContenidos = new HashMap<>();

    }


    /**
     * Función llamada por el constructor del tablero que lo que hace es inicializar cada grupo y las respectivas
     * casillas de este.
     */
    private void crearGrupos(){

        //Casillas negras

        ArrayList<Object> aux = new ArrayList<>();

        //Se añaden los nombres de las casillas en orden fila - posición
        aux.add(0); aux.add(1); aux.add("Platform 9 3/4");

        ArrayList<Object> aux2 = new ArrayList<>();
        aux2.add(0); aux2.add(3); aux2.add("Diagon Alley");

        //Se introduce el tipo de grupo, el tablero, si es comprable y los nombres de las casillas
        Grupo negro = new Grupo(TipoGrupo.negro, this, true, aux, aux2);
        this.grupos.put(TipoGrupo.negro, negro);


        //Casillas cyan

        aux = new ArrayList<>();
        aux.add(0); aux.add(6); aux.add("Godric's Hollow");

        aux2 = new ArrayList<>();
        aux2.add(0); aux2.add(8); aux2.add("Hogsmeade");

        ArrayList<Object> aux3 = new ArrayList<>();
        aux3.add(0); aux3.add(9); aux3.add("Hogwarts");

        Grupo cyan = new Grupo(TipoGrupo.cyan, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.cyan, cyan);


        //Casillas rosa

        aux = new ArrayList<>();
        aux.add(1); aux.add(1); aux.add("Tatooine");

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add(3); aux2.add("Alderaan");

        aux3 = new ArrayList<>();
        aux3.add(1); aux3.add(4); aux3.add("Coruscant");

        Grupo rosa = new Grupo(TipoGrupo.rosa, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.rosa, rosa);


        //Casillas naranja

        aux = new ArrayList<>();
        aux.add(1); aux.add(6); aux.add("Endor");

        aux2 = new ArrayList<>();
        aux2.add(1);aux2.add(8); aux2.add("Hoth");

        aux3 = new ArrayList<>();
        aux3.add(1); aux3.add(9); aux3.add("Mustafar");

        Grupo naranja = new Grupo(TipoGrupo.naranja, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.naranja, naranja);


        //Casillas rojo

        aux = new ArrayList<>();
        aux.add(2); aux.add(1); aux.add("Meereen");

        aux2 = new ArrayList<>();
        aux2.add(2); aux2.add(3); aux2.add("Casterly Rock");

        aux3 = new ArrayList<>();
        aux3.add(2); aux3.add(4); aux3.add("Braavos");

        Grupo rojo = new Grupo(TipoGrupo.rojo, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.rojo, rojo);


        //Casilla marron

        aux = new ArrayList<>();
        aux.add(2); aux.add(6); aux.add("Winterfell");

        aux2 = new ArrayList<>();
        aux2.add(2); aux2.add(7); aux2.add("Night's Watch");

        aux3 = new ArrayList<>();
        aux3.add(2); aux3.add(9); aux3.add("Beyond the Wall");

        Grupo marron = new Grupo(TipoGrupo.marron, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.marron, marron);


        //Casillas verde

        aux = new ArrayList<>();
        aux.add(3); aux.add(1); aux.add("The Shire");

        aux2 = new ArrayList<>();
        aux2.add(3); aux2.add(2); aux2.add("Moria");

        aux3 = new ArrayList<>();
        aux3.add(3); aux3.add(4); aux3.add("Helm's Deep");

        Grupo verde = new Grupo(TipoGrupo.verde, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.verde, verde);


        //Casilla azul

        aux = new ArrayList<>();
        aux.add(3); aux.add(7); aux.add("Isengard");

        aux2 = new ArrayList<>();
        aux2.add(3); aux2.add(9); aux2.add("Mordor");

        Grupo azul = new Grupo(TipoGrupo.azul, this, true, aux, aux2);
        this.grupos.put(TipoGrupo.azul, azul);


        //Salida

        aux = new ArrayList<>();
        aux.add(0); aux.add(0); aux.add("malloc(newGame)");

        Grupo salida = new Grupo(TipoGrupo.salida, this, false, aux);
        this.grupos.put(TipoGrupo.salida, salida);


        //Carcel

        aux = new ArrayList<>();
        aux.add(1); aux.add(0); aux.add("Azkaban");

        Grupo carcel = new Grupo(TipoGrupo.carcel, this, false, aux);
        this.grupos.put(TipoGrupo.carcel, carcel);


        //Parking

        aux = new ArrayList<>();
        aux.add(2); aux.add(0); aux.add("Death Star");

        Grupo parking = new Grupo(TipoGrupo.parking, this, false, aux);
        this.grupos.put(TipoGrupo.parking, parking);


        //irCarcel

        aux = new ArrayList<>();
        aux.add(3);  aux.add(0); aux.add("goto(Azkaban)");

        Grupo irCarcel = new Grupo(TipoGrupo.irCarcel, this, false, aux); //26
        this.grupos.put(TipoGrupo.irCarcel, irCarcel);


        //Transporte

        aux = new ArrayList<>();
        aux.add(0); aux.add(5); aux.add("Hogwarts Express");

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add(5); aux2.add("Imperial Destroyer");

        aux3 = new ArrayList<>();
        aux3.add(2); aux3.add(5); aux3.add("King's Landing");

        ArrayList<Object> aux4 = new ArrayList<>();
        aux4.add(3); aux4.add(5); aux4.add("Gwaihir");

        Grupo transporte = new Grupo(TipoGrupo.transporte, this, true, aux, aux2, aux3, aux4); //30
        this.grupos.put(TipoGrupo.transporte, transporte);


        //Casillas servicio

        aux = new ArrayList<>();
        aux.add(1); aux.add(2); aux.add("servicio1");

        aux2 = new ArrayList<>();
        aux2.add(2); aux2.add(8); aux2.add("servicio2");

        Grupo servicios = new Grupo(TipoGrupo.servicios, this, true, aux, aux2); //32
        this.grupos.put(TipoGrupo.servicios, servicios);


        //Impuesto tipo 1

        aux = new ArrayList<>();
        aux.add(0); aux.add(4); aux.add("Sméagol's Tax");

        Grupo impuestos1 = new Grupo(TipoGrupo.impuesto1, this, false, aux);
        this.grupos.put(TipoGrupo.impuesto1, impuestos1);


        //Impuesto tipo 2

        aux = new ArrayList<>();
        aux.add(3); aux.add(8); aux.add("Smaug's Tax");

        Grupo impuestos2 = new Grupo(TipoGrupo.impuesto2, this, false, aux);//34
        this.grupos.put(TipoGrupo.impuesto2, impuestos2);


        //Casillas suerte

        aux = new ArrayList<>();
        aux.add(0); aux.add(7); aux.add("suerte1");

        aux2 = new ArrayList<>();
        aux2.add(2); aux2.add(2); aux2.add("suerte2");

        aux3 = new ArrayList<>();
        aux3.add(3); aux3.add(6); aux3.add("suerte3");

        Grupo suerte = new Grupo(TipoGrupo.suerte, this, false, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.suerte, suerte);


        //Casillas comunidad

        aux = new ArrayList<>();
        aux.add(0); aux.add(2); aux.add("comunidad1");

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add(7); aux2.add("comunidad2");

        aux3 = new ArrayList<>();
        aux3.add(3); aux3.add(3); aux3.add("comunidad3");

        Grupo comunidad = new Grupo(TipoGrupo.comunidad, this, false, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.comunidad, comunidad);

    }

    /* Getters */
    public ArrayList<ArrayList<Casilla>> getCasillas() {
        return casillas;
    }

    public Juego getJuego() {
        return juego;
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

    public Dado getDado(){
        return dado;
    }

    public HashMap<TipoGrupo, Grupo> getGrupos() {
        return grupos;
    }
}