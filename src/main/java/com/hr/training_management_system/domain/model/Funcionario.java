package com.hr.training_management_system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    public int codigo;
    public String nome;
    public String cpf;
    public LocalDate nascimento;
    public String cargo;
    public LocalDate admissao;
    public Boolean status; // sugestão de melhoria: utilizar um nome que deixe mais claro: 'ativo'

    public void updateData(String nome, String cpf, LocalDate nascimento, String cargo,
                           LocalDate admissao, Boolean status){
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.cargo = cargo;
        this.admissao = admissao;
        this.status = status;
    }

}
