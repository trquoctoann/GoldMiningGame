package goldmining.model.trap;

import goldmining.constant.TrapType;

import goldmining.model.Location;

public class BearTrap extends Trap {
    private final int TRAP_DAMAGE = 3;

    public BearTrap(Location location) {
        super(location);
    }

    @Override
    public int getDamage() {
        return TRAP_DAMAGE;
    }

    @Override
    public String getInfo() {
        return "Bear Trap";
    }

    @Override
    public TrapType getTrapType() {
        return TrapType.BEAR_TRAP;
    }
}
