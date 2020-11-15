package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.PartService;
import com.example.SpringProject.Services.ToolService;
import com.example.SpringProject.models.Part;
import com.example.SpringProject.models.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
public class ToolController {

    @Autowired
    ToolService toolService;

    @Autowired
    PartService partService;

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
    public ModelAndView addTool(@ModelAttribute(name = "tool") Tool tool, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        tool.setToolName(tool.getToolName().toLowerCase());
        if (toolService.checkByToolName(tool.getToolName())) {
            redirectAttributes.addFlashAttribute("error", "The tool already exists!");
            mv.setViewName("redirect:/addTool");
        } else if (tool.getTotal_defective() > tool.getTotal_available()) {
            redirectAttributes.addFlashAttribute("error", "Defective count cannot exceed total available count!");
            mv.setViewName("redirect:/addTool");
        } else {
            toolService.saveTool(tool);
            mv.setViewName("redirect:/tools");
        }
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
    public ModelAndView incrementDe(@PathVariable(name = "id") int tool_id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView();
        Tool tool = toolService.getToolById(tool_id);
        tool.setToolName(tool.getToolName().toLowerCase());
        if (toolService.checkByToolName(tool.getToolName())) {
            redirectAttributes.addFlashAttribute("error", "Defective tools cannot exceed available tools!");
            mv.setViewName("redirect:/addTool");
        } else{
            tool.setTotal_defective(tool.getTotal_defective() + 1);
            toolService.saveTool(tool);
            mv.setViewName("redirect:/tools");
        }
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

    @GetMapping("/toolsUsed/{id}")
    public ModelAndView toolsUsed(@PathVariable(name = "id") int part_id) {
        ModelAndView mv = new ModelAndView("listToolRequirements");
        List<Tool> all_tools = partService.getPartById(part_id).getTools_used();
        List<Tool> av_tools = toolService.getAllTools();
        Tool tool = new Tool();
        mv.addObject("tool", tool);
        mv.addObject("all_tools", all_tools);
        mv.addObject("available_tools", av_tools);
        mv.addObject("part", partService.getPartById(part_id));
        return mv;
    }

    @PostMapping("/addToolRequirement/{id}")
    public ModelAndView addToolRequirement(@PathVariable(name = "id") int part_id, @ModelAttribute(name = "tool") Tool tool
    , RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("part_id", part_id);
        ModelAndView mv = new ModelAndView("redirect:/toolsUsed/{part_id}");
        Tool new_tool = toolService.getToolById(tool.getTool_id());
        Part part = partService.getPartById(part_id);

        // add a inconsistency check later

        new_tool.getUsed_in().add(part);
        part.getTools_used().add(new_tool);

        toolService.saveTool(new_tool);
        partService.savePart(part);

        return mv;
    }

    @GetMapping("/deleteToolRequirement/{part_id}/{tool_id}")
    public ModelAndView deleteToolRequirement(@PathVariable(name = "part_id") int part_id, @PathVariable(name = "tool_id") int tool_id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("part_id", part_id);
        ModelAndView mv = new ModelAndView("redirect:/toolsUsed/{part_id}");
        Part part = partService.getPartById(part_id);
        Tool tool = toolService.getToolById(tool_id);
        part.getTools_used().remove(tool);
        tool.getUsed_in().remove(part);
        partService.savePart(part);
        toolService.saveTool(tool);
        return mv;
    }
}
