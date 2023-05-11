package br.com.pedro.DemoCursoAPI.api.services.impl;

import br.com.pedro.DemoCursoAPI.api.exception.IdInvalidoException;
import br.com.pedro.DemoCursoAPI.api.exception.RecursoNaoEncontradoException;
import br.com.pedro.DemoCursoAPI.api.exception.ValidacaoException;
import br.com.pedro.DemoCursoAPI.api.model.Curso;
import br.com.pedro.DemoCursoAPI.api.repository.CursoRepository;
import br.com.pedro.DemoCursoAPI.api.services.CursoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository repository;


    private void validId(Long id) {
        if (id == null || id <= 0) {
            throw new IdInvalidoException();
        } else if (!existsById(id)) {
            throw new RecursoNaoEncontradoException();
        }
    }


    private void validar(Curso curso) {
        List<String> erros = new LinkedList<>();
        if (StringUtils.isEmpty(curso.getNome())) {
            erros.add("Nome deve ser informado.");
        } else if (curso.getNome().length() > 80) {
            erros.add("Nome deve ter no máximo 80 caracteres.");
        } else {
            final Long id = curso.getId() == null ? 0l : curso.getId();
            if (repository.existsByIdNotAndNome(id, curso.getNome())) {
                erros.add("Este curso já foi cadastrado.");
            }
        }


        if (curso.getValor() == null) {
            erros.add("Valor deve ser informado.");
        } else if (curso.getValor() <= 0) {
            erros.add("Valor deve ser maior que zero.");
        }


        if (StringUtils.isNotEmpty(curso.getDescricao()) && curso.getDescricao().length() > 300) {
            erros.add("Descriçaõ deve ter no máximo 300 caracteres.");
        }


        if (erros.size() > 0) {
            throw new ValidacaoException(erros);
        }
    }


    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }


    @Override
    public Curso insert(Curso curso) {
        validar(curso);
        return repository.save(curso);
    }


    @Override
    public Curso update(Curso curso, Long id) {
        validId(id);
        curso.setId(id);
        validar(curso);
        return repository.save(curso);
    }


    @Override
    public Optional<Curso> findById(Long id) {
        validId(id);
        return repository.findById(id);
    }


    @Override
    public void delete(Long id) {
        validId(id);
        repository.deleteById(id);
    }


    @Override
    public Page<Curso> paginar(Integer page, Integer size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "nome")));
    }

    @Override
    public List<Curso> findAll(){
        return repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }
}