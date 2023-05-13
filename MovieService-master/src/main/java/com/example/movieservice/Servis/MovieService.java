package com.example.movieservice.Servis;

import com.example.movieservice.Model.Movie;
import com.example.movieservice.Repository.MovieRepository;
import com.example.movieservice.exceptions.MovieNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie findById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(MovieNotFoundExceptions::new);
    }

    public List<Movie> getAll(){
        return movieRepository.findAll();
    }

    public Movie saveMovie(Movie movie){
        return  movieRepository.save(movie);
    }
    //public Movie deleteMovie(Long id){
      //  return movieRepository.deleteById(id);


    //}

    //public void updateMovie(Movie movieNewData, Long id){
      //  Movie movie = findById(id);
       // if (movie != null){
        //    movie.setCategory(movieNewData.getCategory());
         //   movie.setName(movieNewData.getName());
        //}
    // }

    //}
}
