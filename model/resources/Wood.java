package goldmining.model.resources;

import goldmining.constant.ResourcesType;

import goldmining.model.Location;

public class Wood extends Resource {
    private final int WOOD_PRICE = 1;

    public Wood(Location location) {
        super(location);
    }

    @Override
    public int getPrice() {
        return WOOD_PRICE;
    }

    @Override
    public String getInfo() {
        return "Wood";
    }

    @Override
    public ResourcesType getResourcesType() {
        return ResourcesType.WOOD;
    }
}
