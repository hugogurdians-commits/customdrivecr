package cr.ac.ucr.customdrivecr.service;

import cr.ac.ucr.customdrivecr.entity.Compatibilidad;
import cr.ac.ucr.customdrivecr.repository.CompatibilidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompatibilidadService {

    private final CompatibilidadRepository compatibilidadRepository;

    public CompatibilidadService(CompatibilidadRepository compatibilidadRepository) {
        this.compatibilidadRepository = compatibilidadRepository;
    }

    public List<Compatibilidad> listarTodas() {
        return compatibilidadRepository.findAll();
    }

    public List<Compatibilidad> listarTodos() {
        return compatibilidadRepository.findAll();
    }

    public List<Compatibilidad> listarActivos() {
        return compatibilidadRepository.findByActivoTrue();
    }

    public Optional<Compatibilidad> buscarPorId(Long id) {
        return compatibilidadRepository.findById(id);
    }

    public Compatibilidad guardar(Compatibilidad compatibilidad) {
        return compatibilidadRepository.save(compatibilidad);
    }

    public void eliminar(Long id) {
        compatibilidadRepository.deleteById(id);
    }

    public List<Compatibilidad> buscarPorVehiculoId(Long vehiculoId) {
        return compatibilidadRepository.findByVehiculoId(vehiculoId);
    }

    public List<Compatibilidad> buscarPorVehiculo(Long vehiculoId) {
        return compatibilidadRepository.findByVehiculoId(vehiculoId);
    }

    public List<Compatibilidad> buscarPorVehiculoActivas(Long vehiculoId) {
        return compatibilidadRepository.findByVehiculoIdAndActivoTrue(vehiculoId);
    }

    public List<Compatibilidad> buscarPorAccesorioId(Long accesorioId) {
        return compatibilidadRepository.findByAccesorioId(accesorioId);
    }
}