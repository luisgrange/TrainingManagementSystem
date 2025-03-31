package com.hr.training_management_system.domain.service;

import com.hr.training_management_system.domain.model.Funcionario;
import com.hr.training_management_system.domain.model.Turma;

import java.util.Optional;

public interface IServiceGateway {
    boolean deleteCursoAndReferences(int codigoCurso);
    Optional<Turma> findTurmaToTurmaParticipantes(int codigoTurma);
    Optional<Funcionario> findFuncionarioToTurmaParticipantes(int codigoFuncionario);
    boolean deleteTurmaAndReferences(int codigoTurma);
}
