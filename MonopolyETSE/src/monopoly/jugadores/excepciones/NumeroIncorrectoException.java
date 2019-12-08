package monopoly.jugadores.excepciones;

public class NumeroIncorrectoException extends JugadorException {

    public NumeroIncorrectoException() {

        super("Número introducido incorrecto.");
    }

    public NumeroIncorrectoException(String numero) {

        super(numero + " es un número incorrecto");
    }
}
