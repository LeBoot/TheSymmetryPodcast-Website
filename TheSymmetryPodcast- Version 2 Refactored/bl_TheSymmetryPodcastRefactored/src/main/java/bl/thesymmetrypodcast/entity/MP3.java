/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Boone
 */
@Entity
@Table(name = "mp3tbl")
@Data
public class MP3 {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int episodekey;
    
    @Column(name = "episodelink")
    private String episodeLink;
    
    @Column(name = "episodetitle")
    private String episodeTitle;
    
    @Column(name = "episodedate")
    private String episodeDate;
    
    @Column(name = "episodedescription")
    private String episodeDescription;
}