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
    public List<MP3> getAllMP3s();
    public MP3 getAnMP3(int key);
    public MP3 saveMP3(MP3 mp3);
    public void deleteMP3(MP3 mp3);
    public List<MP3> getTwoNewestMP3s();
    
    //VALIDATION ---------------------------------------------------------------
    public boolean validateInput(MP3 mp3);
    public boolean validateInputForNewMP3(RBNewMP3 mp3);
    
}
