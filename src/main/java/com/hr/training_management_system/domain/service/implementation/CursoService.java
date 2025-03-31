package com.hr.training_management_system.domain.service.implementation;

import com.hr.training_management_system.application.dto.request.CursoRequestDto;
import com.hr.training_management_system.application.dto.response.CursoResponseDTO;
import com.hr.training_management_system.domain.service.IServiceGateway;
import com.hr.training_management_system.shared.exception.ResourceNotFoundException;
import com.hr.training_management_system.domain.model.Curso;
import com.hr.training_management_system.domain.repository.interfaces.ICursoRepository;
import com.hr.training_management_system.domain.service.interfaces.ICursoService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CursoService implements ICursoService {
    private final ICursoRepository _repository;
    private final IServiceGateway _serviceGateway;

    private static final Logger logger = LogManager.getLogger(CursoService.class);

    @Override
    public boolean save(CursoRequestDto novoCurso) {
        logger.info("O serviço de criação de cursos foi chamado.");

        var curso = new Curso();
        curso.updateData(novoCurso.nome(), novoCurso.descricao(), novoCurso.duracao());

        return _repository.save(curso);
    }

    @Override
    public boolean update(int codigo, CursoRequestDto cursoAtualizado) {
        logger.info("O serviço de atualização de cursos foi chamado.");

        var curso = _repository.findByCodigo(codigo).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
        curso.updateData(cursoAtualizado.nome(), cursoAtualizado.descricao(), cursoAtualizado.duracao());

        return _repository.update(curso);
    }

    @Override
    public boolean delete(int codigo) {
        logger.info("O serviço de remoção de cursos foi chamado.");
        var curso = _repository.findByCodigo(codigo).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        var successDeleteReferences = _serviceGateway.deleteCursoAndReferences(curso.getCodigo());
        return successDeleteReferences ? _repository.delete(codigo) : false;
    }

    @Override
    public List<CursoResponseDTO> listAllCursos() {
        logger.info("O serviço de busca de cursos foi chamado.");

        return _repository.listAll().stream().map(curso -> {
                    return new CursoResponseDTO(curso.getCodigo(), curso.getNome(), curso.getDescricao(), curso.getDuracao());
                }
        ).toList();
    }
}
