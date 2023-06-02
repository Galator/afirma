package com.afirma.crud.service;

import com.afirma.crud.bean.UsuarioBean;

import java.util.List;

public interface UsuarioService {

    UsuarioBean altaUsuario (UsuarioBean usuarioBean);

    Boolean editarUsuario (Long usuarioId, UsuarioBean usuarioBean);

    List<UsuarioBean> consultaUsuarios (Integer page, Integer size);

    UsuarioBean consultaUsuarioId (Long usuarioId);

    Boolean eliminarUsuario (Long usuarioId);
}
