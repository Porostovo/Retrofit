package org.example.springadvanced.repositories;

import org.example.springadvanced.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
