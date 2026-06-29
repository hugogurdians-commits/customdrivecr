package cr.ac.ucr.customdrivecr.repository;

import cr.ac.ucr.customdrivecr.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    List<Vehiculo> findByActivoTrue();

    List<Vehiculo> findByModeloId(Long modeloId);

    List<Vehiculo> findByModeloMarcaId(Long marcaId);

    List<Vehiculo> findByAnio(Integer anio);
}