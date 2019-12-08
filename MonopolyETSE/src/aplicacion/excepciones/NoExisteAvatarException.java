package aplicacion.excepciones;

import aplicacion.excepciones.ArgComandoIncorrectoException;

public class NoExisteAvatarException extends ArgComandoIncorrectoException {

        public NoExisteAvatarException(String nombreComando) {
            super(nombreComando, "el avatar introducido no existe.");
        }

        public NoExisteAvatarException(String nombreComando, String idAvatar) {
            super(nombreComando, "el avatar " + idAvatar + " no existe.");
        }

}
