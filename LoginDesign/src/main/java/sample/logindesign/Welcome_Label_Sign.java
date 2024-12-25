package sample.logindesign;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Welcome_Label_Sign {
    @FXML
    private Label welcomeLabel;

    // متدی برای تنظیم پیام خوش‌آمدگویی یا پاسخ سرور
    public void setWelcomeMessage(String message) {
        welcomeLabel.setText(message);
    }
}
