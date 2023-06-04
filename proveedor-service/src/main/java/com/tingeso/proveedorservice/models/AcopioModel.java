package com.tingeso.proveedor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcopioModel {
    private LocalDate fecha;
    private Integer quincena;
    private Integer kls_leche;
    private String turno;
    private Integer id_proveedor;
}
