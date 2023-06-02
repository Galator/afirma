package com.afirma.crud.repository;

import com.afirma.crud.entity.UsuarioEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<UsuarioEntity, Long> {

    List<UsuarioEntity> findAll();
}
