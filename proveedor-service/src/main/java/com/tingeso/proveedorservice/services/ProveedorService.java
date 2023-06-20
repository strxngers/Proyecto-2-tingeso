package com.tingeso.proveedorservice.services;

import com.tingeso.proveedorservice.entities.ProveedorEntity;
import com.tingeso.proveedorservice.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public ProveedorEntity saveProveedor(ProveedorEntity proveedor) {
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

    public ProveedorEntity getByCodigo(Integer codigo){
        ProveedorEntity proveedorEntregar = new ProveedorEntity();
        List<ProveedorEntity> proveedores = listaProveedores();
        for(int i = 0; i< proveedores.size(); i++){
            if(proveedores.get(i).getId_proveedor().equals(codigo)){
                proveedorEntregar = proveedores.get(i);
            }
        }
        return proveedorEntregar;
    }

    public void delete(ProveedorEntity proveedor){
        if(estaRegistrado(proveedor)){
            proveedorRepository.delete(proveedor);
        }
    }
}
