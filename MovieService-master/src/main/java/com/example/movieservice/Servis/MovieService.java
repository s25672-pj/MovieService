package com.example.movieservice.Servis;

import com.example.movieservice.Model.Movie;
import com.example.movieservice.Repository.MovieRepository;
import com.example.movieservice.exceptions.MovieNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public Movie findById(Long id){
        return movieRepository.findById(id).orElseThrow(MovieNotFoundExceptions::new);
    }

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    public Movie saveMovie(Movie movie){
        return  movieRepository.save(movie);
    }

    public void updateMovie(Movie movieWithUpdate){
        Movie movieInBase = findById(movieWithUpdate.getId());
        if(movieInBase != null){
            if(movieWithUpdate.getName() != null){
                movieInBase.setName(movieWithUpdate.getName());
            }
            if(movieWithUpdate.getCategory() != null){
                movieInBase.setCategory(movieWithUpdate.getCategory());
            }

        }else {
            throw new IllegalArgumentException("Nie udało sie zaktualizować, film o id " + movieWithUpdate.getId() + " nie został odnaleziony w bazie");
        }
        movieRepository.save(movieInBase);
    }

    public void deleteMovie(Long id){
        Movie movieToDelete = findById(id);
        if (movieToDelete != null){
            movieRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Nie udało się usunąć, film  id = " + id + ", nie został odnaleziony w bazie");
        }
    }

    public void changeIsA(Long id) {
        Movie target = findById(id);
        target.setAvailable(!target.isAvailable());
        movieRepository.save(target);
    }
}

