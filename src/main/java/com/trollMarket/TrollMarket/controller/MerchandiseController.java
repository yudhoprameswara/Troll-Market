package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.product.UpsertProductDTO;
import com.trollMarket.TrollMarket.service.MerchandiseService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/merchandise")
public class MerchandiseController {

    @Autowired
    private MerchandiseService service;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        Model model,Principal principal){
        var username = principal.getName();
        var rows = service.getRows(username,page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "merchandise/merchandise-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Long id, Model model, Principal principal){
        var dependencies = service.getCountProductByTransaction(id);
        if(dependencies == 0){
        var dto = new UpsertProductDTO();
        if(id != null){

            dto = service.findOne(id);
        }
        var username = principal.getName();
        var sellerId = service.getSellerId(username);
        model.addAttribute("sellerId",sellerId);
        model.addAttribute("dto",dto);
        return "merchandise/product-form";
        }
        model.addAttribute("dependencies",dependencies);
        return "merchandise/product-edit-message";
    }
    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("dto") UpsertProductDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        if(!bindingResult.hasErrors()){
            service.save(dto);
            redirectAttributes.addAttribute("username",service.getUsernameBySellerId(dto.getSellerId()));
            return "redirect:/merchandise/index";
        }
        return  "merchandise/product-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model,RedirectAttributes redirectAttributes,Principal principal){
        var dependencies = service.getCountProductByTransaction(id);
        if(dependencies == 0){
            service.delete(id);
            redirectAttributes.addAttribute("username",principal.getName());
            return "redirect:/merchandise/index";
        }
        model.addAttribute("dependencies", dependencies);
        return "merchandise/product-edit-message";
    }

    @GetMapping("/discontinue")
    public String discontinue(@RequestParam Long id,RedirectAttributes redirectAttributes,Principal principal){
        service.discontinueButton(id);
        redirectAttributes.addAttribute("username",principal.getName());
        return "redirect:/merchandise/index";
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> getDetailProduct(@PathVariable Long id){
        var dto = service.findOne(id);
        return ResponseEntity.status(200).body(dto);
    }
}
