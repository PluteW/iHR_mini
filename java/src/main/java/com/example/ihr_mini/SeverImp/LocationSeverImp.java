package com.example.ihr_mini.SeverImp;

import com.example.ihr_mini.Entity.Location;
import com.example.ihr_mini.Mapper.LocationMapper;
import com.example.ihr_mini.Sever.Locationsever;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationSeverImp implements Locationsever {

    private LocationMapper locationMapper;

    @Override
    public List<Location> getLocations() {
        return locationMapper.getLocations();
    }

    @Override
    public Location getById(String id) {
        return locationMapper.getById(id);
    }

    @Override
    public String getId(String name) {
        return locationMapper.getId(name);
    }

    @Override
    public String getName(String id) {
        return locationMapper.getName(id);
    }

    @Override
    public void setName(String name, String id) {
        return;
    }

    @Override
    public void addLocation(String id, String name) {
        return;
    }
}
