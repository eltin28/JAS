package jasAplication.service.service;

import jasAplication.dto.inventario.ActualizarInventarioDTO;
import jasAplication.dto.inventario.CrearInventarioDTO;
import jasAplication.dto.inventario.ItemInventarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventarioService {
    void crearInventario(CrearInventarioDTO inventarioDTO);

    void actualizarInventario(ActualizarInventarioDTO inventarioDTO);

    void agregarItemInventario(ItemInventarioDTO itemDTO, String idInventario);

    void eliminarItemInventario(String idInventario, String idProducto);

    List<ItemInventarioDTO> listarItemsInventario(String idInventario);

    void vaciarInventario(String idInventario);
}
