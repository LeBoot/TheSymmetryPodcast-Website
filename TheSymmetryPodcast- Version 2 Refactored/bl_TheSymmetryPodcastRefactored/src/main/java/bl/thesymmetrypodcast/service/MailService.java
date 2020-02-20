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
    
    /**
     * Send an email to the recipientAddress
     * @param recipientAddress
     * @param recipientName
     * @return auto-generated password
     * @throws Exception 
     */
    public String sendEmail(String recipientAddress, String recipientName) throws Exception;
    
}
