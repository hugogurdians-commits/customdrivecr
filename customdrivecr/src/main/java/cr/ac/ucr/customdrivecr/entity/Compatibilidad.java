package cr.ac.ucr.customdrivecr.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compatibilidades")
public class Compatibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String notas;

    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "accesorio_id", nullable = false)
    private Accesorio accesorio;

    public Compatibilidad() {
    }

    public Compatibilidad(Long id, String notas, Boolean activo, Vehiculo vehiculo, Accesorio accesorio) {
        this.id = id;
        this.notas = notas;
        this.activo = activo;
        this.vehiculo = vehiculo;
        this.accesorio = accesorio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Accesorio getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(Accesorio accesorio) {
        this.accesorio = accesorio;
    }
}