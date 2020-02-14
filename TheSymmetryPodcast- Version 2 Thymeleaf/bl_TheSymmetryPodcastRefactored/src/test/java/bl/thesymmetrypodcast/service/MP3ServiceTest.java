/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.MP3;
import bl.thesymmetrypodcast.repository.MP3Repository;
import bl.thesymmetrypodcast.requestBody.RBNewMP3;
import java.util.List;
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
public class MP3ServiceTest {
    
    @Autowired
    MP3ServiceImpl service;
    
    @Autowired
    MP3Repository mp3repo;
    
    public MP3ServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        List<MP3> myList = mp3repo.findAll();
        for (MP3 mp3 : myList) {
            mp3repo.delete(mp3);
        }
    }
    
    @After
    public void tearDown() {
    }

    //HELPER METHODS -----------------------------------------------------------
    //--------------------------------------------------------------------------
    private MP3 createMP3() {
        MP3 mp3 = new MP3();
        
        mp3.setEpisodeTitle("title");
        mp3.setEpisodeLink("somelink");
        mp3.setEpisodeDescription("description");
        mp3.setEpisodeDate("September 25, 2020");
        
        return mp3;
    }
    
    private MP3 createCustomMP3(String title, String link, String description, String date) {
        MP3 mp3 = new MP3();
        
        mp3.setEpisodeTitle(title);
        mp3.setEpisodeLink(link);
        mp3.setEpisodeDescription(description);
        mp3.setEpisodeDate(date);
        
        return mp3;
    }
    
    private RBNewMP3 createRBNewMP3(String title, String link, String description, String date) {
        RBNewMP3 mp3 = new RBNewMP3();
        
        mp3.setEpisodeTitle(title);
        mp3.setEpisodeLink(link);
        mp3.setEpisodeDescription(description);
        mp3.setEpisodeDate(date);
        
        return mp3;
    }
    
    //TESTS --------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    //CRUD =====================================================================
    @Test
    public void testGetAllMP3s_1() {
        MP3 mp3_1 = createMP3();
        service.saveMP3(mp3_1);
        
        MP3 mp3_2 = createCustomMP3("title2", "link2", "description2", "December 2, 2002");
        service.saveMP3(mp3_2);
        
        List<MP3> myList = service.getAllMP3s();
        
        assertEquals(2, myList.size());
    }

    @Test
    public void testGetAllMP3s_2() {
        MP3 mp3_1 = createMP3();
        service.saveMP3(mp3_1);
        
        MP3 mp3_2 = createCustomMP3("title2", "link2", "description2", "December 2, 2002");
        service.saveMP3(mp3_2);
        
        List<MP3> myList = service.getAllMP3s();
        
        assertTrue(myList.contains(mp3_1));
    }
    
    @Test
    public void testGetAllMP3s_3() {
        MP3 mp3_1 = createMP3();
        service.saveMP3(mp3_1);
        
        MP3 mp3_2 = createCustomMP3("title2", "link2", "description2", "December 2, 2002");
        service.saveMP3(mp3_2);
        
        List<MP3> myList = service.getAllMP3s();
        
        assertTrue(myList.contains(mp3_2));
    }

    @Test
    public void testGetAnMP3ANDSaveMP3() {
        MP3 mp3 = createMP3();
        mp3 = service.saveMP3(mp3);
        
        MP3 fromDB = service.getAnMP3(mp3.getEpisodekey());
        
        assertEquals(fromDB, mp3);
    }

    @Test
    public void testDeleteMP3() {
        MP3 mp3 = createMP3();
        mp3 = service.saveMP3(mp3);
        
        int mp3Key = mp3.getEpisodekey();
        
        MP3 fromDB = service.getAnMP3(mp3Key);
        
        assertTrue(fromDB != null);
        
        service.deleteMP3(fromDB);
        
        MP3 deletedFile = service.getAnMP3(mp3Key);
        
        assertTrue(deletedFile == null);
    }


    @Test
    public void testGetTwoNewestMP3s_1() {
        MP3 mp3_1 = createCustomMP3("title1", "link1", "description1", "December 1, 2001");
        service.saveMP3(mp3_1);
        
        MP3 mp3_2 = createCustomMP3("title2", "link2", "description2", "December 2, 2002");
        service.saveMP3(mp3_2);
        
        MP3 mp3_3 = createCustomMP3("title3", "link3", "description3", "December 3, 2003");
        service.saveMP3(mp3_3);
        
        List<MP3> myList = service.getTwoNewestMP3s();
        
        assertEquals(2, myList.size());        
    }
    
    @Test
    public void testGetTwoNewestMP3s_2() {
        MP3 mp3_1 = createCustomMP3("title1", "link1", "description1", "December 1, 2001");
        service.saveMP3(mp3_1);
        
        MP3 mp3_2 = createCustomMP3("title2", "link2", "description2", "December 2, 2002");
        service.saveMP3(mp3_2);
        
        MP3 mp3_3 = createCustomMP3("title3", "link3", "description3", "December 3, 2003");
        service.saveMP3(mp3_3);
        
        List<MP3> myList = service.getTwoNewestMP3s();
        
        assertFalse(myList.contains(mp3_1));        
    }
    
    @Test
    public void testGetTwoNewestMP3s_3() {
        MP3 mp3_1 = createCustomMP3("title1", "link1", "description1", "December 1, 2001");
        service.saveMP3(mp3_1);
        
        MP3 mp3_2 = createCustomMP3("title2", "link2", "description2", "December 2, 2002");
        service.saveMP3(mp3_2);
        
        MP3 mp3_3 = createCustomMP3("title3", "link3", "description3", "December 3, 2003");
        service.saveMP3(mp3_3);
        
        List<MP3> myList = service.getTwoNewestMP3s();
        
        assertTrue(myList.contains(mp3_2));        
    }
    
    @Test
    public void testGetTwoNewestMP3s_4() {
        MP3 mp3_1 = createCustomMP3("title1", "link1", "description1", "December 1, 2001");
        service.saveMP3(mp3_1);
        
        MP3 mp3_2 = createCustomMP3("title2", "link2", "description2", "December 2, 2002");
        service.saveMP3(mp3_2);
        
        MP3 mp3_3 = createCustomMP3("title3", "link3", "description3", "December 3, 2003");
        service.saveMP3(mp3_3);
        
        List<MP3> myList = service.getTwoNewestMP3s();
        
        assertTrue(myList.contains(mp3_3));        
    }

    //VALIDATION ===============================================================
    
    /**
     * Test of validateInput method --------------------------------------------
     */
    @Test //good input
    public void testValidateInput_1() {
        String title = "title";
        String link = "link";
        String description = "description";
        String date = "December 1, 2002";
        
        MP3 mp3 = createCustomMP3(title, link, description, date);
        assertTrue(service.validateInput(mp3));
    }
    
    @Test //short link
        public void testValidateInput_2() {
        String title = "title";
        String link = "l";
        String description = "description";
        String date = "December 1, 2002";
        
        MP3 mp3 = createCustomMP3(title, link, description, date);
        assertFalse(service.validateInput(mp3));
    }
        
    @Test //long link
    public void testValidateInput_3() {
        String text = "e";
        for (int i = 0; i < 60; i++) {
            text += "e";
        }
            
        String title = "title";
        String link = text;
        String description = "description";
        String date = "December 1, 2002";
        
        MP3 mp3 = createCustomMP3(title, link, description, date);
        assertFalse(service.validateInput(mp3));
    }
    
    @Test //short title
        public void testValidateInput_4() {
        String title = "t";
        String link = "link";
        String description = "description";
        String date = "December 1, 2002";
        
        MP3 mp3 = createCustomMP3(title, link, description, date);
        assertFalse(service.validateInput(mp3));
    }
        
    @Test //long title
    public void testValidateInput_5() {
        String text = "e";
        for (int i = 0; i < 50; i++) {
            text += "e";
        }
            
        String title = text;
        String link = "link";
        String description = "description";
        String date = "December 1, 2002";
        
        MP3 mp3 = createCustomMP3(title, link, description, date);
        assertFalse(service.validateInput(mp3));
    }
    
    @Test //short description
        public void testValidateInput_6() {
        String title = "title";
        String link = "link";
        String description = "d";
        String date = "December 1, 2002";
        
        MP3 mp3 = createCustomMP3(title, link, description, date);
        assertFalse(service.validateInput(mp3));
    }
        
    @Test //long description
    public void testValidateInput_7() {
        String text = "e";
        for (int i = 0; i < 5000; i++) {
            text += "e";
        }
            
        String title = "title";
        String link = "link";
        String description = text;
        String date = "December 1, 2002";
        
        MP3 mp3 = createCustomMP3(title, link, description, date);
        assertFalse(service.validateInput(mp3));
    }
    
    @Test //short date
        public void testValidateInput_8() {
        String title = "title";
        String link = "link";
        String description = "description";
        String date = "D";
        
        MP3 mp3 = createCustomMP3(title, link, description, date);
        assertFalse(service.validateInput(mp3));
    }
        
    @Test //long date
    public void testValidateInput_9() {
        String text = "e";
        for (int i = 0; i < 25; i++) {
            text += "e";
        }
            
        String title = "title";
        String link = "link";
        String description = "description";
        String date = text;
        
        MP3 mp3 = createCustomMP3(title, link, description, date);
        assertFalse(service.validateInput(mp3));
    }
    
    /**
     * Test of validateInputForNewMP3 method -----------------------------------
     */
    @Test //good input
    public void testValidateInputForNewMP3_1() {
        MP3 presentMP3 = createMP3();
        service.saveMP3(presentMP3);
        
        String title = "title";
        String link = presentMP3.getEpisodeLink() + "link"; //different link
        String description = "description";
        String date = "December 1, 2002";
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertTrue(service.validateInputForNewMP3(mp3));
    }
    
    @Test //short link
    public void testValidateInputForNewMP3_2() {        
        String title = "title";
        String link = "l";
        String description = "description";
        String date = "December 1, 2002";
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertFalse(service.validateInputForNewMP3(mp3));
    }
        
    @Test //long link
    public void testValidateInputForNewMP3_3() {
        String text = "e";
        for (int i = 0; i < 60; i++) {
            text += "e";
        }
        
        String title = "title";
        String link = text;
        String description = "description";
        String date = "December 1, 2002";
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertFalse(service.validateInputForNewMP3(mp3));
    }
    
    @Test //duplicate link
    public void testValidateInputForNewMP3_4() {
        MP3 presentMP3 = createMP3();
        service.saveMP3(presentMP3);
        
        String title = "title";
        String link = presentMP3.getEpisodeLink();
        String description = "description";
        String date = "December 1, 2002";
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertFalse(service.validateInputForNewMP3(mp3));
    }

    @Test //short title
    public void testValidateInputForNewMP3_5() {        
        String title = "t";
        String link = "link";
        String description = "description";
        String date = "December 1, 2002";
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertFalse(service.validateInputForNewMP3(mp3));
    }
        
    @Test //long title
    public void testValidateInputForNewMP3_6() {
        String text = "e";
        for (int i = 0; i < 50; i++) {
            text += "e";
        }
        
        String title = text;
        String link = "link";
        String description = "description";
        String date = "December 1, 2002";
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertFalse(service.validateInputForNewMP3(mp3));
    }
    
    @Test //short date
    public void testValidateInputForNewMP3_7() {        
        String title = "title";
        String link = "link";
        String description = "description";
        String date = "D";
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertFalse(service.validateInputForNewMP3(mp3));
    }
        
    @Test //long date
    public void testValidateInputForNewMP3_8() {
        String text = "e";
        for (int i = 0; i < 25; i++) {
            text += "e";
        }
        
        String title = "title";
        String link = "link";
        String description = "description";
        String date = text;
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertFalse(service.validateInputForNewMP3(mp3));
    }
    
    @Test //short date
    public void testValidateInputForNewMP3_9() {        
        String title = "title";
        String link = "link";
        String description = "d";
        String date = "December 1, 2002";
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertFalse(service.validateInputForNewMP3(mp3));
    }
        
    @Test //long date
    public void testValidateInputForNewMP3_10() {
        String text = "e";
        for (int i = 0; i < 5000; i++) {
            text += "e";
        }
        
        String title = "title";
        String link = "link";
        String description = text;
        String date = "December 1, 2002";
        
        RBNewMP3 mp3 = createRBNewMP3(title, link, description, date);
        
        assertFalse(service.validateInputForNewMP3(mp3));
    }
    
}
