package com.tingeso.acopioservice.controllers;

import com.tingeso.acopioservice.entities.AcopioEntity;
import com.tingeso.acopioservice.services.AcopioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/acopio")
public class AcopioController {

    @Autowired
    AcopioService acopioService;

    @GetMapping
    public ResponseEntity<List<AcopioEntity>> getAcopios(){
        List<AcopioEntity> acopios = acopioService.getAcopios();
        if(acopios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(acopios);
    }

    @GetMapping("/{idprov}")
    public ResponseEntity<List<AcopioEntity>> getByCodigo(@PathVariable Integer idprov){
        List<AcopioEntity> acopio = acopioService.getByProveedor(idprov);
        if(acopio == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(acopio);
    }

    @PostMapping("/uploadklsleche")
    public String uploadKlsleche(@RequestParam("file") MultipartFile file) {
        acopioService.guardar(file);
        acopioService.leerCsv("Acopio.csv");
        return "Se leyó correctamente";
    }
}
