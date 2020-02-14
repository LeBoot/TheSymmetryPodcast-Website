/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

/**
 *
 * @author Boone
 */
public interface MailService {
    
    public String sendEmail(String recipientAddress, String recipientName) throws Exception;
    
}
