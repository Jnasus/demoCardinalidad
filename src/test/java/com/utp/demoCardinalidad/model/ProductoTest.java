package com.utp.demoCardinalidad.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ProductoTest {

    @Test
    void testProductoBuilder() {
        // Given
        Producto producto = Producto.builder()
                .idProducto(1)
                .nombre("Test Producto")
                .precio(100.0)
                .build();

        // Then
        assertThat(producto.getIdProducto()).isEqualTo(1);
        assertThat(producto.getNombre()).isEqualTo("Test Producto");
        assertThat(producto.getPrecio()).isEqualTo(100.0);
    }

    @Test
    void testProductoSettersAndGetters() {
        // Given
        Producto producto = new Producto();

        // When
        producto.setIdProducto(1);
        producto.setNombre("Test Producto");
        producto.setPrecio(100.0);

        // Then
        assertThat(producto.getIdProducto()).isEqualTo(1);
        assertThat(producto.getNombre()).isEqualTo("Test Producto");
        assertThat(producto.getPrecio()).isEqualTo(100.0);
    }

    @Test
    void testProductoWithCategoria() {
        // Given
        Categoria categoria = Categoria.builder()
                .idCategoria(1)
                .nombre("Test Categoria")
                .build();

        Producto producto = Producto.builder()
                .idProducto(1)
                .nombre("Test Producto")
                .precio(100.0)
                .categoria(categoria)
                .build();

        // Then
        assertThat(producto.getCategoria()).isNotNull();
        assertThat(producto.getCategoria().getNombre()).isEqualTo("Test Categoria");
    }
} 