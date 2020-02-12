/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.repository;

import bl.thesymmetrypodcast.entity.Region;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Boone
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RegionRepositoryTest {
    
    @Autowired
    RegionRepository regionRepo;
    
    public RegionRepositoryTest() {
    }
    
    //TESTS --------------------------------------------------------------------
    //--------------------------------------------------------------------------

    @Test
    public void testFindById() {
        Optional<Region> region = regionRepo.findById(2);
        assertFalse(region.isEmpty());
    }
    
    @Test
    public void testFindAll() {
        List<Region> myList = regionRepo.findAll();
        assertFalse(myList.isEmpty());
    }
    
    @Test
    public void testExistsById_1() {
        assertTrue(regionRepo.existsById(1));
    }
    
    @Test
    public void testExistsById_2() {
        assertFalse(regionRepo.existsById(100));
    }
    
}
