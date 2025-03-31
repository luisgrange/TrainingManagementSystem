package com.hr.training_management_system.web.controller;

import com.hr.training_management_system.application.dto.request.TurmaRequestDto;
import com.hr.training_management_system.application.dto.request.TurmaUpdateRequestDto;
import com.hr.training_management_system.domain.service.interfaces.ITurmaService;
import com.hr.training_management_system.domain.service.interfaces.ITurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/turmas")
@Tag(name = "Turmas", description = "Endpoints para gerenciamento de turmas")
@RequiredArgsConstructor
public class TurmaController {
    private final ITurmaService _service;

    @Operation(
            summary = "Criação de Turma",
            description = "Executa a criação de um novo Turma no sistema"
    )
    @PostMapping()
    public ResponseEntity create(@RequestBody TurmaRequestDto novaTurma)
    {
        var successResponse = _service.save(novaTurma);
        if (!successResponse)
            return ResponseEntity.badRequest().build();

        return  ResponseEntity.ok("Turma registrado com sucesso");
    }

    @Operation(
            summary = "Busca de todos os Turmas",
            description = "Executa uma busca de todos os Turmas cadastrados no sistema"
    )
    @GetMapping()
    public  ResponseEntity read(){
        var turmas = _service.listAll();

        if (turmas.isEmpty())
            return ResponseEntity.badRequest().body("Nenhum Turma encontrado");

        return  ResponseEntity.ok(turmas);
    }

    @Operation(
            summary = "Atualização do Turma",
            description = "Executa uma atualização do Turma desejado"
    )
    @PutMapping("/{codigo}")
    public  ResponseEntity update(
            @PathVariable int codigo,
            @RequestBody TurmaUpdateRequestDto turmaAtualizada
    ){
        var successResponse = _service.update(codigo, turmaAtualizada);

        return successResponse ?
                ResponseEntity.ok("Turma atualizado com sucesso") :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{codigo}")
    public  ResponseEntity delete(
            @PathVariable int codigo){
        var successResponse = _service.delete(codigo);

        return successResponse ?
                ResponseEntity.ok("Turma deletada com sucesso") :
                ResponseEntity.badRequest().build();
    }
}
