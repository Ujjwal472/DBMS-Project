package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.MaterialService;
import com.example.SpringProject.Services.PartService;
import com.example.SpringProject.Services.RequirementService;
import com.example.SpringProject.models.Part;
import com.example.SpringProject.models.Product;
import com.example.SpringProject.models.RawMaterial;
import com.example.SpringProject.models.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RequirementController {

    @Autowired
    RequirementService requirementService;

    @Autowired
    PartService partService;

    @Autowired
    MaterialService materialService;

    @GetMapping("/requirements/{id}")
    public ModelAndView listRequirements(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("listRequirements");
        Part part = partService.getPartById(part_id);
        List<Requirement> all_requirements = part.getRequirements();
        mv.addObject("all_requirements", all_requirements);
        mv.addObject("part", part);
        List<Double> costs = new ArrayList<>();
        for (Requirement req: all_requirements) {
            costs.add(req.getRawMaterial().getCost_per_unit());
        }
        return mv;
    }

    @GetMapping("/addRequirement/{id}")
    public ModelAndView addRequirementForm(@PathVariable(name = "id") int part_id ) {
        ModelAndView mv = new ModelAndView("addRequirementForm");
        Requirement requirement = new Requirement();
        requirement.setPart(partService.getPartById(part_id));
        requirement.setRawMaterial(new RawMaterial());
        mv.addObject("requirement", requirement);
        mv.addObject("all_materials", materialService.getAllMaterials());
        return mv;
    }

    @PostMapping("/addRequirement")
    public ModelAndView addRequirement(@ModelAttribute(name = "requirement") Requirement requirement, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        Part part = partService.getPartById(requirement.getPart().getPart_id());
        requirement.setPart(part);
        requirement.setRawMaterial(materialService.getMaterialById(requirement.getRawMaterial().getMaterial_id()));
        redirectAttributes.addAttribute("part_id", part.getPart_id());
        if (requirementService.checkByRawMaterialAndPart(requirement.getRawMaterial(), requirement.getPart())) {
            redirectAttributes.addFlashAttribute("error", "dependency already exists for this part (either delete it and retry or simply update it)");
            mv.setViewName("redirect:/addRequirement/{part_id}");
        } else if (requirement.getUnits_required() == 0) {
            redirectAttributes.addFlashAttribute("error", "if the part is not required just omit it");
            mv.setViewName("redirect:/addRequirement/{part_id}");
        } else {
            mv.setViewName("redirect:/requirements/{part_id}");

            // while adding a new requirement the total material cost of the part may change so increment the
            // cost accordingly

            double cost = requirement.getPart().getTotal_material_cost();
            cost += requirement.getRawMaterial().getCost_per_unit() * requirement.getUnits_required();

            part.setTotal_material_cost(cost);
            partService.savePart(part);
            requirementService.saveRequirement(requirement);
        }
        return mv;
    }

    @PostMapping("/addUpdatedRequirement")
    public ModelAndView addUpdatedRequirement(@ModelAttribute(name = "requirement") Requirement requirement, RedirectAttributes redirectAttributes) {
        System.out.println("requirement id = " + requirement.getRequirement_id());
        Requirement old_requirement = requirementService.getRequirementById(requirement.getRequirement_id());
        Part part = old_requirement.getPart();
        RawMaterial material = old_requirement.getRawMaterial();
        requirement.setPart(part);
        requirement.setRawMaterial(material);
        redirectAttributes.addAttribute("part_id", part.getPart_id());
        ModelAndView mv = new ModelAndView("redirect:/requirements/{part_id}");
        double cost = part.getTotal_material_cost();
        cost -= requirementService.getRequirementById(requirement.getRequirement_id()).getUnits_required() * material.getCost_per_unit();
        cost += requirement.getUnits_required() * material.getCost_per_unit();
        part.setTotal_material_cost(cost);
        partService.savePart(part);
        requirementService.saveRequirement(requirement);
        return mv;
    }

    @GetMapping("/requirementUpdateForm/{id}")
    public ModelAndView updateRequirement(@PathVariable(name = "id") int requirement_id) {
        ModelAndView mv = new ModelAndView("requirementUpdateForm");
        mv.addObject("requirement", requirementService.getRequirementById(requirement_id));
        return mv;
    }

    @GetMapping("/deleteRequirement/{id}")
    public ModelAndView deleteRequirement(@PathVariable(name = "id") int requirement_id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("part_id", requirementService.getRequirementById(requirement_id).getPart().getPart_id());
        ModelAndView mv = new ModelAndView("redirect:/requirements/{part_id}");
        Requirement requirement = requirementService.getRequirementById(requirement_id);
        double cost = requirement.getPart().getTotal_material_cost();
        cost -= requirement.getUnits_required() * requirement.getRawMaterial().getCost_per_unit();
        Part part = requirement.getPart();
        part.setTotal_material_cost(cost);
        partService.savePart(part);
        requirementService.deleteRequirementById(requirement_id);
        return mv;
    }

}
