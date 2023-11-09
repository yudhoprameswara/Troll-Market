package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dto.cart.CartRowDTO;
import com.trollMarket.TrollMarket.dto.history.HistoryRowDTO;
import com.trollMarket.TrollMarket.repository.BuyerRepository;
import com.trollMarket.TrollMarket.repository.ProductRepository;
import com.trollMarket.TrollMarket.repository.PurchaseRepository;
import com.trollMarket.TrollMarket.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public Page<CartRowDTO> getRows(String username,Integer page){
        var buyerId = buyerRepository.getBuyerIdByUsername(username);
        var pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
        var rows = purchaseRepository.getCartRow(buyerId,pageable);
        return rows;
    }

    public void delete(Long id){
        purchaseRepository.deleteById(id);
    }

    public boolean purchaseAll(String username) {
        var buyerId = buyerRepository.getBuyerIdByUsername(username);
        var cartList = purchaseRepository.getCartList(buyerId);
        var totalPrice = purchaseRepository.totalPrice(buyerId);
        var buyer = buyerRepository.findById(buyerId).get();


        var saldo = buyer.getBalance() - totalPrice;
        if (saldo >= 0) {
            buyer.setBalance(saldo);
            buyerRepository.save(buyer); // buyer Balance Updated

            for (var cart : cartList){
                var purchaseId =cart.getId();
                var purchase = purchaseRepository.findById(purchaseId).get();
                purchase.setDate(LocalDateTime.now()); // date updated

                var product = productRepository.findById(cart.getProductId()).get();
                var sellerId = product.getSellerId();

                var seller = sellerRepository.findById(sellerId).get();
                seller.setBalance(seller.getBalance()+cart.getTotalPrice());

                purchaseRepository.save(purchase);
                productRepository.save(product);
                sellerRepository.save(seller);
            }
            return true;
        }
        else{
            return false;
        }
    }






//
//            for(var cart : cartList){
//                var sellerId = cart.getSellerId();
//                var seller = sellerRepository.findById(sellerId).get();
//                var id= cart.getId();
//
//                var entity =purchaseRepository.findById(id).get();
//                entity.setDate(LocalDateTime.now());
//                purchaseRepository.save(entity);
//
//                double sellerIncome= 0;
//                for(var cartSeller : purchaseRepository.getCartBySeller(buyerId,sellerId)){
//                    sellerIncome += cartSeller.getTotalPrice();
//                }
//                seller.setBalance(seller.getBalance()+sellerIncome);
//                sellerRepository.save(seller);


//           seller.setBalance(seller.getBalance()+cart.getTotalPrice());//           var sellerId = cart.getSellerId();
////           var seller = sellerRepository.findById(sellerId).get();
////           seller.setBalance(seller.getBalance()+cart.getTotalPrice());


}
