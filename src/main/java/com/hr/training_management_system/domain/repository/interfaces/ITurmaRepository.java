package com.hr.training_management_system.domain.repository.interfaces;

import com.hr.training_management_system.domain.model.Turma;

import java.util.Optional;

public interface ITurmaRepository extends IGenericRepository<Turma, Integer> {
    boolean save(Turma turma);
    boolean update(Turma turma);
    Optional<Turma> findByCurso(int codigoCurso);
    boolean deleteByCurso(int codigoCurso);
}
