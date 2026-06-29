package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.DetallePedido;
import cr.ac.ucr.customdrivecr.entity.Pedido;
import cr.ac.ucr.customdrivecr.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/pedidos")
public class AdminPedidoController {

    private final PedidoService pedidoService;

    public AdminPedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.listarTodos();

        Map<Long, List<DetallePedido>> detallesPorPedido = new HashMap<>();

        for (Pedido pedido : pedidos) {
            detallesPorPedido.put(
                    pedido.getId(),
                    pedidoService.buscarDetallesPorPedido(pedido.getId())
            );
        }

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("detallesPorPedido", detallesPorPedido);

        return "admin/pedidos";
    }

    @GetMapping("/detalle/{id}")
    public String verDetallePedido(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id).orElse(null);

        if (pedido == null) {
            return "redirect:/admin/pedidos";
        }

        model.addAttribute("pedido", pedido);
        model.addAttribute("detalles", pedidoService.buscarDetallesPorPedido(id));

        return "admin/detalle-pedido";
    }

    @GetMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        pedidoService.cambiarEstado(id, estado);
        return "redirect:/admin/pedidos";
    }
}