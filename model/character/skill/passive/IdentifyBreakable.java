package goldmining.model.character.skill.passive;

import goldmining.constant.MapObjectType;
import goldmining.constant.ResourcesType;
import goldmining.constant.TrapType;

import goldmining.model.MapObject;
import goldmining.model.resources.Resource;
import goldmining.model.trap.Trap;

public class IdentifyBreakable implements PassiveSkill {

    public boolean useSkill(MapObject mapObject) {
        return checkBreakableObject(mapObject);
    }

    private boolean checkBreakableObject(MapObject mapObject) {
        MapObjectType mapObjectType = mapObject.getObjectType();
        switch (mapObjectType) {

            case RESOURCES:
                Resource resourceObject = (Resource) mapObject;
                ResourcesType resourcesObjectType = resourceObject.getResourcesType();
                return checkBreakableResouce(resourcesObjectType);
            
            case TRAP:
                Trap trapObject = (Trap) mapObject;
                TrapType trapObjectType = trapObject.getTrapType();
                return checkBreakableTrap(trapObjectType);

            case MINER:
                return false;
        }
        return false;
    };

    private boolean checkBreakableResouce(ResourcesType resourcesType) {
        switch (resourcesType) {
            case GOLD:
                return true;
            case SILVER:
                return true;
            case WOOD:
                return true;
        }
        return false;
    }

    private boolean checkBreakableTrap(TrapType trapType) {
        switch (trapType) {
            case BEAR_TRAP:
                return true;
            case STONE_TRAP:
                return true;
            case THORN_TRAP:
                return true;
        }
        return false;
    }

    @Override
    public String getInfo() {
        return "BreakObject";
    }
}