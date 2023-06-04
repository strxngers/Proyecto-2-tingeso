package com.tingeso.proveedorservice.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalidadModel {
    private Integer por_grasa;
    private Integer por_solidos;
    private Integer id_proveedor;
}
