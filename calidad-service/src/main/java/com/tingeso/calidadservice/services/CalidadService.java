package com.tingeso.calidadservice.services;

import com.tingeso.calidadservice.entities.CalidadEntity;
import com.tingeso.calidadservice.repositories.CalidadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@Service
public class CalidadService {

    @Autowired
    CalidadRepository calidadRepository;

    public List<CalidadEntity> getCalidades(){
        List<CalidadEntity> calidades = calidadRepository.findAll();
        return calidades;
    }
    private final Logger logg = LoggerFactory.getLogger(CalidadService.class);

    public String guardar(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename != null) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                } catch (IOException e) {
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito!";
        } else {
            return "No se pudo guardar el archivo";
        }
    }

    public void leerCsv(String direccion) {
        String texto = "";
        BufferedReader bf = null;
        calidadRepository.deleteAll();
        try {
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while ((bfRead = bf.readLine()) != null) {
                if (count == 1) {
                    count = 0;
                } else {
                    guardarDataDB(Integer.parseInt(bfRead.split(";")[0]), Integer.parseInt(bfRead.split(";")[1]), Integer.parseInt(bfRead.split(";")[2]));
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
        } catch (Exception e) {
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    logg.error("ERROR", e);
                }
            }
        }
    }

    public void guardarData(CalidadEntity data) {
        calidadRepository.save(data);
    }

    public void guardarDataDB(Integer id_proveedor, Integer por_grasa, Integer por_solidos) {
        CalidadEntity newData = new CalidadEntity();
        newData.setPorGrasa(por_grasa);
        newData.setPorSolidos(por_solidos);
        newData.setId_proveedor(id_proveedor);
        guardarData(newData);
    }
    public CalidadEntity getByProveedor(Integer id_proveedor){
        List<CalidadEntity> aux = calidadRepository.findAll();
        List<CalidadEntity> calidad = new ArrayList<>();
        for(int i = 0; i < aux.size(); i++){
            if(aux.get(i).getId_proveedor().equals(id_proveedor)){
                calidad.add(aux.get(i));
            }
        }
        return calidad.get(0);
    }

    public Integer getGrasa(Integer id_proveedor){
        CalidadEntity aux = getByProveedor(id_proveedor);
        return aux.getPorGrasa();
    }

    public Integer getSt(Integer id_proveedor){
        CalidadEntity aux = getByProveedor(id_proveedor);
        return aux.getPorSolidos();
    }

}
