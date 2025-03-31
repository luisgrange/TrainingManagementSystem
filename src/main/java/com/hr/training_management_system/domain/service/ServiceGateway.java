package com.hr.training_management_system.domain.service;

import com.hr.training_management_system.domain.model.Funcionario;
import com.hr.training_management_system.domain.model.Turma;
import com.hr.training_management_system.domain.repository.interfaces.IFuncionarioRepository;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaParticipanteRepository;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceGateway implements IServiceGateway {
    private final ITurmaRepository _turmaRepository;
    private final ITurmaParticipanteRepository _turmaParticipanteRepository;
    private final IFuncionarioRepository _funcionarioRepository;

    @Override
    public boolean deleteCursoAndReferences(int codigoCurso) {
        var success = true;

        var turma = _turmaRepository.findByCurso(codigoCurso).orElse(null);
        var turmaParticipante = turma != null ?
                _turmaParticipanteRepository.findByTurma(turma.getCodigo()) :
                null;

        if(turmaParticipante != null)
            success = _turmaParticipanteRepository.deleteByTurma(turma.getCodigo());

        if(turma != null)
            success = _turmaRepository.deleteByCurso(codigoCurso);

        return success;
    }

    public Optional<Turma> findTurmaToTurmaParticipantes(int codigoTurma) {
        return _turmaRepository.findByCodigo(codigoTurma);
    }

    public Optional<Funcionario> findFuncionarioToTurmaParticipantes(int codigoFuncionario) {
        return _funcionarioRepository.findByCodigo(codigoFuncionario);
    }

    public boolean deleteTurmaAndReferences(int codigoTurma) {
        var success = true;
        var turmaParticipante = _turmaParticipanteRepository.findByTurma(codigoTurma);

        if(turmaParticipante != null)
            success = _turmaParticipanteRepository.deleteByTurma(codigoTurma);

        return success;
    }
}
