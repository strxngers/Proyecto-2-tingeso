package com.tingeso.pagoservice.models;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AcopioModel {
    private Integer quincena;
    private Integer kls_leche;
    private LocalDate fecha;
    private String turno;
    private Integer id_proveedor;
}
