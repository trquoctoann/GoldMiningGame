package goldmining.model.trap;

import goldmining.constant.TrapType;

import goldmining.model.Location;

public class StoneTrap extends Trap {
    private final int TRAP_DAMAGE = 2;

    public StoneTrap(Location location) {
        super(location);
    }

    @Override
    public int getDamage() {
        return TRAP_DAMAGE;
    }

    @Override
    public String getInfo() {
        return "Stone Trap";
    }

    @Override
    public TrapType getTrapType() {
        return TrapType.STONE_TRAP;
    }
}