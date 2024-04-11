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

    /**
     * Adds a new medicine and displays the change afterwards
     * @param event
     */
    @FXML
    void addMed(ActionEvent event) { // How to add medicine
        int dose = 0; // Initializing
        int fullBottle = 0;
        int currentBottle = 0;
        double price = 0;

        String name = medName.getText(); // Can be anything, no need for error checking

        try { // Have to make sure that inputs are integers
            dose = Integer.parseInt(medDose.getText());
            fullBottle = Integer.parseInt(medFull.getText());
            currentBottle = Integer.parseInt(medCurr.getText());
        } catch (NumberFormatException e) {
            addStatus.setText("At least one invalid integer input: " + e.getMessage()); // Tells the user what is invalid
            return;
        }

        try { // Needs double for price
            price = Double.parseDouble(medPrice.getText());
        } catch (NumberFormatException e) { // Prompts user until it gets desired info
            addStatus.setText("Invalid double input: " + e.getMessage());
            return;
        }

        data.storeNewMedicine(name, dose, fullBottle, currentBottle, price); // Use data object method to add medicine to ArrayList

        mainController.viewMedications(); // Changes to display to reflect addition
    }

}

