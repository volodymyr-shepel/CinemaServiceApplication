package com.example.movieApp.api.unauthorizedUser;


import com.example.movieApp.entities.Movie;
import com.example.movieApp.entities.MovieSession;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/public")
public class UnauthorizedUserController {

    private final UnauthorizedUserService unauthorizedUserService;

    @Autowired
    public UnauthorizedUserController(UnauthorizedUserService unauthorizedUserService) {
        this.unauthorizedUserService = unauthorizedUserService;
    }

    @GetMapping(path = "/getAllMovies")
    public List<Movie> getAllMovies(){
        return unauthorizedUserService.getAllMovies();
    }
    @GetMapping(path = "/getMovieSessions")
    public List<MovieSession> getMovieSessionsByDate(
            @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    )  {
        return unauthorizedUserService.getMovieSessionsByDate(date);
    }

}

