package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.dto.ItemCarrito;
import cr.ac.ucr.customdrivecr.entity.Accesorio;
import cr.ac.ucr.customdrivecr.service.AccesorioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private final AccesorioService accesorioService;

    public CarritoController(AccesorioService accesorioService) {
        this.accesorioService = accesorioService;
    }

    @GetMapping
    public String verCarrito(HttpSession session, Model model) {
        List<ItemCarrito> carrito = obtenerCarrito(session);

        model.addAttribute("carrito", carrito);
        model.addAttribute("total", calcularTotal(carrito));
        model.addAttribute("carritoValido", carritoValido(carrito));

        /*
         * Aquí se manda el vehículo seleccionado al carrito.html.
         */
        model.addAttribute("vehiculoSeleccionado", session.getAttribute("vehiculoSeleccionado"));

        return "carrito";
    }

    @GetMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id, HttpSession session) {
        Accesorio accesorio = accesorioService.buscarPorId(id).orElse(null);

        if (accesorio == null) {
            return "redirect:/carrito";
        }

        if (accesorio.getStock() == null || accesorio.getStock() <= 0) {
            return "redirect:/carrito?sinStock=true";
        }

        List<ItemCarrito> carrito = obtenerCarrito(session);

        boolean encontrado = false;

        for (ItemCarrito item : carrito) {
            if (item.getAccesorio().getId().equals(id)) {
                encontrado = true;

                if (item.getCantidad() < accesorio.getStock()) {
                    item.setCantidad(item.getCantidad() + 1);
                }

                item.setAccesorio(accesorio);
                break;
            }
        }

        if (!encontrado) {
            carrito.add(new ItemCarrito(accesorio, 1));
        }

        session.setAttribute("carrito", carrito);

        return "redirect:/carrito";
    }

    @GetMapping("/aumentar/{id}")
    public String aumentarCantidad(@PathVariable Long id, HttpSession session) {
        Accesorio accesorioActual = accesorioService.buscarPorId(id).orElse(null);

        if (accesorioActual == null) {
            return "redirect:/carrito";
        }

        if (accesorioActual.getStock() == null || accesorioActual.getStock() <= 0) {
            return "redirect:/carrito?sinStock=true";
        }

        List<ItemCarrito> carrito = obtenerCarrito(session);

        for (ItemCarrito item : carrito) {
            if (item.getAccesorio().getId().equals(id)) {

                if (item.getCantidad() < accesorioActual.getStock()) {
                    item.setCantidad(item.getCantidad() + 1);
                }

                item.setAccesorio(accesorioActual);
                break;
            }
        }

        session.setAttribute("carrito", carrito);

        return "redirect:/carrito";
    }

    @GetMapping("/disminuir/{id}")
    public String disminuirCantidad(@PathVariable Long id, HttpSession session) {
        List<ItemCarrito> carrito = obtenerCarrito(session);

        for (ItemCarrito item : carrito) {
            if (item.getAccesorio().getId().equals(id)) {

                if (item.getCantidad() > 1) {
                    item.setCantidad(item.getCantidad() - 1);
                }

                break;
            }
        }

        session.setAttribute("carrito", carrito);

        return "redirect:/carrito";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDelCarrito(@PathVariable Long id, HttpSession session) {
        List<ItemCarrito> carrito = obtenerCarrito(session);

        carrito.removeIf(item -> item.getAccesorio().getId().equals(id));

        session.setAttribute("carrito", carrito);

        return "redirect:/carrito";
    }

    @GetMapping("/vaciar")
    public String vaciarCarrito(HttpSession session) {
        session.removeAttribute("carrito");

        return "redirect:/carrito";
    }

    @SuppressWarnings("unchecked")
    private List<ItemCarrito> obtenerCarrito(HttpSession session) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
            session.setAttribute("carrito", carrito);
        }

        actualizarStockActual(carrito);

        return carrito;
    }

    private void actualizarStockActual(List<ItemCarrito> carrito) {
        for (ItemCarrito item : carrito) {
            Accesorio accesorioActual = accesorioService.buscarPorId(item.getAccesorio().getId()).orElse(null);

            if (accesorioActual != null) {
                item.setAccesorio(accesorioActual);
            }
        }
    }

    private BigDecimal calcularTotal(List<ItemCarrito> carrito) {
        BigDecimal total = BigDecimal.ZERO;

        for (ItemCarrito item : carrito) {
            total = total.add(item.getSubtotal());
        }

        return total;
    }

    private boolean carritoValido(List<ItemCarrito> carrito) {
        if (carrito == null || carrito.isEmpty()) {
            return false;
        }

        for (ItemCarrito item : carrito) {

            if (item.getAccesorio() == null) {
                return false;
            }

            if (item.getAccesorio().getStock() == null) {
                return false;
            }

            if (item.getAccesorio().getStock() <= 0) {
                return false;
            }

            if (item.getCantidad() <= 0) {
                return false;
            }

            if (item.getCantidad() > item.getAccesorio().getStock()) {
                return false;
            }
        }

        return true;
    }
}