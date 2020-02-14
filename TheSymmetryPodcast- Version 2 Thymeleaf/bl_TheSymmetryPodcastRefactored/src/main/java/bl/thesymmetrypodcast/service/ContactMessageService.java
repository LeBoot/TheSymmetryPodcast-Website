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
    public ContactMessage save(ContactMessage message);
    public List<ContactMessage> getAllMessages();
    public ContactMessage getMessageById(int messageId);
    public List<ContactMessage> getMessagesByStatus(int contactStatusId);
    
    //VALIDATION ---------------------------------------------------------------
    public boolean validateNewMessageRB(RBNewMessage message);
    public boolean validateEditMessageNoteRB(RBEditMessageNote message);
    public boolean validateEditMessageStatusRB(RBEditMessageStatus message);
}
