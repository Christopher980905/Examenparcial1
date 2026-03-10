package com.distribuida.service.util;

import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.PedidoDetalle;
import com.distribuida.model.Pedidos;

import java.util.Date;

public class CheckoutMapper {

    private CheckoutMapper() {}

    public static Pedidos construirFacturaDesdeCarrito(Carrito carrito, String numFactura, double tasaIva) {
        Pedidos f = new Pedidos();
        f.setNumFactura(numFactura);
        f.setFechapedido(new Date());
        f.setCliente(carrito.getCliente());

        double subtotal = carrito.getItems().stream()
                .mapToDouble(i -> i.getTotal().doubleValue())
                .sum();

        double iva = Math.max(0, subtotal) * tasaIva;
        double total = subtotal + iva;

        f.setTotalneto(subtotal);
        f.setIVA(iva);
        f.setMonto_pagar(total);

        return f;
    }

    public static PedidoDetalle construirDetalle(Pedidos pedidos, CarritoItem item) {
        PedidoDetalle d = new PedidoDetalle();
        d.setPedidos(pedidos);
        d.setProductos(item.getProducto());
        d.setCantidad(item.getCantidad());
        d.setSubtotal(item.getTotal().doubleValue()); // tu esquema usa Double
        return d;
    }
}
