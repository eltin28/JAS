package jasAplication.exceptions;

public class ProductoException extends ProyectoException {
    public ProductoException(String message) {
        super(message);
    }

    public static ProductoException productoNoEncontrado(String id) {
        return new ProductoException("El producto con ID " + id + " no existe.");
    }

    public static ProductoException errorAlCrearProducto() {
        return new ProductoException("Ocurrió un error al crear el producto.");
    }

    public static ProductoException errorAlActualizarProducto(String id) {
        return new ProductoException("No se pudo actualizar el producto con ID " + id);
    }
}
