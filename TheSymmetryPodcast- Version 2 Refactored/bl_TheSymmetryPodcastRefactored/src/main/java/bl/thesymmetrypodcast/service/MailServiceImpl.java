/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bl.thesymmetrypodcast.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boone
 */
@Service
public class MailServiceImpl implements MailService {
    
    @Override
    public String sendEmail(String recipientAddress, String recipientName) throws Exception {
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "BenLeBoot@gmail.com";
        String myPassword = "password";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myPassword);
            }
        });
        
        String subject = "DO NOT REPLY: The Symmetry Podcast: Account Password Reset";
        
        String newPassword = autogeneratePassword();
        
        String text = "This is an autogenerated email from TheSymmetryPodcast.com.  Do not reply."
                + "\n Your account has been reset."
                + "\n Your username is still " + recipientName
                + "\n Your temporary password is " + newPassword;
        
        Message message = prepareMessage(session, myAccountEmail, recipientAddress, subject, text);
        
        Transport.send(message);
        
        return newPassword;
    }
    
    private String autogeneratePassword() {
        String[] numericCharacters = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String[] alphabeticCharacters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String[] mixedCharacters = new String[6];
        
        String[] numericCharactersShuffled = shuffleArray(numericCharacters);
        String[] alphabeticCharactersShuffled = shuffleArray(alphabeticCharacters);
        
        mixedCharacters[0] = numericCharactersShuffled[0];
        mixedCharacters[1] = numericCharactersShuffled[1];
        mixedCharacters[2] = alphabeticCharactersShuffled[2];
        mixedCharacters[3] = alphabeticCharactersShuffled[3];
        mixedCharacters[4] = alphabeticCharactersShuffled[4];
        mixedCharacters[5] = alphabeticCharactersShuffled[5];
        
        String[] mixedCharactersShuffled = shuffleArray(mixedCharacters);
        
        String newPassword;
        newPassword = mixedCharactersShuffled[0];
        newPassword += mixedCharactersShuffled[1];
        newPassword += mixedCharactersShuffled[2];
        newPassword += mixedCharactersShuffled[3];
        newPassword += mixedCharactersShuffled[4];
        newPassword += mixedCharactersShuffled[5];
        
        return newPassword;        
    }
    
    private String[] shuffleArray(String[] array) {
        List<String> arrayAsList = Arrays.asList(array);
        Collections.shuffle(arrayAsList);
        arrayAsList.toArray(array);
        return array;
    }    
    
    private Message prepareMessage(Session session, String myAccountEmail, String recipientAddress, String subject, String text) throws Exception {
        Message message = new MimeMessage(session);
      
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));
        message.setSubject(subject);
        message.setText(text);
        return message;
    }
    
}
