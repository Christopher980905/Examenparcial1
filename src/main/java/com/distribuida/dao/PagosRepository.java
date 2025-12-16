package com.distribuida.dao;

import com.distribuida.model.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagosRepository extends JpaRepository<Pagos, Integer> {
}
