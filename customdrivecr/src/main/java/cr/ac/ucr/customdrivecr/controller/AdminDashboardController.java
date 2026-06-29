package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.repository.AccesorioRepository;
import cr.ac.ucr.customdrivecr.repository.CompatibilidadRepository;
import cr.ac.ucr.customdrivecr.repository.MarcaRepository;
import cr.ac.ucr.customdrivecr.repository.ModeloRepository;
import cr.ac.ucr.customdrivecr.repository.UsuarioRepository;
import cr.ac.ucr.customdrivecr.repository.VehiculoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {

    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;
    private final VehiculoRepository vehiculoRepository;
    private final AccesorioRepository accesorioRepository;
    private final CompatibilidadRepository compatibilidadRepository;
    private final UsuarioRepository usuarioRepository;

    public AdminDashboardController(
            MarcaRepository marcaRepository,
            ModeloRepository modeloRepository,
            VehiculoRepository vehiculoRepository,
            AccesorioRepository accesorioRepository,
            CompatibilidadRepository compatibilidadRepository,
            UsuarioRepository usuarioRepository) {

        this.marcaRepository = marcaRepository;
        this.modeloRepository = modeloRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.accesorioRepository = accesorioRepository;
        this.compatibilidadRepository = compatibilidadRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/admin/dashboard")
    public String mostrarDashboard(Model model) {

        model.addAttribute("totalMarcas", marcaRepository.count());
        model.addAttribute("totalModelos", modeloRepository.count());
        model.addAttribute("totalVehiculos", vehiculoRepository.count());
        model.addAttribute("totalAccesorios", accesorioRepository.count());
        model.addAttribute("totalCompatibilidades", compatibilidadRepository.count());
        model.addAttribute("totalUsuarios", usuarioRepository.count());

        return "admin/dashboard";
    }
}