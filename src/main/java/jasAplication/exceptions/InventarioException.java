package jasAplication.exceptions;

public class InventarioException extends ProyectoException {
    public InventarioException(String message) {
        super(message);
    }

    public static InventarioException inventarioNoEncontrado(String id) {
        return new InventarioException("El inventario con ID " + id + " no existe.");
    }

    public static InventarioException errorAlActualizarInventario(String id) {
        return new InventarioException("No se pudo actualizar el inventario con ID " + id);
    }

    public static InventarioException itemNoDisponible(String nombreProducto) {
        return new InventarioException("El producto '" + nombreProducto + "' no está disponible en el inventario.");
    }
}
