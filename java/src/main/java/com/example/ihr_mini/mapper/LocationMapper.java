package com.example.ihr_mini.Mapper;

import com.example.ihr_mini.Entity.Location;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LocationMapper {

    public List<Location> getLocations();

    public Location getById(String id);

    public List<String> getNames();

    public String getId(String name);

    public String getName(String id);

    public void setName(String name,String id);

    public void addLocation(String id,String name);
}
