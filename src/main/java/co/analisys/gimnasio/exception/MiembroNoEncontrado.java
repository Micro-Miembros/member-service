package co.analisys.gimnasio.exception;

public class MiembroNoEncontrado extends RuntimeException {

    public MiembroNoEncontrado(Long miembroId) {
        super("No se encontró el miembro con ID " + miembroId);
    }
}
