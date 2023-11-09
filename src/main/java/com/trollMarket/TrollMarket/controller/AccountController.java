package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.account.AdminRegisterDTO;
import com.trollMarket.TrollMarket.dto.account.RegisterDTO;
import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;


    @GetMapping("/loginForm")
    public String loginForm(Model model){
        return "account/login-form";
    }


    @GetMapping("/registerFormBuyer")
    public String registerFormBuyer(Model model){
        var dto = new RegisterDTO();
        model.addAttribute("role","Buyer");
        model.addAttribute("dto",dto);
        return "account/register-form";
    }

    @GetMapping("/registerFormSeller")
    public String registerFormSeller(Model model){
        var dto = new RegisterDTO();
        model.addAttribute("role","Seller");
        model.addAttribute("dto",dto);
        return "account/register-form";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("dto") RegisterDTO dto,
                           BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "account/register-form";
        }
        service.register(dto);
        return "redirect:/account/loginForm";
    }


    @GetMapping("/failLogin")
    public String failLogon(){
        return "account/fail-login";
    }

    @RequestMapping(value="/accessDenied",method = {RequestMethod.GET,RequestMethod.POST})
    public String accessDenied(){
        return "account/access-denied";
    }

}
