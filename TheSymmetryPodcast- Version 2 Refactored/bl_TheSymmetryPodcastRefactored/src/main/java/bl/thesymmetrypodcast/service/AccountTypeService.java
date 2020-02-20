/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.AccountType;
import java.util.List;

/**
 *
 * @author Boone
 */
public interface AccountTypeService {
    
    /**
     * Returns an AccountType based on Id.
     * @param accountTypeId
     * @return AccountType
     */
    public AccountType getAccountTypeById(int accountTypeId);
    
    /**
     * Returns a list of all Account Types.
     * @return List of Account Types
     */
    public List<AccountType> getAllAccountTypes();
    
}
