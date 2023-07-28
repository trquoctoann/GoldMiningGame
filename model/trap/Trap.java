package goldmining.model.trap;

import goldmining.model.Location;
import goldmining.model.MapObject;

public class Trap extends MapObject {

    private static final int TRAP_DAMAGE = 1;

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

    public static int getDamage() {
        return TRAP_DAMAGE;
    }

    public String getInfo() {
        return "Trap";
    }
}
