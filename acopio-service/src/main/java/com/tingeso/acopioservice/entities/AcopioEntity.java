package com.tingeso.acopioservice.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "acopio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcopioEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_acopio;
    private Integer quincena;
    private Integer kls_leche;
    private LocalDate fecha;
    private String turno;
    private Integer id_proveedor;
}
