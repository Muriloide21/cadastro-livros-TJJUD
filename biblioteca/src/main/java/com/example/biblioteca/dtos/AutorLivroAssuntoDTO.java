/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.dtos;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import java.math.BigDecimal;

/**
 *
 * @author Murilo Schirmer
 */

@SqlResultSetMapping(
    name = "AutorLivroAssuntoDTOMapping", 
    classes = @ConstructorResult(
        targetClass = AutorLivroAssuntoDTO.class, 
        columns = {
            @ColumnResult(name = "autorNome", type = String.class),
            @ColumnResult(name = "livroTitulo", type = String.class),
            @ColumnResult(name = "livroEditora", type = String.class),
            @ColumnResult(name = "livroEdicao", type = Integer.class),
            @ColumnResult(name = "livroAnoPublicacao", type = Integer.class),
            @ColumnResult(name = "livroValor", type = BigDecimal.class),
            @ColumnResult(name = "assuntoDescricao", type = String.class)
        }
    )
)
public class AutorLivroAssuntoDTO {
    private String autorNome;
    private String livroTitulo;
    private String livroEditora;
    private Integer livroEdicao;
    private Integer livroAnoPublicacao ;
    private BigDecimal livroValor;
    private String assuntoDescricao;

    public AutorLivroAssuntoDTO(String autorNome, String livroTitulo, String livroEditora, Integer livroEdicao, Integer livroAnoPublicacao, BigDecimal livroValor, String assuntoDescricao) {
        this.autorNome = autorNome;
        this.livroTitulo = livroTitulo;
        this.livroEditora = livroEditora;
        this.livroEdicao = livroEdicao;
        this.livroAnoPublicacao = livroAnoPublicacao;
        this.livroValor = livroValor;
        this.assuntoDescricao = assuntoDescricao;
    }

    
    
    public String getAutorNome() {
        return autorNome;
    }

    public void setAutorNome(String autorNome) {
        this.autorNome = autorNome;
    }

    public String getLivroTitulo() {
        return livroTitulo;
    }

    public void setLivroTitulo(String livroTitulo) {
        this.livroTitulo = livroTitulo;
    }

    public String getLivroEditora() {
        return livroEditora;
    }

    public void setLivroEditora(String livroEditora) {
        this.livroEditora = livroEditora;
    }

    public Integer getLivroEdicao() {
        return livroEdicao;
    }

    public void setLivroEdicao(Integer livroEdicao) {
        this.livroEdicao = livroEdicao;
    }

    public Integer getLivroAnoPublicacao() {
        return livroAnoPublicacao;
    }

    public void setLivroAnoPublicacao(Integer livroAnoPublicacao) {
        this.livroAnoPublicacao = livroAnoPublicacao;
    }

    public BigDecimal getLivroValor() {
        return livroValor;
    }

    public void setLivroValor(BigDecimal livroValor) {
        this.livroValor = livroValor;
    }

    public String getAssuntoDescricao() {
        return assuntoDescricao;
    }

    public void setAssuntoDescricao(String assuntoDescricao) {
        this.assuntoDescricao = assuntoDescricao;
    }
    
    
}
