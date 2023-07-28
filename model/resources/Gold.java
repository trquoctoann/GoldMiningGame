package goldmining.model.resources;

import goldmining.model.MapObject;
import goldmining.model.Location;

public class Gold extends MapObject {

    private static final int GOLD_PRICE = 1;

    public Gold(Location location) {
        super(location);
    }

    public void setGoldLatitude(int newLatitude) {
        Location currentLocation = getLocation();
        currentLocation.setLatitude(newLatitude);
    }

    public void setGoldLongitude(int newLongitude) {
        Location currentLocation = getLocation();
        currentLocation.setLongitude(newLongitude);
    }

    public static int getPrice() {
        return GOLD_PRICE;
    }

    public String getInfo() {
        return "Gold";
    }
}
