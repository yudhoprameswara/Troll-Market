package com.trollMarket.TrollMarket.repository;

import com.trollMarket.TrollMarket.dto.shipper.ShipperRowDTO;
import com.trollMarket.TrollMarket.dto.utility.DropdownDTO;
import com.trollMarket.TrollMarket.entity.Shipper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper,Long> {

    @Query("""
        SELECT new com.trollMarket.TrollMarket.dto.shipper.ShipperRowDTO(
        shi.id,shi.name, shi.price, 
        CASE WHEN shi.service = true THEN 'Yes'
        ELSE 'No'
        END
        )
        FROM Shipper as shi
        """)
    public Page<ShipperRowDTO> getShipperRow(Pageable pageable);

    @Query("""
            SELECT new com.trollMarket.TrollMarket.dto.utility.DropdownDTO(
            shi.id, shi.name
            )
            FROM Shipper AS shi
            WHERE shi.service = true
            """)
    public List<DropdownDTO> getShipperDropdown();
}
