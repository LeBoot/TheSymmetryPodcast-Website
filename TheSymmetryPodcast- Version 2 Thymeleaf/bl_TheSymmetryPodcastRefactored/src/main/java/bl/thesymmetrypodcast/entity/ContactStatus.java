/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Boone
 */
@Entity
@Table(name = "contactstatustbl")
@Data
public class ContactStatus {
    
    @Id
    private int contactstatusid;
    
    @Column(name = "contactstatusname")
    private String contactStatusName;
    
}