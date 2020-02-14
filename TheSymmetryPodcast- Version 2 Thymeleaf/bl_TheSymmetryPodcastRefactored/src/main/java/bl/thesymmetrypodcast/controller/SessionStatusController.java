/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.controller;

import bl.thesymmetrypodcast.entity.Account;
import bl.thesymmetrypodcast.entity.AccountType;
import bl.thesymmetrypodcast.requestBody.RBLoginAttempt;
import bl.thesymmetrypodcast.requestBody.RBSessionStats;
import bl.thesymmetrypodcast.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Boone
 */
@Controller
@CrossOrigin
@RequestMapping("/session-status")
public class SessionStatusController {
    
    @Autowired
    AccountServiceImpl accountService;
    
    static final String SESSION_IS_USER_ACCOUNT = "USER";
    static final String SESSION_IS_MEMBER_ACCOUNT = "MEMBER";
    static final String SESSION_IS_ADMINISTRATOR_ACCOUNT = "ADMIN";
    static final Integer NO_ACTIVE_SESSION = -1;
    
    static String sessionStatus = SESSION_IS_USER_ACCOUNT;
    static Integer currentAccount = NO_ACTIVE_SESSION;
    
    //handles request to know session status -----------------------------------
    @GetMapping("/get")
    public ResponseEntity<RBSessionStats> getSessionStatus() {
        RBSessionStats thisSession = new RBSessionStats();
        thisSession.setRbCurrentAccount(currentAccount);
        thisSession.setRbSessionStatus(sessionStatus);
        return new ResponseEntity<>(thisSession, HttpStatus.OK); 
    }
    
    //handles calls from log-out -----------------------------------------------
    @GetMapping("/logout")
    public ResponseEntity<String> changeSessionStatus() {
        sessionStatus = SESSION_IS_USER_ACCOUNT;
        currentAccount = NO_ACTIVE_SESSION;
        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }
    
    //handles login attempts ---------------------------------------------------
    @PostMapping("/login")
    public ResponseEntity<Integer> addNewMessage(@RequestBody RBLoginAttempt attempt) {
        //validate attempt in service layer
        int accountNumber = accountService.validateLoginAttempt(attempt);
        
        if (accountNumber == NO_ACTIVE_SESSION) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Account account = accountService.getAccountById(accountNumber);
            int accountTypeId = account.getAccountType().getAccounttypeid();
            
            switch (accountTypeId) {
                case 1 :
                    sessionStatus = SESSION_IS_MEMBER_ACCOUNT;
                    currentAccount = account.getAccountnumber();
                    return new ResponseEntity<>(HttpStatus.OK);
                case 2 :
                    sessionStatus = SESSION_IS_ADMINISTRATOR_ACCOUNT;
                    currentAccount = account.getAccountnumber();
                    return new ResponseEntity<>(HttpStatus.OK);
                default : //deactivated account or something unexpected; treat as if no account exists
                    sessionStatus = SESSION_IS_USER_ACCOUNT;
                    currentAccount = NO_ACTIVE_SESSION;
                    return new ResponseEntity<>(HttpStatus.OK);                   
            }
        }
    }
    
    //method for other controllers to change session status --------------------
    public void changeSessionStatusToLoggedOut() {
        sessionStatus = SESSION_IS_USER_ACCOUNT;
        currentAccount = NO_ACTIVE_SESSION;
    }
    
}
