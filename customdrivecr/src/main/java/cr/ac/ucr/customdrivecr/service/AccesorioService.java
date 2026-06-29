package cr.ac.ucr.customdrivecr.service;

import cr.ac.ucr.customdrivecr.entity.Accesorio;
import cr.ac.ucr.customdrivecr.repository.AccesorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccesorioService {

    private final AccesorioRepository accesorioRepository;

    public AccesorioService(AccesorioRepository accesorioRepository) {
        this.accesorioRepository = accesorioRepository;
    }

    public List<Accesorio> listarTodos() {
        return accesorioRepository.findAll();
    }

    public List<Accesorio> listarActivos() {
        return accesorioRepository.findByActivoTrue();
    }

    public Accesorio guardar(Accesorio accesorio) {
        if (accesorio.getActivo() == null) {
            accesorio.setActivo(true);
        }
        return accesorioRepository.save(accesorio);
    }

    public Optional<Accesorio> buscarPorId(Long id) {
        return accesorioRepository.findById(id);
    }

    public void eliminar(Long id) {
        accesorioRepository.deleteById(id);
    }

    public void desactivar(Long id) {
        Optional<Accesorio> accesorioOptional = accesorioRepository.findById(id);

        if (accesorioOptional.isPresent()) {
            Accesorio accesorio = accesorioOptional.get();
            accesorio.setActivo(false);
            accesorioRepository.save(accesorio);
        }
    }
}