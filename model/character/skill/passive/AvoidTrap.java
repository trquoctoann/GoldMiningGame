package goldmining.model.character.skill.passive;

import goldmining.model.MapObject;
import goldmining.model.character.Miner;

public class AvoidTrap implements PassiveSkill {

    private int remainingUsages;

    public AvoidTrap(int initialUsages) {
        this.remainingUsages = initialUsages;
    }

    @Override
    public boolean useSkill(MapObject mapObject) {
        Miner miner = (Miner) mapObject;
        if (isImmuneToTrap()) {
            int usageTime = 1;
            this.remainingUsages -= usageTime;
            miner.takeDamage(0);
            return true;
        }
        return false;
    }

    private boolean isImmuneToTrap() {
        return this.remainingUsages > 0;
    }

    public void collectImmunityUsage(int collectedUsageTime) {
        this.remainingUsages += collectedUsageTime;
    }

    @Override
    public String getInfo() {
        return "AvoidTrap";
    }
}