package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.DetallePedido;
import cr.ac.ucr.customdrivecr.entity.Pedido;
import cr.ac.ucr.customdrivecr.entity.Usuario;
import cr.ac.ucr.customdrivecr.service.PedidoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MisPedidosController {

    private final PedidoService pedidoService;

    public MisPedidosController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/mis-pedidos")
    public String verMisPedidos(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/login?requiereLogin=true";
        }

        String correoUsuario = usuario.getCorreo();

        List<Pedido> todosLosPedidos = pedidoService.listarTodos();
        List<Pedido> misPedidos = new ArrayList<>();

        for (Pedido pedido : todosLosPedidos) {
            if (pedido.getCorreoCliente() != null &&
                    pedido.getCorreoCliente().equalsIgnoreCase(correoUsuario)) {
                misPedidos.add(pedido);
            }
        }

        Map<Long, List<DetallePedido>> detallesPorPedido = new HashMap<>();

        for (Pedido pedido : misPedidos) {
            detallesPorPedido.put(
                    pedido.getId(),
                    pedidoService.buscarDetallesPorPedido(pedido.getId())
            );
        }

        model.addAttribute("pedidos", misPedidos);
        model.addAttribute("detallesPorPedido", detallesPorPedido);
        model.addAttribute("usuario", usuario);

        return "mis-pedidos";
    }
}