package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("/index")
    public String cartIndex(@RequestParam(defaultValue = "1") Integer page, Principal principal,
                              Model model){
        var username = principal.getName();
        var rows = service.getRows(username,page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("saldoError", true);
        return "cart/cart-index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model){
        service.delete(id);
        return "redirect:/cart/index";
    }

    @GetMapping("/purchase")
    public String purchaseAll(@RequestParam(defaultValue = "1") Integer page,Principal principal, Model model){
        var username = principal.getName();
        boolean purchaseSuccess = service.purchaseAll(username);

        if(!purchaseSuccess){
            var rows = service.getRows(username,page);
            model.addAttribute("grid", rows);
            model.addAttribute("totalPages", rows.getTotalPages());
            model.addAttribute("currentPage", page);
            model.addAttribute("saldoError", false);
            return "cart/cart-index";
        }
        else {
            return "redirect:/cart/index";
        }
    }
}
