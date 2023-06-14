package com.tingeso.proveedorservice.controllers;

import com.tingeso.proveedorservice.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tingeso.proveedor.entities.ProveedorEntity;

import java.util.List;

@RestController
@RequestMapping("/proveedor")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<ProveedorEntity>> getAll() {
        List<ProveedorEntity> proveedores = proveedorService.listaProveedores();
        if (proveedores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(proveedores);
    }

    @PostMapping
    public ResponseEntity<ProveedorEntity> saveProveedor(@RequestBody ProveedorEntity proveedor){
        return ResponseEntity.ok(proveedorService.saveProveedor(proveedor));
    }
}
