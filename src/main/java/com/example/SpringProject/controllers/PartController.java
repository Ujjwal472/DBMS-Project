package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.PartService;
import com.example.SpringProject.Services.RequirementService;
import com.example.SpringProject.models.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PartController {

    @Autowired
    PartService partService;

    @Autowired
    RequirementService requirementService;

    @GetMapping("/parts")
    public ModelAndView partList() {
        ModelAndView mv = new ModelAndView("listParts");
        List<Part> all_parts = partService.getAllPart();
        mv.addObject("all_parts", all_parts);
        return mv;
    }

    @GetMapping("/addPart")
    public ModelAndView addPartForm() {
        ModelAndView mv = new ModelAndView("addPartForm");
        Part part = new Part();
        part.setTotal_material_cost(0);
        mv.addObject("part", part);
        return mv;
    }

    @PostMapping("/addPart")
    public ModelAndView addPart(@ModelAttribute(name = "part") Part part) {
        ModelAndView mv = new ModelAndView("redirect:/parts");
        partService.savePart(part);
        return mv;
    }

    @GetMapping("/incrementPartAvailable/{id}")
    public ModelAndView incrementAv(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("redirect:/parts");
        Part part = partService.getPartById(part_id);
        part.setTotal_available(part.getTotal_available() + 1);
        partService.savePart(part);
        return mv;
    }

    @GetMapping("/decrementPartAvailable/{id}")
    public ModelAndView decrementAv(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("redirect:/parts");
        Part part = partService.getPartById(part_id);
        if (part.getTotal_available() > 0) part.setTotal_available(part.getTotal_available() - 1);
        partService.savePart(part);
        return mv;
    }
    @GetMapping("/incrementPartDefective/{id}")
    public ModelAndView incrementDef(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("redirect:/parts");
        Part part = partService.getPartById(part_id);
        part.setTotal_defective(part.getTotal_defective() + 1);
        partService.savePart(part);
        return mv;
    }

    @GetMapping("/decrementPartDefective/{id}")
    public ModelAndView decrementDef(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("redirect:/parts");
        Part part = partService.getPartById(part_id);
        if (part.getTotal_defective() > 0) part.setTotal_defective(part.getTotal_defective() - 1);
        partService.savePart(part);
        return mv;
    }

    @GetMapping("/partUpdateForm/{id}")
    public ModelAndView partUpdateForm(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("partUpdateForm");
        Part part = partService.getPartById(part_id);
        mv.addObject("part", part);
        return mv;
    }

    @GetMapping("/deletePart/{id}")
    public ModelAndView deletePart(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("redirect:/parts");
        partService.deletePartById(part_id);
        return mv;
    }
}
