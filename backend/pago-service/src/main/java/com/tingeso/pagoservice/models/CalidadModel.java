package com.tingeso.pagoservice.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalidadModel {
    private Integer por_grasa;
    private Integer por_solidos;
    private Integer id_proveedor;
}
