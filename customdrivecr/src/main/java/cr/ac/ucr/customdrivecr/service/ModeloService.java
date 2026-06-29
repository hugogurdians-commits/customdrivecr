package cr.ac.ucr.customdrivecr.service;

import cr.ac.ucr.customdrivecr.entity.Modelo;
import cr.ac.ucr.customdrivecr.repository.ModeloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;

    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    public List<Modelo> listarActivos() {
        return modeloRepository.findByActivoTrue();
    }

    public List<Modelo> listarPorMarca(Long marcaId) {
        return modeloRepository.findByMarcaId(marcaId);
    }

    public Modelo guardar(Modelo modelo) {
        if (modelo.getActivo() == null) {
            modelo.setActivo(true);
        }

        return modeloRepository.save(modelo);
    }

    public Optional<Modelo> buscarPorId(Long id) {
        return modeloRepository.findById(id);
    }

    public void eliminar(Long id) {
        modeloRepository.deleteById(id);
    }

    public void desactivar(Long id) {
        Optional<Modelo> modeloOptional = modeloRepository.findById(id);

        if (modeloOptional.isPresent()) {
            Modelo modelo = modeloOptional.get();
            modelo.setActivo(false);
            modeloRepository.save(modelo);
        }
    }
}