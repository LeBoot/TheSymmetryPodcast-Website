/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.Region;
import java.util.List;

/**
 *
 * @author Boone
 */
public interface RegionService {
    
    public Region getRegionById(int regionId);
    public List<Region> getAllRegions();
    
}
