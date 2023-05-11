package br.com.pedro.DemoCursoAPI.api.repository;

import br.com.pedro.DemoCursoAPI.api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByIdNotAndCpf(Long id, String cpf);
}
