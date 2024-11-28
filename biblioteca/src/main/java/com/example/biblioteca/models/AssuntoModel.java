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
@Table(name = "assunto")
public class AssuntoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codAs; // Código do Assunto

    @Column(length = 20, nullable = false)
    @NotNull(message = "A descrição do assunto não pode ser nula")
    @Size(min = 3, max = 20, message = "A descrição do assunto deve ter entre 3 e 20 caracteres")
    private String descricao; // Descrição do Assunto

    public Long getCodAs() {
        return codAs;
    }

    public void setCodAs(Long codAs) {
        this.codAs = codAs;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}