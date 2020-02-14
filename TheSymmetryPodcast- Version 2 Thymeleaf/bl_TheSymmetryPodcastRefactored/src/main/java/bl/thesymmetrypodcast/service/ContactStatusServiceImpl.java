/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.ContactStatus;
import bl.thesymmetrypodcast.repository.ContactStatusRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boone
 */
@Service
public class ContactStatusServiceImpl implements ContactStatusService {
    
    @Autowired
    ContactStatusRepository csRepo;
    
    @Override
    public ContactStatus getStatusById(int contactStatusId) {
        Optional<ContactStatus> csOpt = csRepo.findById(contactStatusId);
        if (csOpt.isPresent()) {
            return csOpt.get();
        } else {
            return null;
        }
    }
}
