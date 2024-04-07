package ca.ucalgary.groupdemo3.groupdemo3.objects;

import java.util.HashMap;
import java.util.Map;

public class FoodIntake extends Medicine {

    private HashMap<String, String> foodIntake;
    public FoodIntake(String name, Integer dosage, Integer fullBottle, Integer currentBottle, double price, HashMap foodIntake) {
        super(name, dosage, fullBottle, currentBottle, price);
        this.foodIntake = foodIntake;
    }

    public String getFoodIntake() {
        String whenConsume = "";
        for (Map.Entry<String, String> i : foodIntake.entrySet()) {
            whenConsume = String.valueOf(i);
        }
        return whenConsume;
    }

    public String commonFoodIntakes() {
        // Needs to be written in (use "instanceof core.objects.FoodIntake")
        return "";
    }
}
