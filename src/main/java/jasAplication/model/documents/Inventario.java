package jasAplication.model.documents;

import jasAplication.model.vo.ItemInventario;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("inventario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Inventario {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private List<ItemInventario> itemsInventario;
}