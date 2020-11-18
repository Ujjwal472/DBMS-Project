package com.example.SpringProject.controllers;

import com.example.SpringProject.Services.DateService;
import com.example.SpringProject.Services.EmployeeService;
import com.example.SpringProject.Services.PartService;
import com.example.SpringProject.Services.WorksService;
import com.example.SpringProject.models.Employee;
import com.example.SpringProject.models.Part;
import com.example.SpringProject.models.Works;
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
public class WorksController {

    @Autowired
    WorksService worksService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PartService partService;

    @Autowired
    DateService dateService;

    @GetMapping("/works/{id}")
    public ModelAndView ListWorks(@PathVariable(name = "id") int employee_id) {
        ModelAndView mv = new ModelAndView("listWorks");
        Employee employee = employeeService.getEmployeeById(employee_id);
        List<Works> all_works = worksService.getWorksByEmployee(employee);
        mv.addObject("all_works", all_works);
        mv.addObject("employee", employee);
        return mv;
    }

    @GetMapping("/addWorks/{id}")
    public ModelAndView addWorkForm(@PathVariable(name = "id") int employee_id) {
        ModelAndView mv = new ModelAndView("addWorkForm");
        Employee employee = employeeService.getEmployeeById(employee_id);
        Works work = new Works();
        work.setEmployee(employee);
        work.setPart(new Part());
        mv.addObject("work", work);
        mv.addObject("all_parts", partService.getAllPart());
        return mv;
    }

    @PostMapping("/addWorks")
    public ModelAndView addWork(@ModelAttribute(name = "work") Works work, RedirectAttributes redirectAttributes) {
        work.setEmployee(employeeService.getEmployeeById(work.getEmployee().getEmployee_id()));
        work.setPart(partService.getPartById(work.getPart().getPart_id()));
        redirectAttributes.addAttribute("employee_id", work.getEmployee().getEmployee_id());
        redirectAttributes.addAttribute("works_id", work.getWorksId());
        ModelAndView mv = new ModelAndView();
        if (!dateService.isValid(work.getDay(), work.getMonth(), work.getYear())) {
            redirectAttributes.addFlashAttribute("error", "please enter a valid date");
            if (work.getWorksId() == 0) mv.setViewName("redirect:/addWorks/{employee_id}");
            else mv.setViewName("redirect:/updateWork/{works_id}");
            return mv;
        }
        if (work.getWorksId() == 0) {
            // adding first time
            if (worksService.checkByDate(work.getEmployee(), work.getPart(), work.getDay(), work.getMonth(), work.getYear())) {
                redirectAttributes.addFlashAttribute("error", "work corresponding to this day aleady exists, update it");
                mv.setViewName("redirect:/addWorks/{employee_id}");
                return mv;
            }
        } else {
            // update
            if (worksService.checkByDate(work.getEmployee(), work.getPart(), work.getDay(), work.getMonth(), work.getYear())) {
                Works work1 = worksService.getByDate(work.getEmployee(), work.getPart(), work.getDay(), work.getMonth(), work.getYear());
                if (work1.getWorksId() == work.getWorksId()) {
                    redirectAttributes.addFlashAttribute("error", "Work corresponding to this part and day already exists!");
                    mv.setViewName("redirect:/updateWork/{works_id}");
                    return mv;
                }
            }
        }
        mv.setViewName("redirect:/works/{employee_id}");
        worksService.savework(work);
        return mv;
    }

    @GetMapping("/updateWork/{id}")
    public ModelAndView updateWork(@PathVariable(name = "id") int works_id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("worksUpdateForm");
        Works work = worksService.getWorkById(works_id);
        mv.addObject("work", work);
        return mv;
    }

    @GetMapping("/deleteWork/{id}")
    public ModelAndView deleteWork(@PathVariable(name = "id") int works_id, RedirectAttributes redirectAttributes) {
        Works work = worksService.getWorkById(works_id);
        redirectAttributes.addAttribute("employee_id", work.getEmployee().getEmployee_id());
        ModelAndView mv = new ModelAndView("redirect:/works/{employee_id}");
        worksService.deleteByWork(work);
        return mv;
    }

}
