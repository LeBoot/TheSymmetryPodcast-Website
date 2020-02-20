/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.repository;

import bl.thesymmetrypodcast.entity.ContactMessage;
import bl.thesymmetrypodcast.entity.ContactStatus;
import bl.thesymmetrypodcast.entity.Region;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
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
public class ContactMessageRepositoryTest {
    
    @Autowired
    ContactMessageRepository contactRepo;
    
    @Autowired
    RegionRepository regionRepo;
    
    @Autowired
    ContactStatusRepository statusRepo;
    
    public ContactMessageRepositoryTest() {
    }
    
    @Before
    public void setUp() {
        List<ContactMessage> myList = contactRepo.findAll();
        for (ContactMessage message : myList) {
            contactRepo.delete(message);
        }
    }

    
    //HELPER METHODS -----------------------------------------------------------
    //--------------------------------------------------------------------------
    
    private ContactMessage createMessage(int contactStatusId) {
        ContactMessage message = new ContactMessage();
        
        message.setMyName("name");
        message.setMyEmail("email@email.com");
        message.setMessageText("text");
        message.setTimeStamp(LocalDateTime.now().withNano(0));
        
        Optional<Region> region = regionRepo.findById(1);
        message.setRegion(region.get());
        
        Optional<ContactStatus> status = statusRepo.findById(contactStatusId);
        message.setContactStatus(status.get());
        
        return message;
    }
    

    //TESTS --------------------------------------------------------------------
    //--------------------------------------------------------------------------
    @Test
    public void testFindByIdANDSave() {
        ContactMessage message = createMessage(1);
        message = contactRepo.save(message);
        
        int messageId = message.getContactid();
        
        Optional<ContactMessage> fromDB = contactRepo.findById(messageId);
        
        assertEquals(message, fromDB.get());
    }
    
    @Test
    public void testFindAll_1() {
        ContactMessage message = createMessage(1);
        contactRepo.save(message);
        
        ContactMessage message2 = createMessage(2);
        contactRepo.save(message2);
        
        List<ContactMessage> myList = contactRepo.findAll();
        
        assertEquals(2, myList.size());
    }  
    
    @Test
    public void testFindAll_2() {
        ContactMessage message = createMessage(1);
        contactRepo.save(message);
        
        ContactMessage message2 = createMessage(2);
        contactRepo.save(message2);
        
        List<ContactMessage> myList = contactRepo.findAll();
        
        assertTrue(myList.contains(message));
    }   
    
    @Test
    public void testFindAll_3() {
        ContactMessage message = createMessage(1);
        contactRepo.save(message);
        
        ContactMessage message2 = createMessage(2);
        contactRepo.save(message2);
        
        List<ContactMessage> myList = contactRepo.findAll();
        
        assertTrue(myList.contains(message2));
    }   
    
    
    @Test
    public void testFindByStatus_1() {
        ContactMessage message1 = createMessage(1);
        message1 = contactRepo.save(message1);
        
        List<ContactMessage> listOfMessages = contactRepo.findByStatus(1);
        
        assertTrue(listOfMessages.contains(message1));
    }
    
    @Test
    public void testFindByStatus_2() {
        ContactMessage message1 = createMessage(2);
        contactRepo.save(message1);
        
        List<ContactMessage> listOfMessages = contactRepo.findByStatus(1);
        
        assertFalse(listOfMessages.contains(message1));
    }
    
    @Test
    public void testFindByStatus_3() {
        ContactMessage message1 = createMessage(1);
        contactRepo.save(message1);
        
        ContactMessage message2 = createMessage(1);
        contactRepo.save(message2);
        
        ContactMessage message3 = createMessage(2);
        contactRepo.save(message3);
        
        List<ContactMessage> listOfMessages = contactRepo.findByStatus(1);
        
        assertEquals(2, listOfMessages.size());
    }
    
    @Test
    public void testFindByStatus_4() {
        ContactMessage message1 = createMessage(2);
        contactRepo.save(message1);
        
        List<ContactMessage> listOfMessages = contactRepo.findByStatus(2);
        
        assertTrue(listOfMessages.contains(message1));
    }
    
    @Test
    public void testFindByStatus_5() {
        ContactMessage message1 = createMessage(1);
        contactRepo.save(message1);
        
        List<ContactMessage> listOfMessages = contactRepo.findByStatus(2);
        
        assertFalse(listOfMessages.contains(message1));
    }
    
    @Test
    public void testFindByStatus_6() {
        ContactMessage message1 = createMessage(1);
        contactRepo.save(message1);
        
        ContactMessage message2 = createMessage(1);
        contactRepo.save(message2);
        
        ContactMessage message3 = createMessage(2);
        contactRepo.save(message3);
        
        List<ContactMessage> listOfMessages = contactRepo.findByStatus(2);
        
        assertEquals(1, listOfMessages.size());
    }
    
    @Test
    public void testFindByStatus_7() {
        ContactMessage message1 = createMessage(3);
        contactRepo.save(message1);
        
        List<ContactMessage> listOfMessages = contactRepo.findByStatus(3);
        
        assertTrue(listOfMessages.contains(message1));
    }
    
    @Test
    public void testFindByStatus_8() {
        ContactMessage message1 = createMessage(1);
        contactRepo.save(message1);
        
        List<ContactMessage> listOfMessages = contactRepo.findByStatus(3);
        
        assertFalse(listOfMessages.contains(message1));
    }
    
    @Test
    public void testFindByStatus_9() {
        ContactMessage message1 = createMessage(3);
        contactRepo.save(message1);
        
        ContactMessage message2 = createMessage(3);
        contactRepo.save(message2);
        
        ContactMessage message3 = createMessage(2);
        contactRepo.save(message3);
        
        List<ContactMessage> listOfMessages = contactRepo.findByStatus(3);
        
        assertEquals(2, listOfMessages.size());
    }
}
