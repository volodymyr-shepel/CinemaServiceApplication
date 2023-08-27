package com.example.movieApp.api.admin.movieSession;

import com.example.movieApp.entities.Movie;
import com.example.movieApp.entities.MovieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/movieSession")
public class AdminMovieSessionController {
    private final AdminMovieSessionService adminMovieSessionService;

    @Autowired
    public AdminMovieSessionController(AdminMovieSessionService adminMovieSessionService) {
        this.adminMovieSessionService = adminMovieSessionService;
    }

    @GetMapping(path = "/getAllMovieSessions")
    public List<MovieSession> getAllMovieSessions(){
       return adminMovieSessionService.getAllMovieSessions();
    }
    @PostMapping(path = "/addSession")
    public ResponseEntity<MovieSession> addMovieSession(@RequestBody MovieSessionDTO movieSessionDTO){
        return adminMovieSessionService.addMovieSession(movieSessionDTO);
    }
    @DeleteMapping(path = "/removeSession/{movieSessionId}")
    public ResponseEntity<Long> removeMovieSession(@PathVariable Long movieSessionId){
        return adminMovieSessionService.removeMovieSession(movieSessionId);
    }
//    @PutMapping(path = "/updateSession/{movieSessionId}")
//    public ResponseEntity<MovieSession> updateMovieSession(@PathVariable Long movieSessionId,MovieSessionDTO movieSessionDTO){
//        return adminMovieSessionService.updateMovieSession(movieSessionId,movieSessionDTO);
//    }
}