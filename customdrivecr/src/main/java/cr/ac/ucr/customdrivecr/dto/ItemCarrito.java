package cr.ac.ucr.customdrivecr.dto;

import cr.ac.ucr.customdrivecr.entity.Accesorio;
import java.math.BigDecimal;

public class ItemCarrito {

    private Accesorio accesorio;
    private int cantidad;

    public ItemCarrito() {
    }

    public ItemCarrito(Accesorio accesorio, int cantidad) {
        this.accesorio = accesorio;
        this.cantidad = cantidad;
    }

    public Accesorio getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(Accesorio accesorio) {
        this.accesorio = accesorio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        if (accesorio == null || accesorio.getPrecio() == null) {
            return BigDecimal.ZERO;
        }

        return accesorio.getPrecio().multiply(BigDecimal.valueOf(cantidad));
    }
}