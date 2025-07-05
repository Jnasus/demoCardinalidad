package com.utp.demoCardinalidad.service;

import com.utp.demoCardinalidad.model.Producto;
import com.utp.demoCardinalidad.model.Categoria;
import com.utp.demoCardinalidad.repository.ProductoRepository;
import com.utp.demoCardinalidad.DTO.ProductoDTO;
import com.utp.demoCardinalidad.DTO.CategoriaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;
    private ProductoDTO productoDTO;
    private Categoria categoria;
    private CategoriaDTO categoriaDTO;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setNombre("Test Categoria");

        categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdCategoria(1);
        categoriaDTO.setNombre("Test Categoria");

        producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Test Producto");
        producto.setPrecio(100.0);
        producto.setCategoria(categoria);

        productoDTO = new ProductoDTO();
        productoDTO.setIdProducto(1);
        productoDTO.setNombre("Test Producto");
        productoDTO.setPrecio(100.0);
        productoDTO.setCategoriaDTO(categoriaDTO);
    }

    @Test
    void whenFindAll_thenReturnProductoDTOList() {
        // Given
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));

        // When
        List<ProductoDTO> found = productoService.findAll();

        // Then
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getIdProducto()).isEqualTo(producto.getIdProducto());
        assertThat(found.get(0).getNombre()).isEqualTo(producto.getNombre());
        assertThat(found.get(0).getCategoriaDTO().getNombre()).isEqualTo(producto.getCategoria().getNombre());
        verify(productoRepository).findAll();
    }

    @Test
    void whenFindById_thenReturnProducto() {
        // Given
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        // When
        Producto found = productoService.findById(1);

        // Then
        assertThat(found).isNotNull();
        assertThat(found.getIdProducto()).isEqualTo(producto.getIdProducto());
        verify(productoRepository).findById(1);
    }

    @Test
    void whenSave_thenReturnProducto() {
        // Given
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        // When
        Producto saved = productoService.save(producto);

        // Then
        assertThat(saved).isNotNull();
        assertThat(saved.getIdProducto()).isEqualTo(producto.getIdProducto());
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    void whenDeleteById_thenRepositoryMethodCalled() {
        // Given
        doNothing().when(productoRepository).deleteById(1);

        // When
        productoService.deleteById(1);

        // Then
        verify(productoRepository).deleteById(1);
    }
} 