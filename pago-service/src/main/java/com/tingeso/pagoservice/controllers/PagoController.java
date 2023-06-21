package com.tingeso.pagoservice.controllers;


import com.tingeso.pagoservice.entities.PagoEntity;
import com.tingeso.pagoservice.models.AcopioModel;
import com.tingeso.pagoservice.models.ProveedorModel;
import com.tingeso.pagoservice.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<PagoEntity>> getPagos(){
        List<PagoEntity> pagos = pagoService.planillaPago();
        if(pagos.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/prueba")
    public String str(){
        return "a";
    }

    @GetMapping("/getacopio/{idprov}")
    public ResponseEntity<AcopioModel> acopioByCodigo(@PathVariable Integer idprov){
        return ResponseEntity.ok(pagoService.acopioByProveedor(idprov));
    }
    @GetMapping("/a/{idprov}")
    public ProveedorModel proveedorById(@PathVariable Integer idprov){
        return pagoService.proveedorById(idprov);
    }



}
