package com.tth_module4.controller;


import com.tth_module4.model.Branch;
import com.tth_module4.model.Staff;
import com.tth_module4.service.BranchService;
import com.tth_module4.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private BranchService branchService;


    @ModelAttribute(name = "branch")
    public List<Branch> branches() {
        return branchService.getAll();
    }

    @ModelAttribute(name = "staff")
    public Staff staff() {
        return new Staff();
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("staffs", staffService.getAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public String showCreate() {
        return "create";
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute("staff") Staff staff, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/create");
            return modelAndView;
        }
        staffService.save(staff);
        ModelAndView modelAndView = new ModelAndView("redirect:/staff");
        return modelAndView;
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewStaff(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("staffView", staffService.findById(id));
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("staffEdit", staffService.findById(id));
        return "/edit";
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("staffEdit") Staff staffEdit, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("redirect:/edit");
            staffService.save(staffEdit);
            return modelAndView;
        }
        staffService.save(staffEdit);
        ModelAndView modelAndView = new ModelAndView("redirect:/staff");
        return modelAndView;
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("staffDelete", staffService.findById(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("idStaff") Long idStaff, RedirectAttributes redirect) {

        staffService.delete(idStaff);
        redirect.addFlashAttribute("success", "");
        return "redirect:/staff";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Staff> staffs = staffService.findByStaffNameContaining(search);
        model.addAttribute("staffs", staffs);
        return "index";
    }
}
