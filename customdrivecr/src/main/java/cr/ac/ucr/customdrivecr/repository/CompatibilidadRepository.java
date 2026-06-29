package cr.ac.ucr.customdrivecr.repository;

import cr.ac.ucr.customdrivecr.entity.Compatibilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompatibilidadRepository extends JpaRepository<Compatibilidad, Long> {

    List<Compatibilidad> findByActivoTrue();

    List<Compatibilidad> findByVehiculoId(Long vehiculoId);

    List<Compatibilidad> findByAccesorioId(Long accesorioId);

    List<Compatibilidad> findByVehiculoIdAndActivoTrue(Long vehiculoId);
}