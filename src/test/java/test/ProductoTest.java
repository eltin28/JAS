package test;

import jasAplication.JasApplication;
import jasAplication.model.documents.Inventario;
import jasAplication.model.documents.Proceso;
import jasAplication.model.documents.Producto;
import jasAplication.model.enums.Modalidad;
import jasAplication.model.enums.Peso;
import jasAplication.model.vo.ItemInventario;
import jasAplication.repository.InventarioRepository;
import jasAplication.repository.ProcesoRepository;
import jasAplication.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = JasApplication.class)
public class ProductoTest {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProcesoRepository procesoRepository;

    private Producto producto1;
    private Producto producto2;

    private Proceso proceso1;

    @BeforeEach
    public void setUp() {
        // Limpiar la BD antes de cada test
        inventarioRepository.deleteAll();
        productoRepository.deleteAll();
        procesoRepository.deleteAll();

        // Crear y guardar un proceso
        proceso1 = new Proceso(null, "Proceso Natural");
        proceso1 = procesoRepository.save(proceso1);

        // Crear y guardar productos
        producto1 = new Producto(null, "Café Premium", Peso.PESO_250, proceso1, "15000", Modalidad.MOLIDO);
        producto2 = new Producto(null, "Café Orgánico", Peso.PESO_500, proceso1, "25000", Modalidad.GRANO);

        producto1 = productoRepository.save(producto1);
        producto2 = productoRepository.save(producto2);
    }

    @Test
    public void registrarInventarioTest() {
        // Crear inventario sin items
        Inventario inventario = new Inventario();
        inventario.setItemsInventario(new ArrayList<>());

        Inventario inventarioGuardado = inventarioRepository.save(inventario);

        assertNotNull(inventarioGuardado);
        assertNotNull(inventarioGuardado.getId());
        assertTrue(inventarioGuardado.getItemsInventario().isEmpty());
    }

    @Test
    public void agregarUnItemInventarioTest() {
        // Crear un item con el producto1
        List<ItemInventario> items = new ArrayList<>();
        items.add(new ItemInventario(producto1.getId(), producto1.getNombre(), 20, LocalDate.now()));

        // Crear inventario con el item
        Inventario inventario = new Inventario();
        inventario.setItemsInventario(items);

        // Guardar inventario
        Inventario inventarioGuardado = inventarioRepository.save(inventario);

        // Validaciones
        assertNotNull(inventarioGuardado);
        assertEquals(1, inventarioGuardado.getItemsInventario().size());

        // Verificar que el producto está bien referenciado
        assertEquals(producto1.getId(), inventarioGuardado.getItemsInventario().get(0).getProductoId());
    }

}
