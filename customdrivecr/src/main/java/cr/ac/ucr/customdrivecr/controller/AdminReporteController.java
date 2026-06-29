package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminReporteController {

    private final PedidoService pedidoService;

    public AdminReporteController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/admin/reporte-ventas")
    public String mostrarReporteVentas(Model model) {

        model.addAttribute("totalPedidos", pedidoService.contarPedidos());
        model.addAttribute("pedidosPendientes", pedidoService.contarPorEstado("PENDIENTE"));
        model.addAttribute("pedidosConfirmados", pedidoService.contarPorEstado("CONFIRMADO"));
        model.addAttribute("pedidosEntregados", pedidoService.contarPorEstado("ENTREGADO"));
        model.addAttribute("pedidosCancelados", pedidoService.contarPorEstado("CANCELADO"));
        model.addAttribute("totalVendido", pedidoService.calcularTotalVendido());
        model.addAttribute("pedidos", pedidoService.listarTodos());

        return "admin/reporte-ventas";
    }
}