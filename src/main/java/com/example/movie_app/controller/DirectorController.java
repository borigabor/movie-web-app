package com.example.movie_app.controller;


import com.example.movie_app.domain.Director;
import com.example.movie_app.service.DirectorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/directors")
public class DirectorController {


    private DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/list")
    public String getAllDirectors(Model model) {
        List<Director> directors = directorService.getAllDirectors();
        model.addAttribute("directors", directors);
        return "directors/directors";
    }


    @PostMapping("/delete/{id}")
    public String deleteDirector(@PathVariable UUID id) {
        directorService.deleteById(id);
        return "redirect:/directors/list"; // Redirect to /authors/list after deleting
    }

}
