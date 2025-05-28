package com.fisnig.springBootCrud.controller;

import com.fisnig.springBootCrud.model.Staff;

import com.fisnig.springBootCrud.service.StaffService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class StaffController {

    private StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/staff")
    public String homestaff(@RequestParam(value = "namenew", defaultValue = "") String namenew, Model model) {

        List<Staff> staffList = staffService.getAllSatffs();

        model.addAttribute("staffList", staffList);
        return "homestaff";
    }
    
    
    

    @GetMapping("createstaff")
    public String createstaff(Model model) {
        Staff staff = new Staff();
        model.addAttribute("staff", staff);
        return "createstaff";
    }
   
	
 

    @GetMapping("/staff/{id}")
    public String staff(@PathVariable Long id, Model model) {
        Optional<Staff> staff = staffService.findById(id);

        staff.ifPresent(value -> model.addAttribute("staff", value));

        return "show";
    }

    @GetMapping("staff/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        Staff staff = staffService.findById(id).orElse(null);
        model.addAttribute("staff", staff);
        return "create";
    }

    @GetMapping("staff/{id}/delete")
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        staffService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Staff deleted successfully");
        return "redirect:/";
    }

}
