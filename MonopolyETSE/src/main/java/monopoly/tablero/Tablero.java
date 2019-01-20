package monopoly.tablero;

import monopoly.Constantes;
import monopoly.Juego;
import monopoly.jugadores.Avatar;
import monopoly.Dado;
import monopoly.jugadores.Banca;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Grupo;
import monopoly.tablero.jerarquiaCasillas.Impuesto;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.*;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;

import java.util.ArrayList;
import java.util.HashMap;

public class Tablero {

    /* Atributos */
    private Dado dado;

    //La banca
    private Banca banca;

    //El juego actual
    private Juego juego;

    //ArrayList bidimensional para las casillas
    private ArrayList<ArrayList<Casilla>> casillas;

    //HashMap con las casillas, para acceder de forma directa a través de la clave.
    private HashMap<String, Casilla> casillasTablero;

    //HashMap con los grupos
    private HashMap<TipoGrupo, Grupo> grupos;

    //HashMap con los avatares que contiene el tablero
    private HashMap<Character, Avatar> avataresContenidos;

    //HashMap que contiene el numero de edificios que contiene el tablero de un mismo tipo
    private HashMap<TipoEdificio, Integer> numEdificios;




    /* Constructores */

    public Tablero() {
    }

    /**
     * Único constructor, se le pasa la banca y el juego para inicializar todos los atributos. Crea las casillas y los
     * grupos.
     *
     * @param banca el jugador que tomará el rol de banca en el juego
     * @param juego juego al que pertenecerá el tablero
     */
    public Tablero(Banca banca, Juego juego) {

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

        numEdificios = new HashMap<>();

        for(TipoEdificio aux : TipoEdificio.values()){
            numEdificios.put(aux, 0);
        }

        //La función crearGrupos crea los correspondientes grupos y sus correspondientes casillas.
        crearGrupos();

        avataresContenidos = new HashMap<>();

    }

    public HashMap<TipoEdificio, Integer> getNumEdificios() {
        return numEdificios;
    }

    /**
     * Función llamada por el constructor del tablero que lo que hace es inicializar cada grupo y las respectivas
     * casillas de este.
     */
    private void crearGrupos(){

        //Casillas negras

        ArrayList<Object> aux = new ArrayList<>();

        //Se añaden los nombres de las casillas en orden fila - posición
        aux.add(0); aux.add(1); aux.add(Constantes.NOMBRE_NEGRO_1);

        ArrayList<Object> aux2 = new ArrayList<>();
        aux2.add(0); aux2.add(3); aux2.add(Constantes.NOMBRE_NEGRO_2);

        //Se introduce el tipo de grupo, el tablero, si es comprable y los nombres de las casillas
        Grupo negro = new Grupo(TipoGrupo.negro, this, true, aux, aux2);
        this.grupos.put(TipoGrupo.negro, negro);


        //Casillas cyan

        aux = new ArrayList<>();
        aux.add(0); aux.add(6); aux.add(Constantes.NOMBRE_CYAN_1);

        aux2 = new ArrayList<>();
        aux2.add(0); aux2.add(8); aux2.add(Constantes.NOMBRE_CYAN_2);

        ArrayList<Object> aux3 = new ArrayList<>();
        aux3.add(0); aux3.add(9); aux3.add(Constantes.NOMBRE_CYAN_3);

        Grupo cyan = new Grupo(TipoGrupo.cyan, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.cyan, cyan);


        //Casillas rosa

        aux = new ArrayList<>();
        aux.add(1); aux.add(1); aux.add(Constantes.NOMBRE_ROSA_1);

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add(3); aux2.add(Constantes.NOMBRE_ROSA_2);

        aux3 = new ArrayList<>();
        aux3.add(1); aux3.add(4); aux3.add(Constantes.NOMBRE_ROSA_3);

        Grupo rosa = new Grupo(TipoGrupo.rosa, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.rosa, rosa);


        //Casillas naranja

        aux = new ArrayList<>();
        aux.add(1); aux.add(6); aux.add(Constantes.NOMBRE_NARANJA_1);

        aux2 = new ArrayList<>();
        aux2.add(1);aux2.add(8); aux2.add(Constantes.NOMBRE_NARANJA_2);

        aux3 = new ArrayList<>();
        aux3.add(1); aux3.add(9); aux3.add(Constantes.NOMBRE_NARANJA_3);

        Grupo naranja = new Grupo(TipoGrupo.naranja, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.naranja, naranja);


        //Casillas rojo

        aux = new ArrayList<>();
        aux.add(2); aux.add(1); aux.add(Constantes.NOMBRE_ROJO_1);

        aux2 = new ArrayList<>();
        aux2.add(2); aux2.add(3); aux2.add(Constantes.NOMBRE_ROJO_2);

        aux3 = new ArrayList<>();
        aux3.add(2); aux3.add(4); aux3.add(Constantes.NOMBRE_ROJO_3);

        Grupo rojo = new Grupo(TipoGrupo.rojo, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.rojo, rojo);


        //Casilla marron

        aux = new ArrayList<>();
        aux.add(2); aux.add(6); aux.add(Constantes.NOMBRE_MARRON_1);

        aux2 = new ArrayList<>();
        aux2.add(2); aux2.add(7); aux2.add(Constantes.NOMBRE_MARRON_2);

        aux3 = new ArrayList<>();
        aux3.add(2); aux3.add(9); aux3.add(Constantes.NOMBRE_MARRON_3);

        Grupo marron = new Grupo(TipoGrupo.marron, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.marron, marron);


        //Casillas verde

        aux = new ArrayList<>();
        aux.add(3); aux.add(1); aux.add(Constantes.NOMBRE_VERDE_1);

        aux2 = new ArrayList<>();
        aux2.add(3); aux2.add(2); aux2.add(Constantes.NOMBRE_VERDE_2);

        aux3 = new ArrayList<>();
        aux3.add(3); aux3.add(4); aux3.add(Constantes.NOMBRE_VERDE_3);

        Grupo verde = new Grupo(TipoGrupo.verde, this, true, aux, aux2, aux3);
        this.grupos.put(TipoGrupo.verde, verde);


        //Casilla azul

        aux = new ArrayList<>();
        aux.add(3); aux.add(7); aux.add(Constantes.NOMBRE_AZUL_1);

        aux2 = new ArrayList<>();
        aux2.add(3); aux2.add(9); aux2.add(Constantes.NOMBRE_AZUL_2);

        Grupo azul = new Grupo(TipoGrupo.azul, this, true, aux, aux2);
        this.grupos.put(TipoGrupo.azul, azul);


        //Salida

        Salida salida = new Salida(Constantes.NOMBRE_SALIDA, 0, this);
        getCasillas().get(salida.getPosicionEnTablero()/10).set(salida.getPosicionEnTablero()%10,salida);
        getCasillasTablero().put(salida.getNombre(),salida);

        //Carcel

        Carcel carcel = new Carcel(Constantes.NOMBRE_CARCEL, 10, this);
        getCasillas().get(carcel.getPosicionEnTablero()/10).set(carcel.getPosicionEnTablero()%10,carcel);
        getCasillasTablero().put(carcel.getNombre(),carcel);


        //Parking

        Parking parking = new Parking(Constantes.NOMBRE_PARKING, 20, this);
        getCasillas().get(parking.getPosicionEnTablero()/10).set(parking.getPosicionEnTablero()%10,parking);
        getCasillasTablero().put(parking.getNombre(),parking);


        //irCarcel

        IrCarcel irCarcel = new IrCarcel(Constantes.NOMBRE_IR_A_CARCEL, 30, this);
        getCasillas().get(irCarcel.getPosicionEnTablero()/10).set(irCarcel.getPosicionEnTablero()%10,irCarcel);
        getCasillasTablero().put(irCarcel.getNombre(),irCarcel);


        //Transporte

        aux = new ArrayList<>();
        aux.add(0); aux.add(5); aux.add(Constantes.NOMBRE_TRANSPORTE_1);

        aux2 = new ArrayList<>();
        aux2.add(1); aux2.add(5); aux2.add(Constantes.NOMBRE_TRANSPORTE_2);

        aux3 = new ArrayList<>();
        aux3.add(2); aux3.add(5); aux3.add(Constantes.NOMBRE_TRANSPORTE_3);

        ArrayList<Object> aux4 = new ArrayList<>();
        aux4.add(3); aux4.add(5); aux4.add(Constantes.NOMBRE_TRANSPORTE_4);

        Grupo transporte = new Grupo(TipoGrupo.transporte, this, true, aux, aux2, aux3, aux4); //30
        this.grupos.put(TipoGrupo.transporte, transporte);


        //Casillas servicio

        aux = new ArrayList<>();
        aux.add(1); aux.add(2); aux.add(Constantes.NOMBRE_SERVICIO_1);

        aux2 = new ArrayList<>();
        aux2.add(2); aux2.add(8); aux2.add(Constantes.NOMBRE_SERVICIO_2);

        Grupo servicios = new Grupo(TipoGrupo.servicios, this, true, aux, aux2); //32
        this.grupos.put(TipoGrupo.servicios, servicios);


        //Impuesto tipo 1

        Impuesto impuesto = new Impuesto(Constantes.NOMBRE_IMPUESTO_1, 4, this, Constantes.IMPUESTO_1);

        getCasillas().get(impuesto.getPosicionEnTablero()/10).set(impuesto.getPosicionEnTablero()%10,impuesto);
        getCasillasTablero().put(impuesto.getNombre(),impuesto);


        //Impuesto tipo 2

        impuesto = new Impuesto(Constantes.NOMBRE_IMPUESTO_2, 38, this, Constantes.IMPUESTO_1);

        getCasillas().get(impuesto.getPosicionEnTablero()/10).set(impuesto.getPosicionEnTablero()%10,impuesto);
        getCasillasTablero().put(impuesto.getNombre(),impuesto);


        //Casillas suerte

        SuerteCasilla suerte = new SuerteCasilla(Constantes.NOMBRE_SUERTE_1, 7, this);

        getCasillas().get(suerte.getPosicionEnTablero()/10).set(suerte.getPosicionEnTablero()%10,suerte);
        getCasillasTablero().put(suerte.getNombre(),suerte);

        suerte = new SuerteCasilla(Constantes.NOMBRE_SUERTE_2, 22, this);

        getCasillas().get(suerte.getPosicionEnTablero()/10).set(suerte.getPosicionEnTablero()%10,suerte);
        getCasillasTablero().put(suerte.getNombre(),suerte);

        suerte = new SuerteCasilla(Constantes.NOMBRE_SUERTE_3, 36, this);

        getCasillas().get(suerte.getPosicionEnTablero()/10).set(suerte.getPosicionEnTablero()%10,suerte);
        getCasillasTablero().put(suerte.getNombre(),suerte);


        //Casillas comunidad

        ComunidadCasilla comunidad = new ComunidadCasilla(Constantes.NOMBRE_COMUNIDAD_1, 2, this);

        getCasillas().get(comunidad.getPosicionEnTablero()/10).set(comunidad.getPosicionEnTablero()%10,comunidad);
        getCasillasTablero().put(comunidad.getNombre(),comunidad);

        comunidad = new ComunidadCasilla(Constantes.NOMBRE_COMUNIDAD_2, 17, this);

        getCasillas().get(comunidad.getPosicionEnTablero()/10).set(comunidad.getPosicionEnTablero()%10,comunidad);
        getCasillasTablero().put(comunidad.getNombre(),comunidad);

        comunidad = new ComunidadCasilla(Constantes.NOMBRE_COMUNIDAD_3, 33, this);

        getCasillas().get(comunidad.getPosicionEnTablero()/10).set(comunidad.getPosicionEnTablero()%10,comunidad);
        getCasillasTablero().put(comunidad.getNombre(),comunidad);

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

    public Banca getBanca(){
        return banca;
    }

    public Dado getDado(){
        return dado;
    }

    public HashMap<TipoGrupo, Grupo> getGrupos() {
        return grupos;
    }
}