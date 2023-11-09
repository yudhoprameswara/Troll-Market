package com.trollMarket.TrollMarket.controller;

import com.trollMarket.TrollMarket.dto.shipper.UpsertShipperDTO;
import com.trollMarket.TrollMarket.service.ShipperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/shipper")
public class ShipperController extends AbstractRestController {

    @Autowired
    private ShipperService service;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page, Model model){

        var rows = service.getRows(page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        return "shipper/shipper-index";
    }

    @PostMapping("/upsert")
    public ResponseEntity<Object> post(@Valid @RequestBody UpsertShipperDTO dto, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            var response = service.save(dto);
            return ResponseEntity.status(201).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @GetMapping("/stopService")
    public String stopService(@RequestParam Long id){
        service.stopService(id);
        return "redirect:/shipper/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model){
        var dependencies = service.countPurchaseByShipper(id);
        if(dependencies == 0){
            service.delete(id);
            return "redirect:/shipper/index";
        }
        model.addAttribute("dependencies", dependencies);
        return "shipper/shipper-edit-message";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id){
        var dto = service.findOne(id);
        return ResponseEntity.status(200).body(dto);
    }
}
