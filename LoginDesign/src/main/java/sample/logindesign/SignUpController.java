package sample.logindesign;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class SignUpController {

    @FXML
    private Label signLabel;
    @FXML
    private TextField userName;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPassword;

    // Method when the sign up button clicked
    @FXML
    public void signOnAction() {
        String username = userName.getText();
        String password = passwordField.getText();
        String confirmPass = confirmPassword.getText();
        String emailUser = emailField.getText();

        if (!username.isBlank() && !password.isBlank() && !confirmPass.isBlank() && !emailUser.isBlank()) {
            // Send the data to the server
            String request = "SIGNUP " + username + " " + password + " " + confirmPass + " " + emailUser;
            // received the response from server
            String response = Main.sendRequestToServer(request);

            if (response.equals("Signup successful")) {
                try {
                    // New page When sign up is done successfully
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/logindesign/Sign_Welcome.fxml"));
                    Parent root = fxmlLoader.load();

                    Stage stage = new Stage();
                    stage.setTitle("Welcome");


                    stage.setScene(new Scene(root, 400, 200));
                    stage.show();

                    // Close the previous page
                    Stage currentStage = (Stage) userName.getScene().getWindow();
                    currentStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                signLabel.setText(response);
            }
        } else {
            signLabel.setText("All fields must be filled.");
        }
    }


}
