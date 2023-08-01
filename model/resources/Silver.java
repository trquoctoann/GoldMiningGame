package goldmining.model.resources;

import goldmining.constant.ResourcesType;

import goldmining.model.Location;

public class Silver extends Resource {
    private final int SILVER_PRICE = 2;

    public Silver(Location location) {
        super(location);
    }

    @Override
    public int getPrice() {
        return SILVER_PRICE;
    }

    @Override
    public String getInfo() {
        return "Silver";
    }

    @Override
    public ResourcesType getResourcesType() {
        return ResourcesType.SILVER;
    }
}
