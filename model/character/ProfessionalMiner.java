package goldmining.model.character;

import goldmining.constant.MinerType;
import goldmining.constant.SkillName;
import goldmining.model.Location;
import goldmining.model.character.skill.passive.AvoidTrap;

public class ProfessionalMiner extends Miner {

    public ProfessionalMiner(Location location) {
        super(location);
        addPassiveSkill(SkillName.AVOID_TRAP);
    }

    public boolean activateAvoidTrap() {
        if (hasPassiveSkill(SkillName.AVOID_TRAP)) {
            AvoidTrap avoidTrap = (AvoidTrap) getPassiveSkill(SkillName.AVOID_TRAP);
            if (avoidTrap.useSkill(this)) return true;
        }
        return false;
    }

    @Override
    public String getInfo() {
        return "Professional Miner";
    }

    @Override
    public MinerType getMinerType() {
        return MinerType.PROFESSIONAL_MINER;
    }
}
