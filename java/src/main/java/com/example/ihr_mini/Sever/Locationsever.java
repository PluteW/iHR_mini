package com.example.ihr_mini.Sever;

import com.example.ihr_mini.Entity.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Locationsever {

    public List<Location> getLocations();

    public Location getById(String id);

    public String getId(String name);

    public String getName(String id);

    public void setName(String name,String id);

    public void addLocation(String id,String name);
}
