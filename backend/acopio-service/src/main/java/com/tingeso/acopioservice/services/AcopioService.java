package com.tingeso.acopioservice.services;

import com.tingeso.acopioservice.entities.AcopioEntity;
import com.tingeso.acopioservice.repositories.AcopioRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AcopioService {
    @Autowired
    AcopioRepository acopioRepository;

    Integer mitadMes = 15;
    private final Logger logg = LoggerFactory.getLogger(AcopioService.class);

    public List<AcopioEntity> getAcopios(){
        return acopioRepository.findAll();
    }
    @Generated
    public String guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                }
                catch (IOException e){
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito!";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }

    @Generated
    public void leerCsv(String direccion){
        String texto = "";
        BufferedReader bf = null;
        acopioRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if (count == 1){
                    count = 0;
                }
                else{
                    guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], Integer.parseInt(bfRead.split(";")[2]), Integer.parseInt(bfRead.split(";")[3]));
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
        }catch(Exception e){
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
    }

    @Generated
    public void guardarData(AcopioEntity data){
        acopioRepository.save(data);
    }

    @Generated
    public void guardarDataDB(String fecha, String turno, Integer id_proveedor, Integer kls_leche) {
        AcopioEntity newData = new AcopioEntity();
        newData.setFecha(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        newData.setQuincena(quincena(fecha));
        newData.setTurno(turno);
        newData.setKls_leche(kls_leche);
        newData.setId_proveedor(id_proveedor);
        guardarData(newData);
    }

    public Integer quincena(String fecha){
        Integer quincena = 0;
        if(Integer.parseInt(fecha.substring(8)) <= mitadMes){
            quincena = 1;
        }else if(Integer.parseInt(fecha.substring(8)) >= mitadMes){
            quincena = 2;
        }
        return quincena;
    }
}
