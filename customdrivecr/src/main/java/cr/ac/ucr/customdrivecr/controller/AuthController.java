package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.Usuario;
import cr.ac.ucr.customdrivecr.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String correo,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        Optional<Usuario> usuarioOptional = usuarioService.login(correo, password);

        if (usuarioOptional.isEmpty()) {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
            return "login";
        }

        Usuario usuario = usuarioOptional.get();

        session.setAttribute("usuarioLogueado", usuario);
        session.setAttribute("rolUsuario", usuario.getRol());
        session.setAttribute("nombreUsuario", usuario.getNombre());

        if (usuario.getRol().equalsIgnoreCase("ADMIN")) {
            return "redirect:/admin/dashboard";
        }

        return "redirect:/seleccion-vehiculo";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}