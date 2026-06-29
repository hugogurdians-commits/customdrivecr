package cr.ac.ucr.customdrivecr.service;

import cr.ac.ucr.customdrivecr.entity.Marca;
import cr.ac.ucr.customdrivecr.repository.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    public List<Marca> listarActivas() {
        return marcaRepository.findByActivoTrue();
    }

    public Marca guardar(Marca marca) {
        if (marca.getActivo() == null) {
            marca.setActivo(true);
        }

        return marcaRepository.save(marca);
    }

    public Optional<Marca> buscarPorId(Long id) {
        return marcaRepository.findById(id);
    }

    public void eliminar(Long id) {
        marcaRepository.deleteById(id);
    }

    public void desactivar(Long id) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);

        if (marcaOptional.isPresent()) {
            Marca marca = marcaOptional.get();
            marca.setActivo(false);
            marcaRepository.save(marca);
        }
    }
}