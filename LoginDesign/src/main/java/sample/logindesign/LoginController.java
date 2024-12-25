package sample.logindesign;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
    public static Stage stage;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginMassage;



    // Method when our sign button clicked
    @FXML
    protected void  registrationButton(){

        try {
            // this is new page for register the new user
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/logindesign/Sign_up.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Creating Account");
            stage.setScene(new Scene(root, 350, 550));
            stage.show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Method when the Login button clicked
    @FXML
    private void loginButton() {
        String username = userName.getText();
        String passwordd = password.getText();


        if (username.isEmpty() && passwordd.isEmpty()) {
            loginMassage.setText("Fields must be filled");

        } else {

            // Send the data to the server form sendRequestToServer method from main class
            String request = "LOGIN " + username + " " + passwordd;
            String response = Main.sendRequestToServer(request);

            if(response.equals("Login successful")) {
                try {
                    // New page when user login successfully
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/logindesign/Login_view.fxml"));
                    Parent root = fxmlLoader.load();

                    Stage stage = new Stage();
                    stage.setTitle("Welcome Page");
                    stage.setScene(new Scene(root, 400, 200));
                    stage.show();

                    // it is optional to close the previous page
                    //     Stage currentStage = (Stage) userName.getScene().getWindow();
                    //  currentStage.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {
                loginMassage.setText(response);

            }




        }
    }


}
