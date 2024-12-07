package models;

public abstract class WasteItem {
    private String name;
    private String type;
    private double weight;
    private String disposalInstructions;

    public WasteItem(String name, String type, double weight, String disposalInstructions) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.disposalInstructions = disposalInstructions;
    }

    public String getName() { 
        return name; 
    }
    
    public String getType() { 
        return type; 
    }

    public double getWeight() {
        return weight; 
    }

    public String getDisposalInstructions() { 
        return disposalInstructions;
    }
}
