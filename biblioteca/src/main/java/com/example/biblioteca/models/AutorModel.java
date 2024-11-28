/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Murilo Schirmer
 */
@Entity
@Table(name = "autor")
public class AutorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codAu;

    @Column(length = 40, nullable = false)
    @NotNull(message = "O nome do autor não pode ser nulo")
    @Size(min = 2, max = 40, message = "O nome do autor deve ter entre 2 e 40 caracteres")
    private String nome;

    public Long getCodAu() {
        return codAu;
    }

    public void setCodAu(Long codAu) {
        this.codAu = codAu;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}