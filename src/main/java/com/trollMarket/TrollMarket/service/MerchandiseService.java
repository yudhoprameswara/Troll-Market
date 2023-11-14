package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dto.product.ProductRowDTO;
import com.trollMarket.TrollMarket.dto.product.UpsertProductDTO;
import com.trollMarket.TrollMarket.dto.shipper.ShipperRowDTO;
import com.trollMarket.TrollMarket.entity.Product;
import com.trollMarket.TrollMarket.repository.AccountRepository;
import com.trollMarket.TrollMarket.repository.ProductRepository;
import com.trollMarket.TrollMarket.repository.PurchaseRepository;
import com.trollMarket.TrollMarket.repository.SellerRepository;
import com.trollMarket.TrollMarket.utility.FileHelper;
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
        var dto = new UpsertProductDTO();
              dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setSellerId(entity.getSellerId());
                dto.setCategory(entity.getCategory());
                dto.setDescription(entity.getDescription());
                dto.setPrice(entity.getPrice());
                dto.setDiscontinue(entity.getDiscontinue());
                dto.setImagePath(entity.getImagePath());
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
        entity.setImagePath(dto.getImagePath());
        productRepository.save(entity);
    }

    public void imageFileHandler(UpsertProductDTO dto){
        var multipartFile = dto.getImage();
        var isMultipartEmpty = multipartFile.isEmpty();
        var imagePath = ((dto.getImagePath() == null || dto.getImagePath().equals(""))
                && isMultipartEmpty) ? null : dto.getImagePath();
        try{
            if(!isMultipartEmpty){
                imagePath = FileHelper.uploadProductPhoto(imagePath, multipartFile);
            }
            dto.setImagePath(imagePath);

        } catch (Exception exception){
            System.out.println("eh kena");
        }
    }

    public Long getCountProductByTransaction(Long productId){
        return purchaseRepository.countByProduct(productId);
    }

    public void delete(Long id){

        String imagePath = productRepository.getImagePath(id);
        FileHelper.deleteProductPhoto(imagePath);
        productRepository.deleteById(id);
    }

    public void discontinueButton(Long id){
       var entity= productRepository.findById(id).get();
       entity.setDiscontinue(true);
       productRepository.save(entity);
    }
}
