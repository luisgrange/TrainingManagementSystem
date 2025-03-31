package com.hr.training_management_system.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    public int codigo;
    public String nome;
    public String descricao;
    public int duracao;

    public void updateData(String nome, String descricao, int duracao){
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
    }
}
