package goldmining.model;

import goldmining.constant.MapObjectType;

public abstract class MapObject {

    private Location location;

    public MapObject(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public abstract String getInfo();

    public abstract MapObjectType getObjectType();
}
