module ca.ucalgary.groupdemo3.groupdemo3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.ucalgary.groupdemo3.groupdemo3 to javafx.fxml;
    exports ca.ucalgary.groupdemo3.groupdemo3;
}