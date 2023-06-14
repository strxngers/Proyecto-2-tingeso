package com.tingeso.pagoservice.repositories;

import com.tingeso.pagoservice.entities.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<PagoEntity,Integer> {
}
