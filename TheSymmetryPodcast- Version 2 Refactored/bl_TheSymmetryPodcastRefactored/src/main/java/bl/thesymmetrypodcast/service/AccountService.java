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
    
    /**
     * Returns an account based on an accountID.
     * @param accountID
     * @return Account
     */
    public Account getAccountById(int accountID);
    
    /**
     * Saves an account (either new or edited) and then returns account.
     * <p>
     * For a new account, the returned value will contain the accountID.
     * @param account
     * @return Account
     */
    public Account saveAccount(Account account);
    
    /**
     * Returns a list of all accounts.
     * @return List of Accounts
     */
    public List<Account> getAllAccounts();
    
    /**
     * Permanently deletes an account.
     * @param accountID 
     */
    public void deleteAnAccount(int accountID);
    
    //VALIDATION ---------------------------------------------------------------
    
    /**
     * Validates a login attempt.  Username and password must match an active
     * account.
     * <p>
     * If the attempt is valid, then the account number is returned.  If not
     * valid, the value -1 is returned.
     * @param attempt
     * @return account number or -1
     */
    public int validateLoginAttempt(RBLoginAttempt attempt);
    
    /**
     * Validates a request for a new account by ensuring that all values are
     * present and within database size constraints.  Additionally, a new account
     * cannot have a username that is held by an active account.
     * @param requestBody
     * @return true = data is good; false = data is bad.
     */
    public boolean validateNewAccountRequest(RBCreateAccount requestBody);
    
    /**
     * Validates a request to edit an account by ensuring that all values are
     * present and within database size constraints.  Additionally, the username
     * cannot be one that is currently held by an active account, except that of
     * the account requesting the change.
     * @param account
     * @return true = data is good; false = data is bad.
     */
    public boolean validateEditAccountRequest(RBEditAccount account);
    
}
