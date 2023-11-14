package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.profile.TopUpBalanceDTO;
import com.trollMarket.TrollMarket.dto.shop.AddToCartDTO;
import com.trollMarket.TrollMarket.service.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/shop")
public class ShopController extends AbstractRestController {

    @Autowired
    private ShopService service;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "") String name,
                        @RequestParam(defaultValue = "") String category,
                        @RequestParam(defaultValue = "") String description,
                        @RequestParam(defaultValue = "1") Integer page,
                        Model model){

        var rows = service.getRows(name, category, description, page);
        var shipperDropdown = service.getShipperDropdown();
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("shipperDropdown",shipperDropdown);
        return "shop-updated/shop-updated-index";
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> getProductDetail(@PathVariable Long id){
        var dto = service.getProductDetail(id);
        return ResponseEntity.status(200).body(dto);
    }
    @PostMapping("/addToCart")
    public ResponseEntity<Object> addToCart (@Valid  @RequestBody AddToCartDTO dto , BindingResult bindingResult ,Principal principal){
        if(!bindingResult.hasErrors()) {
            var username = principal.getName();
            service.addToCart(username, dto);
            return ResponseEntity.status(201).body("updated");
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }
}
