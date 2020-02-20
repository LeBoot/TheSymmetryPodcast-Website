/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.Account;
import bl.thesymmetrypodcast.repository.AccountRepository;
import bl.thesymmetrypodcast.repository.AccountTypeRepository;
import bl.thesymmetrypodcast.repository.RegionRepository;
import bl.thesymmetrypodcast.requestBody.RBCreateAccount;
import bl.thesymmetrypodcast.requestBody.RBEditAccount;
import bl.thesymmetrypodcast.requestBody.RBLoginAttempt;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boone
 */
@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    AccountRepository accountRepo;
    
    @Autowired
    RegionRepository regionRepo;
    
    @Autowired
    AccountTypeRepository accountTypeRepo;
    
    //CRUD ---------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    @Override
    public Account getAccountById(int accountID) {
        Optional<Account> accountOpt = accountRepo.findById(accountID);
        return accountOpt.get();
    }
    
    @Override
    public Account saveAccount(Account account) {
        return accountRepo.save(account);
    }
    
    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
    
    @Override
    public void deleteAnAccount(int accountID) {
        Optional<Account> accountToDeleteOpt = accountRepo.findById(accountID);
        accountRepo.delete(accountToDeleteOpt.get());
    }
    
    //VALIDATION ---------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    @Override
    public int validateLoginAttempt(RBLoginAttempt attempt) {
        //get all accounts
        List<Account> allAccounts = accountRepo.findAll();
        
        //loop through all accounts, and if one has a username that matches the
            //input username, and also a password that matches the input password,
            //then that accout exists and the input is valid
        for (Account account : allAccounts) {
            if (
                attempt.getInputUsername().equals(account.getUsername()) &&
                attempt.getInputPassword().equals(account.getPassword()) &&
                (account.getAccountType().getAccounttypeid() != 3)
                ){
                return account.getAccountnumber();
            }
        }
        
        return -1;
    }
    
    @Override
    public boolean validateNewAccountRequest(RBCreateAccount requestBody) {        
        //check first name
        String myFirstName = requestBody.getFirstName();
        if ((myFirstName.length() < 2) || (myFirstName.length() > 24)) {
            return false;
        }
        //check last name
        String myLastName = requestBody.getLastName();
        if ((myLastName.length() < 2) || (myLastName.length() > 24)) {
            return false;
        }
        //check email
        String myEmail = requestBody.getEmail();
        if ((myEmail.length() < 4) || (myEmail.length() > 39) || (!myEmail.contains("@")) || (!myEmail.contains("."))) {
            return false;
        }
        //check region Id
        int myRegionId = requestBody.getRegionId();
        boolean doesRegionExist = regionRepo.existsById(myRegionId);
        if (doesRegionExist == false) {
            return false;
        }
        //check account type
        int myAccountTypeId = requestBody.getAccountTypeId();
        boolean doesAccountTypeExist = accountTypeRepo.existsById(myAccountTypeId);
        if (doesAccountTypeExist == false) {
            return false;
        }
        //check username 
        String myUsername = requestBody.getUsername();
        if ((myUsername.length() < 2) || (myUsername.length() > 15)) {
            return false;
        }
        List<Account> allAccounts = accountRepo.findAll();
        for (Account account : allAccounts) {
            if (account.getUsername().equalsIgnoreCase(myUsername) && (account.getAccountType().getAccounttypeid() != 3)) {
                return false;
            }
        }
        //check password
        String myPassword = requestBody.getPassword();
        if ((myPassword.length() < 2) || (myPassword.length() > 19)) {
            return false;
        }
        //if none of the if-statements were thrown...
        return true;  
    }
            
    @Override
    public boolean validateEditAccountRequest(RBEditAccount account) {
        //check first name
        String myFirstName = account.getFirstName();
        if ((myFirstName.length() < 2) || (myFirstName.length() > 24)) {
            return false;
        }
        //check last name
        String myLastName = account.getLastName();
        if ((myLastName.length() < 2) || (myLastName.length() > 24)) {
            return false;
        }
        //check email
        String myEmail = account.getEmail();
        if ((myEmail.length() < 4) || (myEmail.length() > 39) || (!myEmail.contains("@")) || (!myEmail.contains("."))) {
            return false;
        }
        //check region Id
        int myRegionId = account.getRegionId();
        boolean doesRegionExist = regionRepo.existsById(myRegionId);
        if (doesRegionExist == false) {
            return false;
        }
        //check account type
        int myAccountTypeId = account.getAccountTypeId();
        boolean doesAccountTypeExist = accountTypeRepo.existsById(myAccountTypeId);
        if (doesAccountTypeExist == false) {
            return false;
        }
        //check username 
        String myUsername = account.getUsername();
        if ((myUsername.length() < 2) || (myUsername.length() > 15)) {
            return false;
        }
        List<Account> allAccounts = accountRepo.findAll();
        for (Account loopAccount : allAccounts) {
            if (
                (loopAccount.getUsername().equalsIgnoreCase(myUsername)) && 
                (loopAccount.getAccountType().getAccounttypeid() != 3) &&
                (loopAccount.getAccountnumber() != account.getAccountId())
                ) {
                return false;
            }
        }
        //check password
        String myPassword = account.getPassword();
        if ((myPassword.length() < 2) || (myPassword.length() > 19)) {
            return false;
        }
        //if none of the if-statements were thrown...
        return true; 
    }
            
            
    
    
}
