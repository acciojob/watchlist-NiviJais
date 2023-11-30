package com.driver.Services;

import com.driver.Models.Director;
import com.driver.Models.Movie;
import com.driver.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public ResponseEntity<String> addMovie(Movie movie){
        movieRepository.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie added successfully");
    }

    public ResponseEntity<String> addDirector(Director director) {
        movieRepository.addDirector(director);
        return ResponseEntity.status(HttpStatus.CREATED).body("Director added successfully");
    }

    public ResponseEntity<String> addMovieDirectorPair(String movieName, String directorName){
        movieRepository.addMovieDirectorPair(movieName,directorName);
        return ResponseEntity.ok("Movie-Director Pair added Successfully");
    }

    public ResponseEntity<Movie> getMovieByName(String name){
        Movie movie = movieRepository.getMovieByName(name);
        return movie != null ? ResponseEntity.ok(movie) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<Director> getDirectorByName(String name){
         Director director  = movieRepository.getDirectorByName(name);
        return director != null ? ResponseEntity.ok(director) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public ResponseEntity<List<String>> getMoviesByDirectorName(String director) {
        List<String> movies = movieRepository.getMoviesByDirectorName(director);
        return ResponseEntity.ok(movies);
    }

    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movies = movieRepository.findAllMovies();
        return ResponseEntity.ok(movies);
    }

    public ResponseEntity<String> deleteDirectorByName(String directorName) {
        movieRepository.deleteDirectorByName(directorName);
        return ResponseEntity.ok("Director and associated movies deleted successfully");
    }

    public ResponseEntity<String> deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
        return ResponseEntity.ok("All directors and their movies deleted successfully");
    }

}
