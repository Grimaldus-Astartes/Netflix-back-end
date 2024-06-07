package com.netflix.store.Netflix.webservice;

import com.netflix.store.Netflix.business.StoreService;
import com.netflix.store.Netflix.dto.PeliculaDTO;
import com.netflix.store.Netflix.exceptions.PeliculaAlreadyExistsException;
import com.netflix.store.Netflix.exceptions.PeliculaIncorrectDataException;
import com.netflix.store.Netflix.exceptions.PeliculaNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PeliculaWebService implements WebMvcConfigurer {
    private final StoreService service;

    public PeliculaWebService(StoreService service) {
        this.service = service;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }

    @GetMapping(value = "/peliculas")
    public List<PeliculaDTO> getPeliculas() {
        List<PeliculaDTO> peliculas = service.getPeliculas();
        System.out.println(peliculas);
        return peliculas;
    }

    @PostMapping(value = "/peliculas")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addPelicula(@Valid @RequestBody PeliculaDTO peliculaDTO) throws SQLException {
        service.savePelicula(peliculaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Película creada exitosamente");
    }

    @DeleteMapping("/peliculas/{idPelicula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deletePelicula(@PathVariable Integer idPelicula) {
        try {
            service.deletePelicula(idPelicula);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Pelicula eliminada");
        } catch (PeliculaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SQLException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/peliculas/{idPelicula}")
    public ResponseEntity<String> updatePelicula(@PathVariable Integer idPelicula, @RequestBody PeliculaDTO peliculaDTO) {
        try {
            if (!idPelicula.equals(peliculaDTO.getIdPelicula())) {
                throw new PeliculaIncorrectDataException("El ID en la URL y en el cuerpo de la solicitud no coinciden");
            }
            service.updatePelicula(peliculaDTO);
            return ResponseEntity.ok("Película actualizada correctamente");
        } catch (PeliculaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Película no encontrada");
        } catch (PeliculaIncorrectDataException e) {
            return ResponseEntity.badRequest().body("Datos de película incorrectos: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}
