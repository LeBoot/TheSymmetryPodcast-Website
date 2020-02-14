/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.service;

import bl.thesymmetrypodcast.entity.Region;
import bl.thesymmetrypodcast.repository.RegionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Boone
 */
@Service
public class RegionServiceImpl implements RegionService {
    
    @Autowired
    RegionRepository regionRepo;
    
    @Override
    public Region getRegionById(int regionId) {
        Optional<Region> regionOpt = regionRepo.findById(regionId);
        if (regionOpt.isPresent()) {
            return regionOpt.get();
        } else {
            return null;
        }
    }
    
    @Override
    public List<Region> getAllRegions() {
        return regionRepo.findAll();
    }
}
