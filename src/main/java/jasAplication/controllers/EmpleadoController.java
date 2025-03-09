package jasAplication.controllers;

import jakarta.validation.Valid;
import jasAplication.dto.autenticacion.MensajeDTO;
import jasAplication.dto.inventario.ItemInventarioDTO;
import jasAplication.dto.proceso.ActualizarProcesoDTO;
import jasAplication.dto.proceso.CrearProcesoDTO;
import jasAplication.dto.proceso.DetalleProcesoDTO;
import jasAplication.dto.producto.ActualizarProductoDTO;
import jasAplication.dto.producto.CrearProductoDTO;
import jasAplication.dto.producto.DetalleProductoDTO;
import jasAplication.exceptions.InventarioException;
import jasAplication.exceptions.ProcesoException;
import jasAplication.exceptions.ProductoException;
import jasAplication.service.service.InventarioService;
import jasAplication.service.service.ProcesoService;
import jasAplication.service.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
@RequiredArgsConstructor
public class EmpleadoController {

    @Autowired
    ProcesoService procesoService;
    @Autowired
    ProductoService productoService;
    @Autowired
    InventarioService inventarioService;

    //==================================== METODOS PROCESO =============================================//

    @PostMapping("/crear-proceso")
    public ResponseEntity<MensajeDTO<String>> crearProceso(@Valid @RequestBody CrearProcesoDTO crearProcesoDTO) {
        try {
            procesoService.crearProceso(crearProcesoDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Proceso creado exitosamente", null));
        } catch (ProcesoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    @PutMapping("/actualizar-proceso")
    public ResponseEntity<MensajeDTO<String>> actualizarProceso(@Valid @RequestBody ActualizarProcesoDTO actualizarProcesoDTO) {
        try {
            procesoService.actualizarProceso(actualizarProcesoDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Proceso actualizado exitosamente", null));
        } catch (ProcesoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    @GetMapping("/obtener-proceso/{id}")
    public ResponseEntity<MensajeDTO<DetalleProcesoDTO>> obtenerProcesoPorId(@Valid @PathVariable String id) {
        try {
            DetalleProcesoDTO proceso = procesoService.obtenerProcesoPorId(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, proceso, null));
        } catch (ProcesoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    @GetMapping("/listar-procesos")
    public ResponseEntity<MensajeDTO<List<DetalleProcesoDTO>>> listarProcesos() {
        try {
            List<DetalleProcesoDTO> procesos = procesoService.listarProcesos();
            return ResponseEntity.ok(new MensajeDTO<>(false, procesos, null));
        } catch (ProcesoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    @DeleteMapping("/eliminar-proceso/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarProceso(@PathVariable String id) {
        try {
            procesoService.eliminarProceso(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Proceso eliminado exitosamente", null));
        } catch (ProcesoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    //==================================== METODOS PRODUCTO =============================================//

    @PostMapping("/crear-producto")
    public ResponseEntity<MensajeDTO<String>> crearProducto(@Valid @RequestBody CrearProductoDTO crearProductoDTO) {
        try {
            productoService.crearProducto(crearProductoDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Producto creado exitosamente", null));
        } catch (ProductoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    @PutMapping("/actualizar-producto")
    public ResponseEntity<MensajeDTO<String>> actualizarProducto(@Valid @RequestBody ActualizarProductoDTO actualizarProductoDTO) {
        try {
            productoService.actualizarProducto(actualizarProductoDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Producto actualizado exitosamente", null));
        } catch (ProductoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    @GetMapping("/obtener-producto/{id}")
    public ResponseEntity<MensajeDTO<DetalleProductoDTO>> obtenerProductoPorId(@Valid @PathVariable String id) {
        try {
            DetalleProductoDTO producto = productoService.obtenerProductoPorId(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, producto, null));
        } catch (ProductoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    @GetMapping("/listar-productos")
    public ResponseEntity<MensajeDTO<List<DetalleProductoDTO>>> listarProductos() {
        try {
            List<DetalleProductoDTO> productos = productoService.listarProductos();
            return ResponseEntity.ok(new MensajeDTO<>(false, productos, null));
        } catch (ProductoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    @DeleteMapping("/eliminar-producto/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarProducto(@PathVariable String id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Producto eliminado exitosamente", null));
        } catch (ProductoException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }
    //==================================== METODOS INVENTARIO =============================================//

    /**
     * Agrega un ítem al inventario.
     */
    @PostMapping("/agregar-item/{idInventario}")
    public ResponseEntity<MensajeDTO<String>> agregarItemInventario(
            @PathVariable String idInventario,
            @Valid @RequestBody ItemInventarioDTO itemDTO) {
        try {
            inventarioService.agregarItemInventario(itemDTO, idInventario);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Ítem agregado al inventario correctamente.", null));
        } catch (InventarioException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    /**
     * Elimina un ítem del inventario.
     */
    @DeleteMapping("/eliminar-item/{idInventario}/{idProducto}")
    public ResponseEntity<MensajeDTO<String>> eliminarItemInventario(
            @PathVariable String idInventario,
            @PathVariable String idProducto) {
        try {
            inventarioService.eliminarItemInventario(idInventario, idProducto);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Ítem eliminado del inventario correctamente.", null));
        } catch (InventarioException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    /**
     * Lista todos los ítems del inventario.
     */
    @GetMapping("/listar-items/{idInventario}")
    public ResponseEntity<MensajeDTO<List<ItemInventarioDTO>>> listarItemsInventario(@PathVariable String idInventario) {
        try {
            List<ItemInventarioDTO> items = inventarioService.listarItemsInventario(idInventario);
            return ResponseEntity.ok(new MensajeDTO<>(false, items, null));
        } catch (InventarioException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }

    /**
     * Vacía el inventario completamente.
     */
    @DeleteMapping("/vaciar/{idInventario}")
    public ResponseEntity<MensajeDTO<String>> vaciarInventario(@PathVariable String idInventario) {
        try {
            inventarioService.vaciarInventario(idInventario);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Inventario vaciado correctamente.", null));
        } catch (InventarioException e) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, null, e.getMessage()));
        }
    }
}
