
import monopoly.*;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.TipoAvatar;
import monopoly.tablero.Casilla;
import monopoly.tablero.Grupo;
import monopoly.tablero.TipoGrupo;
import org.junit.Test;

import java.util.ArrayList;

public class JuegoTest {

    @Test
    public void crearJuego() {
        ArrayList<ArrayList<Casilla>> casillas = new ArrayList<>();
        ArrayList<Casilla> fila = new ArrayList<>();
        Jugador Banca = new Jugador("Banca", TipoAvatar.banca);

        Grupo cyan = new Grupo(TipoGrupo.azul, Banca, "Cyan1","Cyan2","Cyan3");
        Grupo negro = new Grupo(TipoGrupo.negro, Banca, "Negro1","Negro2");
        Grupo naranja = new Grupo(TipoGrupo.naranja, Banca, "Naranja1","Naranja2", "Naranja3");
        Grupo rosa = new Grupo(TipoGrupo.rosa, Banca, "Rosa1","Rosa2", "Rosa3");
        Grupo rojo = new Grupo(TipoGrupo.rojo, Banca, "Rojo1","Rojo2", "Rojo3");
        Grupo verde = new Grupo(TipoGrupo.verde, Banca, "Verde1", "Verde2", "Verde3");
        Grupo azul = new Grupo(TipoGrupo.azul, Banca, "Azul1","Azul2)");
        Grupo marron = new Grupo(TipoGrupo.marron, Banca, "Marron1", "Marron2", "Marron3");
        Grupo salida = new Grupo(TipoGrupo.salida, Banca, "Salida");
        Grupo carcel = new Grupo(TipoGrupo.carcel, Banca, "Carcel");
        Grupo parking = new Grupo(TipoGrupo.parking, Banca, "Parking");
        Grupo irCarcel = new Grupo(TipoGrupo.irCarcel, Banca, "irCarcel"); //26
        Grupo transporte = new Grupo(TipoGrupo.transporte, Banca, "T1","T2","T3","T4"); //30
        Grupo servicios = new Grupo(TipoGrupo.servicios, Banca, "S1","S2"); //32
        Grupo impuestos1 = new Grupo(TipoGrupo.impuesto1, Banca, "I1");
        Grupo impuestos2 = new Grupo(TipoGrupo.impuesto2, Banca, "I2");//34
        Grupo suerte = new Grupo(TipoGrupo.suerte, Banca, "S1","S2","S3");
        Grupo comunidad = new Grupo(TipoGrupo.comunidad, Banca, "C1","C2","C3");

        fila.add(salida.getCasillas().get(0));
        fila.add(negro.getCasillas().get(0));
        fila.add(negro.getCasillas().get(1));
        fila.add(impuestos1.getCasillas().get(0));
        fila.add(suerte.getCasillas().get(0));
        fila.add(cyan.getCasillas().get(0));
        fila.add(cyan.getCasillas().get(1));
        fila.add(cyan.getCasillas().get(2));
        fila.add(comunidad.getCasillas().get(0));
        fila.add(transporte.getCasillas().get(0));

        casillas.add(fila);
        Juego juego = new Juego(casillas);

        juego.addJugador(new Jugador("Fran", TipoAvatar.coche));
        juego.addJugador(new Jugador("Alvaro", TipoAvatar.coche));
        juego.addJugador(new Jugador("Perico", TipoAvatar.esfinge));
        juego.iniciarJuego();

    }

}
