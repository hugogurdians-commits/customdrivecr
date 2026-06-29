package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.Marca;
import cr.ac.ucr.customdrivecr.service.MarcaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/marcas")
public class AdminMarcaController {

    private final MarcaService marcaService;

    public AdminMarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public String listarMarcas(Model model) {
        model.addAttribute("marcas", marcaService.listarTodas());
        model.addAttribute("marca", new Marca());
        return "admin/marcas";
    }

    @PostMapping("/guardar")
    public String guardarMarca(@ModelAttribute Marca marca) {
        marcaService.guardar(marca);
        return "redirect:/admin/marcas";
    }

    @GetMapping("/editar/{id}")
    public String editarMarca(@PathVariable Long id, Model model) {
        Marca marca = marcaService.buscarPorId(id).orElse(new Marca());

        model.addAttribute("marcas", marcaService.listarTodas());
        model.addAttribute("marca", marca);

        return "admin/marcas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMarca(@PathVariable Long id) {
        marcaService.eliminar(id);
        return "redirect:/admin/marcas";
    }
}