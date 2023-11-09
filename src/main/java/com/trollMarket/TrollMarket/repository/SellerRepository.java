package com.trollMarket.TrollMarket.repository;

import com.trollMarket.TrollMarket.dto.buyer.BuyerDetailDTO;
import com.trollMarket.TrollMarket.dto.profile.ProfileDetailDTO;
import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long> {

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.profile.ProfileDetailDTO(
            sel.id, sel.name, sel.address, sel.balance
            )
            FROM Seller AS sel
            WHERE sel.username = :username
            """)
    public ProfileDetailDTO getDetailSeller(@Param("username")String username);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.utility.DropdownDTO
            (sel.id, sel.name)
            FROM Seller AS sel
            """)
    public List<DropdownDTO> sellerListDropdown();
}
