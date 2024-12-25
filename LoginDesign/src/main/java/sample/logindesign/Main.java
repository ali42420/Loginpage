package sample.logindesign;
/*
GUI Login and Sign up Project
1403/9/24
Developed By Ali Mukhtar Bakhtiari
Instructor : Dr. Mahsa Saeedi
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 450);
        stage.setTitle("Login And Sign");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    // Method to send the request to the server
    public static String sendRequestToServer(String request) {
        String response = "";
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            writer.println(request);

            response = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}