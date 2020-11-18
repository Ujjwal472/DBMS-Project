package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.MaterialService;
import com.example.SpringProject.Services.PartService;
import com.example.SpringProject.Services.RequirementService;
import com.example.SpringProject.models.Part;
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

import java.util.List;

@Controller
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @Autowired
    RequirementService requirementService;

    @Autowired
    PartService partService;

    @GetMapping("/rawMaterials")
    public ModelAndView listMaterials() {
        ModelAndView mv = new ModelAndView("listMaterials");
        List<RawMaterial> all_materials = materialService.getAllMaterials();
        mv.addObject("all_materials", all_materials);
        System.out.println("Some error in listMaterials view");
        return mv;
    }

    @GetMapping("/addRawMaterial")
    public ModelAndView addMaterialForm() {
        ModelAndView mv = new ModelAndView("addRawMaterialForm");
        mv.addObject("material", new RawMaterial());
        return mv;
    }

    @PostMapping("/addRawMaterial")
    public ModelAndView addMaterial(@ModelAttribute(name = "material") RawMaterial material, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        material.setMaterialName(material.getMaterialName().toLowerCase());
        material.setType(material.getType().toLowerCase());
        if (materialService.checkByMaterialAndType(material.getMaterialName(), material.getType())) {
            redirectAttributes.addFlashAttribute("error", "Similar Entry corresponding to this type of material already exists (You may either update that entry or delete it to enter a new one)");
            mv.setViewName("redirect:/addRawMaterial");
        } else {
            materialService.saveMaterial(material);
            mv.setViewName("redirect:/rawMaterials");
        }
        return mv;
    }

    @GetMapping("/decrementMaterialAvailable/{id}")
    public ModelAndView decrementAvailable(@PathVariable(name = "id") int material_id) {
        ModelAndView mv = new ModelAndView("redirect:/rawMaterials");
        RawMaterial material = materialService.getMaterialById(material_id);
        int cnt = material.getTotal_available();
        if (cnt > 0) material.setTotal_available(cnt - 1);
        materialService.saveMaterial(material);
        return mv;
    }

    @GetMapping("/incrementMaterialAvailable/{id}")
    public ModelAndView incrementAvailable(@PathVariable(name = "id") int material_id) {
        ModelAndView mv = new ModelAndView("redirect:/rawMaterials");
        RawMaterial material = materialService.getMaterialById(material_id);
        int cnt = material.getTotal_available();
        System.out.println(cnt);
        material.setTotal_available(cnt + 1);
        materialService.saveMaterial(material);
        return mv;
    }

    @PostMapping("/addUpdatedMaterial")
    public ModelAndView addUpdated(@ModelAttribute(name = "material") RawMaterial material, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        if (materialService.checkByMaterialAndType(material.getMaterialName(), material.getType())) {
            RawMaterial material1 = materialService.getByMaterialAndType(material.getMaterialName(), material.getType());
            if (material1.getMaterial_id() != material.getMaterial_id()) {
                redirectAttributes.addFlashAttribute("error", "Similar Entry corresponding to this type of material already exists (You may either update that entry or delete it to enter a new one)");
                redirectAttributes.addAttribute("material_id", material.getMaterial_id());
                mv.setViewName("redirect:/materialUpdateForm/{material_id}");
                return mv;
            }
        }
        if (material.getCost_per_unit() < 0) {
            redirectAttributes.addFlashAttribute("error", "please ensure that is cost is non negative");
            mv.setViewName("redirect:/materialUpdateForm/{material_id}");
            return mv;
        }
        mv.setViewName("redirect:/rawMaterials");
        RawMaterial old_material = materialService.getMaterialById(material.getMaterial_id());
        material.setRequired(old_material.getRequired());
        if (material.getCost_per_unit() != old_material.getCost_per_unit()) {
            // update all the dependencies
            List<Requirement> all_requirements = old_material.getRequired();
            for (Requirement requirement : all_requirements) {
                Part part = requirement.getPart();
                double cost = part.getTotal_material_cost();
                double req = requirementService.getByPartAndMaterial(part, old_material).getUnits_required();
                cost -= old_material.getCost_per_unit() * req;
                cost += material.getCost_per_unit() * req;
                part.setTotal_material_cost(cost);
                partService.savePart(part);
            }
        }
        materialService.saveMaterial(material);
        return mv;
    }

    @GetMapping("/materialUpdateForm/{id}")
    public ModelAndView updateMaterial(@PathVariable(name = "id") int material_id) {
        ModelAndView mv = new ModelAndView("materialUpdateForm");
        RawMaterial material = materialService.getMaterialById(material_id);
        mv.addObject("material", material);
        return mv;
    }

    @GetMapping("/deleteMaterial/{id}")
    public ModelAndView deleteRawMaterial(@PathVariable(name = "id") int material_id) {
        ModelAndView mv = new ModelAndView("redirect:/rawMaterials");
        materialService.deleteMaterialById(material_id);
        return mv;
    }

}
