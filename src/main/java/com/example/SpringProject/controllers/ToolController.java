package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.ToolService;
import com.example.SpringProject.models.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ToolController {

    @Autowired
    ToolService toolService;

    @GetMapping("/tools")
    public ModelAndView toolsList() {
        ModelAndView mv = new ModelAndView("listTools");
        List<Tool> all_tools = toolService.getAllTools();
        mv.addObject("all_tools", all_tools);
        return mv;
    }

    @GetMapping("/addTool")
    public ModelAndView addToolForm() {
        ModelAndView mv = new ModelAndView("addToolForm");
        mv.addObject("tool", new Tool());
        return mv;
    }

    @PostMapping("/addTool")
    public ModelAndView addTool(@ModelAttribute(name = "tool") Tool tool) {
        ModelAndView mv = new ModelAndView("redirect:/tools");
        toolService.saveTool(tool);
        return mv;
    }

    @GetMapping("/toolUpdateForm/{id}")
    public ModelAndView updateTool(@PathVariable(name = "id") int tool_id) {
        ModelAndView mv = new ModelAndView("toolUpdateForm");
        Tool tool = toolService.getToolById(tool_id);
        mv.addObject("tool", tool);
        return mv;
    }

    @GetMapping("/incrementToolAvailable/{id}")
    public ModelAndView incrementAv(@PathVariable(name = "id") int tool_id) {
        ModelAndView mv = new ModelAndView("redirect:/tools");
        Tool tool = toolService.getToolById(tool_id);
        tool.setTotal_available(tool.getTotal_available() + 1);
        toolService.saveTool(tool);
        return mv;
    }

    @GetMapping("/decrementToolAvailable/{id}")
    public ModelAndView decrementAv(@PathVariable(name = "id") int tool_id) {
        ModelAndView mv = new ModelAndView("redirect:/tools");
        Tool tool = toolService.getToolById(tool_id);
        if (tool.getTotal_available() > 0) tool.setTotal_available(tool.getTotal_available() - 1);
        toolService.saveTool(tool);
        return mv;
    }

    @GetMapping("/incrementToolDefective/{id}")
    public ModelAndView incrementDe(@PathVariable(name = "id") int tool_id) {
        ModelAndView mv = new ModelAndView("redirect:/tools");
        Tool tool = toolService.getToolById(tool_id);
        tool.setTotal_defective(tool.getTotal_defective() + 1);
        toolService.saveTool(tool);
        return mv;
    }

    @GetMapping("/decrementToolDefective/{id}")
    public ModelAndView decrementDe(@PathVariable(name = "id") int tool_id) {
        ModelAndView mv = new ModelAndView("redirect:/tools");
        Tool tool = toolService.getToolById(tool_id);
        if (tool.getTotal_defective() > 0) tool.setTotal_defective(tool.getTotal_defective() - 1);
        toolService.saveTool(tool);
        return mv;
    }
    @GetMapping("/deleteTool/{id}")
    public ModelAndView deleteTool(@PathVariable(name = "id") int tool_id) {
        ModelAndView mv = new ModelAndView("redirect:/tools");
        toolService.deleteToolById(tool_id);
        return mv;
    }
}
