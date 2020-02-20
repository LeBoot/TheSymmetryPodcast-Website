/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.repository;

import bl.thesymmetrypodcast.entity.MP3;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class MP3RepositoryTest {
    
    @Autowired
    MP3Repository repo;
    
    public MP3RepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<MP3> myList = repo.findAll();
        for (MP3 mp3 : myList) {
            repo.delete(mp3);
        }
    }
    
    @After
    public void tearDown() {
    }

    //HELPER METHODS -----------------------------------------------------------
    //--------------------------------------------------------------------------
    private MP3 createMP3_1() {
        MP3 episode = new MP3();
        
        episode.setEpisodeTitle("title");
        episode.setEpisodeLink("link.html");
        episode.setEpisodeDate("September 1, 2019");
        episode.setEpisodeDescription("text");
       
        return episode;
    }
    
    private MP3 createMP3_2() {
        MP3 episode = new MP3();
        
        episode.setEpisodeTitle("another title");
        episode.setEpisodeLink("another link.html");
        episode.setEpisodeDate("September 2, 2019");
        episode.setEpisodeDescription("more text");
       
        return episode;
    }
    
    //TESTS --------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    @Test
    public void testFindByIdANDSave() {
        MP3 episode = createMP3_1();
        episode = repo.save(episode);
        
        int episodeKey = episode.getEpisodekey();
        
        Optional<MP3> fromDB = repo.findById(episodeKey);

        assertEquals(episode, fromDB.get());   
    }
    
    @Test
    public void testFindAll_1() {
        MP3 episode = createMP3_1();
        episode = repo.save(episode);
        
        MP3 episode2 = createMP3_2();
        episode = repo.save(episode2);
        
        List<MP3> myList = repo.findAll();
        
        assertEquals(2, myList.size());        
    }
    
    @Test
    public void testFindAll_2() {
        MP3 episode = createMP3_1();
        repo.save(episode);
        
        List<MP3> myList = repo.findAll();
        
        assertTrue(myList.contains(episode));        
    }
    
    @Test
    public void testDelete() {
        MP3 episode = createMP3_1();
        episode = repo.save(episode);
        
        int episodeKey = episode.getEpisodekey();
        
        Optional<MP3> fromDBopt = repo.findById(episodeKey);
        MP3 fromDB = fromDBopt.get();
        
        assertNotNull(fromDB);
        
        repo.delete(fromDB);
        
        Optional<MP3> deletedEpisode = repo.findById(episodeKey);
        
        assertTrue(deletedEpisode.isEmpty());   
    }
    
}
