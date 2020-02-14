/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.Account;
import bl.thesymmetrypodcast.requestBody.RBCreateAccount;
import bl.thesymmetrypodcast.requestBody.RBEditAccount;
import bl.thesymmetrypodcast.requestBody.RBLoginAttempt;
import java.util.List;

/**
 *
 * @author Boone
 */
public interface AccountService {
    
    //CRUD ---------------------------------------------------------------------
    public Account getAccountById(int accountID);
    public Account saveAccount(Account account);
    public List<Account> getAllAccounts();
    public void deleteAnAccount(int accountID);
    
    //VALIDATION ---------------------------------------------------------------
    public int validateLoginAttempt(RBLoginAttempt attempt);
    public boolean validateNewAccountRequest(RBCreateAccount requestBody);
    public boolean validateEditAccountRequest(RBEditAccount account);
    
}
