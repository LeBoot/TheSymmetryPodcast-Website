/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.controller;

import bl.thesymmetrypodcast.entity.Account;
import bl.thesymmetrypodcast.requestBody.RBCreateAccount;
import bl.thesymmetrypodcast.requestBody.RBEditAccount;
import bl.thesymmetrypodcast.service.AccountServiceImpl;
import bl.thesymmetrypodcast.service.AccountTypeServiceImpl;
import bl.thesymmetrypodcast.service.RegionServiceImpl;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Boone
 */
@Controller
@CrossOrigin
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    AccountServiceImpl accountService;
    
    @Autowired
    RegionServiceImpl regionService;
    
    @Autowired
    AccountTypeServiceImpl accountTypeService;
    
    @Autowired
    SessionStatusController sessionStatusController;
    
    //MAPPINGS -----------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    //handles attempts to create account ---------------------------------------
    @PostMapping("/create")
    public ResponseEntity<String> createNewAccount(@RequestBody RBCreateAccount requestBody ) {
        boolean isRequestValid = accountService.validateNewAccountRequest(requestBody);
        if (isRequestValid == false) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Account newAccount = new Account();
            
            newAccount = fillAccountParameters(requestBody, newAccount);
            
            accountService.saveAccount(newAccount);
            
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
    
    //retrieves a single account based upon incoming id ------------------------
    @GetMapping("/get/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable int accountId) {
        Account account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    
    //handles edited accounts --------------------------------------------------
    @PostMapping("/edit")
    public ResponseEntity<String> editAnAccount(@RequestBody RBEditAccount editedAccount) {
        boolean areEditsValid = accountService.validateEditAccountRequest(editedAccount);
        if (areEditsValid == false) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Account account = accountService.getAccountById(editedAccount.getAccountId());
            
            account = fillAccountParameters(editedAccount, account);
            
            accountService.saveAccount(account);
            
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    
    //handles when a user "deletes" an account ---------------------------------
    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateAnAccount(@RequestBody RBEditAccount editedAccount) {
            Account account = accountService.getAccountById(editedAccount.getAccountId());
            
            account = fillAccountParameters(editedAccount, account);
            
            accountService.saveAccount(account);
            
            sessionStatusController.changeSessionStatusToLoggedOut();
            
            return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //handles when an administrator deletes an account -------------------------
    @GetMapping("/delete/{accountId}")
    public ResponseEntity<Account> deleteAnAccountById(@PathVariable int accountId) {
        accountService.deleteAnAccount(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //handles request for get all accounts -------------------------------------
    @GetMapping("/get-all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> allAccounts = accountService.getAllAccounts();
        return new ResponseEntity<>(allAccounts, HttpStatus.OK);
    }
    
    //HELPER METHODS -----------------------------------------------------------
    //--------------------------------------------------------------------------
    
    private Account fillAccountParameters(RBCreateAccount requestBody, Account account) {
        account.setFirstName(requestBody.getFirstName());
        account.setLastName(requestBody.getLastName());
        account.setEmail(requestBody.getEmail());
        account.setUsername(requestBody.getUsername());
        account.setPassword(requestBody.getPassword());
        account.setRegion(regionService.getRegionById(requestBody.getRegionId()));
        account.setStartDate(LocalDate.now());
        account.setAccountType(accountTypeService.getAccountTypeById(requestBody.getAccountTypeId()));
        return account;
    } 
    
    private Account fillAccountParameters(RBEditAccount requestBody, Account account) {
        account.setFirstName(requestBody.getFirstName());
        account.setLastName(requestBody.getLastName());
        account.setEmail(requestBody.getEmail());
        account.setUsername(requestBody.getUsername());
        account.setPassword(requestBody.getPassword());
        account.setRegion(regionService.getRegionById(requestBody.getRegionId()));
        account.setAccountType(accountTypeService.getAccountTypeById(requestBody.getAccountTypeId()));
        return account;
    } 
}
