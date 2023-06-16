package com.tingeso.proveedorservice.repositories;

import com.tingeso.proveedorservice.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity,Integer> {
}
