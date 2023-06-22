package com.tingeso.pagoservice.controllers;


import com.tingeso.pagoservice.entities.PagoEntity;
import com.tingeso.pagoservice.models.AcopioModel;
import com.tingeso.pagoservice.models.CalidadModel;
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

    @GetMapping("/getacopio/{idprov}")
    public ResponseEntity<List<AcopioModel>> acopioByCodigo(@PathVariable Integer idprov){
        return ResponseEntity.ok(pagoService.acopiosByProveedor(idprov));
    }

    @GetMapping("/getcalidad/{idprov}")
    public ResponseEntity<CalidadModel> calidadByCodigo(@PathVariable Integer idprov){
        return ResponseEntity.ok(pagoService.calidadByProveedor(idprov));
    }

    @GetMapping("/getGrasa/{idprov}")
    public ResponseEntity<Integer> getGrasa(@PathVariable Integer idprov){
        return ResponseEntity.ok(pagoService.getGrasa(idprov));
    }

    @GetMapping("/getSt/{idprov}")
    public ResponseEntity<Integer> getSt(@PathVariable Integer idprov){
        return ResponseEntity.ok(pagoService.getSt(idprov));
    }

    @GetMapping("/provs")
    public List<ProveedorModel> allprov(){
        return pagoService.getProveedores();
    }

    @GetMapping("/a/{idprov}")
    public ProveedorModel proveedorById(@PathVariable Integer idprov){
        return pagoService.proveedorById(idprov);
    }



}
