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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 *
 * @author Murilo Schirmer
 */

@Entity
@Table(name = "livro")
public class LivroModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codL;

    
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres.")
    @Column(length = 100, nullable = false)
    @NotNull(message = "O título não pode ser nulo.")
    private String titulo;

    @Size(min=2, max = 40, message = "A editora pode ter no máximo 40 caracteres.")
    @Column(length = 40)
    @NotNull(message = "A editora não pode ser nulo.")
    private String editora;

    @Min(value = 1, message = "A edição deve ser um número positivo.")
    @NotNull(message = "A edição não pode ser nulo.")
    private Integer edicao;

    @Min(value = 1500, message = "O ano de publicação deve ser posterior a 1500.")
    @NotNull(message = "O ano de publicação não pode ser nulo.")
    private Integer anoPublicacao;

    
    @Min(value = 0, message = "O valor não pode ser negativo.")
    @Column(precision = 10, scale = 2, nullable = false)
    @NotNull(message = "O valor não pode ser nulo.")
    private BigDecimal valor;

    public Long getCodL() {
        return codL;
    }

    public void setCodL(Long codL) {
        this.codL = codL;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    
    
}
