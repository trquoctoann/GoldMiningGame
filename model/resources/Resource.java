package goldmining.model.resources;

import goldmining.constant.MapObjectType;
import goldmining.constant.ResourcesType;

import goldmining.model.MapObject;
import goldmining.model.Location;

public abstract class Resource extends MapObject {

    public Resource(Location location) {
        super(location);
    }

    public void setResourceLatitude(int newLatitude) {
        Location currentLocation = getLocation();
        currentLocation.setLatitude(newLatitude);
    }

    public void setResourceLongitude(int newLongitude) {
        Location currentLocation = getLocation();
        currentLocation.setLongitude(newLongitude);
    }

    @Override
    public MapObjectType getObjectType() {
        return MapObjectType.RESOURCES;
    }

    public abstract int getPrice();

    public abstract String getInfo();

    public abstract ResourcesType getResourcesType();
}
