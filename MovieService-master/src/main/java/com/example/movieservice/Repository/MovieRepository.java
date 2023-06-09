package com.example.movieservice.Repository;

import com.example.movieservice.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Override
    Optional<Movie> findById(Long aLong);
    @Override
    <S extends Movie> S save(S entity);
    @Override
    void deleteById(Long aLong);
    @Override
    List<Movie> findAll();

}
