package jasAplication.service.service;

import jasAplication.dto.inventario.ItemInventarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventarioService {
    void inicializarInventario(); // <-- Asegurarse de que la interfaz lo incluya

    void agregarItemInventario(ItemInventarioDTO itemDTO, String idInventario);

    void eliminarItemInventario(String idInventario, String idProducto);

    List<ItemInventarioDTO> listarItemsInventario(String idInventario);

    void vaciarInventario(String idInventario);
}
