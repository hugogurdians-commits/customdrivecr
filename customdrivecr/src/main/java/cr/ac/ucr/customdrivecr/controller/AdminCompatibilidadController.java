package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.Compatibilidad;
import cr.ac.ucr.customdrivecr.service.AccesorioService;
import cr.ac.ucr.customdrivecr.service.CompatibilidadService;
import cr.ac.ucr.customdrivecr.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/compatibilidades")
public class AdminCompatibilidadController {

    private final CompatibilidadService compatibilidadService;
    private final VehiculoService vehiculoService;
    private final AccesorioService accesorioService;

    public AdminCompatibilidadController(
            CompatibilidadService compatibilidadService,
            VehiculoService vehiculoService,
            AccesorioService accesorioService) {
        this.compatibilidadService = compatibilidadService;
        this.vehiculoService = vehiculoService;
        this.accesorioService = accesorioService;
    }

    @GetMapping
    public String listarCompatibilidades(Model model) {
        model.addAttribute("compatibilidades", compatibilidadService.listarTodas());
        model.addAttribute("compatibilidad", new Compatibilidad());
        model.addAttribute("vehiculos", vehiculoService.listarActivos());
        model.addAttribute("accesorios", accesorioService.listarActivos());

        return "admin/compatibilidades";
    }

    @PostMapping("/guardar")
    public String guardarCompatibilidad(@ModelAttribute Compatibilidad compatibilidad) {
        compatibilidadService.guardar(compatibilidad);
        return "redirect:/admin/compatibilidades";
    }

    @GetMapping("/editar/{id}")
    public String editarCompatibilidad(@PathVariable Long id, Model model) {
        Compatibilidad compatibilidad = compatibilidadService.buscarPorId(id).orElse(new Compatibilidad());

        model.addAttribute("compatibilidades", compatibilidadService.listarTodas());
        model.addAttribute("compatibilidad", compatibilidad);
        model.addAttribute("vehiculos", vehiculoService.listarActivos());
        model.addAttribute("accesorios", accesorioService.listarActivos());

        return "admin/compatibilidades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCompatibilidad(@PathVariable Long id) {
        compatibilidadService.eliminar(id);
        return "redirect:/admin/compatibilidades";
    }
}