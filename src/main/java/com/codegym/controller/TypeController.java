package com.codegym.controller;

import com.codegym.model.Type;
import com.codegym.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @GetMapping("/type")
    public String listTypes(Model model) {
        Iterable<Type> types = typeService.findAll();
        model.addAttribute("types", types);
        return "type/listtype";
    }

    @GetMapping("/type/create")
    public String showCreateTypeForm(Model model) {
        model.addAttribute("type", new Type());
        return "type/createtype";
    }

    @PostMapping("/type/create")
    public String createType(@Valid @ModelAttribute("type") Type type, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "type/createtype";
        }
        typeService.save(type);
        redirect.addFlashAttribute("message", "Type created successfully");
        return "redirect:/type";
    }

    @GetMapping("/type/update/{id}")
    public String showUpdateTypeForm(@PathVariable Long id, Model model) {
        return typeService.findById(id)
                .map(type -> {
                    model.addAttribute("type", type);
                    return "type/updatetype";
                }).orElse("redirect:/error_404");
    }

    @PostMapping("/type/update/{id}")
    public String updateType(@PathVariable Long id, @Valid @ModelAttribute("type") Type type, BindingResult bindingResult, RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "type/updatetype";
        }
        type.setId(id);
        typeService.save(type);
        redirect.addFlashAttribute("message", "Type updated successfully");
        return "redirect:/type";
    }

    @GetMapping("/type/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        typeService.remove(id);
        redirect.addFlashAttribute("message", "Tour deleted successfully");
        return "redirect:/type";
    }
}
