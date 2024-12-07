package models.wasteTypes;

import models.WasteItem;

public class GlassWaste extends WasteItem {
    public GlassWaste(String name, double weight) {
        super(name, "Glass", weight, "Rinse and place in glass recycling bin");
    }
}
