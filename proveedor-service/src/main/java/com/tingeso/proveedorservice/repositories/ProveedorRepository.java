package com.tingeso.proveedor.repositories;

import com.tingeso.proveedor.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity,Integer> {

    @Query("SELECT p FROM ProveedorEntity p WHERE p.id_proveedor = :id_proveedor")
    ProveedorEntity findByIdProveedor(@Param("id_proveedor") Integer id_proveedor);

    @Query("select e.categoria from ProveedorEntity e where e.id_proveedor = :id_proveedor")
    String findCategory(@Param("id_proveedor") Integer id_proveedor);

    String findNombre(Integer idProveedor);
}
