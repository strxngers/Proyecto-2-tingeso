package com.tingeso.proveedorservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorEntity {
    @Id
    private Integer id_proveedor;
    private String nombre;
    private String categoria;
    private String retencion;
}

