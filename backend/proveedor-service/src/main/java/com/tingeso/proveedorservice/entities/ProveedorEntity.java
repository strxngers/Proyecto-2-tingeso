package com.tingeso.proveedor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")
@Data
@AllArgsConstructor // Constructor con todos los argumentos
@NoArgsConstructor
public class ProveedorEntity {
    @Id
    @Column(name = "id_proveedor")
    private Integer id_proveedor;
    private String nombre;
    private String categoria;
    private String retencion;
}
