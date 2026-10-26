package com.example.biblioteca.controller;

import com.example.biblioteca.entity.Libro;
import com.example.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    // Endpoint para obtener todos los libros (GET)
    @GetMapping
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // Endpoint para guardar un nuevo libro (POST)
    @PostMapping
    public Libro guardarLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }
}