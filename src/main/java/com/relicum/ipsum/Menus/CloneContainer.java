package com.relicum.ipsum.Menus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.Validate;

/**
 * Name: CloneContainer.java Created: 05 February 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class CloneContainer {

    private Map<String, Region> regions;

    public CloneContainer() {
        this.regions = new HashMap<>();
    }

    public void addNewRegion(String key, Region region) {
        Validate.notNull(key);
        Validate.notNull(region);

        regions.put(key, region);
    }

    public Region getRegion(String key) {
        Validate.notNull(key);

        return regions.get(key);
    }

    public boolean contains(String key) {

        Validate.notNull(key);

        return regions.containsKey(key);
    }

    public Set<Map.Entry<String, Region>> getRegions() {

        return regions.entrySet();
    }

    public void removeRegion(String key) {
        Validate.notNull(key);

        regions.remove(key);

    }

    public void removeAllRegions() {

        regions.clear();
    }

}
