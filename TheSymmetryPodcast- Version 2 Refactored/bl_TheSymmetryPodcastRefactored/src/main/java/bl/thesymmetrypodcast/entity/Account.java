/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.entity;

import java.time.LocalDate;
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
@Table(name = "accounttbl")
@Data
public class Account {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int accountnumber;
    
    @Column(name = "firstname")
    private String firstName;
    
    @Column(name = "lastname")
    private String lastName;
    
    @Column(name = "myemail")
    private String email;
    
    @Column(name = "myusername")
    private String username;
    
    @Column(name = "mypassword")
    private String password;
    
    @Column(name = "startdate")
    private LocalDate startDate;
    
    @ManyToOne
    @JoinColumn(name = "regionid")
    private Region region;
    
    @ManyToOne
    @JoinColumn(name = "accounttypeid")
    private AccountType accountType;
    
}
