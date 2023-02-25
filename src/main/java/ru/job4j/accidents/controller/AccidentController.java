package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accidents.service.AccidentService;

@Controller
@RequestMapping("/accidents")
public class AccidentController {

    private final AccidentService accidentService;

    public AccidentController(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/all")
    public String findAllAccidents(Model model) {
        model.addAttribute("accidents", accidentService.getAllAccidents());
        return "accidents/all";
    }
}
