package br.com.pedro.DemoCursoAPI.api.services;

import br.com.pedro.DemoCursoAPI.api.model.Curso;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CursoService {


    Boolean existsById(Long id);


    Curso insert(Curso curso);


    Curso update(Curso curso, Long id);


    Optional<Curso> findById(Long id);


    void delete(Long id);


    Page<Curso> paginar(Integer page, Integer size);


    List<Curso> findAll();
}