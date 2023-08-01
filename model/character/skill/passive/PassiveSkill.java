package goldmining.model.character.skill.passive;

import goldmining.model.MapObject;

public interface PassiveSkill {
    boolean useSkill(MapObject mapObject);
    String getInfo();
}
