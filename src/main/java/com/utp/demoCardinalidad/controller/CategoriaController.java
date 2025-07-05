/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.demoCardinalidad.controller;

import com.utp.demoCardinalidad.model.Categoria;
import com.utp.demoCardinalidad.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jtorr
 */
@Tag(name = "Categoria", description = "API para la gestión de categorías de productos")
@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

//    @GetMapping
//    public List<Categoria> listarCategoria() {
//        //categoriaService.save(categoria);
//        List<Categoria> categorias = categoriaService.findAll();
//        return categorias;
//    }
//    
//    @PutMapping
//    public void modificarCategoria(@RequestBody Categoria categoria) {
//        categoriaService.save(categoria);
//    }
//    
//    @GetMapping("/{id}")
//    public Categoria obtenerCategoria(@PathVariable Integer id) {
//        return categoriaService.findById(id);
//    }
//    
//    @DeleteMapping("/{id}")
//    public void eliminarCategoria(@PathVariable Integer id){
//        categoriaService.deleteById(id);
//    }
    // Obtener todas las categorías
    @Operation(summary = "Obtener todas las categorías", description = "Retorna una lista de todas las categorías disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de categorías encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)))
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Obtener una categoría por ID
    @Operation(summary = "Obtener una categoría por ID", description = "Retorna una categoría según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría encontrada",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(
            @Parameter(description = "ID de la categoría a buscar") @PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria != null) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear una nueva categoría
    @Operation(summary = "Crear una nueva categoría", description = "Crea una nueva categoría con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)))
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(
            @Parameter(description = "Datos de la categoría a crear") @RequestBody Categoria categoria) {
        Categoria newCategoria = categoriaService.save(categoria);
        return new ResponseEntity<>(newCategoria, HttpStatus.CREATED);
    }

    // Actualizar una categoría existente
    @Operation(summary = "Actualizar una categoría existente", description = "Actualiza una categoría existente según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class))),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(
            @Parameter(description = "ID de la categoría a actualizar") @PathVariable Integer id,
            @Parameter(description = "Nuevos datos de la categoría") @RequestBody Categoria categoria) {
        Categoria existingCategoria = categoriaService.findById(id);
        if (existingCategoria != null) {
            categoria.setIdCategoria(id);
            Categoria updatedCategoria = categoriaService.save(categoria);
            return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una categoría por ID
    @Operation(summary = "Eliminar una categoría", description = "Elimina una categoría según el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(
            @Parameter(description = "ID de la categoría a eliminar") @PathVariable Integer id) {
        Categoria existingCategoria = categoriaService.findById(id);
        if (existingCategoria != null) {
            categoriaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
