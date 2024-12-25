package sample.logindesign;
import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        // This is our server, and this method start the server to get request from the client
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String request = reader.readLine();
                    String response = processRequest(request);
                    writer.println(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method get the value or request from client
    private static String processRequest(String request) throws IOException {
        //this section check the input if the user want to sign up
        if (request.startsWith("SIGNUP")) {
            String[] parts = request.split(" ");
            if (parts.length == 5) {
                saveToFile(parts[1], parts[2], parts[3],parts[4]);
                return "Signup successful";
            } else {
                return "Invalid signup format";
            }
         // this section check the input if the user want to login in
        } else if (request.startsWith("LOGIN")) {
            String[] parts = request.split(" ");
            if (parts.length == 3 && checkCredentials(parts[1], parts[2])) {
                return "Login successful";
            } else {
                return "Invalid username or password";
            }
        }
        return "Unknown command";
    }

    // This method save the information of the user that send from the request method
    private static void saveToFile(String username,String confirmPass , String password,String emailUser) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\Niazi Wall\\Documents\\Account.txt", true))) {
            writer.println(username + " " + confirmPass + " " + password + " " +  emailUser);
        }
    }

    //This method check the input of user for logining to there account
    private static boolean checkCredentials(String username, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Niazi Wall\\Documents\\Account.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length >= 2 && parts[0].equals(username) && parts[2].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
