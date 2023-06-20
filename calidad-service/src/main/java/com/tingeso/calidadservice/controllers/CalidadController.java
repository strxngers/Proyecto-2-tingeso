package com.tingeso.calidadservice.controllers;

import com.tingeso.calidadservice.entities.CalidadEntity;
import com.tingeso.calidadservice.services.CalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/calidad")
public class CalidadController {

    @Autowired
    CalidadService calidadService;

    @GetMapping
    public ResponseEntity<List<CalidadEntity>> getCalidades(){
        List<CalidadEntity> acopios = calidadService.getCalidades();
        if(acopios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(acopios);
    }

    @PostMapping("/uploadcalidadleche")
    public String uploadKls(@RequestParam("file") MultipartFile file) {
        calidadService.guardar(file);
        calidadService.leerCsv("Calidad.csv");
        return "Se leyó correctamente";
    }
}
