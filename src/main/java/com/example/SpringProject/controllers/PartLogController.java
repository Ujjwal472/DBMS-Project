package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.PartLogService;
import com.example.SpringProject.Services.PartService;
import com.example.SpringProject.models.Part;
import com.example.SpringProject.models.PartLog;
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
public class PartLogController {

    @Autowired
    PartLogService partLogService;

    @Autowired
    PartService partService;

    @GetMapping("/partLog/{id}")
    public ModelAndView listPartLog(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("listPartLog");
        List<PartLog> all_logs = partLogService.getPartLogsByPartId(part_id);
        mv.addObject("all_logs", all_logs);
        return mv;
    }

    @GetMapping("/addPartLog/{id}")
    public ModelAndView addPartLogForm(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("addPartLog");
        PartLog partLog = new PartLog();
        partLog.getPartLogKey().setPartId(part_id);
        mv.addObject("partLog", partLog);
        return mv;
    }

    @PostMapping("/addPartLog/{id}")
    public ModelAndView addPartLog(@PathVariable(name = "id") int part_id, RedirectAttributes redirectAttributes, @ModelAttribute(name = "partLog") PartLog partLog) {
        redirectAttributes.addAttribute("part_id", part_id);
        ModelAndView mv = new ModelAndView("redirect:/partLog/{part_id}");
        Part part = partService.getPartById(part_id);
        double cost = partLog.getTotal_produced() * part.getTotal_material_cost();
        partLog.setTotal_cost(cost);
        partLogService.savePartLog(partLog);
        return mv;
    }
}
