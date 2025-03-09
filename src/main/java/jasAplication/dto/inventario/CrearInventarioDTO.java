package jasAplication.dto.inventario;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record CrearInventarioDTO (

    @NotBlank(message = "Los items del inventario no pueden ser nulos o vacíos")
    List<ItemInventarioDTO> itemsInventario
) {}