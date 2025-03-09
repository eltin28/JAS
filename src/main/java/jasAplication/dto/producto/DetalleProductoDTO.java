package jasAplication.dto.producto;

import jasAplication.model.enums.Modalidad;
import jasAplication.model.enums.Peso;

public record DetalleProductoDTO(
        String id,
        String nombre,
        Peso peso,
        String idProceso,
        int precio,
        Modalidad modalidad
) {
}
