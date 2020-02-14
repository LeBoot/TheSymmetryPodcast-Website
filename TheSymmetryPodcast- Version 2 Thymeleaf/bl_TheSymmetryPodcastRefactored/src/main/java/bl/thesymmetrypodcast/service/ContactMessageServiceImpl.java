/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.ContactMessage;
import bl.thesymmetrypodcast.entity.ContactStatus;
import bl.thesymmetrypodcast.repository.ContactMessageRepository;
import bl.thesymmetrypodcast.repository.ContactStatusRepository;
import bl.thesymmetrypodcast.repository.RegionRepository;
import bl.thesymmetrypodcast.requestBody.RBEditMessageNote;
import bl.thesymmetrypodcast.requestBody.RBEditMessageStatus;
import bl.thesymmetrypodcast.requestBody.RBNewMessage;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boone
 */
@Service
public class ContactMessageServiceImpl implements ContactMessageService {
    
    @Autowired
    ContactMessageRepository contactRepo;
    
    @Autowired
    RegionRepository regionRepo;
    
    @Autowired
    ContactStatusRepository contactStatusRepo;
    
    //CRUD ---------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    @Override
    public ContactMessage save(ContactMessage message) {
        return contactRepo.save(message);
    }
    
    @Override
    public List<ContactMessage> getAllMessages() {
        return contactRepo.findAll();
    }
    
    @Override
    public ContactMessage getMessageById(int messageId) {
        Optional<ContactMessage> messageOpt = contactRepo.findById(messageId);
        return messageOpt.get();
    }
    
    @Override
    public List<ContactMessage> getMessagesByStatus(int contactStatusId) {
        return contactRepo.findByStatus(contactStatusId);
    }
    
    //VALIDATION ---------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    @Override
    public boolean validateNewMessageRB(RBNewMessage message) {
        //create return boolean
        boolean isMessageValid = true;

        //validate length of name
        String name = message.getRbName();
        if ((name.length() > 50) || (name.length() < 2)) {
            isMessageValid = false;
        }
        
        //validate length of email and presence of "@"
        String email = message.getRbEmail();
        if ((email.length() > 50) || (email.length() < 4)) {
            isMessageValid = false;
        }
        if (!email.contains("@")) {
            isMessageValid = false;
        }
        
        //validate message length
        String messageText = message.getRbMessageText();
        if ((messageText.length() > 5000) || (messageText.length() < 2)) {
            isMessageValid = false;
        }
        
        //validate regionId
        int regionId = message.getRbRegionId();
        boolean isRegionValid = regionRepo.existsById(regionId);
        if (isRegionValid == false) {
            isMessageValid = false;
        }
        
        //return final assessment
        return isMessageValid;
    }
    
    @Override
    public boolean validateEditMessageNoteRB(RBEditMessageNote message) {
        //create return boolean
        boolean isMessageValid = true;

        //validate message exists
        int messageId = message.getContactId();
        Optional<ContactMessage> testMessageOpt = contactRepo.findById(messageId);
        if (testMessageOpt.isEmpty()) {
            isMessageValid = false;
        }
        
        //validate notes length
        String messageNotes = message.getRbNotes();
        if (messageNotes.length() > 5000) {
            isMessageValid = false;
        }
        
        //return final assessment
        return isMessageValid; 
    }
    
    @Override
    public boolean validateEditMessageStatusRB(RBEditMessageStatus message) {
        //create return boolean
        boolean isMessageValid = true;

        //validate message exists
        int messageId = message.getContactId();
        Optional<ContactMessage> testMessageOpt = contactRepo.findById(messageId);
        if (testMessageOpt.isEmpty()) {
            isMessageValid = false;
        }
        
        //validate statusId exists
        int statusId = message.getRbStatusId();
        Optional<ContactStatus> cs = contactStatusRepo.findById(statusId);
        if (cs.isEmpty()) {
            isMessageValid = false;
        }        
        
        //return final assessment
        return isMessageValid; 
    }
}
