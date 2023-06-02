package com.afirma.crud.service;

import com.afirma.crud.bean.UsuarioBean;
import com.afirma.crud.entity.UsuarioEntity;
import com.afirma.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioBean altaUsuario(UsuarioBean usuarioBean) {
        return converterEntityToBean(usuarioRepository.save(converterBeanToEntity(usuarioBean)));
    }

    @Override
    public Boolean editarUsuario(Long usuarioId, UsuarioBean usuarioBean) {
        return usuarioRepository.findById(usuarioId).map(usuarioEntity -> {
            usuarioEntity.setApellido(usuarioBean.getApellidoUsuario().isEmpty() ? usuarioEntity.getApellido() : usuarioBean.getApellidoUsuario());
            usuarioEntity.setNombre(usuarioBean.getNombreUsuario().isEmpty() ? usuarioEntity.getNombre() : usuarioBean.getNombreUsuario());
            usuarioEntity.setCorreo(usuarioBean.getCorreoElectronico().isEmpty() ? usuarioEntity.getCorreo() : usuarioBean.getCorreoElectronico());
            usuarioEntity.setNacimiento(usuarioBean.getFechaNacimiento().isEmpty() ? usuarioEntity.getNacimiento() : usuarioBean.getFechaNacimiento());

            usuarioRepository.save(usuarioEntity);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }

    @Override
    public List<UsuarioBean> consultaUsuarios(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page.intValue(), size.intValue());
        List<UsuarioBean> usuarioBeanList = new ArrayList<>();
        Page<UsuarioEntity> usuariosList = usuarioRepository.findAll(paging);
        usuariosList.getContent().stream().forEach(usuarioEntity -> usuarioBeanList.add(converterEntityToBean(usuarioEntity)));
        return usuarioBeanList;
    }

    @Override
    public UsuarioBean consultaUsuarioId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).map(usuarioEntity -> converterEntityToBean(usuarioEntity)).orElse(null);
    }

    @Override
    public Boolean eliminarUsuario(Long usuarioId) {
       return usuarioRepository.findById(usuarioId).map(usuarioEntity -> {
            usuarioRepository.deleteById(usuarioId);
            return Boolean.TRUE;
        }).orElse(Boolean.FALSE);
    }

    private UsuarioEntity converterBeanToEntity (UsuarioBean usuarioBean) {
        return UsuarioEntity.builder()
                .nombre(usuarioBean.getNombreUsuario())
                .apellido(usuarioBean.getApellidoUsuario())
                .correo(usuarioBean.getCorreoElectronico())
                .nacimiento(usuarioBean.getFechaNacimiento())
                .build();
    }

    private UsuarioBean converterEntityToBean (UsuarioEntity usuarioEntity) {
        return UsuarioBean.builder()
                .userId(usuarioEntity.getId())
                .nombreUsuario(usuarioEntity.getNombre())
                .apellidoUsuario(usuarioEntity.getApellido())
                .correoElectronico(usuarioEntity.getCorreo())
                .fechaNacimiento(usuarioEntity.getNacimiento())
                .build();
    }

}
