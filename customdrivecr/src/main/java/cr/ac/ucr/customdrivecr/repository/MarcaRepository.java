package cr.ac.ucr.customdrivecr.repository;

import cr.ac.ucr.customdrivecr.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    List<Marca> findByActivoTrue();

    List<Marca> findByNombreContainingIgnoreCase(String nombre);
}