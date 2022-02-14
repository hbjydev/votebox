package moe.hayden.votebox.controllers.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import moe.hayden.votebox.ApplicationState;
import moe.hayden.votebox.FXUtils;
import moe.hayden.votebox.exceptions.InvalidUserException;
import moe.hayden.votebox.providers.AuthenticationProvider;
import moe.hayden.votebox.repositories.UserRepository;

import java.io.IOException;

public class AdminIndexController {
    private ApplicationState state = ApplicationState.getInstance();

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        var authProvider = new AuthenticationProvider();
        try {
            var pwValid = authProvider.login(
                usernameField.getText(),
                passwordField.getText()
            );
            if (!pwValid) {
                var alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid password for user [" + usernameField.getText() + "].");
                alert.show();
                return;
            }
        } catch (InvalidUserException e) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No such user found with username [" + usernameField.getText() + "].");
            alert.show();
            return;
        }

        System.out.println("Authentication successful.");
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Authenticated as user [" + usernameField.getText() + "].");
        alert.show();

        state.setUser(UserRepository.getInstance().findByName(usernameField.getText()).get());
        var stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(FXUtils.getScene("admin/dashboard.fxml"));
        stage.setTitle("Vote Admin");
    }
}
