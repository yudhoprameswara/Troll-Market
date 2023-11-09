package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dto.buyer.BuyerDetailDTO;
import com.trollMarket.TrollMarket.dto.shipper.ResponseUpsertShipperDTO;
import com.trollMarket.TrollMarket.dto.shipper.ShipperRowDTO;
import com.trollMarket.TrollMarket.dto.shipper.UpsertShipperDTO;
import com.trollMarket.TrollMarket.entity.Shipper;
import com.trollMarket.TrollMarket.repository.PurchaseRepository;
import com.trollMarket.TrollMarket.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ShipperService {

    @Autowired
    private ShipperRepository shipperRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Page<ShipperRowDTO> getRows( Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
        var rows = shipperRepository.getShipperRow(pageable);
        return rows;
    }

    public ResponseUpsertShipperDTO save(UpsertShipperDTO dto){
        var entity = new Shipper();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        if(dto.getService().equals("Yes")){
            entity.setService(true);
        }
        else entity.setService(false);
        var responseEntity =shipperRepository.save(entity);
        var responseDTO = new ResponseUpsertShipperDTO(
                responseEntity.getId(),
                responseEntity.getName(),
                responseEntity.getPrice(),
                dto.getService());
        return responseDTO;

    }

    public void stopService (Long id){
        var entity = shipperRepository.findById(id).get();
        entity.setService(false);
        shipperRepository.save(entity);
    }

    public Long countPurchaseByShipper(Long id){
        return purchaseRepository.countByShipper(id);
    }

    public void delete(Long id){
        shipperRepository.deleteById(id);
    }

    public UpsertShipperDTO findOne(Long id){
        var entity = shipperRepository.findById(id).get();
        var service = "";
        if(entity.getService() == true){
            service = "Yes";
        }
        else service ="No";
        var dto = new UpsertShipperDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                service
        );
        return dto;
    }
}
