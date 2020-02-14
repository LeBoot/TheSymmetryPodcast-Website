/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author Boone
 */
@Entity
@Table(name = "contactmessagestbl")
@Data
public class ContactMessage {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int contactid;
    
    @Column(name = "myname", nullable = false)
    private String myName;
    
    @Column(name = "myemail", nullable = false)
    private String myEmail;
    
    @Column(name = "message", nullable = false)
    private String messageText;
    
    @Column(name = "notes", nullable = true)
    private String notes;
    
    @Column(name = "mytimestamp", nullable = false)
    private LocalDateTime timeStamp;
    
    @ManyToOne
    @JoinColumn(name = "regionid")
    private Region region;
    
    @ManyToOne
    @JoinColumn(name = "contactstatusid")
    private ContactStatus contactStatus;
    
}