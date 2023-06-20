package com.tingeso.acopioservice.repositories;

import com.tingeso.acopioservice.entities.AcopioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcopioRepository extends JpaRepository<AcopioEntity,Integer> {
    @Override
    List<AcopioEntity> findAll();

}
