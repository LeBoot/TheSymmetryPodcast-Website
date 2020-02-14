/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.Region;
import java.util.List;
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
public class RegionServiceTest {
    
    @Autowired
    RegionServiceImpl service;
    
    public RegionServiceTest() {
    }

    //TESTS --------------------------------------------------------------------
    @Test
    public void testGetRegionById_1() {
        Region region = service.getRegionById(1);
        assertTrue(region != null);
    }

    @Test
    public void testGetRegionById_2() {
        Region region = service.getRegionById(100);
        assertTrue(region == null);
    }
    
    @Test
    public void testGetAllRegions() {
        List<Region> myList = service.getAllRegions();
        assertFalse(myList.isEmpty());
    }
    
}
