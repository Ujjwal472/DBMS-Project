package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.PartService;
import com.example.SpringProject.Services.RequirementService;
import com.example.SpringProject.Services.ToolService;
import com.example.SpringProject.models.Part;
import com.example.SpringProject.models.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PartController {

    @Autowired
    PartService partService;

    @Autowired
    ToolService toolService;

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

    @GetMapping("/partsUsedIn/{tool_id}")
    public ModelAndView partDependencies(@PathVariable(name = "tool_id") int tool_id) {
        ModelAndView mv = new ModelAndView("listPartDependencies");
        List<Part> all_parts = toolService.getToolById(tool_id).getUsed_in();
        List<Part> av_parts = partService.getAllPart();
        Part part = new Part();
        mv.addObject("all_parts", all_parts);
        mv.addObject("available_parts", av_parts);
        mv.addObject("part", part);
        mv.addObject("tool", toolService.getToolById(tool_id));
        return mv;
    }

    @PostMapping("/addPartDependency/{id}")
    public ModelAndView addPartDependency(@PathVariable(name = "id") int tool_id, @ModelAttribute(name = "part") Part part, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("tool_id", tool_id);
        ModelAndView mv = new ModelAndView("redirect:/partsUsedIn/{tool_id}");
        Tool tool = toolService.getToolById(tool_id);
        Part new_part = partService.getPartById(part.getPart_id());

        // add inconsistency check later

        tool.getUsed_in().add(new_part);
        new_part.getTools_used().add(tool);

        toolService.saveTool(tool);
        partService.savePart(new_part);

        return mv;
    }

    @GetMapping("/deletePartDependency/{part_id}/{tool_id}")
    public ModelAndView deletePartDependency(@PathVariable(name = "part_id") int part_id, @PathVariable(name = "tool_id") int tool_id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("tool_id", tool_id);
        ModelAndView mv = new ModelAndView("redirect:/partsUsedIn/{tool_id}");
        Part part = partService.getPartById(part_id);
        Tool tool = toolService.getToolById(tool_id);

        part.getTools_used().remove(tool);
        tool.getUsed_in().remove(part);

        partService.savePart(part);
        toolService.saveTool(tool);
        return mv;
    }
}
