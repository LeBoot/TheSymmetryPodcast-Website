/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.repository;

import bl.thesymmetrypodcast.entity.ContactStatus;
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
public class ContactStatusRepositoryTest {
    
    @Autowired
    ContactStatusRepository statusRepo;
    
    public ContactStatusRepositoryTest() {
    }

    //TESTS --------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    @Test
    public void testFindById() {
        Optional<ContactStatus> type = statusRepo.findById(2);
        assertFalse(type.isEmpty());
    }
    
    @Test
    public void testFindAll() {
        List<ContactStatus> myList = statusRepo.findAll();
        assertFalse(myList.isEmpty());
    }
    
}
