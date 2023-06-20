package com.tingeso.proveedorservice.controllers;

import com.tingeso.proveedorservice.entities.ProveedorEntity;
import com.tingeso.proveedorservice.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorEntity>> getAll(){
        List<ProveedorEntity> proveedores = proveedorService.listaProveedores();
        if(proveedores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping()
    public ResponseEntity<ProveedorEntity> save(@RequestBody ProveedorEntity proveedor){
        return ResponseEntity.ok(proveedorService.saveProveedor(proveedor));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProveedorEntity> getByCodigo(@PathVariable Integer codigo) {
        ProveedorEntity proveedor = proveedorService.getByCodigo(codigo);
        if (proveedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(proveedor);
    }
}
