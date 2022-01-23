package moe.hayden.votebox.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminIndexController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onLoginButtonClick() {
        // TODO: Replace this with a database lookup.
        if (!(usernameField.getText().equals("hayden") && passwordField.getText().equals("P4ssw0rd!"))) {
            // TODO: Handle unsuccessful authentication
            System.out.println("Authentication failure.");
            return;
        }

        System.out.println("Authentication successful.");
    }
}
