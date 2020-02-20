/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.controller;

import bl.thesymmetrypodcast.entity.Account;
import bl.thesymmetrypodcast.entity.AccountType;
import bl.thesymmetrypodcast.entity.Region;
import bl.thesymmetrypodcast.service.AccountServiceImpl;
import bl.thesymmetrypodcast.service.AccountTypeServiceImpl;
import bl.thesymmetrypodcast.service.MailServiceImpl;
import bl.thesymmetrypodcast.service.RegionServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Boone
 */
@Controller
@CrossOrigin
@RequestMapping("/community")
public class CommunityController {
    
    @Autowired
    RegionServiceImpl regionService;
    
    @Autowired
    AccountTypeServiceImpl accountTypeService;
    
    @Autowired
    AccountServiceImpl accountService;
    
    @Autowired
    MailServiceImpl mailService;
    
    
    //Return a list of all the regions -----------------------------------------
    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> allRegions = regionService.getAllRegions();
        return new ResponseEntity<>(allRegions, HttpStatus.OK);
    }
    
    //return a list of all account types ---------------------------------------
    @GetMapping("/accountTypes")
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
        List<AccountType> allAccountTypes = accountTypeService.getAllAccountTypes();
        return new ResponseEntity<>(allAccountTypes, HttpStatus.OK);
    }
    
    //handles forgot password --------------------------------------------------
    @GetMapping("/forgotPassword/{inputEmail}")
    public ResponseEntity<HttpStatus> forgotPassword(@PathVariable String inputEmail) {
        List<Account> allAccounts = accountService.getAllAccounts();
        for (Account account : allAccounts) {
            if (account.getEmail().equalsIgnoreCase(inputEmail) && (account.getAccountType().getAccounttypeid() != 3)) {
                try {
                    String newPassword = mailService.sendEmail(account.getEmail(), account.getUsername());
                    account.setPassword(newPassword);
                    accountService.saveAccount(account);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (Exception ex) {
                    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
