package cr.ac.ucr.customdrivecr.repository;

import cr.ac.ucr.customdrivecr.entity.Accesorio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccesorioRepository extends JpaRepository<Accesorio, Long> {

    List<Accesorio> findByActivoTrue();

    List<Accesorio> findByCategoriaContainingIgnoreCase(String categoria);
}