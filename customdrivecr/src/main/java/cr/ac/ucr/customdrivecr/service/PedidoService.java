package cr.ac.ucr.customdrivecr.service;

import cr.ac.ucr.customdrivecr.dto.ItemCarrito;
import cr.ac.ucr.customdrivecr.entity.Accesorio;
import cr.ac.ucr.customdrivecr.entity.DetallePedido;
import cr.ac.ucr.customdrivecr.entity.Pedido;
import cr.ac.ucr.customdrivecr.repository.AccesorioRepository;
import cr.ac.ucr.customdrivecr.repository.DetallePedidoRepository;
import cr.ac.ucr.customdrivecr.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final AccesorioRepository accesorioRepository;

    public PedidoService(PedidoRepository pedidoRepository,
            DetallePedidoRepository detallePedidoRepository,
            AccesorioRepository accesorioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.accesorioRepository = accesorioRepository;
    }

    public Pedido crearPedido(String nombreCliente, String correoCliente, String telefonoCliente,
            List<ItemCarrito> carrito) {

        BigDecimal total = BigDecimal.ZERO;

        for (ItemCarrito item : carrito) {
            total = total.add(item.getSubtotal());
        }

        Pedido pedido = new Pedido();
        pedido.setNombreCliente(nombreCliente);
        pedido.setCorreoCliente(correoCliente);
        pedido.setTelefonoCliente(telefonoCliente);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setTotal(total);
        pedido.setEstado("PENDIENTE");

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        for (ItemCarrito item : carrito) {
            Accesorio accesorio = item.getAccesorio();

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedidoGuardado);
            detalle.setAccesorio(accesorio);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(accesorio.getPrecio());
            detalle.setSubtotal(item.getSubtotal());

            detallePedidoRepository.save(detalle);

            if (accesorio.getStock() != null) {
                accesorio.setStock(accesorio.getStock() - item.getCantidad());
                accesorioRepository.save(accesorio);
            }
        }

        return pedidoGuardado;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<DetallePedido> buscarDetallesPorPedido(Long pedidoId) {
        return detallePedidoRepository.findByPedidoId(pedidoId);
    }

    public void cambiarEstado(Long id, String estado) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setEstado(estado);
            pedidoRepository.save(pedido);
        }
    }

    public long contarPedidos() {
        return pedidoRepository.count();
    }

    public long contarPorEstado(String estado) {
        return pedidoRepository.countByEstado(estado);
    }

    public BigDecimal calcularTotalVendido() {
        BigDecimal total = BigDecimal.ZERO;

        for (Pedido pedido : pedidoRepository.findAll()) {
            if (pedido.getTotal() != null && !pedido.getEstado().equalsIgnoreCase("CANCELADO")) {
                total = total.add(pedido.getTotal());
            }
        }

        return total;
    }

}