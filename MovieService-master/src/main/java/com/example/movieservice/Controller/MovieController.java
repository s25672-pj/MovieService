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

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllFilms(){
        if(MovieService.getAll() == null){
            return ResponseEntity.ok(MovieService.getAll());
        }else{
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        if (MovieService.findById(id) != null) {
            return ResponseEntity.ok(MovieService.findById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        if (movie != null) {
            MovieService.saveMovie(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/movies/isAvaiable/{id}")
    public ResponseEntity<Void> updateMovieIsAvailable(@PathVariable Long id){
        movieService.changeIsA(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        if (getMovieById(id) != null) {
            movie.setId(id);
            MovieService.updateMovie(movie);
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        if(getMovieById(id) != null){
            MovieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
