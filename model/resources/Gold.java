package goldmining.model.resources;

import goldmining.constant.ResourcesType;

import goldmining.model.Location;

public class Gold extends Resource {

    private final int GOLD_PRICE = 3;

    public Gold(Location location) {
        super(location);
    }

    @Override
    public int getPrice() {
        return GOLD_PRICE;
    }

    @Override
    public String getInfo() {
        return "Gold";
    }

    @Override
    public ResourcesType getResourcesType() {
        return ResourcesType.GOLD;
    }
}
