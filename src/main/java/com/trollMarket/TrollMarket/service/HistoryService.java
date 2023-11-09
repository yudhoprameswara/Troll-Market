package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dto.history.HistoryRowDTO;
import com.trollMarket.TrollMarket.dto.product.ProductRowDTO;
import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.repository.BuyerRepository;
import com.trollMarket.TrollMarket.repository.PurchaseRepository;
import com.trollMarket.TrollMarket.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public Page<HistoryRowDTO> getRows(Long sellerId,Long buyerId,Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("date"));
        var rows = purchaseRepository.getHistoryRow(sellerId,buyerId,pageable);
        return rows;
    }

    public List<DropdownDTO> getBuyerDropdown(){
        return buyerRepository.buyerListDropdown();
    }

    public List<DropdownDTO> getSellerDropdown(){
        return sellerRepository.sellerListDropdown();
    }
}


