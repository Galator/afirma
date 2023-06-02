package com.afirma.crud.controller;

import com.afirma.crud.bean.UsuarioBean;
import com.afirma.crud.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Alta de un nuevo usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 201, response = UsuarioBean.class)
    @PostMapping
    public ResponseEntity<UsuarioBean> altaUsuario (@Valid @RequestBody UsuarioBean usuarioBean) {
        HttpStatus httpStatus = HttpStatus.CREATED;

        UsuarioBean usuarioCreado = usuarioService.altaUsuario(usuarioBean);

        if (usuarioCreado == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(usuarioCreado);
    }

    @ApiOperation(value = "Consulta todos los usuarios existentes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 200, response = UsuarioBean.class, responseContainer = "List")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No hay elementos")})
    @GetMapping
    public ResponseEntity<List<UsuarioBean>> consultaUsuarios (Integer pagina, Integer tamanio) {
        HttpStatus httpStatus = HttpStatus.OK;

        List<UsuarioBean> usuarioBeanList = usuarioService.consultaUsuarios(pagina, tamanio);

        if (usuarioBeanList.isEmpty()){
            httpStatus = HttpStatus.NO_CONTENT;
            usuarioBeanList = Collections.emptyList();
        }
        return ResponseEntity.status(httpStatus).body(usuarioBeanList);
    }

    @ApiOperation(value = "Consulta de usuario por id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 200, response = UsuarioBean.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No se encontro el usuario")})
    @GetMapping(value = "/{usuarioId}")
    public ResponseEntity<UsuarioBean> getProjectById (@PathVariable(name = "usuarioId") Long usuarioId){
        HttpStatus httpStatus = HttpStatus.OK;

        UsuarioBean usuarioBean = usuarioService.consultaUsuarioId(usuarioId);

        if (usuarioBean == null){
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(httpStatus).body(usuarioBean);
    }

    @ApiOperation(value = "Actualiza un usuario por id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 200)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No se encontro el proyecto")})
    @PutMapping(value = "/{usuarioId}")
    public ResponseEntity editarUsuario (@Valid @RequestBody UsuarioBean usuarioBean,
                                         @PathVariable(name = "usuarioId") Long usuarioId) {
        HttpStatus httpStatus = HttpStatus.OK;
        Boolean isUpdated = usuarioService.editarUsuario(usuarioId, usuarioBean);

        if (!isUpdated){
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(httpStatus).build();
    }

    @ApiOperation(value = "Elimina un usuario por id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, code = 200)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No se encontro el usuario")})
    @DeleteMapping(value = "/{usuarioId}")
    public ResponseEntity projectDelete (@PathVariable(name = "usuarioId") Long usuarioId) {
        HttpStatus httpStatus = HttpStatus.OK;
        Boolean isDeleted = usuarioService.eliminarUsuario(usuarioId);

        if (!isDeleted){
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(httpStatus).build();
    }

}
