package com.tingeso.proveedorservice.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ProveedorEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_proveedor;
    private String nombre;
    private String categoria;
    private String retencion;
}

