package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.profile.TopUpBalanceDTO;
import com.trollMarket.TrollMarket.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractRestController{

    @Autowired
    private ProfileService service ;

    @GetMapping("/index")
    public  String buyerMenu(Principal principal,
                             @RequestParam(defaultValue = "1") Integer page,
                             Model model){
        var username = principal.getName();
        var detail = service.getDetails(username);
        var rows = service.getRowTransaction(detail.getId(),username,page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("detail",detail);
        return "profile/profile-index";
    }
    @PostMapping("/topUp")
    public ResponseEntity<Object> topUp(@Valid @RequestBody TopUpBalanceDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            service.topUpBalance(dto);
            return ResponseEntity.status(200).body("updated");
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }
}
