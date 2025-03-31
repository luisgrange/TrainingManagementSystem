package com.hr.training_management_system.domain.repository.interfaces;

import com.hr.training_management_system.domain.model.Curso;
import com.hr.training_management_system.domain.model.Turma;

import java.util.List;
import java.util.Optional;

public interface ICursoRepository extends IGenericRepository<Curso, Integer> {
    boolean save(Curso curso);
    boolean update(Curso curso);
}
