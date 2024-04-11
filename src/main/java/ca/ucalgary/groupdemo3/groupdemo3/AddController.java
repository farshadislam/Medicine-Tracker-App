package ca.ucalgary.groupdemo3.groupdemo3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    @FXML
    private Label addStatus;

    private Data data;

    private MainController mainController;

    public void establishData(Data data, MainController mainController) {
        this.data = data;
        this.mainController = mainController;
    }

    @FXML
    void addMed(ActionEvent event) {
        int dose = 0;
        int fullBottle = 0;
        int currentBottle = 0;
        double price = 0;

        String name = medName.getText();

        try {
            dose = Integer.parseInt(medDose.getText());
            fullBottle = Integer.parseInt(medFull.getText());
            currentBottle = Integer.parseInt(medCurr.getText());
        } catch (NumberFormatException e) {
            addStatus.setText("At least one invalid integer input: " + e.getMessage());
        }

        try {
            price = Double.parseDouble(medPrice.getText());
        } catch (NumberFormatException e) {
            addStatus.setText("Invalid double input: " + e.getMessage());
        }

        data.storeNewMedicine(name, dose, fullBottle, currentBottle, price);

        mainController.viewMedications();
    }

}

