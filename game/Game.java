package goldmining.game;

import goldmining.constant.Direction;
import goldmining.constant.MapObjectType;
import goldmining.constant.MinerType;
import goldmining.constant.ResourcesType;
import goldmining.constant.TrapType;

import goldmining.model.Location;
import goldmining.model.MapObject;
import goldmining.model.character.Miner;
import goldmining.model.character.ProfessionalMiner;
import goldmining.model.resources.Resource;
import goldmining.model.trap.Trap;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {

    private int numRows;
    private int numCols;
    private int numResources;
    private int numTraps;
    private int requiredMoney;
    private Miner miner;
    private Map<Location, List<MapObject>> objectLocationMap;

    public Game(int numRows, int numCols, int numResources, int numTraps, int requiredMoney, String minerType) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.numResources = numResources;
        this.numTraps = numTraps;
        this.requiredMoney = requiredMoney;
        this.miner = GameUtil.createMinerObject(new Location(0, 0), minerType);
        objectLocationMap = new HashMap<Location, List<MapObject>>();
        addMapObject(miner.getLocation().getLatitude(), miner.getLocation().getLongitude(), miner.getMinerType());
        objectRandomAddition();
    }

    public void addMapObject(int latitude, int longitude, ResourcesType resourcesType) {
        Location location = new Location(latitude, longitude);
        Resource resource = GameUtil.createResourceObject(location, resourcesType);

        List<MapObject> objectsAtLocation = objectLocationMap.get(location);
        if (objectsAtLocation == null) {
            objectsAtLocation = new LinkedList<>();
            objectLocationMap.put(location, objectsAtLocation);
        }

        objectsAtLocation.add(resource);
    }

    public void addMapObject(int latitude, int longitude, TrapType trapType) {
        Location location = new Location(latitude, longitude);
        Trap trap = GameUtil.createTrapObject(location, trapType);

        List<MapObject> objectsAtLocation = objectLocationMap.get(location);
        if (objectsAtLocation == null) {
            objectsAtLocation = new LinkedList<>();
            objectLocationMap.put(location, objectsAtLocation);
        }

        objectsAtLocation.add(trap);
    }

    public void addMapObject(int latitude, int longitude, MinerType minerType) {
        Location location = new Location(latitude, longitude);

        List<MapObject> objectsAtLocation = objectLocationMap.get(location);
        if (objectsAtLocation == null) {
            objectsAtLocation = new LinkedList<>();
            objectLocationMap.put(location, objectsAtLocation);
        }

        objectsAtLocation.add(miner);
    }

    private void objectRandomAddition() {
        Random random = new Random();

        int trap = 0;
        while (trap < this.numTraps) {
            
            int latitude = random.nextInt(this.numRows);
            int longitude = random.nextInt(this.numCols);
            TrapType trapType = GameUtil.getRandomTrap();

            if (latitude == 0 && longitude == 0) {
                continue;
            }

            if (getListMapObjects(latitude, longitude) == null) {
                addMapObject(latitude, longitude, trapType);
                trap++;
            }
        }

        int resource = 0;
        while (resource < this.numResources) {

            int latitude = random.nextInt(this.numRows);
            int longitude = random.nextInt(this.numCols);
            ResourcesType resourcesType = GameUtil.getRandomResource();

            if (latitude == 0 && longitude == 0) {
                continue;
            }

            if (getListMapObjects(latitude, longitude) == null) {
                addMapObject(latitude, longitude, resourcesType);
                resource++;
            }
        }
    }
    
    public void removeMapObject(int latitude, int longitude, MapObjectType mapObjectType) {
        Location location = new Location(latitude, longitude);
        List<MapObject> objectsAtLocation = objectLocationMap.get(location);

        if (objectsAtLocation != null) {
            for (MapObject eachObject : objectsAtLocation) {
                if (eachObject.getObjectType() == mapObjectType) {
                    objectsAtLocation.remove(eachObject);
                    break;
                }
            }
            if (objectsAtLocation.isEmpty()) {
                objectLocationMap.remove(location);
            }
        }
    }

    public void move(int distance, Direction direction) {
        Location minerLocation = miner.getLocation();
        int currentLatitude = minerLocation.getLatitude();
        int currentLongitude = minerLocation.getLongitude();

        switch (direction) {
            case UP:
                if (currentLatitude > 0) {
                    miner.setMinerLatitude(currentLatitude - distance);
                }
                break;
            case DOWN:
                if (currentLatitude < this.numRows - 1) {
                    miner.setMinerLatitude(currentLatitude + distance);
                }
                break;
            case LEFT:
                if (currentLongitude > 0) {
                    miner.setMinerLongitude(currentLongitude - distance);
                }
                break;
            case RIGHT:
                if (currentLongitude < this.numCols - 1) {
                    miner.setMinerLongitude(currentLongitude + distance);
                }
                break;
            default:
                break;
        }
        removeMapObject(currentLatitude, currentLongitude, MapObjectType.MINER);
        checkCollision();
        addMapObject(miner.getLocation().getLatitude(), miner.getLocation().getLongitude(), miner.getMinerType());
    }

    public MapObject getMapObject(int latitude, int longitude, MapObjectType mapObjectType) {
        List<MapObject> pointedListObject = getListMapObjects(latitude, longitude);
        if (pointedListObject != null) {
            for (MapObject eachObject : pointedListObject) {
                if (eachObject.getObjectType() == mapObjectType) {
                    return eachObject;
                }
            }
        }
        return null;
    }

    public List<MapObject> getListMapObjects(int latitude, int longitude) {
        Location location = new Location(latitude, longitude);
        if (objectLocationMap.containsKey(location)) {
            List<MapObject> pointedListObject = objectLocationMap.get(location);
            return pointedListObject;
        }
        return null;
    }

    private void checkCollision() {
        Location minerLocation = miner.getLocation();
        int minerLatitude = minerLocation.getLatitude();
        int minerLongitude = minerLocation.getLongitude();

        MapObject pointedResource = getMapObject(minerLatitude, minerLongitude, MapObjectType.RESOURCES);
        MapObject pointedTrap = getMapObject(minerLatitude, minerLongitude, MapObjectType.TRAP);
        if (pointedResource != null) {
            if (GameUtil.isResource(pointedResource)) {
                checkCollisionResource(pointedResource);
            }
            minerBreakObject(minerLatitude, minerLongitude, pointedResource);
        }
        
        if (pointedTrap != null) {
            if (GameUtil.isTrap(pointedTrap)) {
                checkCollisionTrap(pointedTrap);
            }
            minerBreakObject(minerLatitude, minerLongitude, pointedTrap);
        }
    }

    private void checkCollisionResource(MapObject pointedObject) {
        Resource pointedResource = (Resource) pointedObject;
        int price = pointedResource.getPrice();
        miner.collectResource(price);
    }

    private void checkCollisionTrap(MapObject pointedObject) {
        Trap pointedTrap = (Trap) pointedObject;
        int damage = pointedTrap.getDamage();
        
        if (miner.getMinerType() == MinerType.PROFESSIONAL_MINER) {
            ProfessionalMiner proMiner = (ProfessionalMiner) miner;
            if (proMiner.activateAvoidTrap()) {
                return;
            }
        }
        miner.takeDamage(damage);
    }

    private void minerBreakObject(int minerLatitude, int minerLongitude, MapObject pointedObject) {
        if (miner.activateIdentifyBreakable(pointedObject)) {
            if (pointedObject.getObjectType() == MapObjectType.TRAP) {
                removeMapObject(minerLatitude, minerLongitude, MapObjectType.TRAP);
            } else if (pointedObject.getObjectType() == MapObjectType.RESOURCES) {
                removeMapObject(minerLatitude, minerLongitude, MapObjectType.RESOURCES);
            }
        }
    }

    private boolean isWin() {
        int minerMoney = miner.getMoney();
        return minerMoney >= this.requiredMoney;
    }

    private boolean isLose() {
        int minerHP = miner.getHP();
        int dead = 0;
        return minerHP <= dead;
    }

    public char checkGameStatus() {
        if (isWin()) return 'W';
        if (isLose()) return 'L';
        return ' ';
    }
    
    private boolean isNearObject(int latitude, int longitude, MapObjectType mapObjectType) {
    
        int[][] directions = {
            {latitude - 1, longitude}, 
            {latitude + 1, longitude}, 
            {latitude, longitude - 1}, 
            {latitude, longitude + 1}
        };
    
        for (int[] direction : directions) {
            MapObject pointedResource = getMapObject(direction[0], direction[1], MapObjectType.RESOURCES);
            MapObject pointedTrap = getMapObject(direction[0], direction[1], MapObjectType.TRAP);
            if (pointedResource != null) {
                if (mapObjectType == MapObjectType.RESOURCES && GameUtil.isResource(pointedResource)) {
                    return true;
                } 
            }
            
            if (pointedTrap != null) {
                if (mapObjectType == MapObjectType.TRAP && GameUtil.isTrap(pointedTrap)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isNearResource(int latitude, int longitude) {
        return isNearObject(latitude, longitude, MapObjectType.RESOURCES);
    }
    
    public boolean isNearTrap(int latitude, int longitude) {
        return isNearObject(latitude, longitude, MapObjectType.TRAP);
    }

    public int getNumRows() {
        return this.numRows;
    }

    public int getNumCols() {
        return this.numCols;
    }
}
