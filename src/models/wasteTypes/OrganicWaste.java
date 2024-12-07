package models.wasteTypes;

import models.WasteItem;

public class OrganicWaste extends WasteItem {
    public OrganicWaste(String name, double weight) {
        super(name, "Organic", weight, "Place in compost bin");
    }
}
