package com.example.movieservice.Controller;

import com.example.movieservice.Model.Movie;
import com.example.movieservice.Servis.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    public final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }



    @GetMapping("/Movies")
    public ResponseEntity<List<Movie>> showAllMovies(){
//        return (ResponseEntity<List<Movie>>) this.movieService.getAllMovies();
        return ResponseEntity.ok(movieService.getAllMovies());
    }



    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> findById(@PathVariable long id){
        return ResponseEntity.ok(movieService.finById(id));
    }

    //edp adding new movie

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        if (movie != null){
            movieService.save(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //updating movie

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody Movie movie){
        if (findById(id) != null) {
            movie.setId(id);
            movieService.updateMovie(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //Deleting movie

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deletingMovie(@PathVariable long id){
        if (findById(id) != null){
            movieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
