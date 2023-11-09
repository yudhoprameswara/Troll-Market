package com.trollMarket.TrollMarket.repository;


import com.trollMarket.TrollMarket.dto.cart.CartDetailDTO;
import com.trollMarket.TrollMarket.dto.cart.CartRowDTO;
import com.trollMarket.TrollMarket.dto.history.HistoryRowDTO;
import com.trollMarket.TrollMarket.dto.profile.ProfileTransactionRowDTO;
import com.trollMarket.TrollMarket.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {


    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.profile.ProfileTransactionRowDTO(
            pur.date,
            pro.name,
            pur.quantity,
            shi.name,
            ((pro.price*pur.quantity)+shi.price)
            )
            FROM Purchase AS pur
            JOIN pur.product AS pro
            JOIN pur.shipper AS shi
            WHERE pur.buyerId = :buyerId
            AND pur.date IS NOT NULL
            """)
    public Page<ProfileTransactionRowDTO> getBuyerTransactionRow(@Param("buyerId")Long buyerId, Pageable pageable);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.profile.ProfileTransactionRowDTO(
            pur.date,
            pro.name,
            pur.quantity,
            shi.name,
            ((pro.price*pur.quantity)+shi.price)
            )
            FROM Purchase AS pur
            JOIN pur.product AS pro
            JOIN pur.shipper AS shi
            WHERE pro.sellerId = :sellerId
            AND pur.date IS NOT NULL
            """)
    public Page<ProfileTransactionRowDTO> getSellerTransactionRow(@Param("sellerId")Long buyerId, Pageable pageable);

    @Query("""
            SELECT COUNT(pur.productId)
            FROM Purchase AS pur
            WHERE pur.productId = :productId
            """)
    public Long countByProduct(@Param("productId") Long productId);

    @Query("""
            SELECT COUNT(pur.shipmentId)
            FROM Purchase AS pur
            WHERE pur.shipmentId = :shipmentId
            """)
    public Long countByShipper(@Param("shipmentId") Long shipperId);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.history.HistoryRowDTO(
             pur.date,sel.name,buy.name,pro.name, pur.quantity, shi.name,(pro.price * pur.quantity) + shi.price
            )
            FROM Purchase AS pur
            JOIN Product AS pro ON pur.productId = pro.id
            JOIN Shipper AS shi ON pur.shipmentId = shi.id
            JOIN Buyer AS buy ON pur.buyerId = buy.id
            JOIN Seller AS sel ON pro.sellerId = sel.id
            WHERE 
            pur.date IS NOT NULL 
            AND
            (:sellerId IS NULL OR sel.id = :sellerId)
            AND
            (:buyerId IS NULL OR pur.buyerId = :buyerId)
            """)
    public Page<HistoryRowDTO> getHistoryRow(@Param("sellerId") Long sellerId,
                                             @Param("buyerId") Long buyerId,Pageable pageable);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.cart.CartRowDTO(
            pur.id,
            pro.name,
            pur.quantity,
            shi.name,
            sel.name,
            (pro.price * pur.quantity) + shi.price
            )
            FROM Purchase AS pur
            JOIN Product AS pro ON pur.productId = pro.id
            JOIN Shipper AS shi ON pur.shipmentId = shi.id
            JOIN Seller AS sel ON pro.sellerId = sel.id
            WHERE pur.date IS NULL
            AND pur.buyerId = :buyerId
            """)
    public Page<CartRowDTO> getCartRow (@Param ("buyerId") Long buyerId, Pageable pageable);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.cart.CartDetailDTO(
            pur.id,
            pur.productId,
            pur.quantity,
            shi.name,
            sel.name,
            (pro.price * pur.quantity),
            (pro.price * pur.quantity) + shi.price
            )
            FROM Purchase AS pur
            JOIN Product AS pro ON pur.productId = pro.id
            JOIN Shipper AS shi ON pur.shipmentId = shi.id
            JOIN Seller AS sel ON pro.sellerId = sel.id
            WHERE pur.date IS NULL
            AND pur.buyerId = :buyerId
            """)
    public List<CartDetailDTO> getCartList (@Param ("buyerId") Long buyerId);

    @Query("""
            SELECT SUM ((pro.price * pur.quantity) + shi.price)
            FROM Purchase AS pur
            JOIN Product AS pro ON pur.productId = pro.id
            JOIN Shipper AS shi ON pur.shipmentId = shi.id
            WHERE pur.buyerId = :buyerId
            AND pur.date IS NULL
            """)
    public Double totalPrice(@Param("buyerId")Long buyerId);

}
