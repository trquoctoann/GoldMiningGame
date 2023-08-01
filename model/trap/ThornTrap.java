package goldmining.model.trap;

import goldmining.constant.TrapType;

import goldmining.model.Location;

public class ThornTrap extends Trap {
    private final int TRAP_DAMAGE = 1;

    public ThornTrap(Location location) {
        super(location);
    }

    @Override
    public int getDamage() {
        return TRAP_DAMAGE;
    }

    @Override
    public String getInfo() {
        return "Thorn Trap";
    }

    @Override
    public TrapType getTrapType() {
        return TrapType.THORN_TRAP;
    }
}