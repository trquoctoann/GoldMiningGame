package goldmining.model.character;

import java.util.HashMap;
import java.util.Map;

import goldmining.constant.MapObjectType;
import goldmining.constant.MinerType;
import goldmining.constant.SkillName;
import goldmining.model.MapObject;
import goldmining.model.character.skill.passive.AvoidTrap;
import goldmining.model.character.skill.passive.IdentifyBreakable;
import goldmining.model.character.skill.passive.PassiveSkill;
import goldmining.model.Location;

public abstract class Miner extends MapObject {

    protected int money = 0;
    protected int hitPoint = 5;
    protected Map<SkillName, PassiveSkill> availablePassiveSkill = new HashMap<>();

    public Miner(Location location) {
        super(location);
        addPassiveSkill(SkillName.IDENTIFY_BREAKABLE);
    }

    public void setMinerLatitude(int newLatitude) {
        Location currentLocation = getLocation();
        currentLocation.setLatitude(newLatitude);
    }

    public void setMinerLongitude(int newLongitude) {
        Location currentLocation = getLocation();
        currentLocation.setLongitude(newLongitude);
    }

    public boolean hasPassiveSkill(SkillName skillName) {
        return availablePassiveSkill.containsKey(skillName);
    }

    public boolean addPassiveSkill(SkillName skillName) {
        if(!hasPassiveSkill(skillName)) {
            switch (skillName) {
                case AVOID_TRAP:
                    this.availablePassiveSkill.put(skillName, new AvoidTrap(1));
                    break;
                case IDENTIFY_BREAKABLE:
                    this.availablePassiveSkill.put(skillName, new IdentifyBreakable());
                    break;
            }
            return true;
        }
        return false;
    }

    public boolean removePassiveSkill(SkillName skillName) {
        if(hasPassiveSkill(skillName)) {
            this.availablePassiveSkill.remove(skillName);
            return true;
        }
        return false;
    }

    public PassiveSkill getPassiveSkill(SkillName skillName) {
        if (hasPassiveSkill(skillName)) {
            return this.availablePassiveSkill.get(skillName);
        }
        return null;
    }

    public boolean activateIdentifyBreakable(MapObject mapObject) {
        if (hasPassiveSkill(SkillName.IDENTIFY_BREAKABLE)) {
            IdentifyBreakable identifyBreakable = (IdentifyBreakable) getPassiveSkill(SkillName.IDENTIFY_BREAKABLE);
            return identifyBreakable.useSkill(mapObject);
        }
        return false;
    }

    public void takeDamage(int damage) {
        this.hitPoint -= damage;
    }

    public void collectResource(int collectedResourcePrice) {
        this.money += collectedResourcePrice;
    }

    public int getHP() {
        return this.hitPoint;
    }

    public int getMoney() {
        return this.money;
    }

    @Override
    public MapObjectType getObjectType() {
        return MapObjectType.MINER;
    }

    public abstract String getInfo();

    public abstract MinerType getMinerType();
}
