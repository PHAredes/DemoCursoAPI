package br.com.pedro.DemoCursoAPI.api.services;

import java.util.List;
import java.util.Optional;


import br.com.pedro.DemoCursoAPI.api.model.Aluno;
import org.springframework.data.domain.Page;



public interface AlunoService {


    Aluno insert(Aluno aluno);


    Aluno update(Aluno aluno, Long id);


    Optional<Aluno> findById(Long id);


    void delete(Long id);


    Page<Aluno> paginar(Integer page, Integer size);


    List<Aluno> findAll();
}
