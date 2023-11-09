package com.trollMarket.TrollMarket.service;


import com.trollMarket.TrollMarket.dto.profile.ProfileDetailDTO;
import com.trollMarket.TrollMarket.dto.profile.ProfileTransactionRowDTO;
import com.trollMarket.TrollMarket.dto.profile.TopUpBalanceDTO;
import com.trollMarket.TrollMarket.entity.Buyer;
import com.trollMarket.TrollMarket.repository.AccountRepository;
import com.trollMarket.TrollMarket.repository.BuyerRepository;
import com.trollMarket.TrollMarket.repository.PurchaseRepository;
import com.trollMarket.TrollMarket.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public ProfileDetailDTO getDetails(String username){
        var role = accountRepository.findById(username).get().getRole();
        if(role.equals("Buyer")){
            return buyerRepository.getDetailBuyer(username);
        }
        return sellerRepository.getDetailSeller(username);
    }

    public Page<ProfileTransactionRowDTO> getRowTransaction(Long id, String username, Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
        var role = accountRepository.findById(username).get().getRole();
        if(role.equals("Buyer")){
            return purchaseRepository.getBuyerTransactionRow(id,pageable);
        }
        return purchaseRepository.getSellerTransactionRow(id,pageable);
    }

    public void topUpBalance(TopUpBalanceDTO dto){
           var entity =  buyerRepository.findById(dto.getId()).get();
           Double currentBalance;
           if (entity.getBalance()!= null){
               currentBalance = entity.getBalance();
               entity.setBalance(currentBalance + dto.getTopUp());
           }
           else{
               entity.setBalance(dto.getTopUp());
           }
           buyerRepository.save(entity);

       }

}
