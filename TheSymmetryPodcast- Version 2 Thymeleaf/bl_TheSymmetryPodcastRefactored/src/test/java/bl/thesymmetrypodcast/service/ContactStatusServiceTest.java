/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.ContactStatus;
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
public class ContactStatusServiceTest {
    
    @Autowired
    ContactStatusServiceImpl csService;
    
    public ContactStatusServiceTest() {
    }

    //TESTS ====================================================================
    @Test
    public void testGetStatusById_1() {
        ContactStatus returnedStatus = csService.getStatusById(1);
        assertTrue(returnedStatus != null);
    }
    
    @Test
    public void testGetStatusById_2() {
        ContactStatus returnedStatus = csService.getStatusById(100);
        assertTrue(returnedStatus == null);
    }
    
}
