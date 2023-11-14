package com.trollMarket.TrollMarket.repository;

import com.trollMarket.TrollMarket.dto.product.ProductDetailDTO;
import com.trollMarket.TrollMarket.dto.product.ProductRowDTO;
import com.trollMarket.TrollMarket.dto.product.ShopListProductDTO;
import com.trollMarket.TrollMarket.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.product.ProductRowDTO(
            pro.id,pro.name,pro.category,
            CASE
            WHEN pro.discontinue = true THEN 'Yes'
            WHEN pro.discontinue = false THEN 'No'
            END,
            pro.sellerId
            )
            FROM Product AS pro
            WHERE pro.sellerId = :sellerId
            """)
    public Page<ProductRowDTO> getProductRow(@Param("sellerId")Long sellerId, Pageable pageable);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.product.ShopListProductDTO(
            pro.id,
            pro.name,
            pro.price,
            pro.imagePath,
            pro.description)
            FROM Product AS pro
            WHERE  pro.discontinue = false 
            AND
            pro.name LIKE %:name%
            AND pro.category LIKE %:category%
            AND pro.description LIKE %:description%
            """)
    public Page<ShopListProductDTO> getShopList(@Param("name") String name,
                                                @Param("category") String category,
                                                @Param("description") String description,
                                                Pageable pageable);


    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.product.ProductDetailDTO
            (
                pro.name,
                pro.category,
                pro.description,
                pro.price,
                sel.name
            )
            FROM Product AS pro
            JOIN Seller AS sel ON pro.sellerId = sel.Id
            WHERE pro.id = :id
            """)
    public ProductDetailDTO getProductDetail(@Param("id") Long id);

    @Query("""
            SELECT pro.imagePath
            FROM Product AS pro
            WHERE pro.id = :id
            """)
    public String getImagePath(@Param("id") Long id);

}
