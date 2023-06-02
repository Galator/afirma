package com.afirma.crud.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "USUARIO")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = -8633738079593657570L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "CORREO_ELECTRONICO")
    private String correo;

    @Column(name = "FECHA_NACIMIENTO")
    private String nacimiento;


}
