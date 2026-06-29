package cr.ac.ucr.customdrivecr.service;

import cr.ac.ucr.customdrivecr.entity.Vehiculo;
import cr.ac.ucr.customdrivecr.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> listarTodos() {
        return vehiculoRepository.findAll();
    }

    public List<Vehiculo> listarActivos() {
        return vehiculoRepository.findByActivoTrue();
    }

    public List<Vehiculo> listarPorModelo(Long modeloId) {
        return vehiculoRepository.findByModeloId(modeloId);
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        if (vehiculo.getActivo() == null) {
            vehiculo.setActivo(true);
        }

        return vehiculoRepository.save(vehiculo);
    }

    public Optional<Vehiculo> buscarPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    public void eliminar(Long id) {
        vehiculoRepository.deleteById(id);
    }

    public void desactivar(Long id) {
        Optional<Vehiculo> vehiculoOptional = vehiculoRepository.findById(id);

        if (vehiculoOptional.isPresent()) {
            Vehiculo vehiculo = vehiculoOptional.get();
            vehiculo.setActivo(false);
            vehiculoRepository.save(vehiculo);
        }
    }
}