package com.example.biblioteca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
@Table(name = "vw_autor_livro_assunto")  // A view do banco de dados
public class AutorLivroAssuntoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "autorNome")
    private String autorNome;

    @Column(name = "livroTitulo")
    private String livroTitulo;

    @Column(name = "livroEditora")
    private String livroEditora;

    @Column(name = "livroEdicao")
    private Integer livroEdicao;

    @Column(name = "livroAnoPublicacao")
    private Integer livroAnoPublicacao;

    @Column(name = "livroValor")
    private BigDecimal livroValor;

    @Column(name = "assuntoDescricao")
    private String assuntoDescricao;

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

