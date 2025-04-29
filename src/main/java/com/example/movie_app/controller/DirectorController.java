package com.example.movie_app.controller;


import com.example.movie_app.domain.Director;
import com.example.movie_app.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;


    @GetMapping("/list")
    public String getAllDirectors(Model model) {
        List<Director> directors = directorService.getAllDirectors();
        model.addAttribute("directors", directors);
        return "directors/directors";
    }

    @GetMapping("/new")
    public String createDirectorForm(Model model) {
        model.addAttribute("director", new Director());
        return "directors/create-director"; // Template for creating authors
    }

    @PostMapping
    public String saveDirector(@ModelAttribute Director director) {
        directorService.save(director);
        return "redirect:/directors/list";
    }

    @GetMapping("/edit/{id}")
    public String editMovieForm(@PathVariable UUID id, Model model) {
        Director director = directorService.findById(id);
        model.addAttribute("director", director);
        return "directors/edit-director";
    }

    @PostMapping("/edit")
    public String updateDirector(@ModelAttribute Director director) {
        directorService.edit(director);
        return "redirect:/directors/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteDirector(@PathVariable UUID id) {
        directorService.deleteById(id);
        return "redirect:/directors/list";
    }

    @GetMapping("/search")
    public String searchDirector(@RequestParam String name, Model model) {
        List<Director> directors = directorService.searchByName(name);
        model.addAttribute("directors", directors);
        return "directors/directors";
    }

}
