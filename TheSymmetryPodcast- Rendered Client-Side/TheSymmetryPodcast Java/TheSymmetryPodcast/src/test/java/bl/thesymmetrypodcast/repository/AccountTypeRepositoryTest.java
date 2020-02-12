/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.repository;

import bl.thesymmetrypodcast.entity.AccountType;
import java.util.List;
import java.util.Optional;
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
public class AccountTypeRepositoryTest {
    
    @Autowired
    AccountTypeRepository typeRepo;
    
    public AccountTypeRepositoryTest() {
    }
    
    //TESTS --------------------------------------------------------------------
    //--------------------------------------------------------------------------

    @Test
    public void testFindById() {
        Optional<AccountType> type = typeRepo.findById(2);
        assertFalse(type.isEmpty());
    }
    
    @Test
    public void testFindAll() {
        List<AccountType> myList = typeRepo.findAll();
        assertFalse(myList.isEmpty());
    }
    
    @Test
    public void testExistsById_1() {
        assertTrue(typeRepo.existsById(1));
    }
    
    @Test
    public void testExistsById_2() {
        assertFalse(typeRepo.existsById(100));
    }
    
}
