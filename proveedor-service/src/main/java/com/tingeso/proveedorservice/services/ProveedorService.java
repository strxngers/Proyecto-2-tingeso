package com.tingeso.proveedorservice.services;

import com.tingeso.proveedor.entities.ProveedorEntity;
import com.tingeso.proveedor.models.AcopioModel;
//import com.tingeso.proveedor.models.CalidadModel;
import com.tingeso.proveedorservice.models.CalidadModel;
import com.tingeso.proveedorservice.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;
    @Autowired
    RestTemplate restTemplate;

    public ProveedorEntity saveProveedor(ProveedorEntity prov) {
        if (!estaRegistrado(prov)) {
            return proveedorRepository.save(prov);
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

    public String obtenerCategoria(Integer id_proveedor){
        return proveedorRepository.findCategory(id_proveedor);
    }

    public ProveedorEntity findByIdProveedor(Integer id_proveedor){
        return proveedorRepository.findByIdProveedor(id_proveedor);
    }

    public String obtenerNombre(Integer id_proveedor){
        return proveedorRepository.findNombre(id_proveedor);
    }

    public List<AcopioModel> getAcopios(Integer id_proveedor) {
        List<AcopioModel> acopios = restTemplate.getForObject("http://acopio-service/acopio/porproveedor/" + id_proveedor, List.class);
        return acopios;
    }

        public List<CalidadModel> getCalidad(Integer id_proveedor) {
        List<CalidadModel> calidad = restTemplate.getForObject("http://calidad-service/calidad/porproveedor/" + id_proveedor, List.class);
        return calidad;
    }
}
