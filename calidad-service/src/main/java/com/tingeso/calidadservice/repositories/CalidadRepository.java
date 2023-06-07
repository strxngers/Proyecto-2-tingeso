package com.tingeso.calidadservice.repositories;

import com.tingeso.calidadservice.entities.CalidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalidadRepository extends JpaRepository<CalidadEntity,Integer> {
}
