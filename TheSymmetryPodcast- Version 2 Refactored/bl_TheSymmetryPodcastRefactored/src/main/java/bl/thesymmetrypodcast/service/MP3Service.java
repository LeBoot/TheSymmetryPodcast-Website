/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.MP3;
import bl.thesymmetrypodcast.requestBody.RBNewMP3;
import java.util.List;

/**
 *
 * @author Boone
 */
public interface MP3Service {
    
    //CRUD --------------------------------------------------------------------- 
    
    /**
     * Returns a list of all MP3s.
     * @return List of MP3s
     */
    public List<MP3> getAllMP3s();
    
    /**
     * Retrieves an MP3 based on the mp3's key.
     * @param key
     * @return MP3
     */
    public MP3 getAnMP3(int key);
    
    /**
     * Saves an MP3 (either new or edited)
     * @param mp3
     * @return MP3
     */
    public MP3 saveMP3(MP3 mp3);
    
    /**
     * Deletes an mp3.
     * @param mp3 
     */
    public void deleteMP3(MP3 mp3);
    
    /**
     * Retrieves a list of the two newest MP3s in the database.
     * <p>
     * It determines the newest not by date but by key (which is auto-incremented)
     * @return List of 2 MP3s
     */
    public List<MP3> getTwoNewestMP3s();
    
    //VALIDATION ---------------------------------------------------------------
    
    /**
     * Validates that an edited mp3's changes meet database criteria
     * @param mp3
     * @return true = data is good; false = data is bad.
     */
    public boolean validateInput(MP3 mp3);
    
    /**
     * Validates that a new mp3 meets database criteria
     * @param mp3
     * @return true = data is good; false = data is bad.
     */
    public boolean validateInputForNewMP3(RBNewMP3 mp3);
    
}
