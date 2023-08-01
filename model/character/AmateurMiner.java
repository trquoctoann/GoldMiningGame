package goldmining.model.character;

import goldmining.constant.MinerType;

import goldmining.model.Location;

public class AmateurMiner extends Miner {
    
    public AmateurMiner(Location location) {
        super(location);
    }

    @Override
    public String getInfo() {
        return "Amateur Miner";
    }

    @Override
    public MinerType getMinerType() {
        return MinerType.AMATEUR_MINER;
    }
}
