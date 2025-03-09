package jasAplication.model.documents;

import jasAplication.model.enums.Modalidad;
import jasAplication.model.enums.Peso;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String nombre;
    private Peso peso;
    private Proceso proceso;
    private int precio;
    private Modalidad modalidad;

}