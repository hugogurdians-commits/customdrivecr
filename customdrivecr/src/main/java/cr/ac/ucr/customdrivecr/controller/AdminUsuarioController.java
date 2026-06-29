package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.Usuario;
import cr.ac.ucr.customdrivecr.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuarioController {

    private final UsuarioService usuarioService;

    public AdminUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("usuario", new Usuario());

        return "admin/usuarios";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id).orElse(new Usuario());

        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("usuario", usuario);

        return "admin/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivarUsuario(@PathVariable Long id) {
        usuarioService.desactivar(id);
        return "redirect:/admin/usuarios";
    }
}