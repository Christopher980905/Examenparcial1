package com.distribuida.service;

import com.distribuida.model.Pedidos;

public interface GuestCheckoutService {

    Pedidos checkoutByToken(String token);


}
