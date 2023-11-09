package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.account.AdminRegisterDTO;
import com.trollMarket.TrollMarket.dto.account.RegisterDTO;
import com.trollMarket.TrollMarket.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AccountService service;

    @GetMapping("/index")
    public String adminMenu(Model model){
        var dto = new RegisterDTO();
        model.addAttribute("dto",dto);
        return "admin/admin-index";
    }

    @PostMapping("/register")
    public String registerAdmin(@Valid @ModelAttribute("dto") AdminRegisterDTO dto,
                                BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "admin/admin-index";
        }
        service.registerAdmin(dto);
        return "redirect:/admin/index";
    }
}
