/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.controller;

import bl.thesymmetrypodcast.entity.MP3;
import bl.thesymmetrypodcast.requestBody.RBNewMP3;
import bl.thesymmetrypodcast.service.MP3ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Boone
 */
@Controller
@CrossOrigin
@RequestMapping("/episode")
public class MP3Controller {
    
    @Autowired
    MP3ServiceImpl mp3service;
    
    //Return a list of all mp3s ------------------------------------------------    
    @GetMapping("/get/all")
    public ResponseEntity<List<MP3>> getAllMp3s() {
        List<MP3> allMP3s = mp3service.getAllMP3s();
        return new ResponseEntity<>(allMP3s, HttpStatus.OK);
    }
    
    //Return two newest episodes -----------------------------------------------
    @GetMapping("/get/two")
    public ResponseEntity<List<MP3>> getTwoMp3s() {
        List<MP3> twoNewest = mp3service.getTwoNewestMP3s();
        return new ResponseEntity<>(twoNewest, HttpStatus.OK);
    }
    
    //Retun a single mp3 -------------------------------------------------------
    @GetMapping("/get/{key}")
    public ResponseEntity<MP3> getAnMP3(@PathVariable int key) {
        MP3 thisMP3 = mp3service.getAnMP3(key);
        return new ResponseEntity<>(thisMP3, HttpStatus.OK);
    }
    
    //Handle request to edit an mp3 --------------------------------------------
    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveAnMP3(@RequestBody MP3 mp3) {
        boolean areFieldsValid = mp3service.validateInput(mp3);
        if (areFieldsValid == true) {
            mp3service.saveMP3(mp3);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    //Handle request to add a new mp3 ------------------------------------------
    @PostMapping("/save/new")
    public ResponseEntity<HttpStatus> createAnMP3(@RequestBody RBNewMP3 mp3) {
        boolean areFieldsValid = mp3service.validateInputForNewMP3(mp3);
        if (areFieldsValid == true) {
            MP3 newMP3 = new MP3();
            newMP3.setEpisodeTitle(mp3.getEpisodeTitle());
            newMP3.setEpisodeDate(mp3.getEpisodeDate());
            newMP3.setEpisodeLink(mp3.getEpisodeLink());
            newMP3.setEpisodeDescription(mp3.getEpisodeDescription());
            
            mp3service.saveMP3(newMP3);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    //Handle deletion ----------------------------------------------------------
    @GetMapping("/delete/{key}")
    public ResponseEntity<HttpStatus> deleteAnMP3(@PathVariable Integer key) {
        MP3 mp3ToDelete = mp3service.getAnMP3(key);
        mp3service.deleteMP3(mp3ToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
