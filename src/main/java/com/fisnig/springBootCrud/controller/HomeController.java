package com.fisnig.springBootCrud.controller;

import com.fisnig.springBootCrud.model.Customer;

import com.fisnig.springBootCrud.service.CustomerService;
import com.fisnig.springBootCrud.service.FileUploadService;
import com.fisnig.springBootCrud.SpringBootCrudApplication;


import jakarta.validation.Valid;

import org.springframework.data.repository.query.Param;
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
public class HomeController {

    private CustomerService customerService;
    private CustomerService courseService;
   
    public HomeController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("registration")
    public String home(@RequestParam(value = "name", defaultValue = "") String name, Model model) {

        List<Customer> customerList = customerService.getAllCustomers();
       
        model.addAttribute("customerList", customerList);
        
        return "home";
    }
    @GetMapping("/java")
	public String java() {
		return "java";
	}
    @GetMapping("/")
	public String home() {
		return "index";
	}
    @GetMapping("dashboard")
	public String homenew() {
		return "index";
	}
    @GetMapping("phpinfo")
	public String homephp() {
		return "php";
	}
    
    
    @GetMapping("create")
    public String create(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "create";
    }

    @PostMapping("save")
    public String save(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if(bindingResult.hasErrors()) {
            return "/create";
        }

        if(!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            customer.setPhoto(fileName);

            Customer savedCustomer = customerService.save(customer);

            String uploadDir = "customer-photos/"+savedCustomer.getId();
            FileUploadService.saveFile(uploadDir, fileName, multipartFile);

        }




//

        redirectAttributes.addFlashAttribute("message", "Student saved successfully");
        return "redirect:/registration";
    }

    @GetMapping("/customer/{id}")
    public String customer(@PathVariable Long id, Model model) {
        Optional<Customer> customer = customerService.findById(id);

        customer.ifPresent(value -> model.addAttribute("customer", value));

        return "show";
    }

    @GetMapping("customer/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        Customer customer = customerService.findById(id).orElse(null);
        model.addAttribute("customer", customer);
        return "create";
    }

    @GetMapping("customer/{id}/delete")
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        customerService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully");
        return "redirect:/registration";
    }
    
    @RequestMapping("/search")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Customer> customerList = customerService.listAll(keyword);
        model.addAttribute("customerList", customerList);
        model.addAttribute("keyword", keyword);
         
        return "home";
    }
}
