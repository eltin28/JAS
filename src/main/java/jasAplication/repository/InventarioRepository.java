package jasAplication.repository;

import jasAplication.model.documents.Inventario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends MongoRepository<Inventario, String> {
}
