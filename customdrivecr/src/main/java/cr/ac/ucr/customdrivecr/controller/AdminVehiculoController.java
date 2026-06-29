package cr.ac.ucr.customdrivecr.controller;

import cr.ac.ucr.customdrivecr.entity.Vehiculo;
import cr.ac.ucr.customdrivecr.service.ModeloService;
import cr.ac.ucr.customdrivecr.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin/vehiculos")
public class AdminVehiculoController {

    private final VehiculoService vehiculoService;
    private final ModeloService modeloService;

    public AdminVehiculoController(VehiculoService vehiculoService, ModeloService modeloService) {
        this.vehiculoService = vehiculoService;
        this.modeloService = modeloService;
    }

    @GetMapping
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoService.listarTodos());
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("modelos", modeloService.listarActivos());

        return "admin/vehiculos";
    }

    @PostMapping("/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo,
                                  @RequestParam("imagenArchivo") MultipartFile imagenArchivo) {

        try {
            if (vehiculo.getId() != null) {
                Vehiculo vehiculoExistente = vehiculoService.buscarPorId(vehiculo.getId()).orElse(null);

                if (vehiculoExistente != null && (imagenArchivo == null || imagenArchivo.isEmpty())) {
                    vehiculo.setImagenUrl(vehiculoExistente.getImagenUrl());
                }
            }

            if (imagenArchivo != null && !imagenArchivo.isEmpty()) {
                String nombreOriginal = imagenArchivo.getOriginalFilename();
                String extension = "";

                if (nombreOriginal != null && nombreOriginal.contains(".")) {
                    extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
                }

                String nombreArchivo = UUID.randomUUID().toString() + extension;

                Path carpetaUploads = Paths.get("uploads/vehiculos");
                Files.createDirectories(carpetaUploads);

                Path rutaArchivo = carpetaUploads.resolve(nombreArchivo);
                Files.write(rutaArchivo, imagenArchivo.getBytes());

                vehiculo.setImagenUrl("/uploads/vehiculos/" + nombreArchivo);
            }

            vehiculoService.guardar(vehiculo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/vehiculos";
    }

    @GetMapping("/editar/{id}")
    public String editarVehiculo(@PathVariable Long id, Model model) {
        Vehiculo vehiculo = vehiculoService.buscarPorId(id).orElse(new Vehiculo());

        model.addAttribute("vehiculos", vehiculoService.listarTodos());
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("modelos", modeloService.listarActivos());

        return "admin/vehiculos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminar(id);
        return "redirect:/admin/vehiculos";
    }
}