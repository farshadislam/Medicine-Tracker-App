package ca.ucalgary.groupdemo3.groupdemo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddController {

    @FXML
    private TextField medCurr;

    @FXML
    private TextField medDose;

    @FXML
    private TextField medFull;

    @FXML
    private TextField medName;

    @FXML
    private TextField medPrice;

    private Data data;

    public void establishData(Data data) {
        this.data = data;
    }

    @FXML
    void addMed(ActionEvent event) {
        String name = medName.getText();
        int dose = Integer.parseInt(medDose.getText());
        int fullBottle = Integer.parseInt(medFull.getText());
        int currentBottle = Integer.parseInt(medCurr.getText());
        double price = Integer.parseInt(medPrice.getText());
        data.storeNewMedicine();
    }

}

