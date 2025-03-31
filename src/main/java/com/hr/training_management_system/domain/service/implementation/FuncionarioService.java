package com.hr.training_management_system.domain.service.implementation;

import com.hr.training_management_system.application.dto.request.FuncionarioRequestDto;
import com.hr.training_management_system.application.dto.response.FuncionarioResponseDto;
import com.hr.training_management_system.shared.exception.ResourceNotFoundException;
import com.hr.training_management_system.domain.model.Funcionario;
import com.hr.training_management_system.domain.repository.interfaces.IFuncionarioRepository;
import com.hr.training_management_system.domain.service.interfaces.IFuncionarioService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FuncionarioService implements IFuncionarioService {
    @Autowired
    private final IFuncionarioRepository _repository;
    private static final Logger logger = LogManager.getLogger(FuncionarioService.class);

    @Override
    public List<FuncionarioResponseDto> listAll(Optional<Boolean> status) {
        logger.info("O serviço de busca de funcionários foi chamado.");
        return status.map(this::findByStatus).orElseGet(this::findAll);
    }

    private List<FuncionarioResponseDto> findAll() {
        return _repository.listAll().stream()
                .map(funcionario -> {
                    return new FuncionarioResponseDto(
                            funcionario.getCodigo(),
                            funcionario.getNome(),
                            funcionario.getCpf(),
                            funcionario.getNascimento(),
                            funcionario.getCargo(),
                            funcionario.getAdmissao(),
                            funcionario.getStatus());
                })
                .toList();
    }

    private List<FuncionarioResponseDto> findByStatus(boolean status) {
        return _repository.findByStatus(status).stream()
                .map(funcionario -> {
                    return new FuncionarioResponseDto(
                            funcionario.getCodigo(),
                            funcionario.getNome(),
                            funcionario.getCpf(),
                            funcionario.getNascimento(),
                            funcionario.getCargo(),
                            funcionario.getAdmissao(),
                            funcionario.getStatus());
                })
                .toList();
    }

    @Override
    public boolean save(FuncionarioRequestDto novoFuncionario) {
        logger.info("O serviço de criação de funcionários foi chamado.");

        var funcionario = new Funcionario();

        funcionario.updateData(novoFuncionario.nome(), novoFuncionario.cpf(), novoFuncionario.nascimento(),
                novoFuncionario.cargo(), novoFuncionario.admissao(), novoFuncionario.status());

        try{
            return _repository.save(funcionario);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int codigo) {
        _repository.findByCodigo(codigo).orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado"));
        logger.info("O serviço de remoção de funcionários foi chamado.");

        return _repository.delete(codigo);
    }

    @Override
    public boolean update(int codigo, FuncionarioRequestDto funcionarioAtualizado) {
        logger.info("O serviço de atualização de funcionários foi chamado.");

        var funcionario = _repository.findByCodigo(codigo).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
        funcionario.updateData(funcionarioAtualizado.nome(), funcionarioAtualizado.cpf(), funcionarioAtualizado.nascimento(),
                funcionarioAtualizado.cargo(), funcionarioAtualizado.admissao(), funcionarioAtualizado.status());

        return _repository.update(funcionario);
    }
}
