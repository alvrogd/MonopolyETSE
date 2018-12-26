package monopoly.jugadores.acciones;

import monopoly.jugadores.Jugador;
import monopoly.tablero.jerarquiaCasillas.jerarquiaEdificios.TipoEdificio;
import monopoly.tablero.jerarquiaCasillas.Solar;

import java.util.HashMap;

public class Edificacion implements IAccionJugador {

    /* Atributos */

    private Solar solar;
    private HashMap<TipoEdificio, Integer> balances;



    /* Constructor */

    public Edificacion(Solar solar, TipoEdificio tipoEdificio, int balance) {

        this( solar, 0, 0, 0, 0);

        switch( tipoEdificio ) {

            case casa:
                this.balances.put(TipoEdificio.casa, balance);
                break;

            case hotel:
                this.balances.put(TipoEdificio.hotel, balance);
                break;

            case piscina:
                this.balances.put(TipoEdificio.piscina, balance);
                break;

            case pistaDeporte:
                this.balances.put(TipoEdificio.pistaDeporte, balance);
                break;
        }
    }

    public Edificacion(Solar solar, int balanceCasas, int balanceHoteles, int balancePistas, int balancePiscinas) {

        if (solar == null) {
            System.err.println("Solar no inicializado");
            System.exit(1);
        }

        this.solar = solar;

        this.balances = new HashMap<>();
        this.balances.put(TipoEdificio.casa, balanceCasas);
        this.balances.put(TipoEdificio.hotel, balanceHoteles);
        this.balances.put(TipoEdificio.pistaDeporte, balancePistas);
        this.balances.put(TipoEdificio.piscina, balancePiscinas);
    }


    public Edificacion(Solar solar) {

        this(solar, 0, 0, 0, 0);
    }



    /* Getters y setters */

    public Solar getSolar() {
        return solar;
    }

    public void setSolar(Solar solar) {

        if (solar == null) {
            System.err.println("Solar no inicializado");
            return;
        }

        this.solar = solar;
    }

    public HashMap<TipoEdificio, Integer> getBalances() {
        return balances;
    }

    public void setBalances(HashMap<TipoEdificio, Integer> balances) {

        if (balances == null) {
            System.err.println("HashMap de balances no inicializado");
            return;
        }

        this.balances = balances;
    }



    /* Métodos */

    /**
     * Se deshacen las edificaciones que ha efectuado un jugador en una propiedad, creando y destruyendo los apropiados
     * edificios así como restaurando su fortuna al valor apropiado
     */
    @Override
    // todo es posible que sea necesario un atributo para forzar las acciones por el tema de restricciones entre tipos de edificios al construir
    public void revertirAccion() {

        final Jugador propietario = (Jugador) getSolar().getPropietario();

        // Para cada tipo de edificio
        for (TipoEdificio tipoEdificio : getBalances().keySet()) {

            // Si se han construido edificios, se eliminan
            if (getBalances().get(tipoEdificio) > 0)
                // Debe multiplicarse por 2 el precio devuelto al vender los edificios dado que tan śolo se devuelve la
                // mitad del importe invertido, lo cual tiene sentido en el juego normal pero no al revertir las
                // acciones
                propietario.setFortuna(propietario.getFortuna() + 2 *
                        getSolar().venderEdificio(tipoEdificio, getBalances().get(tipoEdificio), false));

                // Si se han destruido edificios, se vuelven a crear
            else if (getBalances().get(tipoEdificio) < 0) {

                for (int i = 0; i > getBalances().get(tipoEdificio); i--)
                    // Se reduce a la mitad el importe a restar a la fortuna del propietario dado que, mientras que en
                    // condiciones normales tendría sentido, al revertir las acciones se habrá obtenido al vender los
                    // edificios la mitad del importe invertido para crearlos, por lo que ahora deberá restarse tan
                    // sólo dicha cantidad
                    propietario.setFortuna((int) (propietario.getFortuna() - 0.5 *
                            getSolar().edificar(tipoEdificio, false)));
            }
        }
    }
}
