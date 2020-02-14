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
 
//    @Query(value = "SELECT b.* FROM buyer b INNER JOIN `user` u ON b.user_id = u.user_id WHERE u.user_id = ?1", nativeQuery = true)
//    Buyer findByUserId(int userId);
    
//    @Query(value = "SELECT * FROM ContactMessagesTbl WHERE ContactStatusID = 1", nativeQuery = true)
//    public List<ContactMessage> findByStatus1();
//    
//    @Query(value = "SELECT * FROM ContactMessagesTbl WHERE ContactStatusID = 2", nativeQuery = true)
//    public List<ContactMessage> findByStatus2();
//    
//    @Query(value = "SELECT * FROM ContactMessagesTbl WHERE ContactStatusID = 3", nativeQuery = true)
//    public List<ContactMessage> findByStatus3();
    
    @Query(value = "SELECT * FROM ContactMessagesTbl WHERE ContactStatusID = ?1", nativeQuery = true)
    List<ContactMessage> findByStatus(int contactStatusID);
    
}
