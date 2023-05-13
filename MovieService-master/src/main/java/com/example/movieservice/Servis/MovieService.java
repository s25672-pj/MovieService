package com.example.movieservice.Servis;

import com.example.movieservice.Model.Movie;
import com.example.movieservice.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.getAll();
    }

    public Movie finById(long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        } else {
            throw new RuntimeException();
        }
    }

    public void updateMovie(Movie movieWithUpdatedData){
        Movie movieInBase = finById(movieWithUpdatedData.getId());
        if(movieInBase != null){
            if(movieWithUpdatedData.getName() != null){
                movieInBase.setName(movieWithUpdatedData.getName());
            }
            if(movieWithUpdatedData.getCategory() != null){
                movieInBase.setCategory(movieWithUpdatedData.getCategory());
            }else{
                throw new IllegalArgumentException("Bledne dane");
            }
        }else {
            throw new IllegalArgumentException("Brak filmu o id: " + movieWithUpdatedData.getId());
        }
    }

    public void deleteMovie(long id){
        Movie deletingMovie = finById(id);
        if (deletingMovie != null){
            movieRepository.remove(deletingMovie);
        }else {
            throw new IllegalArgumentException("Brak filmu o id: "+ id);
        }
    }
}
