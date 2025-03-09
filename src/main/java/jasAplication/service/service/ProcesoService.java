package jasAplication.service.service;

import jasAplication.dto.proceso.ActualizarProcesoDTO;
import jasAplication.dto.proceso.CrearProcesoDTO;
import jasAplication.dto.proceso.DetalleProcesoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProcesoService {
    void crearProceso(CrearProcesoDTO procesoDTO);

    void actualizarProceso(ActualizarProcesoDTO procesoDTO);

    DetalleProcesoDTO obtenerProcesoPorId(String id);

    List<DetalleProcesoDTO> listarProcesos();

    void eliminarProceso(String id);
}
