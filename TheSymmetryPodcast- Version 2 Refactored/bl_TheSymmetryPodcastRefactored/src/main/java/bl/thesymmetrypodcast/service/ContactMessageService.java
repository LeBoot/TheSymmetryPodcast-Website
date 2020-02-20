/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.ContactMessage;
import bl.thesymmetrypodcast.requestBody.RBEditMessageNote;
import bl.thesymmetrypodcast.requestBody.RBEditMessageStatus;
import bl.thesymmetrypodcast.requestBody.RBNewMessage;
import java.util.List;

/**
 *
 * @author Boone
 */
public interface ContactMessageService {
    
    //CRUD ---------------------------------------------------------------------
    
    /**
     * Saves a message (either edited or new) and then returns that message.
     * @param message
     * @return ContactMessage
     */
    public ContactMessage save(ContactMessage message);
    
    /**
     * Returns a list of all the contact messages
     * @return List of Contact Messages
     */
    public List<ContactMessage> getAllMessages();
    
    /**
     * Retrieves a message based on Id.
     * @param messageId
     * @return Contact Message
     */
    public ContactMessage getMessageById(int messageId);
    
    /**
     * Returns a list of all the contactMessages that have a particular contactStatusID.
     * @param contactStatusId
     * @return List of Contact Messages.
     */
    public List<ContactMessage> getMessagesByStatus(int contactStatusId);
    
    //VALIDATION ---------------------------------------------------------------
    
    /**
     * Validates that a new message has all required fields and that they are within
     * the size constraints of the database.
     * @param message
     * @return true = data is good; false = data is bad.
     */
    public boolean validateNewMessageRB(RBNewMessage message);
    
    /**
     * Validates that an edited message has all required fields and that they are
     * within the size constraints of the database.
     * @param message
     * @return true = data is good; false = data is bad.
     */
    public boolean validateEditMessageNoteRB(RBEditMessageNote message);
    
    /**
     * Validates that an edited message has all required fields and that they are
     * within the size constraints of the database.
     * @param message
     * @return true = data is good; false = data is bad.
     */
    public boolean validateEditMessageStatusRB(RBEditMessageStatus message);
}
