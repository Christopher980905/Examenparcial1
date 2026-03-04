package com.distribuida.service;


import com.distribuida.dao.CarritoItemRepository;
import com.distribuida.dao.CarritoRepository;
import com.distribuida.dao.ClienteRepository;
import com.distribuida.dao.ProductosRepository;
import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarritoServiceImpl implements CarritoService{


    private final CarritoRepository carritoRepository;
    private final CarritoItemRepository carritoItemRepository;
    private final ClienteRepository clienteRepository;
    private final ProductosRepository productosRepository;

    private static final BigDecimal IVA = new BigDecimal("0.15"); // ajusta si corresponde

    public CarritoServiceImpl(CarritoRepository carritoRepository,
                              CarritoItemRepository carritoItemRepository,
                              ClienteRepository clienteRepository,
                              ProductosRepository productosRepository) {
        this.carritoRepository = carritoRepository;
        this.carritoItemRepository = carritoItemRepository;
        this.clienteRepository = clienteRepository;
        this.productosRepository = productosRepository;
    }

    @Override
    @Transactional
    public Carrito getOrCreateByClienteId(int clienteId, String token) {
        var cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + clienteId));

        var carritoOpt = carritoRepository.findByCliente(cliente);
        if (carritoOpt.isPresent()) return carritoOpt.get();

        var carrito = new Carrito();
        carrito.setCliente(cliente);
        carrito.setToken(token);
        carrito.recomprobacionTotalesCompat(); // (ver método al final) — opcional
        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public Carrito addItem(int clienteId, int productoId, int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");

        var carrito = getOrCreateByClienteId(clienteId, null);
        var productos = productosRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado: " + productoId));

        var itemOpt = carritoItemRepository.findByCarritoAndProducto(carrito, productos);
        if (itemOpt.isPresent()) {
            var item = itemOpt.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.setPrecioUnitario(BigDecimal.valueOf(productos.getPrecio()));
            item.calcTotal();                         // <-- CALCULAR ANTES
            carritoItemRepository.save(item);
        } else {
            var item = new CarritoItem();
            item.setCarrito(carrito);
            item.setProducto(productos);
            item.setCantidad(cantidad);
            item.setPrecioUnitario(BigDecimal.valueOf(productos.getPrecio()));
            item.calcTotal();                         // <-- CALCULAR ANTES
            carrito.getItems().add(item);
        }

        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);
    }


    @Override
    @Transactional
    public Carrito updateItemCantidad(int clienteId, long carritoItemId, int nuevaCantidad) {
        if (nuevaCantidad < 0) throw new IllegalArgumentException("Cantidad no puede ser negativa");

        var carrito = getByClienteId(clienteId);
        var item = carritoItemRepository.findById(carritoItemId)
                .orElseThrow(() -> new IllegalArgumentException("Item no encontrado: " + carritoItemId));

        if (!item.getCarrito().getIdCarrito().equals(carrito.getIdCarrito())) {
            throw new IllegalStateException("El item no pertenece al carrito del cliente");
        }

        if (nuevaCantidad == 0) {
            carrito.getItems().remove(item);
            carritoItemRepository.delete(item);
        } else {
            item.setCantidad(nuevaCantidad);
            carritoItemRepository.save(item);
        }

        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public void removeItem(int clienteId, long carritoItemId) {
        updateItemCantidad(clienteId, carritoItemId, 0);
    }

    @Override
    @Transactional
    public void clear(int clienteId) {
        var carrito = getByClienteId(clienteId);
        carrito.getItems().clear();
        carrito.recomputarTotales(IVA);
        carritoRepository.save(carrito);
    }

    @Override
    @Transactional(readOnly = true)
    public Carrito getByClienteId(int clienteId) {
        var cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + clienteId));
        return carritoRepository.findByCliente(cliente)
                .orElseGet(() -> { // devolver vacío sin crear
                    var c = new Carrito();
                    c.setCliente(cliente);
                    return c;
                });
    }



    @Override
    @Transactional
    public Carrito getOrCreateByToken(String token) {
        if (token == null || token.isBlank())
            throw new IllegalArgumentException("Token de carrito requerido");

        return carritoRepository.findByToken(token).orElseGet(() -> {
            var c = new Carrito();
            c.setToken(token);
            c.setSubtotal(BigDecimal.ZERO);
            c.setDescuento(BigDecimal.ZERO);
            c.setImpuestos(BigDecimal.ZERO);
            c.setTotal(BigDecimal.ZERO);
            return carritoRepository.save(c);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Carrito getByToken(String token) {
        return carritoRepository.findByToken(token)
                .orElseGet(() -> {
                    var c = new Carrito();
                    c.setToken(token);
                    c.setSubtotal(BigDecimal.ZERO);
                    c.setDescuento(BigDecimal.ZERO);
                    c.setImpuestos(BigDecimal.ZERO);
                    c.setTotal(BigDecimal.ZERO);
                    return c; // no persisto en GET
                });
    }

    @Override
    @Transactional
    public Carrito addItem(String token, int productoId, int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");

        var carrito = getOrCreateByToken(token);
        var productos = productosRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + productoId));

        var itemOpt = carritoItemRepository.findByCarritoAndProducto(carrito, productos);
        if (itemOpt.isPresent()) {
            var item = itemOpt.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.setPrecioUnitario(BigDecimal.valueOf(productos.getPrecio()));
            item.calcTotal();
            carritoItemRepository.save(item);
        } else {
            var item = new CarritoItem();
            item.setCarrito(carrito);
            item.setProducto(productos);
            item.setCantidad(cantidad);
            item.setPrecioUnitario(BigDecimal.valueOf(productos.getPrecio()));
            item.calcTotal();
            carrito.getItems().add(item);
        }

        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public Carrito updateItemCantidad(String token, long carritoItemId, int nuevaCantidad) {
        var carrito = getOrCreateByToken(token);
        var item = carritoItemRepository.findById(carritoItemId)
                .orElseThrow(() -> new IllegalArgumentException("Item no encontrado: " + carritoItemId));

        if (!item.getCarrito().getIdCarrito().equals(carrito.getIdCarrito()))
            throw new IllegalStateException("El item no pertenece al carrito del token");

        if (nuevaCantidad <= 0) {
            carrito.getItems().remove(item);
            carritoItemRepository.delete(item);
        } else {
            item.setCantidad(nuevaCantidad);
            item.calcTotal();
            carritoItemRepository.save(item);
        }

        carrito.recomputarTotales(IVA);
        return carritoRepository.save(carrito);
    }

    @Override
    @Transactional
    public void removeItem(String token, long carritoItemId) {
        updateItemCantidad(token, carritoItemId, 0);
    }

    @Override
    @Transactional
    public void clearByToken(String token) {
        var carrito = getOrCreateByToken(token);
        carrito.getItems().clear();
        carrito.setSubtotal(BigDecimal.ZERO);
        carrito.setDescuento(BigDecimal.ZERO);
        carrito.setImpuestos(BigDecimal.ZERO);
        carrito.setTotal(BigDecimal.ZERO);
        carritoRepository.save(carrito);
    }

}
