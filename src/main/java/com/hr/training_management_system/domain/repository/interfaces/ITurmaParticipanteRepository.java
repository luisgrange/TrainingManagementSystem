package com.hr.training_management_system.domain.repository.interfaces;

import com.hr.training_management_system.domain.model.Turma;
import com.hr.training_management_system.domain.model.TurmaParticipante;

import java.util.Optional;

public interface ITurmaParticipanteRepository extends IGenericRepository<TurmaParticipante, Integer> {
    boolean save(TurmaParticipante participante);
    Optional<TurmaParticipante> findByTurma(int codigoTurma);
    boolean deleteByTurma(int codigoTurma);
}
