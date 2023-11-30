package com.driver.Repository;

import com.driver.Models.Director;
import com.driver.Models.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String, Movie> movies = new HashMap<>();
    private HashMap<String, Director> directors = new HashMap<>();
    private HashMap<String, String> movieDirectorPairs = new HashMap<>();

    public void addMovie(Movie movie){
        movies.put(movie.getName(), movie);
    }

    public void addDirector(Director director){
        directors.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName){
        movieDirectorPairs.put(movieName, directorName);
    }

    public Movie getMovieByName(String name){
        return movies.get(name);
    }

    public Director getDirectorByName(String name){
        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String director){
        List<String> directorMovies = new ArrayList<>();
        for(var entry : movieDirectorPairs.entrySet()){
            if(entry.getValue().equals(director)){
                directorMovies.add(entry.getKey());
            }
        }
        return directorMovies;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movies.keySet());
    }

    public void deleteDirectorByName(String directorName){
        // Remove director and associated movie
        directors.remove(directorName);

        // Remove movie associated with director
        List<String> moviesToRemove = new ArrayList<>();
        for(var entry : movieDirectorPairs.entrySet()){
            if(entry.getValue().equals(directorName)){
                moviesToRemove.add(entry.getKey());
            }
        }

        for(String movie : moviesToRemove){
            movies.remove(movie);
            movieDirectorPairs.remove(movie);
        }
    }

    public void deleteAllDirectors(){
        // Get a list of all director names
        List<String> directorNames = new ArrayList<>(directors.keySet());

        // Iterate through each director and remove associated movies
        for (String directorName : directorNames) {
            // Remove movies associated with the director
            List<String> moviesToRemove = new ArrayList<>();
            for (var entry : movieDirectorPairs.entrySet()) {
                if (entry.getValue().equals(directorName)) {
                    moviesToRemove.add(entry.getKey());
                }
            }
            for (String movie : moviesToRemove) {
                movies.remove(movie);
                movieDirectorPairs.remove(movie);
            }
        }

        // Clear directors and associated data
        directors.clear();
    }
}

