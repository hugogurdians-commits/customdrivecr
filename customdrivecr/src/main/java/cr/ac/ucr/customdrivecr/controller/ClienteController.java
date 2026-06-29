package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.Accesorio;
import cr.ac.ucr.customdrivecr.entity.Vehiculo;
import cr.ac.ucr.customdrivecr.service.AccesorioService;
import cr.ac.ucr.customdrivecr.service.CompatibilidadService;
import cr.ac.ucr.customdrivecr.service.VehiculoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    private final VehiculoService vehiculoService;
    private final CompatibilidadService compatibilidadService;
    private final AccesorioService accesorioService;

    public ClienteController(VehiculoService vehiculoService,
                             CompatibilidadService compatibilidadService,
                             AccesorioService accesorioService) {
        this.vehiculoService = vehiculoService;
        this.compatibilidadService = compatibilidadService;
        this.accesorioService = accesorioService;
    }

    @GetMapping("/seleccion-vehiculo")
    public String seleccionarVehiculo(Model model) {
        model.addAttribute("vehiculos", vehiculoService.listarActivos());
        return "seleccion-vehiculo";
    }

    @GetMapping("/catalogo")
    public String verCatalogo(@RequestParam(required = false) Long vehiculoId,
                              Model model,
                              HttpSession session) {

        if (vehiculoId == null) {
            model.addAttribute("mensaje", "Debe seleccionar un vehículo para ver accesorios compatibles.");
            model.addAttribute("compatibilidades", java.util.List.of());
            model.addAttribute("vehiculo", null);
            return "catalogo";
        }

        Vehiculo vehiculo = vehiculoService.buscarPorId(vehiculoId).orElse(null);

        if (vehiculo == null) {
            model.addAttribute("mensaje", "El vehículo seleccionado no existe.");
            model.addAttribute("compatibilidades", java.util.List.of());
            model.addAttribute("vehiculo", null);
            return "catalogo";
        }

        /*
         * Aquí se guarda el vehículo en sesión.
         * Esto permite mostrarlo después en detalle-producto y carrito.
         */
        session.setAttribute("vehiculoSeleccionado", vehiculo);

        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("compatibilidades", compatibilidadService.buscarPorVehiculoId(vehiculoId));

        return "catalogo";
    }

    @GetMapping("/detalle-producto")
    public String verDetalleProducto(@RequestParam Long id,
                                     Model model,
                                     HttpSession session) {

        Accesorio accesorio = accesorioService.buscarPorId(id).orElse(null);

        if (accesorio == null) {
            model.addAttribute("mensaje", "El producto solicitado no existe.");
            return "detalle-producto";
        }

        model.addAttribute("accesorio", accesorio);
        model.addAttribute("vehiculoSeleccionado", session.getAttribute("vehiculoSeleccionado"));

        return "detalle-producto";
    }
}