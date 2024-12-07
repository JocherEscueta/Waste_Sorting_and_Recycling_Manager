package models.wasteTypes;

import models.WasteItem;

public class MetalWaste extends WasteItem {
    public MetalWaste(String name, double weight) {
        super(name, "Metal", weight, "Rinse and place in metal recycling bin");
    }
}
