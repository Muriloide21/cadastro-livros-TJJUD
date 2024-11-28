/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.biblioteca.repository;

import com.example.biblioteca.dtos.AutorLivroAssuntoDTO;
import com.example.biblioteca.models.AutorLivroAssuntoModel;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.tree.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Murilo Schirmer
 */
@Repository
public class AutorLivroAssuntoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AutorLivroAssuntoModel> findLivroAutorAssunto() {
        String sql = "SELECT autorNome, livroTitulo, livroEditora, livroEdicao, livroAnoPublicacao, livroValor, assuntoDescricao FROM vw_autor_livro_assunto";

        // Usando lambda para mapear os resultados para a Model
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            AutorLivroAssuntoModel model = new AutorLivroAssuntoModel();
            model.setAutorNome(rs.getString("autorNome"));
            model.setLivroTitulo(rs.getString("livroTitulo"));
            model.setLivroEditora(rs.getString("livroEditora"));
            model.setLivroEdicao(rs.getInt("livroEdicao"));
            model.setLivroAnoPublicacao(rs.getInt("livroAnoPublicacao"));
            model.setLivroValor(rs.getBigDecimal("livroValor"));
            model.setAssuntoDescricao(rs.getString("assuntoDescricao"));
            return model;
        });
    }
}
