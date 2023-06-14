package com.tingeso.pagoservice.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pago;
    private Integer id_proveedor;
    private String nombreProveedor;
    private Integer totalKlsLeche;
    private Integer nroDiasDeEnvio;
    private double promDiarioKlsLeche;
    private double variacionLeche;
    private Integer porGrasa;
    private double varGrasa;
    private Integer solidosTotales;
    private double varST;
    private  double pagoXLeche;
    private  double pagoXGrasa;
    private  double pagoXST;
    private double bonoFrecuencia;
    private double dctoVarLeche;
    private double dctoVarGrasa;
    private double dctoVarST;
    private double pagoTotal;
    private double retencion;
    private double montoFinal;  // Monto final
    private String fecha; // AAAA/MM/QUINCENA
}
