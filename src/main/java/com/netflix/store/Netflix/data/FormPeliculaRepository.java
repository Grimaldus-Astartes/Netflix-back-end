package com.netflix.store.Netflix.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormPeliculaRepository extends JpaRepository<Pelicula, Integer> {
}
