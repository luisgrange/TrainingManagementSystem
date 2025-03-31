package com.hr.training_management_system.domain.service.implementation;

import com.hr.training_management_system.application.dto.request.TurmaParticipanteRequestDto;
import com.hr.training_management_system.application.dto.response.TurmaParticipanteResponseDto;
import com.hr.training_management_system.domain.model.TurmaParticipante;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaParticipanteRepository;
import com.hr.training_management_system.domain.repository.interfaces.ITurmaRepository;
import com.hr.training_management_system.domain.service.IServiceGateway;
import com.hr.training_management_system.domain.service.interfaces.ITurmaParticipantesService;
import com.hr.training_management_system.shared.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TurmaParticipantesService implements ITurmaParticipantesService {
    private final ITurmaParticipanteRepository _repository;
    private final IServiceGateway _serviceGateway;
    private static final Logger logger = LogManager.getLogger(TurmaParticipantesService.class);


    @Override
    public List<TurmaParticipanteResponseDto> listAll() {
        logger.info("O serviço de busca de participantes em turmas foi chamado.");

        return _repository.listAll().stream().map(turmaParticipante -> {
            var turma = _serviceGateway.findTurmaToTurmaParticipantes(turmaParticipante.getTurma())
                    .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrado"));

            var funcionario = _serviceGateway.findFuncionarioToTurmaParticipantes(turmaParticipante.getFuncionario())
                    .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));

            return new TurmaParticipanteResponseDto(
                    turmaParticipante.getCodigo(),
                    turmaParticipante.getTurma(),
                    turmaParticipante.getFuncionario(),
                    funcionario.getNome(),
                    turma.turmaAtiva()
            );
        }).toList();
    }

    @Override
    public boolean save(TurmaParticipanteRequestDto novaTurmaParticipante) {
        logger.info("O serviço de inclusão de participantes em turmas foi chamado.");

        TurmaParticipante turmaParticipante = new TurmaParticipante();
        turmaParticipante.setTurma(novaTurmaParticipante.turma());
        turmaParticipante.setFuncionario(novaTurmaParticipante.funcionario());

        return _repository.save(turmaParticipante);
    }

    @Override
    public boolean delete(int codigo) {
        logger.info("O serviço de remoção de participantes em turmas foi chamado.");
         _repository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada"));

        return _repository.delete(codigo);
    }
}
