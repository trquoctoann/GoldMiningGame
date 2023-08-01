package goldmining.game;

import goldmining.constant.MapObjectType;
import goldmining.constant.MinerType;
import goldmining.constant.ResourcesType;
import goldmining.constant.TrapType;

import goldmining.model.Location;
import goldmining.model.MapObject;
import goldmining.model.trap.*;
import goldmining.model.resources.*;
import goldmining.model.character.*;

import java.util.Random;

import javax.swing.JOptionPane;

public class GameUtil {
    
    public static boolean checkInputDataValid(int numRows, int numCols, int numResources, int numTraps, int requiredMoney) {
        if (requiredMoney > numResources) {
            JOptionPane.showMessageDialog(null, "Required money cannot be greater than the number of resources", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ((numResources + numTraps) > (numCols * numRows - 1)) {
            JOptionPane.showMessageDialog(null, "Number of objects cannot be greater than map area", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (numRows <= 0 || numCols <= 0 || requiredMoney < 0 || numResources < 0 || numTraps < 0) {
            JOptionPane.showMessageDialog(null, "Value must be positive", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (numRows > 6) {
            JOptionPane.showMessageDialog(null, "Max value of rows is 6", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (numCols > 12) {
            JOptionPane.showMessageDialog(null, "Max value of cols is 12", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isResource(MapObject pointedObject) {
        return pointedObject.getObjectType() == MapObjectType.RESOURCES;
    }

    public static boolean isTrap(MapObject pointedObject) {
        return pointedObject.getObjectType() == MapObjectType.TRAP;
    }

    public static boolean isMiner(MapObject pointedObject) {
        return pointedObject.getObjectType() == MapObjectType.MINER;
    }

    private static boolean isTrapOfType(MapObject pointedObject, TrapType trapType) {
        if (isTrap(pointedObject)) {
            Trap pointedTrap = (Trap) pointedObject;
            return pointedTrap.getTrapType() == trapType;
        }
        return false;
    }

    public static boolean isBearTrap(MapObject pointedObject) {
        return isTrapOfType(pointedObject, TrapType.BEAR_TRAP);
    }
    
    public static boolean isStoneTrap(MapObject pointedObject) {
        return isTrapOfType(pointedObject, TrapType.STONE_TRAP);
    }
    
    public static boolean isThornTrap(MapObject pointedObject) {
        return isTrapOfType(pointedObject, TrapType.THORN_TRAP);
    }
    
    private static boolean isResourceType(MapObject pointedObject, ResourcesType resourcesType) {
        if (isResource(pointedObject)) {
            Resource pointedResource = (Resource) pointedObject;
            return pointedResource.getResourcesType() == resourcesType;
        }
        return false;
    }
    
    public static boolean isGold(MapObject pointedObject) {
        return isResourceType(pointedObject, ResourcesType.GOLD);
    }
    
    public static boolean isSilver(MapObject pointedObject) {
        return isResourceType(pointedObject, ResourcesType.SILVER);
    }
    
    public static boolean isWood(MapObject pointedObject) {
        return isResourceType(pointedObject, ResourcesType.WOOD);
    }    

    private static boolean isMinerType(MapObject pointedObject, MinerType minerType) {
        if (isMiner(pointedObject)) {
            Miner pointedMiner = (Miner) pointedObject;
            return pointedMiner.getMinerType() == minerType;
        }
        return false;
    }

    public static boolean isAmateurMiner(MapObject pointedObject) {
        return isMinerType(pointedObject, MinerType.AMATEUR_MINER);
    }
    
    public static boolean isProfessionalMiner(MapObject pointedObject) {
        return isMinerType(pointedObject, MinerType.PROFESSIONAL_MINER);
    }    

    public static Resource createResourceObject(Location resourceLocation, ResourcesType resourcesType) {
        switch (resourcesType) {
            case GOLD:
                return new Gold(resourceLocation);
            case SILVER:
                return new Silver(resourceLocation);
            case WOOD: 
                return new Wood(resourceLocation);
        }
        return null;
    }

    public static Trap createTrapObject(Location trapLocation, TrapType trapType) {
        switch (trapType) {
            case BEAR_TRAP:
                return new BearTrap(trapLocation);
            case STONE_TRAP:
                return new StoneTrap(trapLocation);
            case THORN_TRAP:
                return new ThornTrap(trapLocation);
        }
        return null;
    }

    public static Miner createMinerObject(Location minerLocation, MinerType minerType) {
        switch (minerType) {
            case AMATEUR_MINER:
                return new AmateurMiner(minerLocation);
            case PROFESSIONAL_MINER:
                return new ProfessionalMiner(minerLocation);
        }
        return null;
    }

    public static Miner createMinerObject(Location minerLocation, String minerType) {
        switch (minerType) {
            case "Amateur Miner":
                return new AmateurMiner(minerLocation);
            case "Professional Miner":
                return new ProfessionalMiner(minerLocation);
        }
        return null;
    }

    public static ResourcesType getRandomResource() {
        ResourcesType[] allResources = ResourcesType.values();

        Random random = new Random();
        int randomIndex = random.nextInt(allResources.length);
        return allResources[randomIndex];
    }

    public static TrapType getRandomTrap() {
        TrapType[] allTraps = TrapType.values();

        Random random = new Random();
        int randomIndex = random.nextInt(allTraps.length);
        return allTraps[randomIndex];
    }
}
