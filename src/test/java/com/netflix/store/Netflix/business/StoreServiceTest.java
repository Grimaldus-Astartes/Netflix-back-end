package com.netflix.store.Netflix.business;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.netflix.store.Netflix.data.FormPeliculaRepository;
import com.netflix.store.Netflix.data.Pelicula;
import com.netflix.store.Netflix.dto.PeliculaDTO;
import com.netflix.store.Netflix.exceptions.PeliculaAlreadyExistsException;

import com.netflix.store.Netflix.exceptions.PeliculaIncorrectDataException;
import com.netflix.store.Netflix.exceptions.PeliculaNotFoundException;
import com.netflix.store.Netflix.mappers.PeliculaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.test.context.jdbc.Sql;

import java.sql.SQLException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StoreServiceTest {

    @Mock
    private FormPeliculaRepository formPeliculaRepository;
    @Mock
    private PeliculaMapper peliculaMapper;

    @InjectMocks
    private StoreService storeService;

    private PeliculaDTO peliculaDTO;
    private Pelicula pelicula;

    @BeforeEach
    void setUp() {
        peliculaDTO = new PeliculaDTO();
        peliculaDTO.setIdPelicula(8);
        peliculaDTO.setNombreCompleto("Test Pelicula");
        peliculaDTO.setNombreCorto("Test");
        peliculaDTO.setDirector("Test Director");
        peliculaDTO.setReparto("Test Reparto");
        peliculaDTO.setFamiliar(true);
        peliculaDTO.setProductora("Test Productora");
        peliculaDTO.setPoster("Test Poster");
        peliculaDTO.setExistencia(10);

        pelicula = new Pelicula();
        pelicula.setIdPelicula(8);
        pelicula.setNombreCompleto("Test Pelicula");
        pelicula.setNombreCorto("Test");
        pelicula.setDirector("Test Director");
        pelicula.setReparto("Test Reparto");
        pelicula.setFamiliar(true);
        pelicula.setProductora("Test Productora");
        pelicula.setPoster("Test Poster");
        pelicula.setExistencia(10);
    }

    @Test
    void testSavePelicula_ExistingPelicula() {
        // Configura el mock para que devuelva una película existente con el ID 8
        when(formPeliculaRepository.findById(8)).thenReturn(Optional.of(pelicula));

        // Agrega mensajes de depuración
        System.out.println("Mock configurado para devolver una película existente.");

        try {
            storeService.savePelicula(peliculaDTO);
            fail("Debería lanzar PeliculaAlreadyExistsException");
        } catch (PeliculaAlreadyExistsException e) {
            System.out.println("Excepción lanzada correctamente: " + e.getMessage());
        } catch (SQLException e) {
            fail("No debería lanzar SQLException");
        }

        // Verifica que el método findById fue llamado con el argumento 8
        verify(formPeliculaRepository, times(1)).findById(8);

        // Verifica que el método save no fue llamado
        verify(formPeliculaRepository, never()).save(any(Pelicula.class));

        // Verifica que no hubo más interacciones con el mock
        verifyNoMoreInteractions(formPeliculaRepository);
    }

    @Test
    void testFindByIdInteraction() {
        // Configura el mock para que devuelva una película existente con el ID 8
        when(formPeliculaRepository.findById(8)).thenReturn(Optional.of(pelicula));

        // Llama directamente al mock para verificar la interacción
        formPeliculaRepository.findById(8);

        // Verifica que el método findById fue llamado con el argumento 8
        verify(formPeliculaRepository, times(1)).findById(8);
    }

    @Test
    void testSavePelicula_NewPelicula() throws SQLException {
        // Configura el mock para que no devuelva ninguna película existente con el ID 1
        when(formPeliculaRepository.findById(8)).thenReturn(Optional.empty());
        // Configura el comportamiento del mock PeliculaMapper
        when(peliculaMapper.toEntity(any(PeliculaDTO.class))).thenReturn(pelicula);
        when(peliculaMapper.toDto(any(Pelicula.class))).thenReturn(peliculaDTO);
        when(formPeliculaRepository.save(any(Pelicula.class))).thenReturn(pelicula);

        PeliculaDTO savedPeliculaDTO = storeService.savePelicula(peliculaDTO);

        assertNotNull(savedPeliculaDTO);
        assertEquals("Test Pelicula", savedPeliculaDTO.getNombreCompleto());
        verify(formPeliculaRepository, times(1)).save(any(Pelicula.class));
    }

    @Test
    public void testDeletePelicula() {
        // Configurar el comportamiento del mock
        when(formPeliculaRepository.findById(8)).thenReturn(Optional.of(pelicula));
        doNothing().when(formPeliculaRepository).deleteById(8);


        try {
            // Llamar al método que queremos probar
            storeService.deletePelicula(pelicula.getIdPelicula());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        // Verificar que los métodos se llamaron correctamente
        verify(formPeliculaRepository, times(1)).findById(8);
        verify(formPeliculaRepository, times(1)).delete(pelicula);
    }

    @Test
    public void testDeletePelicula_NotExisting() {
        // Simulamos que no se encuentra la película con ID 8
        when(formPeliculaRepository.findById(8)).thenReturn(Optional.empty());

        try {
            // Llamamos al método deletePelicula con un ID de película
            storeService.deletePelicula(8); // Aquí deberías usar el ID correcto
            fail("Debería lanzar PeliculaNotFoundException");
        } catch (PeliculaNotFoundException e) {
            System.out.println("\"Excepción lanzada correctamente: \" + e.getMessage()");
        } catch (SQLException e) {
            System.out.println("Excepción erronea: " + e.getMessage());
        }

        // Verificamos que se haya llamado a findById con el ID correcto
        verify(formPeliculaRepository, times(1)).findById(pelicula.getIdPelicula());

        // Verificamos que no se haya llamado al método delete
        verify(formPeliculaRepository, never()).delete(any(Pelicula.class));
    }

    @Test
    void shouldThrowExceptionWhenPeliculaDTOIsEmpty() {
        PeliculaDTO peliculaVaciaDTO = new PeliculaDTO(); // Pelicula vacía
        try {
            storeService.updatePelicula(peliculaVaciaDTO);
            fail("Debe lanzar PeliculaIncorrectDataException");
        } catch (PeliculaNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (PeliculaIncorrectDataException e) {
            System.out.println(e.getMessage());
        }

        // Verificamos que nunca se haya llamado el método save
        verify(formPeliculaRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenPeliculaDoesNotExist() {
        when(formPeliculaRepository.findById(anyInt())).thenReturn(Optional.empty());

        try {
            storeService.updatePelicula(peliculaDTO);
            fail("Debe lanzar PeliculaNotFoundException");
        } catch (PeliculaNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (PeliculaIncorrectDataException e) {
            System.out.println(e.getMessage());
        }

        // Verificamos que nunca se haya llamado el método updatePeliculaFromDto
        verify(peliculaMapper, never()).updatePeliculaFromDto(any(), any());
        // Verificamos que nunca se haya llamado el método save
        verify(formPeliculaRepository, never()).save(any());
    }

    @Test
    void shouldUpdatePeliculaSuccessfully() {
        when(formPeliculaRepository.findById(anyInt())).thenReturn(Optional.of(pelicula));
        doNothing().when(peliculaMapper).updatePeliculaFromDto(any(PeliculaDTO.class), any(Pelicula.class));

        try {
            storeService.updatePelicula(peliculaDTO);
        } catch (PeliculaNotFoundException e) {
            fail("No debe lanzar PeliculaNotFoundException");
        } catch (PeliculaIncorrectDataException e) {
            fail("No debe lanzar PeliculaIncorrectDataException");
        }

        // Verificamos que el método updatePeliculaFromDto fue llamado una vez
        verify(peliculaMapper, times(1)).updatePeliculaFromDto(any(PeliculaDTO.class), any(Pelicula.class));
        // Verificamos que el método save fue llamado una vez
        verify(formPeliculaRepository, times(1)).save(any(Pelicula.class));
    }
}
