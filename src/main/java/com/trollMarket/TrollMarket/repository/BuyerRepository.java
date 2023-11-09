package com.trollMarket.TrollMarket.repository;

import com.trollMarket.TrollMarket.dto.buyer.BuyerDetailDTO;
import com.trollMarket.TrollMarket.dto.profile.ProfileDetailDTO;
import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Long> {

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.profile.ProfileDetailDTO(
            buy.id, buy.name, buy.address, buy.balance
            )
            FROM Buyer AS buy
            WHERE buy.username = :username
            """)
    public ProfileDetailDTO getDetailBuyer(@Param("username")String username);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.utility.DropdownDTO
            (buy.id, buy.name)
            FROM Buyer AS buy
            """)
    public List<DropdownDTO> buyerListDropdown();

    @Query("""
            SELECT buy.id
            FROM Buyer AS buy
            WHERE buy.username = :username
            """)
    public Long getBuyerIdByUsername(@Param("username")String username);
}
