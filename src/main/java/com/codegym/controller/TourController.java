package com.codegym.controller;


import com.codegym.model.Tour;
import com.codegym.model.Type;
import com.codegym.service.ITourService;
import com.codegym.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TourController {
    @Autowired
    private ITourService tourService;

    @Autowired
    private ITypeService typeService;

    @ModelAttribute("types")
    public Iterable<Type> listType() {
        return typeService.findAll();
    }

    @GetMapping("/tour")
    public ModelAndView listTour() {
        ModelAndView modelAndView = new ModelAndView("list");
        Iterable<Tour> tours = tourService.findAll();
        modelAndView.addObject("tours", tours);
        return modelAndView;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("tour", new Tour());
        return "create";
    }

    @PostMapping("/create")
    public String checkValidation(@Valid Tour tour, Model model, BindingResult bindingResult) {
        new Tour().validate(tour, bindingResult);
        if (bindingResult.hasErrors()) {
            return "create";
        }
        tourService.save(tour);
        model.addAttribute("message", "New tour created successfully");
        return "redirect:/tour";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Tour> tour = tourService.findById(id);
        if (tour.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/update");
            modelAndView.addObject("tour", tour.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String checkValidationUpdate(@Valid Tour tour, Model model, BindingResult bindingResult) {
        new Tour().validate(tour, bindingResult);
        if (bindingResult.hasErrors()) {
            return "update";
        }
        tourService.save(tour);
        model.addAttribute("message", "Tour updated successfully");
        return "redirect:/tour";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        tourService.remove(id);
        redirect.addFlashAttribute("message", "Tour deleted successfully");
        return "redirect:/tour";
    }
}

