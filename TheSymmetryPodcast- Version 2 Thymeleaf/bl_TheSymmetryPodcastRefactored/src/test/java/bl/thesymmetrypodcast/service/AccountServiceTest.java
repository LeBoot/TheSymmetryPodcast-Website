/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.Account;
import bl.thesymmetrypodcast.entity.AccountType;
import bl.thesymmetrypodcast.entity.Region;
import bl.thesymmetrypodcast.repository.AccountRepository;
import bl.thesymmetrypodcast.repository.AccountTypeRepository;
import bl.thesymmetrypodcast.repository.RegionRepository;
import bl.thesymmetrypodcast.requestBody.RBCreateAccount;
import bl.thesymmetrypodcast.requestBody.RBEditAccount;
import bl.thesymmetrypodcast.requestBody.RBLoginAttempt;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class AccountServiceTest {
    
    @Autowired
    AccountServiceImpl accountService;
    
    @Autowired
    AccountRepository accountRepo;
    
    @Autowired
    RegionRepository regionRepo;
    
    @Autowired
    AccountTypeRepository accountTypeRepo;
    
    public AccountServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<Account> myList = accountRepo.findAll();
        for (Account account : myList) {
            accountRepo.delete(account);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    
    //HELPER METHODS -----------------------------------------------------------
    //--------------------------------------------------------------------------
    
    private Account createNewAccount1() {
        Account newAccount = new Account();
        
        newAccount.setFirstName("first-name-1");
        newAccount.setLastName("last-name-1");
        newAccount.setUsername("username-1");
        newAccount.setPassword("password-1");
        newAccount.setEmail("email-1@website.com");
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
        newAccount.setEmail("email-2@website.com");
        newAccount.setStartDate(LocalDate.now());
        
        Optional<Region> region = regionRepo.findById(2);
        newAccount.setRegion(region.get());
        
        Optional<AccountType> accountType = accountTypeRepo.findById(2);
        newAccount.setAccountType(accountType.get());
        
        return newAccount;     
    }
    
    private RBLoginAttempt createRBLoginAttempt(String password, String username) {
        RBLoginAttempt attempt = new RBLoginAttempt();
        
        attempt.setInputPassword(password);
        attempt.setInputUsername(username);
        
        return attempt;
    }
    
    private RBCreateAccount createRBCreateAccount(String myFirstName, String myLastName, 
            String myEmail, String myUsername, String myPassword, int myRegionID, int myAccountTypeID) {
        RBCreateAccount request = new RBCreateAccount();
        
        request.setFirstName(myFirstName);
        request.setLastName(myLastName);
        request.setEmail(myEmail);
        request.setUsername(myUsername);
        request.setPassword(myPassword);
        request.setRegionId(myRegionID);
        request.setAccountTypeId(myAccountTypeID);
        
        return request;
    }
    
    private RBEditAccount createRBEditAccount(int myAccountID, String myFirstName, String myLastName, 
            String myEmail, String myUsername, String myPassword, int myRegionID, int myAccountTypeID) {
        RBEditAccount request = new RBEditAccount();
        
        request.setAccountId(myAccountID);
        request.setFirstName(myFirstName);
        request.setLastName(myLastName);
        request.setEmail(myEmail);
        request.setUsername(myUsername);
        request.setPassword(myPassword);
        request.setRegionId(myRegionID);
        request.setAccountTypeId(myAccountTypeID);
        
        return request;
    }
    
    //TESTS --------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    //CRUD ---------------------------------------------------------------------
    @Test
    public void testGetAccountByIdANDSaveAccount() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
        
        int accountNum = account.getAccountnumber();
        
        Account fromDB = accountService.getAccountById(accountNum);
        
        assertEquals(account, fromDB);
    }

    @Test
    public void testGetAllAccounts_1() {
        Account account = createNewAccount1();
        accountService.saveAccount(account);
        
        Account account2 = createNewAccount2();
        accountService.saveAccount(account2);
        
        List<Account> myList = accountService.getAllAccounts();
        
        assertEquals(2, myList.size());
    }
    
    @Test
    public void testGetAllAccounts_2() {
        Account account = createNewAccount1();
        accountService.saveAccount(account);
        
        List<Account> myList = accountService.getAllAccounts();
        
        assertTrue(myList.contains(account));
    }

    @Test
    public void testDeleteAnAccount() {
        Account account = createNewAccount1();
        accountService.saveAccount(account);
        
        int accountNum = account.getAccountnumber();
        
        Account fromDB = accountService.getAccountById(accountNum);
        
        assertNotNull(fromDB);
        
        accountService.deleteAnAccount(accountNum);
        
        Optional<Account> deletedAccount = accountRepo.findById(accountNum);
        
        assertTrue(deletedAccount.isEmpty()); 
        
    }
    
    
    //VALIDATION ---------------------------------------------------------------
    
    /**
     * Test of validateLoginAttempt method--------------------------------------
     */
    @Test //should be good input
    public void testValidateLoginAttempt_1() {
        Account account = createNewAccount1();
        String password = account.getPassword();
        String username = account.getUsername();
        accountService.saveAccount(account);
        
        RBLoginAttempt attempt = createRBLoginAttempt(password, username);
        
        int accountNumber = accountService.validateLoginAttempt(attempt);
        
        assertTrue(accountNumber != -1);
    }
    
    @Test //username and password flipped
    public void testValidateLoginAttempt_2() {
        Account account = createNewAccount1();
        String password = account.getPassword();
        String username = account.getUsername();
        accountService.saveAccount(account);
        
        RBLoginAttempt attempt = createRBLoginAttempt(username, password);
        
        int accountNumber = accountService.validateLoginAttempt(attempt);
        
        assertTrue(accountNumber == -1);
    }
    
    @Test //good username, bad password
    public void testValidateLoginAttempt_3() {
        Account account = createNewAccount1();
        String password = account.getPassword() + "abc";
        String username = account.getUsername();
        accountService.saveAccount(account);
        
        RBLoginAttempt attempt = createRBLoginAttempt(password, username);
        
        int accountNumber = accountService.validateLoginAttempt(attempt);
        
        assertTrue(accountNumber == -1);
    }
    
    @Test //good password, bad username
    public void testValidateLoginAttempt_4() {
        Account account = createNewAccount1();
        String password = account.getPassword();
        String username = account.getUsername() + "abc";
        accountService.saveAccount(account);
        
        RBLoginAttempt attempt = createRBLoginAttempt(password, username);
        
        int accountNumber = accountService.validateLoginAttempt(attempt);
        
        assertTrue(accountNumber == -1);
    }

    @Test //bad username and bad password
    public void testValidateLoginAttempt_5() {
        Account account = createNewAccount1();
        String password = account.getPassword() + "abc";
        String username = account.getUsername() + "abc";
        accountService.saveAccount(account);
        
        RBLoginAttempt attempt = createRBLoginAttempt(password, username);
        
        int accountNumber = accountService.validateLoginAttempt(attempt);
        
        assertTrue(accountNumber == -1);
    }
    
    @Test
    public void testValidateLoginAttempt_6() {
        Account account = createNewAccount1();
        String password = account.getPassword();
        String username = account.getUsername();
        
        Optional<AccountType> type = accountTypeRepo.findById(3);
        account.setAccountType(type.get());
        
        accountService.saveAccount(account);
        
        RBLoginAttempt attempt = createRBLoginAttempt(password, username);
        
        int accountNumber = accountService.validateLoginAttempt(attempt);
        
        assertTrue(accountNumber == -1);
    }
    
    
    @Test //all data good
    public void testValidateNewAccountRequest_1() {
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "goodEmail@Website.com";
        String un = "goodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertTrue(accountService.validateNewAccountRequest(rb));
    }
    
    /**
     * Test of validateCreateAccountRequest method------------------------------
     */
    @Test //short first name
    public void testValidateNewAccountRequest_2() {
        String fn = "a";
        String ln = "goodLastName";
        String em = "goodEmail@Website.com";
        String un = "goodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //long first name
    public void testValidateNewAccountRequest_3() {
        String fn = "goodFirstNamegoodFirstNamegoodFirstNamegoodFirstName";
        String ln = "goodLastName";
        String em = "goodEmail@Website.com";
        String un = "goodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //short last name
    public void testValidateNewAccountRequest_4() {
        String fn = "goodFirstName";
        String ln = "e";
        String em = "goodEmail@Website.com";
        String un = "goodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //long last name
    public void testValidateNewAccountRequest_5() {
        String fn = "goodFirstName";
        String ln = "goodLastNamegoodLastNamegoodLastNamegoodLastNamegoodLastName";
        String em = "goodEmail@Website.com";
        String un = "goodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //short email
    public void testValidateNewAccountRequest_6() {
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "@.";
        String un = "goodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //long email
    public void testValidateNewAccountRequest_7() {
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "email@website.comemail@website.comemail@website.comemail@website.comemail@website.com";
        String un = "goodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //no @ email
    public void testValidateNewAccountRequest_8() {
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "badEmail.com";
        String un = "goodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //no . email
    public void testValidateNewAccountRequest_9() {
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "badEmail@com";
        String un = "goodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }

    @Test //short username
    public void testValidateNewAccountRequest_10() {
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "goodEmail@Website.com";
        String un = "e";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //long username
    public void testValidateNewAccountRequest_11() {
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "goodEmail@Website.com";
        String un = "goodUsernamegoodUsernamegoodUsernamegoodUsernamegoodUsernamegoodUsername";
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //duplicate username of active account
    public void testValidateNewAccountRequest_12() {
        Account account = createNewAccount1();
        accountService.saveAccount(account);
        
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "goodEmail@Website.com";
        String un = account.getUsername();
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //duplicate username of inactive account
    public void testValidateNewAccountRequest_13() {
        Account account = createNewAccount1();
        
        AccountType at = accountTypeRepo.getOne(3);
        account.setAccountType(at);
        accountService.saveAccount(account);
        
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "goodEmail@Website.com";
        String un = account.getUsername();
        String pw = "goodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertTrue(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //short password
    public void testValidateNewAccountRequest_14() {
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "goodEmail@Website.com";
        String un = "goodUsername";
        String pw = "e";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }
    
    @Test //long password
    public void testValidateNewAccountRequest_15() {
        String fn = "goodFirstName";
        String ln = "goodLastName";
        String em = "goodEmail@Website.com";
        String un = "goodUsername";
        String pw = "goodPasswordgoodPasswordgoodPasswordgoodPasswordgoodPasswordgoodPassword";
        int rID = regionRepo.findById(2).get().getRegionid();
        int atID = accountTypeRepo.findById(1).get().getAccounttypeid();
        
        RBCreateAccount rb = createRBCreateAccount(fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateNewAccountRequest(rb));
    }

    /**
     * Test of validateEditAccountRequest method--------------------------------
     */
    @Test //all data good
    public void testValidateEditAccountRequest_1() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = account.getEmail();
        String un = account.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertTrue(accountService.validateEditAccountRequest(rb));
    }

    @Test //short first name
    public void testValidateEditAccountRequest_2() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = "a";
        String ln = account.getLastName();
        String em = account.getEmail();
        String un = account.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //long first name
    public void testValidateEditAccountRequest_3() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = "longfirstnamelongfirstnamelongfirstnamelongfirstname";
        String ln = account.getLastName();
        String em = account.getEmail();
        String un = account.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //short last name
    public void testValidateEditAccountRequest_4() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = "e";
        String em = account.getEmail();
        String un = account.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //long last name
    public void testValidateEditAccountRequest_5() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = "longLastNamelongLastNamelongLastNamelongLastNamelongLastName";
        String em = account.getEmail();
        String un = account.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //short email
    public void testValidateEditAccountRequest_6() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = "@.";
        String un = account.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //long email
    public void testValidateEditAccountRequest_7() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = "email@website.comemail@website.comemail@website.comemail@website.com";
        String un = account.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //no @ email
    public void testValidateEditAccountRequest_8() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = "emailatwebsite.com";
        String un = account.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //no . email
    public void testValidateEditAccountRequest_9() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = "email@websitedotcom";
        String un = account.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //short username
    public void testValidateEditAccountRequest_10() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = account.getEmail();
        String un = "e";
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //long username
    public void testValidateEditAccountRequest_11() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = account.getEmail();
        String un = "longusernamelongusernamelongusernamelongusernamelongusername";
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //duplicate username of different active account
    public void testValidateEditAccountRequest_12() {
        Account activeAccount = createNewAccount2();
        accountService.saveAccount(activeAccount);
        
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = account.getEmail();
        String un = activeAccount.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //duplicate username of inactive account
    public void testValidateEditAccountRequest_13() {
        Account inactiveAccount = createNewAccount2();
        
        AccountType at = accountTypeRepo.getOne(3);
        inactiveAccount.setAccountType(at);
        
        accountService.saveAccount(inactiveAccount);
        
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = account.getEmail();
        String un = inactiveAccount.getUsername();
        String pw = account.getPassword();
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertTrue(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //short password
    public void testValidateEditAccountRequest_14() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = account.getEmail();
        String un = account.getUsername();
        String pw = "e";
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
    @Test //long password
    public void testValidateEditAccountRequest_15() {
        Account account = createNewAccount1();
        account = accountService.saveAccount(account);
                
        int aID = account.getAccountnumber();
        String fn = account.getFirstName();
        String ln = account.getLastName();
        String em = account.getEmail();
        String un = account.getUsername();
        String pw = "longpasswordlongpasswordlongpasswordlongpassword";
        int rID = account.getRegion().getRegionid();
        int atID = account.getAccountType().getAccounttypeid();
        
        RBEditAccount rb = createRBEditAccount(aID, fn, ln, em, un, pw, rID, atID);
        
        assertFalse(accountService.validateEditAccountRequest(rb));
    }
    
}
