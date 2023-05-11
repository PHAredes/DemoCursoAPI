package br.com.pedro.DemoCursoAPI.api.repository;

import br.com.pedro.DemoCursoAPI.api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    boolean existsByIdNotAndNome(Long id, String Name);
}
