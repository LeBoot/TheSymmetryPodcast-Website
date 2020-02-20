/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.repository;

import bl.thesymmetrypodcast.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Boone
 */
@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    
}