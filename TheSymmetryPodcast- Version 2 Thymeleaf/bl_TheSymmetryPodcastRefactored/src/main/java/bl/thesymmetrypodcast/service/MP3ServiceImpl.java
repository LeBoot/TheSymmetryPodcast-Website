/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.MP3;
import bl.thesymmetrypodcast.repository.MP3Repository;
import bl.thesymmetrypodcast.requestBody.RBNewMP3;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boone
 */
@Service
public class MP3ServiceImpl implements MP3Service {
    
    @Autowired
    MP3Repository mp3repo;
    
    //CRUD ---------------------------------------------------------------------
    
    @Override
    public List<MP3> getAllMP3s() {
        return mp3repo.findAll();
    }
    
    @Override
    public MP3 getAnMP3(int key) {
        Optional<MP3> thisMP3 = mp3repo.findById(key);
        if (thisMP3.isPresent()) {
            return thisMP3.get();
        } else {
            return null;
        }
    }
    
    @Override
    public MP3 saveMP3(MP3 mp3) {
        return mp3repo.save(mp3);
    }
    
    @Override
    public void deleteMP3(MP3 mp3) {
        mp3repo.delete(mp3);
    }
    
    @Override
    public List<MP3> getTwoNewestMP3s() {
        List<MP3> allMP3s = mp3repo.findAll();
        int numOfEpisodes = allMP3s.size();
        
        List<MP3> newestMP3s = new ArrayList<>();
        newestMP3s.add(allMP3s.get(numOfEpisodes - 1));
        newestMP3s.add(allMP3s.get(numOfEpisodes - 2));
        
        return newestMP3s;
    }
    
    
    //VALIDATION ---------------------------------------------------------------
    
    @Override
    public boolean validateInput(MP3 mp3) {
        if ((mp3.getEpisodeLink().length() > 60) || (mp3.getEpisodeLink().length() < 2)) {
            return false;
        }
        if ((mp3.getEpisodeTitle().length() > 50) || (mp3.getEpisodeTitle().length() < 2)) {
            return false;
        }
        if ((mp3.getEpisodeDate().length() > 25) || (mp3.getEpisodeDate().length() < 2)) {
            return false;
        }
        if ((mp3.getEpisodeDescription().length() > 5000) || (mp3.getEpisodeDescription().length() < 2)) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean validateInputForNewMP3(RBNewMP3 mp3) {
        if ((mp3.getEpisodeLink().length() > 60) || (mp3.getEpisodeLink().length() < 2)) {
            return false;
        }
        
        List<MP3> currentEpisodes = mp3repo.findAll();
        for (MP3 episode : currentEpisodes) {
            if (mp3.getEpisodeLink().equalsIgnoreCase(episode.getEpisodeLink())) {
                return false;
            }
        }        
        if ((mp3.getEpisodeTitle().length() > 50) || (mp3.getEpisodeTitle().length() < 2)) {
            return false;
        }
        if ((mp3.getEpisodeDate().length() > 25) || (mp3.getEpisodeDate().length() < 2)) {
            return false;
        }
        if ((mp3.getEpisodeDescription().length() > 5000) || (mp3.getEpisodeDescription().length() < 2)) {
            return false;
        }
        
        return true;
    }
}
