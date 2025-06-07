package com.utp.demoCardinalidad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.demoCardinalidad.model.Producto;
import com.utp.demoCardinalidad.service.ProductoService;
import com.utp.demoCardinalidad.DTO.ProductoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto;
    private ProductoDTO productoDTO;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Test Producto");
        producto.setPrecio(100.0);

        productoDTO = new ProductoDTO();
        productoDTO.setIdProducto(1);
        productoDTO.setNombre("Test Producto");
        productoDTO.setPrecio(100.0);
    }

    @Test
    void getAllProductos_ShouldReturnProductoList() throws Exception {
        when(productoService.findAll()).thenReturn(Arrays.asList(productoDTO));

        mockMvc.perform(get("/api/v1/producto"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idProducto").value(productoDTO.getIdProducto()))
                .andExpect(jsonPath("$[0].nombre").value(productoDTO.getNombre()));
    }

    @Test
    void getProductoById_ShouldReturnProducto() throws Exception {
        when(productoService.findById(1)).thenReturn(producto);

        mockMvc.perform(get("/api/v1/producto/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idProducto").value(producto.getIdProducto()))
                .andExpect(jsonPath("$.nombre").value(producto.getNombre()));
    }

    @Test
    void getProductoById_WhenNotFound_ShouldReturn404() throws Exception {
        when(productoService.findById(1)).thenReturn(null);

        mockMvc.perform(get("/api/v1/producto/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createProducto_ShouldReturnCreatedProducto() throws Exception {
        when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/v1/producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idProducto").value(producto.getIdProducto()))
                .andExpect(jsonPath("$.nombre").value(producto.getNombre()));
    }

    @Test
    void updateProducto_ShouldReturnUpdatedProducto() throws Exception {
        when(productoService.findById(1)).thenReturn(producto);
        when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/api/v1/producto/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProducto").value(producto.getIdProducto()))
                .andExpect(jsonPath("$.nombre").value(producto.getNombre()));
    }

    @Test
    void updateProducto_WhenNotFound_ShouldReturn404() throws Exception {
        when(productoService.findById(1)).thenReturn(null);

        mockMvc.perform(put("/api/v1/producto/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteProducto_ShouldReturn204() throws Exception {
        when(productoService.findById(1)).thenReturn(producto);

        mockMvc.perform(delete("/api/v1/producto/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteProducto_WhenNotFound_ShouldReturn404() throws Exception {
        when(productoService.findById(1)).thenReturn(null);

        mockMvc.perform(delete("/api/v1/producto/1"))
                .andExpect(status().isNotFound());
    }
} 