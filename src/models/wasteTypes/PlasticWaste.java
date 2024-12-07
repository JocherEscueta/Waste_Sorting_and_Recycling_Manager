package models.wasteTypes;

import models.WasteItem;

public class PlasticWaste extends WasteItem {
    public PlasticWaste(String name, double weight) {
        super(name, "Plastic", weight, "Rinse and place in recycling bin");
    }
}
