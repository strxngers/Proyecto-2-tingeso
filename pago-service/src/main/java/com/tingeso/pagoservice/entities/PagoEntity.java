package com.tingeso.pagoservice.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pago")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PagoEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pago;
    private Integer id_proveedor;
    private String nombreproveedor;
    private Integer totalklsleche;
    private Integer nrodiasdeenvio;
    private double promdiarioklsleche;
    private double variacionLeche;
    private Integer porgrasa;
    private double vargrasa;
    private Integer solidostotales;
    private double varst;
    private double pagoxleche;
    private double pagoxgrasa;
    private double pagoxst;
    private double bonofrecuencia;
    private double dctovarleche;
    private double dctovargrasa;
    private double dctovarst;
    private double pagototal;
    private double retencion;
    private double montofinal;
    private String fecha;
}
