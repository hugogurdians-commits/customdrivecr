package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.dto.ItemCarrito;
import cr.ac.ucr.customdrivecr.entity.Pedido;
import cr.ac.ucr.customdrivecr.entity.Usuario;
import cr.ac.ucr.customdrivecr.service.PedidoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/confirmar")
    public String mostrarConfirmacion(HttpSession session, Model model) {
        List<ItemCarrito> carrito = obtenerCarrito(session);

        if (carrito.isEmpty()) {
            return "redirect:/carrito";
        }

        model.addAttribute("carrito", carrito);
        model.addAttribute("total", calcularTotal(carrito));

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario != null) {
            model.addAttribute("nombreCliente", usuario.getNombre());
            model.addAttribute("correoCliente", usuario.getCorreo());
        }

        return "confirmar-pedido";
    }

    @PostMapping("/confirmar")
    public String confirmarPedido(@RequestParam String nombreCliente,
                                  @RequestParam String correoCliente,
                                  @RequestParam String telefonoCliente,
                                  HttpSession session) {

        List<ItemCarrito> carrito = obtenerCarrito(session);

        if (carrito.isEmpty()) {
            return "redirect:/carrito";
        }

        Pedido pedido = pedidoService.crearPedido(nombreCliente, correoCliente, telefonoCliente, carrito);

        session.removeAttribute("carrito");

        return "redirect:/pedidos/factura/" + pedido.getId();
    }

    @GetMapping("/factura/{id}")
    public String mostrarFactura(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id).orElse(null);

        if (pedido == null) {
            return "redirect:/seleccion-vehiculo";
        }

        model.addAttribute("pedido", pedido);
        model.addAttribute("detalles", pedidoService.buscarDetallesPorPedido(id));

        return "factura";
    }

    @SuppressWarnings("unchecked")
    private List<ItemCarrito> obtenerCarrito(HttpSession session) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }

        return carrito;
    }

    private BigDecimal calcularTotal(List<ItemCarrito> carrito) {
        BigDecimal total = BigDecimal.ZERO;

        for (ItemCarrito item : carrito) {
            total = total.add(item.getSubtotal());
        }

        return total;
    }
}