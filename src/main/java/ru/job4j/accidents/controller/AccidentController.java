package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.Accident;

import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.Optional;

@Controller
public class AccidentController {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;

    public AccidentController(AccidentService accidentService, AccidentTypeService accidentTypeService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
    }

    @GetMapping("/all")
    public String findAllAccidents(Model model) {
        model.addAttribute("accidents", accidentService.getAllAccidents());
        return "accidents/all";
    }

    @GetMapping("/create")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.getAllTypes());
        return "accidents/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidentService.addAccident(accident);
        return "redirect:/index";
    }

    @GetMapping("/edit/{accidentId}")
    public String getFormEdit(Model model, @PathVariable("accidentId") int id) {
        Optional<Accident> optionalAccident = accidentService.findById(id);
        if (optionalAccident.isEmpty()) {
            return "shared/error404";
        }
        Accident accident = optionalAccident.get();
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentTypeService.getAllTypes());
        return "accidents/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident) {
        if (!accidentService.update(accident)) {
            return "shared/error404";
        }
        return "redirect:/index";
    }
}
