package com.tingeso.calidadservice.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "calidad")
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
public class CalidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer idCalidad;
    private Integer porGrasa;
    private Integer porSolidos;
    private Integer id_proveedor;
}
