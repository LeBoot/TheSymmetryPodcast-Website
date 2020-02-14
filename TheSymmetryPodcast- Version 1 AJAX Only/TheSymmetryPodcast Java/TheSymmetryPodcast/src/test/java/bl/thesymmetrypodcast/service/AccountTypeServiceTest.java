/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.AccountType;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Boone
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountTypeServiceTest {
    
    @Autowired
    AccountTypeServiceImpl atService;
    
    public AccountTypeServiceTest() {
    }

    //TESTS ====================================================================
    @Test
    public void testGetAccountTypeById() {
        AccountType at = atService.getAccountTypeById(1);
        assertTrue(at != null);
    }
    
    @Test
    public void testGetAllAccountTypes() {
        List<AccountType> myList = atService.getAllAccountTypes();
        assertFalse(myList.isEmpty());
    }
    
}
