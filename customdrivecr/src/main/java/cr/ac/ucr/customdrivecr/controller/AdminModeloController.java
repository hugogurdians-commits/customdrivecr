package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.Modelo;
import cr.ac.ucr.customdrivecr.service.MarcaService;
import cr.ac.ucr.customdrivecr.service.ModeloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/modelos")
public class AdminModeloController {

    private final ModeloService modeloService;
    private final MarcaService marcaService;

    public AdminModeloController(ModeloService modeloService, MarcaService marcaService) {
        this.modeloService = modeloService;
        this.marcaService = marcaService;
    }

    @GetMapping
    public String listarModelos(Model model) {
        model.addAttribute("modelos", modeloService.listarTodos());
        model.addAttribute("modelo", new Modelo());
        model.addAttribute("marcas", marcaService.listarActivas());

        return "admin/modelos";
    }

    @PostMapping("/guardar")
    public String guardarModelo(@ModelAttribute Modelo modelo) {
        modeloService.guardar(modelo);
        return "redirect:/admin/modelos";
    }

    @GetMapping("/editar/{id}")
    public String editarModelo(@PathVariable Long id, Model model) {
        Modelo modelo = modeloService.buscarPorId(id).orElse(new Modelo());

        model.addAttribute("modelos", modeloService.listarTodos());
        model.addAttribute("modelo", modelo);
        model.addAttribute("marcas", marcaService.listarActivas());

        return "admin/modelos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarModelo(@PathVariable Long id) {
        modeloService.eliminar(id);
        return "redirect:/admin/modelos";
    }
}