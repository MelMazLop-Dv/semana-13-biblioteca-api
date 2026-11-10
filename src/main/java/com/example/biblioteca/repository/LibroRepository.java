
package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {


    List<Libro> findByCategoriaIgnoreCase(String categoria);


    List<Libro> findByDisponibleTrue();


    Optional<Libro> findByCodigo(String codigo);


    boolean existsByCodigo(String codigo);


    boolean existsByCodigoAndIdNot(String codigo, Long id);
}
