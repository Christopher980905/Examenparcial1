package com.distribuida.service;

import com.distribuida.dao.CarritoRepository;
import com.distribuida.dao.PedidoDetalleRepository;
import com.distribuida.dao.PedidosRepository;
import com.distribuida.dao.ProductosRepository;
import com.distribuida.model.Pedidos;
import com.distribuida.service.util.CheckoutMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Service
public class GuestCheckoutServiceImpl implements GuestCheckoutService {

    private final CarritoRepository carritoRepository;
    private final PedidosRepository pedidosRepository;
    private final PedidoDetalleRepository pedidoDetalleRepository;
    private final ProductosRepository productosRepository;

    private static final double IVA = 0.15d;

    public GuestCheckoutServiceImpl(CarritoRepository carritoRepository,
                                    PedidosRepository pedidosRepository,
                                    PedidoDetalleRepository pedidoDetalleRepository,
                                    ProductosRepository productosRepository) {
        this.carritoRepository = carritoRepository;
        this.pedidosRepository = pedidosRepository;
        this.pedidoDetalleRepository = pedidoDetalleRepository;
        this.productosRepository = productosRepository;
    }

    @Override
    @Transactional
    public Pedidos checkoutByToken(String token) {
        var carrito = carritoRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("No existe carrito para el token"));

        if (carrito.getItems() == null || carrito.getItems().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        // Validar stock
        for (var item : carrito.getItems()) {
            var productos = item.getProducto();
            if (productos.getStock() < item.getCantidad()) {
                throw new IllegalStateException("Stock insuficiente para: " + productos.getNombre());
            }
        }
        // Descontar stock
        for (var item : carrito.getItems()) {
            var productos = item.getProducto();
            productos.setStock(productos.getStock() - item.getCantidad());
            productosRepository.save(productos);
        }

        // Número de factura
        String numFactura = "ROAD-" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
                .format(LocalDateTime.now());

        // Construir factura desde carrito (cliente puede ser null si tu esquema lo permite)
        var pedidos = CheckoutMapper.construirFacturaDesdeCarrito(carrito, numFactura, IVA);
        // Si tu Factura requiere cliente NOT NULL, aquí puedes forzar uno invitado:
        // if (factura.getCliente() == null) factura.setCliente(ensureGuestCliente(token));

        pedidos = pedidosRepository.save(pedidos);

        // Detalles
        for (var item : carrito.getItems()) {
            var det = CheckoutMapper.construirDetalle(pedidos, item);
            pedidoDetalleRepository.save(det);
        }

        // Limpiar carrito
        carrito.getItems().clear();
        carritoRepository.save(carrito);

        return pedidos;
    }

    // Si tu tabla FACTURA exige cliente != null, crea/usa un cliente invitado:
    // private final ClienteRepository clienteRepository;
    // private Cliente ensureGuestCliente(String token) { ... }




}
