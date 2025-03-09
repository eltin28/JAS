package jasAplication.service.implement;

import jasAplication.dto.producto.ActualizarProductoDTO;
import jasAplication.dto.producto.CrearProductoDTO;
import jasAplication.dto.producto.DetalleProductoDTO;
import jasAplication.exceptions.ProcesoException;
import jasAplication.model.documents.Proceso;
import jasAplication.model.documents.Producto;
import jasAplication.repository.ProcesoRepository;
import jasAplication.repository.ProductoRepository;
import jasAplication.service.service.ProductoService;
import jasAplication.exceptions.ProductoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProcesoRepository procesoRepository;

    @Override
    public void crearProducto(CrearProductoDTO productoDTO) {
        if (productoDTO.nombre() == null || productoDTO.nombre().isBlank()) {
            throw ProductoException.nombreInvalido();
        }

        Proceso proceso = procesoRepository.findById(productoDTO.idProceso())
                .orElseThrow(() -> ProcesoException.procesoNoEncontrado(productoDTO.idProceso()));

        Producto producto = new Producto();
        producto.setNombre(productoDTO.nombre());
        producto.setPeso(productoDTO.peso());
        producto.setProceso(proceso);
        producto.setPrecio(productoDTO.precio());
        producto.setModalidad(productoDTO.modalidad());

        productoRepository.save(producto);
    }

    @Override
    public void actualizarProducto(ActualizarProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(productoDTO.id())
                .orElseThrow(() -> ProductoException.productoNoEncontrado(productoDTO.id()));

        if (productoDTO.nombre() == null || productoDTO.nombre().isBlank()) {
            throw ProductoException.nombreInvalido();
        }

        Proceso proceso = procesoRepository.findById(productoDTO.idProceso())
                .orElseThrow(() -> ProcesoException.procesoNoEncontrado(productoDTO.idProceso()));

        producto.setNombre(productoDTO.nombre());
        producto.setPeso(productoDTO.peso());
        producto.setProceso(proceso);
        producto.setPrecio(productoDTO.precio());
        producto.setModalidad(productoDTO.modalidad());

        productoRepository.save(producto);
    }

    @Override
    public DetalleProductoDTO obtenerProductoPorId(String id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> ProductoException.productoNoEncontrado(id));

        return new DetalleProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getPeso(),
                producto.getProceso().getId(),
                producto.getPrecio(),
                producto.getModalidad()
        );
    }

    @Override
    public List<DetalleProductoDTO> listarProductos() {
        List<Producto> productos = productoRepository.findAll();

        return productos.stream()
                .map(producto -> new DetalleProductoDTO(
                        producto.getId(),
                        producto.getNombre(),
                        producto.getPeso(),
                        producto.getProceso().getId(),
                        producto.getPrecio(),
                        producto.getModalidad()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProducto(String id) {
        if (!productoRepository.existsById(id)) {
            throw ProductoException.productoNoEncontrado(id);
        }

        productoRepository.deleteById(id);
    }
}