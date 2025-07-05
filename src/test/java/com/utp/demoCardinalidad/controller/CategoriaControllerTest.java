package com.utp.demoCardinalidad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.demoCardinalidad.model.Categoria;
import com.utp.demoCardinalidad.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setNombre("Test Categoria");
    }

    @Test
    void getAllCategorias_ShouldReturnCategoriaList() throws Exception {
        when(categoriaService.findAll()).thenReturn(Arrays.asList(categoria));

        mockMvc.perform(get("/api/v1/categoria"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idCategoria").value(categoria.getIdCategoria()))
                .andExpect(jsonPath("$[0].nombre").value(categoria.getNombre()));
    }

    @Test
    void getCategoriaById_ShouldReturnCategoria() throws Exception {
        when(categoriaService.findById(1)).thenReturn(categoria);

        mockMvc.perform(get("/api/v1/categoria/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idCategoria").value(categoria.getIdCategoria()))
                .andExpect(jsonPath("$.nombre").value(categoria.getNombre()));
    }

    @Test
    void getCategoriaById_WhenNotFound_ShouldReturn404() throws Exception {
        when(categoriaService.findById(1)).thenReturn(null);

        mockMvc.perform(get("/api/v1/categoria/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createCategoria_ShouldReturnCreatedCategoria() throws Exception {
        when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);

        mockMvc.perform(post("/api/v1/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCategoria").value(categoria.getIdCategoria()))
                .andExpect(jsonPath("$.nombre").value(categoria.getNombre()));
    }

    @Test
    void updateCategoria_ShouldReturnUpdatedCategoria() throws Exception {
        when(categoriaService.findById(1)).thenReturn(categoria);
        when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);

        mockMvc.perform(put("/api/v1/categoria/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCategoria").value(categoria.getIdCategoria()))
                .andExpect(jsonPath("$.nombre").value(categoria.getNombre()));
    }

    @Test
    void updateCategoria_WhenNotFound_ShouldReturn404() throws Exception {
        when(categoriaService.findById(1)).thenReturn(null);

        mockMvc.perform(put("/api/v1/categoria/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCategoria_ShouldReturn204() throws Exception {
        when(categoriaService.findById(1)).thenReturn(categoria);

        mockMvc.perform(delete("/api/v1/categoria/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCategoria_WhenNotFound_ShouldReturn404() throws Exception {
        when(categoriaService.findById(1)).thenReturn(null);

        mockMvc.perform(delete("/api/v1/categoria/1"))
                .andExpect(status().isNotFound());
    }
} 