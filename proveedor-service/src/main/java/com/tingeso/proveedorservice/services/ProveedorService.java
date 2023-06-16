package com.tingeso.proveedorservice.services;

import com.tingeso.proveedorservice.entities.ProveedorEntity;
import com.tingeso.proveedorservice.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public ProveedorEntity saveProveedor(Integer id_proveedor, String nombre, String categoria, String retencion) {
        ProveedorEntity proveedor = new ProveedorEntity(id_proveedor, nombre, categoria, retencion);
        if (!estaRegistrado(proveedor)) {
            return proveedorRepository.save(proveedor);
        }else
            return null;
    }

    public List<ProveedorEntity> listaProveedores(){
        return proveedorRepository.findAll();
    }

    // Método que ve si el proveedor a ingresar ya se encuentra en la lista de proveedores
    public boolean estaRegistrado(ProveedorEntity proveedor){
        ProveedorEntity newProveedor = proveedorRepository.findById(proveedor.getId_proveedor()).orElse(null);
        return newProveedor != null;
    }

    public void delete(ProveedorEntity proveedor){
        if(estaRegistrado(proveedor)){
            proveedorRepository.delete(proveedor);
        }
    }
}
