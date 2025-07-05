package com.utp.demoCardinalidad.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;

class CategoriaTest {

    @Test
    void testCategoriaBuilder() {
        // Given
        Categoria categoria = Categoria.builder()
                .idCategoria(1)
                .nombre("Test Categoria")
                .build();

        // Then
        assertThat(categoria.getIdCategoria()).isEqualTo(1);
        assertThat(categoria.getNombre()).isEqualTo("Test Categoria");
    }

    @Test
    void testCategoriaSettersAndGetters() {
        // Given
        Categoria categoria = new Categoria();

        // When
        categoria.setIdCategoria(1);
        categoria.setNombre("Test Categoria");

        // Then
        assertThat(categoria.getIdCategoria()).isEqualTo(1);
        assertThat(categoria.getNombre()).isEqualTo("Test Categoria");
    }

    @Test
    void testCategoriaWithProductos() {
        // Given
        Producto producto1 = Producto.builder()
                .idProducto(1)
                .nombre("Test Producto 1")
                .precio(100.0)
                .build();

        Producto producto2 = Producto.builder()
                .idProducto(2)
                .nombre("Test Producto 2")
                .precio(200.0)
                .build();

        Categoria categoria = Categoria.builder()
                .idCategoria(1)
                .nombre("Test Categoria")
                .productos(Arrays.asList(producto1, producto2))
                .build();

        // Then
        assertThat(categoria.getProductos()).hasSize(2);
        assertThat(categoria.getProductos().get(0).getNombre()).isEqualTo("Test Producto 1");
        assertThat(categoria.getProductos().get(1).getNombre()).isEqualTo("Test Producto 2");
    }
} 