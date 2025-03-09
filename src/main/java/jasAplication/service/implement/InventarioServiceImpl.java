package jasAplication.service.implement;

import jakarta.annotation.PostConstruct;
import jasAplication.dto.inventario.ItemInventarioDTO;
import jasAplication.exceptions.InventarioException;
import jasAplication.exceptions.ProductoException;
import jasAplication.model.documents.Inventario;
import jasAplication.model.documents.Producto;
import jasAplication.model.vo.ItemInventario;
import jasAplication.repository.InventarioRepository;
import jasAplication.repository.ProductoRepository;


import jasAplication.service.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;

    @PostConstruct
    public void inicializarInventario() {
        try {
            if (inventarioRepository.count() == 0) {
                Inventario inventario = new Inventario();
                inventarioRepository.save(inventario);
            }
        } catch (Exception e) {
            throw new InventarioException("Error al inicializar el inventario: " + e.getMessage());
        }
    }

    @Override
    public void agregarItemInventario(ItemInventarioDTO itemDTO, String idInventario) {
        Inventario inventario = inventarioRepository.findById(idInventario)
                .orElseThrow(() -> InventarioException.inventarioNoEncontrado(idInventario));

        Producto producto = productoRepository.findById(itemDTO.idProducto())
                .orElseThrow(() -> ProductoException.productoNoEncontrado(itemDTO.idProducto()));

        ItemInventario nuevoItem = new ItemInventario(
                producto.getId(),  // Se usa el ID del producto
                producto.getNombre(),
                itemDTO.cantidad(),
                itemDTO.fechaIngreso()
        );

        inventario.getItemsInventario().add(nuevoItem);
        inventarioRepository.save(inventario);
    }

    @Override
    public void eliminarItemInventario(String idInventario, String idProducto) {
        Inventario inventario = inventarioRepository.findById(idInventario)
                .orElseThrow(() -> InventarioException.inventarioNoEncontrado(idInventario));

        boolean eliminado = inventario.getItemsInventario().removeIf(item -> item.getProductoId().equals(idProducto));

        if (!eliminado) {
            throw InventarioException.itemNoEncontrado(idProducto);
        }

        inventarioRepository.save(inventario);
    }

    @Override
    public List<ItemInventarioDTO> listarItemsInventario(String idInventario) {
        Inventario inventario = inventarioRepository.findById(idInventario)
                .orElseThrow(() -> InventarioException.inventarioNoEncontrado(idInventario));

        return inventario.getItemsInventario().stream()
                .map(item -> new ItemInventarioDTO(item.getProductoId(), item.getNombreProducto(), item.getCantidadProducto(), item.getFechaIngreso()))
                .collect(Collectors.toList());
    }

    @Override
    public void vaciarInventario(String idInventario) {
        Inventario inventario = inventarioRepository.findById(idInventario)
                .orElseThrow(() -> InventarioException.inventarioNoEncontrado(idInventario));

        inventario.getItemsInventario().clear();
        inventarioRepository.save(inventario);
    }
}