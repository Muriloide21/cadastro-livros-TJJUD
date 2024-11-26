/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.biblioteca.models.LivroModel;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Murilo Schirmer
 */
@Repository
public interface LivroRepository extends CrudRepository<LivroModel,Long> {
    LivroModel findByCodL(Long codL);
}
