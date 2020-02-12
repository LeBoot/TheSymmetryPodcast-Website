/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.repository;

import bl.thesymmetrypodcast.entity.Account;
import bl.thesymmetrypodcast.entity.AccountType;
import bl.thesymmetrypodcast.entity.Region;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import org.junit.Before;
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
public class AccountRepositoryTest {
    
    @Autowired
    AccountRepository accountRepo;
    
    @Autowired
    AccountTypeRepository accountTypeRepo;
    
    @Autowired
    RegionRepository regionRepo;
    
    public AccountRepositoryTest() {
    }
    
    @Before
    public void setUp() {
        List<Account> myList = accountRepo.findAll();
        for (Account account : myList) {
            accountRepo.delete(account);
        }
    }
    
    //HELPER METHODS -----------------------------------------------------------
    //--------------------------------------------------------------------------
    
    private Account createNewAccount1() {
        Account newAccount = new Account();
        
        newAccount.setFirstName("first-name-1");
        newAccount.setLastName("last-name-1");
        newAccount.setUsername("username-1");
        newAccount.setPassword("password-1");
        newAccount.setEmail("email-1");
        newAccount.setStartDate(LocalDate.now());
        
        Optional<Region> region = regionRepo.findById(1);
        newAccount.setRegion(region.get());
        
        Optional<AccountType> accountType = accountTypeRepo.findById(1);
        newAccount.setAccountType(accountType.get());
        
        return newAccount;        
    }
    
    private Account createNewAccount2() {
        Account newAccount = new Account();
        
        newAccount.setFirstName("first-name-2");
        newAccount.setLastName("last-name-2");
        newAccount.setUsername("username-2");
        newAccount.setPassword("password-2");
        newAccount.setEmail("email-2");
        newAccount.setStartDate(LocalDate.now());
        
        Optional<Region> region = regionRepo.findById(2);
        newAccount.setRegion(region.get());
        
        Optional<AccountType> accountType = accountTypeRepo.findById(2);
        newAccount.setAccountType(accountType.get());
        
        return newAccount;     
    }
    
    
    //TESTS --------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    @Test
    public void testFindByIdANDSave() {
        Account account = createNewAccount1();
        account = accountRepo.save(account);
        
        int accountNum = account.getAccountnumber();
        
        Optional<Account> fromDB = accountRepo.findById(accountNum);
        
        assertEquals(account, fromDB.get());        
    }
    
    @Test
    public void testFindAll_1() {
        Account account = createNewAccount1();
        account = accountRepo.save(account);
        
        Account account2 = createNewAccount2();
        account2 = accountRepo.save(account2);
        
        List<Account> myList = accountRepo.findAll();
        
        assertEquals(2, myList.size());        
    }
    
    @Test
    public void testFindAll_2() {
        Account account = createNewAccount1();
        account = accountRepo.save(account);
        
        List<Account> myList = accountRepo.findAll();
        
        assertTrue(myList.contains(account));        
    }
    
    @Test
    public void testDelete() {
        Account account = createNewAccount1();
        account = accountRepo.save(account);
        
        int accountNum = account.getAccountnumber();
        
        Optional<Account> fromDBopt = accountRepo.findById(accountNum);
        Account fromDB = fromDBopt.get();
        
        assertNotNull(fromDB);
        
        accountRepo.delete(fromDB);
        
        Optional<Account> deletedAccount = accountRepo.findById(accountNum);
        
        assertTrue(deletedAccount.isEmpty());   
    }
    
}
