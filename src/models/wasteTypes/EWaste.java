package models.wasteTypes;

import models.WasteItem;

public class EWaste extends WasteItem {
    public EWaste(String name, double weight) {
        super(name, "E-Waste", weight, "Dispose of at an authorized e-waste center");
    }
}
