package com.afirma.crud.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = -5542744863044626898L;

    private Long userId;

    @NotNull
    private String nombreUsuario;

    @NotNull
    private String apellidoUsuario;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}")
    private String correoElectronico;

    @NotNull
    private String fechaNacimiento;
}
