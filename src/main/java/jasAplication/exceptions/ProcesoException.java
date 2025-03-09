package jasAplication.exceptions;

public class ProcesoException extends ProyectoException {
    public ProcesoException(String message) {
        super(message);
    }

    public static ProcesoException procesoNoEncontrado(String id) {
        return new ProcesoException("El proceso con ID " + id + " no existe.");
    }

    public static ProcesoException errorAlCrearProceso() {
        return new ProcesoException("Ocurrió un error al crear el proceso.");
    }

    public static ProcesoException nombreInvalido() {
        return new ProcesoException("El nombre del proceso no puede estar vacío.");
    }

    public static ProcesoException errorAlActualizarProceso(String id) {
        return new ProcesoException("No se pudo actualizar el proceso con ID " + id);
    }
}
