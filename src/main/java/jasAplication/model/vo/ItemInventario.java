package jasAplication.model.vo;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemInventario {

    private String productoId;
    private String nombreProducto;
    private int cantidadProducto;
    private LocalDate fechaIngreso;

}