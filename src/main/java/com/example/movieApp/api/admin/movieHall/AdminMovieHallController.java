package com.example.movieApp.api.admin.movieHall;

import com.example.movieApp.entities.Movie;
import com.example.movieApp.entities.MovieHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/movieHall")
public class AdminMovieHallController {
    private final AdminMovieHallService adminMovieHallService;

    @Autowired
    public AdminMovieHallController(AdminMovieHallService adminMovieHallService) {
        this.adminMovieHallService = adminMovieHallService;
    }

    @GetMapping(path = "/getAllMovieHalls")
    public List<MovieHall> getAllMovieHalls(){
        return adminMovieHallService.getAllMovieHalls();
    }

    @PostMapping(path = "/addMovieHall")
    public ResponseEntity<MovieHall> addMovieHall(@RequestBody MovieHallDTO movieHallDTO) {
        return adminMovieHallService.addMovieHall(movieHallDTO);
    }


//    @PutMapping(path = "/updateMovieHall/{movieHallId}")
//    public ResponseEntity<String> updateMovieHall(@PathVariable Long movieHallId, @RequestBody MovieHall movieHall) {
//        return adminMovieHallService.updateMovieHall(movieHallId, movieHall);
//
//    }

    @DeleteMapping(path = "/removeMovieHall/{movieHallId}")
    public ResponseEntity<Long> removeMovieHall(@PathVariable Long movieHallId) {
        return adminMovieHallService.removeMovieHall(movieHallId);
    }
}