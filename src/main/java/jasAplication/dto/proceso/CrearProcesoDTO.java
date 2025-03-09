package jasAplication.dto.proceso;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CrearProcesoDTO(
        @NotBlank(message = "El nombre del proceso no puede estar vacío")
        @Length(min = 3, max = 100, message = "El nombre del proceso debe tener entre 3 y 100 caracteres")
        String nombre
) {
}
