package jasAplication.dto.inventario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ItemInventarioDTO(

        @NotBlank(message = "Debe especificar el ID del producto")
        String idProducto,

        @Positive(message = "La cantidad debe ser mayor a 0")
        int cantidad,

        @NotNull(message = "Debe proporcionar una fecha de ingreso")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate fechaIngreso
) {}