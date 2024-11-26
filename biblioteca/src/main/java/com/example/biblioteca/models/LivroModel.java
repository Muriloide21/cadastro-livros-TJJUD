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
    
    @Column(length = 40)
    private String titulo;
    
    @Column(length = 40)
    private String editora;
    
    private Integer edicao;
    
    @Column(length = 4)
    private Integer anoPublicacao;
    
    @Column(precision = 10, scale = 2)
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
