module sample.logindesign {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.logindesign to javafx.fxml;
    exports sample.logindesign;
}