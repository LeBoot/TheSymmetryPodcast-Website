/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.ContactMessage;
import bl.thesymmetrypodcast.entity.ContactStatus;
import bl.thesymmetrypodcast.entity.Region;
import bl.thesymmetrypodcast.repository.ContactMessageRepository;
import bl.thesymmetrypodcast.repository.ContactStatusRepository;
import bl.thesymmetrypodcast.repository.RegionRepository;
import bl.thesymmetrypodcast.requestBody.RBEditMessageNote;
import bl.thesymmetrypodcast.requestBody.RBEditMessageStatus;
import bl.thesymmetrypodcast.requestBody.RBNewMessage;
import java.time.LocalDateTime;
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
public class ContactMessageServiceTest {
    
    @Autowired
    ContactMessageServiceImpl cmService;
    
    @Autowired
    ContactMessageRepository cmRepo;
    
    @Autowired
    ContactStatusRepository csRepo;
    
    @Autowired
    RegionRepository regionRepo;
    
    public ContactMessageServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<ContactMessage> myList = cmRepo.findAll();
        for (ContactMessage message : myList) {
            cmRepo.delete(message);
        }
    }
    
    @After
    public void tearDown() {
    }

    //HELPER METHODS -----------------------------------------------------------
    //--------------------------------------------------------------------------
    private ContactMessage createNewMessage() {
        ContactMessage message = new ContactMessage();
        
        Optional<ContactStatus> cs = csRepo.findById(1);
        Optional<Region> region = regionRepo.findById(4);
        
        message.setContactStatus(cs.get());
        message.setMessageText("Nice things to say.");
        message.setMyEmail("email@website.com");
        message.setMyName("Name Name");
        message.setRegion(region.get());
        message.setTimeStamp(LocalDateTime.now().withNano(0));
        
        return message;
    }
    
    private ContactMessage createNewMessage2() {
        ContactMessage message = new ContactMessage();
        
        Optional<ContactStatus> cs = csRepo.findById(2);
        Optional<Region> region = regionRepo.findById(8);
        
        message.setContactStatus(cs.get());
        message.setMessageText("Different nice things to say.");
        message.setMyEmail("email2@website.com");
        message.setMyName("Nombre Nombre");
        message.setRegion(region.get());
        message.setTimeStamp(LocalDateTime.now().withNano(0));
        
        return message;
    }
    
    private ContactMessage createNewMessageByStatus(int StatusID) {
        ContactMessage message = new ContactMessage();
        
        Optional<ContactStatus> cs = csRepo.findById(StatusID);
        Optional<Region> region = regionRepo.findById(8);
        
        message.setContactStatus(cs.get());
        message.setMessageText("Different nice things to say.");
        message.setMyEmail("email2@website.com");
        message.setMyName("Nombre Nombre");
        message.setRegion(region.get());
        message.setTimeStamp(LocalDateTime.now().withNano(0));
        
        return message;
    }
    
    private RBNewMessage createNewRBNewMessage() {
        RBNewMessage rb = new RBNewMessage();
        
        rb.setRbName("name");
        rb.setRbEmail("email@website.com");
        rb.setRbMessageText("nice things to say");
        rb.setRbRegionId(7);
        
        return rb;
    }
    
    private RBEditMessageNote createRBEditMessageNote(int messageID, String notes) {
        RBEditMessageNote rb = new RBEditMessageNote();
        
        rb.setContactId(messageID);
        rb.setRbNotes(notes);
        
        return rb;
    }
    
    private RBEditMessageStatus createRBEditMessageStatus(int messageID, int statusID) {
        RBEditMessageStatus rb = new RBEditMessageStatus();
        
        rb.setContactId(messageID);
        rb.setRbStatusId(statusID);
        
        return rb;
    }
    
    // TESTS -------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    //CRUD ---------------------------------------------------------------------
    @Test
    public void testSaveANDGetMessageByID() {
        ContactMessage cm = createNewMessage();
        cm = cmService.save(cm);
        
        ContactMessage fromDB = cmService.getMessageById(cm.getContactid());
        
        assertEquals(cm, fromDB);   
    }

    @Test
    public void testGetAllMessages_1() {
        ContactMessage cm1 = createNewMessage();
        cmService.save(cm1);
        
        ContactMessage cm2 = createNewMessage2();
        cmService.save(cm2);
        
        List<ContactMessage> myList = cmService.getAllMessages();
        
        assertEquals(2, myList.size());
    }
    
    @Test
    public void testGetAllMessages_2() {
        ContactMessage cm1 = createNewMessage();
        cmService.save(cm1);
        
        ContactMessage cm2 = createNewMessage2();
        cmService.save(cm2);
        
        List<ContactMessage> myList = cmService.getAllMessages();
        
        assertTrue(myList.contains(cm1));
    }
    
    @Test
    public void testGetAllMessages_3() {
        ContactMessage cm1 = createNewMessage();
        cmService.save(cm1);
        
        ContactMessage cm2 = createNewMessage2();
        cmService.save(cm2);
        
        List<ContactMessage> myList = cmService.getAllMessages();
        
        assertTrue(myList.contains(cm2));
    }

    @Test
    public void testGetMessagesByStatus_1() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(1);
        
        assertEquals(3, myList.size());        
    }
    
    @Test
    public void testGetMessagesByStatus_2() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(2);
        
        assertEquals(2, myList.size());        
    }
    
    @Test
    public void testGetMessagesByStatus_3() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(3);
        
        assertEquals(1, myList.size());        
    }
    
    @Test
    public void testGetMessagesByStatus_4() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(1);
        
        assertTrue(myList.contains(cmS1_1));        
    }
    
    @Test
    public void testGetMessagesByStatus_5() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(1);
        
        assertFalse(myList.contains(cmS2_1));        
    }
    
    @Test
    public void testGetMessagesByStatus_6() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(1);
        
        assertFalse(myList.contains(cmS3_1));        
    }
    
    @Test
    public void testGetMessagesByStatus_7() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(2);
        
        assertTrue(myList.contains(cmS2_1));        
    }
    
    @Test
    public void testGetMessagesByStatus_8() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(2);
        
        assertFalse(myList.contains(cmS1_1));        
    }
    
    @Test
    public void testGetMessagesByStatus_9() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(2);
        
        assertFalse(myList.contains(cmS3_1));        
    }
    
    @Test
    public void testGetMessagesByStatus_10() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(3);
        
        assertFalse(myList.contains(cmS1_1));        
    }
    
    @Test
    public void testGetMessagesByStatus_11() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(3);
        
        assertFalse(myList.contains(cmS2_1));        
    }
    
    @Test
    public void testGetMessagesByStatus_12() {
        ContactMessage cmS1_1 = createNewMessageByStatus(1);
        cmService.save(cmS1_1);
        
        ContactMessage cmS1_2 = createNewMessageByStatus(1);
        cmService.save(cmS1_2);
        
        ContactMessage cmS1_3 = createNewMessageByStatus(1);
        cmService.save(cmS1_3);
        
        ContactMessage cmS2_1 = createNewMessageByStatus(2);
        cmService.save(cmS2_1);
        
        ContactMessage cmS2_2 = createNewMessageByStatus(2);
        cmService.save(cmS2_2);
        
        ContactMessage cmS3_1 = createNewMessageByStatus(3);
        cmService.save(cmS3_1);
        
        List<ContactMessage> myList = cmService.getMessagesByStatus(3);
        
        assertTrue(myList.contains(cmS3_1));        
    }
    
  
    //VALIDATION ---------------------------------------------------------------
    
    /**
     * Test of testValidateNewMessageRB method----------------------------------
     */
    @Test //good info
    public void testValidateNewMessageRB_1() {
        RBNewMessage rb = createNewRBNewMessage();
        assertTrue(cmService.validateNewMessageRB(rb));
    }
    
    @Test //short name
    public void testValidateNewMessageRB_2() {
        RBNewMessage rb = createNewRBNewMessage();
        rb.setRbName("e");
        assertFalse(cmService.validateNewMessageRB(rb));
    }
    
    @Test //long name
    public void testValidateNewMessageRB_3() {
        RBNewMessage rb = createNewRBNewMessage();
        rb.setRbName("longnamelongnamelongnamelongnamelongnamelongnamelongnamelongnamelongname");
        assertFalse(cmService.validateNewMessageRB(rb));
    }

    @Test //short email
    public void testValidateNewMessageRB_4() {
        RBNewMessage rb = createNewRBNewMessage();
        rb.setRbEmail("@.");
        assertFalse(cmService.validateNewMessageRB(rb));
    }
    
    @Test //long email
    public void testValidateNewMessageRB_5() {
        RBNewMessage rb = createNewRBNewMessage();
        rb.setRbEmail("emaill@website.comemaill@website.comemaill@website.comemaill@website.com");
        assertFalse(cmService.validateNewMessageRB(rb));
    }
    
    @Test //email no @
    public void testValidateNewMessageRB_6() {
        RBNewMessage rb = createNewRBNewMessage();
        rb.setRbEmail("emailatwebsite");
        assertFalse(cmService.validateNewMessageRB(rb));
    }
    
    @Test //short message
    public void testValidateNewMessageRB_7() {
        RBNewMessage rb = createNewRBNewMessage();
        rb.setRbMessageText("e");
        assertFalse(cmService.validateNewMessageRB(rb));
    }
    
    @Test //long email
    public void testValidateNewMessageRB_8() {
        RBNewMessage rb = createNewRBNewMessage();
        
        String text = "e";
        for (int i = 0; i < 5000; i++) {
            text += "e";
        }
        
        rb.setRbMessageText(text);
        assertFalse(cmService.validateNewMessageRB(rb));
    }
    
    @Test //bad ID
    public void testValidateNewMessageRB_9() {
        RBNewMessage rb = createNewRBNewMessage();
        rb.setRbRegionId(100);
        assertFalse(cmService.validateNewMessageRB(rb));
    }
    
    /**
     * Test of validateEditMessageNoteRB method---------------------------------
     */
    @Test //good input
    public void testValidateEditMessageNoteRB_1() {
        ContactMessage cm = createNewMessage();
        cm = cmService.save(cm);
        
        RBEditMessageNote rb = createRBEditMessageNote(cm.getContactid(), "notes");
        
        assertTrue(cmService.validateEditMessageNoteRB(rb));
    }
    
    @Test //long note
    public void testValidateEditMessageNoteRB_2() {
        ContactMessage cm = createNewMessage();
        cm = cmService.save(cm);
        
        String text = "e";
        for (int i = 0; i < 5000; i++) {
            text += "e";
        }
        
        RBEditMessageNote rb = createRBEditMessageNote(cm.getContactid(), text);
        
        assertFalse(cmService.validateEditMessageNoteRB(rb));
    }
    
    @Test //nonexistant message
    public void testValidateEditMessageNoteRB_3() {        
        RBEditMessageNote rb = createRBEditMessageNote(2, "notes");
        
        assertFalse(cmService.validateEditMessageNoteRB(rb));
    }    
    
    /**
     * Test of validateEditMessageStatusRB method-------------------------------
     */
    @Test //good input
    public void testValidateEditMessageStatusRB_1() {
        ContactMessage cm = createNewMessage();
        cm = cmService.save(cm);
        
        int messageID = cm.getContactid();
        int statusID = cm.getContactStatus().getContactstatusid();
        
        RBEditMessageStatus rb = createRBEditMessageStatus(messageID, statusID);
        
        assertTrue(cmService.validateEditMessageStatusRB(rb));
    }
    
    @Test //nonexistant message
    public void testValidateEditMessageStatusRB_2() {        
        int messageID = 10;
        int statusID = 1;
        
        RBEditMessageStatus rb = createRBEditMessageStatus(messageID, statusID);
        
        assertFalse(cmService.validateEditMessageStatusRB(rb));
    }
    
    @Test //nonexistant statusID
    public void testValidateEditMessageStatusRB_3() {
        ContactMessage cm = createNewMessage();
        cm = cmService.save(cm);
        
        int messageID = cm.getContactid();
        int statusID = 100;
        
        RBEditMessageStatus rb = createRBEditMessageStatus(messageID, statusID);
        
        assertFalse(cmService.validateEditMessageStatusRB(rb));
    }
    
}
