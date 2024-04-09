package ca.ucalgary.groupdemo3.groupdemo3;

import ca.ucalgary.groupdemo3.groupdemo3.objects.FoodIntake;
import ca.ucalgary.groupdemo3.groupdemo3.objects.Medicine;
import ca.ucalgary.groupdemo3.groupdemo3.objects.SideEffects;
import ca.ucalgary.groupdemo3.groupdemo3.util.FileLoader;
import ca.ucalgary.groupdemo3.groupdemo3.util.FileSaver;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class MainController {
    private static final String MEDICINE_FORMAT = "%-20s %-20s %-20s %-20s";
    private static final String INFORMATION_FORMAT = "%-20s %-20s";
    private static final String MEDICINE_HEADER = String.format(MEDICINE_FORMAT, "Name", "Dosage per day", "Full Bottle", "Currently in Bottle");
    private static final String SIDE_EFFECT_HEADER = String.format(INFORMATION_FORMAT, "Name", "Side Effects");
    private static final String FOOD_INTAKE_HEADER = String.format(INFORMATION_FORMAT, "Name", "Food Intake");

    private static String MEDICINE_SEPERATOR = "";

    static {
        for (int i = 0; i < MEDICINE_HEADER.length(); i++) {
            MEDICINE_SEPERATOR += "-";
        }
    }

    private static Data data = new Data();

    @FXML
    private Button addFoodIntakeButton;

    @FXML
    private TextArea infoTextArea;

    @FXML
    private Label infoLabel;

    @FXML
    private Button addSideEffectsButton;

    @FXML
    private TextArea rightInfoTextArea;

    @FXML
    private Button deleteMedicationButton;

    @FXML
    private Button dosageMGButton;

    @FXML
    private TextField viewMedicineNameTextField;

    @FXML
    private TextField foodIntakeTextField;

    @FXML
    private Button highestExpenseButton;

    @FXML
    private TextField lastPillIntakeTextField;

    @FXML
    private Label leftInfoLabel;

    @FXML
    private Button lowestExpenseButton;

    @FXML
    private TextField madicationNamedelTextField;

    @FXML
    private TextField medicationFINameTextField;

    @FXML
    private TextField medicationMGTextField;

    @FXML
    private TextArea leftInfoTextArea;

    @FXML
    private TextField medicationSENameTextField;

    @FXML
    private Button nextPillTimeButton;

    @FXML
    private TextField pillTimeGapsTextField;

    @FXML
    private Button refillPeriodButton;

    @FXML
    private TextField refillPeriodTextField;

    @FXML
    private Label rightInfoLabel;

    @FXML
    private TextField sideEffectsTextField;

    @FXML
    private Label statusLabel;

    @FXML
    private Button totalExpenseButton;

    @FXML
    private Button viewFoodIntakeButton;

    @FXML
    private Button viewMedicationButton;

    @FXML
    private Button viewSideEffectsButton;

    @FXML
    void onAbout(ActionEvent event) {

    }

    @FXML
    void onAddFoodIntake(ActionEvent event) {
        String medicine;
        String answer;
        boolean medicineExists;
        boolean success;
        medicine = medicationFINameTextField.getText();
        medicineExists = data.checkExistMedicine(medicine); //checks to see the entered medicine exists on the program
        if (!medicineExists){
            statusLabel.setText(medicine + " does not exists!");//output if the medicine doesn't exist
        }
        else{
            answer = foodIntakeTextField.getText();
            success = data.storeMedicineFoodIntake(medicine, answer); //checks to see if the food intake data was stored
            if (success){
                statusLabel.setText("Information has been stored!"); //output if information has been stored
            }
            else{
                statusLabel.setText("Information could not be stored!"); //output of information wasn't able to be stored
            }
        }

    }

    @FXML
    void onAddMedicine(ActionEvent event) {

    }

    @FXML
    void onAddSideEffects(ActionEvent event) {
        String medicine;
        String sideEffect;
        boolean medicineExists;
        boolean success;
        medicine = medicationSENameTextField.getText();
        medicineExists = data.checkExistMedicine(medicine); //Checks to see if the medicine the user entered exists in the program.
        if (!medicineExists) {
            statusLabel.setText(medicine + " does not exists!"); //Output if the medicine doesn't exists
        }
        else{
            sideEffect = sideEffectsTextField.getText();
            success = data.storeMedicineSideEffect(medicine, sideEffect); //checks to see if the side effects are stored
            if (success) {
                statusLabel.setText(sideEffect + " have been added!"); //output if the side effects are stored, otherwise, back to menu.
            }
        }

    }

    @FXML
    void onClose(ActionEvent event) {
        Platform.exit(); //quits the program
    }

    @FXML
    void onDeleteMedication(ActionEvent event) {

    }

    @FXML
    void onDosageMG(ActionEvent event) {

    }

    /**
     * Handles the action when the "Load" button is clicked.
     * Opens a file chooser dialog for the user to select a file to load.
     * If a file is selected and successfully loaded, updates the status label.
     * If loading fails or no file is selected, updates the status label accordingly.
     *
     * @param event The ActionEvent triggered by clicking the "Load" button.
     */
    @FXML
    void onLoad(ActionEvent event) {
        // Create a new FileChooser instance
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Data File");

        // Set initial directory to user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show open file dialog and wait for user to select a file
        File file = fileChooser.showOpenDialog(new Stage());

        // Check if a file was selected
        if (file != null) {
            // Attempt to load the data from the selected file
            Data data = FileLoader.load(file);

            // Check if data loading was successful
            if (data == null) {
                // Update status label if data loading failed
                statusLabel.setText("Failed to load data from " + file.getName());
            } else {
                // Update status label if data was successfully loaded
                statusLabel.setText("Data loaded from " + file.getName());
                MainController.data = data;
            }
        } else {
            // Update status label if no file was selected
            statusLabel.setText("No file selected.");
        }
        viewMedications();
    }

    private void viewMedications() {
        StringBuilder sb = new StringBuilder();
        sb.append(MEDICINE_HEADER);
        //sb.append(MEDICINE_SEPERATOR);
        sb.append("\n");
        for (Medicine medicine: data.getAllMedicineInfo()){
            sb.append(String.format(MEDICINE_FORMAT, medicine.getName(), medicine.getDosage(), medicine.getFullBottle(), medicine.getCurrentBottle(), medicine.getPrice()));
        }
        leftInfoTextArea.setText(sb.toString());
    }

    @FXML
    void onLowestExpense(ActionEvent event) {

    }

    @FXML
    void onNextPillTime(ActionEvent event) {

    }

    @FXML
    void onRefillPeriod(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {
        // Create a new FileChooser instance
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Data File");

        // Set initial directory to user's home directory
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Show open file dialog and wait for user to select a file
        File file = fileChooser.showOpenDialog(new Stage());
        if (FileSaver.save(file, data)){
            statusLabel.setText("Saved to " + file); //output for if file has been saved
        } else{
            statusLabel.setText("Failed to save to " + file); //output if file has not been saved
        }

    }

    @FXML
    void onTotalExpense(ActionEvent event) {

    }

    @FXML
    void onViewFoodIntake(ActionEvent event) {
        rightInfoTextArea.clear();
        viewMedicationsFoodIntake();
    }

    private void viewMedicationsFoodIntake() {
        StringBuilder sb = new StringBuilder();
        sb.append(FOOD_INTAKE_HEADER);
        sb.append("\n");
        for (FoodIntake food_intake: data.getAllFoodIntakeInfo()){
            sb.append(String.format(INFORMATION_FORMAT, food_intake.getName(), food_intake.getFoodIntake()));
        }
        rightInfoTextArea.setText(sb.toString());
    }

    @FXML
    void onViewMedicationButton(ActionEvent event) {
        rightInfoTextArea.clear();
        viewMedication();
    }

    private void viewMedication() {
        String name = viewMedicineNameTextField.getText();
        Medicine medicine = data.getMedicationInfo(name);
        StringBuilder sb = new StringBuilder();
        if (medicine != null) {
            sb.append(MEDICINE_HEADER);
            sb.append("\n");
            sb.append(String.format(MEDICINE_FORMAT, medicine.getName(), medicine.getDosage(), medicine.getFullBottle(), medicine.getCurrentBottle(), medicine.getPrice()));
        }
        else{
            statusLabel.setText("core.objects.Medicine with name '" + name + "' does not exist!%n");
        }
        rightInfoTextArea.setText(sb.toString());
    }

    private void viewMedicationsSideEffects() {
        StringBuilder sb = new StringBuilder();
        sb.append(SIDE_EFFECT_HEADER);
        sb.append("\n");
        for (SideEffects side_effects: data.getAllSideEffectInfo()){
            sb.append(String.format(INFORMATION_FORMAT, side_effects.getName(), side_effects.getSideEffects()));
        }
        rightInfoTextArea.setText(sb.toString());
    }

    @FXML
    void onViewSideEffectsButton(ActionEvent event) {
        rightInfoTextArea.clear();
        viewMedicationsSideEffects();
    }

}