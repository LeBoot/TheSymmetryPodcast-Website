/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.AccountType;
import bl.thesymmetrypodcast.repository.AccountTypeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boone
 */
@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    
    @Autowired
    AccountTypeRepository accountTypeRepo;
    
    @Override
    public AccountType getAccountTypeById(int accountTypeId) {
        Optional<AccountType> account = accountTypeRepo.findById(accountTypeId);
        return account.get();
    }
    
    @Override
    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepo.findAll();
    }
    
}
