/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.requestBody;

import lombok.Data;

/**
 *
 * @author Boone
 */
@Data
public class RBNewMP3 {
    
    private String episodeLink;
    private String episodeTitle;
    private String episodeDate;
    private String episodeDescription;
    
}
