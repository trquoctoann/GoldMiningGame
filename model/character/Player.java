package goldmining.model.character;

import goldmining.model.MapObject;
import goldmining.model.Location;

public class Player extends MapObject {

    private int money = 0;
    private int hitPoint = 2;

    public Player(Location location) {
        super(location);
    }

    public void setPlayerLatitude(int newLatitude) {
        Location currentLocation = getLocation();
        currentLocation.setLatitude(newLatitude);
    }

    public void setPlayerLongitude(int newLongitude) {
        Location currentLocation = getLocation();
        currentLocation.setLongitude(newLongitude);
    }

    public int getHP() {
        return this.hitPoint;
    }

    public void takeDamage(int damage) {
        this.hitPoint -= damage;
    }

    public int getMoney() {
        return this.money;
    }

    public void collectResource(int collectedResourcePrice) {
        this.money += collectedResourcePrice;
    }

    public String getInfo() {
        return "Player";
    }
}
