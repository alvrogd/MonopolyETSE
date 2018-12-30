package monopoly.jugadores.acciones;

import monopoly.jugadores.excepciones.EdificiosSolarException;

public interface IAccionJugador {

    void revertirAccion() throws EdificiosSolarException;
}
