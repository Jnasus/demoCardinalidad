package com.utp.demoCardinalidad.service;

import com.utp.demoCardinalidad.model.Categoria;
import com.utp.demoCardinalidad.repository.CategoriaRepository;
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
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setNombre("Test Categoria");
    }

    @Test
    void whenFindAll_thenReturnCategoriaList() {
        // Given
        when(categoriaRepository.findAll()).thenReturn(Arrays.asList(categoria));

        // When
        List<Categoria> found = categoriaService.findAll();

        // Then
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getIdCategoria()).isEqualTo(categoria.getIdCategoria());
        assertThat(found.get(0).getNombre()).isEqualTo(categoria.getNombre());
        verify(categoriaRepository).findAll();
    }

    @Test
    void whenFindById_thenReturnCategoria() {
        // Given
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));

        // When
        Categoria found = categoriaService.findById(1);

        // Then
        assertThat(found).isNotNull();
        assertThat(found.getIdCategoria()).isEqualTo(categoria.getIdCategoria());
        verify(categoriaRepository).findById(1);
    }

    @Test
    void whenSave_thenReturnCategoria() {
        // Given
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        // When
        Categoria saved = categoriaService.save(categoria);

        // Then
        assertThat(saved).isNotNull();
        assertThat(saved.getIdCategoria()).isEqualTo(categoria.getIdCategoria());
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void whenDeleteById_thenRepositoryMethodCalled() {
        // Given
        doNothing().when(categoriaRepository).deleteById(1);

        // When
        categoriaService.deleteById(1);

        // Then
        verify(categoriaRepository).deleteById(1);
    }
} 