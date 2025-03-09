package jasAplication.service.implement;

import jasAplication.dto.proceso.ActualizarProcesoDTO;
import jasAplication.dto.proceso.CrearProcesoDTO;
import jasAplication.dto.proceso.DetalleProcesoDTO;
import jasAplication.exceptions.ProcesoException;
import jasAplication.model.documents.Proceso;
import jasAplication.repository.ProcesoRepository;
import jasAplication.service.service.ProcesoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcesoServiceImpl implements ProcesoService {

    private final ProcesoRepository procesoRepository;

    @Override
    public void crearProceso(CrearProcesoDTO procesoDTO) {
        if (procesoDTO.nombre() == null || procesoDTO.nombre().isBlank()) {
            throw ProcesoException.nombreInvalido();
        }

        Proceso proceso = new Proceso();
        proceso.setNombre(procesoDTO.nombre());

        procesoRepository.save(proceso);
    }

    @Override
    public void actualizarProceso(ActualizarProcesoDTO procesoDTO) {
        Proceso proceso = procesoRepository.findById(procesoDTO.id())
                .orElseThrow(() -> ProcesoException.procesoNoEncontrado(procesoDTO.id()));

        if (procesoDTO.nombre() == null || procesoDTO.nombre().isBlank()) {
            throw ProcesoException.nombreInvalido();
        }

        proceso.setNombre(procesoDTO.nombre());
        procesoRepository.save(proceso);
    }

    @Override
    public DetalleProcesoDTO obtenerProcesoPorId(String id) {
        Proceso proceso = procesoRepository.findById(id)
                .orElseThrow(() -> ProcesoException.procesoNoEncontrado(id));

        return new DetalleProcesoDTO(proceso.getId(), proceso.getNombre());
    }

    @Override
    public List<DetalleProcesoDTO> listarProcesos() {
        List<Proceso> procesos = procesoRepository.findAll();

        return procesos.stream()
                .map(proceso -> new DetalleProcesoDTO(proceso.getId(), proceso.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarProceso(String id) {
        if (!procesoRepository.existsById(id)) {
            throw ProcesoException.procesoNoEncontrado(id);
        }

        procesoRepository.deleteById(id);
    }
}
