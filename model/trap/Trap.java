package goldmining.model.trap;

import goldmining.constant.MapObjectType;
import goldmining.constant.TrapType;

import goldmining.model.Location;
import goldmining.model.MapObject;

public abstract class Trap extends MapObject {

    public Trap(Location location) {
        super(location);
    }

    public void setTrapLatitude(int newLatitude) {
        Location currentLocation = getLocation();
        currentLocation.setLatitude(newLatitude);
    }

    public void setTrapLongitude(int newLongitude) {
        Location currentLocation = getLocation();
        currentLocation.setLongitude(newLongitude);
    }

    @Override
    public MapObjectType getObjectType() {
        return MapObjectType.TRAP;
    }

    public abstract int getDamage();

    public abstract String getInfo();

    public abstract TrapType getTrapType();
}
