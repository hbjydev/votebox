package moe.hayden.votebox.controllers.voter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class VoterIndexController {
    @FXML
    private TextField registrationField;

    @FXML
    protected void onSubmitButtonClick() {
        System.out.println("Voter registration: " + registrationField.getText());
    }
}
