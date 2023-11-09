package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.account.RegisterDTO;
import com.trollMarket.TrollMarket.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService service;

    @GetMapping("/index")
    public String historyMenu(@RequestParam(required = false) Long buyerId,
                              @RequestParam(required = false) Long sellerId,
                              @RequestParam(defaultValue = "1") Integer page,
                              Model model){
        var rows = service.getRows(sellerId,buyerId,page);
        var buyerDropdown = service.getBuyerDropdown();
        var sellerDropdown = service.getSellerDropdown();
        model.addAttribute("grid", rows);
        model.addAttribute("buyerDropdown",buyerDropdown);
        model.addAttribute("sellerDropdown",sellerDropdown);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "history/history-index";
    }
}
