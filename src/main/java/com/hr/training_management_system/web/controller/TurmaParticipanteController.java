package com.hr.training_management_system.web.controller;

import com.hr.training_management_system.application.dto.request.TurmaParticipanteRequestDto;
import com.hr.training_management_system.application.dto.request.TurmaRequestDto;
import com.hr.training_management_system.domain.service.interfaces.ITurmaParticipantesService;
import com.hr.training_management_system.domain.service.interfaces.ITurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turmaParticipantes")
@Tag(name = "Turma e participantes", description = "Endpoints para gerenciamento de turmas")
@RequiredArgsConstructor
public class TurmaParticipanteController {
    private final ITurmaParticipantesService _service;


    @PostMapping()
    public ResponseEntity create(@RequestBody TurmaParticipanteRequestDto novaTurmaParticipante)
    {
        var successResponse = _service.save(novaTurmaParticipante);

        return successResponse ?
                ResponseEntity.ok("Turma registrado com sucesso") :
                ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public  ResponseEntity read(){
        var turmas = _service.listAll();

        return  turmas.isEmpty() ?
                ResponseEntity.badRequest().body("Nenhuma turma com participantes encontrada") :
                ResponseEntity.ok(turmas);
    }

    @DeleteMapping("/{codigo}")
    public  ResponseEntity delete(@PathVariable int codigo){
        var successReponse  = _service.delete(codigo);

        return  successReponse ?
                ResponseEntity.ok("Registro deletado com sucesso") :
                ResponseEntity.badRequest().body("Nenhuma turma com participantes encontrada");
    }
}
