package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dto.product.ProductRowDTO;
import com.trollMarket.TrollMarket.dto.product.UpsertProductDTO;
import com.trollMarket.TrollMarket.dto.shipper.ShipperRowDTO;
import com.trollMarket.TrollMarket.entity.Product;
import com.trollMarket.TrollMarket.repository.AccountRepository;
import com.trollMarket.TrollMarket.repository.ProductRepository;
import com.trollMarket.TrollMarket.repository.PurchaseRepository;
import com.trollMarket.TrollMarket.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MerchandiseService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Page<ProductRowDTO> getRows(String username,Integer page){

        var sellerId = sellerRepository.getDetailSeller(username).getId();
        var pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
        var rows = productRepository.getProductRow(sellerId,pageable);
        return rows;
    }

    public Long getSellerId(String username){
        return sellerRepository.getDetailSeller(username).getId();
    }
    public String getUsernameBySellerId(Long id){
        return sellerRepository.findById(id).get().getUsername();
    }
    public UpsertProductDTO findOne(Long id){
        var entity = productRepository.findById(id).get();
        var dto = new UpsertProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getSellerId(),
                entity.getCategory(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getDiscontinue()
        );
        return dto;
    }

    public void save(UpsertProductDTO dto){
        var entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSellerId(dto.getSellerId());
        entity.setCategory(dto.getCategory());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setDiscontinue(dto.getDiscontinue());
        productRepository.save(entity);
    }

    public Long getCountProductByTransaction(Long productId){
        return purchaseRepository.countByProduct(productId);
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public void discontinueButton(Long id){
       var entity= productRepository.findById(id).get();
       entity.setDiscontinue(true);
       productRepository.save(entity);
    }
}
