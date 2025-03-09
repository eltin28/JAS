package jasAplication.service.service;

import jasAplication.dto.producto.ActualizarProductoDTO;
import jasAplication.dto.producto.CrearProductoDTO;
import jasAplication.dto.producto.DetalleProductoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {
    void crearProducto(CrearProductoDTO productoDTO);

    void actualizarProducto(ActualizarProductoDTO productoDTO);

    DetalleProductoDTO obtenerProductoPorId(String id);

    List<DetalleProductoDTO> listarProductos();

    void eliminarProducto(String id);
}
