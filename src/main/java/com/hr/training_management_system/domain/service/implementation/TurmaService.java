package com.hr.training_management_system.domain.service.implementation;

import com.hr.training_management_system.application.dto.request.TurmaRequestDto;
import com.hr.training_management_system.application.dto.request.TurmaUpdateRequestDto;
import com.hr.training_management_system.application.dto.response.TurmaResponseDto;
import com.hr.training_management_system.domain.model.Turma;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaRepository;
import com.hr.training_management_system.domain.service.IServiceGateway;
import com.hr.training_management_system.domain.service.interfaces.ITurmaService;
import com.hr.training_management_system.shared.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TurmaService implements ITurmaService {
    private final ITurmaRepository _repository;
    private final IServiceGateway _serviceGateway;
    private static final Logger logger = LogManager.getLogger(TurmaService.class);


    @Override
    public List<TurmaResponseDto> listAll() {
        logger.info("O serviço de busca de participantes foi chamado.");

        return _repository.listAll().stream().map(turma -> {
            return new TurmaResponseDto(
                    turma.getCodigo(),
                    turma.getInicio(),
                    turma.getFim(),
                    turma.getLocal(),
                    turma.getCurso()
            );
        }).toList();

    }

    @Override
    public boolean save(TurmaRequestDto novaTurma) {
        logger.info("O serviço de criação de turmas foi chamado.");

        Turma turma = new Turma();
        turma.setInicio(novaTurma.inicio());
        turma.setFim(novaTurma.fim());
        turma.setLocal(novaTurma.local());
        turma.setCurso(novaTurma.curso());

        return _repository.save(turma);
    }

    @Override
    public boolean delete(int codigo) {
        logger.info("O serviço de remoção de turmas foi chamado.");
        var turma = _repository.findByCodigo(codigo).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        var success = _serviceGateway.deleteTurmaAndReferences(turma.getCodigo());

        return success && _repository.delete(codigo);
    }

    @Override
    public boolean update(int codigo, TurmaUpdateRequestDto turmaAtualizada) {
        logger.info("O serviço de atualização de turmas foi chamado.");
        Turma turma = _repository.findByCodigo(codigo).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
        turma.updateData(turmaAtualizada.inicio(), turmaAtualizada.fim(), turmaAtualizada.local());

        return _repository.update(turma);
    }
}
