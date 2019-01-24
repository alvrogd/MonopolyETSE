package aplicacionGUI.menuGUI.entrada;

import aplicacionGUI.ConstantesGUI;
import aplicacionGUI.menuGUI.MenuGUI;
import monopoly.jugadores.tratos.Inmunidad;
import monopoly.jugadores.tratos.Trato;

public class CambiarTurnoTrato extends CambiarTrato{

    private Inmunidad inmunidad;

    public CambiarTurnoTrato(Trato trato, MenuGUI menuGUI, Inmunidad inmunidad) {
        super(trato, menuGUI, ConstantesGUI.INPUT_ENTERO_TURNOS_IMAGEN, ConstantesGUI.INPUT_ENTERO_TURNOS_IMAGEN_OSCURA);
        this.inmunidad = inmunidad;
    }

    public Inmunidad getInmunidad() {
        return inmunidad;
    }

    public void setInmunidad(Inmunidad inmunidad) {
        this.inmunidad = inmunidad;
    }

    @Override
    public void almacenarEntero(int enteroLeido, int identificadorAtributo) {

        int index = getTrato().getInmunidades().indexOf(getInmunidad());
        System.out.println(index);
        getTrato().getInmunidades().get(index).setNumeroTurnos(enteroLeido);

        getInputEntero().eliminarRastro();
        getMenuGUI().setInputActivo(false);
    }
}
