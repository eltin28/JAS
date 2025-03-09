package jasAplication.dto.inventario;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ActualizarInventarioDTO(

        @NotBlank(message = "El ID del inventario no puede ser nulo o vacío")
        String id,

        @NotBlank(message = "Los items del inventario no pueden ser nulos o vacíos")
        List<ItemInventarioDTO> itemsInventario
) {}