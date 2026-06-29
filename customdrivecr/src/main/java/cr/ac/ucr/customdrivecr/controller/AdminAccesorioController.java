package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.Accesorio;
import cr.ac.ucr.customdrivecr.service.AccesorioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/accesorios")
public class AdminAccesorioController {

    private final AccesorioService accesorioService;

    public AdminAccesorioController(AccesorioService accesorioService) {
        this.accesorioService = accesorioService;
    }

    @GetMapping
    public String listarAccesorios(Model model) {
        model.addAttribute("accesorios", accesorioService.listarTodos());
        model.addAttribute("accesorio", new Accesorio());
        return "admin/accesorios";
    }

    @PostMapping("/guardar")
    public String guardarAccesorio(@ModelAttribute Accesorio accesorio) {
        accesorioService.guardar(accesorio);
        return "redirect:/admin/accesorios";
    }

    @GetMapping("/editar/{id}")
    public String editarAccesorio(@PathVariable Long id, Model model) {
        Accesorio accesorio = accesorioService.buscarPorId(id).orElse(new Accesorio());

        model.addAttribute("accesorios", accesorioService.listarTodos());
        model.addAttribute("accesorio", accesorio);

        return "admin/accesorios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAccesorio(@PathVariable Long id) {
        accesorioService.eliminar(id);
        return "redirect:/admin/accesorios";
    }
}