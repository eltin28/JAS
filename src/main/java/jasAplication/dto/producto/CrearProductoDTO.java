package jasAplication.dto.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jasAplication.model.enums.Modalidad;
import jasAplication.model.enums.Peso;
import org.hibernate.validator.constraints.Length;


public record CrearProductoDTO(

        @NotBlank(message = "El nombre del producto no puede estar vacío")
        @Length(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String nombre,

        @NotNull(message = "Debe seleccionar un peso")
        Peso peso,

        @NotNull(message = "Debe especificar el ID del proceso")
        String idProceso,

        @NotNull(message = "El precio no puede estar vacío")
        int precio,

        @NotNull(message = "Debe seleccionar una modalidad")
        Modalidad modalidad
) {}