package goldmining.game;

import goldmining.constant.Direction;

import goldmining.model.Location;
import goldmining.model.MapObject;
import goldmining.model.character.Player;
import goldmining.model.resources.Gold;
import goldmining.model.trap.Trap;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private int numRows;
    private int numCols;
    private int numResources;
    private int numTraps;
    private int requiredMoney;
    private Player player;
    private Map<Location, MapObject> objectLocationMap;

    public Game(int numRows, int numCols, int numResources, int numTraps, int requiredMoney) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.numResources = numResources;
        this.numTraps = numTraps;
        this.requiredMoney = requiredMoney;
        this.player = new Player(new Location(0, 0));
        objectLocationMap = new HashMap<Location, MapObject>();
        addPlayer();
        objectRandomAddition();
    }

    private void addPlayer() {
        objectLocationMap.put(this.player.getLocation(), this.player);
    }

    public void addTrap(int latitude, int longitude) {
        Location trapLocation = new Location(latitude, longitude);
        objectLocationMap.put(trapLocation, new Trap(trapLocation));
    }

    public void addGold(int latitude, int longitude) {
        Location goldLocation = new Location(latitude, longitude);
        objectLocationMap.put(goldLocation, new Gold(goldLocation));
    }

    private void objectRandomAddition() {
        Random random = new Random();

        int trap = 0;
        while (trap < this.numTraps) {
            
            int latitude = random.nextInt(this.numRows);
            int longitude = random.nextInt(this.numCols);

            if (latitude == 0 && longitude == 0) {
                continue;
            }

            if (getMapObject(latitude, longitude) == null) {
                addTrap(latitude, longitude);
                trap++;
            }
        }

        int gold = 0;
        while (gold < this.numResources) {

            int latitude = random.nextInt(this.numRows);
            int longitude = random.nextInt(this.numCols);

            if (latitude == 0 && longitude == 0) {
                continue;
            }

            if (getMapObject(latitude, longitude) == null) {
                addGold(latitude, longitude);
                gold++;
            }
        }
    }

    public void removeMapObject(int latitude, int longitude) {
        Location location = new Location(latitude, longitude);
        objectLocationMap.remove(location);
    }

    public void move(int distance, Direction direction) {
        Location playerLocation = player.getLocation();
        int currentLatitude = playerLocation.getLatitude();
        int currentLongitude = playerLocation.getLongitude();
        
        switch (direction) {
            case UP:
                if (currentLatitude > 0) {
                    player.setPlayerLatitude(currentLatitude - distance);
                }
                break;
            case DOWN:
                if (currentLatitude < this.numRows - 1) {
                    player.setPlayerLatitude(currentLatitude + distance);
                }
                break;
            case LEFT:
                if (currentLongitude > 0) {
                    player.setPlayerLongitude(currentLongitude - distance);
                }
                break;
            case RIGHT:
                if (currentLongitude < this.numCols - 1) {
                    player.setPlayerLongitude(currentLongitude + distance);
                }
                break;
            default:
                break;
        }
        removeMapObject(currentLatitude, currentLongitude);
        checkCollision();
        addPlayer();
    }

    public MapObject getMapObject(int latitude, int longitude) {
        Location pointedLocation = new Location(latitude, longitude);
        if (objectLocationMap.containsKey(pointedLocation)) {
            MapObject pointedObject = objectLocationMap.get(pointedLocation);
            return pointedObject;
        }
        return null;
    }

    private void checkCollision() {
        Location playerLocation = player.getLocation();
        int playerLatitude = playerLocation.getLatitude();
        int playerLongitude = playerLocation.getLongitude();

        MapObject pointedObject = getMapObject(playerLatitude, playerLongitude);
        if (pointedObject != null) {

            if (GameUtil.isGold(pointedObject)) {
                int price = Gold.getPrice();
                player.collectResource(price);
            } else if (GameUtil.isTrap(pointedObject)) {
                int damage = Trap.getDamage();
                player.takeDamage(damage);
            }
            removeMapObject(playerLatitude, playerLongitude);
        }
    }

    private boolean isWin() {
        int playerMoney = player.getMoney();
        return playerMoney >= this.requiredMoney;
    }

    private boolean isLose() {
        int playerHP = player.getHP();
        int dead = 0;
        return playerHP <= dead;
    }

    public char checkGameStatus() {
        if (isWin()) return 'W';
        if (isLose()) return 'L';
        return ' ';
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumCols() {
        return this.numCols;
    }
}
