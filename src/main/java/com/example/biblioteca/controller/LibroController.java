
package com.example.biblioteca.controller;

import com.example.biblioteca.entity.Libro;
import com.example.biblioteca.repository.LibroRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }


    @GetMapping
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }


    @GetMapping("/{id}")
    public Libro buscarPorId(@PathVariable Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Libro no encontrado"
                ));
    }


    @GetMapping("/categoria/{categoria}")
    public List<Libro> buscarPorCategoria(
            @PathVariable String categoria) {

        return libroRepository
                .findByCategoriaIgnoreCase(categoria);
    }


    @GetMapping("/disponibles")
    public List<Libro> librosDisponibles() {
        return libroRepository.findByDisponibleTrue();
    }


    @GetMapping("/codigo/{codigo}")
    public Libro buscarPorCodigo(
            @PathVariable String codigo) {

        return libroRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No existe un libro con ese codigo"
                ));
    }


    @PostMapping
    public ResponseEntity<Libro> guardarLibro(
            @RequestBody Libro libro) {

        validarCodigo(libro.getCodigo());

        if (libroRepository.existsByCodigo(
                libro.getCodigo())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un libro con ese codigo"
            );
        }

        try {
            Libro nuevo = libroRepository.saveAndFlush(libro);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevo);

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "No se pudo registrar el libro. " +
                            "Verifique que el codigo sea unico",
                    e
            );
        }
    }


    @PutMapping("/{id}")
    public Libro actualizarLibro(
            @PathVariable Long id,
            @RequestBody Libro datos) {

        Libro libro = buscarPorId(id);

        validarCodigo(datos.getCodigo());

        if (libroRepository.existsByCodigoAndIdNot(
                datos.getCodigo(), id)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El codigo ya pertenece a otro libro"
            );
        }

        libro.setCodigo(datos.getCodigo());
        libro.setTitulo(datos.getTitulo());
        libro.setAutor(datos.getAutor());
        libro.setCategoria(datos.getCategoria());
        libro.setAnioPublicacion(
                datos.getAnioPublicacion());
        libro.setPrecio(datos.getPrecio());
        libro.setDisponible(datos.getDisponible());

        try {
            return libroRepository.saveAndFlush(libro);

        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "No se pudo actualizar el libro. " +
                            "Verifique que el codigo sea unico",
                    e
            );
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(
            @PathVariable Long id) {

        Libro libro = buscarPorId(id);

        libroRepository.delete(libro);

        return ResponseEntity.noContent().build();
    }


    private void validarCodigo(String codigo) {

        if (codigo == null || codigo.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El codigo del libro es obligatorio"
            );
        }
    }
}
