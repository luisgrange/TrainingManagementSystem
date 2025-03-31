package com.hr.training_management_system.web.controller;

import com.hr.training_management_system.application.dto.request.FuncionarioRequestDto;
import com.hr.training_management_system.domain.service.interfaces.IFuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
@Tag(name = "Funcionarios", description = "Endpoints para gerenciamento de funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    private final IFuncionarioService _service;

    @Operation(
            summary = "Criação de funcionario",
            description = "Executa a criação de um novo funcionario no sistema"
    )
    @PostMapping()
    public ResponseEntity create(@RequestBody FuncionarioRequestDto novoFuncionario)
    {
        var successResponse = _service.save(novoFuncionario);
        if (!successResponse)
            return ResponseEntity.badRequest().build();

        return  ResponseEntity.ok("funcionario registrado com sucesso");
    }

    @Operation(
            summary = "Busca de todos os funcionarios",
            description = "Executa uma busca de todos os funcionarios cadastrados no sistema"
    )
    @GetMapping(value = {"", "/{status}"})
    public  ResponseEntity read(@PathVariable(required = false) String status){
        var convertedStatus = status == null ? null : Boolean.parseBoolean(status);
        var funcionarios = _service.listAll(Optional.ofNullable(convertedStatus));

        if (funcionarios.isEmpty())
            return ResponseEntity.badRequest().body("Nenhum funcionario encontrado");

        return  ResponseEntity.ok(funcionarios);
    }

    @Operation(
            summary = "Atualização do funcionario",
            description = "Executa uma atualização do funcionario desejado"
    )
    @PutMapping("/{codigo}")
    public  ResponseEntity update(
            @PathVariable int codigo,
            @RequestBody FuncionarioRequestDto funcionarioAtualizado
    ){
        var successResponse = _service.update(codigo, funcionarioAtualizado);

        if (!successResponse)
            return ResponseEntity.badRequest().build();

        return  ResponseEntity.ok("Funcionario atualizado com sucesso");
    }
}
