package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dto.product.ProductDetailDTO;
import com.trollMarket.TrollMarket.dto.product.ProductRowDTO;
import com.trollMarket.TrollMarket.dto.product.ShopListProductDTO;
import com.trollMarket.TrollMarket.dto.shop.AddToCartDTO;
import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.entity.Purchase;
import com.trollMarket.TrollMarket.repository.BuyerRepository;
import com.trollMarket.TrollMarket.repository.ProductRepository;
import com.trollMarket.TrollMarket.repository.PurchaseRepository;
import com.trollMarket.TrollMarket.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShipperRepository shipperRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Page<ShopListProductDTO> getRows(String name,String category,String description, Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
        var rows = productRepository.getShopList(name,category,description,pageable);
        return rows;
    }

    public ProductDetailDTO getProductDetail(Long id){
        return productRepository.getProductDetail(id);
    }

    public void addToCart(String username,AddToCartDTO dto){
        var buyerId = buyerRepository. getBuyerIdByUsername(username);
        var entity = new Purchase();

        entity.setBuyerId(buyerId);
        entity.setProductId(dto.getProductId());
        entity.setShipmentId(dto.getShipmentId());
        entity.setQuantity(dto.getQuantity());
        entity.setDate(null);
        purchaseRepository.save(entity);

    }

    public List<DropdownDTO> getShipperDropdown()
    {
        return shipperRepository.getShipperDropdown();
    }
}
