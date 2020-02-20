/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.repository;

import bl.thesymmetrypodcast.entity.ContactMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Boone
 */
@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Integer> {
    
    @Query(value = "SELECT * FROM ContactMessagesTbl WHERE ContactStatusID = ?1", nativeQuery = true)
    List<ContactMessage> findByStatus(int contactStatusID);
    
}
