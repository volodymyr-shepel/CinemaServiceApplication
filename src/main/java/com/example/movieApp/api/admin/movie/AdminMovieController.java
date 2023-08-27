package com.example.movieApp.api.admin.movie;

import com.example.movieApp.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/movie")
public class AdminMovieController {

    private final AdminMovieService adminMovieService;

    @Autowired
    public AdminMovieController(AdminMovieService adminService) {
        this.adminMovieService = adminService;
    }


    @PostMapping(path = "/addMovie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
            return adminMovieService.addMovie(movie);
    }

    @DeleteMapping(path = "/removeMovie/{movieId}")
    public ResponseEntity<Long> removeMovie(@PathVariable Long movieId){
        return adminMovieService.removeMovie(movieId);
    }

    @PutMapping(path = "/updateMovie/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long movieId,@RequestBody Movie updatedMovie){
        return adminMovieService.updateMovie(movieId,updatedMovie);
    }
}
