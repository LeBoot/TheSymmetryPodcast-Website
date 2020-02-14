/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.ContactStatus;

/**
 *
 * @author Boone
 */
public interface ContactStatusService {
    
    public ContactStatus getStatusById(int contactStatusId);
    
}
