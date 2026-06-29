package cr.ac.ucr.customdrivecr.repository;

import cr.ac.ucr.customdrivecr.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {

    List<Modelo> findByActivoTrue();

    List<Modelo> findByMarcaId(Long marcaId);

    List<Modelo> findByNombreContainingIgnoreCase(String nombre);
}