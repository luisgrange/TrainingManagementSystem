package com.hr.training_management_system.web.controller;

import com.hr.training_management_system.application.dto.request.CursoRequestDto;

import com.hr.training_management_system.domain.service.interfaces.ICursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Endpoints para gerenciamento de cursos")
@RequiredArgsConstructor
public class CursoController {
    private final ICursoService _service;

    @Operation(
            summary = "Criação de curso",
            description = "Executa a criação de um novo curso no sistema"
    )
    @PostMapping()
    public ResponseEntity create(@RequestBody CursoRequestDto novoCurso)
    {
        var successResponse = _service.save(novoCurso);
        if (!successResponse)
            return ResponseEntity.badRequest().build();

        return  ResponseEntity.ok("Curso registrado com sucesso");
    }

    @Operation(
            summary = "Busca de todos os cursos",
            description = "Executa uma busca de todos os cursos cadastrados no sistema"
    )
    @GetMapping()
    public  ResponseEntity read(){
        var cursos = _service.listAllCursos();
        if (cursos.isEmpty())
            return ResponseEntity.badRequest().body("Nenhum curso encontrado");

        return  ResponseEntity.ok(cursos);
    }

    @Operation(
            summary = "Atualização do curso",
            description = "Executa uma atualização do curso desejado"
    )
    @PutMapping("/{codigo}")
    public  ResponseEntity update(
            @PathVariable int codigo,
            @RequestBody CursoRequestDto cursoAtualizado
    ){
        var successResponse = _service.update(codigo, cursoAtualizado);

        if (!successResponse)
            return ResponseEntity.badRequest().build();

        return  ResponseEntity.ok("Curso atualizado com sucesso");
    }

    @Operation(
            summary = "Remoção do curso",
            description = "Executa a remoção do curso desejado"
    )
    @DeleteMapping("/{codigo}")
    public  ResponseEntity delete(@PathVariable int codigo){
        var successResponse = _service.delete(codigo);
        if (!successResponse)
            return ResponseEntity.badRequest().build();

        return  ResponseEntity.ok("Curso registrado com sucesso");
    }
}
