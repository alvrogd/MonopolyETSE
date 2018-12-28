package monopoly.jugadores.excepciones;

public class NumeroIncorrectoException extends Exception{

    public NumeroIncorrectoException() {

        super("Número introducido incorrecto.");

    }

    public NumeroIncorrectoException(String numero) {

        super(numero + " es un número incorrecto");

    }

}
