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
    
    public AccountType getAccountTypeById(int accountTypeId);
    public List<AccountType> getAllAccountTypes();
    
}
