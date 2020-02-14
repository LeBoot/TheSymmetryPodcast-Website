/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.controller;

import bl.thesymmetrypodcast.entity.ContactMessage;
import bl.thesymmetrypodcast.entity.ContactStatus;
import bl.thesymmetrypodcast.entity.Region;
import bl.thesymmetrypodcast.requestBody.RBEditMessageNote;
import bl.thesymmetrypodcast.requestBody.RBEditMessageStatus;
import bl.thesymmetrypodcast.requestBody.RBNewMessage;
import bl.thesymmetrypodcast.service.ContactMessageServiceImpl;
import bl.thesymmetrypodcast.service.ContactStatusServiceImpl;
import bl.thesymmetrypodcast.service.RegionServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Boone
 */
@Controller
@CrossOrigin
@RequestMapping("/contact")
public class ContactController {
    
    @Autowired
    ContactMessageServiceImpl contactService;
    
    @Autowired
    RegionServiceImpl regionService;
    
    @Autowired
    ContactStatusServiceImpl contactStatService;
    
    //Handle a new message from contact.html -----------------------------------
    @PostMapping("/new-message")
    private ResponseEntity<HttpStatus> addNewMessage(@RequestBody RBNewMessage rbNewMessage) {
        //validate message in service layer
        boolean isRequestValid = contactService.validateNewMessageRB(rbNewMessage);
//        boolean isRequestValid = true;
        //is message is good, save it; else, return an error
        if (isRequestValid == true) {
            //create a new message
            ContactMessage message = new ContactMessage();
            
            //set message fields
            message.setMyName(rbNewMessage.getRbName());
            
            message.setMyEmail(rbNewMessage.getRbEmail());
            
            message.setMessageText(rbNewMessage.getRbMessageText());
            
            LocalDateTime timeOfMessage = LocalDateTime.now();
            LocalDateTime timeWithoutNano = timeOfMessage.withNano(0);
            message.setTimeStamp(timeWithoutNano);
            
            int regionId = rbNewMessage.getRbRegionId();
            Region region = regionService.getRegionById(regionId);
            message.setRegion(region);
            
            ContactStatus cs = contactStatService.getStatusById(1);     
            message.setContactStatus(cs);
            
            //save message
            contactService.save(message);
            
            //return success
            return new ResponseEntity<>(HttpStatus.OK);
            
        } else {
            //return error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    //Return a list of all messages --------------------------------------------
    @GetMapping("/messages/all")
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        List<ContactMessage> listOfMessages = contactService.getAllMessages();
        return new ResponseEntity<>(listOfMessages, HttpStatus.ACCEPTED);
    }
    
    //Return a list of unaddressed messages ------------------------------------
    @GetMapping("/messages/1")
    public ResponseEntity<List<ContactMessage>> getUnaddressedMessages() {
        List<ContactMessage> listOfMessages = contactService.getMessagesByStatus(1);
        if (listOfMessages.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(listOfMessages, HttpStatus.ACCEPTED);
        }
    }
    
    //Return a list of addressed messages --------------------------------------
    @GetMapping("/messages/2")
    public ResponseEntity<List<ContactMessage>> getAddressedMessages() {
        List<ContactMessage> listOfMessages = contactService.getMessagesByStatus(2);
        if (listOfMessages.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(listOfMessages, HttpStatus.ACCEPTED);
        }
    }
    
    //Return a list of tabled messages -----------------------------------------
    @GetMapping("/messages/3")
    public ResponseEntity<List<ContactMessage>> getTabledMessages() {
        List<ContactMessage> listOfMessages = contactService.getMessagesByStatus(3);
        if (listOfMessages.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(listOfMessages, HttpStatus.ACCEPTED);
        }
    }
    
    //Return a single message based on its Id ----------------------------------
    @GetMapping("/messages/get-by-id/{messageId}")
    public ResponseEntity<ContactMessage> getMessageById(@PathVariable int messageId) {
        ContactMessage retrievedMessage = contactService.getMessageById(messageId);
        return new ResponseEntity<>(retrievedMessage, HttpStatus.ACCEPTED);
    }
   
    //Edit a message note, coming from TSPadmin.html ---------------------------
    @PostMapping("/edit-message/note")
    public ResponseEntity<HttpStatus> editMessageNote(@RequestBody RBEditMessageNote message) {
        //validate message in service layer
        boolean isRequestValid = contactService.validateEditMessageNoteRB(message);
        
        //is message is good, save it; else, return an error
        if (isRequestValid == true) {
            //retrieve message from DB
            ContactMessage messageBeingEdited = contactService.getMessageById(message.getContactId());
            
            //set note
            messageBeingEdited.setNotes(message.getRbNotes());
            
            //save edited message
            contactService.save(messageBeingEdited);
            
            //return success
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            //return error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    //Edit a message status, coming from TSPadmin.html -------------------------
    @PostMapping("/edit-message/status")
    public ResponseEntity<HttpStatus> editMessageStatus(@RequestBody RBEditMessageStatus message) {
        //validate message in service layer
        boolean isRequestValid = contactService.validateEditMessageStatusRB(message);
        
        //is message is good, save it; else, return an error
        if (isRequestValid == true) {
            //retrieve message from DB
            ContactMessage messageBeingEdited = contactService.getMessageById(message.getContactId());
            
            //set status
            ContactStatus cs = contactStatService.getStatusById(message.getRbStatusId());     
            messageBeingEdited.setContactStatus(cs);
            
            //save edited message
            contactService.save(messageBeingEdited);
            
            //return success
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            //return error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}