package monopoly.tablero.cartas;

import aplicacion.salidaPantalla.Output;
import monopoly.Constantes;
import monopoly.jugadores.Jugador;
import monopoly.jugadores.acciones.TransferenciaMonetaria;
import monopoly.jugadores.excepciones.*;
import monopoly.tablero.Tablero;
import monopoly.tablero.jerarquiaCasillas.jerarquiaAccion.Parking;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;
import monopoly.tablero.jerarquiaCasillas.Casilla;
import monopoly.tablero.jerarquiaCasillas.Propiedad;
import monopoly.tablero.jerarquiaCasillas.Solar;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Carta {

    /* Atributos */

    private final TipoAccion tipoAccion;
    private final Enum accion;
    private final Tablero tablero;



    /* Constructor */

    public Carta(TipoAccion tipoAccion, Enum accion, Tablero tablero) {

        if (tipoAccion == null) {
            System.err.println("Tipo de acción no inicializado");
            System.exit(1);
        }

        if (accion == null) {
            System.err.println("Acción no inicializada");
            System.exit(1);
        }

        if (tablero == null) {
            System.err.println("Tablero no inicializado");
            System.exit(1);
        }

        this.tipoAccion = tipoAccion;
        this.accion = accion;
        this.tablero = tablero;
    }



    /* Métodos */

    public TipoAccion getTipoAccion() {
        return tipoAccion;
    }


    public Enum getAccion() {
        return accion;
    }


    public Tablero getTablero() {
        return tablero;
    }


    /**
     * Se actúa sobre el jugador actual en función de lo que indica la carta
     */
    public void accion() throws EstarBancarrotaException, NoSerPropietarioException, ImposibleCambiarModoException,
            ImposibleMoverseException, EdificiosSolarException {

        switch (getTipoAccion()) {

            case cobro:
                final TipoCobro tipoCobro = (TipoCobro) getAccion();
                Output.respuesta(tipoCobro.getDescripcion());
                cobrarCarta(tipoCobro);
                break;

            case movimiento:
                final TipoMovimiento tipoMovimiento = (TipoMovimiento) getAccion();
                Output.respuesta(tipoMovimiento.getDescripcion());
                moverCarta(tipoMovimiento);
                break;

            case pago:
                final TipoPago tipoPago = (TipoPago) getAccion();
                Output.respuesta(tipoPago.getDescripcion());
                pagarCarta(tipoPago);
                break;
        }
    }


    /**
     * Se procesa un tipo de cobro dado
     *
     * @param tipoCobro tipo de cobro a procesar
     */
    private void cobrarCarta(TipoCobro tipoCobro) {

        final Jugador jugador = getTablero().getJuego().getTurno();

        jugador.setFortuna(jugador.getFortuna() + tipoCobro.getImporte());
        jugador.incrementarPremiosInversionesOBote(tipoCobro.getImporte());
        Output.respuesta("Se han cobrado " + tipoCobro.getImporte() + "K €");

        // Se registra la acción
        jugador.getAcciones().add(new TransferenciaMonetaria(tipoCobro.getImporte(),
                jugador.getAvatar().getTablero().getBanca(), jugador));

    }


    /**
     * Se procesa un tipo de pago dado
     *
     * @param tipoPago tipo de pago a procesar
     */
    private void pagarCarta(TipoPago tipoPago) throws EstarBancarrotaException, NoSerPropietarioException {

        final Jugador jugador = getTablero().getJuego().getTurno();
        final String receptor = tipoPago.getNombreReceptor();

        int importe = 0;

        // Si el importe debe ser calculado, se trata de la casilla de pago de impuesto por bienes inmuebles
        if (tipoPago.isImporteCalculado()) {

            // Se obtienen las propiedades del jugador
            ArrayList<Propiedad> propiedades = jugador.getPropiedades();

            // Se recorren los edificios de cada propiedad sumando el correspondiente importe
            for (Propiedad propiedad : propiedades) {

                if (propiedad instanceof Solar) {

                    final Solar solar = (Solar) propiedad;

                    importe += solar.getEdificiosContenidos().get(TipoEdificio.casa).size() * 400;
                    importe += solar.getEdificiosContenidos().get(TipoEdificio.hotel).size() * 1150;
                    importe += solar.getEdificiosContenidos().get(TipoEdificio.piscina).size() * 200;
                    importe += solar.getEdificiosContenidos().get(TipoEdificio.pistaDeporte).size() * 750;
                }
            }
        }

        // En caso contrario, el importe se obtiene directamente del tipo de pago
        else
            importe = tipoPago.getImporte();

        // Ahora se puede, o bien pagar a la banca
        if (receptor.equals("banca")) {

            // Si se ha efectuado correctamente el pago
            if (jugador.pagar(getTablero().getJuego().getBanca(), importe)) {

                jugador.incrementarPagoTasasEImpuestos(importe);

                // Se incrementa el bote en el parking
                final Parking parking = (Parking) jugador.getAvatar().getTablero().getCasillas().get(
                        Constantes.POSICION_PARKING / 10).get(Constantes.POSICION_PARKING % 10);

                parking.incrementarDinero(importe);
            }

        }

        // O bien pagar a todos los jugadores, en el caso de las cartas de pago de un alquiler en Cannes o pago por
        // ser escodigo presidente de la junta directiva
        else {

            final Collection<Jugador> jugadores = getTablero().getJuego().getJugadores().values();

            int numeroPagos = jugador.pagar(new ArrayList<>(jugadores), importe, false);

            jugador.incrementarPagoTasasEImpuestos(importe * numeroPagos);
        }
    }


    /**
     * Se procesa un tipo de movimiento dado
     *
     * @param tipoMovimiento tipo de movimiento a procesar
     */
    private void moverCarta(TipoMovimiento tipoMovimiento) throws ImposibleCambiarModoException,
            ImposibleMoverseException, EstarBancarrotaException, NoSerPropietarioException, EdificiosSolarException {

        final Jugador jugador = getTablero().getJuego().getTurno();

        // Nombre de la casilla de destino
        final String nombreDestino = tipoMovimiento.getNombreCasillaDestino();

        // Posición actual
        int posicionActual = jugador.getAvatar().getPosicion().getPosicionEnTablero();
        // Posición de destino
        int posicionDestino;

        // Número de casillas a moverse
        int numeroCasillas;

        // Si el movimiento actual del avatar es el avanzado
        boolean movimientoEstandar = jugador.getAvatar().isMovimientoEstandar();


        // Si el movimiento se efectúa directamente a una casilla dada
        if (tipoMovimiento.isMoverseDirectamente()) {

            switch (nombreDestino) {

                // Si el destino es la cárcel, simplemente se encarcela al avatar del jugador
                case "Azkaban":
                    jugador.getAvatar().caerEnIrACarcel();
                    return;

                // Si el destino es la próxima casilla de transporte (los transportes se encuentran en las posiciones
                // 5, 15, 25 y 35
                case "transporte":

                    // La siguiente casilla de transporte puede estar como mucho a 10 casillas de la actual
                    posicionDestino = posicionActual + 10;
                    posicionDestino -= (posicionDestino - 5) % 10;

                    posicionDestino %= 40;
                    break;

                // En caso contrario, se obtiene la posición de destino directamente
                default:
                    final Casilla destino = getTablero().getCasillasTablero().get(nombreDestino);
                    posicionDestino = destino.getPosicionEnTablero();
                    break;
            }
        }

        // O sino, es el caso de la carta en la que se retroceden tres casillas
        else
            posicionDestino = posicionActual + tipoMovimiento.getCasillasDesplazarse();

        // Se calcula el número de casillas a avanzar (el valor de posicionDestino es incrementado en una vuelta
        // para realizar después el módulo y evitar así resultados negativos)
        numeroCasillas = (posicionDestino + 40 - posicionActual) % 40;

        // Se indica que aún no se ha movido las casillas correspondientes a la tirada
        jugador.getAvatar().sethaMovidoCasillasTirada(false);
        // Se indica el número de casillas restantes por moverse
        jugador.getAvatar().setCasillasRestantesPorMoverse(numeroCasillas +
                jugador.getAvatar().getCasillasRestantesPorMoverse());

        // Si el movimiento no es el estándar, se cambia
        boolean seHaCambiadoMovimiento = false;

        if (!movimientoEstandar) {
            jugador.getAvatar().switchMovimiento(true, false);
            seHaCambiadoMovimiento = true;
        }

        // Se avanzan las casillas dadas
        jugador.getAvatar().avanzar(numeroCasillas, tipoMovimiento.isCobrarCasillaSalida(), false,
                tipoMovimiento.getMultiplicadorPago());

        // Y se devuelve el modo de movimiento a su estado original si fue modificado
        if (seHaCambiadoMovimiento)
            jugador.getAvatar().switchMovimiento(true, false);
    }


    @Override
    public String toString() {

        String string = "";

        switch (tipoAccion) {

            case cobro:
                final TipoCobro tipoCobro = (TipoCobro) getAccion();
                string += tipoCobro.getDescripcion();
                break;

            case movimiento:
                final TipoMovimiento tipoMovimiento = (TipoMovimiento) getAccion();
                string += tipoMovimiento.getDescripcion();
                break;

            case pago:
                final TipoPago tipoPago = (TipoPago) getAccion();
                string += tipoPago.getDescripcion();
                break;
        }

        return string;
    }
}
