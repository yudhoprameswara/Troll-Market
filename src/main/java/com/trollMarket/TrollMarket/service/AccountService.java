package com.trollMarket.TrollMarket.service;

import com.trollMarket.TrollMarket.dto.account.AdminRegisterDTO;
import com.trollMarket.TrollMarket.dto.account.RegisterDTO;
import com.trollMarket.TrollMarket.entity.Account;
import com.trollMarket.TrollMarket.entity.Buyer;
import com.trollMarket.TrollMarket.entity.Seller;
import com.trollMarket.TrollMarket.repository.AccountRepository;
import com.trollMarket.TrollMarket.repository.BuyerRepository;
import com.trollMarket.TrollMarket.repository.SellerRepository;
import com.trollMarket.TrollMarket.utility.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = accountRepository.findById(username).get();
        if(account == null){
            throw new UsernameNotFoundException("Username tidak ditemukan");
        }
        else {
            var userDetail = new ApplicationUserDetails(account);
            return userDetail;
        }
    }


    public void register(RegisterDTO dto){
        var entity = new Account();
        entity.setUsername(dto.getUsername());
        var hashedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashedPassword);
        entity.setRole(dto.getRole());
        accountRepository.save(entity);

        if(dto.getRole().equals("Buyer")) {
            saveBuyer(dto);
        }
        else if (dto.getRole().equals("Seller")) {
            saveSeller(dto);
        }
    }

    public void registerAdmin(AdminRegisterDTO dto){
        var entity = new Account();
        entity.setUsername(dto.getUsername());
        var hashedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashedPassword);
        entity.setRole(dto.getRole());
        accountRepository.save(entity);
    }

    public void saveBuyer(RegisterDTO dto){
        var entity = new Buyer();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setBalance(dto.getBalance());
        buyerRepository.save(entity);
    }

    public void saveSeller(RegisterDTO dto){
        var entity = new Seller();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setBalance(dto.getBalance());
        sellerRepository.save(entity);
    }

    public Boolean isUsernameExist(String username){
        var total = accountRepository.countExistingUser(username);
        return (total > 0);
    }
}
