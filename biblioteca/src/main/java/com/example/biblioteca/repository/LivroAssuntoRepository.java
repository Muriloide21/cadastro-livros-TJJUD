/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.biblioteca.repository;

import com.example.biblioteca.models.LivroAssuntoId;
import com.example.biblioteca.models.LivroAssuntoModel;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Murilo Schirmer
 */
public interface LivroAssuntoRepository extends CrudRepository<LivroAssuntoModel, LivroAssuntoId>{
    List<LivroAssuntoModel> findByLivroCodL(Long livroCodL);

    List<LivroAssuntoModel> findByAssuntoCodAs(Long assuntoCodAs);
    
    @Query("SELECT las.assunto.codAs FROM LivroAssuntoModel las WHERE las.livro.codL = :livroId")
    List<Long> findAssuntosByLivroId(@Param("livroId") Long livroId);

    @Transactional
    @Modifying
    @Query("DELETE FROM LivroAssuntoModel las WHERE las.livro.codL = :livroId")
    void deleteByLivroId(@Param("livroId") Long livroId);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM LivroAssuntoModel las WHERE las.assunto.codAs = :assuntoId")
    void deleteByAssuntoId(@Param("assuntoId") Long assuntoId);
}
