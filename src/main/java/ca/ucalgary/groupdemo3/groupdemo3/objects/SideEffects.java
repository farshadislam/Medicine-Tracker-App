package ca.ucalgary.groupdemo3.groupdemo3.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SideEffects extends Medicine {
    private HashMap<String, ArrayList> medicineSideEffects = new HashMap<>();

    public SideEffects(String name, Integer dosage, Integer fullBottle, Integer currentBottle, double price, HashMap medicineSideEffects) {
        super(name, dosage, fullBottle, currentBottle, price);
        this.medicineSideEffects = medicineSideEffects;
    }

    public String getSideEffectsArray() {
        String allSE = "";
        for (Map.Entry<String, ArrayList> effect : medicineSideEffects.entrySet()) {
            allSE = allSE + " " + effect.getValue();
        }
        return allSE;
    }

    public String getSideEffects() {
        String allSE = "";
        for (Map.Entry<String, ArrayList> effect : medicineSideEffects.entrySet()) {
            for (Object se : effect.getValue()) {
                String eff = (String) se;
                if (allSE == ""){
                    allSE = eff;
                } else {
                    allSE = allSE + "," + eff;
                }
            }
        }
        return allSE;
    }

    public String commonSideEffects() {
        // Needs to be written in
        return "";
    }

}
