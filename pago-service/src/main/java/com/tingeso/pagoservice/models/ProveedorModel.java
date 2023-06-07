package com.tingeso.pagoservice.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProveedorModel {
    private Integer id_proveedor;
    private String nombre;
    private String categoria;
    private String retencion;
}
